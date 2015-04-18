package Inventory;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class InventoryListGUI extends JPanel
{

	private JLabel lblHeader, lblSearchBy, lblSearch, lblItemsFound,
			lblNumOfItemsFound;
	private JTextField tfSearch;
	private String strHeader[] = { "Part Number", "Description",
			"<html><center>Quantity<br>(Functional)</center></html>",
			"<html><center>Quantity<br>(Defective)</center></html>",
			"<html><center>Last<br>Cost</center></html>",
			"<html><center>Sister<br>Company<br>Price</center></html>",
			"<html><center>Retail<br>Price</center></html>",
			"<html><center>Walk-in<br>Price</center></html>" };
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbInventory;
	private JScrollPane spInventoryTable;
	private JRadioButton rdbtnPartNumber, rdbtnDescription;
	private ButtonGroup searchBy;
	private JButton btnViewAllItems, btnViewItemProfile, btnAddItemProfile,
			btnDelete, btnClose;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController guiController;
	private String pkey; // for delete
	private InventoryController mainController;

	public InventoryListGUI(GUIController temp)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		guiController = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Inventory");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 130, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search:");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 120, 99, 30);
		add(lblSearch);

		lblItemsFound = new JLabel("Item/s Found: ");
		lblItemsFound.setFont(fntPlainText);
		lblItemsFound.setBounds(30, 160, 147, 30);
		add(lblItemsFound);

		lblNumOfItemsFound = new JLabel("0");
		lblNumOfItemsFound.setFont(fntPlainText);
		lblNumOfItemsFound.setBounds(167, 160, 250, 30);
		add(lblNumOfItemsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(150, 120, 500, 30);
		add(tfSearch);

		tfSearch.getDocument().addDocumentListener(new DocumentListener()
		{

			public void insertUpdate(DocumentEvent de)
			{
				try
				{
					done();
				} catch (Exception ex)
				{

				}
			}

			public void removeUpdate(DocumentEvent de)
			{
				try
				{
					done();
				} catch (Exception ex)
				{

				}
			}

			public void changedUpdate(DocumentEvent de)
			{
				try
				{
					done();
				} catch (Exception ex)
				{

				}
			}

		});

		tbInventory = new JTable()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}

			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column)
			{
				component = super.prepareRenderer(renderer, row, column);
				modelRow = convertRowIndexToModel(row);
				if (!isRowSelected(modelRow))
				{
					component.setBackground(Color.WHITE);
				} else
				{
					component.setBackground(Color.yellow);
				}
				return component;
			}
		};

		tbInventory.getTableHeader().setFont(fntHeaderTableText);
		tbInventory.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbInventory.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbInventory.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbInventory.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbInventory, tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbInventory.setFont(fntPlainText);

		spInventoryTable = new JScrollPane(tbInventory);
		spInventoryTable.setBounds(30, 205, 935, 325);
		add(spInventoryTable);

		tbInventory.getTableHeader().setFont(fntHeaderTableText);
		tbInventory.getParent().setBackground(tbInventory.getBackground());
		tbInventory.getTableHeader().setResizingAllowed(false);
		tbInventory.getTableHeader().setReorderingAllowed(false);
		tbInventory.setColumnSelectionAllowed(true);
		tbInventory.setRowSelectionAllowed(true);
		tbInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbInventory.setRowHeight(30);

		rdbtnPartNumber = new JRadioButton("Part Number");
		rdbtnPartNumber.setActionCommand("Part Number");
		rdbtnPartNumber.setFont(fntPlainText);
		rdbtnPartNumber.setSelected(true);
		rdbtnPartNumber.setBounds(150, 80, 158, 30);
		add(rdbtnPartNumber);
		rdbtnPartNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		rdbtnDescription = new JRadioButton("Description");
		rdbtnDescription.setActionCommand("Description");
		rdbtnDescription.setFont(fntPlainText);
		rdbtnDescription.setBounds(310, 80, 158, 30);
		add(rdbtnDescription);
		rdbtnDescription.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnPartNumber);
		searchBy.add(rdbtnDescription);

		btnViewAllItems = new JButton("View All Items");
		btnViewAllItems.setFont(fntPlainText);
		btnViewAllItems.setBounds(725, 150, 240, 40);
		add(btnViewAllItems);
		btnViewAllItems.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					mainController.ViewAllItems();
				} catch (Exception ex)
				{
					Logger.getLogger(InventoryListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});

		btnViewItemProfile = new JButton("View Item Profile");
		btnViewItemProfile.setFont(fntPlainText);
		btnViewItemProfile.setBounds(30, 545, 210, 40);
		add(btnViewItemProfile);
		btnViewItemProfile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int row;
					row = tbInventory.getSelectedRow();

					if (row == -1)
						JOptionPane.showMessageDialog(null,
								"Please select an item.");
					else
					{
						pkey = tbInventory.getValueAt(row, 0).toString();

						try
						{
							mainController.ViewItemProfile(pkey);
						} catch (SQLException ex)
						{
							Logger.getLogger(InventoryListGUI.class.getName())
									.log(Level.SEVERE, null, ex);
						}
						guiController.changePanelToViewItemProfile();
					}
				} catch (IOException ex)
				{
					Logger.getLogger(InventoryListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});

		btnAddItemProfile = new JButton("Add Item Profile");
		btnAddItemProfile.setFont(fntPlainText);
		btnAddItemProfile.setBounds(300, 545, 217, 40);
		add(btnAddItemProfile);
		btnAddItemProfile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToAddItemProfile();
			}
		});

		btnDelete = new JButton("Delete Item Profile");
		btnDelete.setFont(fntPlainText);
		btnDelete.setBounds(580, 545, 217, 40);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pkey = null;
				int row;
				row = tbInventory.getSelectedRow();

				if (row == -1)
					JOptionPane.showMessageDialog(null,
							"Please select an item.");
				else
				{
					pkey = tbInventory.getValueAt(row, 0).toString();
					try
					{
						confirmMessage("<html><center>Are you sure you want to delete part number: "
								+ pkey
								+ "?<br> Deleted item can not be recovered.</center></html> ");
					} catch (SQLException ex)
					{
						Logger.getLogger(InventoryListGUI.class.getName()).log(
								Level.SEVERE, null, ex);
					} catch (Exception ex)
					{
						Logger.getLogger(InventoryListGUI.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				}
			}
		});

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToMainMenu();
			}
		});
	}

	public void setItemCount(int itemcount)
	{
		lblNumOfItemsFound.setText(Integer.toString(itemcount));
	}

	public void confirmMessage(String question) throws SQLException, Exception
	{
		// JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, question, "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION)
		{

		} else if (response == JOptionPane.YES_OPTION)
		{

			mainController.DeleteItem(pkey);
			// System.out.println("the key "+pkey);
			ViewAll();
			infoMessage("You have successfully deleted item number: " + pkey
					+ ".");
		} else if (response == JOptionPane.CLOSED_OPTION)
		{
			System.out.println("JOptionPane closed");
		}
	}

	public void infoMessage(String info)
	{
		JFrame frame = null;
		JOptionPane.showMessageDialog(frame, info);
	}

	public void setMainController(InventoryController temp)
	{
		this.mainController = temp;
	}

	public void ViewAll() throws Exception
	{
		tfSearch.setText("");
		TableModel AllModel = mainController.getAllModel();
		tbInventory.setModel(AllModel);

		JTableHeader th = tbInventory.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbInventory.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbInventory.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbInventory, tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbInventory.repaint();
	}

	public void done() throws Exception
	{
		if (tfSearch.getText().length() > 0)
		{
			mainController.SearchSomething(tfSearch.getText(), searchBy
					.getSelection().getActionCommand()); // if a key is typed
															// search

		} else if (tfSearch.getText().length() == 0) // if nothing is typed
														// display all
		{
			TableModel AllModel = mainController.getAllModel();
			tbInventory.setModel(AllModel);

			JTableHeader th = tbInventory.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();

			for (int i = 0; i < strHeader.length; i++)
			{
				TableColumn tc = tcm.getColumn(i);
				tc.setHeaderValue(strHeader[i]);
			}
			tbCellRenderer = tbInventory.getTableHeader().getDefaultRenderer();
			tbColumnRenderer = tbInventory.getColumnModel();
			for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
			{
				tbColumn = tbColumnRenderer.getColumn(j);
				tbCellRendererColumn = tbColumn.getHeaderRenderer();
				if (tbCellRendererColumn == null)
					tbCellRendererColumn = tbCellRenderer;
				component = tbCellRendererColumn.getTableCellRendererComponent(
						tbInventory, tbColumn.getHeaderValue(), false, false,
						0, j);
				tbColumn.setPreferredWidth(component.getPreferredSize().width);
			}

			tbInventory.repaint();
		}
	}

	public void setTableModel(TableModel tbm)
	{ // Setting the Headers
		tbInventory.setModel(tbm);
                if(tbInventory.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbInventory.getModel();
                    JTableHeader th = tbInventory.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                }
                else
                {
		JTableHeader th = tbInventory.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();

		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbInventory.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbInventory.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbInventory, tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                }
		tbInventory.repaint();
	}

	class btnAddItemProfile_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// ic.AddItemProfileView();
		}
	}

	class btnDeleteItemProfile_Action implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			pkey = null;
			int row;
			row = tbInventory.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Please select an item.");
			else
			{
				pkey = tbInventory.getValueAt(row, 0).toString();
				try
				{
					// confirmMessage("Are you sure you want to delete Item Profile# "+
					// pkey
					// +" of Inventory?\n Deleted Item Profile can not be recovered. ");
				} catch (Exception ex)
				{

				}

			}
		}
	}

	class btnViewAll_Action implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			try
			{
				mainController.ViewAllItems();
				tfSearch.setText("");
			} catch (Exception ex)
			{
			}
		}
	}

	class btnViewItemProfile_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			pkey = null;
			int row;
			row = tbInventory.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Please select an item.");
			else
			{
				pkey = tbInventory.getValueAt(row, 0).toString();
				try
				{
					mainController.ViewItemProfile(pkey);
				} catch (SQLException ex)
				{
					Logger.getLogger(InventoryListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex)
				{
					Logger.getLogger(InventoryListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
	}

	public static void main(String args[]) throws Exception
	{
		GUIController temp = new GUIController();
		temp.changePanelToInventory();
	}
}

package Collectibles;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.*;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class CollectiblesListGUI extends JPanel
{
	private JLabel lblHeader, lblDisplay, lblCustomer, lblRange,
			lblCollectiblesFound, lblNumOfPayablesFound, lblTo;
	private JTextField tfCustomer;
	private String strMonths[] = { "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" },
			strHeader[] = {
					"Customer Name",
					"      Date      ",
					"Type",
					"<html><center>Sales Invoice/<br> Acknowledgement<br>Receipt<br> Number</center></html>",
					"<html><center>Original<br>Amount</center></html>",
					"<html><center>Current<br>Balance</center></html>",
					"Status" },blank[]={""};
	private JComboBox cmbToYear, cmbToMonth, cmbFromMonth, cmbFromYear;
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbCollectibles;
	private JScrollPane spTable;
	private JCheckBox chckbxActiveCollectibles, chckbxClosedPayables;
	private JButton btnClose, btnViewCollectible, btnViewAllCollectibles,
			btnAddCollectible;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController guiController;
	private CollectiblesController mainController;

	public CollectiblesListGUI(GUIController temp)
	{
		guiController = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Collectibles");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblDisplay = new JLabel("Display: ");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 80, 91, 30);
		add(lblDisplay);

		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 160, 111, 30);
		add(lblCustomer);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 120, 80, 30);
		add(lblRange);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(425, 120, 38, 30);
		add(lblTo);

		lblCollectiblesFound = new JLabel("Collectible/s Found: ");
		lblCollectiblesFound.setFont(fntPlainText);
		lblCollectiblesFound.setBounds(30, 200, 203, 30);
		add(lblCollectiblesFound);

		lblNumOfPayablesFound = new JLabel("0");
		lblNumOfPayablesFound.setFont(fntPlainText);
		lblNumOfPayablesFound.setBounds(230, 200, 250, 30);
		add(lblNumOfPayablesFound);

		tfCustomer = new JTextField();
		tfCustomer.setFont(fntPlainText);
		tfCustomer.setBounds(140, 160, 600, 30);
		add(tfCustomer);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(140, 120, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(315, 120, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(465, 120, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(640, 120, 100, 30);
		add(cmbToYear);

		tbModel = new DefaultTableModel(blank,blank.length);
                tbModel.setColumnCount(1);
                tbModel.setRowCount(0);
                tbModel.addRow(new Object[]{"                                                             No Results Found            "});
                

		chckbxActiveCollectibles = new JCheckBox("Active Collectibles");
		chckbxActiveCollectibles.setFont(fntPlainText);
		chckbxActiveCollectibles.setBounds(140, 80, 222, 30);
		add(chckbxActiveCollectibles);

		chckbxClosedPayables = new JCheckBox("Closed Collectibles");
		chckbxClosedPayables.setFont(fntPlainText);
		chckbxClosedPayables.setBounds(360, 80, 228, 30);
		add(chckbxClosedPayables);

		tbCollectibles = new JTable()
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

		tbCollectibles.getTableHeader().setFont(fntHeaderTableText);
		tbCollectibles.getTableHeader()
				.setPreferredSize(new Dimension(100, 80));
		tbCollectibles.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbCollectibles.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCollectibles.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbCollectibles, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbCollectibles.setFont(fntPlainText);

		spTable = new JScrollPane(tbCollectibles);
		spTable.setBounds(30, 255, 935, 280);
		add(spTable);

		tbCollectibles.getParent()
				.setBackground(tbCollectibles.getBackground());
		tbCollectibles.getTableHeader().setResizingAllowed(false);
		tbCollectibles.getTableHeader().setReorderingAllowed(false);
		tbCollectibles.setColumnSelectionAllowed(true);
		tbCollectibles.setRowSelectionAllowed(true);
		tbCollectibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCollectibles.setRowHeight(30);

		cmbToMonth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfCustomer.setText("");
				if (chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActiveCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbCollectibles.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});

		for (int i = 0; i < strMonths.length; i++)
		{
			cmbFromMonth.addItem(strMonths[i]);
			cmbToMonth.addItem(strMonths[i]);
		}

		cmbFromMonth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfCustomer.setText("");
				if (chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActiveCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbCollectibles.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});
		cmbFromYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfCustomer.setText("");
				if (chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActiveCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbCollectibles.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});

		cmbToYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfCustomer.setText("");
				if (chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActiveCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbCollectibles.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});

		tfCustomer.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent de)
			{
				try
				{
					done();
				} catch (Exception ex)
				{
					Logger.getLogger(CollectiblesListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

			public void removeUpdate(DocumentEvent de)
			{
				try
				{
					done();
				} catch (Exception ex)
				{
					Logger.getLogger(CollectiblesListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

			public void changedUpdate(DocumentEvent de)
			{
				try
				{
					done();
				} catch (Exception ex)
				{
					Logger.getLogger(CollectiblesListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

			public void done() throws Exception
			{
				int type = 0;
				if (tfCustomer.getText().length() > 0)
				{
					if (chckbxActiveCollectibles.isSelected()
							&& chckbxClosedPayables.isSelected()) // both
						type = 0;
					else if (chckbxActiveCollectibles.isSelected()
							&& !chckbxClosedPayables.isSelected()) // customer
						type = 1;
					else if (!chckbxActiveCollectibles.isSelected()
							&& chckbxClosedPayables.isSelected()) // supplier
						type = 2;
					mainController.SearchSomething(
							tfCustomer.getText(),
							type,
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31"); // if a key is typed search

				} else if (tfCustomer.getText().length() == 0) // if nothing is
																// typed display
																// all
				{
					if (chckbxActiveCollectibles.isSelected()
							&& chckbxClosedPayables.isSelected()) // both
						ViewAll();
					else if (chckbxActiveCollectibles.isSelected()
							&& !chckbxClosedPayables.isSelected()) // customer
						mainController.ViewActiveCollectibles(
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					else if (!chckbxActiveCollectibles.isSelected()
							&& chckbxClosedPayables.isSelected()) // supplier
						mainController.ViewClosedCollectibles(
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");

				}
			}

		});

		chckbxActiveCollectibles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfCustomer.setText("");
				if (chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					ViewAll();
				} else if (chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActiveCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbCollectibles.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});

		chckbxClosedPayables.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfCustomer.setText("");
				if (chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					ViewAll();
				} else if (chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActiveCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedCollectibles(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActiveCollectibles.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbCollectibles.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});

		btnViewAllCollectibles = new JButton("View All Collectibles");
		btnViewAllCollectibles.setFont(fntPlainText);
		btnViewAllCollectibles.setBounds(725, 200, 240, 40);
		add(btnViewAllCollectibles);
		btnViewAllCollectibles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// chckbxActiveCollectibles.setSelected(true);
				// chckbxClosedPayables.setSelected(true);
				ViewAll();
			}
		});

		btnViewCollectible = new JButton("View Payment");
		btnViewCollectible.setFont(fntPlainText);
		btnViewCollectible.setBounds(30, 545, 203, 40);
		add(btnViewCollectible);
		btnViewCollectible.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int row;
				String id;
				String type;
				row = tbCollectibles.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(null,
							"Please select an item.");
				else
				{
					id = tbCollectibles.getValueAt(row, 3).toString();
					type = tbCollectibles.getValueAt(row, 2).toString();
					guiController
							.changePanelToViewPaymentCollectibles(id, type);
				}

			}
		});

		btnAddCollectible = new JButton("Add Payment");
		btnAddCollectible.setFont(fntPlainText);
		btnAddCollectible.setBounds(436, 545, 213, 40);
		add(btnAddCollectible);
		btnAddCollectible.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToAddPaymentCollectibles();
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

	public void setMainController(CollectiblesController temp)
	{
		mainController = temp;
	}

	public void setComboBox()
	{
		cmbToYear.removeAllItems();
		cmbFromYear.removeAllItems();
		int cnt = 0;
		if (mainController.getMaxYear() != null
				&& mainController.getMinYear() != null)
		{
			for (int i = Integer.parseInt(mainController.getMinYear()); i <= Integer
					.parseInt(mainController.getMaxYear()); i++)
			{
				cmbToYear.addItem(i);
				cmbFromYear.addItem(i);
				cnt++;
			}
			cmbToYear.setSelectedIndex(cnt - 1);
			cmbFromYear.setSelectedIndex(0);
			cmbFromMonth.setSelectedIndex(0);
			cmbToMonth.setSelectedIndex(11);
		} else
		{
			cmbToYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
			cmbFromYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
		}
	}

	public void setItemCount(int itemcount)
	{
		System.out.println(Integer.toString(itemcount));
		lblNumOfPayablesFound.setText(Integer.toString(itemcount));
	}

	public void ViewAll()
	{
		TableModel AllModel = mainController.getAllModel();
		tbCollectibles.setModel(AllModel);

		JTableHeader th = tbCollectibles.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbCollectibles.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCollectibles.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbCollectibles, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbCollectibles.repaint();
		chckbxActiveCollectibles.setSelected(true);
		chckbxClosedPayables.setSelected(true);
		setComboBox();
	}

	public void setTableModel(TableModel tbm)
	{
                btnViewCollectible.setEnabled(true);
		tbCollectibles.setModel(tbm);
                if(tbCollectibles.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbCollectibles.getModel();
                    JTableHeader th = tbCollectibles.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                    btnViewCollectible.setEnabled(false);
                }
                else
                {
		JTableHeader th = tbCollectibles.getTableHeader();

		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbCollectibles.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCollectibles.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbCollectibles, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                }
		tbCollectibles.repaint();

	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToCollectibles();
	}
}

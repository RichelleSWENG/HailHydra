package Inventory;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Color;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class SetInventoryLastCostGUI extends JPanel
{

	private JLabel lblHeader, lblSearch, lblSearchBy, lblItemsFound,
			lblNumOfItemsFound;
	private JTextField tfSearch;
	private JButton btnViewAllItems, btnSubmit, btnCancel;
	private JRadioButton rdbtnPartNumber, rdbtnDescription;
	private ButtonGroup searchBy;
	private JScrollPane spQuantityTable;
	private String strHeader[] = { "Part Number", "Description",
			"<html><center>Reference<br>Last Cost</center></html>",
			"<html><center>Current<br>Last Cost</center></html>" };
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbSetInventoryQuantity;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private GUIController controller;
	private LastCostController mainController;
	private int modelRow;

	public SetInventoryLastCostGUI(GUIController temp)
	{
		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Set Inventory Last Cost");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 119, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search:");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 120, 78, 30);
		add(lblSearch);

		lblItemsFound = new JLabel("Item/s Found:");
		lblItemsFound.setFont(fntPlainText);
		lblItemsFound.setBounds(30, 160, 131, 30);
		add(lblItemsFound);

		lblNumOfItemsFound = new JLabel("0");
		lblNumOfItemsFound.setFont(fntPlainText);
		lblNumOfItemsFound.setBounds(165, 160, 250, 30);
		add(lblNumOfItemsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(140, 120, 360, 30);
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

			public void done() throws Exception
			{
				if (tfSearch.getText().length() > 0)
				{
					if (rdbtnPartNumber.isSelected())
						mainController.searchSomething(tfSearch.getText(), 0);
					else if (rdbtnDescription.isSelected())
						mainController.searchSomething(tfSearch.getText(), 1);
				} else if (tfSearch.getText().length() == 0)
				{
					ViewAll();
				}
			}
		});

		tbSetInventoryQuantity = new JTable()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if (mColIndex == 3)
					return true;
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
				if (column == 2)
					component.setBackground(Color.lightGray);
				return component;
			}
		};
		tbSetInventoryQuantity.getTableHeader().setFont(fntHeaderTableText);
		tbSetInventoryQuantity.getTableHeader().setPreferredSize(
				new Dimension(100, 55));
		tbSetInventoryQuantity.getTableHeader().setResizingAllowed(false);
		tbSetInventoryQuantity.setFont(fntPlainText);

		tbSetInventoryQuantity.getTableHeader().setFont(fntHeaderTableText);
		tbSetInventoryQuantity.getTableHeader().setPreferredSize(
				new Dimension(100, 55));
		tbSetInventoryQuantity.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbSetInventoryQuantity.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbSetInventoryQuantity.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRenderer = tbColumn.getHeaderRenderer();
			if (tbCellRenderer == null)
				tbCellRenderer = tbCellRenderer;
			component = tbCellRenderer.getTableCellRendererComponent(
					tbSetInventoryQuantity, tbColumn.getHeaderValue(), false,
					false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbSetInventoryQuantity.setFont(fntPlainText);

		spQuantityTable = new JScrollPane(tbSetInventoryQuantity);
		spQuantityTable.setBounds(30, 205, 935, 320);
		add(spQuantityTable);

		tbSetInventoryQuantity.getParent().setBackground(
				tbSetInventoryQuantity.getBackground());
		tbSetInventoryQuantity.getTableHeader().setResizingAllowed(false);
		tbSetInventoryQuantity.getTableHeader().setReorderingAllowed(false);
		tbSetInventoryQuantity.setColumnSelectionAllowed(true);
		tbSetInventoryQuantity.setRowSelectionAllowed(true);
		tbSetInventoryQuantity
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbSetInventoryQuantity.setRowHeight(30);

		rdbtnPartNumber = new JRadioButton("Part Number");
		rdbtnPartNumber.setFont(fntPlainText);
		rdbtnPartNumber.setSelected(true);
		rdbtnPartNumber.setBounds(140, 80, 160, 30);
		add(rdbtnPartNumber);
		rdbtnPartNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText("");
			}
		});

		rdbtnDescription = new JRadioButton("Description");
		rdbtnDescription.setFont(fntPlainText);
		rdbtnDescription.setBounds(310, 80, 157, 30);
		add(rdbtnDescription);
		rdbtnDescription.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText("");
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
				ViewAll();
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToMainMenu();
			}
		});

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(719, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mainController.changeAllLastCost();
				controller.changePanelToMainMenu();
			}
		});
	}

	public void setMainController(LastCostController temp)
	{
		mainController = temp;
	}

	public void setItemCount(int itemcount)
	{
		lblNumOfItemsFound.setText(Integer.toString(itemcount));
	}

	public void changeLastCost()
	{
		for (int i = 0; i < tbSetInventoryQuantity.getRowCount(); i++)
		{
			mainController.changeLastCost(
					tbSetInventoryQuantity.getValueAt(i, 0).toString(),
					tbSetInventoryQuantity.getValueAt(i, 3).toString());
		}
	}

	public void ViewAll()
	{
		tfSearch.setText("");
		TableModel AllModel = mainController.getAllModel();
		tbSetInventoryQuantity.setModel(AllModel);
		JTableHeader th = tbSetInventoryQuantity.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbSetInventoryQuantity.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbSetInventoryQuantity.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbSetInventoryQuantity, tbColumn.getHeaderValue(), false,
					false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbSetInventoryQuantity.repaint();
	}

	public void setTableModel(TableModel tbm)
	{
		tbSetInventoryQuantity.setModel(tbm);
		JTableHeader th = tbSetInventoryQuantity.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbSetInventoryQuantity.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbSetInventoryQuantity.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbSetInventoryQuantity, tbColumn.getHeaderValue(), false,
					false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbSetInventoryQuantity.repaint();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToSetInventoryLastCost();
	}

}

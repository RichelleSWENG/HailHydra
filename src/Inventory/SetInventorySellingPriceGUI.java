package Inventory;

import HailHydra.GUIController;
import java.awt.Dimension;
import java.awt.Font;

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

import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class SetInventorySellingPriceGUI extends JPanel
{

	private JLabel lblHeader, lblSearch, lblSearchBy, lblItemsFound,
			lblNumOfItemsFound;
	private JTextField tfSearch;
	private String strHeader[] = {
			"Part Number",
			"<html><center>Reference<br>Sister Company<br>Price</center></html>",
			"<html><center>Current<br>Sister Company<br>Price</center></html>",
			"<html><center>Reference<br>Retail Price</center></html>",
			"<html><center>Current<br>Retail Price</center></html>",
			"<html><center>Reference<br>Walk-in Price</center></html>",
			"<html><center>Current<br>Walk-in Price</center></html>" };
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JRadioButton rdbtnPartNumber, rdbtnDescription;
	private ButtonGroup searchBy;
	private JTable tbSetInventorySellingPrice;
	private JScrollPane spQuantityTable;
	private JButton btnViewAllItems, btnCancel, btnSubmit;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private GUIController controller;
	private SellingPriceController mainController;
	private int modelRow;

	public SetInventorySellingPriceGUI(GUIController temp)
	{
		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Set Inventory Selling Price");
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

		tbSetInventorySellingPrice = new JTable()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if (mColIndex == 2 || mColIndex == 4 || mColIndex == 6)
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

				if (column == 1 || column == 3 || column == 5)
					component.setBackground(Color.lightGray);
				return component;
			}
		};
		tbSetInventorySellingPrice.getTableHeader().setFont(fntHeaderTableText);
		tbSetInventorySellingPrice.getTableHeader().setPreferredSize(
				new Dimension(100, 55));
		tbSetInventorySellingPrice.getTableHeader().setResizingAllowed(false);
		tbSetInventorySellingPrice.setFont(fntPlainText);

		tbSetInventorySellingPrice.getTableHeader().setFont(fntHeaderTableText);
		tbSetInventorySellingPrice.getTableHeader().setPreferredSize(
				new Dimension(100, 55));
		tbSetInventorySellingPrice.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbSetInventorySellingPrice.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbSetInventorySellingPrice.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbSetInventorySellingPrice, tbColumn.getHeaderValue(),
					false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbSetInventorySellingPrice.setFont(fntPlainText);

		spQuantityTable = new JScrollPane(tbSetInventorySellingPrice);
		spQuantityTable.setBounds(30, 205, 935, 320);
		add(spQuantityTable);

		tbSetInventorySellingPrice.getParent().setBackground(
				tbSetInventorySellingPrice.getBackground());
		tbSetInventorySellingPrice.getTableHeader().setResizingAllowed(false);
		tbSetInventorySellingPrice.getTableHeader().setReorderingAllowed(false);
		tbSetInventorySellingPrice.setColumnSelectionAllowed(true);
		tbSetInventorySellingPrice.setRowSelectionAllowed(true);
		tbSetInventorySellingPrice
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbSetInventorySellingPrice.setRowHeight(30);

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
		rdbtnDescription.setBounds(300, 80, 157, 30);
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
				mainController.changeAllPrices();
				controller.changePanelToMainMenu();
			}
		});
	}

	public void setTableModel(TableModel tbm)
	{
		tbSetInventorySellingPrice.setModel(tbm);
		JTableHeader th = tbSetInventorySellingPrice.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbSetInventorySellingPrice.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbSetInventorySellingPrice.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbSetInventorySellingPrice, tbColumn.getHeaderValue(),
					false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbSetInventorySellingPrice.repaint();
	}

	public void changePrices()
	{
		for (int i = 0; i < tbSetInventorySellingPrice.getRowCount(); i++)
		{
			mainController.changeSisterCompanyPrice(tbSetInventorySellingPrice
					.getValueAt(i, 0).toString(), tbSetInventorySellingPrice
					.getValueAt(i, 2).toString());
			mainController.changeTradersPrice(tbSetInventorySellingPrice
					.getValueAt(i, 0).toString(), tbSetInventorySellingPrice
					.getValueAt(i, 4).toString());
			mainController.changeWalkInPrices(tbSetInventorySellingPrice
					.getValueAt(i, 0).toString(), tbSetInventorySellingPrice
					.getValueAt(i, 6).toString());
		}
	}

	public void ViewAll()
	{
		tfSearch.setText("");
		TableModel AllModel = mainController.getAllModel();
		tbSetInventorySellingPrice.setModel(AllModel);

		JTableHeader th = tbSetInventorySellingPrice.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbSetInventorySellingPrice.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbSetInventorySellingPrice.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbSetInventorySellingPrice, tbColumn.getHeaderValue(),
					false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbSetInventorySellingPrice.repaint();
	}

	public void setMainController(SellingPriceController temp)
	{
		mainController = temp;
	}

	public void setItemCount(int itemcount)
	{
		lblNumOfItemsFound.setText(Integer.toString(itemcount));
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToSetInventorySellingPrice();
	}
}

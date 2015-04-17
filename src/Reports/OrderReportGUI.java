package Reports;

import java.awt.Component;
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

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class OrderReportGUI extends JPanel
{
	private JLabel lblHeader, lblSearchBy, lblSearch, lblItemsFound,
			lblNumOfItemsFound;
	private JTextField tfSearch;
	private String strHeader[] = { "Part Number", "Description",
			"<html><center>Stock<br>Minimum</center></html>",
			"<html><center>Quantity<br>(Functional)</center></html>",
			"<html><center>Last<br>Cost</center></html>",
			"<html><center>Rack<br>Location</center></html>" };
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbOrderReport;
	private JScrollPane spReportsTable;
	private JRadioButton rdbtnPartNumber, rdbtnDescription;
	private ButtonGroup searchBy;
	private JButton btnAddPurchaseTransaction, btnClose, btnViewAllItems;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController controller;
	private ReportController mainController;

	public OrderReportGUI(GUIController temp)
	{

		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Order Report");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 130, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search:");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 120, 89, 30);
		add(lblSearch);

		lblItemsFound = new JLabel("Item/s Found: ");
		lblItemsFound.setFont(fntPlainText);
		lblItemsFound.setBounds(30, 160, 142, 30);
		add(lblItemsFound);

		lblNumOfItemsFound = new JLabel("0");
		lblNumOfItemsFound.setFont(fntPlainText);
		lblNumOfItemsFound.setBounds(165, 160, 250, 30);
		add(lblNumOfItemsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(145, 120, 284, 30);
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
						mainController.SearchSomethingfromOrder(
								tfSearch.getText(), 0);
					else if (rdbtnDescription.isSelected())
						mainController.SearchSomethingfromOrder(
								tfSearch.getText(), 1);
				} else if (tfSearch.getText().length() == 0) // if nothing is
																// typed display
																// all
				{
					ViewAll();
				}
			}
		});

		tbOrderReport = new JTable()
		{
			public boolean isCellEditable(int row, int column)
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

		tbOrderReport.getTableHeader().setFont(fntHeaderTableText);
		tbOrderReport.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbOrderReport.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbOrderReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbOrderReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbOrderReport, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbOrderReport.setFont(fntPlainText);

		spReportsTable = new JScrollPane(tbOrderReport);
		spReportsTable.setBounds(30, 205, 935, 320);
		add(spReportsTable);

		tbOrderReport.getParent().setBackground(tbOrderReport.getBackground());
		tbOrderReport.getTableHeader().setResizingAllowed(false);
		tbOrderReport.getTableHeader().setReorderingAllowed(false);
		tbOrderReport.setColumnSelectionAllowed(true);
		tbOrderReport.setRowSelectionAllowed(true);
		tbOrderReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbOrderReport.setRowHeight(30);

		rdbtnPartNumber = new JRadioButton("Part Number");
		rdbtnPartNumber.setFont(fntPlainText);
		rdbtnPartNumber.setSelected(true);
		rdbtnPartNumber.setBounds(145, 76, 147, 30);
		add(rdbtnPartNumber);
		rdbtnPartNumber.addActionListener(new ActionListener()
		{// Everytime All is selected
					public void actionPerformed(ActionEvent e)
					{
						tfSearch.setText(null);
					}
				});

		rdbtnDescription = new JRadioButton("Description");
		rdbtnDescription.setFont(fntPlainText);
		rdbtnDescription.setBounds(300, 76, 134, 30);
		add(rdbtnDescription);
		rdbtnDescription.addActionListener(new ActionListener()
		{// Everytime All is selected
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
		{// Everytime All is selected
					public void actionPerformed(ActionEvent e)
					{
						ViewAll();
					}
				});

		btnAddPurchaseTransaction = new JButton("Add Purchase Transaction");
		btnAddPurchaseTransaction.setFont(fntPlainText);
		btnAddPurchaseTransaction.setBounds(455, 545, 310, 40);
		add(btnAddPurchaseTransaction);
		btnAddPurchaseTransaction.addActionListener(new ActionListener()
		{// Everytime All is selected
					public void actionPerformed(ActionEvent e)
					{
						controller.changePanelToAddPurchaseTransaction();
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
				controller.changePanelToMainMenu();
			}
		});

	}

	public void setItemCount(int itemcount)
	{
		lblNumOfItemsFound.setText(Integer.toString(itemcount));
	}

	public void setTableModel(TableModel tbm)
	{
		tbOrderReport.setModel(tbm);
		JTableHeader th = tbOrderReport.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbOrderReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbOrderReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbOrderReport, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbOrderReport.repaint();
	}

	public void setMainController(ReportController temp)
	{
		mainController = temp;
	}

	public void ViewAll()
	{
		tfSearch.setText("");
		TableModel AllModel = mainController.getAllOrderModel();
		tbOrderReport.setModel(AllModel);

		JTableHeader th = tbOrderReport.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbOrderReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbOrderReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbOrderReport, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbOrderReport.repaint();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToOrderReport();
	}
}

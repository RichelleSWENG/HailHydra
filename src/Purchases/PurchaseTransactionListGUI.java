package Purchases;

import HailHydra.GUIController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class PurchaseTransactionListGUI extends JPanel
{

	private JLabel lblHeader, lblSearchBy, lblSearch, lblRange, lblTo,
			lblTransactionsFound, lblNumofTransactions;
	private JTextField tfSearch;
	JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
	private String strHeader[] = { "        Supplier Name        ", "Date",
			"<html><center>Purchase<br>Transaction<br>Number</center></html>",
			"<html><center>Original<br>Amount</center></html>",
			"<html><center>Current<br>Balance</center></html>" }, months[] = {
			"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbPurchaseTransaction;
	private JScrollPane spTable;
	private JRadioButton rdbtnSupplierName, rdbtnPurchaseTransactionNum,
			rdbtnPartNumber;
	private ButtonGroup searchBy;
	private JButton btnViewAllTransactions, btnAddPurchaseTransaction,
			btnViewPurchaseTransaction, btnClose;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController controller;
	public PurchaseTransactionController mainController;

	public PurchaseTransactionListGUI(GUIController temp)
	{
		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Purchase Transaction");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 121, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search: ");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 160, 84, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 120, 80, 30);
		add(lblRange);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(425, 120, 36, 30);
		add(lblTo);

		lblTransactionsFound = new JLabel("Transaction/s Found:");
		lblTransactionsFound.setFont(fntPlainText);
		lblTransactionsFound.setBounds(30, 200, 219, 30);
		add(lblTransactionsFound);

		lblNumofTransactions = new JLabel("0");
		lblNumofTransactions.setFont(fntPlainText);
		lblNumofTransactions.setBounds(250, 200, 250, 30);
		add(lblNumofTransactions);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(150, 160, 650, 30);
		add(tfSearch);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(150, 120, 140, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(300, 120, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(471, 120, 140, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(621, 120, 100, 30);
		add(cmbToYear);
		for (int i = 0; i < months.length; i++)
		{
			cmbFromMonth.addItem(months[i]);
			cmbToMonth.addItem(months[i]);
		}
		cmbToYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSearch.setText("");
				mainController.searchbyDate(
						cmbFromYear.getSelectedItem() + "-"
								+ (cmbFromMonth.getSelectedIndex() + 1) + "-01",
						cmbToYear.getSelectedItem() + "-"
								+ (cmbToMonth.getSelectedIndex() + 1) + "-31");

			}

		});
		cmbToMonth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSearch.setText("");
				mainController.searchbyDate(
						cmbFromYear.getSelectedItem() + "-"
								+ (cmbFromMonth.getSelectedIndex() + 1) + "-01",
						cmbToYear.getSelectedItem() + "-"
								+ (cmbToMonth.getSelectedIndex() + 1) + "-31");

			}

		});
		cmbFromMonth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSearch.setText("");
				mainController.searchbyDate(
						cmbFromYear.getSelectedItem() + "-"
								+ (cmbFromMonth.getSelectedIndex() + 1) + "-01",
						cmbToYear.getSelectedItem() + "-"
								+ (cmbToMonth.getSelectedIndex() + 1) + "-31");

			}

		});
		cmbFromYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSearch.setText(null);
				mainController.searchbyDate(
						cmbFromYear.getSelectedItem() + "-"
								+ (cmbFromMonth.getSelectedIndex() + 1) + "-01",
						cmbToYear.getSelectedItem() + "-"
								+ (cmbToMonth.getSelectedIndex() + 1) + "-31");

			}

		});
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
					if (rdbtnSupplierName.isSelected())
					{
						mainController.SearchSomething(tfSearch.getText(), 0,
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					} else if (rdbtnPurchaseTransactionNum.isSelected())
					{
						mainController.SearchSomething(tfSearch.getText(), 1,
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					} else if (rdbtnPartNumber.isSelected())
					{
						mainController.SearchSomething(tfSearch.getText(), 2,
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					}
				} else if (tfSearch.getText().length() == 0) // if nothing is
																// typed display
																// all
				{
					mainController.searchbyDate(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				}
			}
		});

		tbPurchaseTransaction = new JTable()
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

		tbPurchaseTransaction.getTableHeader().setFont(fntHeaderTableText);
		tbPurchaseTransaction.getTableHeader().setPreferredSize(
				new Dimension(100, 55));
		tbPurchaseTransaction.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbPurchaseTransaction.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbPurchaseTransaction.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
			{
				tbCellRendererColumn = tbCellRenderer;
			}
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbPurchaseTransaction, tbColumn.getHeaderValue(), false,
					false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbPurchaseTransaction.setFont(fntPlainText);

		spTable = new JScrollPane(tbPurchaseTransaction);
		spTable.setBounds(30, 245, 935, 285);
		add(spTable);

		tbPurchaseTransaction.getParent().setBackground(
				tbPurchaseTransaction.getBackground());
		tbPurchaseTransaction.getTableHeader().setResizingAllowed(false);
		tbPurchaseTransaction.getTableHeader().setReorderingAllowed(false);
		tbPurchaseTransaction.setColumnSelectionAllowed(true);
		tbPurchaseTransaction.setRowSelectionAllowed(true);
		tbPurchaseTransaction
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPurchaseTransaction.setRowHeight(30);

		rdbtnSupplierName = new JRadioButton("Supplier Name");
		rdbtnSupplierName.setFont(fntPlainText);
		rdbtnSupplierName.setSelected(true);
		rdbtnSupplierName.setBounds(150, 80, 180, 30);
		add(rdbtnSupplierName);
		rdbtnSupplierName.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		rdbtnPurchaseTransactionNum = new JRadioButton(
				"Purchase Transaction Number");
		rdbtnPurchaseTransactionNum.setFont(fntPlainText);
		rdbtnPurchaseTransactionNum.setBounds(330, 80, 320, 30);
		add(rdbtnPurchaseTransactionNum);
		rdbtnPurchaseTransactionNum.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		rdbtnPartNumber = new JRadioButton("Part Number");
		rdbtnPartNumber.setFont(fntPlainText);
		rdbtnPartNumber.setBounds(655, 80, 352, 30);
		add(rdbtnPartNumber);
		rdbtnPartNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnSupplierName);
		searchBy.add(rdbtnPurchaseTransactionNum);
		searchBy.add(rdbtnPartNumber);

		btnViewAllTransactions = new JButton("View All Transactions");
		btnViewAllTransactions.setFont(fntPlainText);
		btnViewAllTransactions.setBounds(725, 195, 240, 40);
		add(btnViewAllTransactions);
		btnViewAllTransactions.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewAll();
			}
		});

		btnViewPurchaseTransaction = new JButton("View Purchase Transaction");
		btnViewPurchaseTransaction.setFont(fntPlainText);
		btnViewPurchaseTransaction.setBounds(30, 545, 300, 40);
		add(btnViewPurchaseTransaction);
		btnViewPurchaseTransaction.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int row;
				row = tbPurchaseTransaction.getSelectedRow();
				if (row == -1)
				{
					JOptionPane.showMessageDialog(null,
							"Please select an item.");
				} else
				{
					mainController.setPurchaseTransaction(mainController
							.getPT(tbPurchaseTransaction.getValueAt(row, 2)
									.toString()));
					controller.changePanelToViewPurchaseTransaction();
				}

			}
		});

		btnAddPurchaseTransaction = new JButton("Add Purchase Transaction");
		btnAddPurchaseTransaction.setFont(fntPlainText);
		btnAddPurchaseTransaction.setBounds(420, 545, 290, 40);
		add(btnAddPurchaseTransaction);
		btnAddPurchaseTransaction.addActionListener(new ActionListener()
		{
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
		lblNumofTransactions.setText(Integer.toString(itemcount));
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

	public void setTableModel(TableModel tbm)
	{ 
                btnViewPurchaseTransaction.setEnabled(true);
		tbPurchaseTransaction.setModel(tbm);
                if(tbPurchaseTransaction.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbPurchaseTransaction.getModel();
                    JTableHeader th = tbPurchaseTransaction.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                    btnViewPurchaseTransaction.setEnabled(false);
                }
                else
                {
		JTableHeader th = tbPurchaseTransaction.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 5; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
                tbCellRenderer = tbPurchaseTransaction.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbPurchaseTransaction.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbPurchaseTransaction, tbColumn.getHeaderValue(), false, false,
					0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                }
		tbPurchaseTransaction.repaint();
	}

	public void setMainController(PurchaseTransactionController temp)
	{
		mainController = temp;
	}

	public void ViewAll()
	{
		TableModel AllModel = mainController.getAllModel();
		tbPurchaseTransaction.setModel(AllModel);

		JTableHeader th = tbPurchaseTransaction.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbPurchaseTransaction.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbPurchaseTransaction.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbPurchaseTransaction, tbColumn.getHeaderValue(), false,
					false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbPurchaseTransaction.repaint();
		setComboBox();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToPurchaseTransactionList();
	}

}

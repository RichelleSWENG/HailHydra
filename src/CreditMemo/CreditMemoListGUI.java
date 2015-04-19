package CreditMemo;

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

import HailHydra.GUIController;
import ReturnSlip.ReturnSlipController;
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

public class CreditMemoListGUI extends JPanel
{
	private JLabel lblHeader, lblSearchBy, lblSearch, lblRange, lblTo,
			lblMemosFound, lblNumOfMemosFound;
	private JTextField tfSearch;
	private String strHeader[] = { "Date",
			"<html><center>Credit Memo<br>Number</center><html>",
			"Supplier Name",
			"<html><center>Return Slip<br>Number</center><html>", "Amount" },
			strMonths[] = { "January", "February", "March", "April", "May",
					"June", "July", "August", "September", "October",
					"November", "December" };
	private JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbCreditMemo;
	private JScrollPane spTable;
	private JRadioButton rdbtnSupplierName, rdbtnCreditMemoNo,
			rdbtnReturnSlipNo;
	private ButtonGroup searchBy;
	private JButton btnViewAllMemos, btnViewCreditMemo, btnClose;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController controller;
	private CreditMemoController mainController;
	private ReturnSlipController RSController;

	public CreditMemoListGUI(GUIController temp)
	{
		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Credit Memo");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 123, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search: ");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 160, 100, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 120, 83, 30);
		add(lblRange);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(429, 120, 36, 30);
		add(lblTo);

		lblMemosFound = new JLabel("Memo/s Found:");
		lblMemosFound.setFont(fntPlainText);
		lblMemosFound.setBounds(30, 200, 162, 30);
		add(lblMemosFound);

		lblNumOfMemosFound = new JLabel("0");
		lblNumOfMemosFound.setFont(fntPlainText);
		lblNumOfMemosFound.setBounds(180, 200, 75, 30);
		add(lblNumOfMemosFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(140, 160, 606, 30);
		add(tfSearch);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(140, 120, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(305, 120, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(481, 120, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(646, 120, 100, 30);
		add(cmbToYear);

		for (int i = 0; i < strMonths.length; i++)
		{
			cmbFromMonth.addItem(strMonths[i]);
			cmbToMonth.addItem(strMonths[i]);
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
						mainController.SearchSomething(tfSearch.getText(), 0,
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					else if (rdbtnCreditMemoNo.isSelected())
						mainController.SearchSomething(tfSearch.getText(), 1,
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					else if (rdbtnReturnSlipNo.isSelected())
						mainController.SearchSomething(tfSearch.getText(), 2,
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
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

		tbCreditMemo = new JTable()
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

		tbCreditMemo.getTableHeader().setFont(fntHeaderTableText);
		tbCreditMemo.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbCreditMemo.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbCreditMemo.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCreditMemo.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn
					.getTableCellRendererComponent(tbCreditMemo,
							tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbCreditMemo.setFont(fntPlainText);

		spTable = new JScrollPane(tbCreditMemo);
		spTable.setBounds(30, 245, 935, 285);
		add(spTable);

		tbCreditMemo.getParent().setBackground(tbCreditMemo.getBackground());
		tbCreditMemo.getTableHeader().setResizingAllowed(false);
		tbCreditMemo.getTableHeader().setReorderingAllowed(false);
		tbCreditMemo.setColumnSelectionAllowed(true);
		tbCreditMemo.setRowSelectionAllowed(true);
		tbCreditMemo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCreditMemo.setRowHeight(30);

		rdbtnSupplierName = new JRadioButton("Supplier Name");
		rdbtnSupplierName.setFont(fntPlainText);
		rdbtnSupplierName.setSelected(true);
		rdbtnSupplierName.setBounds(139, 80, 170, 25);
		add(rdbtnSupplierName);
		rdbtnSupplierName.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		rdbtnCreditMemoNo = new JRadioButton("Credit Memo Number");
		rdbtnCreditMemoNo.setFont(fntPlainText);
		rdbtnCreditMemoNo.setBounds(305, 80, 230, 25);
		add(rdbtnCreditMemoNo);
		rdbtnCreditMemoNo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		rdbtnReturnSlipNo = new JRadioButton("Return Slip Number");
		rdbtnReturnSlipNo.setFont(fntPlainText);
		rdbtnReturnSlipNo.setBounds(539, 80, 235, 25);
		add(rdbtnReturnSlipNo);
		rdbtnReturnSlipNo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText(null);
			}
		});

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnSupplierName);
		searchBy.add(rdbtnCreditMemoNo);
		searchBy.add(rdbtnReturnSlipNo);

		btnViewAllMemos = new JButton("View All Memos");
		btnViewAllMemos.setFont(fntPlainText);
		btnViewAllMemos.setBounds(725, 195, 240, 40);
		add(btnViewAllMemos);
		btnViewAllMemos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewAll();
			}
		});

		btnViewCreditMemo = new JButton("View Credit Memo");
		btnViewCreditMemo.setFont(fntPlainText);
		btnViewCreditMemo.setBounds(30, 540, 238, 40);
		add(btnViewCreditMemo);
		btnViewCreditMemo.addActionListener(new ActionListener()
		{
			private String returnslipID;
			private String creditmemoID;

			public void actionPerformed(ActionEvent e)
			{
				int row;
				row = tbCreditMemo.getSelectedRow();

				if (row == -1)
					JOptionPane.showMessageDialog(null,
							"Please select an item.");
				else
				{
					returnslipID = tbCreditMemo.getValueAt(row, 3).toString();
					creditmemoID = tbCreditMemo.getValueAt(row, 1).toString();

					RSController.setSlipTarget(RSController.getRS(returnslipID));
					mainController.setCMTarget(mainController
							.getCM(creditmemoID));
					controller.changePanelToViewCreditMemo();
				}

			}
		});

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(850, 545, 110, 40);
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
		lblNumOfMemosFound.setText(Integer.toString(itemcount));
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
                btnViewCreditMemo.setEnabled(true);
		tbCreditMemo.setModel(tbm);
                if(tbCreditMemo.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbCreditMemo.getModel();
                    JTableHeader th = tbCreditMemo.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                    btnViewCreditMemo.setEnabled(false);
                }
                else
                {
		JTableHeader th = tbCreditMemo.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbCreditMemo.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCreditMemo.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn
					.getTableCellRendererComponent(tbCreditMemo,
							tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                }
		tbCreditMemo.repaint();
	}

	public void setMainController(CreditMemoController temp)
	{
		mainController = temp;
	}

	public void ViewAll()
	{
		tfSearch.setText("");
		TableModel AllModel = mainController.getAllModel();
		tbCreditMemo.setModel(AllModel);
		JTableHeader th = tbCreditMemo.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbCreditMemo.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCreditMemo.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn
					.getTableCellRendererComponent(tbCreditMemo,
							tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbCreditMemo.repaint();
		setComboBox();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToCreditMemo();
	}

	public void setRSController(ReturnSlipController RSController)
	{
		this.RSController = RSController;
	}

}

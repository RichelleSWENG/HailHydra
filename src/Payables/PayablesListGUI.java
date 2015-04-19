package Payables;

import HailHydra.GUIController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class PayablesListGUI extends JPanel
{
	private JLabel lblHeader, lblDisplay, lblSupplier, lblRrange,
			lblPayablesFound, lblNumOfPayablesFound, lblTo;
	private JTextField tfSupplier;
	private String strHeader[] = { "            Supplier Name            ", "      Date    ",
			"<html><center>Purchase<br>Transaction<br>Number</center></html>",
			"<html><center> Original <br> Amount </center></html>",
			"<html><center> Current <br> Balance </center></html>", "Status" },
			strMonths[] = { "January", "February", "March", "April", "May",
					"June", "July", "August", "September", "October",
					"November", "December" };
	private JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbPayables;
	private JScrollPane spPayablesTable;
	private JCheckBox chckbxActivePayables, chckbxClosedPayables;
	private JButton btnClose, btnAddPayment, btnViewAllPayables,
			btnViewPayment;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController controller;
	private PayablesController mainController;
	private PaymentController paymentController;

	public PayablesListGUI(GUIController temp)
	{
		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Payables");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblDisplay = new JLabel("Display: ");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 80, 87, 30);
		add(lblDisplay);

		lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(30, 160, 107, 30);
		add(lblSupplier);

		lblRrange = new JLabel("Range:");
		lblRrange.setFont(fntPlainText);
		lblRrange.setBounds(30, 120, 87, 30);
		add(lblRrange);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(402, 120, 36, 30);
		add(lblTo);

		lblPayablesFound = new JLabel("Payable/s Found: ");
		lblPayablesFound.setFont(fntPlainText);
		lblPayablesFound.setBounds(30, 200, 176, 30);
		add(lblPayablesFound);

		lblNumOfPayablesFound = new JLabel("0");
		lblNumOfPayablesFound.setFont(fntPlainText);
		lblNumOfPayablesFound.setBounds(205, 200, 250, 30);
		add(lblNumOfPayablesFound);

		tfSupplier = new JTextField();
		tfSupplier.setFont(fntPlainText);
		tfSupplier.setBounds(125, 160, 585, 30);
		add(tfSupplier);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(125, 120, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(295, 120, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(440, 120, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(610, 120, 100, 30);
		add(cmbToYear);

		for (int i = 0; i < strMonths.length; i++)
		{
			cmbFromMonth.addItem(strMonths[i]);
			cmbToMonth.addItem(strMonths[i]);
		}

		cmbFromMonth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSupplier.setText("");
				if (chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActivePayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedPayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbPayables.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});
		cmbFromYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSupplier.setText("");
				if (chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActivePayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedPayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbPayables.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});

		cmbToMonth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSupplier.setText("");
				if (chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActivePayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedPayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbPayables.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});
		cmbToYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSupplier.setText("");
				if (chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.DateSearch(cmbFromYear.getSelectedItem()
							+ "-" + (cmbFromMonth.getSelectedIndex() + 1)
							+ "-01", cmbToYear.getSelectedItem() + "-"
							+ (cmbToMonth.getSelectedIndex() + 1) + "-31");
				} else if (chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActivePayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedPayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					tbPayables.setModel(tbModel);
					lblNumOfPayablesFound.setText("0");
				}

			}

		});

		tfSupplier.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent de)
			{
				try
				{
					done();
				} catch (Exception ex)
				{
					Logger.getLogger(PayablesListGUI.class.getName()).log(
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
					Logger.getLogger(PayablesListGUI.class.getName()).log(
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
					Logger.getLogger(PayablesListGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

			public void done() throws Exception
			{
				int type = 0;
				if (tfSupplier.getText().length() > 0)
				{
					if (chckbxActivePayables.isSelected()
							&& chckbxClosedPayables.isSelected()) // both
						type = 0;
					else if (chckbxActivePayables.isSelected()
							&& !chckbxClosedPayables.isSelected()) // customer
						type = 1;
					else if (!chckbxActivePayables.isSelected()
							&& chckbxClosedPayables.isSelected()) // supplier
						type = 2;
					mainController.SearchSomething(
							tfSupplier.getText(),
							type,
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31"); // if a key is typed search

				} else if (tfSupplier.getText().length() == 0) // if nothing is
																// typed display
																// all
				{
					if (chckbxActivePayables.isSelected()
							&& chckbxClosedPayables.isSelected()) // both
						ViewAll();
					else if (chckbxActivePayables.isSelected()
							&& !chckbxClosedPayables.isSelected()) // customer
						mainController.ViewActivePayables(
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					else if (!chckbxActivePayables.isSelected()
							&& chckbxClosedPayables.isSelected()) // supplier
						mainController.ViewClosedPayables(
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");

				}
			}

		});
                tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};

		tbModel.setRowCount(0);

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}

		tbPayables = new JTable(tbModel)
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
		tbPayables.getTableHeader().setFont(fntHeaderTableText);
		tbPayables.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbPayables.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbPayables.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbPayables.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbPayables, tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbPayables.setFont(fntPlainText);
		spPayablesTable = new JScrollPane(tbPayables);
		spPayablesTable.setBounds(30, 238, 935, 294);
		add(spPayablesTable);

		tbPayables.getParent().setBackground(tbPayables.getBackground());
		tbPayables.getTableHeader().setResizingAllowed(false);
		tbPayables.getTableHeader().setReorderingAllowed(false);
		tbPayables.setColumnSelectionAllowed(true);
		tbPayables.setRowSelectionAllowed(true);
		tbPayables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPayables.setRowHeight(30);

		chckbxActivePayables = new JCheckBox("Active Payables");
		chckbxActivePayables.setSelected(true);
		chckbxActivePayables.setFont(fntPlainText);
		chckbxActivePayables.setBounds(125, 80, 194, 30);
		add(chckbxActivePayables);
		chckbxActivePayables.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSupplier.setText("");
				if (chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					ViewAll();
				} else if (chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActivePayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedPayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
                                        lblNumOfPayablesFound.setText("0");
					setTableModel(tbModel);
				}

			}

		});

		chckbxClosedPayables = new JCheckBox("Closed Payables");
		chckbxClosedPayables.setSelected(true);
		chckbxClosedPayables.setFont(fntPlainText);
		chckbxClosedPayables.setBounds(315, 80, 201, 30);
		add(chckbxClosedPayables);
		chckbxClosedPayables.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				tfSupplier.setText("");
				if (chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					ViewAll();
				} else if (chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
					mainController.ViewActivePayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& chckbxClosedPayables.isSelected())
				{
					mainController.ViewClosedPayables(
							cmbFromYear.getSelectedItem() + "-"
									+ (cmbFromMonth.getSelectedIndex() + 1)
									+ "-01", cmbToYear.getSelectedItem() + "-"
									+ (cmbToMonth.getSelectedIndex() + 1)
									+ "-31");
				} else if (!chckbxActivePayables.isSelected()
						&& !chckbxClosedPayables.isSelected())
				{
                                        lblNumOfPayablesFound.setText("0");
					setTableModel(tbModel);
				}

			}

		});

		btnViewAllPayables = new JButton("View All Payables");
		btnViewAllPayables.setFont(fntPlainText);
		btnViewAllPayables.setBounds(725, 190, 240, 40);
		add(btnViewAllPayables);
		btnViewAllPayables.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				chckbxActivePayables.setSelected(true);
				chckbxClosedPayables.setSelected(true);
				ViewAll();
			}
		});

		btnViewPayment = new JButton("View Payment");
		btnViewPayment.setFont(fntPlainText);
		btnViewPayment.setBounds(30, 545, 180, 40);
		add(btnViewPayment);
		btnViewPayment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int row;
				String id;
				row = tbPayables.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(null,
							"Please select an item.");
				else
				{
					id = tbPayables.getValueAt(row, 2).toString();
					controller.changePanelToViewPaymentPayables(id);
				}
			}
		});

		btnAddPayment = new JButton("Add Payment");
		btnAddPayment.setFont(fntPlainText);
		btnAddPayment.setBounds(431, 545, 190, 40);
		add(btnAddPayment);
		btnAddPayment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToAddPaymentPayables();
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

	public void setMainController(PayablesController temp)
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
		lblNumOfPayablesFound.setText(Integer.toString(itemcount));
	}

	public void ViewAll()
	{
		TableModel AllModel = mainController.getAllModel();
		tbPayables.setModel(AllModel);

		JTableHeader th = tbPayables.getTableHeader(); // Setting the Headers
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 6; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		th.repaint();
		setComboBox();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToPayablesList();
	}

	public void setTableModel(TableModel tbm)
	{ 
                btnViewPayment.setEnabled(true);
		tbPayables.setModel(tbm);
                 if(tbPayables.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbPayables.getModel();
                    JTableHeader th = tbPayables.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                    btnViewPayment.setEnabled(false);
                }
                else
                {
                DefaultTableModel model = (DefaultTableModel) tbPayables.getModel();
                model.setColumnCount(6);
		JTableHeader th = tbPayables.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 6; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
                if(tbPayables.getValueAt(0, 0).equals("                                                             No Results Found            ")) // do not change
                {
                    model.setColumnCount(1); 
                    tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                }
                tbCellRenderer = tbPayables.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbPayables.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbPayables, tbColumn.getHeaderValue(), false, false,
					0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                }
		tbPayables.repaint();
	}
}

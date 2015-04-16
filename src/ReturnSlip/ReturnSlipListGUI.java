package ReturnSlip;

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

public class ReturnSlipListGUI extends JPanel {

	private JLabel lblHeader, lblSearchBy, lblSearch, lblRange, lblTo,
			lblSlipsFound, lblNumOfSlipsFound;
	private String strHeader[] = { "Date",
			"<html><center>Return Slip<br>Number</center></html>",
			"Supplier Name", "Part Number", "Quantity", "Amount" },
			strMonths[] = { "January", "February", "March", "April", "May",
					"June", "July", "August", "September", "October",
					"November", "December" };
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbReturnSlip;
	private JScrollPane spTable;
	private JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
	private JRadioButton rdbtnSupplierName, rdbtnReturnSlipNo, rdbtnPartNo;
	private ButtonGroup searchBy;
	private JTextField tfSearch;
	private JButton btnViewAllSlips, btnAddCreditMemo, btnAddReturnSlip,
			btnViewReturnSlip, btnClose;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController guiController;
	private ReturnSlipController mainController;
        private String returnslipID;

	public ReturnSlipListGUI(GUIController temp) {

		guiController = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Return Slip");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 118, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search: ");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 160, 100, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 120, 84, 30);
		add(lblRange);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(435, 120, 36, 30);
		add(lblTo);

		lblSlipsFound = new JLabel("Slip/s Found:");
		lblSlipsFound.setFont(fntPlainText);
		lblSlipsFound.setBounds(30, 200, 149, 30);
		add(lblSlipsFound);

		lblNumOfSlipsFound = new JLabel("0");
		lblNumOfSlipsFound.setFont(fntPlainText);
		lblNumOfSlipsFound.setBounds(170, 200, 250, 30);
		add(lblNumOfSlipsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(160, 160, 585, 30);
		add(tfSearch);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(160, 120, 149, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(319, 120, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(481, 120, 149, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(645, 120, 100, 30);
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
		cmbToMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
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
					else if (rdbtnReturnSlipNo.isSelected())
						mainController.SearchSomething(tfSearch.getText(), 1,
								cmbFromYear.getSelectedItem() + "-"
										+ (cmbFromMonth.getSelectedIndex() + 1)
										+ "-01", cmbToYear.getSelectedItem()
										+ "-"
										+ (cmbToMonth.getSelectedIndex() + 1)
										+ "-31");
					else if (rdbtnPartNo.isSelected())
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

		tbReturnSlip = new JTable() 
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

		tbReturnSlip.getTableHeader().setFont(fntHeaderTableText);
		tbReturnSlip.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbReturnSlip.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbReturnSlip.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbReturnSlip.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1) {
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn
					.getTableCellRendererComponent(tbReturnSlip,
							tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbReturnSlip.setFont(fntPlainText);

		spTable = new JScrollPane(tbReturnSlip);
		spTable.setBounds(30, 245, 935, 285);
		add(spTable);

		tbReturnSlip.getParent().setBackground(tbReturnSlip.getBackground());
		tbReturnSlip.getTableHeader().setResizingAllowed(false);
		tbReturnSlip.getTableHeader().setReorderingAllowed(false);
		tbReturnSlip.setColumnSelectionAllowed(true);
		tbReturnSlip.setRowSelectionAllowed(true);
		tbReturnSlip.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReturnSlip.setRowHeight(30);

		rdbtnSupplierName = new JRadioButton("Supplier Name");
		rdbtnSupplierName.setFont(fntPlainText);
		rdbtnSupplierName.setSelected(true);
		rdbtnSupplierName.setBounds(160, 80, 170, 30);
		add(rdbtnSupplierName);
		rdbtnSupplierName.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				tfSearch.setText(null);
			}
		});

		rdbtnReturnSlipNo = new JRadioButton("Return Slip Number");
		rdbtnReturnSlipNo.setFont(fntPlainText);
		rdbtnReturnSlipNo.setBounds(350, 80, 220, 30);
		add(rdbtnReturnSlipNo);
		rdbtnReturnSlipNo.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				tfSearch.setText(null);
			}
		});

		rdbtnPartNo = new JRadioButton("Part Number");
		rdbtnPartNo.setFont(fntPlainText);
		rdbtnPartNo.setBounds(590, 80, 171, 30);
		add(rdbtnPartNo);
		rdbtnPartNo.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				tfSearch.setText(null);
			}
		});

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnSupplierName);
		searchBy.add(rdbtnReturnSlipNo);
		searchBy.add(rdbtnPartNo);

		btnViewAllSlips = new JButton("View All Slips");
		btnViewAllSlips.setFont(fntPlainText);
		btnViewAllSlips.setBounds(725, 195, 240, 40);
		add(btnViewAllSlips);
		btnViewAllSlips.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				ViewAll();
			}
		});

		btnViewReturnSlip = new JButton("View Return Slip");
		btnViewReturnSlip.setFont(fntPlainText);
		btnViewReturnSlip.setBounds(30, 545, 230, 40);
		add(btnViewReturnSlip);
		btnViewReturnSlip.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
                                int row;
				row = tbReturnSlip.getSelectedRow();

				if (row == -1)
					JOptionPane.showMessageDialog(null,"Please select an item.");
				else 
                                {
                                        returnslipID = tbReturnSlip.getValueAt(row, 1).toString();
                                        
                                        mainController.setSlipTarget(mainController.getRS(returnslipID));
					guiController.changePanelToViewReturnSlip();
				}
                                
				
			}
		});

		btnAddReturnSlip = new JButton("Add Return Slip");
		btnAddReturnSlip.setFont(fntPlainText);
		btnAddReturnSlip.setBounds(325, 545, 190, 40);
		add(btnAddReturnSlip);
		btnAddReturnSlip.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				guiController.changePanelToAddReturnSlip();
			}
		});

		btnAddCreditMemo = new JButton("Add Credit Memo");
		btnAddCreditMemo.setFont(fntPlainText);
		btnAddCreditMemo.setBounds(575, 545, 215, 40);
		add(btnAddCreditMemo);
		btnAddCreditMemo.addActionListener(new ActionListener() 
                {
                    private String returnslipItem;
			public void actionPerformed(ActionEvent e) 
                        {
                               int row;
				row = tbReturnSlip.getSelectedRow();

				if (row == -1)
					JOptionPane.showMessageDialog(null,"Please select an item.");
				else 
                                {
                                        returnslipID = tbReturnSlip.getValueAt(row, 1).toString();
                                        returnslipItem = tbReturnSlip.getValueAt(row, 3).toString();
                                        mainController.setSlipTarget(mainController.getRS(returnslipID));
                                        mainController.setReturnSlipItem(returnslipItem);
					guiController.changePanelToAddCreditMemo();
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
		lblNumOfSlipsFound.setText(Integer.toString(itemcount));
	}

	public void setComboBox() {
		cmbToYear.removeAllItems();
		cmbFromYear.removeAllItems();
		int cnt = 0;
                if(mainController.getMaxYear()!=null&&mainController.getMinYear()!=null)
                {
                    for (int i = Integer.parseInt(mainController.getMinYear()); i <= Integer
                                    .parseInt(mainController.getMaxYear()); i++) {
                            cmbToYear.addItem(i);
                            cmbFromYear.addItem(i);
                            cnt++;
                    }
                    cmbToYear.setSelectedIndex(cnt - 1);
                    cmbFromYear.setSelectedIndex(0);
                    cmbFromMonth.setSelectedIndex(0);
                    cmbToMonth.setSelectedIndex(11);
                }
                else
                {
                    cmbToYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
                    cmbFromYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
                }
	}

	public void setTableModel(TableModel tbm) { // Setting the Headers
		tbReturnSlip.setModel(tbm);
		JTableHeader th = tbReturnSlip.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 6; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		th.repaint();
	}

	public void setMainController(ReturnSlipController temp) {
		mainController = temp;
	}

	public void ViewAll() 
        {
		TableModel AllModel = mainController.getAllModel();
		tbReturnSlip.setModel(AllModel);

		JTableHeader th = tbReturnSlip.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++) 
                {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
                tbCellRenderer = tbReturnSlip.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbReturnSlip.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbReturnSlip, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                
		tbReturnSlip.repaint();
		setComboBox();
	}

	public static void main(String args[]) 
	{
		GUIController temp = new GUIController();
		temp.changePanelToReturnSlip();
	}

}

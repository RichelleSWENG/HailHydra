package Reports;

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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class TermsReportGUI extends JPanel
{

	private JLabel lblHeader, lblSearchBy, lblSearch, lblReportsFound,
			lblNumofReportsFound, lblDisplay;
	private JTextField tfSearch;
	private String strHeader[] = {
			"        Customer Name        ",
			"<html><center>Sales Invoice /<br>Acknowledgement<br>Receipt Number</center></htm>",
                        "<html><center>&nbsp&nbsp&nbsp&nbsp&nbsp Type&nbsp&nbsp&nbsp&nbsp&nbsp</html></center>",
			"<html><center>Terms<br>(Days)</html></center>",
			"<html><center>Due<br>&nbsp&nbsp&nbsp&nbsp&nbsp Date&nbsp&nbsp&nbsp&nbsp&nbsp</center><html>",
			"<html><center>Current<br>Balance</center></html>" },blank[]={""};
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbTermsReport;
	private JScrollPane spTermsReportTable;
	private JCheckBox chckbxNearTerms, chckbxExceededTerms;
	private JRadioButton rdbtnCustomer, rdbtnReceiptNumber;
	private ButtonGroup searchBy;
	private JButton btnViewAckReceipt, btnViewAllReports, btnViewSalesInvoice,
			btnClose;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController controller;
	private ReportController mainController;

	public TermsReportGUI(GUIController temp)
	{

		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Terms Report");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblDisplay = new JLabel("Display: ");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 80, 89, 30);
		add(lblDisplay);

		lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 120, 119, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search: ");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 160, 89, 30);
		add(lblSearch);

		lblReportsFound = new JLabel("Report/s Found: ");
		lblReportsFound.setFont(fntPlainText);
		lblReportsFound.setBounds(30, 200, 167, 30);
		add(lblReportsFound);

		lblNumofReportsFound = new JLabel("0");
		lblNumofReportsFound.setFont(fntPlainText);
		lblNumofReportsFound.setBounds(190, 200, 250, 30);
		add(lblNumofReportsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(150, 160, 630, 30);
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
					if (chckbxNearTerms.isSelected()
							&& chckbxExceededTerms.isSelected())
					{
						if (rdbtnCustomer.isSelected())
							mainController.SearchAll("customer",
									tfSearch.getText());
						else if (rdbtnReceiptNumber.isSelected())
							mainController.SearchAll("receipt number",
									tfSearch.getText());
					} else if (chckbxNearTerms.isSelected()
							&& !chckbxExceededTerms.isSelected())
					{
						if (rdbtnCustomer.isSelected())
							mainController.SearchNear("customer",
									tfSearch.getText());
						else if (rdbtnReceiptNumber.isSelected())
							mainController.SearchNear("receipt number",
									tfSearch.getText());
					} else if (!chckbxNearTerms.isSelected()
							&& chckbxExceededTerms.isSelected())
					{
						if (rdbtnCustomer.isSelected())
							mainController.SearchExceeded("customer",
									tfSearch.getText());
						else if (rdbtnReceiptNumber.isSelected())
							mainController.SearchExceeded("receipt number",
									tfSearch.getText());
					} else if (!chckbxNearTerms.isSelected()
							&& !chckbxExceededTerms.isSelected())
					{
						tbTermsReport.setModel(tbModel);
						lblNumofReportsFound.setText("0");
					}
				} else if (tfSearch.getText().length() == 0)
				{
					if (chckbxNearTerms.isSelected()
							&& chckbxExceededTerms.isSelected())
					{
						ViewAll();
					} else if (chckbxNearTerms.isSelected()
							&& !chckbxExceededTerms.isSelected())
					{
						mainController.ViewNearTerms();
					} else if (!chckbxNearTerms.isSelected()
							&& chckbxExceededTerms.isSelected())
					{
						mainController.ViewExceededTerms();
					} else if (!chckbxNearTerms.isSelected()
							&& !chckbxExceededTerms.isSelected())
					{
						tbTermsReport.setModel(tbModel);
						lblNumofReportsFound.setText("0");
					}
				}
			}
		});

		tbTermsReport = new JTable()
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

		tbTermsReport.getTableHeader().setFont(fntHeaderTableText);
		tbTermsReport.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbTermsReport.getTableHeader().setResizingAllowed(false);
                
                tbModel = new DefaultTableModel(blank,blank.length);
                tbModel.setColumnCount(1);
                tbModel.setRowCount(0);
                tbModel.addRow(new Object[]{"                                                             No Results Found            "});
                
                

		tbCellRenderer = tbTermsReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbTermsReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbTermsReport, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbTermsReport.setFont(fntPlainText);

		spTermsReportTable = new JScrollPane(tbTermsReport);
		spTermsReportTable.setBounds(30, 253, 935, 280);
		add(spTermsReportTable);

		tbTermsReport.getParent().setBackground(tbTermsReport.getBackground());
		tbTermsReport.getTableHeader().setResizingAllowed(false);
		tbTermsReport.getTableHeader().setReorderingAllowed(false);
		tbTermsReport.setColumnSelectionAllowed(true);
		tbTermsReport.setRowSelectionAllowed(true);
		tbTermsReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbTermsReport.setRowHeight(30);
                
                tbTermsReport.setModel(tbModel);
                

		chckbxNearTerms = new JCheckBox("Near Terms");
		chckbxNearTerms.setFont(fntPlainText);
		chckbxNearTerms.setSelected(true);
		chckbxNearTerms.setBounds(150, 80, 150, 30);
		add(chckbxNearTerms);
		chckbxNearTerms.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText("");
				if (chckbxNearTerms.isSelected()
						&& chckbxExceededTerms.isSelected())
				{
					ViewAll();
				} else if (chckbxNearTerms.isSelected()
						&& !chckbxExceededTerms.isSelected())
				{
					mainController.ViewNearTerms();
				} else if (!chckbxNearTerms.isSelected()
						&& chckbxExceededTerms.isSelected())
				{
					mainController.ViewExceededTerms();
				} else if (!chckbxNearTerms.isSelected()
						&& !chckbxExceededTerms.isSelected())
				{
					lblNumofReportsFound.setText("0");
                                        tbTermsReport.setModel(tbModel);
                                        
				}
			}
		});

		chckbxExceededTerms = new JCheckBox("Exceeded Terms");
		chckbxExceededTerms.setFont(fntPlainText);
		chckbxExceededTerms.setSelected(true);
		chckbxExceededTerms.setBounds(300, 80, 200, 30);
		add(chckbxExceededTerms);
		chckbxExceededTerms.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText("");
				if (chckbxNearTerms.isSelected()
						&& chckbxExceededTerms.isSelected())
				{
					ViewAll();
				} else if (chckbxNearTerms.isSelected()
						&& !chckbxExceededTerms.isSelected())
				{
					mainController.ViewNearTerms();
				} else if (!chckbxNearTerms.isSelected()
						&& chckbxExceededTerms.isSelected())
				{
					mainController.ViewExceededTerms();
				} else if (!chckbxNearTerms.isSelected()
						&& !chckbxExceededTerms.isSelected())
				{
					tbTermsReport.setModel(tbModel);
					lblNumofReportsFound.setText("0");
				}
			}
		});

		rdbtnCustomer = new JRadioButton("Customer");
		rdbtnCustomer.setFont(fntPlainText);
		rdbtnCustomer.setBounds(150, 120, 130, 30);
		rdbtnCustomer.setSelected(true);
		add(rdbtnCustomer);
		rdbtnCustomer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText("");
			}
		});

		rdbtnReceiptNumber = new JRadioButton(
				"Sales Invoice/ Acknowledgement Receipt Number");
		rdbtnReceiptNumber.setFont(fntPlainText);
		rdbtnReceiptNumber.setBounds(280, 120, 515, 30);
		add(rdbtnReceiptNumber);
		rdbtnReceiptNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tfSearch.setText("");
			}
		});

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnCustomer);
		searchBy.add(rdbtnReceiptNumber);

		btnViewAllReports = new JButton("View All Reports");
		btnViewAllReports.setFont(fntPlainText);
		btnViewAllReports.setBounds(725, 200, 240, 40);
		add(btnViewAllReports);
		btnViewAllReports.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewAll();
				chckbxExceededTerms.setSelected(true);
				chckbxNearTerms.setSelected(true);
			}
		});

		btnViewAckReceipt = new JButton("View Acknowledgement Receipt");
		btnViewAckReceipt.setFont(fntPlainText);
		btnViewAckReceipt.setBounds(380, 545, 353, 40);
		add(btnViewAckReceipt);
		btnViewAckReceipt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int row;
				row = tbTermsReport.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(null,
							"Please select an item.");
                                else if (tbTermsReport.getValueAt(row, 2).toString().equals("Acknowledgement Receipt"))
				{
					controller.getARController().setReceiptTarget(controller.getARController()
							.getAR(tbTermsReport.getValueAt(row, 1).toString()));
					controller.setCallingFunction("Terms Report");
                                        controller.changePanelToViewAcknowledgementReceipt();
				}
                                else
                                    JOptionPane.showMessageDialog(null,
							"Please select an Acknowledgement Receipt.");
			}
		});

		btnViewSalesInvoice = new JButton("View Sales Invoice");
		btnViewSalesInvoice.setFont(fntPlainText);
		btnViewSalesInvoice.setBounds(30, 545, 225, 40);
		add(btnViewSalesInvoice);
		btnViewSalesInvoice.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int row;
				row = tbTermsReport.getSelectedRow();
				if (row == -1)
				{
					JOptionPane.showMessageDialog(null,
							"Please select an item.");
				} else if (tbTermsReport.getValueAt(row, 2).toString().equals("Sales Invoice"))
				{
					controller.getSIController().setTarget(controller.getSIController()
							.getSI(tbTermsReport.getValueAt(row, 1).toString()));
					controller.setCallingFunction("Terms Report");
                                        controller.changePanelToViewSalesInvoice();
				}
                                else
                                    JOptionPane.showMessageDialog(null,
							"Please select a Sales Invoice.");

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
		lblNumofReportsFound.setText(Integer.toString(itemcount));
	}

	public void setTableModel(TableModel tbm)
	{ 
                btnViewAckReceipt.setEnabled(true);
                btnViewSalesInvoice.setEnabled(true);
		tbTermsReport.setModel(tbm);
                if(tbTermsReport.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbTermsReport.getModel();
                    JTableHeader th = tbTermsReport.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                    btnViewAckReceipt.setEnabled(false);
                    btnViewSalesInvoice.setEnabled(false);
                }
                else
                {
		JTableHeader th = tbTermsReport.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbTermsReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbTermsReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbTermsReport, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                }
		tbTermsReport.repaint();
	}

	public void setMainController(ReportController temp)
	{
		mainController = temp;
	}

	public void ViewAll()
	{
                btnViewAckReceipt.setEnabled(true);
                btnViewSalesInvoice.setEnabled(true);
		tfSearch.setText("");
		TableModel AllModel = mainController.getAllTermsModel();
		tbTermsReport.setModel(AllModel);

		JTableHeader th = tbTermsReport.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbTermsReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbTermsReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbTermsReport, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		tbTermsReport.repaint();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToTermsReport();
	}
}

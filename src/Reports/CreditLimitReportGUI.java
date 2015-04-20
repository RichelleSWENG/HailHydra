package Reports;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import HailHydra.GUIController;
import TableRenderer.TableRenderer;

public class CreditLimitReportGUI extends JPanel
{

	private JLabel lblHeader, lblCustomer, lblReportsFound,
			lblNumOfReportsFound;
	private JTextField tfCustomer;
	private String strHeader[] = { "Customer Name", "Credit Limit",
			"Current Balance" };
	private DefaultTableModel tbModel;
	private Component component;
	private JTable tbCreditLimit;
	private JScrollPane spTable;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private JButton btnClose, btnAddPayment, btnViewAllReports;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController controller;
	private ReportController mainController;

	public CreditLimitReportGUI(GUIController temp)
	{

		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Credit Limit Report");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 80, 112, 30);
		add(lblCustomer);

		lblReportsFound = new JLabel("Report/s Found: ");
		lblReportsFound.setFont(fntPlainText);
		lblReportsFound.setBounds(30, 120, 165, 30);
		add(lblReportsFound);

		lblNumOfReportsFound = new JLabel("0");
		lblNumOfReportsFound.setFont(fntPlainText);
		lblNumOfReportsFound.setBounds(190, 120, 250, 30);
		add(lblNumOfReportsFound);

		tfCustomer = new JTextField();
		tfCustomer.setFont(fntPlainText);
		tfCustomer.setBounds(140, 80, 400, 30);
		add(tfCustomer);
		tfCustomer.getDocument().addDocumentListener(new DocumentListener()
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
				if (tfCustomer.getText().length() > 0)
				{
					mainController.SearchSomethingfromTerms(tfCustomer
							.getText());
				} else if (tfCustomer.getText().length() == 0) // if nothing is
				{
					ViewAll();
				}
			}
		});

		tbCreditLimit = new JTable()
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

		tbCreditLimit.getTableHeader().setFont(fntHeaderTableText);
		tbCreditLimit.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbCreditLimit.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbCreditLimit.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCreditLimit.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbCreditLimit, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbCreditLimit.setFont(fntPlainText);

		spTable = new JScrollPane(tbCreditLimit);
		spTable.setBounds(30, 165, 935, 360);
		add(spTable);

		tbCreditLimit.getParent().setBackground(tbCreditLimit.getBackground());
		tbCreditLimit.getTableHeader().setResizingAllowed(false);
		tbCreditLimit.getTableHeader().setReorderingAllowed(false);
		tbCreditLimit.setColumnSelectionAllowed(true);
		tbCreditLimit.setRowSelectionAllowed(true);
		tbCreditLimit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCreditLimit.setRowHeight(30);

		btnViewAllReports = new JButton("View All Reports");
		btnViewAllReports.setFont(fntPlainText);
		btnViewAllReports.setBounds(725, 110, 240, 40);
		add(btnViewAllReports);
		btnViewAllReports.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewAll();
			}
		});

		btnAddPayment = new JButton("Add Payment");
		btnAddPayment.setFont(fntPlainText);
		btnAddPayment.setBounds(600, 545, 165, 40);
		add(btnAddPayment);
		btnAddPayment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToAddPaymentCollectibles();
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
		lblNumOfReportsFound.setText(Integer.toString(itemcount));
	}

	public void setTableModel(TableModel tbm)
	{ // Setting the Headers
		tbCreditLimit.setModel(tbm);
                if(tbCreditLimit.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbCreditLimit.getModel();
                    JTableHeader th = tbCreditLimit.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                }
                else
                {
		JTableHeader th = tbCreditLimit.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbCreditLimit.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCreditLimit.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbCreditLimit, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                }
		tbCreditLimit.repaint();
	}

	public void setMainController(ReportController temp)
	{
		mainController = temp;
	}

	public void ViewAll()
	{
		tfCustomer.setText("");
		TableModel AllModel = mainController.getAllCreditModel();
		tbCreditLimit.setModel(AllModel);
                
                if(tbCreditLimit.getRowCount() == 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tbCreditLimit.getModel();
                    JTableHeader th = tbCreditLimit.getTableHeader();
                    model.setColumnCount(1);    // set columnCount to 1
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(0); 
                    tc.setHeaderValue("");
                    
                    model.addRow(new Object[]{"                                                             No Results Found            "});
                }
                else{
                    JTableHeader th = tbCreditLimit.getTableHeader();
                    TableColumnModel tcm = th.getColumnModel();
                    for (int i = 0; i < strHeader.length; i++)
                    {
                            TableColumn tc = tcm.getColumn(i);
                            tc.setHeaderValue(strHeader[i]);
                    }
                    tbCellRenderer = tbCreditLimit.getTableHeader().getDefaultRenderer();
                    tbColumnRenderer = tbCreditLimit.getColumnModel();
                    for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
                    {
                            tbColumn = tbColumnRenderer.getColumn(j);
                            tbCellRendererColumn = tbColumn.getHeaderRenderer();
                            if (tbCellRendererColumn == null)
                                    tbCellRendererColumn = tbCellRenderer;
                            component = tbCellRendererColumn.getTableCellRendererComponent(
                                            tbCreditLimit, tbColumn.getHeaderValue(), false, false, 0,
                                            j);
                            tbColumn.setPreferredWidth(component.getPreferredSize().width);
                    }
                }
		tbCreditLimit.repaint();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToCreditLimitReport();
	}
}

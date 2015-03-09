package Sales;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;

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
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesInvoiceListGUI extends JPanel 
{
	private JLabel  lblHeader, lblSearchBy, lblSearch, lblRange, lblInvoicesFound,
			lblNumofTransactions, lblTo;
        private String strHeader[] = { "Customer Name", "Date",
		"<html><center>Sales Invoice<br>Number</center></html>", 
                "<html><center>Original<br>Amount</center></html>", 
                "<html><center>Current<br>Balance</center></html>" },
                strMonths[] = { "January", "February", "March", "April", "May",
		"June", "July", "August", "September", "October", "November",
		"December" };
        private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbSalesInvoice;
	private JScrollPane spSalesInvoice;
	private JRadioButton rdbtnCustomerName, rdbtnTransactionNo;
	private ButtonGroup searchBy;
	private JTextField tfSearch;
	private JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
	private JButton btnViewAllInvoices, btnAddSalesInvoice,
			btnViewSalesInvoice, btnClose;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIController guiController;
	
	public SalesInvoiceListGUI(GUIController temp) {
            
                guiController=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
		
                lblHeader = new JLabel("Sales Invoice");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 120, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search: ");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 120, 84, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 160, 84, 30);
		add(lblRange);
                
                lblTo = new JLabel("To");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(390, 160, 36, 30);
		add(lblTo);
                
                lblInvoicesFound = new JLabel("Invoice/s Found:");
		lblInvoicesFound.setFont(fntPlainText);
		lblInvoicesFound.setBounds(30, 200, 182, 30);
		add(lblInvoicesFound);

		lblNumofTransactions = new JLabel("0");
		lblNumofTransactions.setFont(fntPlainText);
		lblNumofTransactions.setBounds(200, 200, 46, 30);
		add(lblNumofTransactions);
                
                tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(117, 120, 579, 30);
		add(tfSearch);

                
		tbModel = new DefaultTableModel() 
                {
			public boolean isCellEditable(int rowIndex, int mColIndex) 
                        {
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++) {
			tbModel.addColumn(strHeader[i]);
		}
		

		tbSalesInvoice = new JTable(tbModel) {
			public TableCellRenderer getCellRenderer(int row, int column) {
				return new TableRenderer();
			}
                        
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
                         {
                            component = super.prepareRenderer(renderer, row, column);
                            modelRow = convertRowIndexToModel(row);
                            if (!isRowSelected(modelRow))
                            {
                                component.setBackground(Color.WHITE);
                            }else
                            {
                                component.setBackground(Color.yellow);
                            }
                            return component;
                            }
		};
		
		tbSalesInvoice.getTableHeader().setFont(fntHeaderTableText);
		tbSalesInvoice.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbSalesInvoice.getTableHeader().setResizingAllowed(false);
		tbCellRenderer  = tbSalesInvoice.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbSalesInvoice.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbSalesInvoice, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbSalesInvoice.setFont(fntPlainText);
		
		spSalesInvoice = new JScrollPane(tbSalesInvoice);
		spSalesInvoice.setBounds(30, 245, 934, 285);
		add(spSalesInvoice);

		tbSalesInvoice.getParent().setBackground(tbSalesInvoice.getBackground());
		tbSalesInvoice.getTableHeader().setResizingAllowed(false);
		tbSalesInvoice.getTableHeader().setReorderingAllowed(false);
		tbSalesInvoice.setColumnSelectionAllowed(true);
		tbSalesInvoice.setRowSelectionAllowed(true);
		tbSalesInvoice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbSalesInvoice.setRowHeight(30);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(113, 160, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(278, 160, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(431, 160, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(596, 160, 100, 30);
		add(cmbToYear);

		for (int i = 0; i < strMonths.length; i++) 
                {
			cmbFromMonth.addItem(strMonths[i]);
			cmbToMonth.addItem(strMonths[i]);
		}

		Calendar date = Calendar.getInstance();
		int yr = date.get(Calendar.YEAR);
		for (int j = 0; j < 5; j++) {
			cmbFromYear.addItem(Integer.toString(yr - 2));
			cmbToYear.addItem(Integer.toString(yr - 2));
			yr++;
		}
		String yearBefore = String.valueOf(date.get(Calendar.YEAR) - 1);
		String yearToday = String.valueOf(date.get(Calendar.YEAR));
		cmbFromMonth.setSelectedIndex(0);
		cmbFromYear.setSelectedItem(yearBefore);
		cmbToMonth.setSelectedIndex(date.get(Calendar.MONTH));
		cmbToYear.setSelectedItem(yearToday);

		rdbtnCustomerName = new JRadioButton("Customer Name");
		rdbtnCustomerName.setFont(fntPlainText);
                rdbtnCustomerName.setBackground(SystemColor.textHighlight);
		rdbtnCustomerName.setSelected(true);
		rdbtnCustomerName.setBounds(149, 80, 195, 30);
		add(rdbtnCustomerName);

		rdbtnTransactionNo = new JRadioButton("Transaction Number");
                rdbtnTransactionNo.setBackground(SystemColor.textHighlight);
		rdbtnTransactionNo.setFont(fntPlainText);
		rdbtnTransactionNo.setBounds(346, 80, 257, 30);
		add(rdbtnTransactionNo);

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnCustomerName);
		searchBy.add(rdbtnTransactionNo);

		btnViewAllInvoices = new JButton("View All Invoices");
		btnViewAllInvoices.setFont(fntPlainText);
		btnViewAllInvoices.setBounds(725, 190, 240, 40);
		add(btnViewAllInvoices);

		btnViewSalesInvoice = new JButton("View Sales Invoice");
		btnViewSalesInvoice.setFont(fntPlainText);
		btnViewSalesInvoice.setBounds(30, 545, 244, 40);
		add(btnViewSalesInvoice);
                btnViewSalesInvoice.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToViewSalesInvoice();
                        }
                    });
                
                btnAddSalesInvoice = new JButton("Add Sales Invoice");
		btnAddSalesInvoice.setFont(fntPlainText);
		btnAddSalesInvoice.setBounds(400, 545, 244, 40);
		add(btnAddSalesInvoice);
                btnAddSalesInvoice.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToAddSalesInvoice();
                        }
                    });

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
		btnClose.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToMainMenu();
                        }
                    });
		
	}
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToSalesInvoice();
        }

}

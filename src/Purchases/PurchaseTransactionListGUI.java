package Purchases;

import HailHydra.GUIController;
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
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PurchaseTransactionListGUI extends JPanel
{

        private JLabel  lblHeader, lblSearchBy, lblSearch, lblRange, lblTo,
			lblTransactionsFound, lblNumofTransactions;
        private JTextField tfSearch;
        JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
        private String strHeader[] ={ "        Supplier Name        ", "Date", 
                "<html><center>Purchase<br>Transaction<br>Number</center></html>",
                "<html><center>Original<br>Amount</center></html>", 
                "<html><center>Current<br>Balance</center></html>" },
                months[] = { "January", "February", "March", "April", 
                "May", "June", "July", "August", "September", "October", 
                "November", "December" };
        private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbPurchaseTransaction;
        private JScrollPane spTable;
	private JRadioButton rdbtnSupplierName, rdbtnPurchaseTransactionNum, rdbtnPartNumber;
	private ButtonGroup searchBy;
	private JButton btnViewAllTransactions, btnAddPurchaseTransaction, 
                btnViewPurchaseTransaction, btnClose;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIController controller;


	
	public PurchaseTransactionListGUI(GUIController temp)
	{
		controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
		
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
		lblSearch.setBounds(30, 120, 84, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 160, 80, 30);
		add(lblRange);
                
                lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(382, 160, 36, 30);
		add(lblTo);
                
                lblTransactionsFound = new JLabel("Transaction/s Found:");
		lblTransactionsFound.setFont(fntPlainText);
		lblTransactionsFound.setBounds(30, 200, 219, 30);
		add(lblTransactionsFound);

		lblNumofTransactions = new JLabel("0");
		lblNumofTransactions.setFont(fntPlainText);
		lblNumofTransactions.setBounds(234, 200, 46, 30);
		add(lblNumofTransactions);
                
                tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(117, 120, 571, 30);
		add(tfSearch);
                
                cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(107, 160, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(272, 160, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(423, 160, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(588, 160, 100, 30);
		add(cmbToYear);
		for (int i = 0; i < 12; i++)
		{
			cmbFromMonth.addItem(months[i]);
			cmbToMonth.addItem(months[i]);
		}

		Calendar date = Calendar.getInstance();
		int yr = date.get(Calendar.YEAR);
		for (int j = 0; j < 5; j++)
		{
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
                
		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}

		tbPurchaseTransaction = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
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
		
		tbPurchaseTransaction.getTableHeader().setFont(fntHeaderTableText);
		tbPurchaseTransaction.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbPurchaseTransaction.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbPurchaseTransaction.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbPurchaseTransaction.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbPurchaseTransaction, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbPurchaseTransaction.setFont(fntPlainText);
		
		spTable = new JScrollPane(tbPurchaseTransaction);
		spTable.setBounds(30, 245, 935, 285);
		add(spTable);

		tbPurchaseTransaction.getParent().setBackground(tbPurchaseTransaction.getBackground());
		tbPurchaseTransaction.getTableHeader().setResizingAllowed(false);
		tbPurchaseTransaction.getTableHeader().setReorderingAllowed(false);
		tbPurchaseTransaction.setColumnSelectionAllowed(true);
		tbPurchaseTransaction.setRowSelectionAllowed(true);
		tbPurchaseTransaction.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPurchaseTransaction.setRowHeight(30);

		rdbtnSupplierName = new JRadioButton("Supplier Name");
		rdbtnSupplierName.setFont(fntPlainText);
                rdbtnSupplierName.setBackground(SystemColor.textHighlight);
		rdbtnSupplierName.setSelected(true);
		rdbtnSupplierName.setBounds(150, 80, 184, 30);
		add(rdbtnSupplierName);

		rdbtnPurchaseTransactionNum = new JRadioButton("Purchase Transaction Number");
		rdbtnPurchaseTransactionNum.setFont(fntPlainText);
                rdbtnPurchaseTransactionNum.setBackground(SystemColor.textHighlight);
		rdbtnPurchaseTransactionNum.setBounds(336, 80, 340, 30);
		add(rdbtnPurchaseTransactionNum);
                
                rdbtnPartNumber = new JRadioButton("Part Number");
		rdbtnPartNumber.setFont(fntPlainText);
                rdbtnPartNumber.setBackground(SystemColor.textHighlight);
		rdbtnPartNumber.setBounds(675, 80, 352, 30);
		add(rdbtnPartNumber);

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnSupplierName);
		searchBy.add(rdbtnPurchaseTransactionNum);
                searchBy.add(rdbtnPartNumber);

		btnViewAllTransactions = new JButton("View All Transactions");
		btnViewAllTransactions.setFont(fntPlainText);
		btnViewAllTransactions.setBounds(725, 190, 240, 40);
		add(btnViewAllTransactions);
                btnViewAllTransactions.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                
                        }
                    });

		btnViewPurchaseTransaction = new JButton("View Purchase Transaction");
		btnViewPurchaseTransaction.setFont(fntPlainText);
		btnViewPurchaseTransaction.setBounds(30, 545, 300, 40);
		add(btnViewPurchaseTransaction);
                btnViewPurchaseTransaction.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                
                        }
                    });
                
                btnAddPurchaseTransaction = new JButton("Add Purchase Transaction");
		btnAddPurchaseTransaction.setFont(fntPlainText);
		btnAddPurchaseTransaction.setBounds(420, 545, 290, 40);
		add(btnAddPurchaseTransaction);
                btnAddPurchaseTransaction.addActionListener(
                    new ActionListener()
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
                btnClose.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToMainMenu();
                        }
                    });
		
		
	}
        
        public static void main(String args[])
        {
           GUIController temp=new GUIController();
           temp.changePanelToPurchaseTransactionList();
        }

}

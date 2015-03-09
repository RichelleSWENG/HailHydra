package DebitMemo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class DebitMemoListGUI extends JPanel
{

	private JLabel lblHeader, lblSearchBy, lblSearch, lblRange, lblTo,
			lblMemosFound, lblNumofMemos;
        private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbDebitMemo;
	private JScrollPane spTable;
	private String strHeader[] = { "Date", 
                "<html><center>Debit Memo<br>Number</center></html>", 
                "Customer Name", "Part Number", "Quantity",
			"Amount" };
	private String strMonths[] = { "January", "February", 
                "March", "April", "May", "June", "July", "August",
		"September", "October", "November", "December" };
	private JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
	private JRadioButton rdbtnCustomerName, rdbtnDebitMemoNo, rdbtnPartNo;
	private ButtonGroup searchBy;
	private JTextField tfSearch;
	private JButton btnViewAllMemos, btnAddDebitMemo, btnViewDebitMemo,
			btnClose;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIController controller;

	
	public DebitMemoListGUI(GUIController temp)
	{
		controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
                lblHeader = new JLabel("Debit Memo");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(23, 85, 123, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search: ");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(23, 120, 100, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(23, 156, 83, 30);
		add(lblRange);

		lblTo = new JLabel("To");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(396, 156, 36, 30);
		add(lblTo);

		lblMemosFound = new JLabel("Memo/s Found:");
		lblMemosFound.setFont(fntPlainText);
		lblMemosFound.setBounds(23, 187, 162, 30);
		add(lblMemosFound);

		lblNumofMemos = new JLabel("0");
		lblNumofMemos.setFont(fntPlainText);
		lblNumofMemos.setBounds(180, 187, 75, 30);
		add(lblNumofMemos);

                tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(111, 120, 606, 30);
		add(tfSearch);
                
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

		tbDebitMemo = new JTable(tbModel)
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
		
		tbDebitMemo.getTableHeader().setFont(fntHeaderTableText);
		tbDebitMemo.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbDebitMemo.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbDebitMemo.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbDebitMemo.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbDebitMemo, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbDebitMemo.setFont(fntPlainText);
		
		spTable = new JScrollPane(tbDebitMemo);
		spTable.setBounds(23, 228, 937, 281);
		add(spTable);

		tbDebitMemo.getParent().setBackground(tbDebitMemo.getBackground());
		tbDebitMemo.getTableHeader().setResizingAllowed(false);
		tbDebitMemo.getTableHeader().setReorderingAllowed(false);
		tbDebitMemo.setColumnSelectionAllowed(true);
		tbDebitMemo.setRowSelectionAllowed(true);
		tbDebitMemo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDebitMemo.setRowHeight(30);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(104, 156, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(272, 156, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(442, 156, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(613, 156, 100, 30);
		add(cmbToYear);

		for (int i = 0; i < strMonths.length; i++)
		{
			cmbFromMonth.addItem(strMonths[i]);
			cmbToMonth.addItem(strMonths[i]);
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

		rdbtnCustomerName = new JRadioButton("Customer Name");
		rdbtnCustomerName.setFont(fntPlainText);
                rdbtnCustomerName.setBackground(SystemColor.textHighlight);
		rdbtnCustomerName.setSelected(true);
		rdbtnCustomerName.setBounds(139, 88, 195, 25);
		add(rdbtnCustomerName);

		rdbtnDebitMemoNo = new JRadioButton("Debit Memo Number");
		rdbtnDebitMemoNo.setFont(fntPlainText);
                rdbtnDebitMemoNo.setBackground(SystemColor.textHighlight);
		rdbtnDebitMemoNo.setBounds(343, 88, 232, 25);
		add(rdbtnDebitMemoNo);

		rdbtnPartNo = new JRadioButton("Part Number");
		rdbtnPartNo.setFont(fntPlainText);
                rdbtnPartNo.setBackground(SystemColor.textHighlight);
		rdbtnPartNo.setBounds(576, 88, 168, 25);
		add(rdbtnPartNo);

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnCustomerName);
		searchBy.add(rdbtnDebitMemoNo);
		searchBy.add(rdbtnPartNo);

		btnViewAllMemos = new JButton("View All Memos");
		btnViewAllMemos.setFont(fntPlainText);
		btnViewAllMemos.setBounds(764, 177, 196, 40);
		add(btnViewAllMemos);
                
                btnViewDebitMemo = new JButton("View Debit Memos");
		btnViewDebitMemo.setFont(fntPlainText);
		btnViewDebitMemo.setBounds(30, 525, 238, 40);
		add(btnViewDebitMemo);

		btnAddDebitMemo = new JButton("Add Debit Memo");
		btnAddDebitMemo.setFont(fntPlainText);
		btnAddDebitMemo.setBounds(435, 525, 232, 40);
		add(btnAddDebitMemo);
                btnAddDebitMemo.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToAddDebitMemo();
                        }
                    });


		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(850, 525, 110, 40);
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


        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToDebitMemo();
        }
}
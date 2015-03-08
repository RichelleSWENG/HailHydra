package CreditMemo;

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

import HailHydra.GUIModel;
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditMemoListGUI extends JPanel
{
        private JLabel  lblHeader, lblSearchBy, lblSearch, lblRange, lblTo,
			lblMemosFound, lblNumOfMemosFound;
        private JTextField tfSearch;
	private String strHeader[] = { "Date", 
                "<html><center>Credit Memo<br>Number</center><html>", 
                "Supplier Name", "<html><center>Return Slip<br>Number</center><html>", 
                "Amount"}, strMonths[] = { "January", "February", "March",
                "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        private JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
        private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbCreditMemo;
	private JScrollPane spTable;
	private JRadioButton rdbtnSupplierName, rdbtnCreditMemoNo, rdbtnReturnSlipNo;
	private ButtonGroup searchBy;
	private JButton btnViewAllMemos, btnViewCreditMemo,
			btnClose;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIModel controller;
	
	public CreditMemoListGUI(GUIModel temp)
	{
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
		
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
		lblSearch.setBounds(30, 120, 100, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 160, 83, 30);
		add(lblRange);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(396, 160, 36, 30);
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
		tfSearch.setBounds(111, 120, 606, 30);
		add(tfSearch);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(104, 160, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(272, 160, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(442, 160, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(613, 160, 100, 30);
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
		

		tbCreditMemo = new JTable(tbModel)
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
			component = tbCellRendererColumn.getTableCellRendererComponent(tbCreditMemo, tbColumn.getHeaderValue(), false, false, 0,j);
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
                rdbtnSupplierName.setBackground(SystemColor.textHighlight);
		rdbtnSupplierName.setSelected(true);
		rdbtnSupplierName.setBounds(139, 80, 177, 25);
		add(rdbtnSupplierName);

		rdbtnCreditMemoNo = new JRadioButton("Credit Memo Number");
		rdbtnCreditMemoNo.setFont(fntPlainText);
                rdbtnCreditMemoNo.setBackground(SystemColor.textHighlight);
		rdbtnCreditMemoNo.setBounds(318, 80, 232, 25);
		add(rdbtnCreditMemoNo);

		rdbtnReturnSlipNo = new JRadioButton("Return Slip Number");
		rdbtnReturnSlipNo.setFont(fntPlainText);
                rdbtnReturnSlipNo.setBackground(SystemColor.textHighlight);
		rdbtnReturnSlipNo.setBounds(552, 80, 226, 25);
		add(rdbtnReturnSlipNo);

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnSupplierName);
		searchBy.add(rdbtnCreditMemoNo);
		searchBy.add(rdbtnReturnSlipNo);

		btnViewAllMemos = new JButton("View All Memos");
		btnViewAllMemos.setFont(fntPlainText);
		btnViewAllMemos.setBounds(725, 190, 240, 40);
		add(btnViewAllMemos);

		btnViewCreditMemo = new JButton("View Credit Memo");
		btnViewCreditMemo.setFont(fntPlainText);
		btnViewCreditMemo.setBounds(23, 545, 238, 40);
		add(btnViewCreditMemo);

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(850, 545, 110, 40);
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
           GUIModel temp=new GUIModel();
           temp.changePanelToCreditMemo();
        }

}

package Payables;

import HailHydra.GUIModel;
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
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayablesListGUI extends JPanel
{
	private JLabel  lblHeader, lblDisplay, lblSupplier, lblRrange, 
                        lblPayablesFound, lblNumOfPayablesFound, lblTo;
        private JTextField tfSupplier;
        private String strHeader[] = { "Supplier Name", "Date", 
                "<html><center>Purchase<br>Transaction<br>Number</center></html>",
		"<html><center>Original<br>Amount</center></html>", 
                "<html><center>Current<br>Balance</center></html>", 
                "Status" }, strMonths[] = { "January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", 
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
        private GUIModel controller;
        
        
	public PayablesListGUI(GUIModel temp)
	{
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);

                lblHeader = new JLabel("Payables");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
		lblDisplay = new JLabel("Display: ");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 74, 87, 30);
		add(lblDisplay);

		tfSupplier = new JTextField();
		tfSupplier.setFont(fntPlainText);
		tfSupplier.setBounds(126, 115, 545, 30);
		add(tfSupplier);

		lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(30, 115, 107, 30);
		add(lblSupplier);

		lblRrange = new JLabel("Range:");
		lblRrange.setFont(fntPlainText);
		lblRrange.setBounds(30, 156, 87, 30);
		add(lblRrange);
                
                lblPayablesFound = new JLabel("Payable/s Found: ");
		lblPayablesFound.setFont(fntPlainText);
		lblPayablesFound.setBounds(30, 197, 176, 30);
		add(lblPayablesFound);

		lblNumOfPayablesFound = new JLabel("0");
		lblNumOfPayablesFound.setFont(fntPlainText);
		lblNumOfPayablesFound.setBounds(198, 197, 26, 30);
		add(lblNumOfPayablesFound);

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
		cmbToMonth.setBounds(408, 156, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(577, 156, 100, 30);
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

		tbPayables = new JTable(tbModel)
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
		tbPayables.getTableHeader().setFont(fntHeaderTableText);
		tbPayables.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbPayables.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbPayables.getTableHeader()
				.getDefaultRenderer();
		tbColumnRenderer = tbPayables.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbPayables, tbColumn.getHeaderValue(), false, false, 0,j);
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
		chckbxActivePayables.setFont(fntPlainText);
                chckbxActivePayables.setBackground(SystemColor.textHighlight);
		chckbxActivePayables.setBounds(116, 74, 194, 30);
		add(chckbxActivePayables);

		chckbxClosedPayables = new JCheckBox("Closed Payables");
		chckbxClosedPayables.setFont(fntPlainText);
                chckbxClosedPayables.setBackground(SystemColor.textHighlight);
		chckbxClosedPayables.setBounds(312, 74, 201, 30);
		add(chckbxClosedPayables);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(382, 156, 36, 30);
		add(lblTo);

		btnViewPayment = new JButton("View Payment");
		btnViewPayment.setFont(fntPlainText);
		btnViewPayment.setBounds(431, 543, 216, 40);
		add(btnViewPayment);

		btnAddPayment = new JButton("Add Payment");
		btnAddPayment.setFont(fntPlainText);
		btnAddPayment.setBounds(23, 543, 216, 40);
		add(btnAddPayment);
                btnAddPayment.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToAddPaymentPayables();
                        }
                    });

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(836, 543, 127, 40);
		add(btnClose);
                btnClose.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToMainMenu();
                        }
                    });

		btnViewAllPayables = new JButton("View All Payables");
		btnViewAllPayables.setFont(fntPlainText);
		btnViewAllPayables.setBounds(724, 187, 239, 40);
		add(btnViewAllPayables);

	}
        
        
        public static void main(String args[]){
           GUIModel temp=new GUIModel();
           temp.changePanelToPayablesList();
        }
}

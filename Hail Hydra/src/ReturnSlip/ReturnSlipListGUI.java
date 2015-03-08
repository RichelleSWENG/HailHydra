package ReturnSlip;

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

public class ReturnSlipListGUI extends JPanel {

	private JLabel  lblHeader, lblSearchBy, lblSearch, lblRange, lblTo,
                lblSlipsFound, lblNumofSlips;
        private String strHeader[] = { "Date", "<html><center>Return Slip<br>Number</center></html>",
                "Supplier Name","Part Number", "Quantity", "Amount" },
                strMonths[] = { "January", "February", "March", "April", "May",
		"June", "July", "August", "September", "October", "November",
		"December" };
        private DefaultTableModel tbModel;
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
	private JButton btnViewAllSlips, btnAddReturnSlip, btnViewReturnSlip, 
                btnAddCreditMemo, btnClose;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIModel controller;

	
	public ReturnSlipListGUI(GUIModel temp) {
		
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
            
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
		lblSearch.setBounds(30, 120, 100, 30);
		add(lblSearch);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 160, 84, 30);
		add(lblRange);

		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(384, 160, 36, 30);
		add(lblTo);
		
		lblSlipsFound = new JLabel("Slip/s Found:");
		lblSlipsFound.setFont(fntPlainText);
		lblSlipsFound.setBounds(30, 200, 149, 30);
		add(lblSlipsFound);

		lblNumofSlips = new JLabel("0");
		lblNumofSlips.setFont(fntPlainText);
		lblNumofSlips.setBounds(160, 200, 46, 30);
		add(lblNumofSlips);
                
                tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(110, 120, 585, 30);
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
		cmbToMonth.setBounds(430, 160, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(595, 160, 100, 30);
                add(cmbToYear);

		for (int i = 0; i < strMonths.length; i++) {
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
		

		tbReturnSlip = new JTable(tbModel) {
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
		
		tbReturnSlip.getTableHeader().setFont(fntHeaderTableText);
		tbReturnSlip.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbReturnSlip.getTableHeader().setResizingAllowed(false);
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
                rdbtnSupplierName.setBackground(SystemColor.textHighlight);
		rdbtnSupplierName.setSelected(true);
		rdbtnSupplierName.setBounds(147, 80, 180, 30);
		add(rdbtnSupplierName);

		rdbtnReturnSlipNo = new JRadioButton("Return Slip Number");
		rdbtnReturnSlipNo.setFont(fntPlainText);
                rdbtnReturnSlipNo.setBackground(SystemColor.textHighlight);
		rdbtnReturnSlipNo.setBounds(325, 80, 220, 30);
		add(rdbtnReturnSlipNo);

		rdbtnPartNo = new JRadioButton("Part Number");
		rdbtnPartNo.setFont(fntPlainText);
                rdbtnPartNo.setBackground(SystemColor.textHighlight);
		rdbtnPartNo.setBounds(548, 80, 171, 30);
		add(rdbtnPartNo);

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnSupplierName);
		searchBy.add(rdbtnReturnSlipNo);
		searchBy.add(rdbtnPartNo);
		
		btnViewAllSlips = new JButton("View All Slips");
		btnViewAllSlips.setFont(fntPlainText);
		btnViewAllSlips.setBounds(725, 190, 240, 40);
		add(btnViewAllSlips);
                
                btnViewReturnSlip = new JButton("View Return Slips");
		btnViewReturnSlip.setFont(fntPlainText);
		btnViewReturnSlip.setBounds(30, 545, 230, 40);
		add(btnViewReturnSlip);

		btnAddReturnSlip = new JButton("Add Return Slip");
		btnAddReturnSlip.setFont(fntPlainText);
		btnAddReturnSlip.setBounds(320, 545, 190, 40);
		add(btnAddReturnSlip);
                btnAddReturnSlip.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToAddReturnSlip();
                        }
                    });

                btnAddCreditMemo = new JButton("Add Credit Memo");
		btnAddCreditMemo.setFont(fntPlainText);
		btnAddCreditMemo.setBounds(580, 545, 210, 40);
		add(btnAddCreditMemo);

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
           GUIModel temp=new GUIModel();
           temp.changePanelToReturnSlip();
        }

}

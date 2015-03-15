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
import ReturnSlip.ReturnSlipController;
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

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
        private DebitMemoController mainController;

	
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

		cmbToYear.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        tfSearch.setText(""); 
                        mainController.searchbyDate(cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31");
                       
                    }

                });
                cmbToMonth.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        tfSearch.setText(""); 
                        mainController.searchbyDate(cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31");
                       
                    }

                });
                cmbFromMonth.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        tfSearch.setText(""); 
                        mainController.searchbyDate(cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31");
                       
                    }

                });
                cmbFromYear.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        tfSearch.setText(null); 
                        mainController.searchbyDate(cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31");
                       
                    }

                });
                
                tfSearch.getDocument().addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void insertUpdate(DocumentEvent de)
                    {
                        try
                        {
                            done();
                        } catch (Exception ex)
                        {
  
                        }
                    }   

                    @Override
                    public void removeUpdate(DocumentEvent de)
                    {
                        try
                        {
                            done();
                        } catch (Exception ex)
                        {
                   
                        }
                    }

                    @Override
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
                            if(rdbtnCustomerName.isSelected())
                                mainController.SearchSomething(tfSearch.getText(),0,cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31"); 
                            else if(rdbtnDebitMemoNo.isSelected())
                                mainController.SearchSomething(tfSearch.getText(),1,cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31");
                            else if(rdbtnPartNo.isSelected())
                                mainController.SearchSomething(tfSearch.getText(),2,cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31");
                        } else if (tfSearch.getText().length() == 0)  //if nothing is typed display all
                        {
                            mainController.searchbyDate(cmbFromYear.getSelectedItem()+"-"+(cmbFromMonth.getSelectedIndex()+1)+"-01",cmbToYear.getSelectedItem()+"-"+(cmbToMonth.getSelectedIndex()+1)+"-31");
                        }
                    }});

		rdbtnCustomerName = new JRadioButton("Customer Name");
		rdbtnCustomerName.setFont(fntPlainText);
                rdbtnCustomerName.setBackground(SystemColor.textHighlight);
		rdbtnCustomerName.setSelected(true);
		rdbtnCustomerName.setBounds(139, 88, 195, 25);
		add(rdbtnCustomerName);
                rdbtnCustomerName.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                tfSearch.setText(null);
                        }
                    });
		rdbtnDebitMemoNo = new JRadioButton("Debit Memo Number");
		rdbtnDebitMemoNo.setFont(fntPlainText);
                rdbtnDebitMemoNo.setBackground(SystemColor.textHighlight);
		rdbtnDebitMemoNo.setBounds(343, 88, 232, 25);
		add(rdbtnDebitMemoNo);
                rdbtnDebitMemoNo.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                tfSearch.setText(null);
                        }
                    });
		rdbtnPartNo = new JRadioButton("Part Number");
		rdbtnPartNo.setFont(fntPlainText);
                rdbtnPartNo.setBackground(SystemColor.textHighlight);
		rdbtnPartNo.setBounds(576, 88, 168, 25);
		add(rdbtnPartNo);
                rdbtnPartNo.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                tfSearch.setText(null);
                        }
                    });
		searchBy = new ButtonGroup();
		searchBy.add(rdbtnCustomerName);
		searchBy.add(rdbtnDebitMemoNo);
		searchBy.add(rdbtnPartNo);
                

		btnViewAllMemos = new JButton("View All Memos");
		btnViewAllMemos.setFont(fntPlainText);
		btnViewAllMemos.setBounds(764, 177, 196, 40);
		add(btnViewAllMemos);
                btnViewAllMemos.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                ViewAll();
                        }
                    });
                
                
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
        public void setItemCount(int itemcount)
        {
            lblNumofMemos.setText(Integer.toString(itemcount));
        }
        public void setComboBox()
        {
            cmbToYear.removeAllItems();
            cmbFromYear.removeAllItems();
            int cnt=0;
            for(int i=Integer.parseInt(mainController.getMinYear());i<=Integer.parseInt(mainController.getMaxYear());i++)
            {
                cmbToYear.addItem(i);
                cmbFromYear.addItem(i);
                cnt++;
            }
            cmbToYear.setSelectedIndex(cnt-1);
            cmbFromYear.setSelectedIndex(0);
            cmbFromMonth.setSelectedIndex(0);
            cmbToMonth.setSelectedIndex(11);
        }
        public void setTableModel(TableModel tbm)
        {                  // Setting the Headers
            tbDebitMemo.setModel(tbm);
            JTableHeader th = tbDebitMemo.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            for (int i = 0; i < 6; i++)
            {
                TableColumn tc = tcm.getColumn(i);
                tc.setHeaderValue(strHeader[i]);
            }
            th.repaint();
        }
        
        public void setMainController(DebitMemoController temp){
            mainController=temp;
        }
        
        public void ViewAll()
        {
            TableModel AllModel = mainController.getAllModel();
            tbDebitMemo.setModel(AllModel);

            JTableHeader th = tbDebitMemo.getTableHeader();      // Setting the Headers
            TableColumnModel tcm = th.getColumnModel();
            for (int i = 0; i < 6; i++)
            {
                TableColumn tc = tcm.getColumn(i);
                tc.setHeaderValue(strHeader[i]);
            }
            th.repaint();
            setComboBox();
        }

        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToDebitMemo();
        }
}
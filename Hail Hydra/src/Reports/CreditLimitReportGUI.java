package Reports;

import HailHydra.GUIModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

public class CreditLimitReportGUI extends JPanel {
    
        private JLabel lblHeader, lblCustomer, lblReportsFound, 
                lblNumOfReportsFound;
        private JTextField tfCustomer;
	private String strHeader[] = {"Customer Name", "Credit Limit", 
                "Current Balance" };
        private DefaultTableModel tbModel;
        private Component component;
	private JTable tbCreditLimit;
        private JScrollPane spTable;
        private JButton btnClose, btnAddPayment, btnViewAllReports;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIModel controller;
	
	

	public CreditLimitReportGUI(GUIModel temp) {
		
		controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
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
		tfCustomer.setBounds(135, 80, 307, 30);
		add(tfCustomer);

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

		tbCreditLimit = new JTable(tbModel) 
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
                
		spTable = new JScrollPane(tbCreditLimit);
		spTable.setBounds(30, 165, 935, 360);
		add(spTable);

                tbCreditLimit.getTableHeader().setFont(fntHeaderTableText);
		tbCreditLimit.getParent().setBackground(tbCreditLimit.getBackground());
                tbCreditLimit.getTableHeader().setPreferredSize(new Dimension(100, 55));
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
                
                btnAddPayment = new JButton("Add Payment");
		btnAddPayment.setFont(fntPlainText);
		btnAddPayment.setBounds(600, 545, 165, 40);
		add(btnAddPayment);

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
	
        
        public static void main(String args[]){
            GUIModel temp=new GUIModel();
            temp.changePanelToCreditLimitReport();
        }
}

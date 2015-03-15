package Reports;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class TermsReportGUI extends JPanel {

	
	private JLabel  lblHeader, lblSearchBy, lblSearch, lblReportsFound, 
                        lblNumofReportsFound, lblDisplay;
	private JTextField tfSearch;
        private String strHeader[] = { "Customer Name", 
                "<html><center>Sales Invoice<br>Number</center></html>",
		"<html><center>Acknowledgement<br>Receipt Number</center></html>", 
                "<html><center>Terms<br>(Days)</html></center>", 
		"<html><center>Due<br>Date</center><html>",  
                "<html><center>Current<br>Balance</center></html>" };
        private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbTermsReport;
        private JScrollPane spTermsReportTable;
	private JCheckBox chckbxNearTerms, chckbxExceededTerms;
        private JRadioButton rdbtnAcknowledgementReceipt, rdbtnCustomer, rdbtnSalesInvoice;
	private ButtonGroup searchBy;
	private JButton btnViewAckReceipt, btnViewAllReports,
			btnViewSalesInvoice, btnClose;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIController controller;
        private ReportController mainController;
	

	public TermsReportGUI(GUIController temp) 
        {
                
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
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
		lblNumofReportsFound.setBounds(194, 200, 250, 30);
		add(lblNumofReportsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(115, 163, 600, 30);
		add(tfSearch);
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
                            
                        } else if (tfSearch.getText().length() == 0)  //if nothing is typed display all
                        {
                      
                        }
                    }});

		tbModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
                        
                 
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++) {
			tbModel.addColumn(strHeader[i]);
		}

		tbTermsReport = new JTable(tbModel) {
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
		
		tbTermsReport.getTableHeader().setFont(fntHeaderTableText);
		tbTermsReport.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbTermsReport.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbTermsReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbTermsReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbTermsReport, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbTermsReport.setFont(fntPlainText);
                
		spTermsReportTable = new JScrollPane(tbTermsReport);
		spTermsReportTable.setBounds(30, 245, 935, 280);
		add(spTermsReportTable);

		tbTermsReport.getParent().setBackground(tbTermsReport.getBackground());
		tbTermsReport.getTableHeader().setResizingAllowed(false);
		tbTermsReport.getTableHeader().setReorderingAllowed(false);
		tbTermsReport.setColumnSelectionAllowed(true);
		tbTermsReport.setRowSelectionAllowed(true);
		tbTermsReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbTermsReport.setRowHeight(30);
		
		chckbxNearTerms = new JCheckBox("Near Terms");
		chckbxNearTerms.setFont(fntPlainText);
                chckbxNearTerms.setBackground(SystemColor.textHighlight);
                chckbxNearTerms.setSelected(true);
		chckbxNearTerms.setBounds(115, 80, 153, 30);
		add(chckbxNearTerms);
                chckbxNearTerms.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                        }
                    });
		
                chckbxExceededTerms = new JCheckBox("Exceeded Terms");
		chckbxExceededTerms.setFont(fntPlainText);
                chckbxExceededTerms.setBackground(SystemColor.textHighlight);
                chckbxExceededTerms.setSelected(true);
		chckbxExceededTerms.setBounds(270, 80, 220, 30);
		add(chckbxExceededTerms);
                chckbxExceededTerms.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                        }
                    });
                
                rdbtnCustomer = new JRadioButton("Customer");
		rdbtnCustomer.setFont(fntPlainText);
                rdbtnCustomer.setBackground(SystemColor.textHighlight);
		rdbtnCustomer.setBounds(143, 121, 130, 30);
		rdbtnCustomer.setSelected(true);
		add(rdbtnCustomer);
                rdbtnCustomer.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                        }
                    });

		rdbtnSalesInvoice = new JRadioButton("Sales Invoice");
		rdbtnSalesInvoice.setFont(fntPlainText);
                rdbtnSalesInvoice.setBackground(SystemColor.textHighlight);
		rdbtnSalesInvoice.setBounds(274, 121, 167, 30);
		add(rdbtnSalesInvoice);
                rdbtnSalesInvoice.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                        }
                    });

		rdbtnAcknowledgementReceipt = new JRadioButton("Acknowledgement Receipt");
		rdbtnAcknowledgementReceipt.setFont(fntPlainText);
                rdbtnAcknowledgementReceipt.setBackground(SystemColor.textHighlight);
		rdbtnAcknowledgementReceipt.setBounds(438, 121, 292, 30);
		add(rdbtnAcknowledgementReceipt);
                rdbtnAcknowledgementReceipt.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                        }
                    });

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnCustomer);
		searchBy.add(rdbtnSalesInvoice);
		searchBy.add(rdbtnAcknowledgementReceipt);
                
                btnViewAllReports = new JButton("View All Reports");
		btnViewAllReports.setFont(fntPlainText);
		btnViewAllReports.setBounds(725, 190, 240, 40);
		add(btnViewAllReports);
                btnViewAllReports.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                        }
                    });
                
                btnViewAckReceipt = new JButton("View Acknowledgement Receipt");
		btnViewAckReceipt.setFont(fntPlainText);
		btnViewAckReceipt.setBounds(380, 545, 353, 40);
		add(btnViewAckReceipt);
                btnViewAckReceipt.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                        }
                    });
                
                btnViewSalesInvoice = new JButton("View Sales Invoice");
		btnViewSalesInvoice.setFont(fntPlainText);
		btnViewSalesInvoice.setBounds(30, 545, 225, 40);
		add(btnViewSalesInvoice);
                btnViewSalesInvoice.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
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
        public void setItemCount(int itemcount)
        {
            lblNumofReportsFound.setText(Integer.toString(itemcount));
        }
        
        public void setTableModel(TableModel tbm)
        {                  // Setting the Headers
            tbTermsReport.setModel(tbm);
            JTableHeader th = tbTermsReport.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            for (int i = 0; i < 6; i++)
            {
                TableColumn tc = tcm.getColumn(i);
                tc.setHeaderValue(strHeader[i]);
            }
            th.repaint();
        }
        
        public void setMainController(ReportController temp){
            mainController=temp;
        }
        
        public void ViewAll()
        {
            TableModel AllModel = mainController.getAllTermsModel();
            tbTermsReport.setModel(AllModel);

            JTableHeader th = tbTermsReport.getTableHeader();      // Setting the Headers
            TableColumnModel tcm = th.getColumnModel();
            for (int i = 0; i < 5; i++)
            {
                TableColumn tc = tcm.getColumn(i);
                tc.setHeaderValue(strHeader[i]);
            }
            th.repaint();
        }
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToTermsReport();
        }
}

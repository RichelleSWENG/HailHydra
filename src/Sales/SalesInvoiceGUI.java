package Sales;

import HailHydra.GUIController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import TableRenderer.TableRenderer;
import java.awt.Color;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class SalesInvoiceGUI extends JPanel
{
        protected JLabel  lblHeader, lblCustomer, lblAddress, lblPwdIdNumber,
		  lblSINum, lblDate, lblPONum, lblDRNum, lblDiscount, lblSubtotal,
		  lblVat, lblBalance, lblSalesperson, lblOrderedBy, lblDeliveryNotes,
		  lblDeliveredBy, lblTotal;
        protected JTextField tfPwdNum, tfSINum, tfPONum, tfDRNum,
		  tfSalesperson, tfOrderedBy, tfDeliveredBy;
        protected JFormattedTextField ftfDate, ftfDiscount, ftfSubtotal, ftfTotal,
                  ftfVat, ftfBalance;
	protected JTextArea taAddress, taDeliveryNotes;
	protected String strHeader[] = { "Quantity", "    Part Number    ", 
                  "        Description        ", 
                  "<html><center>   Unit   <br>   Price   </center></html>",
                  "  Subtotal  "};
	protected DefaultTableModel tbModel;
        protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        protected TableColumnModel tbColumnRenderer;
        protected TableColumn tbColumn;
        protected Component component;
	protected JTable tbSalesInvoice;
        protected JScrollPane spTable, spAddress, spDeliveryNotes;
        protected JButton btnAddItem, btnDeleteItem;
        protected Font fntPlainText, fntHeaderText, fntHeaderTableText;
        protected DateFormat dateFormat;
        protected JComboBox cmbCustomer;
        
        public SalesInvoiceGUI()
        {
                setBounds(0, 0, 1000, 620);
		setLayout(null);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                lblHeader = new JLabel("");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 80, 111, 30);
		add(lblCustomer);

		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(30, 110, 111, 30);
		add(lblAddress);
                
                lblPwdIdNumber = new JLabel("PWD ID Number:");
		lblPwdIdNumber.setFont(fntPlainText);
		lblPwdIdNumber.setBounds(30, 170, 174, 30);
		add(lblPwdIdNumber);
                
                lblSINum = new JLabel("Sales Invoice #:");
		lblSINum.setFont(fntPlainText);
		lblSINum.setBounds(530, 80, 170, 30);
		add(lblSINum);
                
                lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(630, 110, 73, 30);
		add(lblDate);
                
                lblPONum = new JLabel("P.O. Number:");
		lblPONum.setFont(fntPlainText);
		lblPONum.setBounds(552, 140, 138, 30);
		add(lblPONum);
                
                lblDRNum = new JLabel("D.R. Number:");
		lblDRNum.setFont(fntPlainText);
		lblDRNum.setBounds(552, 170, 138, 30);
		add(lblDRNum);
                
                lblSalesperson = new JLabel("Salesperson:");
		lblSalesperson.setFont(fntPlainText);
		lblSalesperson.setBounds(30, 435, 147, 30);
		add(lblSalesperson);
                
                lblOrderedBy = new JLabel("Ordered By:");
		lblOrderedBy.setFont(fntPlainText);
		lblOrderedBy.setBounds(30, 465, 133, 30);
		add(lblOrderedBy);

		lblDeliveredBy = new JLabel("Delivered By:");
		lblDeliveredBy.setFont(fntPlainText);
		lblDeliveredBy.setBounds(30, 495, 147, 30);
		add(lblDeliveredBy);

		lblDeliveryNotes = new JLabel("Delivery Notes:");
		lblDeliveryNotes.setFont(fntPlainText);
		lblDeliveryNotes.setBounds(30, 525, 147, 30);
		add(lblDeliveryNotes);
                
                lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(fntPlainText);
		lblDiscount.setBounds(705, 385, 105, 30);
		add(lblDiscount);

                lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(fntPlainText);
		lblSubtotal.setBounds(710, 415, 97, 30);
		add(lblSubtotal);
                
                lblVat = new JLabel("VAT:");
		lblVat.setFont(fntPlainText);
		lblVat.setBounds(745, 445, 80, 30);
		add(lblVat);
                
                lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(738, 475, 80, 30);
		add(lblTotal);
                
                lblBalance = new JLabel("Balance:");
		lblBalance.setFont(fntPlainText);
		lblBalance.setBounds(708, 505, 97, 30);
		add(lblBalance);

		tfPwdNum = new JTextField();
		tfPwdNum.setFont(fntPlainText);
		tfPwdNum.setBounds(200, 170, 300, 30);
		add(tfPwdNum);

		tfSINum = new JTextField();
		tfSINum.setFont(fntPlainText);
		tfSINum.setBounds(690, 80, 275, 30);
		add(tfSINum);
                
                ftfDate= new JFormattedTextField(dateFormat);
                ftfDate.setValue(new java.util.Date());
                ftfDate.setFont(fntPlainText);
                ftfDate.setBounds(690, 110, 275, 30);
                add(ftfDate);

		tfPONum = new JTextField();
		tfPONum.setFont(fntPlainText);
		tfPONum.setBounds(690, 140, 275, 30);
		add(tfPONum);

		tfDRNum = new JTextField();
		tfDRNum.setFont(fntPlainText);
		tfDRNum.setBounds(690, 170, 275, 30);
		add(tfDRNum);

		tfSalesperson = new JTextField();
		tfSalesperson.setFont(fntPlainText);
		tfSalesperson.setBounds(165, 435, 335, 30);
		add(tfSalesperson);

		tfOrderedBy = new JTextField();
		tfOrderedBy.setFont(fntPlainText);
		tfOrderedBy.setBounds(165, 465, 335, 30);
		add(tfOrderedBy);

		tfDeliveredBy = new JTextField();
		tfDeliveredBy.setFont(fntPlainText);
		tfDeliveredBy.setBounds(165, 495, 335, 30);
		add(tfDeliveredBy);
                
		ftfDiscount = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfDiscount.setFont(fntPlainText);
                ftfDiscount.setHorizontalAlignment(JTextField.RIGHT);
                ftfDiscount.setValue(new Float(00.0F));
                ftfDiscount.setBounds(800, 385, 165, 30);
                add(ftfDiscount);

		ftfSubtotal = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfSubtotal.setFont(fntPlainText);
                ftfSubtotal.setHorizontalAlignment(JTextField.RIGHT);
                ftfSubtotal.setValue(new Float(00.0F));
                ftfSubtotal.setEditable(false);
		ftfSubtotal.setBounds(800, 415, 165, 30);
		add(ftfSubtotal);

		ftfVat = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfVat.setFont(fntPlainText);
                ftfVat.setHorizontalAlignment(JTextField.RIGHT);
                ftfVat.setValue(new Float(00.0F));
                ftfVat.setEditable(false);
		ftfVat.setBounds(800, 445, 165, 30);
		add(ftfVat);
                
                ftfTotal = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfTotal.setFont(fntPlainText);
                ftfTotal.setHorizontalAlignment(JTextField.RIGHT);
                ftfTotal.setValue(new Float(00.0F));
                ftfTotal.setEditable(false);
                ftfTotal.setBounds(800, 475, 165, 30);
                add(ftfTotal);

		ftfBalance = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfBalance.setFont(fntPlainText);
                ftfBalance.setHorizontalAlignment(JTextField.RIGHT);
                ftfBalance.setValue(new Float(00.0F));
                ftfBalance.setEditable(false);
		ftfBalance.setBounds(800, 505, 165, 30);
		add(ftfBalance);
		

		cmbCustomer = new JComboBox();
                AutoCompleteDecorator.decorate(cmbCustomer);
		cmbCustomer.setFont(new Font("Arial", Font.PLAIN, 21));
		cmbCustomer.setBounds(200, 80, 300, 30);
		add(cmbCustomer);
                
                taAddress = new JTextArea();
                taAddress.setFont(fntPlainText);
                taAddress.setWrapStyleWord(true);
                taAddress.setLineWrap(true);
                taAddress.setEnabled(false);
		add(taAddress);
                
                taDeliveryNotes = new JTextArea();
		taDeliveryNotes.setFont(fntPlainText);
                taDeliveryNotes.setWrapStyleWord(true);
                taDeliveryNotes.setLineWrap(true);
		add(taDeliveryNotes);
                
                spAddress = new JScrollPane(taAddress);
		spAddress.setBounds(200, 115, 300, 50);
		add(spAddress);
                
                spDeliveryNotes = new JScrollPane(taDeliveryNotes);
		spDeliveryNotes.setBounds(30, 554, 472, 30);
		add(spDeliveryNotes);
                
 
		tbModel = new DefaultTableModel() 
                {
			public boolean isCellEditable(int rowIndex, int mColIndex) 
                        {
                            if (cmbCustomer.getSelectedItem() == null || cmbCustomer.getSelectedItem().equals(""))
                                return false;
                            if (mColIndex == 2 || mColIndex == 4)
				return false;
                            else
                                return true;
			}
		};
                
                for (int i = 0; i < strHeader.length; i++) 
                {
			tbModel.addColumn(strHeader[i]);
             
		}

		tbSalesInvoice = new JTable(tbModel)
                {
			public TableCellRenderer getCellRenderer(int row, int column)
                        {
				return new TableRenderer();
			}
                        
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
                        {
                            component = super.prepareRenderer(renderer, row, column);
                            int modelRow = convertRowIndexToModel(row);
                            if (!isRowSelected(modelRow))
                            {
                                component.setBackground(Color.WHITE);
                            } 
                            else
                            {
                                component.setBackground(Color.yellow);
                            }
                            return component;
                        }
		};
		
		tbSalesInvoice.getTableHeader().setFont(fntHeaderTableText);
		tbSalesInvoice.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbSalesInvoice.getTableHeader().setResizingAllowed(false);
		tbCellRenderer= tbSalesInvoice.getTableHeader().getDefaultRenderer();
		tbColumnRenderer= tbSalesInvoice.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbSalesInvoice,
					tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbSalesInvoice.setFont(fntPlainText);

		spTable = new JScrollPane(tbSalesInvoice);
		spTable.setBounds(30, 212, 935, 163);
		add(spTable);

		tbSalesInvoice.getParent().setBackground(tbSalesInvoice.getBackground());
		tbSalesInvoice.getTableHeader().setResizingAllowed(false);
		tbSalesInvoice.getTableHeader().setReorderingAllowed(false);
		tbSalesInvoice.setColumnSelectionAllowed(true);
		tbSalesInvoice.setRowSelectionAllowed(true);
		tbSalesInvoice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbSalesInvoice.setRowHeight(30);
                
                btnAddItem = new JButton("Add Item");
                btnAddItem.setFont(fntPlainText);
                btnAddItem.setBounds(30, 385, 147, 40);
                add(btnAddItem);

                btnDeleteItem= new JButton("Delete Item");
                btnDeleteItem.setFont(fntPlainText);
                btnDeleteItem.setBounds(190, 385, 147, 40);
                add(btnDeleteItem);
                
        }
        
        public static void main(String args[])
        {
            GUIController temp = new GUIController();
            temp.changePanelToAddSalesInvoice();
        }
}

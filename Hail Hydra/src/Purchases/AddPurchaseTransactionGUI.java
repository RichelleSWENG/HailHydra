package Purchases;

import HailHydra.GUIModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
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
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;

public class AddPurchaseTransactionGUI extends JPanel
{

	private JLabel  lblHeader, lblPurchaseTransactionNum, lblSupplier, lblAddress,
			lblDate, lblPONum, lblSINumber, lblORNumber, lblOrderedBy,
			lblReceivedBy, lblReceivingNotes, lblDiscount, lblSubtotal, lblVat,
			lblTotal, lblBalance;
	private JTextField tfSupplier, tfPurchaseTransaction, tfPONumber,
			tfSINumber, tfORNumber, tfOrderedBy, textField, tfRDAndTN,
			tfDiscount, tfSubtotal, tfVAT, tfTotal, tfBalance;
        private JFormattedTextField ftfDate;
        private JTextArea taAddress;
	private String strHeader[] = { "Quantity", "    Part Number    ", 
                  "        Description        ", 
                  "<html><center>   Unit   <br>   Price   </center></html>",
                  "  Total  "};
	protected DefaultTableModel tbModel;
        protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        protected TableColumnModel tbColumnRenderer;
        protected TableColumn tbColumn;
        protected Component component;
	private JTable tbPurchaseTransaction;
        private JScrollPane spTable;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private JButton  btnSubmit, btnCancel, btnAddItem;
        private GUIModel guiController;
        protected DateFormat dateFormat;
	
	public AddPurchaseTransactionGUI(GUIModel temp)
	{
                guiController=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                lblHeader = new JLabel("Add Purchase Transaction");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(30, 80, 96, 30);
		add(lblSupplier);

		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(30, 120, 96, 30);
		add(lblAddress);

		lblPurchaseTransactionNum = new JLabel("Purchase Transaction #:");
		lblPurchaseTransactionNum.setFont(fntPlainText);
		lblPurchaseTransactionNum.setBounds(532, 80, 262, 30);
		add(lblPurchaseTransactionNum);
                
                lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(532, 110, 67, 30);
		add(lblDate);
                
                lblPONum = new JLabel("P.O. Number:");
		lblPONum.setFont(fntPlainText);
		lblPONum.setBounds(532, 140, 141, 30);
		add(lblPONum);
                
                lblSINumber = new JLabel("S.I. Number:");
		lblSINumber.setFont(fntPlainText);
		lblSINumber.setBounds(532, 170, 134, 30);
		add(lblSINumber);

		lblORNumber = new JLabel("O.R. Number:");
		lblORNumber.setFont(fntPlainText);
		lblORNumber.setBounds(532, 200, 134, 30);
		add(lblORNumber);
                
                lblOrderedBy = new JLabel("Ordered By:");
		lblOrderedBy.setFont(fntPlainText);
		lblOrderedBy.setBounds(30, 407, 129, 30);
		add(lblOrderedBy);
                
                lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 439, 141, 30);
		add(lblReceivedBy);
                
                lblReceivingNotes = new JLabel("Receiving Notes:");
		lblReceivingNotes.setFont(fntPlainText);
		lblReceivingNotes.setBounds(30, 471, 207, 30);
		add(lblReceivingNotes);
                
                lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(fntPlainText);
		lblDiscount.setBounds(584, 407, 116, 30);
		add(lblDiscount);
                
                lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(fntPlainText);
		lblSubtotal.setBounds(584, 439, 116, 30);
		add(lblSubtotal);
                
                lblVat = new JLabel("VAT:");
		lblVat.setFont(fntPlainText);
		lblVat.setBounds(584, 471, 56, 30);
		add(lblVat);
                
                lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(584, 503, 67, 30);
		add(lblTotal);
                
                lblBalance = new JLabel("Balance:");
		lblBalance.setFont(fntPlainText);
		lblBalance.setBounds(584, 530, 67, 30);
		add(lblBalance);
                
                tfSupplier = new JTextField();
		tfSupplier.setFont(fntPlainText);
		tfSupplier.setBounds(121, 63, 400, 30);
		add(tfSupplier);
                
		taAddress = new JTextArea();
		taAddress.setEditable(false);
		taAddress.setFont(fntPlainText);
		taAddress.setBounds(121, 105, 401, 116);
		add(taAddress);

		tfPurchaseTransaction = new JTextField();
		tfPurchaseTransaction.setFont(fntPlainText);
		tfPurchaseTransaction.setBounds(787, 63, 166, 30);
		add(tfPurchaseTransaction);

		ftfDate= new JFormattedTextField(dateFormat);
                ftfDate.setValue(new java.util.Date());
                ftfDate.setFont(fntPlainText);
                ftfDate.setBounds(590, 110, 375, 30);
                add(ftfDate);

		tfPONumber = new JTextField();
		tfPONumber.setFont(fntPlainText);
		tfPONumber.setBounds(666, 127, 287, 30);
		add(tfPONumber);

		tfSINumber = new JTextField();
		tfSINumber.setFont(fntPlainText);
		tfSINumber.setBounds(666, 159, 287, 30);
		add(tfSINumber);

		tfORNumber = new JTextField();
		tfORNumber.setFont(fntPlainText);
		tfORNumber.setBounds(676, 191, 277, 30);
		add(tfORNumber);

		tfOrderedBy = new JTextField();
		tfOrderedBy.setFont(fntPlainText);
		tfOrderedBy.setBounds(159, 407, 363, 30);
		add(tfOrderedBy);

		textField = new JTextField();
		textField.setFont(fntPlainText);
		textField.setBounds(169, 439, 353, 30);
		add(textField);

		tfRDAndTN = new JTextField();
		tfRDAndTN.setFont(fntPlainText);
		tfRDAndTN.setBounds(30, 503, 492, 30);
		add(tfRDAndTN);

		tfDiscount = new JTextField();
		tfDiscount.setFont(fntPlainText);
		tfDiscount.setText("0.00");
		tfDiscount.setEditable(false);
		tfDiscount.setBounds(691, 407, 262, 30);
		add(tfDiscount);

		tfSubtotal = new JTextField();
		tfSubtotal.setFont(fntPlainText);
		tfSubtotal.setText("0.00");
		tfSubtotal.setEditable(false);
		tfSubtotal.setBounds(691, 439, 262, 30);
		add(tfSubtotal);

		tfVAT = new JTextField();
		tfVAT.setFont(fntPlainText);
		tfVAT.setText("0.00");
		tfVAT.setEditable(false);
		tfVAT.setBounds(638, 471, 315, 30);
		add(tfVAT);

		tfTotal = new JTextField();
		tfTotal.setFont(fntPlainText);
		tfTotal.setText("0.00");
		tfTotal.setEditable(false);
		tfTotal.setBounds(648, 503, 305, 30);
		add(tfTotal);
                
		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if(mColIndex != 4)
					return true;
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}
		setLayout(null);

		tbPurchaseTransaction = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}
		};

		tbPurchaseTransaction.getTableHeader().setFont(fntHeaderTableText);
		tbPurchaseTransaction.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbPurchaseTransaction.getTableHeader().setResizingAllowed(false);
		tbCellRenderer= tbPurchaseTransaction.getTableHeader().getDefaultRenderer();
		tbColumnRenderer= tbPurchaseTransaction.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbPurchaseTransaction,
					tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbPurchaseTransaction.setFont(fntPlainText);

		spTable = new JScrollPane(tbPurchaseTransaction);
		spTable.setBounds(30, 228, 935, 168);
		add(spTable);

		tbPurchaseTransaction.getParent().setBackground(tbPurchaseTransaction.getBackground());
		tbPurchaseTransaction.getTableHeader().setResizingAllowed(false);
		tbPurchaseTransaction.getTableHeader().setReorderingAllowed(false);
		tbPurchaseTransaction.setColumnSelectionAllowed(true);
		tbPurchaseTransaction.setRowSelectionAllowed(true);
		tbPurchaseTransaction.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPurchaseTransaction.setRowHeight(30);
                
                btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 175, 40);
		add(btnAddItem);
                btnAddItem.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                
                        }
                    });

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToPurchaseTransactionList();
                        }
                    });

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
                btnCancel.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToPurchaseTransactionList();
                        }
                    });
	}
        
        public static void main(String args[])
        {
           GUIModel temp=new GUIModel();
           temp.changePanelToAddPurchaseTransaction();
        }
}

package Purchases;

import HailHydra.GUIController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import TableRenderer.TableRenderer;
import javax.swing.JButton;

public class PurchaseTransactionGUI extends JPanel
{
	protected JLabel lblHeader, lblPurchaseTransactionNum, lblSupplier, lblAddress, lblDate, lblPONum, lblSINum,
                  lblORNum, lblOrderedBy, lblReceivedBy, lblReceivingNotes, lblDiscount, lblSubtotal, lblVat, lblTotal,
                  lblBalance;
	protected JTextField tfPurchaseTransactionNum, tfPONum, tfSINum, tfDRNum, tfOrderedBy, tfReceivedBy;
	protected JFormattedTextField ftfDate, ftfDiscount, ftfSubtotal, ftfTotal, ftfVat, ftfBalance;
	protected JTextArea taAddress, taReceivingNotes;
	protected String strHeader[] = { "Quantity", "    Part Number    ", "        Description        ",
                  "<html><center>   Unit   <br>   Price   </center></html>", "  Subtotal  " };
	protected DefaultTableModel tbModel;
	protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	protected TableColumnModel tbColumnRenderer;
	protected TableColumn tbColumn;
	protected Component component;
	protected JTable tbPurchaseTransaction;
	protected JScrollPane spTable, spAddress, spReceivingNotes;
        protected JButton btnAddItem, btnDeleteItem;
	protected Font fntPlainText, fntHeaderText, fntHeaderTableText;
	protected DateFormat dateFormat;
	protected JComboBox cmbSupplier;
	protected int modelRow;

	public PurchaseTransactionGUI()
	{
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		lblHeader = new JLabel("");
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
		lblPurchaseTransactionNum.setBounds(530, 80, 262, 30);
		add(lblPurchaseTransactionNum);

		lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(715, 110, 67, 30);
		add(lblDate);

		lblPONum = new JLabel("P.O. Number:");
		lblPONum.setFont(fntPlainText);
		lblPONum.setBounds(637, 140, 141, 30);
		add(lblPONum);

		lblSINum = new JLabel("S.I. Number:");
		lblSINum.setFont(fntPlainText);
		lblSINum.setBounds(647, 170, 134, 30);
		add(lblSINum);

		lblORNum = new JLabel("D.R. Number:");
		lblORNum.setFont(fntPlainText);
		lblORNum.setBounds(637, 200, 134, 30);
		add(lblORNum);

		lblOrderedBy = new JLabel("Ordered By:");
		lblOrderedBy.setFont(fntPlainText);
		lblOrderedBy.setBounds(30, 435, 129, 30);
		add(lblOrderedBy);

		lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 475, 141, 30);
		add(lblReceivedBy);

		lblReceivingNotes = new JLabel("Receiving Notes:");
		lblReceivingNotes.setFont(fntPlainText);
		lblReceivingNotes.setBounds(30, 515, 207, 30);
		add(lblReceivingNotes);

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

		tfPurchaseTransactionNum = new JTextField();
		tfPurchaseTransactionNum.setFont(fntPlainText);
		tfPurchaseTransactionNum.setBounds(770, 80, 195, 30);
		add(tfPurchaseTransactionNum);

		ftfDate = new JFormattedTextField(dateFormat);
		ftfDate.setValue(new java.util.Date());
		ftfDate.setFont(fntPlainText);
		ftfDate.setBounds(770, 110, 195, 30);
		add(ftfDate);

		tfPONum = new JTextField();
		tfPONum.setFont(fntPlainText);
		tfPONum.setBounds(770, 140, 195, 30);
		add(tfPONum);

		tfSINum = new JTextField();
		tfSINum.setFont(fntPlainText);
		tfSINum.setBounds(770, 170, 195, 30);
		add(tfSINum);

		tfDRNum = new JTextField();
		tfDRNum.setFont(fntPlainText);
		tfDRNum.setBounds(770, 200, 195, 30);
		add(tfDRNum);

		tfOrderedBy = new JTextField();
		tfOrderedBy.setFont(fntPlainText);
		tfOrderedBy.setBounds(160, 435, 340, 30);
		add(tfOrderedBy);

		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(160, 475, 340, 30);
		add(tfReceivedBy);

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

		taAddress = new JTextArea();
		taAddress.setFont(fntPlainText);
		taAddress.setWrapStyleWord(true);
		taAddress.setLineWrap(true);
                taAddress.setEditable(false);
		add(taAddress);

		taReceivingNotes = new JTextArea();
		taReceivingNotes.setFont(fntPlainText);
		taReceivingNotes.setWrapStyleWord(true);
		taReceivingNotes.setLineWrap(true);
		add(taReceivingNotes);

		spAddress = new JScrollPane(taAddress);
		spAddress.setBounds(125, 115, 375, 110);
		add(spAddress);

		spReceivingNotes = new JScrollPane(taReceivingNotes);
		spReceivingNotes.setBounds(30, 545, 470, 40);
		add(spReceivingNotes);
                
                cmbSupplier = new JComboBox();
		AutoCompleteDecorator.decorate(cmbSupplier);
		cmbSupplier.setFont(new Font("Arial", Font.PLAIN, 21));
		cmbSupplier.setBounds(125, 80, 375, 30);
		add(cmbSupplier);

		tbModel = new DefaultTableModel() 
                {
			public boolean isCellEditable(int rowIndex, int mColIndex) 
                        {
                            if (cmbSupplier.getSelectedItem() == null || cmbSupplier.getSelectedItem().equals(""))
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
		tbCellRenderer = tbPurchaseTransaction.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbPurchaseTransaction.getColumnModel();
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
		spTable.setBounds(30, 235, 935, 145);
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
            temp.changePanelToAddPurchaseTransaction();
        }
}

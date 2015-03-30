package Payables;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;

public class ViewPaymentPayablesGUI extends JPanel {

	private JLabel lblHeader, lblDisplay, lblSupplier, lblPaymentType, lblDate,
			lblAmount, lblPurchaseTransactionNumber, lblReceivedDate, lblApprovedDate,
			lblReturnedDate, lblReceivedBy, lblApprovedBy, lblReturnedBy;
	private JFormattedTextField ftfDate, ftfAmount;
	private String strHeader[] = { "Date",
			"<html><center>Purchase<br>Transaction<br>Number</center></html>", "<html><center>Credit<br>Memo<br>Number</center><html>",
			"Status", "<html><center>Original<br>Amount</center></html>",
			"<html><center>Current<br>Balance</center></html>",
			"<html><center>Amount<br>Applied</center></html>" },
			strPayment[] = { "Cash", "Bank to Bank", "Check", "Credit Memo" };
	private JComboBox cmbPaymentType, cmbSupplier;
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbPayment;
	private JScrollPane spPaymentTable;
	private JButton btnSubmit, btnCancel;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private DateFormat dateFormat;
	private GUIController controller;
	private PaymentController mainController;
	private JTextField tfCreditMemoNo, ftfReceivedDate, ftfApprovedDate,
			ftfReturnedDate, tfReceivedBy, tfApprovedBy, tfReturnedBy;
	protected JScrollPane spNotes;
	private JLabel lblNotes;
	private JTextArea taNotes;

	public ViewPaymentPayablesGUI(GUIController temp) {
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		lblHeader = new JLabel("View Payment");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(30, 100, 111, 30);
		add(lblSupplier);

		lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setFont(fntPlainText);
		lblPaymentType.setBounds(30, 140, 157, 30);
		add(lblPaymentType);

		lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(594, 100, 111, 30);
		add(lblDate);

		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(fntPlainText);
		lblAmount.setBounds(594, 140, 111, 30);
		add(lblAmount);

		cmbSupplier = new JComboBox();
		AutoCompleteDecorator.decorate(cmbSupplier);
		cmbSupplier.setFont(fntPlainText);
		cmbSupplier.setBounds(186, 100, 329, 30);
		add(cmbSupplier);
                cmbSupplier.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if (cmbSupplier.getSelectedIndex() != 0)
                        {
                            mainController.searchActivePayables((String) cmbSupplier.getSelectedItem());
                        }
                    }
                });

		ftfDate = new JFormattedTextField(dateFormat);
		ftfDate.setValue(new java.util.Date());
		ftfDate.setFont(fntPlainText);
		ftfDate.setBounds(680, 100, 285, 30);
		add(ftfDate);
                

		ftfAmount = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfAmount.setFont(fntPlainText);
		ftfAmount.setValue(new Float(00.0F));
		ftfAmount.setBounds(680, 140, 285, 30);
		add(ftfAmount);

		tbModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				if (mColIndex == 5)
					return true;
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++) {
			tbModel.addColumn(strHeader[i]);
		}

		tbPayment = new JTable(tbModel) {
			public TableCellRenderer getCellRenderer(int row, int column) {
				return new TableRenderer();
			}
		};
		tbPayment.getTableHeader().setFont(fntHeaderTableText);
		tbPayment.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbPayment.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbPayment.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbPayment.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1) {
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbPayment, tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		spPaymentTable = new JScrollPane(tbPayment);
		spPaymentTable.setBounds(30, 226, 935, 151);
		add(spPaymentTable);

		tbPayment.setFont(fntPlainText);
		tbPayment.getParent().setBackground(tbPayment.getBackground());
		tbPayment.getTableHeader().setResizingAllowed(false);
		tbPayment.getTableHeader().setReorderingAllowed(false);
		tbPayment.setColumnSelectionAllowed(true);
		tbPayment.setRowSelectionAllowed(true);
		tbPayment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPayment.setRowHeight(30);

		tfCreditMemoNo = new JTextField();
		tfCreditMemoNo.setFont(fntPlainText);
		tfCreditMemoNo.setBounds(186, 180, 329, 30);
		tfCreditMemoNo.setEditable(false);
		add(tfCreditMemoNo);
		tfCreditMemoNo.setColumns(10);
                tfCreditMemoNo.setText("");

		cmbPaymentType = new JComboBox();
		cmbPaymentType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cmbPaymentType.getSelectedItem() == "Credit Memo")
					tfCreditMemoNo.setEditable(true);
				else
					tfCreditMemoNo.setEditable(false);
			}
		});
		cmbPaymentType.setFont(fntPlainText);
		cmbPaymentType.setBounds(186, 140, 329, 30);
		add(cmbPaymentType);
		
		for (int i = 0; i < strPayment.length; i++)
			cmbPaymentType.addItem(strPayment[i]);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            if(Float.parseFloat(ftfAmount.getText())!=0.00)
                            {
                                if(Float.parseFloat(ftfAmount.getText())==getSum())
                                {
                                    if(checkCurrentBalance())
                                    {
                                        addAllPayment();
                                        deductCurrentBalance();
                                        controller.changePanelToPayablesList();
                                    }
                                }
                                else
                                   JOptionPane.showMessageDialog(null, "Not same Amount");
                            }
                            else
                                JOptionPane.showMessageDialog(null, "Pls fill the Amount");
			}
		});

		lblReturnedBy = new JLabel("Prepared By:");
		lblReturnedBy.setFont(fntPlainText);
		lblReturnedBy.setBounds(30, 388, 143, 30);
		add(lblReturnedBy);

		lblApprovedBy = new JLabel("Approved By:");
		lblApprovedBy.setFont(fntPlainText);
		lblApprovedBy.setBounds(30, 418, 143, 30);
		add(lblApprovedBy);

		lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 448, 143, 30);
		add(lblReceivedBy);

		lblReturnedDate = new JLabel("Date:");
		lblReturnedDate.setFont(fntPlainText);
		lblReturnedDate.setBounds(345, 388, 70, 25);
		add(lblReturnedDate);

		lblApprovedDate = new JLabel("Date:");
		lblApprovedDate.setFont(fntPlainText);
		lblApprovedDate.setBounds(345, 418, 70, 25);
		add(lblApprovedDate);

		lblReceivedDate = new JLabel("Date:");
		lblReceivedDate.setFont(fntPlainText);
		lblReceivedDate.setBounds(345, 448, 70, 25);
		add(lblReceivedDate);

		tfReturnedBy = new JTextField();
		tfReturnedBy.setFont(fntPlainText);
		tfReturnedBy.setBounds(169, 388, 170, 30);
		add(tfReturnedBy);
                tfReturnedBy.setText("");

		tfApprovedBy = new JTextField();
		tfApprovedBy.setFont(fntPlainText);
		tfApprovedBy.setBounds(169, 418, 170, 30);
		add(tfApprovedBy);
                tfApprovedBy.setText("");
                
		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(169, 448, 170, 30);
		add(tfReceivedBy);
                tfReceivedBy.setText("");

		ftfReturnedDate = new JFormattedTextField(dateFormat);
		ftfReturnedDate.setFont(fntPlainText);
		ftfReturnedDate.setBounds(405, 388, 120, 30);
		add(ftfReturnedDate);
                ftfReturnedDate.setText("");

		ftfApprovedDate = new JFormattedTextField(dateFormat);
		ftfApprovedDate.setFont(fntPlainText);
		ftfApprovedDate.setBounds(405, 418, 120, 30);
		add(ftfApprovedDate);
                ftfApprovedDate.setText("");

		ftfReceivedDate = new JFormattedTextField(dateFormat);
		ftfReceivedDate.setFont(fntPlainText);
		ftfReceivedDate.setBounds(405, 448, 120, 30);
		add(ftfReceivedDate);
                ftfReceivedDate.setText("");
                
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);

		lblPurchaseTransactionNumber = new JLabel("P.T. No. : ");
		lblPurchaseTransactionNumber.setFont(fntPlainText);
		lblPurchaseTransactionNumber.setBounds(30, 180, 157, 30);
		add(lblPurchaseTransactionNumber);

		lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(30, 480, 115, 30);
		add(lblNotes);

		taNotes = new JTextArea();
		taNotes.setFont(fntPlainText);
		taNotes.setWrapStyleWord(true);
		taNotes.setLineWrap(true);
		taNotes.setBounds(30, 495, 490, 40);
		add(taNotes);
		
		spNotes = new JScrollPane(taNotes);
		spNotes.setBounds(30, 516, 490, 65);
		add(spNotes);
                taNotes.setText("");
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changePanelToPayablesList();
			}
		});
	}

	public void setMainController(PaymentController temp) {
		mainController = temp;
	}
        
        public void setSupplier()
        {
            ArrayList<String> supplier= new ArrayList<String>();
            try {
                supplier=mainController.getSupplier();
            } catch (SQLException ex) {
                Logger.getLogger(AddPaymentPayablesGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            cmbSupplier.addItem("");
            for(int i=0;i<supplier.size();i++)
            {
                cmbSupplier.addItem(supplier.get(i));
            }
        }
        
        public void addAllPayment()
        {       
            for (int i= 0; i < tbPayment.getRowCount(); i++)
             {   
                if(tbPayment.getValueAt(i, 5)!=null)
                {
                    Payment p;       
                    p = new Payment((int) tbPayment.getValueAt(i, 1),Float.parseFloat((String)tbPayment.getValueAt(i, 5)),ftfReceivedDate.getText(),ftfApprovedDate.getText(),ftfReturnedDate.getText(),tfReceivedBy.getText(),tfApprovedBy.getText(),tfReturnedBy.getText(),(String)cmbPaymentType.getSelectedItem(),tfCreditMemoNo.getText(),ftfDate.getText(),taNotes.getText());
                     mainController.addPayment(p);
                }
              }
        }
        
	public void ViewAll() {
		JTableHeader th = tbPayment.getTableHeader(); // Setting the Headers
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 6; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		th.repaint();
                cmbSupplier.removeAllItems();
                setSupplier();
	}
        
        public float getSum()
        {
            float sum=0;
             for (int i= 0; i < tbPayment.getRowCount(); i++)
             {
                 if(tbPayment.getValueAt(i, 5)!=null)
                 {
                    sum=sum+Float.parseFloat((String) tbPayment.getValueAt(i, 5));
                 }
             }
            return sum;
        }
        public boolean checkCurrentBalance()
        {
            for (int i= 0; i < tbPayment.getRowCount(); i++)
            {
                if(tbPayment.getValueAt(i, 5)!=null)
                {
                    BigDecimal big=(BigDecimal) tbPayment.getValueAt(i, 4);
                    if(big.floatValue()< Float.parseFloat((String) tbPayment.getValueAt(i, 5)))
                    {
                        JOptionPane.showMessageDialog(null, "Applied Amount is greater the Current Balance");
                        return false;
                    }
                }
            }
            return true;
        }
        public void deductCurrentBalance()
        {
            float newCurrentBalance;
            int purchase_transaction_id;
            BigDecimal currentbalance;
            for (int i= 0; i < tbPayment.getRowCount(); i++)
            {
                    purchase_transaction_id=(int)tbPayment.getValueAt(i, 1);
                    currentbalance=(BigDecimal) tbPayment.getValueAt(i, 4);
                    if(tbPayment.getValueAt(i, 5)!=null)
                    {
                        newCurrentBalance=currentbalance.floatValue()-Float.parseFloat((String) tbPayment.getValueAt(i, 5));
                        mainController.changeCurrentBalance(purchase_transaction_id, newCurrentBalance);
                        if(newCurrentBalance==0.0)
                        {
                            mainController.changeStatus(purchase_transaction_id);
                        }
                    }
            }
        }
            
	public void setTableModel(TableModel tbm) { // Setting the Headers
		tbPayment.setModel(tbm);
		JTableHeader th = tbPayment.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 6; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		th.repaint();
	}

	public static void main(String args[]) {
		GUIController temp = new GUIController();
		temp.changePanelToViewPaymentPayables();
	}
}

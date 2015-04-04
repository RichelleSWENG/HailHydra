package Collectibles;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
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

import HailHydra.GUIController;
import Payables.AddPaymentPayablesGUI;
import Payables.Payment;
import TableRenderer.TableRenderer;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class AddPaymentCollectiblesGUI extends JPanel {
	private JLabel lblHeader, lblDeditMemoNumber, lblReceivedBy,
			lblReceivedDate, lblCustomer, lblNotes, lblPaymentType, lblDate,
			lblAmount;
	private JFormattedTextField ftfDate, ftfAmount;
	private String strHeader[] = {
			"Date", "Type",
			"<html><center>Acknowledgement Receipt/<br>Sales Invoice Number</center></html>",
			"Status", "<html><center>Original<br>Amount</center></html>",
			"<html><center>Current<br>Balance</center></html>",
			"<html><center>Amount<br>Applied</center></html>" },
			strPaymentType[] = { "Cash", "Bank to Bank", "Check", "Debit Memo" };
	private JComboBox cmbPaymentType, cmbCustomer;
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
	private JTextArea taNotes;
	private JTextField tfDebitMemoNo, ftfReceivedDate, tfReceivedBy;
	protected JScrollPane spNotes;
        private PaymentCollectiblesController mainController;

	public AddPaymentCollectiblesGUI(GUIController temp) {
		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		lblHeader = new JLabel("Add Payment - Collectibles");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 100, 111, 30);
		add(lblCustomer);

		lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(594, 100, 111, 30);
		add(lblDate);

		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(fntPlainText);
		lblAmount.setBounds(594, 140, 111, 30);
		add(lblAmount);

		lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setFont(fntPlainText);
		lblPaymentType.setBounds(30, 140, 157, 30);
		add(lblPaymentType);

		cmbCustomer = new JComboBox();
		cmbCustomer.setFont(fntPlainText);
		cmbCustomer.setBounds(215, 100, 315, 30);
		add(cmbCustomer);
                cmbCustomer.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if (cmbCustomer.getSelectedIndex() != 0)
                        {
                            mainController.searchActivePayables((String) cmbCustomer.getSelectedItem());
                        }
                    }
                });

		ftfDate = new JFormattedTextField(dateFormat);
		ftfDate.setValue(new java.util.Date());
		ftfDate.setFont(fntPlainText);
		ftfDate.setBounds(680, 100, 285, 30);
		add(ftfDate);

		ftfAmount = new JFormattedTextField(new DecimalFormat("###0.00"));
		ftfAmount.setFont(fntPlainText);
		ftfAmount.setValue(new Float(00.0F));
		ftfAmount.setBounds(680, 140, 285, 30);
		add(ftfAmount);
		
		tfDebitMemoNo = new JTextField();
		tfDebitMemoNo.setFont(fntPlainText);
		tfDebitMemoNo.setBounds(215, 180, 315, 30);
		tfDebitMemoNo.setEditable(false);
		add(tfDebitMemoNo);
		tfDebitMemoNo.setColumns(10);

		cmbPaymentType = new JComboBox();
		cmbPaymentType.setFont(fntPlainText);
		cmbPaymentType.setBounds(215, 140, 315, 30);
		add(cmbPaymentType);
		cmbPaymentType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cmbPaymentType.getSelectedItem() == "Credit Memo")
					tfDebitMemoNo.setEditable(true);
				else
					tfDebitMemoNo.setEditable(false);
			}
		});
		
		for (int i = 0; i < strPaymentType.length; i++) {
			cmbPaymentType.addItem(strPaymentType[i]);
		}

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
		tbPayment.setFont(fntPlainText);
		spPaymentTable = new JScrollPane(tbPayment);
		spPaymentTable.setBounds(30, 222, 935, 209);
		add(spPaymentTable);

		tbPayment.getParent().setBackground(tbPayment.getBackground());
		tbPayment.getTableHeader().setResizingAllowed(false);
		tbPayment.getTableHeader().setReorderingAllowed(false);
		tbPayment.setColumnSelectionAllowed(true);
		tbPayment.setRowSelectionAllowed(true);
		tbPayment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPayment.setRowHeight(30);

		lblDeditMemoNumber = new JLabel("Debit Memo No. : ");
		lblDeditMemoNumber.setFont(fntPlainText);
		lblDeditMemoNumber.setBounds(30, 180, 195, 30);
		add(lblDeditMemoNumber);

		lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 448, 143, 30);
		add(lblReceivedBy);

		lblReceivedDate = new JLabel("Date:");
		lblReceivedDate.setFont(fntPlainText);
		lblReceivedDate.setBounds(345, 448, 70, 25);
		add(lblReceivedDate);

		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(169, 448, 170, 30);
		add(tfReceivedBy);
		
		ftfReceivedDate = new JFormattedTextField(dateFormat);
		ftfReceivedDate.setFont(fntPlainText);
		ftfReceivedDate.setBounds(405, 448, 120, 30);
		add(ftfReceivedDate);

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
		spNotes.setBounds(30, 514, 490, 67);
		add(spNotes);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                    if(checkCurrentBalance())
                                    {
                                        addAllPayment();
                                        //deductCurrentBalance();
                                        controller.changePanelToPayablesList();
                                    }
                        }
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changePanelToMainMenu();
			}
		});
	}
        public void setMainController(PaymentCollectiblesController temp) {
		mainController = temp;
	}
        public void setCustomer()
        {
            ArrayList<String> customer= new ArrayList<String>();
            try {
                customer=mainController.getCustomer();
            } catch (SQLException ex) {
                Logger.getLogger(AddPaymentCollectiblesGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            cmbCustomer.addItem("");
            for(int i=0;i<customer.size();i++)
            {
                cmbCustomer.addItem(customer.get(i));
            }
        }
        
        public float getSum()
        {
            float sum=0;
             for (int i= 0; i < tbPayment.getRowCount(); i++)
             {
                 if(tbPayment.getValueAt(i, 5)!="0.00")
                 {
                    sum=sum+Float.parseFloat((String) tbPayment.getValueAt(i, 5));
                 }
             }
            return sum;
        }
        
        public void addAllPayment()
        {       
            for (int i= 0; i < tbPayment.getRowCount(); i++)
             {   
                if(tbPayment.getValueAt(i, 5)!="0.00")
                {
                    Collection c;       
                    c = new Collection((String)tbPayment.getValueAt(i, 1),Float.parseFloat((String)tbPayment.getValueAt(i, 5)),ftfReceivedDate.getText(),tfReceivedBy.getText(),(String)cmbPaymentType.getSelectedItem(),tfDebitMemoNo.getText(),ftfDate.getText(),taNotes.getText());
                     mainController.addPayment(c);
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
                cmbCustomer.removeAllItems();
                setCustomer();
	}
        
        public boolean checkCurrentBalance()
        {
            for (int i= 0; i < tbPayment.getRowCount(); i++)
            {
                if(tbPayment.getValueAt(i, 5)=="0.00")
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
        
        public void setTableModel(TableModel tbm) { // Setting the Headers
		tbPayment.setModel(tbm);
		JTableHeader th = tbPayment.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 6; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
                tbm.addTableModelListener(new TableModelListener() {
                public void tableChanged(TableModelEvent e) {
                    if(e.getColumn()==5)
                        ftfAmount.setText(Float.toString(getSum()));
                 }
                });
		th.repaint();
	}
        
	public static void main(String args[]) {
		GUIController temp = new GUIController();
		temp.changePanelToAddPaymentCollectibles();
	}
}

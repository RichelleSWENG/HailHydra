package Collectibles;

import AcknowledgementReceipt.AcknowledgementReceipt;
import HailHydra.GUIController;
import Payables.Payment;
import Sales.SalesInvoice;
import TableRenderer.TableRenderer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class ViewPaymentCollectiblesGUI extends JPanel
{

	private JLabel lblHeader, lblSalesAckNumber, lblReceivedBy,
			lblReceivedDate, lblCustomer, lblNotes, lblCurrBal;
	private JFormattedTextField ftfCurrBal, ftfOriginalAmount;
	private String strHeader[] =
	{ "<html><center>Payment<br>Id</center></html>","Date", "<html><center>Applied<br>Amount</center></html>",
			"<html><center>Payment<br>Type</center></html>",
			"<html><center>Debit<br>Memo No.</center></html>" };
	private JComboBox cmbCustomer;
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
	private JLabel lblOrigAmount;
        private PaymentCollectiblesController mainController;
        private String type;
        private String id;

	public ViewPaymentCollectiblesGUI(GUIController temp)
	{

		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		lblHeader = new JLabel("View Payment - Collectibles");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 100, 111, 30);
		add(lblCustomer);

		lblCurrBal = new JLabel("Current Balance:");
		lblCurrBal.setFont(fntPlainText);
		lblCurrBal.setBounds(544, 140, 170, 30);
		add(lblCurrBal);

		cmbCustomer = new JComboBox();
		cmbCustomer.setFont(fntPlainText);
		cmbCustomer.setBounds(167, 100, 341, 30);
		add(cmbCustomer);

		ftfCurrBal = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfCurrBal.setFont(fntPlainText);
		ftfCurrBal.setValue(new Float(00.0F));
		ftfCurrBal.setBounds(713, 140, 251, 30);
		add(ftfCurrBal);

		tfDebitMemoNo = new JTextField();
		tfDebitMemoNo.setFont(fntPlainText);
		tfDebitMemoNo.setBounds(167, 140, 341, 30);
		tfDebitMemoNo.setEditable(false);
		add(tfDebitMemoNo);
		tfDebitMemoNo.setColumns(10);

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

		tbPayment = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}
		};
		tbPayment.getTableHeader().setFont(fntHeaderTableText);
		tbPayment.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbPayment.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbPayment.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbPayment.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
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

		lblSalesAckNumber = new JLabel("S.I./ A.R No.:");
		lblSalesAckNumber.setFont(fntPlainText);
		lblSalesAckNumber.setBounds(30, 140, 195, 30);
		add(lblSalesAckNumber);

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

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);

		lblOrigAmount = new JLabel("Original Amount:");
		lblOrigAmount.setFont(new Font("Arial", Font.PLAIN, 21));
		lblOrigAmount.setBounds(544, 100, 184, 30);
		add(lblOrigAmount);

		ftfOriginalAmount = new JFormattedTextField(new DecimalFormat(
				"#,##0.00"));
		ftfOriginalAmount.setFont(fntPlainText);
		ftfOriginalAmount.setValue(new Float(00.0F));
		ftfOriginalAmount.setBounds(713, 100, 251, 30);
		add(ftfOriginalAmount);

		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToCollectibles();
			}
		});
	}

        public void setMainController(PaymentCollectiblesController temp) {
		mainController = temp;
	}
        
        public void ViewAll()
        {
            TableModel AllModel = mainController.getAllModel(id,type);
            tbPayment.setModel(AllModel);
            
            tfDebitMemoNo.setText(id);
            
            if(type.equalsIgnoreCase("Sales Invoice"))
            {
                SalesInvoice si=mainController.getSIDetails(id);
                if(si!=null)
                {
                    ftfOriginalAmount.setText(Float.toString(si.getOriginal_amount()));
                    ftfCurrBal.setText(Float.toString(si.getCurrent_balance()));
                    cmbCustomer.addItem(si.getDelivered_by());
                }
            }
            else
            {
                AcknowledgementReceipt ar=mainController.getARDetails(id);
                if(ar!=null)
                {
                    ftfOriginalAmount.setText(Float.toString(ar.getOriginal_amount()));
                    ftfCurrBal.setText(Float.toString(ar.getCurrent_balance()));
                    cmbCustomer.addItem(ar.getDelivered_by());
                }
            }   
            
            JTableHeader th = tbPayment.getTableHeader();      // Setting the Headers
            TableColumnModel tcm = th.getColumnModel();
            for (int i = 0; i < strHeader.length; i++)
            {
                TableColumn tc = tcm.getColumn(i);
                tc.setHeaderValue(strHeader[i]);
            }
            th.repaint();
            tbPayment.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    Collection p=mainController.getOtherDetails(Integer.toString((int) tbPayment.getValueAt(tbPayment.getSelectedRow(), 0)));
                    if(p!=null)
                    {
                        taNotes.setText(p.getNotes());
                        ftfReceivedDate.setText(p.getRDate());
                        tfReceivedBy.setText(p.getReceived_by());
                    }
                }  
                });
        }
        
        public void setId(String id,String type)
        {
            this.id=id;
            this.type=type;
        }
        
	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		//temp.changePanelToViewPaymentCollectibles();
	}
}
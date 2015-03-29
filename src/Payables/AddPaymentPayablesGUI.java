package Payables;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AddPaymentPayablesGUI extends JPanel {

	private JLabel lblHeader, lblDisplay, lblSupplier, lblPaymentType, lblDate,
			lblAmount, lblCreditMemoNumber, lblReceivedBy, lblApprovedBy, lblReturnedBy;
	private JFormattedTextField ftfDate, ftfAmount;
	private String strHeader[] = { "Date",
			"<html><center>Purchase<br>Transaction<br>Number</center></html>",
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
	private JCheckBox chckbxClosedPayables;
	private JButton btnSubmit, btnCancel;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private DateFormat dateFormat;
	private GUIController controller;
	private PaymentController mainController;
	private JTextField tfCreditMemoNo, tfReceivedBy, tfApprovedBy, tfReturnedBy;
	private JLabel lblNotes;
	private JTextArea taNotes;

	public AddPaymentPayablesGUI(GUIController temp) {
		controller = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		lblHeader = new JLabel("Add Payment - Payables");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

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
		cmbSupplier.setBounds(215, 100, 300, 30);
		add(cmbSupplier);

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
		tfCreditMemoNo.setBounds(215, 180, 300, 30);
		tfCreditMemoNo.setEditable(false);
		add(tfCreditMemoNo);
		tfCreditMemoNo.setColumns(10);

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
		cmbPaymentType.setBounds(215, 140, 300, 30);
		add(cmbPaymentType);
		
		for (int i = 0; i < strPayment.length; i++)
			cmbPaymentType.addItem(strPayment[i]);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changePanelToPayablesList();
			}
		});

		lblReturnedBy = new JLabel("Returned By:");
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

		tfReturnedBy = new JTextField();
		tfReturnedBy.setFont(fntPlainText);
		tfReturnedBy.setBounds(169, 388, 346, 30);
		add(tfReturnedBy);

		tfApprovedBy = new JTextField();
		tfApprovedBy.setFont(fntPlainText);
		tfApprovedBy.setBounds(169, 418, 346, 30);
		add(tfApprovedBy);

		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(169, 448, 346, 30);
		add(tfReceivedBy);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);

		lblCreditMemoNumber = new JLabel("Credit Memo No. : ");
		lblCreditMemoNumber.setFont(fntPlainText);
		lblCreditMemoNumber.setBounds(30, 180, 195, 30);
		add(lblCreditMemoNumber);

		lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(30, 480, 115, 30);
		add(lblNotes);

		taNotes = new JTextArea();
		taNotes.setBounds(30, 520, 485, 74);
		add(taNotes);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changePanelToPayablesList();
			}
		});
	}

	public void setMainController(PaymentController temp) {
		mainController = temp;
	}

	public void ViewAll() {
		TableModel AllModel = mainController.getAllModel();
		tbPayment.setModel(AllModel);

		JTableHeader th = tbPayment.getTableHeader(); // Setting the Headers
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < 6; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		th.repaint();
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
		temp.changePanelToAddPaymentPayables();
	}
}

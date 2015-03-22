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

public class AddPaymentPayablesGUI extends JPanel
{

	private JLabel  lblHeader, lblDisplay, lblSupplier,
			lblPaymentType, lblDate, lblAmount;
        private JTextField tfSupplier;
        private JFormattedTextField ftfDate, ftfAmount;
        private String strHeader[] = { "Date", 
                "<html><center>Purchase<br>Transaction<br>Number</center></html>",
		"Status", "<html><center>Original<br>Amount</center></html>", 
                "<html><center>Current<br>Balance</center></html>", 
                "<html><center>Amount<br>Applied</center></html>" }, strPayment[] = 
                { "Cash", "Bank to Bank", "Check", "Credit Memo" };
	private JComboBox cmbPaymentType;
        private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbPayment;
	private JScrollPane spPaymentTable;
	private JCheckBox chckbxClosedPayables;
        private JButton btnSubmit, btnCancel, btnDetails;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private DateFormat dateFormat;
        private GUIController controller;
	
	public AddPaymentPayablesGUI(GUIController temp)
	{
		controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
                lblHeader = new JLabel("Add Payment - Payables");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblDisplay = new JLabel("Display:");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 80, 95, 30);
		add(lblDisplay);

		lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(30, 120, 111, 30);
		add(lblSupplier);
                
                lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setFont(fntPlainText);
		lblPaymentType.setBounds(30, 160, 157, 30);
		add(lblPaymentType);

		lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(594, 120, 111, 30);
		add(lblDate);

		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(fntPlainText);
		lblAmount.setBounds(594, 160, 111, 30);
		add(lblAmount);

		tfSupplier = new JTextField();
		tfSupplier.setFont(fntPlainText);
		tfSupplier.setBounds(125, 120, 390, 30);
		add(tfSupplier);

                ftfDate= new JFormattedTextField(dateFormat);
                ftfDate.setValue(new java.util.Date());
                ftfDate.setFont(fntPlainText);
                ftfDate.setBounds(655, 120, 310, 30);
                add(ftfDate);
                
                ftfAmount = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfAmount.setFont(fntPlainText);
                ftfAmount.setValue(new Float(00.0F));
                ftfAmount.setBounds(680, 160, 285, 30);
                add(ftfAmount);
                
		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if(mColIndex == 5)
					return true;
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
			component = tbCellRendererColumn.getTableCellRendererComponent(tbPayment,
					tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		spPaymentTable = new JScrollPane(tbPayment);
		spPaymentTable.setBounds(30, 210, 935, 320);
		add(spPaymentTable);

                tbPayment.setFont(fntPlainText);
		tbPayment.getParent().setBackground(tbPayment.getBackground());
		tbPayment.getTableHeader().setResizingAllowed(false);
		tbPayment.getTableHeader().setReorderingAllowed(false);
		tbPayment.setColumnSelectionAllowed(true);
		tbPayment.setRowSelectionAllowed(true);
		tbPayment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPayment.setRowHeight(30);

		cmbPaymentType = new JComboBox();
		cmbPaymentType.setFont(fntPlainText);
		cmbPaymentType.setBounds(176, 160, 222, 30);
		add(cmbPaymentType);
		for (int i = 0; i < strPayment.length; i++)
			cmbPaymentType.addItem(strPayment[i]);

		chckbxClosedPayables = new JCheckBox("Closed Payables");
		chckbxClosedPayables.setFont(fntPlainText);
		chckbxClosedPayables.setBounds(124, 80, 261, 30);
		add(chckbxClosedPayables);
                
                btnDetails = new JButton("Details");
		btnDetails.setFont(fntPlainText);
		btnDetails.setBounds(408, 155, 124, 40);
		add(btnDetails);
                
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToPayablesList();
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
                                controller.changePanelToPayablesList();
                        }
                    });
	}

        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToAddPaymentPayables();
        }
}

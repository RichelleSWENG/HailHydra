package Collectibles;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;

public class AddPaymentCollectiblesGUI extends JPanel
{
        private JLabel  lblHeader, lblDisplay, lblCustomer, lblPaymentType, 
                        lblDate, lblAmount;
	private JTextField tfCustomer;
        private JFormattedTextField ftfDate, ftfAmount;
        private String strHeader[] ={ "Date", 
                "<html><center>Sales Invoice<br>Number</center></html>",
		"<html><center>Acknowledgement<br>Receipt Number</center></html>", 
                "Status", "<html><center>Original<br>Amount</center></html>", 
                "<html><center>Current<br>Balance</center></html>", 
                "<html><center>Amount<br>Applied</center></html>" }, 
                strPaymentType[] = {"Cash", "Bank to Bank", "Check", "Debit Memo"};
        private JComboBox cmbPaymentType;
	private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbPayment;
	private JScrollPane spPaymentTable;
	private JCheckBox chckbxClosedCollectibes;
        private JButton btnSubmit, btnCancel, btnDetails;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private DateFormat dateFormat;
        private GUIController GUIController;
	
	public AddPaymentCollectiblesGUI(GUIController temp)
	{
		GUIController=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		lblHeader = new JLabel("Add Payment - Collectibles");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblDisplay = new JLabel("Display: ");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 80, 95, 30);
		add(lblDisplay);
		
		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 120, 111, 30);
		add(lblCustomer);
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(594, 120, 111, 30);
		add(lblDate);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(fntPlainText);
		lblAmount.setBounds(594, 160, 111, 30);
		add(lblAmount);
		
		lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setFont(fntPlainText);
		lblPaymentType.setBounds(30, 160, 157, 30);
		add(lblPaymentType);
                
                tfCustomer = new JTextField();
		tfCustomer.setFont(fntPlainText);
		tfCustomer.setBounds(185, 120, 345, 30);
		add(tfCustomer);
                
                ftfDate= new JFormattedTextField(dateFormat);
                ftfDate.setValue(new java.util.Date());
                ftfDate.setFont(fntPlainText);
                ftfDate.setBounds(680, 120, 285, 30);
                add(ftfDate);
                
                ftfAmount = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfAmount.setFont(fntPlainText);
                ftfAmount.setValue(new Float(00.0F));
                ftfAmount.setBounds(680, 160, 285, 30);
                add(ftfAmount);
                
                cmbPaymentType = new JComboBox();
		cmbPaymentType.setFont(fntPlainText);
		cmbPaymentType.setBounds(185, 160, 210, 30);
		add(cmbPaymentType);
		for (int i = 0; i < strPaymentType.length; i++)
                {
                        cmbPaymentType.addItem(strPaymentType[i]);
                }

		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if(mColIndex == 6)
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
		tbCellRenderer = tbPayment.getTableHeader()
				.getDefaultRenderer();
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
		tbPayment.setFont(fntPlainText);
		spPaymentTable = new JScrollPane(tbPayment);
		spPaymentTable.setBounds(30, 210, 935, 320);
		add(spPaymentTable);

		tbPayment.getParent().setBackground(tbPayment.getBackground());
		tbPayment.getTableHeader().setResizingAllowed(false);
		tbPayment.getTableHeader().setReorderingAllowed(false);
		tbPayment.setColumnSelectionAllowed(true);
		tbPayment.setRowSelectionAllowed(true);
		tbPayment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPayment.setRowHeight(30);
		
		chckbxClosedCollectibes = new JCheckBox("Closed Collectibles");
		chckbxClosedCollectibes.setFont(fntPlainText);
		chckbxClosedCollectibes.setBounds(185, 80, 261, 30);
		add(chckbxClosedCollectibes);
                
                btnDetails = new JButton("Details");
		btnDetails.setFont(fntPlainText);
		btnDetails.setBounds(408, 155, 124, 40);
		add(btnDetails);
                
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
                btnCancel.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                GUIController.changePanelToMainMenu();
                        }
                    });
	}
        
        public static void main(String args[]){
                GUIController temp=new GUIController();
                temp.changePanelToAddPaymentCollectibles();
        }
}

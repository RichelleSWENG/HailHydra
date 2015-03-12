package ReturnSlip;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class AddReturnSlipGUI extends JPanel {
    
	private JLabel lblHeader,lblSalesInvoice, lblSupplier, lblPurchaseOrder, lblReturnedBy, 
                lblApprovedBy, lblReceivedBy,lblAddress, lblReturnSlip, lblDate,
                lblDate1, lblDate2, lblDate3, lblTotal, lblNotes;
        private JTextField tfSupplier;
	private JTextArea taAddress;
	private JTextField tfReturnSlipNum, tfDate, tfSalesInvoice, tfPurchaseOrder, 
                tfReturnedBy, tfApprovedBy, tfReceivedBy, tfReturnedByDate, 
                tfApprovedByDate, tfReceivedByDate, tfTotal;
	private JTextArea taNotes;
        private JComboBox cmbSupplier;
	private JButton btnAddItem, btnSubmit, btnCancel;
	protected DefaultTableModel tbModel;
        protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        protected TableColumnModel tbColumnRenderer;
        protected TableColumn tbColumn;
        protected Component component;
	private JTable tbReturnSlip;
	private JScrollPane spTable;
	private String headers[] = { "Quantity", "Part Number", "Description",
		"<html><center>Unit<br>Price</center></html>", "Total" };
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private GUIController GUIController;

	
	public AddReturnSlipGUI(GUIController temp) {
            
                GUIController=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
		
                lblHeader = new JLabel("Add Return Slip");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(30, 80, 90, 30);
		add(lblSupplier);
                
                lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(30, 120, 92, 30);
		add(lblAddress);
                
                lblReturnSlip = new JLabel("Return Slip #:");
		lblReturnSlip.setFont(fntPlainText);
		lblReturnSlip.setBounds(569, 80, 132, 30);
		add(lblReturnSlip);
                
                lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(569, 110, 51, 30);
		add(lblDate);
                
                lblSalesInvoice = new JLabel("S.I. Number:");
		lblSalesInvoice.setFont(fntPlainText);
		lblSalesInvoice.setBounds(569, 140, 163, 30);
		add(lblSalesInvoice);
		
                lblPurchaseOrder = new JLabel("P.O. Number:");
		lblPurchaseOrder.setFont(fntPlainText);
		lblPurchaseOrder.setBounds(569, 170, 174, 30);
		add(lblPurchaseOrder);
                
		lblReturnedBy = new JLabel("Returned By:");
		lblReturnedBy.setFont(fntPlainText);
		lblReturnedBy.setBounds(30, 351, 143, 30);
		add(lblReturnedBy);
                
                lblApprovedBy = new JLabel("Approved By:");
		lblApprovedBy.setFont(fntPlainText);
		lblApprovedBy.setBounds(30, 383, 143, 30);
		add(lblApprovedBy);
                
                lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 415, 143, 30);
		add(lblReceivedBy);
                
                lblDate1 = new JLabel("Date:");
		lblDate1.setFont(fntPlainText);
		lblDate1.setBounds(353, 351, 70, 25);
		add(lblDate1);
                
                lblDate2 = new JLabel("Date:");
		lblDate2.setFont(fntPlainText);
		lblDate2.setBounds(353, 383, 70, 25);
		add(lblDate2);
		
		lblDate3 = new JLabel("Date:");
		lblDate3.setFont(fntPlainText);
		lblDate3.setBounds(353, 415, 70, 25);
		add(lblDate3);
                
                lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(733, 362, 63, 30);
		add(lblTotal);
                
                lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(30, 454, 63, 30);
		add(lblNotes);
                
                cmbSupplier = new JComboBox();
                AutoCompleteDecorator.decorate(cmbSupplier);
                cmbSupplier.setEditable(true);
		cmbSupplier.setFont(new Font("Arial", Font.PLAIN, 21));
		cmbSupplier.setBounds(121, 80, 392, 30);
		add(cmbSupplier);
		
		taAddress = new JTextArea();
		taAddress.setFont(fntPlainText);
		taAddress.setBorder(new LineBorder(Color.LIGHT_GRAY));
                taAddress.setEditable(false);
		taAddress.setBounds(121, 120, 390, 68);
		add(taAddress);
		
		tfReturnSlipNum = new JTextField();
		tfReturnSlipNum.setFont(fntPlainText);
		tfReturnSlipNum.setBounds(710, 80, 248, 30);
		add(tfReturnSlipNum);
		
		tfDate = new JTextField();
		tfDate.setFont(fntPlainText);
		tfDate.setBounds(630, 110, 328, 30);
		add(tfDate);
		
		tfSalesInvoice = new JTextField();
		tfSalesInvoice.setFont(fntPlainText);
		tfSalesInvoice.setBounds(733, 140, 225, 30);
		add(tfSalesInvoice);
		
		tfPurchaseOrder = new JTextField();
		tfPurchaseOrder.setFont(fntPlainText);
		tfPurchaseOrder.setBounds(753, 170, 205, 30);
		add(tfPurchaseOrder);
                
		tfReturnedBy = new JTextField();
		tfReturnedBy.setFont(fntPlainText);
		tfReturnedBy.setBounds(164, 351, 163, 30);
		add(tfReturnedBy);
		
		tfApprovedBy = new JTextField();
		tfApprovedBy.setFont(fntPlainText);
		tfApprovedBy.setBounds(164, 383, 163, 30);
		add(tfApprovedBy);
		
		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(164, 415, 163, 30);
		add(tfReceivedBy);
		
		tfReturnedByDate = new JTextField();
		tfReturnedByDate.setFont(fntPlainText);
		tfReturnedByDate.setBounds(431, 351, 111, 30);
                add(tfReturnedByDate);
		
		tfApprovedByDate = new JTextField();
		tfApprovedByDate.setFont(fntPlainText);
		tfApprovedByDate.setBounds(431, 383, 113, 30);
		add(tfApprovedByDate);
		
		tfReceivedByDate = new JTextField();
		tfReceivedByDate.setFont(fntPlainText);
		tfReceivedByDate.setBounds(431, 415, 113, 30);
		add(tfReceivedByDate);
		
		tfTotal = new JTextField();
		tfTotal.setFont(fntPlainText);
		tfTotal.setBounds(789, 361, 163, 30);
		add(tfTotal);
                
                taNotes = new JTextArea();
		taNotes.setFont(fntPlainText);
		taNotes.setBounds(92, 454, 452, 50);
		add(taNotes);
                
		tbModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				if(mColIndex !=2)
					return true;
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < 5; i++) {
			tbModel.addColumn(headers[i]);
		}
		

		tbReturnSlip = new JTable(tbModel) {
			public TableCellRenderer getCellRenderer(int row, int column) {
				return new TableRenderer();
			}
		};
		spTable = new JScrollPane(tbReturnSlip);
		spTable.setBounds(30, 192, 935, 158);
		add(spTable);

                tbReturnSlip.getTableHeader().setFont(fntHeaderTableText);
		tbReturnSlip.getParent().setBackground(tbReturnSlip.getBackground());
		tbReturnSlip.getTableHeader().setResizingAllowed(false);
		tbReturnSlip.getTableHeader().setReorderingAllowed(false);
		tbReturnSlip.setColumnSelectionAllowed(true);
		tbReturnSlip.setRowSelectionAllowed(true);
		tbReturnSlip.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReturnSlip.setRowHeight(30);
                
                tbReturnSlip.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbReturnSlip.getTableHeader().setResizingAllowed(false);
		tbCellRenderer= tbReturnSlip.getTableHeader().getDefaultRenderer();
		tbColumnRenderer= tbReturnSlip.getColumnModel();
                for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbReturnSlip,
					tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 132, 40);
		add(btnAddItem);
		
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
                                GUIController.changePanelToReturnSlip();
                        }
                    });
		
		
	}
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToAddReturnSlip();
        }

}

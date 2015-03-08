package DebitMemo;

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

import HailHydra.GUIModel;
import TableRenderer.TableRenderer;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDebitMemoGUI extends JPanel
{
        private JLabel lblHeader, lblCustomer, lblAddress, lblDebitMemo,
                        lblDate, lblSalesInvoice, lblApprovedBy, lblReceivedBy, 
                        lblApprovedDate, lblReceivedDate, lblTotal, lblNotes;
	private JTextField tfCustomer, tfDebitMemo, tfDate, tfSalesInvoice,
			tfApprovedBy, tfReceivedBy, tfApprovedDate, tfReceivedDate,
			tfTotal;
	private JButton btnAddItem, btnSubmit, btnCancel;
	private JTextArea taNotes, taAddress;
	private DefaultTableModel table;
	private JTable tbDebitMemo;
	private JScrollPane spTable;
	private String headers[] = { "Quantity", "Part Number", 
                "Description", "<html><center>Unit<br>Price</html>", "Total" };
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private GUIModel controller;
	
	public AddDebitMemoGUI(GUIModel temp)
	{
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
		
                lblHeader = new JLabel("Add Debit Memo");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(20, 73, 98, 30);
		add(lblCustomer);
                
		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(20, 109, 98, 30);
		add(lblAddress);
                
                lblDebitMemo = new JLabel("Debit Memo #:");
		lblDebitMemo.setFont(fntPlainText);
		lblDebitMemo.setBounds(581, 62, 216, 30);
		add(lblDebitMemo);
                
                lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(581, 98, 60, 30);
		add(lblDate);
                
		lblSalesInvoice = new JLabel("Sales Invoice #:");
		lblSalesInvoice.setFont(fntPlainText);
		lblSalesInvoice.setBounds(581, 133, 171, 30);
		add(lblSalesInvoice);
                
                lblApprovedBy = new JLabel("Approved By:");
		lblApprovedBy.setFont(fntPlainText);
		lblApprovedBy.setBounds(30, 378, 145, 30);
		add(lblApprovedBy);
                
                lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 419, 145, 30);
		add(lblReceivedBy);
                
                lblApprovedDate = new JLabel("Date:");
		lblApprovedDate.setFont(fntPlainText);
		lblApprovedDate.setBounds(333, 378, 60, 30);
		add(lblApprovedDate);
                
		lblReceivedDate = new JLabel("Date:");
		lblReceivedDate.setFont(fntPlainText);
		lblReceivedDate.setBounds(333, 419, 60, 30);
		add(lblReceivedDate);
                
                lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(581, 378, 70, 30);
		add(lblTotal);
                
                lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(30, 460, 77, 30);
		add(lblNotes);
                
                tfCustomer = new JTextField();
		tfCustomer.setFont(fntPlainText);
		tfCustomer.setBounds(128, 73, 400, 30);
		add(tfCustomer);
                
                taAddress = new JTextArea();
		taAddress.setFont(fntPlainText);
                taAddress.setEditable(false);
		taAddress.setBounds(117, 109, 411, 58);
		add(taAddress);

		tfDebitMemo = new JTextField();
		tfDebitMemo.setFont(fntPlainText);
		tfDebitMemo.setBounds(737, 62, 212, 30);
		add(tfDebitMemo);

		tfDate = new JTextField();
		tfDate.setFont(fntPlainText);
		tfDate.setBounds(651, 98, 298, 30);
		add(tfDate);

		tfSalesInvoice = new JTextField();
		tfSalesInvoice.setFont(fntPlainText);
		tfSalesInvoice.setBounds(746, 139, 203, 30);
		add(tfSalesInvoice);

		tfApprovedBy = new JTextField();
		tfApprovedBy.setFont(fntPlainText);
		tfApprovedBy.setBounds(158, 378, 165, 30);
		add(tfApprovedBy);

		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(158, 419, 165, 30);
		add(tfReceivedBy);

		tfApprovedDate = new JTextField();
		tfApprovedDate.setFont(fntPlainText);
		tfApprovedDate.setBounds(403, 378, 125, 30);
		add(tfApprovedDate);

		tfReceivedDate = new JTextField();
		tfReceivedDate.setFont(fntPlainText);
		tfReceivedDate.setBounds(403, 419, 125, 30);
		add(tfReceivedDate);

		tfTotal = new JTextField();
		tfTotal.setFont(fntPlainText);
		tfTotal.setBounds(651, 378, 298, 30);
		add(tfTotal);

		taNotes = new JTextArea();
		taNotes.setFont(fntPlainText);
		taNotes.setBounds(90, 460, 438, 50);
		add(taNotes);
                
		table = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if(mColIndex != 2 && mColIndex!=4 )
					return true;
				return false;
			}
		};

		table.setRowCount(15);

		for (int i = 0; i < 5; i++)
		{
			table.addColumn(headers[i]);
		}

		tbDebitMemo = new JTable(table)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}
		};
		spTable = new JScrollPane(tbDebitMemo);
		spTable.setBounds(30, 180, 935, 187);
		add(spTable);

                tbDebitMemo.getTableHeader().setFont(fntHeaderTableText);
		tbDebitMemo.getParent().setBackground(tbDebitMemo.getBackground());
		tbDebitMemo.getTableHeader().setResizingAllowed(false);
		tbDebitMemo.getTableHeader().setReorderingAllowed(false);
		tbDebitMemo.setColumnSelectionAllowed(true);
		tbDebitMemo.setRowSelectionAllowed(true);
		tbDebitMemo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDebitMemo.setRowHeight(30);

		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 515, 216, 40);
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
                                controller.changePanelToDebitMemo();
                        }
                    });

		
	}
        
        public static void main(String args[]){
           GUIModel temp=new GUIModel();
           temp.changePanelToAddDebitMemo();
        }
}

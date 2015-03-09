package CreditMemo;

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

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCreditMemoGUI extends JPanel
{
	private JLabel  lblHeader,  lblTotal, lblReplySlipNo,
			lblDate, lblCreditMemo, lblAddress, lblSupplier;
	private JTextField tfSupplier, tfCreditMemo, tfDate, tfReplySlipNum,
			tfTotal;
	private JTextArea taAddress;
        private String strHeader[] = { "Quantity", "Part Number", "Description", 
                "Unit Price", "Total" };
	private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbCreditMemo;
	private JScrollPane spTable, spAddress;
        private JButton btnSubmit, btnCancel;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private GUIController controller;

	public AddCreditMemoGUI(GUIController temp)
	{
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
		
                lblHeader = new JLabel("Add Credit Memo");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(20, 80, 98, 30);
		add(lblSupplier);

		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(20, 120, 98, 30);
		add(lblAddress);

                lblCreditMemo = new JLabel("Credit Memo #:");
		lblCreditMemo.setFont(fntPlainText);
		lblCreditMemo.setBounds(581, 160, 216, 30);
		add(lblCreditMemo);
                
                lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(581, 80, 60, 30);
		add(lblDate);
                
                lblReplySlipNo = new JLabel("R.S. Number:");
		lblReplySlipNo.setFont(fntPlainText);
		lblReplySlipNo.setBounds(581, 120, 171, 30);
		add(lblReplySlipNo);
                
                lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(727, 371, 70, 30);
		add(lblTotal);
                
		tfSupplier = new JTextField();
		tfSupplier.setFont(fntPlainText);
		tfSupplier.setBounds(128, 80, 400, 30);
		add(tfSupplier);

		taAddress = new JTextArea();
		taAddress.setFont(fntPlainText);
                taAddress.setWrapStyleWord(true);
                taAddress.setLineWrap(true);
		taAddress.setBounds(117, 120, 411, 58);
		add(taAddress);

		tfCreditMemo = new JTextField();
		tfCreditMemo.setFont(fntPlainText);
		tfCreditMemo.setBounds(737, 160, 212, 30);
		add(tfCreditMemo);	

		tfDate = new JTextField();
		tfDate.setFont(fntPlainText);
		tfDate.setBounds(651, 80, 298, 30);
		add(tfDate);

		tfReplySlipNum = new JTextField();
		tfReplySlipNum.setFont(fntPlainText);
		tfReplySlipNum.setBounds(712, 120, 237, 30);
		add(tfReplySlipNum);

		tfTotal = new JTextField();
		tfTotal.setFont(fntPlainText);
		tfTotal.setBounds(804, 371, 145, 30);
		add(tfTotal);
                
                spAddress=new JScrollPane(taAddress);
		spAddress.setBounds(200, 160, 212, 30);
		add(spAddress);

		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};

		tbModel.setRowCount(1);

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}

		tbCreditMemo = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}
		};
		tbCreditMemo.getTableHeader().setFont(fntHeaderTableText);
		tbCreditMemo.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbCreditMemo.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbCreditMemo.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCreditMemo.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbCreditMemo,
					tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbCreditMemo.setFont(fntPlainText);
		
		spTable = new JScrollPane(tbCreditMemo);
		spTable.setBounds(20, 274, 929, 86);
		add(spTable);
                
		tbCreditMemo.getParent().setBackground(tbCreditMemo.getBackground());
		tbCreditMemo.getTableHeader().setResizingAllowed(false);
		tbCreditMemo.getTableHeader().setReorderingAllowed(false);
		tbCreditMemo.setColumnSelectionAllowed(true);
		tbCreditMemo.setRowSelectionAllowed(true);
		tbCreditMemo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCreditMemo.setRowHeight(30);

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
                                controller.changePanelToCreditMemo();
                        }
                    });
	}
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToAddCreditMemo();
        }

}

package DebitMemo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;

public class DebitMemoGUI extends JPanel
{
        private JPanel pnlType;
        protected JLabel lblHeader, lblCustomer, lblAddress, lblDMNum,
                  lblDate, lblSINum, lblApprovedBy, lblReceivedBy, 
                  lblApprovedDate, lblReceivedDate, lblTotal, lblNotes;
	protected JTextField tfDBNum, tfSINum,
		  tfApprovedBy, tfReceivedBy;
        protected JFormattedTextField ftfDate,ftfApprovedDate, ftfReceivedDate, ftfTotal;
	protected JTextArea taNotes, taAddress;
	protected DefaultTableModel tbModel;
        protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        protected TableColumnModel tbColumnRenderer;
        protected TableColumn tbColumn;
        protected Component component;
	protected JTable tbDebitMemo;
	protected JScrollPane spTable, spAddress, spNotes;
	protected String strHeader[] = { "Quantity", "    Part Number    ", 
                  "        Description        ", 
                  "<html><center>   Unit   <br>   Price   </center></html>",
                  "  Total  "};
        protected JComboBox cmbCustomer, cmbRcptType, cmbRcptNumber;
        protected JCheckBox chckbxDefective, chckbxReplacement;
	protected Font fntPlainText, fntHeaderText, fntHeaderTableText;
        protected DateFormat dateFormat;
        private TitledBorder title;
        protected JButton btnAddItem, btnDeleteItem;
        
        public DebitMemoGUI()
        {
                setBounds(0, 0, 1000, 620);
		setLayout(null);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
                lblHeader = new JLabel("");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 80, 98, 30);
		add(lblCustomer);
                
		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(30, 120, 98, 30);
		add(lblAddress);
                
                lblDMNum = new JLabel("Debit Memo #:");
		lblDMNum.setFont(fntPlainText);
		lblDMNum.setBounds(530, 80, 216, 30);
		add(lblDMNum);
                
                lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(530, 120, 60, 30);
		add(lblDate);
                
		lblSINum = new JLabel("S.I. Number:");
		lblSINum.setFont(fntPlainText);
		lblSINum.setBounds(530, 160, 120, 30);
		//add(lblSINum);
                
                lblApprovedBy = new JLabel("Approved By:");
		lblApprovedBy.setFont(fntPlainText);
		lblApprovedBy.setBounds(30, 427, 145, 30);
		add(lblApprovedBy);
                
                lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 468, 145, 30);
		add(lblReceivedBy);
                
                lblApprovedDate = new JLabel("Date:");
		lblApprovedDate.setFont(fntPlainText);
		lblApprovedDate.setBounds(345, 427, 60, 30);
		add(lblApprovedDate);
                
		lblReceivedDate = new JLabel("Date:");
		lblReceivedDate.setFont(fntPlainText);
		lblReceivedDate.setBounds(345, 468, 60, 30);
		add(lblReceivedDate);
                
                lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(733, 378, 70, 30);
		add(lblTotal);
                
                lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(30, 505, 77, 30);
		add(lblNotes);

		tfDBNum = new JTextField();
		tfDBNum.setFont(fntPlainText);
		tfDBNum.setBounds(680, 80, 285, 30);
		add(tfDBNum);
                
                ftfDate= new JFormattedTextField(dateFormat);
                ftfDate.setValue(new java.util.Date());
                ftfDate.setFont(fntPlainText);
                ftfDate.setBounds(680, 120, 285, 30);
                add(ftfDate);

		tfSINum = new JTextField();
		tfSINum.setFont(fntPlainText);
		tfSINum.setBounds(680, 160, 285, 30);
		//add(tfSINum);

		tfApprovedBy = new JTextField();
		tfApprovedBy.setFont(fntPlainText);
		tfApprovedBy.setBounds(164, 427, 170, 30);
		add(tfApprovedBy);

		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(164, 468, 170, 30);
		add(tfReceivedBy);

                ftfApprovedDate= new JFormattedTextField(dateFormat);
                ftfApprovedDate.setFont(fntPlainText);
                ftfApprovedDate.setBounds(400, 427, 120, 30);
                add(ftfApprovedDate);
        
                ftfReceivedDate= new JFormattedTextField(dateFormat);
                ftfReceivedDate.setFont(fntPlainText);
                ftfReceivedDate.setBounds(400, 468, 120, 30);
                add(ftfReceivedDate);             
                
                ftfTotal = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfTotal.setFont(fntPlainText);
                ftfTotal.setHorizontalAlignment(JTextField.RIGHT);
                ftfTotal.setValue(new Float(00.0F));
                ftfTotal.setEditable(false);
                ftfTotal.setBounds(800, 375, 165, 30);
                add(ftfTotal);
                
                taAddress = new JTextArea();
		taAddress.setFont(fntPlainText);
                taAddress.setWrapStyleWord(true);
                taAddress.setLineWrap(true);
		taAddress.setBounds(130, 120, 370, 70);
		add(taAddress);
                
		taNotes = new JTextArea();
		taNotes.setFont(fntPlainText);
                taNotes.setWrapStyleWord(true);
                taNotes.setLineWrap(true);
		taNotes.setBounds(30, 495, 490, 40);
		add(taNotes);
                
                spAddress = new JScrollPane(taAddress);
                spAddress.setBounds(138, 120, 362, 70);
                add(spAddress);
                
                spNotes = new JScrollPane(taNotes);
                spNotes.setBounds(30, 536, 490, 40);
                add(spNotes);
                
                cmbCustomer = new JComboBox();
		AutoCompleteDecorator.decorate(cmbCustomer);
		cmbCustomer.setFont(fntPlainText);
		cmbCustomer.setBounds(138, 80, 362, 30);
		add(cmbCustomer);
                
                String[] comboBoxArray = {"A.R.#","S.I.#"};
                cmbRcptType = new JComboBox(comboBoxArray);
                cmbRcptType.setFont(fntPlainText);
                cmbRcptType.setBounds(530, 160, 120, 30);
                add(cmbRcptType);
                
                cmbRcptNumber = new JComboBox();
                AutoCompleteDecorator.decorate(cmbCustomer);
                cmbRcptNumber.setFont(fntPlainText);
                cmbRcptNumber.setBounds(680, 160, 285, 30);
                add(cmbRcptNumber);
                
		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if(mColIndex != 2 && mColIndex!=4 )
					return true;
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}

		tbDebitMemo = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}
		};
                
                tbDebitMemo.getTableHeader().setPreferredSize(new Dimension(100, 55));
                tbDebitMemo.getTableHeader().setResizingAllowed(false);
                tbCellRenderer= tbDebitMemo.getTableHeader().getDefaultRenderer();
                tbColumnRenderer= tbDebitMemo.getColumnModel();
                for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
                {
                    tbColumn = tbColumnRenderer.getColumn(j);
                    tbCellRendererColumn = tbColumn.getHeaderRenderer();
                    if (tbCellRendererColumn == null)
                        tbCellRendererColumn = tbCellRenderer;
                    component = tbCellRendererColumn.getTableCellRendererComponent(tbDebitMemo,
                    tbColumn.getHeaderValue(), false, false, 0, j);
                    tbColumn.setPreferredWidth(component.getPreferredSize().width);
                }
		spTable = new JScrollPane(tbDebitMemo);
		spTable.setBounds(30, 210, 935, 150);
		add(spTable);

                tbDebitMemo.getTableHeader().setFont(fntHeaderTableText);
		tbDebitMemo.getParent().setBackground(tbDebitMemo.getBackground());
		tbDebitMemo.getTableHeader().setResizingAllowed(false);
		tbDebitMemo.getTableHeader().setReorderingAllowed(false);
		tbDebitMemo.setColumnSelectionAllowed(true);
		tbDebitMemo.setRowSelectionAllowed(true);
		tbDebitMemo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDebitMemo.setRowHeight(30);
                
                pnlType= new JPanel();
                pnlType.setBounds(550, 405,415, 130);
                pnlType.setLayout(null);
                add(pnlType);
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Transaction Type");
                title.setTitleJustification(TitledBorder.LEFT);
                title.setTitleFont(fntPlainText);
                pnlType.setBorder(title);
                
                chckbxDefective = new JCheckBox("Defective");
		chckbxDefective.setFont(fntPlainText);
		chckbxDefective.setBounds(30, 40, 200, 30);
		pnlType.add(chckbxDefective);
                
                chckbxReplacement = new JCheckBox("Replacement");
		chckbxReplacement.setFont(fntPlainText);
		chckbxReplacement.setBounds(30, 70, 200, 30);
		pnlType.add(chckbxReplacement);
		
		btnAddItem = new JButton("Add Item");
        btnAddItem.setFont(fntPlainText);
        btnAddItem.setBounds(30, 378, 147, 40);
        add(btnAddItem);

        btnDeleteItem= new JButton("Delete Item");
        btnDeleteItem.setFont(fntPlainText);
        btnDeleteItem.setBounds(190, 378, 147, 40);
        add(btnDeleteItem);
            
        }
        
        public static void main(String args[]){
                GUIController temp=new GUIController();
                temp.changePanelToAddDebitMemo();
        }
}

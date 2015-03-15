package CreditMemo;

import HailHydra.GUIController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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

import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.SystemColor;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CreditMemoGUI extends JPanel
{
        private JPanel pnlType;
        protected JLabel  lblHeader,  lblTotal, lblReplySlipNo,
                  lblDate, lblCreditMemoNum, lblAddress, lblSupplier;
	protected JTextField tfCreditMemoNum, tfReplySlipNum;
        protected JFormattedTextField ftfTotal, ftfDate;
	protected JTextArea taAddress;
        protected String strHeader[] = { "Quantity", "        Part Number        ", 
                  "        Description        ", 
                  "  Unit Price  ", "  Total  " };
	protected DefaultTableModel tbModel;
        protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        protected TableColumnModel tbColumnRenderer;
        protected TableColumn tbColumn;
        protected Component component;
	protected JTable tbCreditMemo;
        protected JComboBox cmbSupplier;
        protected JCheckBox chckbxDefective, chckbxReplacement;
	protected JScrollPane spTable, spAddress;
        protected Font fntPlainText, fntHeaderText, fntHeaderTableText;
        protected DateFormat dateFormat;
        private TitledBorder title;
        
        public CreditMemoGUI()
        {
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
		
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                lblHeader = new JLabel("");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(fntPlainText);
		lblSupplier.setBounds(30, 80, 98, 30);
		add(lblSupplier);

		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(30, 120, 98, 30);
		add(lblAddress);

                lblCreditMemoNum = new JLabel("Credit Memo #:");
		lblCreditMemoNum.setFont(fntPlainText);
		lblCreditMemoNum.setBounds(530, 80, 216, 30);
		add(lblCreditMemoNum);
                
                lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(530, 120, 60, 30);
		add(lblDate);
                
                lblReplySlipNo = new JLabel("R.S. Number:");
		lblReplySlipNo.setFont(fntPlainText);
		lblReplySlipNo.setBounds(530, 160, 171, 30);
		add(lblReplySlipNo);
                
                lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(770, 380, 70, 30);
		add(lblTotal);

		tfCreditMemoNum = new JTextField();
		tfCreditMemoNum.setFont(fntPlainText);
		tfCreditMemoNum.setBounds(690, 80, 275, 30);
		add(tfCreditMemoNum);	
                
                ftfDate= new JFormattedTextField(dateFormat);
                ftfDate.setValue(new java.util.Date());
                ftfDate.setFont(fntPlainText);
                ftfDate.setEditable(false);
                ftfDate.setBounds(590, 120, 375, 30);
                add(ftfDate);

		tfReplySlipNum = new JTextField();
		tfReplySlipNum.setFont(fntPlainText);
                tfReplySlipNum.setEditable(false);
		tfReplySlipNum.setBounds(670, 160, 295, 30);
		add(tfReplySlipNum);
                
                ftfTotal = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfTotal.setFont(fntPlainText);
                ftfTotal.setHorizontalAlignment(JTextField.RIGHT);
                ftfTotal.setValue(new Float(00.0F));
                ftfTotal.setEditable(false);
                ftfTotal.setBounds(830, 380, 135, 30);
                add(ftfTotal);
                
                taAddress = new JTextArea();
		taAddress.setFont(fntPlainText);
                taAddress.setWrapStyleWord(true);
                taAddress.setLineWrap(true);
                taAddress.setEditable(false);
		taAddress.setBounds(125, 120, 375, 80);
		add(taAddress);
                
                spAddress=new JScrollPane(taAddress);
		spAddress.setBounds(125, 120, 375, 80);
		add(spAddress);

                cmbSupplier = new JComboBox();
		AutoCompleteDecorator.decorate(cmbSupplier);
		cmbSupplier.setFont(fntPlainText);
                cmbSupplier.setEditable(false);
		cmbSupplier.setBounds(125, 80, 375, 30);
		add(cmbSupplier);
                
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
		spTable.setBounds(30, 225, 935, 150);
		add(spTable);
                
		tbCreditMemo.getParent().setBackground(tbCreditMemo.getBackground());
		tbCreditMemo.getTableHeader().setResizingAllowed(false);
		tbCreditMemo.getTableHeader().setReorderingAllowed(false);
		tbCreditMemo.setColumnSelectionAllowed(true);
		tbCreditMemo.setRowSelectionAllowed(true);
		tbCreditMemo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCreditMemo.setRowHeight(30);
                
                pnlType= new JPanel();
                pnlType.setBounds(30, 405,415, 130);
                pnlType.setLayout(null);
                pnlType.setBackground(SystemColor.textHighlight);
                add(pnlType);
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Transaction Type");
                title.setTitleJustification(TitledBorder.LEFT);
                title.setTitleFont(fntPlainText);
                pnlType.setBorder(title);
                
                chckbxDefective = new JCheckBox("Defective");
		chckbxDefective.setFont(fntPlainText);
                chckbxDefective.setBackground(SystemColor.textHighlight);
                chckbxDefective.setEnabled(false);
		chckbxDefective.setBounds(30, 40, 200, 30);
		pnlType.add(chckbxDefective);
                
                chckbxReplacement = new JCheckBox("Replacement");
		chckbxReplacement.setFont(fntPlainText);
                chckbxReplacement.setBackground(SystemColor.textHighlight);
		chckbxReplacement.setBounds(30, 70, 200, 30);
		pnlType.add(chckbxReplacement);
        }
}

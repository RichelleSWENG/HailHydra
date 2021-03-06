package ReturnSlip;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

public class ReturnSlipGUI extends JPanel
{
	private JPanel pnlType;
	protected JLabel lblHeader, lblPTNum, lblSupplier, lblPONum, lblReturnedBy,
			lblApprovedBy, lblReceivedBy, lblAddress, lblRSNum, lblDate,
			lblReturnedDate, lblApprovedDate, lblReceivedDate, lblTotal,
			lblNotes;
	protected JTextField tfRSNum, tfSINum, tfPONum, tfReturnedBy, tfApprovedBy,
			tfReceivedBy;
	protected JFormattedTextField ftfReturnedDate, ftfApprovedDate,
			ftfReceivedDate, ftfTotal;
	protected JTextArea taAddress, taNotes;
	protected JFormattedTextField ftfDate;
	protected String strHeader[] = { "Quantity", "    Part Number    ",
			"        Description        ",
			"<html><center>   Unit   <br>   Price   </center></html>",
			"  Total  " };
	protected DefaultTableModel tbModel;
	protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	protected TableColumnModel tbColumnRenderer;
	protected TableColumn tbColumn;
	protected Component component;
	protected JTable tbReturnSlip;
	protected JScrollPane spTable, spAddress, spNotes;
	protected JComboBox cmbSupplier, cmbPTNum;
	protected JRadioButton rdbtnFunctional, rdbtnDefectiveWithOutDebitMemo,
			rdbtnDefectiveWithDebitMemo;
	protected ButtonGroup type;
	protected Font fntPlainText, fntHeaderText, fntHeaderTableText;
	protected DateFormat dateFormat;
	private TitledBorder title;
	protected JButton btnAddItem, btnDeleteItem;

	public ReturnSlipGUI()
	{
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		lblHeader = new JLabel("");
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

		lblRSNum = new JLabel("Return Slip #:");
		lblRSNum.setFont(fntPlainText);
		lblRSNum.setBounds(532, 80, 132, 30);
		add(lblRSNum);

		lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(610, 110, 51, 30);
		add(lblDate);

		lblPTNum = new JLabel("P.T. Number:");
		lblPTNum.setFont(fntPlainText);
		lblPTNum.setBounds(534, 140, 132, 30);
		add(lblPTNum);

		lblPONum = new JLabel("P.O. Number:");
		lblPONum.setFont(fntPlainText);
		lblPONum.setBounds(532, 170, 174, 30);
		add(lblPONum);

		lblReturnedBy = new JLabel("Returned By:");
		lblReturnedBy.setFont(fntPlainText);
		lblReturnedBy.setBounds(30, 422, 143, 30);
		add(lblReturnedBy);

		lblApprovedBy = new JLabel("Approved By:");
		lblApprovedBy.setFont(fntPlainText);
		lblApprovedBy.setBounds(30, 452, 143, 30);
		add(lblApprovedBy);

		lblReceivedBy = new JLabel("Received By:");
		lblReceivedBy.setFont(fntPlainText);
		lblReceivedBy.setBounds(30, 482, 143, 30);
		add(lblReceivedBy);

		lblReturnedDate = new JLabel("Date:");
		lblReturnedDate.setFont(fntPlainText);
		lblReturnedDate.setBounds(345, 422, 70, 25);
		add(lblReturnedDate);

		lblApprovedDate = new JLabel("Date:");
		lblApprovedDate.setFont(fntPlainText);
		lblApprovedDate.setBounds(345, 452, 70, 25);
		add(lblApprovedDate);

		lblReceivedDate = new JLabel("Date:");
		lblReceivedDate.setFont(fntPlainText);
		lblReceivedDate.setBounds(345, 482, 70, 25);
		add(lblReceivedDate);

		lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(733, 375, 63, 30);
		add(lblTotal);

		lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(30, 520, 63, 30);
		add(lblNotes);

		tfRSNum = new JTextField();
		tfRSNum.setFont(fntPlainText);
		tfRSNum.setBounds(670, 80, 295, 30);
		add(tfRSNum);

		ftfDate = new JFormattedTextField(dateFormat);
		ftfDate.setValue(new java.util.Date());
		ftfDate.setFont(fntPlainText);
		ftfDate.setBounds(670, 110, 295, 30);
		add(ftfDate);

		cmbPTNum = new JComboBox();
		AutoCompleteDecorator.decorate(cmbPTNum);
		cmbPTNum.setFont(fntPlainText);
		cmbPTNum.setBounds(670, 140, 295, 30);
		add(cmbPTNum);

		tfPONum = new JTextField();
		tfPONum.setFont(fntPlainText);
		tfPONum.setBounds(670, 170, 295, 30);
		add(tfPONum);

		tfReturnedBy = new JTextField();
		tfReturnedBy.setFont(fntPlainText);
		tfReturnedBy.setBounds(164, 422, 170, 30);
		add(tfReturnedBy);

		tfApprovedBy = new JTextField();
		tfApprovedBy.setFont(fntPlainText);
		tfApprovedBy.setBounds(164, 452, 170, 30);
		add(tfApprovedBy);

		tfReceivedBy = new JTextField();
		tfReceivedBy.setFont(fntPlainText);
		tfReceivedBy.setBounds(164, 482, 170, 30);
		add(tfReceivedBy);

		ftfReturnedDate = new JFormattedTextField(dateFormat);
		ftfReturnedDate.setFont(fntPlainText);
		ftfReturnedDate.setBounds(400, 422, 120, 30);
		add(ftfReturnedDate);

		ftfApprovedDate = new JFormattedTextField(dateFormat);
		ftfApprovedDate.setFont(fntPlainText);
		ftfApprovedDate.setBounds(400, 452, 120, 30);
		add(ftfApprovedDate);

		ftfReceivedDate = new JFormattedTextField(dateFormat);
		ftfReceivedDate.setFont(fntPlainText);
		ftfReceivedDate.setBounds(400, 482, 120, 30);
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
		taAddress.setEditable(false);
		add(taAddress);

		taNotes = new JTextArea();
		taNotes.setFont(fntPlainText);
		taNotes.setWrapStyleWord(true);
		taNotes.setLineWrap(true);
		add(taNotes);

		spAddress = new JScrollPane(taAddress);
		spAddress.setBounds(125, 120, 375, 80);
		add(spAddress);

		spNotes = new JScrollPane(taNotes);
		spNotes.setBounds(30, 552, 490, 40);
		add(spNotes);

		cmbSupplier = new JComboBox();
		AutoCompleteDecorator.decorate(cmbSupplier);
		cmbSupplier.setFont(fntPlainText);
		cmbSupplier.setBounds(125, 80, 375, 30);
		add(cmbSupplier);

		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if (mColIndex != 2)
					return true;
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}

		tbReturnSlip = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column)
			{
				component = super.prepareRenderer(renderer, row, column);
				int modelRow = convertRowIndexToModel(row);
				if (!isRowSelected(modelRow))
				{
					component.setBackground(Color.WHITE);
				} else
				{
					component.setBackground(Color.yellow);
				}
				return component;
			}
		};
		spTable = new JScrollPane(tbReturnSlip);
		spTable.setBounds(30, 210, 935, 158);
		add(spTable);

		tbReturnSlip.setFont(fntPlainText);
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
		tbCellRenderer = tbReturnSlip.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbReturnSlip.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn
					.getTableCellRendererComponent(tbReturnSlip,
							tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}

		pnlType = new JPanel();
		pnlType.setBounds(550, 405, 415, 130);
		pnlType.setLayout(null);
		add(pnlType);
		title = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Type");
		title.setTitleJustification(TitledBorder.LEFT);
		title.setTitleFont(fntPlainText);
		pnlType.setBorder(title);

		rdbtnFunctional = new JRadioButton("Functional");
		rdbtnFunctional.setActionCommand("Functional");
		rdbtnFunctional.setFont(fntPlainText);
		rdbtnFunctional.setSelected(true);
		rdbtnFunctional.setBounds(30, 25, 184, 30);
		pnlType.add(rdbtnFunctional);
		rdbtnFunctional.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});

		rdbtnDefectiveWithOutDebitMemo = new JRadioButton(
				"Defective w/out Debit Memo");
		rdbtnDefectiveWithOutDebitMemo
				.setActionCommand("Defective w/out Debit Memo");
		rdbtnDefectiveWithOutDebitMemo.setFont(fntPlainText);
		rdbtnDefectiveWithOutDebitMemo.setSelected(true);
		rdbtnDefectiveWithOutDebitMemo.setBounds(30, 55, 300, 30);
		pnlType.add(rdbtnDefectiveWithOutDebitMemo);
		rdbtnDefectiveWithOutDebitMemo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});

		rdbtnDefectiveWithDebitMemo = new JRadioButton("Defective w/Debit Memo");
		rdbtnDefectiveWithDebitMemo.setActionCommand("Defective w/Debit Memo");
		rdbtnDefectiveWithDebitMemo.setFont(fntPlainText);
		rdbtnDefectiveWithDebitMemo.setSelected(true);
		rdbtnDefectiveWithDebitMemo.setBounds(30, 85, 300, 30);
		pnlType.add(rdbtnDefectiveWithDebitMemo);
		rdbtnDefectiveWithDebitMemo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});

		type = new ButtonGroup();
		type.add(rdbtnFunctional);
		type.add(rdbtnDefectiveWithOutDebitMemo);
		type.add(rdbtnDefectiveWithDebitMemo);

		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 375, 147, 40);
		add(btnAddItem);

		btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setFont(fntPlainText);
		btnDeleteItem.setBounds(190, 375, 147, 40);
		add(btnDeleteItem);

	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddReturnSlip();
	}
}

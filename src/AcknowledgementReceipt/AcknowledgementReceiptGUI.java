package AcknowledgementReceipt;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class AcknowledgementReceiptGUI extends JPanel
{

	protected JLabel lblHeader, lblCustomer, lblAddress, lblARNum, lblDate,
			lblPONum, lblDRNum, lblDiscount, lblBalance, lblSalesperson,
			lblOrderedBy, lblDeliveryNotes, lblDeliveredBy, lblTotal;
	protected JTextField tfARNum, tfPONum, tfDRNum, tfSalesperson, tfOrderedBy,
			tfDeliveredBy;
	protected JFormattedTextField ftfDate, ftfDiscount, ftfTotal, ftfBalance;
	protected JTextArea taAddress, taDeliveryNotes;
	protected String[] strHeader = { "Quantity", "    Part Number    ",
			"        Description        ",
			"<html><center>   Unit   <br>   Price   </center></html>",
			"  Subtotal  " };
	protected DefaultTableModel tbModel;
	protected TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	protected TableColumnModel tbColumnRenderer;
	protected TableColumn tbColumn;
	protected Component component;
	protected JTable tbARReceipt;
	protected JScrollPane spTable, spAddress, spDeliveryNotes;
	protected JButton btnAddItem, btnDeleteItem;
	protected Font fntPlainText, fntHeaderText, fntHeaderTableText;
	protected DateFormat dateFormat;
	protected JComboBox cmbCustomer;

	public AcknowledgementReceiptGUI()
	{
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		lblHeader = new JLabel("");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 800, 86);
		add(lblHeader);

		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 80, 111, 30);
		add(lblCustomer);

		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(30, 120, 111, 30);
		add(lblAddress);

		lblARNum = new JLabel("Acknowledgement Receipt #:");
		lblARNum.setFont(fntPlainText);
		lblARNum.setBounds(530, 80, 350, 30);
		add(lblARNum);

		lblDate = new JLabel("Date:");
		lblDate.setFont(fntPlainText);
		lblDate.setBounds(610, 110, 73, 30);
		add(lblDate);

		lblPONum = new JLabel("P.O. Number:");
		lblPONum.setFont(fntPlainText);
		lblPONum.setBounds(530, 140, 138, 30);
		add(lblPONum);

		lblDRNum = new JLabel("D.R. Number:");
		lblDRNum.setFont(fntPlainText);
		lblDRNum.setBounds(530, 170, 138, 30);
		add(lblDRNum);

		lblSalesperson = new JLabel("Salesperson:");
		lblSalesperson.setFont(fntPlainText);
		lblSalesperson.setBounds(30, 436, 147, 30);
		add(lblSalesperson);

		lblOrderedBy = new JLabel("Ordered By:");
		lblOrderedBy.setFont(fntPlainText);
		lblOrderedBy.setBounds(30, 466, 133, 30);
		add(lblOrderedBy);

		lblDeliveredBy = new JLabel("Delivered By:");
		lblDeliveredBy.setFont(fntPlainText);
		lblDeliveredBy.setBounds(30, 496, 147, 30);
		add(lblDeliveredBy);

		lblDeliveryNotes = new JLabel("Delivery Notes:");
		lblDeliveryNotes.setFont(fntPlainText);
		lblDeliveryNotes.setBounds(30, 526, 147, 30);
		add(lblDeliveryNotes);

		lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(fntPlainText);
		lblDiscount.setBounds(700, 385, 105, 30);
		add(lblDiscount);

		lblTotal = new JLabel("Total:");
		lblTotal.setFont(fntPlainText);
		lblTotal.setBounds(740, 425, 80, 30);
		add(lblTotal);

		lblBalance = new JLabel("Balance:");
		lblBalance.setFont(fntPlainText);
		lblBalance.setBounds(710, 505, 97, 30);
		add(lblBalance);

		tfARNum = new JTextField();
		tfARNum.setFont(fntPlainText);
		tfARNum.setBounds(815, 80, 150, 30);
		add(tfARNum);

		ftfDate = new JFormattedTextField(dateFormat);
		ftfDate.setValue(new java.util.Date());
		ftfDate.setFont(fntPlainText);
		ftfDate.setBounds(670, 110, 295, 30);
		add(ftfDate);

		tfPONum = new JTextField();
		tfPONum.setFont(fntPlainText);
		tfPONum.setBounds(670, 140, 295, 30);
		add(tfPONum);

		tfDRNum = new JTextField();
		tfDRNum.setFont(fntPlainText);
		tfDRNum.setBounds(670, 170, 295, 30);
		add(tfDRNum);

		tfSalesperson = new JTextField();
		tfSalesperson.setFont(fntPlainText);
		tfSalesperson.setBounds(165, 436, 335, 30);
		add(tfSalesperson);

		tfOrderedBy = new JTextField();
		tfOrderedBy.setFont(fntPlainText);
		tfOrderedBy.setBounds(165, 466, 335, 30);
		add(tfOrderedBy);

		tfDeliveredBy = new JTextField();
		tfDeliveredBy.setFont(fntPlainText);
		tfDeliveredBy.setBounds(165, 496, 335, 30);
		add(tfDeliveredBy);

		ftfDiscount = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfDiscount.setFont(fntPlainText);
		ftfDiscount.setHorizontalAlignment(JTextField.RIGHT);
		ftfDiscount.setValue(new Float(00.0F));
		ftfDiscount.setBounds(800, 385, 165, 30);
		add(ftfDiscount);

		ftfTotal = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfTotal.setFont(fntPlainText);
		ftfTotal.setHorizontalAlignment(JTextField.RIGHT);
		ftfTotal.setValue(new Float(00.0F));
		ftfTotal.setEditable(false);
		ftfTotal.setBounds(800, 425, 165, 30);
		add(ftfTotal);

		ftfBalance = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfBalance.setFont(fntPlainText);
		ftfBalance.setHorizontalAlignment(JTextField.RIGHT);
		ftfBalance.setValue(new Float(00.0F));
		ftfBalance.setEditable(false);
		ftfBalance.setBounds(800, 505, 165, 30);
		add(ftfBalance);

		taAddress = new JTextArea();
		taAddress.setFont(fntPlainText);
		taAddress.setWrapStyleWord(true);
		taAddress.setLineWrap(true);
                taAddress.setEditable(false);
		add(taAddress);

		taDeliveryNotes = new JTextArea();
		taDeliveryNotes.setFont(fntPlainText);
		taDeliveryNotes.setWrapStyleWord(true);
		taDeliveryNotes.setLineWrap(true);
		add(taDeliveryNotes);

		spAddress = new JScrollPane(taAddress);
		spAddress.setBounds(135, 120, 365, 80);
		add(spAddress);

		spDeliveryNotes = new JScrollPane(taDeliveryNotes);
		spDeliveryNotes.setBounds(30, 555, 472, 30);
		add(spDeliveryNotes);

		tbModel = new DefaultTableModel()
		{
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if (cmbCustomer.getSelectedItem() == null
						|| cmbCustomer.getSelectedItem().equals(""))
				{
					return false;
				}
				if (mColIndex == 2 || mColIndex == 4)
				{
					return false;
				} else
				{
					return true;
				}
			}

		};

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}

		tbARReceipt = new JTable(tbModel)
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

		tbARReceipt.getTableHeader().setFont(fntHeaderTableText);
		tbARReceipt.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbARReceipt.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbARReceipt.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbARReceipt.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
			{
				tbCellRendererColumn = tbCellRenderer;
			}
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbARReceipt, tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbARReceipt.setFont(fntPlainText);

		spTable = new JScrollPane(tbARReceipt);
		spTable.setBounds(30, 215, 935, 160);
		add(spTable);

		tbARReceipt.getParent().setBackground(tbARReceipt.getBackground());
		tbARReceipt.getTableHeader().setResizingAllowed(false);
		tbARReceipt.getTableHeader().setReorderingAllowed(false);
		tbARReceipt.setColumnSelectionAllowed(true);
		tbARReceipt.setRowSelectionAllowed(true);
		tbARReceipt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbARReceipt.setRowHeight(30);

		cmbCustomer = new JComboBox();
		AutoCompleteDecorator.decorate(cmbCustomer);
		cmbCustomer.setFont(fntPlainText);
		cmbCustomer.setBounds(135, 80, 365, 30);
		add(cmbCustomer);

		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 385, 147, 40);
		add(btnAddItem);

		btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setFont(fntPlainText);
		btnDeleteItem.setBounds(190, 385, 147, 40);
		add(btnDeleteItem);
	}

	public static void main(String arg[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddAcknowledgementReceipt();
	}
}

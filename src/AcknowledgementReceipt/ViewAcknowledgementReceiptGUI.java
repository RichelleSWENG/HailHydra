package AcknowledgementReceipt;

import Classes.Company;
import HailHydra.GUIController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Font;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ViewAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI
		implements TableModelListener
{
	private JButton btnModify, btnClose;
	private GUIController guiController;
	private AcknowledgementReceiptController mainController;
	private AcknowledgementReceipt rcpt;
	private int numItems;
	private float totalBalance, totalItemPrice, tentativeTotal, discount,dedBalance;
	private final float defaultVal = 0;
	private String partNums[];
	private Company c;

	public ViewAcknowledgementReceiptGUI(GUIController temp)
	{
		super();
		guiController = temp;
		AcknowledgementReceipt rcpt = new AcknowledgementReceipt();
		lblHeader.setText("View Acknowledgement Receipt");

		cmbCustomer.setEditable(false);
		tfARNum.setEditable(false);
		tfPONum.setEditable(false);
		tfDRNum.setEditable(false);
		tfSalesperson.setEditable(false);
		tfOrderedBy.setEditable(false);
		tfDeliveredBy.setEditable(false);
		ftfDate.setEditable(false);
		ftfDiscount.setEditable(false);
		ftfTotal.setEditable(false);
		ftfBalance.setEditable(false);
		taAddress.setEditable(false);
		taDeliveryNotes.setEditable(false);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Arial", Font.PLAIN, 21));
		btnModify.setBounds(655, 545, 110, 40);
		add(btnModify);
		btnModify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToModifyAcknowledgementReceipt();
			}
		});

		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Arial", Font.PLAIN, 21));
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToAcknowledgementReceipt();
			}
		});

	}

	public void setMainController(AcknowledgementReceiptController temp)
	{
		mainController = temp;
		rcpt = mainController.getReceiptTarget();
	}

	public void setViewComponents()
	{
		setDataComponents();
		cmbCustomer.setEnabled(false);
		tfARNum.setText(rcpt.getAcknowledgement_receipt_id());
		tfPONum.setText(rcpt.getPo_num());
		tfDRNum.setText(rcpt.getDelivery_receipt_num());
		tfSalesperson.setText(rcpt.getSales_person());
		tfOrderedBy.setText(rcpt.getOrdered_by());
		tfDeliveredBy.setText(rcpt.getDelivered_by());
		ftfDate.setText(rcpt.getDate());
		ftfDiscount.setText(String.valueOf(rcpt.getDiscount()));
		ftfTotal.setEditable(false);
		ftfBalance.setText(String.valueOf(rcpt.getCurrent_balance()));
		taAddress.setEditable(false);
		taDeliveryNotes.setText(rcpt.getDelivery_notes());

		cmbCustomer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i, rowCount;
				rowCount = tbModel.getRowCount();
				for (i = 0; i < rowCount; i++)
					tbModel.removeRow(0);
				tbModel.setRowCount(1);
				mainController.removePending();
				if (cmbCustomer.getSelectedIndex() != 0)
				{
					c = mainController.getCustomer(cmbCustomer
							.getSelectedIndex() - 1);
					taAddress.setText(c.getAddressLoc());

					partNums = new String[mainController.getItems(c.getType())
							.size() + 1];
					partNums[0] = "";
					for (i = 1; i < mainController.getItems(c.getType()).size() + 1; i++)
					{
						partNums[i] = mainController.getItems(c.getType())
								.get(i - 1).getPartNum();
					}

					TableColumn col = tbARReceipt.getColumnModel().getColumn(1);
					col.setCellEditor(new MyComboBoxEditor(partNums));
					col.setCellRenderer(new MyComboBoxRenderer(partNums));
				}
				numItems = 0;
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});
		cmbCustomer.setSelectedItem(rcpt.getCompany_name());
		numItems = rcpt.getList().size();
		tbModel.setRowCount(numItems);
		tbModel.addTableModelListener(this);
		for (int i = 0; i < numItems; i++)
		{
			tbModel.setValueAt(rcpt.getList().get(i).getQuantity(), i, 0);
			tbModel.setValueAt(rcpt.getList().get(i).getPartNum(), i, 1);
		}
		tbARReceipt.setEnabled(false);

	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToViewAcknowledgementReceipt();
	}

	public void setDataComponents()
	{
		String[] customerNames = new String[mainController.getCustomers()
				.size() + 1];
		int i;
		customerNames[0] = "";
		for (i = 1; i < mainController.getCustomers().size() + 1; i++)
		{
			customerNames[i] = mainController.getCustomers().get(i - 1)
					.getName();
		}
		cmbCustomer.setModel(new DefaultComboBoxModel(customerNames));
	}

	public void calcTotalBalance()
	{
		int i;
		totalBalance = 0;
		for (i = 0; i < tbModel.getRowCount(); i++)
		{
			if (tbModel.getValueAt(i, 4) != null)
				totalBalance += Float.parseFloat(tbModel.getValueAt(i, 4)
						.toString());
		}
		dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText());
		ftfTotal.setText(String.valueOf(dedBalance));
		ftfBalance.setText(String.valueOf(dedBalance));
	}

	class MyComboBoxRenderer extends JComboBox implements TableCellRenderer
	{

		public MyComboBoxRenderer(String[] items)
		{
			super(items);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column)
		{
			if (isSelected)
			{
				setForeground(table.getSelectionForeground());
				super.setBackground(table.getSelectionBackground());
			} else
			{
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			setSelectedItem(value);
			return this;
		}
	}

	class MyComboBoxEditor extends DefaultCellEditor
	{

		public MyComboBoxEditor(String[] str)
		{
			super(new JComboBox(str));
		}
	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		if (e.getColumn() == 0)
		{

			if (tbModel.getValueAt(e.getFirstRow(), 1) != null)
			{
				String cmb = tbModel.getValueAt(e.getFirstRow(), 1).toString();
				if (Integer.valueOf(tbModel.getValueAt(e.getFirstRow(), 0)
						.toString()) <= mainController.getAvailQuantity(Arrays
						.asList(partNums).indexOf(cmb) - 1))
				{
					if (tbModel.getValueAt(e.getFirstRow(), 0) != null
							&& !cmb.equals("")
							&& !tbModel.getValueAt(e.getFirstRow(), 0)
									.toString().equals(""))
					{
						totalItemPrice = Integer.parseInt(tbModel.getValueAt(
								e.getFirstRow(), 0).toString())
								* Float.parseFloat(tbModel.getValueAt(
										e.getFirstRow(), 3).toString());
						tbModel.setValueAt(totalItemPrice, e.getFirstRow(), 4);
						calcTotalBalance();
					}
				} else
				{
					JOptionPane.showMessageDialog(
							null,
							"You can not buy that many items!!!! You can only buy "
									+ mainController.getAvailQuantity(Arrays
											.asList(partNums).indexOf(cmb) - 1)
									+ ". Pls do not test me");
					tbModel.setValueAt("0", e.getFirstRow(), 0);
				}
			}
		}

		if (e.getColumn() == 1)
		{
			if (tbModel.getValueAt(e.getFirstRow(), 1) != null)
			{
				String cmb = tbModel.getValueAt(e.getFirstRow(), 1).toString();
				if (!cmb.equals(""))
				{
					int i;
					boolean unique = true;
					for (i = 0; i < tbModel.getRowCount(); i++)
						if (tbModel.getValueAt(i, 1) != null
								&& tbModel.getValueAt(i, 1).toString()
										.equals(cmb) && i != e.getFirstRow())
							unique = false;
					if (unique)
					{
						tbModel.setValueAt(mainController.getItems(c.getType())
								.get(Arrays.asList(partNums).indexOf(cmb) - 1)
								.getDescription(), e.getFirstRow(), 2);
						tbModel.setValueAt(mainController.getItems(c.getType())
								.get(Arrays.asList(partNums).indexOf(cmb) - 1)
								.getPrice(), e.getFirstRow(), 3);
					} else
					{
						JOptionPane
								.showMessageDialog(
										null,
										"You've already chosen that item. Edit the quantity previously or select another pls",
										"Duplicate Items",
										JOptionPane.ERROR_MESSAGE);
						tbModel.removeRow(e.getFirstRow());
						tbModel.setRowCount(numItems + 1);
						tbModel.moveRow(numItems, numItems, e.getFirstRow());
					}
				}
				if (tbModel.getValueAt(e.getFirstRow(), 0) != null
						&& !cmb.equals("")
						&& !tbModel.getValueAt(e.getFirstRow(), 0).toString()
								.equals(""))
				{
					if (Integer.valueOf(tbModel.getValueAt(e.getFirstRow(), 0)
							.toString()) <= mainController
							.getAvailQuantity(Arrays.asList(partNums).indexOf(
									cmb) - 1))
					{
						totalItemPrice = Integer.parseInt(tbModel.getValueAt(
								e.getFirstRow(), 0).toString())
								* Float.parseFloat(tbModel.getValueAt(
										e.getFirstRow(), 3).toString());
						tbModel.setValueAt(totalItemPrice, e.getFirstRow(), 4);
						calcTotalBalance();
					} else
					{
						JOptionPane.showMessageDialog(
								null,
								"You can not buy that many items!!!! You can only buy "
										+ mainController
												.getAvailQuantity(Arrays
														.asList(partNums)
														.indexOf(cmb) - 1)
										+ ". Pls do not test me");
						tbModel.setValueAt("0", e.getFirstRow(), 0);
					}
				}
			}
		}

		if (e.getColumn() == 3)
		{
			if (tbModel.getValueAt(e.getFirstRow(), 1) != null)
			{
				String cmb = tbModel.getValueAt(e.getFirstRow(), 1).toString();
				if (tbModel.getValueAt(e.getFirstRow(), 0) != null
						&& !cmb.equals("")
						&& !tbModel.getValueAt(e.getFirstRow(), 0).toString()
								.equals(""))
				{
					totalItemPrice = Integer.parseInt(tbModel.getValueAt(
							e.getFirstRow(), 0).toString())
							* Float.parseFloat(tbModel.getValueAt(
									e.getFirstRow(), 3).toString());
					tbModel.setValueAt(totalItemPrice, e.getFirstRow(), 4);
					calcTotalBalance();
				}
			}

		}
	}
}

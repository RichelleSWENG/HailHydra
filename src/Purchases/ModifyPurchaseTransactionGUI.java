package Purchases;

import Classes.Company;
import HailHydra.GUIController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ModifyPurchaseTransactionGUI extends PurchaseTransactionGUI
		implements TableModelListener
{

	private JButton btnSubmit, btnCancel;
	private GUIController guiController;
	private PurchaseTransactionController mainController;
	private int numItems;
	private String partNums[];
	private Company c;
	private PurchaseTransaction pt;

	public ModifyPurchaseTransactionGUI(GUIController temp)
	{
		guiController = temp;

		lblHeader.setText("Modify Purchase Transaction");

		btnAddItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tbModel.setRowCount(numItems + 1);
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});

		btnDeleteItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (tbPurchaseTransaction.getSelectedRow() != -1
						&& tbModel.getRowCount() > 1)
				{
					tbModel.removeRow(tbPurchaseTransaction.getSelectedRow());
					numItems--;
					calcTotalBalance();
				}
			}
		});

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int i;
					mainController.removePending();
					for (i = 0; i < tbModel.getRowCount(); i++)
					{
						mainController.addPendingItem(new PTLineItem(
								tfPurchaseTransactionNum.getText(), Integer
										.parseInt(tbModel.getValueAt(i, 0)
												.toString()), tbModel
										.getValueAt(i, 1).toString(), Float
										.parseFloat(tbModel.getValueAt(i, 3)
												.toString()), Float
										.parseFloat(tbModel.getValueAt(i, 4)
												.toString())));
					}

					mainController.editPT(tfPurchaseTransactionNum.getText(),
							ftfDate.getText(), Float.parseFloat(ftfSubtotal
									.getText().replaceAll(",", "")), tfPONum
									.getText(), tfReceivedBy.getText(),
							tfOrderedBy.getText(), taReceivingNotes.getText(),
							tfDRNum.getText(), tfSINum.getText(), Float
									.parseFloat(ftfDiscount.getText()
											.replaceAll(",", "")), Float
									.parseFloat(ftfVat.getText().replaceAll(
											",", "")), Float
									.parseFloat(ftfBalance.getText()
											.replaceAll(",", "")), "Open",
							mainController.getCustomer(cmbSupplier
									.getSelectedIndex() - 1));
					guiController.changePanelToViewPurchaseTransaction();
				} catch (NullPointerException exception)
				{
					JOptionPane
							.showMessageDialog(
									null,
									"Please fill in the required fields before adding. I do not like you po",
									"Fill in Required Fiels",
									JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToViewPurchaseTransaction();
			}
		});

		cmbSupplier.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i, rowCount;
				rowCount = tbModel.getRowCount();
				for (i = 0; i < rowCount; i++)
				{
					tbModel.removeRow(0);
				}
				tbModel.setRowCount(1);
				mainController.removePending();
				if (cmbSupplier.getSelectedIndex() != 0)
				{
					c = mainController.getCustomer(cmbSupplier
							.getSelectedIndex() - 1);
					taAddress.setText(c.getAddressLoc());

					partNums = new String[mainController.getItems().size() + 1];
					partNums[0] = "";
					for (i = 1; i < mainController.getItems().size() + 1; i++)
					{
						partNums[i] = mainController.getItems().get(i - 1)
								.getPartNum();
					}

					TableColumn col = tbPurchaseTransaction.getColumnModel()
							.getColumn(1);
					col.setCellEditor(new ModifyPurchaseTransactionGUI.MyComboBoxEditor(
							partNums));
					col.setCellRenderer(new ModifyPurchaseTransactionGUI.MyComboBoxRenderer(
							partNums));
				}
				numItems = 0;
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});
	}

	public void setController(PurchaseTransactionController temp)
	{
		mainController = temp;
		VATpercent = mainController.getCurrentVat();
	}

	public void setViewComponents()
	{
		setDataComponents();
		pt = mainController.getPurchaseTransaction();
		tfPurchaseTransactionNum.setText(pt.getPurchase_transaction_id());
		tfPONum.setText(pt.getPo_num());
		tfDRNum.setText(pt.getDelivery_receipt_num());
		tfOrderedBy.setText(pt.getOrdered_by());
		tfReceivedBy.setText(pt.getReceived_by());
		ftfDate.setText(pt.getDate());
		ftfDiscount.setText(String.valueOf(pt.getDiscount()));
		ftfTotal.setEditable(false);

		taAddress.setEditable(false);
		tfSINum.setText(pt.getRef_sales_invoice_num());
		taReceivingNotes.setText(pt.getReceiving_notes());

		cmbSupplier.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i, rowCount;
				rowCount = tbModel.getRowCount();
				for (i = 0; i < rowCount; i++)
				{
					tbModel.removeRow(0);
				}
				tbModel.setRowCount(1);
				mainController.removePending();
				if (cmbSupplier.getSelectedIndex() != 0)
				{
					c = mainController.getCustomer(cmbSupplier
							.getSelectedIndex() - 1);
					taAddress.setText(c.getAddressLoc());

					partNums = new String[mainController.getItems().size() + 1];
					partNums[0] = "";
					for (i = 1; i < mainController.getItems().size() + 1; i++)
					{
						partNums[i] = mainController.getItems().get(i - 1)
								.getPartNum();
					}

					TableColumn col = tbPurchaseTransaction.getColumnModel()
							.getColumn(1);
					col.setCellEditor(new ModifyPurchaseTransactionGUI.MyComboBoxEditor(
							partNums));
					col.setCellRenderer(new ModifyPurchaseTransactionGUI.MyComboBoxRenderer(
							partNums));
				}
				numItems = 0;
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});
		cmbSupplier.setSelectedItem(pt.getCompany_name());
		numItems = pt.getItems().size();
		System.out.println(numItems);
		tbModel.setRowCount(numItems);
		tbModel.addTableModelListener(this);
		for (int i = 0; i < numItems; i++)
		{
			tbModel.setValueAt(pt.getItems().get(i).getQuantity(), i, 0);
			;
			tbModel.setValueAt(pt.getItems().get(i).getPartNum(), i, 1);
			tbModel.setValueAt(pt.getItems().get(i).getUnit_price(), i, 3);
		}

		ftfBalance.setText(String.valueOf(pt.getCurrent_balance()));
	}

	public void setDataComponents()
	{
		String[] supplierNames = new String[mainController.getSuppliers()
				.size() + 1];
		int i;
		supplierNames[0] = "";
		for (i = 1; i < mainController.getSuppliers().size() + 1; i++)
		{
			supplierNames[i] = mainController.getSuppliers().get(i - 1)
					.getName();
		}
		cmbSupplier.setModel(new DefaultComboBoxModel(supplierNames));
	}

	public void tableChanged(TableModelEvent e)
	{
		if (e.getColumn() == 0)
		{

			if (tbModel.getValueAt(e.getFirstRow(), 1) != null)
			{
				String cmb = tbModel.getValueAt(e.getFirstRow(), 1).toString();
				// if (Integer.valueOf(tbModel.getValueAt(e.getFirstRow(),
				// 0).toString()) <=
				// mainController.getAvailQuantity(Arrays.asList(partNums).indexOf(cmb)-1))
				// {
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
				// }
				// else
				// {
				// JOptionPane.showMessageDialog(null,
				// "You can not buy that many items!!!! You can only buy " +
				// mainController.getAvailQuantity(Arrays.asList(partNums).indexOf(cmb)-1)
				// + ". Pls do not test me");
				// tbModel.setValueAt("0", e.getFirstRow(), 0);
				// }
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
					{
						if (tbModel.getValueAt(i, 1) != null
								&& tbModel.getValueAt(i, 1).toString()
										.equals(cmb) && i != e.getFirstRow())
						{
							unique = false;
						}
					}
					if (unique)
					{
						tbModel.setValueAt(
								mainController
										.getItems()
										.get(Arrays.asList(partNums).indexOf(
												cmb) - 1).getDescription(),
								e.getFirstRow(), 2);
						tbModel.setValueAt(
								mainController
										.getItems()
										.get(Arrays.asList(partNums).indexOf(
												cmb) - 1).getPrice(),
								e.getFirstRow(), 3);
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
					totalItemPrice = Integer.parseInt(tbModel.getValueAt(
							e.getFirstRow(), 0).toString())
							* Float.parseFloat(tbModel.getValueAt(
									e.getFirstRow(), 3).toString());
					tbModel.setValueAt(totalItemPrice, e.getFirstRow(), 4);
					calcTotalBalance();
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

}

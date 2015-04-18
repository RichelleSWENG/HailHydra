package CreditMemo;

import Classes.Company;
import HailHydra.GUIController;
import ReturnSlip.ReturnSlip;
import ReturnSlip.ReturnSlipController;
import ReturnSlip.ViewReturnSlipGUI;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class AddCreditMemoGUI extends CreditMemoGUI implements
		TableModelListener
{
	private JButton btnSubmit, btnCancel;
	private GUIController controller;
	private CreditMemoController mainController;
	private ReturnSlip slip;
	private int numItems;
	private float totalItemPrice, totalBalance;
	private final float defaultVal = 0;
	private String partNums[];
	private Company c;
	private ReturnSlipController RSController;

	public AddCreditMemoGUI(GUIController temp)
	{
		controller = temp;

		lblHeader.setText("Add Credit Memo");

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int status;
				String type;
				if (chckbxDefective.isSelected())
					status = 1;
				else
					status = 0;
				if (chckbxReplacement.isSelected())
					type = "Replacement";
				else
					type = "Not Replacement";

				mainController.addCreditMemo(tfCreditMemoNum.getText(),
						ftfDate.getText(), tfReplySlipNum.getText(), status,
						type, (tbModel.getValueAt(0, 1)).toString(),
						(tbModel.getValueAt(0, 4)).toString());

				updateInventory(status, type);
				controller.changePanelToCreditMemo();
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
				controller.changePanelToCreditMemo();
			}
		});
	}

	public void setViewComponents()
	{
		tfCreditMemoNum.setEditable(false);
		String lastCMID = mainController.getLastCMID();
		String newCMID;
		if (lastCMID.equals("null"))
		{
			newCMID = "000001";
			tfCreditMemoNum.setText(newCMID);
		} else
		{
			String numValue = lastCMID.replaceFirst("^0*", "");
			newCMID = String.format("%06d", Integer.parseInt(numValue) + 1);
			tfCreditMemoNum.setText(newCMID);
		}

		setDataComponents();
		cmbSupplier.setEnabled(false);

		tfReplySlipNum.setText(slip.getReturn_slip_id());
		// tfPONum.setText(slip.getPurchase_order_num());

		ftfDate.setText(slip.getDate());
		/*
		 * rdbtnFunctional, rdbtnDefectiveWithOutDebitMemo,
		 * rdbtnDefectiveWithDebitMemo
		 */
		switch (slip.getType())
		{
			case "Functional":
				break;
			case "Defective w/out Debit Memo":
				chckbxDefective.setSelected(true);
				break;
			case "Defective w/Debit Memo":
				chckbxDefective.setSelected(true);
				break;
		}

		ftfTotal.setEditable(false);
		// ftfBalance.setText(String.valueOf(rcpt.getCurrent_balance()));
		taAddress.setEditable(false);
		// taDeliveryNotes.setText(rcpt.getDelivery_notes());

		cmbSupplier.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i, rowCount;
				rowCount = tbModel.getRowCount();
				for (i = 0; i < rowCount; i++)
					tbModel.removeRow(0);
				tbModel.setRowCount(1);
				RSController.removePending();
				if (cmbSupplier.getSelectedIndex() != 0)
				{
					c = RSController.getSupplier(cmbSupplier.getSelectedIndex() - 1);
					taAddress.setText(c.getAddressLoc());

					partNums = new String[RSController.getItems(c.getType())
							.size() + 1];
					partNums[0] = "";
					for (i = 1; i < RSController.getItems(c.getType()).size() + 1; i++)
					{
						partNums[i] = RSController.getItems(c.getType())
								.get(i - 1).getPartNum();
					}

					TableColumn col = tbCreditMemo.getColumnModel()
							.getColumn(1);
					col.setCellEditor(new AddCreditMemoGUI.MyComboBoxEditor(
							partNums));
					col.setCellRenderer(new AddCreditMemoGUI.MyComboBoxRenderer(
							partNums));

				}
				numItems = 0;
				tbModel.setValueAt(defaultVal, numItems, 4);
			}

		});
		String name = null;
		try
		{
			name = RSController.getSupplierbyID(slip.getCompany_id());
		} catch (SQLException ex)
		{
			Logger.getLogger(ViewReturnSlipGUI.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		cmbSupplier.setSelectedItem(name);
		numItems = slip.getList().size();
		tbModel.setRowCount(numItems);
		tbModel.addTableModelListener(this);
		for (int i = 0; i < numItems; i++)
		{
			if (RSController.getReturnSlipItem().equals(
					slip.getList().get(i).getPartNum()))
			{
				tbModel.setRowCount(1);
				tbModel.setValueAt(slip.getList().get(i).getQuantity(), 0, 0);
				tbModel.setValueAt(slip.getList().get(i).getPartNum(), 0, 1);
				tbModel.setValueAt(slip.getList().get(i).getUnit_price(), 0, 3);
			}

		}
		tbCreditMemo.setEnabled(false);

	}

	public void setDataComponents()
	{
		String[] supplierNames = new String[RSController.getSuppliers().size() + 1];
		int i;
		supplierNames[0] = "";
		for (i = 1; i < RSController.getSuppliers().size() + 1; i++)
		{
			supplierNames[i] = RSController.getSuppliers().get(i - 1).getName();
		}
		cmbSupplier.setModel(new DefaultComboBoxModel(supplierNames));

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
		// dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText());
		ftfTotal.setValue(totalBalance);
		// ftfBalance.setText(String.valueOf(dedBalance));
	}

	public void setRSController(ReturnSlipController RSController)
	{
		this.RSController = RSController;
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
						.toString()) <= RSController.getAvailQuantity(Arrays
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
									+ RSController.getAvailQuantity(Arrays
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
						tbModel.setValueAt(RSController.getItems(c.getType())
								.get(Arrays.asList(partNums).indexOf(cmb) - 1)
								.getDescription(), e.getFirstRow(), 2);
						tbModel.setValueAt(RSController.getItems(c.getType())
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

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddCreditMemo();
	}

	public void setMainController(CreditMemoController temp)
	{
		mainController = temp;
		slip = RSController.getSlipTarget();
	}

	private void updateInventory(int status, String type)
	{
		mainController.updateFromDefec((tbModel.getValueAt(0, 0)).toString(),
				(tbModel.getValueAt(0, 1)).toString(), status); // update if
																// defective or
																// not
		mainController.updateFromType((tbModel.getValueAt(0, 0)).toString(),
				(tbModel.getValueAt(0, 1)).toString(), type); // update if
																// functional
	}

}

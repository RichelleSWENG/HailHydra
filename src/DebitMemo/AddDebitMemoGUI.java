package DebitMemo;

import Classes.Company;
import HailHydra.GUIController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
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

public class AddDebitMemoGUI extends DebitMemoGUI implements TableModelListener
{
	private JButton btnSubmit, btnCancel;
	private GUIController guiController;
	private DebitMemoController mainController;
	private int numItems;
	private float totalBalance, totalItemPrice, tentativeTotal, discount,
			dedBalance;
	private final float defaultVal = 0;
	private String partNums[];
	private Company c;
	private String receiptType;

	public AddDebitMemoGUI(GUIController temp)
	{
		guiController = temp;

		lblHeader.setText("Add Debit Memo");

		add(btnAddItem);
		btnAddItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				numItems++;
				tbModel.setRowCount(numItems + 1);
				tbModel.setValueAt(defaultVal, numItems, 4);
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
					for (i = 0; i < tbModel.getRowCount(); i++)
					{
						mainController.addPendingItem(new DMLineItem(tfDBNum
								.getText(), Integer.parseInt(tbModel
								.getValueAt(i, 0).toString()), tbModel
								.getValueAt(i, 1).toString(),
								Float.parseFloat(tbModel.getValueAt(i, 3)
										.toString()), Float.parseFloat(tbModel
										.getValueAt(i, 4).toString())));
					}
					if (cmbRcptType.getSelectedIndex() == 0)
						receiptType = "Acknowledgement Receipt";
					else if (cmbRcptType.getSelectedIndex() == 1)
						receiptType = "Sales Invoice Receipt";
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

					mainController.addDM(tfDBNum.getText(), mainController
							.getCustomer(cmbCustomer.getSelectedIndex() - 1)
							.getId(), ftfDate.getText(), Float
							.parseFloat(ftfTotal.getText()), receiptType,
							cmbRcptNumber.getSelectedItem().toString(),
							tfApprovedBy.getText(), tfReceivedBy.getText(),
							ftfApprovedDate.getText(), ftfReceivedDate
									.getText(), taNotes.getText(), status, type);
					updateInventory(status, type);
					guiController.changePanelToReturnSlip();
				} catch (NullPointerException exception)
				{
					JOptionPane
							.showMessageDialog(
									null,
									"Please fill in the required fields before adding.",
									"Fill in Required Fiels",
									JOptionPane.ERROR_MESSAGE);
				}
				guiController.changePanelToDebitMemo();
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
				guiController.changePanelToDebitMemo();
			}
		});
		cmbRcptNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cmbRcptNumber.getSelectedIndex() == -1)
					System.out.println("sex");
			}
		});
		MyItemListener actionListener = new MyItemListener();
		cmbRcptType.addItemListener(actionListener);
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
					taAddress.setText(c.getAddressLoc()+" "+c.getAddressCity()+" "+c.getAddressCountry()+" "+c.getPostalCode());

					// populate receipt numbers

					if (cmbRcptType.getSelectedIndex() == 0)
					{
						String[] receiptNumbers = new String[mainController
								.getReceiptNumbersAR(c).size() + 1];
						int j;
						receiptNumbers[0] = "";
						for (j = 1; j < mainController.getReceiptNumbersAR(c)
								.size() + 1; j++)
						{
							receiptNumbers[j] = mainController
									.getReceiptNumbersAR(c).get(j - 1);
						}
						cmbRcptNumber.setModel(new DefaultComboBoxModel(
								receiptNumbers));
						// end of population
					} else if (cmbRcptType.getSelectedIndex() == 1)
					{
						String[] receiptNumbers = new String[mainController
								.getReceiptNumbersSI(c).size() + 1];
						int j;
						receiptNumbers[0] = "";
						for (j = 1; j < mainController.getReceiptNumbersSI(c)
								.size() + 1; j++)
						{
							receiptNumbers[j] = mainController
									.getReceiptNumbersSI(c).get(j - 1);
						}
						cmbRcptNumber.setModel(new DefaultComboBoxModel(
								receiptNumbers));
					}
					partNums = new String[mainController.getItems(c.getType())
							.size() + 1];
					partNums[0] = "";
					for (i = 1; i < mainController.getItems(c.getType()).size() + 1; i++)
					{
						partNums[i] = mainController.getItems(c.getType())
								.get(i - 1).getPartNum();
					}

					TableColumn col = tbDebitMemo.getColumnModel().getColumn(1);
					col.setCellEditor(new MyComboBoxEditor(partNums));
					col.setCellRenderer(new MyComboBoxRenderer(partNums));
				}

				numItems = 0;
				tbModel.setValueAt(defaultVal, numItems, 4);

			}
		});

		tbModel.setRowCount(1);
		tbModel.setValueAt(defaultVal, numItems, 4);
		tbModel.addTableModelListener(this);
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

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddDebitMemo();
	}

	public void setMainController(DebitMemoController DMController)
	{
		this.mainController = DMController;
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
						System.out.println(mainController.getItems(c.getType())
								.get(Arrays.asList(partNums).indexOf(cmb) - 1)
								.getDescription());
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
		ftfTotal.setText(String.valueOf(totalBalance));
		// ftfBalance.setText(String.valueOf(dedBalance));

	}

	public void setViewComponents()
	{
		tfDBNum.setEditable(false);
		String lastDMID = mainController.getLastDMID();
		String newDMID;
		if (lastDMID.equals("null"))
		{
			newDMID = "000001";
			tfDBNum.setText(newDMID);
		} else
		{
			String numValue = lastDMID.replaceFirst("^0*", "");
			newDMID = String.format("%06d", Integer.parseInt(numValue) + 1);
			tfDBNum.setText(newDMID);
		}
		setDataComponents();
	}

	class MyComboBoxRenderer extends JComboBox implements TableCellRenderer
	{

		public MyComboBoxRenderer(String[] items)
		{
			super(items);
		}

		@Override
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

	class MyItemListener implements ItemListener
	{
		// This method is called only if a new item has been selected.
		@Override
		public void itemStateChanged(ItemEvent evt)
		{
			JComboBox cb = (JComboBox) evt.getSource();

			String item = evt.getItem().toString();

			if (evt.getStateChange() == ItemEvent.SELECTED)
			{
				if (item.equals("S.I.#"))
				{
					if (c != null)
					{
						String[] receiptNumbers = new String[mainController
								.getReceiptNumbersSI(c).size() + 1];
						int j;
						receiptNumbers[0] = "";
						for (j = 1; j < mainController.getReceiptNumbersSI(c)
								.size() + 1; j++)
						{
							receiptNumbers[j] = mainController
									.getReceiptNumbersSI(c).get(j - 1);
						}
						cmbRcptNumber.setModel(new DefaultComboBoxModel(
								receiptNumbers));
					}
				} else if (item.equals("A.R.#"))
				{
					if (c != null)
					{
						String[] receiptNumbers = new String[mainController
								.getReceiptNumbersAR(c).size() + 1];
						int j;
						receiptNumbers[0] = "";
						for (j = 1; j < mainController.getReceiptNumbersAR(c)
								.size() + 1; j++)
						{
							receiptNumbers[j] = mainController
									.getReceiptNumbersAR(c).get(j - 1);
						}
						cmbRcptNumber.setModel(new DefaultComboBoxModel(
								receiptNumbers));
					}
				}

			} else if (evt.getStateChange() == ItemEvent.DESELECTED)
			{
				// Item is no longer selected
			}

		}

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

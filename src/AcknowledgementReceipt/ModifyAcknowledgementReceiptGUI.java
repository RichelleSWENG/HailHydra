package AcknowledgementReceipt;

import static AcknowledgementReceipt.AddAcknowledgementReceiptGUI.isFloat;
import static AcknowledgementReceipt.AddAcknowledgementReceiptGUI.isInteger;
import Classes.Company;
import HailHydra.GUIController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ModifyAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI
		implements TableModelListener
{

	private JButton btnSubmit, btnCancel;
	private GUIController guiController;
	private AcknowledgementReceiptController mainController;
	private AcknowledgementReceipt rcpt;
	private int numItems;
	private float totalBalance, totalItemPrice, discount,dedBalance;
	private final float defaultVal = 0;
	private String partNums[];
	private Company c;
        private ArrayList<ARLineItem> tempARLine = new ArrayList<>();

	public ModifyAcknowledgementReceiptGUI(GUIController temp)
	{
		super();
		guiController = temp;

		cmbCustomer.setEditable(true);

		lblHeader.setText("Modify Acknowledgement Receipt");

                ftfDiscount.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent documentEvent)
			{
				update();
			}

			public void insertUpdate(DocumentEvent documentEvent)
			{
				update();
			}

			public void removeUpdate(DocumentEvent documentEvent)
			{
				update();
			}

			private void update()
			{
				try
				{
					Float.parseFloat(ftfDiscount.getText());
					calcTotalBalance();
				} catch (Exception temp)
				{

				}
			}

		});
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                                     boolean error = false;
                                                        if(Float.parseFloat(ftfDiscount.getText().replaceAll(",[^ ]", ""))<0.00f)
                                    {
                                    {JOptionPane
							.showMessageDialog(
									null,
									"Discount cannot be negative.",
									"Invalid Discount",
									JOptionPane.ERROR_MESSAGE);}
                                    error = true;
                                    //ftfDiscount.setText("0.00");
                                    }
                                      if(Float.parseFloat(ftfBalance.getText().replaceAll(",[^ ]", ""))<0.00f)
                                    {
                                    {JOptionPane
							.showMessageDialog(
									null,
									"Balance cannot be negative.",
									"Invalid Balance",
									JOptionPane.ERROR_MESSAGE);}
                                    error = true;
                                    //ftfDiscount.setText("0.00");
                                    }
                                 if(error == false)
                                 {
				try
				{
                                    mainController.DeductQuantity(tempARLine);
					int i;
					for (i = 0; i < tbModel.getRowCount(); i++)
					{
						mainController.addPendingItem(new ARLineItem(tfARNum
								.getText(), Integer.parseInt(tbModel
								.getValueAt(i, 0).toString()), tbModel
								.getValueAt(i, 1).toString(),
								Float.parseFloat(tbModel.getValueAt(i, 3)
										.toString()), Float.parseFloat(tbModel
										.getValueAt(i, 4).toString())));
					}
                                         Date currdate = new Date(); 
                                        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                                        String yourDate = dateFormat.format(currdate);
                                        Date ARDate = df.parse(ftfDate.getText());
                                        currdate = df.parse(yourDate);
                                        if(ARDate.after(currdate))
                                        {
                                        JOptionPane
							.showMessageDialog(
									null,
									"Please do not enter a future date",
									"Fill in Required Fiels",
									JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
					mainController.editAR(tfARNum.getText(), ftfDate.getText(),
							Float.parseFloat(ftfTotal.getText().replaceAll(",", "")), tfPONum
									.getText(), tfOrderedBy.getText(),
							tfSalesperson.getText(), tfDeliveredBy.getText(),
							taDeliveryNotes.getText(), tfDRNum.getText(), Float
									.parseFloat(ftfDiscount.getText().replaceAll(",", "")), Float
									.parseFloat(ftfBalance.getText().replaceAll(",", "")), "Open",
							mainController.getCustomer(cmbCustomer
									.getSelectedIndex() - 1));
					guiController.changePanelToViewAcknowledgementReceipt();
                                        }
				} catch (NullPointerException exception)
				{
					JOptionPane
							.showMessageDialog(
									null,
									"Please fill in the required fields before adding. I do not like you po",
									"Fill in Required Fiels",
									JOptionPane.ERROR_MESSAGE);
				} catch (ParseException ex)
                            {
                                Logger.getLogger(ModifyAcknowledgementReceiptGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
                        }
		});

		btnAddItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                             if (tbARReceipt.isEditing())
                                tbARReceipt.getCellEditor().stopCellEditing();
				tbModel.setRowCount(numItems + 1);
				tbModel.setValueAt(defaultVal, numItems, 4);
                                numItems++;
			}
		});

		btnDeleteItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                                //tbARReceipt.setEnabled(false);
                                
				if (tbARReceipt.getSelectedRow() != -1
						&& tbModel.getRowCount() > 1)
				{
                                        if (tbARReceipt.isEditing())
                                        tbARReceipt.getCellEditor().stopCellEditing();
                                        tbModel.setValueAt("", tbARReceipt.getSelectedRow(), 1);
					tbModel.removeRow(tbARReceipt.getSelectedRow());
					numItems--;
					calcTotalBalance();
				}
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 21));
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToViewAcknowledgementReceipt();
			}
		});
	}

	public void setMainController(AcknowledgementReceiptController temp)
	{
		mainController = temp;
		rcpt = mainController.getReceiptTarget();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToModifyAcknowledgementReceipt();
	}

	public void setViewComponents()
	{
		setDataComponents();
		tfARNum.setText(rcpt.getAcknowledgement_receipt_id());
		tfPONum.setText(rcpt.getPo_num());
		tfDRNum.setText(rcpt.getDelivery_receipt_num());
		tfSalesperson.setText(rcpt.getSales_person());
		tfOrderedBy.setText(rcpt.getOrdered_by());
		tfDeliveredBy.setText(rcpt.getDelivered_by());
		ftfDate.setText(rcpt.getDate());
		ftfDiscount.setValue(rcpt.getDiscount());
		ftfTotal.setEditable(false);
		ftfBalance.setValue(rcpt.getCurrent_balance());
		taDeliveryNotes.setText(rcpt.getDelivery_notes());
                tfARNum.setEditable(false);

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
                    ARLineItem tempAR = new ARLineItem(rcpt.getAcknowledgement_receipt_id(), Integer.parseInt(tbModel.getValueAt(i, 0).toString()),tbModel.getValueAt(i, 1).toString(), Float.parseFloat(tbModel.getValueAt(i, 3).toString()), Float.parseFloat(tbModel.getValueAt(i, 4).toString()));
                        tempARLine.add(tempAR);
		}

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
		dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText().replaceAll(",",""));
		ftfTotal.setValue(dedBalance);
		ftfBalance.setValue(dedBalance);
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
            if(isInteger(tbModel.getValueAt(e.getFirstRow(), 0).toString())==false)
                            {
                            JOptionPane.showMessageDialog(
							null,
							"Invalid Quantity");
                            tbModel.setValueAt("0", e.getFirstRow(), 0);
                            }
		if (e.getColumn() == 0)
		{
                     
                     if (Integer.valueOf(tbModel.getValueAt(e.getFirstRow(), 0).toString()) <0)
                            tbModel.setValueAt("0", e.getFirstRow(), 0);
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
							"Selected item is not enough. There is only "
									+ mainController.getAvailQuantity(Arrays
											.asList(partNums).indexOf(cmb) - 1)
									+ " left.");
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
					if (tbModel.getValueAt(e.getFirstRow(), 0) != null
							&& !cmb.equals("")
							&& !tbModel.getValueAt(e.getFirstRow(), 0)
									.toString().equals(""))
					{
						if (Integer.valueOf(tbModel.getValueAt(e.getFirstRow(),
								0).toString()) <= mainController
								.getAvailQuantity(Arrays.asList(partNums)
										.indexOf(cmb) - 1))
						{
							totalItemPrice = Integer.parseInt(tbModel
									.getValueAt(e.getFirstRow(), 0).toString())
									* Float.parseFloat(tbModel.getValueAt(
											e.getFirstRow(), 3).toString());
							tbModel.setValueAt(totalItemPrice, e.getFirstRow(),
									4);
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
		}

		if (e.getColumn() == 3)
		{
                           if(isFloat(tbModel.getValueAt(e.getFirstRow(), 3).toString())==false)
                            {
                            JOptionPane.showMessageDialog(
							null,
							"Unit Price");
                            tbModel.setValueAt("0.00", e.getFirstRow(), 3);
                            }
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

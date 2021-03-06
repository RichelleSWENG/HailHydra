package Sales;

import static AcknowledgementReceipt.AddAcknowledgementReceiptGUI.isFloat;
import static AcknowledgementReceipt.AddAcknowledgementReceiptGUI.isInteger;
import Classes.Company;
import HailHydra.GUIController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ModifySalesInvoiceGUI extends SalesInvoiceGUI implements
		TableModelListener
{

	private JButton btnSubmit, btnCancel;
	private GUIController guiController;
	private SalesInvoiceController mainController;
	private SalesInvoice si;
	private int numItems;
	private float totalBalance, totalItemPrice, tentativeTotal, discount,
			VATpercent, dedBalance;
       private double totalOfEverything, vat, subtotal, everythingwithVAT;
	private final float defaultVal = 0;
	private String partNums[];
	private Company c;

	public ModifySalesInvoiceGUI(GUIController temp)
	{
		super();
		guiController = temp;

		lblHeader.setText("Modify Sales Invoice");

		cmbCustomer.setEditable(true);
                
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

		btnAddItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                            if (tbSalesInvoice.isEditing())
                                tbSalesInvoice.getCellEditor().stopCellEditing();
				tbModel.setRowCount(numItems + 1);
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});

		btnDeleteItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (tbSalesInvoice.getSelectedRow() != -1
						&& tbModel.getRowCount() > 1)
				{
                                    if (tbSalesInvoice.isEditing())
                                        tbSalesInvoice.getCellEditor().stopCellEditing();
					tbModel.removeRow(tbSalesInvoice.getSelectedRow());
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
                                    boolean error = false;
					int i;
					for (i = 0; i < tbModel.getRowCount(); i++)
					{
						mainController.addPendingItem(new SILineItem(tfSINum
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
                                        error = true;
                                        }
                                        
                                         if (tfPwdNum.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"PWD ID Number can not exceed 45");
					error = true;
                                        }
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
                                        
                                        if (tfPONum.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"PO Number can not exceed 45");
					error = true;
                                        }
                                        
                                        if (tfDRNum.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"DR Number can not exceed 45");
					error = true;
                                        }
                                        if (tfSINum.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"SI Number can not exceed 45");
					error = true;
                                        }
                                        
                                        if (tfSalesperson.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"Salesperson can not exceed 45");
					error = true;
                                        }
                                        
                                        if (tfOrderedBy.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"Ordered By can not exceed 45");
					error = true;
                                        }
                                        
                                        if (tfDeliveredBy.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"Delivered By can not exceed 45");
					error = true;
                                        }

                                        if (taDeliveryNotes.getText().length() > 45)
                                        {
					JOptionPane.showMessageDialog(null,
							"Delivery Notes can not exceed 45");
					error = true;
                                        }
                                        
                                        if(error == false)
                                        {
					mainController.editSI(tfSINum.getText(), ftfDate.getText(),
							Float.parseFloat(ftfSubtotal.getText()), tfPONum
									.getText(), tfOrderedBy.getText(),
							tfSalesperson.getText(), tfDeliveredBy.getText(),
							taDeliveryNotes.getText(), tfDRNum.getText(), Float
									.parseFloat(ftfDiscount.getText()), Float
									.parseFloat(ftfBalance.getText()), "Open",
							tfPwdNum.getText(), Float.parseFloat(ftfVat
									.getText()),Float.parseFloat(ftfSubtotal
									.getText()),
							mainController.getCustomer(cmbCustomer
									.getSelectedIndex() - 1));
					guiController.changePanelToViewSalesInvoice();
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
                                Logger.getLogger(ModifySalesInvoiceGUI.class.getName()).log(Level.SEVERE, null, ex);
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
				guiController.changePanelToViewSalesInvoice();
			}
		});
	}

	public void setMainController(SalesInvoiceController temp)
	{
		mainController = temp;
		VATpercent = mainController.getCurrentVat();
		si = mainController.getTarget();
	}

	public void setViewComponents()
	{
		setDataComponents();
		tfSINum.setText(si.getSales_invoice_id());
		tfPONum.setText(si.getPo_num());
		tfDRNum.setText(si.getDelivery_receipt_num());
		tfSalesperson.setText(si.getSales_person());
		tfOrderedBy.setText(si.getOrdered_by());
		tfDeliveredBy.setText(si.getDelivered_by());
		ftfDate.setText(si.getDate());
		ftfDiscount.setText(String.valueOf(si.getDiscount()));
		ftfTotal.setEditable(false);
		ftfBalance.setText(String.valueOf(si.getCurrent_balance()));
		taDeliveryNotes.setText(si.getDelivery_notes());
                //ftfSubtotal.setText(Float.toString(si.getSubtotal()));
                //ftfVat.setText(Float.toString(si.getVat()));

		cmbCustomer.addActionListener(new ActionListener()
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

					TableColumn col = tbSalesInvoice.getColumnModel()
							.getColumn(1);
					col.setCellEditor(new ModifySalesInvoiceGUI.MyComboBoxEditor(
							partNums));
					col.setCellRenderer(new ModifySalesInvoiceGUI.MyComboBoxRenderer(
							partNums));
				}
				numItems = 0;
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});
		cmbCustomer.setSelectedItem(si.getCompany_name());
		numItems = si.getItems().size();
		tbModel.setRowCount(numItems);
		tbModel.addTableModelListener(this);
		for (int i = 0; i < numItems; i++)
		{
			tbModel.setValueAt(si.getItems().get(i).getQuantity(), i, 0);
			tbModel.setValueAt(si.getItems().get(i).getPartNum(), i, 1);
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
		totalOfEverything = 0;
		for (i = 0; i < tbModel.getRowCount(); i++)
		{
			totalOfEverything += Float.parseFloat(tbModel.getValueAt(i, 4)
					.toString());
		}
		subtotal = ((100 - VATpercent) / 100)
				* (totalOfEverything - Double
						.parseDouble(ftfDiscount.getText()));
		ftfSubtotal.setValue(subtotal);
		vat = VATpercent
				/ 100
				* (totalOfEverything - Double
						.parseDouble(ftfDiscount.getText()));
		ftfVat.setValue(vat);
		ftfTotal.setValue(totalOfEverything
				- Double.parseDouble(ftfDiscount.getText()));
		ftfBalance.setValue(totalOfEverything
				- Double.parseDouble(ftfDiscount.getText()));

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

	public void tableChanged(TableModelEvent e)
	{
		if (e.getColumn() == 0)
		{
                     if(isInteger(tbModel.getValueAt(e.getFirstRow(), 0).toString())==false)
                            {
                            JOptionPane.showMessageDialog(
							null,
							"Invalid Quantity");
                            tbModel.setValueAt("0", e.getFirstRow(), 0);
                            }

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
								"Selected item is not enough. There is only "
										+ mainController
												.getAvailQuantity(Arrays
														.asList(partNums)
														.indexOf(cmb) - 1)
										+ " left.");
						tbModel.setValueAt("0", e.getFirstRow(), 0);
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

package Purchases;

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

public class AddPurchaseTransactionGUI extends PurchaseTransactionGUI implements
		TableModelListener
{

	private JButton btnSubmit, btnCancel;
	private GUIController guiController;
	private PurchaseTransactionController mainController;
	private int numItems;
	private String partNums[];
	private Company c;

	public AddPurchaseTransactionGUI(GUIController temp)
	{
		guiController = temp;

		cmbSupplier.setEditable(true);
		lblHeader.setText("Add Purchase Transaction");

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
                             if (tbPurchaseTransaction.isEditing())
                                tbPurchaseTransaction.getCellEditor().stopCellEditing();
				numItems++;
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
                                     if (tbPurchaseTransaction.isEditing())
                                        tbPurchaseTransaction.getCellEditor().stopCellEditing();
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
									"Discount cannot be negative.",
									"Invalid Discount",
									JOptionPane.ERROR_MESSAGE);}
                                    error = true;
                                    //ftfDiscount.setText("0.00");
                                    }
                                 if(error == false)
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
					mainController.addPT(tfPurchaseTransactionNum.getText(),
							ftfDate.getText(), Float.parseFloat(ftfTotal
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
					
                                        String caller = guiController.getCallingFunction();
                                        guiController.setCallingFunction("");
                                        if (caller.equals("Order Report"))
                                            guiController.changePanelToOrderReport();
                                        else
                                            guiController.changePanelToPurchaseTransactionList();
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
                                Logger.getLogger(AddPurchaseTransactionGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
				mainController.removePending();
                                String caller = guiController.getCallingFunction();
                                guiController.setCallingFunction("");
                                if (caller.equals("Order Report"))
                                    guiController.changePanelToOrderReport();
                                else
                                    guiController.changePanelToPurchaseTransactionList();
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
					taAddress.setText(c.getAddressLoc()+" "+c.getAddressCity()+" "+c.getAddressCountry()+" "+c.getPostalCode());

					partNums = new String[mainController.getItems().size() + 1];
					partNums[0] = "";
					for (i = 1; i < mainController.getItems().size() + 1; i++)
					{
						partNums[i] = mainController.getItems().get(i - 1)
								.getPartNum();
					}

					TableColumn col = tbPurchaseTransaction.getColumnModel()
							.getColumn(1);
					col.setCellEditor(new MyComboBoxEditor(partNums));
					col.setCellRenderer(new MyComboBoxRenderer(partNums));
				}
				numItems = 0;
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});

		ftfDiscount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calcTotalBalance();
			}
		});

		tbModel.setRowCount(1);
		tbModel.setValueAt(defaultVal, numItems, 4);
		tbModel.addTableModelListener(this);

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

	public void setController(PurchaseTransactionController temp)
	{
		mainController = temp;
		VATpercent = mainController.getCurrentVat();
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
                            if (Integer.valueOf(tbModel.getValueAt(e.getFirstRow(), 0).toString()) <0)
                            tbModel.setValueAt("0", e.getFirstRow(), 0);
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

	public void setViewComponents()
	{
		setDataComponents();

		tfPurchaseTransactionNum.setEditable(false);
		String lastPTID = mainController.getLastPTID();
		String newPTID;
		if (lastPTID.equals("null"))
		{
			newPTID = "000001";
			tfPurchaseTransactionNum.setText(newPTID);
		} else
		{
			String numValue = lastPTID.replaceFirst("^0*", "");
			newPTID = String.format("%06d", Integer.parseInt(numValue) + 1);
			tfPurchaseTransactionNum.setText(newPTID);
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

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddPurchaseTransaction();
	}
}

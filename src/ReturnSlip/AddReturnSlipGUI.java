package ReturnSlip;

import Classes.Company;
import HailHydra.GUIController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class AddReturnSlipGUI extends ReturnSlipGUI implements
		TableModelListener
{
	private JButton btnSubmit, btnCancel;
	private GUIController guiController;
	private ReturnSlipController mainController;
	private int numItems;
	private Double totalItemPrice, totalBalance;
	private final float defaultVal = 0;
	private String partNums[];
	private Company c;

	public AddReturnSlipGUI(GUIController temp)
	{
		super();
		guiController = temp;
		numItems = 0;
		totalItemPrice = 0.00;
		totalBalance = 0.00;

		lblHeader.setText("Add Return Slip");

		btnAddItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				numItems++;
				tbModel.setRowCount(numItems + 1);
				tbModel.setValueAt(defaultVal, numItems, 4);
			}
		});

		btnDeleteItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (tbReturnSlip.getSelectedRow() != -1
						&& tbModel.getRowCount() > 1)
				{
					tbModel.removeRow(tbReturnSlip.getSelectedRow());
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
					for (i = 0; i < tbModel.getRowCount(); i++)
					{
						mainController.addPendingItem(new RSLineItem(tfRSNum
								.getText(), Integer.parseInt(tbModel
								.getValueAt(i, 0).toString()), tbModel
								.getValueAt(i, 1).toString(),
								Float.parseFloat(tbModel.getValueAt(i, 3)
										.toString()), Float.parseFloat(tbModel
										.getValueAt(i, 4).toString())));
					}
                                        String tempPTNum = cmbPTNum.getSelectedItem().toString(); // if there is no existing PT
                                        if(tempPTNum.equals(""))
                                            tempPTNum = "0";
					mainController.addRS(tfRSNum.getText(), mainController
							.getSupplier(cmbSupplier.getSelectedIndex() - 1)
							.getId(), ftfDate.getText(), Float
							.parseFloat(ftfTotal.getText().replaceAll(",", "")), Integer
							.parseInt(tempPTNum),
							tfPONum.getText(), tfReturnedBy.getText(),
							ftfReturnedDate.getText(), tfApprovedBy.getText(),
							ftfApprovedDate.getText(), tfReceivedBy.getText(),
							ftfReceivedDate.getText(), taNotes.getText(), type
									.getSelection().getActionCommand());

					UpdateInventory(type.getSelection().getActionCommand());
					guiController.changePanelToReturnSlip();
				} catch (NullPointerException exception)
				{
					JOptionPane
							.showMessageDialog(
									null,
									"Please fill in the required fields before adding.",
									"Fill in Required Fields.",
									JOptionPane.ERROR_MESSAGE);
				}
				guiController.changePanelToReturnSlip();
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
				guiController.changePanelToReturnSlip();
			}
		});

		               cmbSupplier.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tfPONum.setText("");
                int i, rowCount;
                rowCount = tbModel.getRowCount();
                for (i = 0; i < rowCount; i++)
                    tbModel.removeRow(0);
                tbModel.setRowCount(1);
                mainController.removePending();
                cmbPTNum.removeAllItems();
                if (cmbSupplier.getSelectedIndex() != 0)
                {
                    c = mainController.getSupplier(cmbSupplier.getSelectedIndex() - 1);
                    String[] PTNumbers = new String[mainController.getPurchaseTransactionNumbersbySupplier(c).size()+1];
                    int j;
                    PTNumbers[0] = "";
                     for (j = 1; j < mainController.getPurchaseTransactionNumbersbySupplier(c).size()+1; j++)
                     {
                        PTNumbers[j] = mainController.getPurchaseTransactionNumbersbySupplier(c).get(j - 1);
                    }
                    cmbPTNum.setModel(new DefaultComboBoxModel(PTNumbers));
                    //end of population
                    
                    //c = mainController.getSupplier(cmbSupplier.getSelectedIndex() - 1);
                    taAddress.setText(c.getAddressLoc()+" "+c.getAddressCity()+" "+c.getAddressCountry()+" "+c.getPostalCode());
                    
                    
                    partNums = new String[mainController.getItems(c.getType()).size() + 1];
                    partNums[0] = "";
                    for (i = 1; i < mainController.getItems(c.getType()).size() + 1; i++)
                    {
                        partNums[i] = mainController.getItems(c.getType()).get(i - 1).getPartNum();
                    }
        
                TableColumn col = tbReturnSlip.getColumnModel().getColumn(1);
                    col.setCellEditor(new AddReturnSlipGUI.MyComboBoxEditor(partNums));
                    col.setCellRenderer(new AddReturnSlipGUI.MyComboBoxRenderer(partNums));
                }
                numItems = 0;
                tbModel.setValueAt(defaultVal, numItems, 4);
            }
        });

		       cmbPTNum.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String PO;
               if(cmbPTNum.getSelectedIndex()!=0 && cmbPTNum.getSelectedIndex()!=-1)
               {
                    PO = mainController.getPurchaseOrderNumber(cmbPTNum.getSelectedItem().toString());
                    tfPONum.setText(PO);
               }
            }
        });

		tbModel.setRowCount(1);
		tbModel.setValueAt(defaultVal, numItems, 4);
		tbModel.addTableModelListener(this);

	}

	public void setMainController(ReturnSlipController temp)
	{
		mainController = temp;
	}

	public void setDataComponents()
	{
		tfRSNum.setEditable(false);
		String lastRSID = mainController.getLastRSID();
		String newRSID;
		if (lastRSID.equals("null"))
		{
			newRSID = "000001";
			tfRSNum.setText(newRSID);
		} else
		{
			String numValue = lastRSID.replaceFirst("^0*", "");
			newRSID = String.format("%06d", Integer.parseInt(numValue) + 1);
			tfRSNum.setText(newRSID);
		}

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
		// PurchaseTransaction cmbobox
		String[] purchaseTransactionNumbers = new String[mainController
				.getPurchaseTransactionNumbers().size() + 1];
		int j;
		purchaseTransactionNumbers[0] = "";
		for (j = 1; j < mainController.getPurchaseTransactionNumbers().size() + 1; j++)
		{
			purchaseTransactionNumbers[j] = mainController
					.getPurchaseTransactionNumbers().get(j - 1);
		}
		cmbPTNum.setModel(new DefaultComboBoxModel(purchaseTransactionNumbers));

	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddReturnSlip();
	}

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
						totalItemPrice = Double.parseDouble(tbModel.getValueAt(
								e.getFirstRow(), 0).toString())
								* Double.parseDouble(tbModel.getValueAt(
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
					totalItemPrice = Double.parseDouble(tbModel.getValueAt(
							e.getFirstRow(), 0).toString())
							* Double.parseDouble(tbModel.getValueAt(
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
					totalItemPrice = Double.parseDouble(tbModel.getValueAt(
							e.getFirstRow(), 0).toString())
							* Double.parseDouble(tbModel.getValueAt(
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
		totalBalance = 0.00;
		for (i = 0; i < tbModel.getRowCount(); i++)
		{
			totalBalance += Double.parseDouble(tbModel.getValueAt(i, 4)
					.toString());
		}
		// dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText());
		ftfTotal.setValue(totalBalance);
		// ftfBalance.setText(String.valueOf(dedBalance));
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

	public void UpdateInventory(String type)
	{
		if (type.equals("Defective w/Debit Memo"))
		{
			ArrayList<RSLineItem> pending = mainController.getPending();
			for (int i = 0; i < pending.size(); i++)
			{
				String quantity = String.valueOf(pending.get(i).getQuantity());
				String partNum = pending.get(i).getPartNum();
				mainController.DeductFuncAddDef(quantity, partNum);
			}
		}
	}

}

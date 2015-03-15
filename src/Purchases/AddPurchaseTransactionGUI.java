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

public class AddPurchaseTransactionGUI extends PurchaseTransactionGUI
{
	private JButton btnAddItem, btnSubmit, btnCancel;
	private GUIController guiController;
	private PurchaseTransactionController mainController;

	public AddPurchaseTransactionGUI(GUIController temp)
	{
		guiController = temp;
		//temp.changePanelToAddPurchaseTransaction();
		cmbSupplier.setEditable(true);
		lblHeader.setText("Add Purchase Transaction");
		

		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 175, 40);
		add(btnAddItem);
		btnAddItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

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
				guiController.changePanelToPurchaseTransactionList();
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
				guiController.changePanelToPurchaseTransactionList();
			}
		});
		
	}

	public void setController(PurchaseTransactionController temp)
	{
		mainController = temp;
	}

	/*public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddPurchaseTransaction();
	}*/

}

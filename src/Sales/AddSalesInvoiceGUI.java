package Sales;

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

public class AddSalesInvoiceGUI extends SalesInvoiceGUI
{

	private JButton btnAddItem, btnSubmit, btnCancel;
	private GUIController guiController;
	private SalesInvoiceController mainController;
        private int numItems;
        private float totalBalance;
        private float totalItemPrice;
        private float tentativeTotal;
        private float discount;
        private final float defaultVal = 0;
        private float dedBalance;
        private String partNums[];
        private Company c;

	public AddSalesInvoiceGUI(GUIController temp)
	{

		guiController = temp;

		lblHeader.setText("Add Sales Invoice");
                
                cmbCustomer.setEditable(true);

		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 147, 40);
		add(btnAddItem);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToSalesInvoice();
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
				guiController.changePanelToSalesInvoice();
			}
		});
	}

	public void setController(SalesInvoiceController temp)
	{
		mainController = temp;
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddSalesInvoice();
	}
}

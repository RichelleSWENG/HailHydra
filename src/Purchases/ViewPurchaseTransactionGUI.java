package Purchases;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ViewPurchaseTransactionGUI extends PurchaseTransactionGUI
{
	private JButton btnModify, btnClose;
	private GUIController guiController;
	private PurchaseTransactionController mainController;

	public ViewPurchaseTransactionGUI(GUIController temp)
	{
		guiController = temp;

		lblHeader.setText("View Purchase Transaction");

		cmbSupplier.setEditable(false);
		tfPurchaseTransactionNum.setEditable(false);
		tfPONum.setEditable(false);
		tfSINum.setEditable(false);
		tfORNum.setEditable(false);
		tfOrderedBy.setEditable(false);
		tfReceivedBy.setEditable(false);
		ftfDate.setEditable(false);
		ftfDiscount.setEditable(false);
		taAddress.setEditable(false);
		taReceivingNotes.setEditable(false);

		btnModify = new JButton("Modify");
		btnModify.setFont(fntPlainText);
		btnModify.setBounds(655, 545, 110, 40);
		add(btnModify);
		btnModify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guiController.changePanelToModifyPurchaseTransaction();
			}
		});

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
		btnClose.addActionListener(new ActionListener()
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

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToViewPurchaseTransaction();
	}
}

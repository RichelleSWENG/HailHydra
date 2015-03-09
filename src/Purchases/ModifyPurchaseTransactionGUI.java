package Purchases;

import HailHydra.GUIModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ModifyPurchaseTransactionGUI extends PurchaseTransactionGUI
{
        private JButton  btnAddItem, btnSubmit, btnCancel;
        private GUIModel guiController;
	private PurchaseTransactionController mainController;
        
    public ModifyPurchaseTransactionGUI(GUIModel temp)
    {
                guiController=temp;
                
                lblHeader.setText("Modify Purchase Transaction");
                
                btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 175, 40);
		add(btnAddItem);
                btnAddItem.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                
                        }
                    });

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToViewPurchaseTransaction();
                        }
                    });

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
                btnCancel.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToViewPurchaseTransaction();
                        }
                    });
    }
    
    
    public void setController(PurchaseTransactionController temp)
    {
            mainController=temp;
    }
    
    public static void main(String args[])
    {
           GUIModel temp=new GUIModel();
           temp.changePanelToModifyPurchaseTransaction();
    }
    
}

package DebitMemo;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class AddDebitMemoGUI extends DebitMemoGUI
{
        private JButton btnAddItem, btnSubmit, btnCancel;
        private GUIController guiController;
        private DebitMemoController mainController;
	
	public AddDebitMemoGUI(GUIController temp)
	{
                guiController=temp;

                lblHeader.setText("Add Debit Memo");
                
		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 150, 40);
		add(btnAddItem);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToDebitMemo();
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
                                guiController.changePanelToDebitMemo();
                        }
                    });
		
	}
        
        public void setController(DebitMemoController temp)
        {
            mainController=temp;
        }
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToAddDebitMemo();
        }
}

package CreditMemo;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class AddCreditMemoGUI extends CreditMemoGUI
{
        private JButton btnSubmit, btnCancel;
        private GUIController controller;

	public AddCreditMemoGUI(GUIController temp)
	{
                controller=temp;
                
                lblHeader.setText("Add Credit Memo");

                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToCreditMemo();
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
                                controller.changePanelToCreditMemo();
                        }
                    });
	}
        
        
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToAddCreditMemo();
        }

}

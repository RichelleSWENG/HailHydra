package CreditMemo;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ViewCreditMemoGUI extends CreditMemoGUI
{
        private JButton btnSubmit, btnClose;
        private GUIController controller;
        
        public ViewCreditMemoGUI(GUIController temp)
	{
                controller=temp;
                
                lblHeader.setText("View Credit Memo");

                tfCreditMemoNum.setEditable(false);
                chckbxReplacement.setEnabled(false);

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
                btnClose.addActionListener(
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
           temp.changePanelToViewCreditMemo();
        }
}

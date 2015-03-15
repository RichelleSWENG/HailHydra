package DebitMemo;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ViewDebitMemoGUI extends DebitMemoGUI
{
    private JButton btnClose;
    private GUIController guiController;
    private DebitMemoController mainController;
    
    public ViewDebitMemoGUI(GUIController temp)
    {
                guiController=temp;

                lblHeader.setText("View Debit Memo");
                
                tfDBNum.setEditable(false);
                tfSINum.setEditable(false);
                tfApprovedBy.setEditable(false);
                tfReceivedBy.setEditable(false);
                ftfDate.setEditable(false);
                ftfApprovedDate.setEditable(false);
                ftfReceivedDate.setEditable(false);
                taNotes.setEditable(false);
                taAddress.setEditable(false);
                cmbCustomer.setEditable(false);
                chckbxDefective.setEnabled(false);
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
           temp.changePanelToViewDebitMemo();
        }
}

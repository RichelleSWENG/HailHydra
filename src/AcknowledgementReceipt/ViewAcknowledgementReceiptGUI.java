
package AcknowledgementReceipt;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ViewAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI{
    private JButton  btnModify, btnClose;
    private GUIController guiController;
    private AcknowledgementReceiptController mainController;
    
    public ViewAcknowledgementReceiptGUI(GUIController temp)
    {
                super();
                guiController = temp;
                
                lblHeader.setText("View Acknowledgement Receipt");
		
                cmbCustomer.setEditable(false);
                tfARNum.setEditable(false);
                tfPONum.setEditable(false);
                tfDRNum.setEditable(false);
                tfSalesperson.setEditable(false);
                tfOrderedBy.setEditable(false);
                tfDeliveredBy.setEditable(false);
                ftfDate.setEditable(false);
                ftfDiscount.setEditable(false);
                ftfTotal.setEditable(false);
                ftfBalance.setEditable(false);
                taAddress.setEditable(false);
                taDeliveryNotes.setEditable(false);
                        
                btnModify = new JButton("Modify");
		btnModify.setFont(fntPlainText);
		btnModify.setBounds(655, 545, 110, 40);
		add(btnModify);
                btnModify.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToModifyAcknowledgementReceipt();
                        }
                    });

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
		btnClose.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToAcknowledgementReceipt();
                        }
                    });
    }
    
    public void setMainController(AcknowledgementReceiptController temp)
    {
            mainController=temp;
    }
    
    public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToViewAcknowledgementReceipt();
        }
}

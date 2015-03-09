
package AcknowledgementReceipt;

import HailHydra.GUIModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ViewAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI{
    private JButton  btnModify, btnClose;
    private GUIModel guiController;
    private AcknowledgementReceiptController mainController;
    
    public ViewAcknowledgementReceiptGUI(GUIModel temp)
    {
                super();
                guiController=temp;
                
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
                                guiController.changePanelToAcknowledgementReceiptList();
                        }
                    });
    }
    
    public void setController(AcknowledgementReceiptController temp)
    {
            mainController=temp;
    }
    
    public static void main(String args[]){
           GUIModel temp=new GUIModel();
           temp.changePanelToViewAcknowledgementReceipt();
        }
}

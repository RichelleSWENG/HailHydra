package AcknowledgementReceipt;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Font;

public class ModifyAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI{
    
    private JButton  btnSubmit, btnCancel;
    private GUIController guiController;
    private AcknowledgementReceiptController mainController;
        
    public ModifyAcknowledgementReceiptGUI(GUIController temp)
    {
                super();
                guiController=temp;
                
                cmbCustomer.setEditable(true);
                
                lblHeader.setText("Modify Acknowledgement Receipt");
		
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToViewAcknowledgementReceipt();
                        }
                    });

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 21));
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToViewAcknowledgementReceipt();
                        }
                    });
    }
    
    public void setMainController(AcknowledgementReceiptController temp)
    {
            mainController=temp;
    }
    
    public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToModifyAcknowledgementReceipt();
        }
}

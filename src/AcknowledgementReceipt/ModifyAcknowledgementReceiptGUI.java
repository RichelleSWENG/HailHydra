package AcknowledgementReceipt;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ModifyAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI{
    
    private JButton  btnSubmit, btnCancel;
    private GUIController guiController;
    private AcknowledgementReceiptController mainController;
        
    public ModifyAcknowledgementReceiptGUI(GUIController temp)
    {
                super();
                guiController=temp;
                
                lblHeader.setText("Modify Acknowledgement Receipt");
		
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
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
		btnCancel.setFont(fntPlainText);
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
    
    public void setController(AcknowledgementReceiptController temp)
    {
            mainController=temp;
    }
    
    public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToModifyAcknowledgementReceipt();
        }
}

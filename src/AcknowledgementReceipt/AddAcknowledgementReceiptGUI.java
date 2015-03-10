package AcknowledgementReceipt;


import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class AddAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI
{
        private JButton  btnAddItem, btnSubmit, btnCancel;
        private GUIController guiController;
        private AcknowledgementReceiptController mainController;

	public AddAcknowledgementReceiptGUI(GUIController temp) 
        {
                super();
                guiController=temp;
                cmbCustomer.setEditable(true);
                
                lblHeader.setText("Add Acknowledgement Receipt");
                
                btnAddItem = new JButton("Add Item");	
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 147, 40);
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
                                guiController.changePanelToAcknowledgementReceipt();
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
                                guiController.changePanelToAcknowledgementReceipt();
                        }
                    });	
	}
        
        public void setController(AcknowledgementReceiptController temp)
        {
            mainController=temp;
        }
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToAddAcknowledgementReceipt();
        }


}

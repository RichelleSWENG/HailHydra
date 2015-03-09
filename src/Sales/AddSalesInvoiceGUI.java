package Sales;


import javax.swing.JButton;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSalesInvoiceGUI extends SalesInvoiceGUI 
{
    
        private JButton btnAddItem, btnSubmit, btnCancel;
        private GUIController guiController;
        private SalesInvoiceController mainController;

	public AddSalesInvoiceGUI(GUIController temp) 
        {
            
                guiController=temp;
                
                lblHeader.setText("Add Sales Invoice");
                
                btnAddItem = new JButton("Add Item");	
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 147, 40);
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
                                guiController.changePanelToSalesInvoice();
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
                                guiController.changePanelToSalesInvoice();
                        }
                    });
	}
        
        public void setController(SalesInvoiceController temp)
        {
            mainController=temp;
        }
        
        public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToAddSalesInvoice();
        }
}

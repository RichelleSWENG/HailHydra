package Sales;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ModifySalesInvoiceGUI extends SalesInvoiceGUI
{
        private JButton btnAddItem, btnSubmit, btnCancel;
        private GUIController guiController;
        private SalesInvoiceController mainController;
    
    public ModifySalesInvoiceGUI(GUIController temp)
    {
                guiController=temp;
                
                lblHeader.setText("Modify Sales Invoice");
                
                cmbCustomer.setEditable(true);
                
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
                                guiController.changePanelToViewSalesInvoice();
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
                                guiController.changePanelToViewSalesInvoice();
                        }
                    });
    }
    
    public void setController(SalesInvoiceController temp)
    {
            mainController=temp;
    }
    
    public static void main(String args[]){
           GUIController temp=new GUIController();
           temp.changePanelToModifySalesInvoice();
        }
    
}

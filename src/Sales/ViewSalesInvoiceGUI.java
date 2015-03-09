
package Sales;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ViewSalesInvoiceGUI extends SalesInvoiceGUI
{
        private JButton btnModify, btnClose;
        private GUIController guiController;
        private SalesInvoiceController mainController;
        
        public ViewSalesInvoiceGUI(GUIController temp)
        {
                guiController=temp;
		
                lblHeader.setText("View Sales Invoice");
                
                tfCustomer.setEditable(false);
                tfPwdNum.setEditable(false);
                tfSINum.setEditable(false);
                tfPONum.setEditable(false);
                tfDRNum.setEditable(false);
                tfSalesperson.setEditable(false);
                tfOrderedBy.setEditable(false);
                tfDeliveredBy.setEditable(false);
                ftfDate.setEditable(false);
                ftfDiscount.setEditable(false);
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
                                guiController.changePanelToModifySalesInvoice();
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
           temp.changePanelToViewSalesInvoice();
        }
}

package ReturnSlip;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class AddReturnSlipGUI extends ReturnSlipGUI
{
        private JButton btnAddItem, btnSubmit, btnCancel;
        private GUIController guiController;
        private ReturnSlipController mainController;
        
	public AddReturnSlipGUI(GUIController temp) 
        {
                super();
                guiController=temp;
                
		lblHeader.setText("Add Return Slip");
                
		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(fntPlainText);
		btnAddItem.setBounds(30, 545, 132, 40);
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
                                guiController.changePanelToReturnSlip();
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
                                guiController.changePanelToReturnSlip();
                        }
                    });
		
		
	}
        
        public void setController(ReturnSlipController temp)
        {
            mainController=temp;
        }
        
        public static void main(String args[])
        {
           GUIController temp=new GUIController();
           temp.changePanelToAddReturnSlip();
        }

}

package ReturnSlip;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ViewReturnSlipGUI extends ReturnSlipGUI
{
    private JButton btnClose;
    private GUIController guiController;
    private ReturnSlipController mainController;
    
    public ViewReturnSlipGUI(GUIController temp)
    {
                super();
                guiController=temp;
                
		lblHeader.setText("View Return Slip");
                
                cmbSupplier.setEditable(false);
                tfRSNum.setEditable(false);
                tfSINum.setEditable(false);
                tfPONum.setEditable(false);
                ftfDate.setEditable(false);
                tfReturnedBy.setEditable(false);
                tfApprovedBy.setEditable(false);
                tfReceivedBy.setEditable(false);
                ftfReturnedDate.setEditable(false);
                ftfApprovedDate.setEditable(false);
                ftfReceivedDate.setEditable(false);
                taAddress.setEditable(false);
                taNotes.setEditable(false);
		
                        
		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
                btnClose.addActionListener(
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
           temp.changePanelToViewReturnSlip();
    }
}

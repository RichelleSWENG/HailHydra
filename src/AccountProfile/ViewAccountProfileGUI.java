package AccountProfile;

import HailHydra.GUIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ViewAccountProfileGUI extends AccountProfileGUI
{
        private JButton btnEdit, btnClose;
        private JPanel grpPnlBasicInfo, grpPnlContactInfo;
        private GUIController guiController;
        private AccountProfileController mainController;
        
        public ViewAccountProfileGUI(GUIController temp,AccountProfileController AccountProfileController)
        {            
                super();
                setMainController(AccountProfileController);
                guiController=temp;
                
                lblHeader.setText("View Account Profile");
                
                tfName.setEditable(false);
                tfCity.setEditable(false);
                tfPostCode.setEditable(false);
                tfCountry.setEditable(false);
                tfPhone1.setEditable(false);
                tfPhone2.setEditable(false);
                tfPhone3.setEditable(false);
                tfFaxNumber.setEditable(false);
                tfEmailAddress.setEditable(false);
                tfWebsite.setEditable(false);
                tfContactPerson.setEditable(false);
                ftfCreditLimit.setEditable(false);
                ftfTerms.setEditable(false);
                tfContactPerson.setEditable(false);
                taAddress.setEditable(false);
                rdbtnSisterCompanyCustomer.setEnabled(false);
                rdbtnRetailCustomer.setEnabled(false);
                rdbtnWalkinCustomer.setEnabled(false);
                rdbtnSupplier.setEnabled(false);
                chckbxInactiveAccount.setEnabled(false);
                
                ArrayList accountProfile = mainController.getAccountProfile(); //get the created array list to be placed on this view

                tfName.setText(accountProfile.get(0).toString());
                taAddress.setText(accountProfile.get(1).toString());
                tfCity.setText(accountProfile.get(2).toString());
                tfPostCode.setText(accountProfile.get(3).toString());
                tfCountry.setText(accountProfile.get(4).toString());
                ftfCreditLimit.setText(accountProfile.get(5).toString());
                ftfTerms.setText(accountProfile.get(6).toString());
                tfPhone1.setText(accountProfile.get(7).toString());
                tfPhone2.setText(accountProfile.get(8).toString());
                tfPhone3.setText(accountProfile.get(9).toString());
                tfFaxNumber.setText(accountProfile.get(10).toString());
                tfEmailAddress.setText(accountProfile.get(11).toString());
                tfWebsite.setText(accountProfile.get(12).toString());
                tfContactPerson.setText(accountProfile.get(13).toString());
                if(accountProfile.get(14).toString().equals("Sister Company Customer"))
                    rdbtnSisterCompanyCustomer.setSelected(true);
                else if(accountProfile.get(14).toString().equals("Retail Customer"))
                      rdbtnRetailCustomer.setSelected(true);
                else if(accountProfile.get(14).toString().equals("Walk-In Customer"))
                      rdbtnWalkinCustomer.setSelected(true);
                else if(accountProfile.get(14).toString().equals("Supplier"))
                      rdbtnSupplier.setSelected(true);
                if(accountProfile.get(15).toString().equals("Inactive"))
                      chckbxInactiveAccount.setSelected(true);
                else chckbxInactiveAccount.setSelected(false);
            
                //The following code are for JButtons
                btnEdit = new JButton("Modify");
                btnEdit.setFont(fntPlainText);
                btnEdit.setBounds(655, 545, 110, 40);
                add(btnEdit);
                btnEdit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                              guiController.changePanelToModifyAccountProfile();
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
                                guiController.changePanelToAccountProfile();
                        }
                    });
		        
		grpPnlBasicInfo = new JPanel();
                grpPnlBasicInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                grpPnlBasicInfo.setBounds(10, 147, 482, 394);
                add(grpPnlBasicInfo);
                grpPnlBasicInfo.setLayout(null);

                grpPnlContactInfo = new JPanel();
                grpPnlContactInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                grpPnlContactInfo.setBounds(496, 147, 494, 333);
                add(grpPnlContactInfo);
                grpPnlContactInfo.setLayout(null);
        }
        
        public void setMainController(AccountProfileController temp){
            mainController=temp;
        }
        
        public static void main(String args[]){
            //GUIModel temp=new GUIModel();
           //temp.changePanelToViewAccountProfile();
        }
}

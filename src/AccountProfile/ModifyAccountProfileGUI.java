package AccountProfile;

import HailHydra.GUIModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ModifyAccountProfileGUI extends AccountProfileGUI 
{
        private JButton btnSubmit, btnCancel;
        private GUIModel GUIController;
        private AccountProfileController mainController;
        private String realName;
        private String realType;
        public ModifyAccountProfileGUI(GUIModel temp,AccountProfileController AccountProfileController)
        {
                super();
                setMainController(AccountProfileController);
                GUIController=temp;
                
                lblHeader.setText("Modify Account Profile");
                
                ArrayList accountProfile = mainController.getAccountProfile(); //get the created array list to be placed on this view
                realName = accountProfile.get(0).toString(); //for pkey
                realType = accountProfile.get(14).toString(); //for pkey
                
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
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
                add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            ArrayList al = new ArrayList();
                            al.add(tfName.getText());
                            al.add(taAddress.getText());
                            al.add(tfCity.getText());
                            al.add(tfPostCode.getText());
                            al.add(tfCountry.getText());
                            al.add(ftfCreditLimit.getText());
                            al.add(ftfTerms.getText());
                            al.add(tfPhone1.getText());
                            al.add(tfPhone2.getText());
                            al.add(tfPhone3.getText());
                            al.add(tfFaxNumber.getText());
                            al.add(tfEmailAddress.getText());
                            al.add(tfWebsite.getText());
                            al.add(tfContactPerson.getText());
                            al.add(type.getSelection().getActionCommand());
                            if(chckbxInactiveAccount.isSelected())
                                al.add("Inactive");
                            else al.add("Active");
                            
                           boolean error = false;
                            
                            if(tfName.getText().equals("") || ftfCreditLimit.getText().equals("") || ftfTerms.getText().equals(""))
                            {
                                JOptionPane.showMessageDialog(null, "Please fill in the required fields");
                                error = true;
                            }
                            
                            if(hasSpecial(tfPostCode.getText()))
                            {
                                JOptionPane.showMessageDialog(null, "Please enter a valid postal code");
                                error = true;
                            }    
                            if(error == false)
                            {
                            
                            mainController.ModifyAccountProfile(al,realName,realType); // edit the account
                            GUIController.changePanelToViewAccountProfile();
                            }
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
                                GUIController.changePanelToViewAccountProfile();
                        }
                    });
        }
        
             public void setMainController(AccountProfileController temp){
            mainController=temp;
        }
        
             private boolean isInteger(String s)
        {

            try
            {
                Integer.parseInt(s);
            } catch (NumberFormatException e)
            {
                return false;
            }
            // only got here if we didn't return false
            return true;

        }

        private boolean isFloat(String s)
        {

            try
            {
                Float.parseFloat(s);
            } catch (NumberFormatException e)
            {
                return false;
            }
            return true;
        }
        
        private boolean hasSpecial(String s)
        {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            boolean b = m.find();

        if (b || s.contains(" "))
         return true;
        else 
         return false;
        }
        public static void main(String args[]){
            GUIModel temp=new GUIModel();
           temp.changePanelToModifyAccountProfile();
        }
}

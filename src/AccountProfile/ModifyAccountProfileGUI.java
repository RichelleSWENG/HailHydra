package AccountProfile;

import HailHydra.GUIController;
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
        private GUIController GUIController;
        private AccountProfileController mainController;
        private String realName, realType;
        private ArrayList al;
        
        public ModifyAccountProfileGUI(GUIController temp,AccountProfileController AccountProfileController)
        {
                super();
                setMainController(AccountProfileController);
                GUIController=temp;
                
                lblHeader.setText("Modify Account Profile");
                al = new ArrayList();
                
                rdbtnRetailCustomer.setEnabled(false); 
                rdbtnSisterCompanyCustomer.setEnabled(false);
                rdbtnWalkinCustomer.setEnabled(false); 
                rdbtnSupplier.setEnabled(false);
                
                ArrayList accountProfile = mainController.getAccountProfile(); //get the created array list to be placed on this view
                realName = accountProfile.get(0).toString(); //for pkey
                realType = accountProfile.get(14).toString(); //for pkey
                
                tfName.setText(accountProfile.get(0).toString());
                taAddress.setText(accountProfile.get(1).toString());
                tfCity.setText(accountProfile.get(2).toString());
                tfPostCode.setText(accountProfile.get(3).toString());
                tfCountry.setText(accountProfile.get(4).toString());
                ftfCreditLimit.setValue(Double.parseDouble(accountProfile.get(5).toString()));
                ftfTerms.setValue(Integer.parseInt(accountProfile.get(6).toString()));
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
                           boolean error = false;
                            
                            if(tfName.getText().equals("") || ftfCreditLimit.getText().equals("") || ftfTerms.getText().equals(""))
                            {
                                JOptionPane.showMessageDialog(null, "Please fill-up all required fields.");
                                error = true;
                            }
                            
                            if(tfName.getText().length()>50)
                            {                           
                                JOptionPane.showMessageDialog(null, "Name can not exceed 50 characters. Please re-input the name.");
                                error = true;
                            }
                            
                            if(taAddress.getText().length()>100)
                            {
                                JOptionPane.showMessageDialog(null, "Address can not exceed 100 characters. Please re-input the address.");
                                error = true;
                            }
                            
                            if(tfCity.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "City can not exceed 45 characters. Please re-input the city.");
                                error = true;
                            }
                            
                            if(tfCountry.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Country can not exceed 45 characters. Please re-input the country.");
                                error = true;
                            }
                            
                            if(tfPostCode.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Postcode can not exceed 45 characters. Please re-input the postcode.");
                                error = true;
                            }
                            
                            if(Integer.parseInt(ftfTerms.getText().replaceAll(",", ""))>2000)
                            {
                                JOptionPane.showMessageDialog(null, "Terms can not be greater than 2000. Please re-input the terms.");
                                error = true;
                            }
                                
                            if(tfPhone1.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Phone #1 can not exceed 11 characters. Please re-input the phone #1.");
                                error = true;
                            }
                            
                            if(tfPhone2.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Phone #2 can not exceed 11 characters. Please re-input the phone #2.");
                                error = true;
                            }
                            
                            if(tfPhone3.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Phone #3 can not exceed 11 characters. Please re-input the phone #3.");
                                error = true;
                            }
                            
                            if(tfFaxNumber.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Fax Number can not exceed 11 characters. Please re-input the city.");
                                error = true;
                            }
                            
                            if(tfEmailAddress.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Email address can not exceed 45 characters. Please re-input the email.");
                                error = true;
                            }
                            
                            if(tfWebsite.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Website can not exceed 45 characters. Please re-input the website.");
                                error = true;
                            }
                            
                            if(tfContactPerson.getText().length()>45)
                            {
                                JOptionPane.showMessageDialog(null, "Contact person can not exceed 45 characters. Please re-input the contact person.");
                                error = true;
                            }
                            
                            if(ftfCreditLimit.getText().length()>12)
                            {
                                JOptionPane.showMessageDialog(null, "Credit Limit can not exceed 999,999,999,999");
                                error = true;
                            }  

                            if(Float.parseFloat(ftfCreditLimit.getText())<0.00f)
                            {
                                JOptionPane.showMessageDialog(null, "Credit Limit is invalid.");
                                error= true;
                            }
                            
                            if(!"".equals(tfPhone1.getText()))
                                if(!isInteger(tfPhone1.getText()) || Integer.parseInt(tfPhone1.getText())<0)
                                {
                                    JOptionPane.showMessageDialog(null, "Phone#1  is invalid.");
                                    error = true;
                                }
                            
                            if(!"".equals(tfPhone2.getText()))
                                if(!isInteger(tfPhone2.getText()) || Integer.parseInt(tfPhone2.getText())<0)
                                {
                                    JOptionPane.showMessageDialog(null, "Phone#2 is invalid.");
                                    error = true;
                                }
                            
                            if(!tfPhone3.getText().isEmpty())
                                if(!isInteger(tfPhone3.getText()) || Integer.parseInt(tfPhone3.getText())<0)
                                {
                                    JOptionPane.showMessageDialog(null, "Phone#3 is invalid.");
                                    error = true;
                                }
                            
                            if(!"".equals(tfFaxNumber.getText())) 
                                if(!isInteger(tfFaxNumber.getText()) || Integer.parseInt(tfFaxNumber.getText())<0)
                                {
                                    JOptionPane.showMessageDialog(null, "Fax Number is invalid.");
                                    error = true;
                                }
                     
                            if(hasSpecial(tfPostCode.getText()))
                            {
                                JOptionPane.showMessageDialog(null, "Please enter a valid postal code.");
                                error = true;
                            }      
                            

                            if(error == false)
                            {
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
                            
                                mainController.ModifyAccountProfile(al,realName,realType); // edit the account
                                mainController.setAccountProfile(al);
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
        
        public boolean isInteger(String s)
        {
            try
            {
                Integer.parseInt(s);
            } catch (NumberFormatException e)
            {
                return false;
            }
            return true;
        }

        public boolean isFloat(String s)
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
        
        public boolean hasSpecial(String s)
        {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            boolean b = m.find();

            if (b || s.contains(" "))
             return true;
            else 
             return false;
        }
        
}

package AccountProfile;

import HailHydra.GUIModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;



public class AddAccountProfileGUI extends AccountProfileGUI
{
        private JButton btnSubmit, btnCancel;
        private GUIModel guiController;
        private AccountProfileController mainController;
        private ArrayList<String> al; 
                
	public AddAccountProfileGUI(GUIModel temp )
	{
                super();
                guiController=temp;
            
                lblHeader.setText("Add Account Profile");
                
                //The following code are for JButtons
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
                add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {

                            al = new ArrayList();
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
                            
                            mainController.AddAccountProfile(al); // add the account
                            guiController.changePanelToAccountProfile();
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
                                guiController.changePanelToAccountProfile();
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
            temp.changePanelToAddAccountProfile();
        }

	
	
		
}

package BasicSystemSettings;

import HailHydra.GUIController;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class BasicSystemSettingsGUI extends JPanel 
{
        private JPanel pnlSystem, pnlAdministrator, pnlEmployee;
        private TitledBorder tbSystem, tbAdministrator, tbEmployee;
        private JLabel lblHeader, lblSystemName, lblVAT, lblVATPercentage,
                lblTermsAlert, lblTermsDays, lblCreditLimitAlert, lblCreditLimitPercentage,
                lblSystemStatus, lblAdministratorUsername, 
                lblAdministratorPassword, lblAdministratorVerifyPassword, 
                lblAdministratorStatus, lblEmployeeUsername, lblEmployeePassword, 
                lblEmployeeVerifyPassword, lblEmployeeStatus;
	private JTextField tfSystemName, tfAdminUsername, tfEmployeeUsername;
        private JFormattedTextField ftfVAT, ftfCreditLimitAlert, ftfTermsAlert;
	private JPasswordField pfAdministratorPassword, pfAdministratorVerifyPassword,
                pfEmployeePassword, pfEmployeeVerifyPassword;
        private JButton btnSubmit;
        private Font fntPlainText, fntHeaderText;
        private GUIController GUIcontroller;
	
	public BasicSystemSettingsGUI(GUIController temp) 
        {
                GUIcontroller=temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText=new Font("Arial", Font.BOLD, 40);
                
                pnlSystem= new JPanel();
                pnlSystem.setBounds(30, 80, 935, 240);
                pnlSystem.setLayout(null);
                add(pnlSystem);
                tbSystem = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "System Account");
                tbSystem.setTitleJustification(TitledBorder.LEFT);
                tbSystem.setTitleFont(fntPlainText);
                pnlSystem.setBorder(tbSystem);
                        
                pnlAdministrator= new JPanel();
                pnlAdministrator.setBounds(30, 340, 460, 190);
                pnlAdministrator.setLayout(null);
                add(pnlAdministrator);
                tbAdministrator = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Administrator Account");
                tbAdministrator.setTitleJustification(TitledBorder.LEFT);
                tbAdministrator.setTitleFont(fntPlainText);
                pnlAdministrator.setBorder(tbAdministrator);
                
                pnlEmployee= new JPanel();
                pnlEmployee.setBounds(505, 340, 460, 190);
                pnlEmployee.setLayout(null);
                add(pnlEmployee);
                tbEmployee = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Employee Account");
                tbEmployee.setTitleJustification(TitledBorder.LEFT);
                tbEmployee.setTitleFont(fntPlainText);
                pnlEmployee.setBorder(tbEmployee);
		
		lblHeader = new JLabel("Basic System Settings");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 800, 86);
		add(lblHeader);
		
		lblSystemName = new JLabel("System Name:");
		lblSystemName.setFont(fntPlainText);
		lblSystemName.setBounds(35, 30, 200, 30);
		pnlSystem.add(lblSystemName);
                
                lblVAT = new JLabel("VAT:");
		lblVAT.setFont(fntPlainText);
		lblVAT.setBounds(125, 70, 88, 30);
		pnlSystem.add(lblVAT);
                
                lblVATPercentage = new JLabel("%");
		lblVATPercentage.setFont(fntPlainText);
		lblVATPercentage.setBounds(335, 70, 40, 30);
		pnlSystem.add(lblVATPercentage);
                
                lblCreditLimitAlert = new JLabel("Credit Limit Alert:");
		lblCreditLimitAlert.setFont(fntPlainText);
		lblCreditLimitAlert.setBounds(10, 110, 196, 30);
		pnlSystem.add(lblCreditLimitAlert);
                
                lblCreditLimitPercentage = new JLabel("%");
		lblCreditLimitPercentage.setFont(fntPlainText);
		lblCreditLimitPercentage.setBounds(335, 110, 40, 30);
		pnlSystem.add(lblCreditLimitPercentage);
                
		lblTermsAlert = new JLabel("Terms Alert:");
		lblTermsAlert.setFont(fntPlainText);
		lblTermsAlert.setBounds(55, 150, 171, 30);
		pnlSystem.add(lblTermsAlert);
                
                lblTermsDays = new JLabel("Days");
		lblTermsDays.setFont(fntPlainText);
		lblTermsDays.setBounds(335, 150, 100, 30);
		pnlSystem.add(lblTermsDays);
                
                lblSystemStatus = new JLabel("Please fill-up everything.");
		lblSystemStatus.setFont(fntPlainText);
                lblSystemStatus.setForeground(Color.RED);
		lblSystemStatus.setBounds(10, 190, 500, 30);
		pnlSystem.add(lblSystemStatus);
		
		lblAdministratorUsername = new JLabel("Username:");
		lblAdministratorUsername.setFont(fntPlainText);
		lblAdministratorUsername.setBounds(66, 30, 200, 30);
		pnlAdministrator.add(lblAdministratorUsername);
		 
		lblAdministratorPassword = new JLabel("Password:");
		lblAdministratorPassword.setFont(fntPlainText);
		lblAdministratorPassword.setBounds(70, 70, 200, 30);
		pnlAdministrator.add(lblAdministratorPassword);
		
		lblAdministratorVerifyPassword = new JLabel("Verify Password:");
		lblAdministratorVerifyPassword.setFont(fntPlainText);
		lblAdministratorVerifyPassword.setBounds(10, 110, 168, 30);
		pnlAdministrator.add(lblAdministratorVerifyPassword);
                
                lblAdministratorStatus = new JLabel("Please fill-up everything.");
		lblAdministratorStatus.setFont(fntPlainText);
                lblAdministratorStatus.setForeground(Color.RED);
		lblAdministratorStatus.setBounds(10, 150, 400, 30);
		pnlAdministrator.add(lblAdministratorStatus);
                        
		lblEmployeeUsername = new JLabel("Username:");
		lblEmployeeUsername.setFont(fntPlainText);
		lblEmployeeUsername.setBounds(66, 30, 200, 30);
		pnlEmployee.add(lblEmployeeUsername);
		
		lblEmployeePassword = new JLabel("Password:");
		lblEmployeePassword.setFont(fntPlainText);
		lblEmployeePassword.setBounds(70, 70, 200, 30);
		pnlEmployee.add(lblEmployeePassword);
		
		lblEmployeeVerifyPassword = new JLabel("Verify Password:");
		lblEmployeeVerifyPassword.setFont(fntPlainText);
		lblEmployeeVerifyPassword.setBounds(10, 110, 168, 30);
		pnlEmployee.add(lblEmployeeVerifyPassword);
                
                lblEmployeeStatus = new JLabel("Please fill-up everything.");
		lblEmployeeStatus.setFont(fntPlainText);
                lblEmployeeStatus.setForeground(Color.RED);
		lblEmployeeStatus.setBounds(10, 150, 400, 30);
		pnlEmployee.add(lblEmployeeStatus);
                
                tfSystemName = new JTextField();
		tfSystemName.setFont(fntPlainText);
		tfSystemName.setBounds(180, 30, 500, 30);
		pnlSystem.add(tfSystemName);
                tfSystemName.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        private void checkMaxNum()
                        {
                            if(tfSystemName.getText().length()>60)
                              lblSystemStatus.setText("Username can not exceed 60 characters.");
                            else if(tfSystemName.getText().isEmpty())
                              lblSystemStatus.setText("Please fill-up everything.");
                            else
                            {
                              lblSystemStatus.setText("");
                              if(changeTermsStatus())
                                    if(changeVATStatus())
                                        changeCreditLimitStatus();
                            }
                        }
                        
                });
                
                ftfVAT = new JFormattedTextField(new DecimalFormat("0"));
                ftfVAT.setFont(fntPlainText);
                ftfVAT.setHorizontalAlignment(JTextField.RIGHT);
                ftfVAT.setValue(new Float(0));
                ftfVAT.setBounds(180, 70, 150, 30);
                pnlSystem.add(ftfVAT);
                ftfVAT.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                            changeVATStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                            changeVATStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                            changeVATStatus();
                        }
                        
                 });
                
                ftfCreditLimitAlert = new JFormattedTextField(new DecimalFormat("0"));
                ftfCreditLimitAlert.setFont(fntPlainText);
                ftfCreditLimitAlert.setHorizontalAlignment(JTextField.RIGHT);
                ftfCreditLimitAlert.setValue(new Float(0));
                ftfCreditLimitAlert.setBounds(180, 110, 150, 30);
                pnlSystem.add(ftfCreditLimitAlert);
                ftfCreditLimitAlert.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                            changeCreditLimitStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          changeCreditLimitStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          changeCreditLimitStatus();
                        }
                        
                 });
                
                ftfTermsAlert = new JFormattedTextField(new DecimalFormat("0"));
                ftfTermsAlert.setFont(fntPlainText);
                ftfTermsAlert.setHorizontalAlignment(JTextField.RIGHT);
                ftfTermsAlert.setValue(new Float(0));
                ftfTermsAlert.setBounds(180, 150, 150, 30);
                pnlSystem.add(ftfTermsAlert); //does not exceed 2000
		ftfTermsAlert.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                            changeTermsStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                            changeTermsStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                            changeTermsStatus();
                        }
                        
                 });
                        
		tfAdminUsername = new JTextField();
		tfAdminUsername.setFont(fntPlainText);
		tfAdminUsername.setBounds(180, 30, 255, 30);
		pnlAdministrator.add(tfAdminUsername);
                tfAdminUsername.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        private void checkMaxNum()
                        {
                            lblAdministratorStatus.setForeground(Color.RED);
                            if(tfAdminUsername.getText().length()>45)
                              lblAdministratorStatus.setText("Username can not exceed 45 characters.");
                            else if(tfAdminUsername.getText().isEmpty()|| pfAdministratorPassword.getPassword().length==0|| pfAdministratorVerifyPassword.getPassword().length==0)
                              lblEmployeeStatus.setText("Please fill-up everything.");
                            else
                              updateAdministratorStatus();
                        }
                        
                });
                        
                pfAdministratorPassword = new JPasswordField();
                pfAdministratorPassword.setFont(fntPlainText);
		pfAdministratorPassword.setBounds(180, 70, 255, 30);
		pnlAdministrator.add(pfAdministratorPassword);
                pfAdministratorPassword.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          updateAdministratorStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          updateAdministratorStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          updateAdministratorStatus();
                        }
                        
                });
		
		pfAdministratorVerifyPassword = new JPasswordField();
                pfAdministratorVerifyPassword.setFont(fntPlainText);
		pfAdministratorVerifyPassword.setBounds(180, 110, 255, 30);
		pnlAdministrator.add(pfAdministratorVerifyPassword);
                pfAdministratorVerifyPassword.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          updateAdministratorStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          updateAdministratorStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          updateAdministratorStatus();
                        }
                        
                 });
		
		tfEmployeeUsername = new JTextField();
		tfEmployeeUsername.setFont(fntPlainText);
		tfEmployeeUsername.setBounds(180, 30, 255, 30);
		pnlEmployee.add(tfEmployeeUsername);
                tfEmployeeUsername.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          checkMaxNum();
                        }
                        private void checkMaxNum()
                        {
                            lblEmployeeStatus.setForeground(Color.RED);
                            if(tfEmployeeUsername.getText().length()>45)
                              lblEmployeeStatus.setText("Username can not exceed 45 characters.");
                            else if(tfEmployeeUsername.getText().isEmpty()|| pfEmployeePassword.getPassword().length==0|| pfEmployeeVerifyPassword.getPassword().length==0)
                              lblEmployeeStatus.setText("Please fill-up everything.");
                            else
                              updateEmployeeStatus();                              
                        }
                        
                });
                
                pfEmployeePassword = new JPasswordField();
                pfEmployeePassword.setFont(fntPlainText);
		pfEmployeePassword.setBounds(180, 70, 255, 30);
		pnlEmployee.add(pfEmployeePassword);
                pfEmployeePassword.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          updateEmployeeStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          updateEmployeeStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          updateEmployeeStatus();
                        }
                        
                 });
		
		pfEmployeeVerifyPassword = new JPasswordField();
                pfEmployeeVerifyPassword.setFont(fntPlainText);
		pfEmployeeVerifyPassword.setBounds(180, 110, 255, 30);
		pnlEmployee.add(pfEmployeeVerifyPassword);
                pfEmployeeVerifyPassword.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          updateEmployeeStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          updateEmployeeStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          updateEmployeeStatus();
                        }
                        
                 });
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(855, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            if(tfSystemName.getText().length()==0)  JOptionPane.showMessageDialog(null, "System Name is empty. Please fill-up the system name.");
                            else if(tfAdminUsername.getText().length()==0) JOptionPane.showMessageDialog(null, "Administrator username is empty. Please fill-up the administrator username.");
                            else if(tfEmployeeUsername.getText().length()==0) JOptionPane.showMessageDialog(null, "Employee username is empty. Please fill-up the employee username.");
                            else if(pfAdministratorPassword.getPassword().length==0) JOptionPane.showMessageDialog(null, "Administrator password is empty. Please fill-up the administrator password.");
                            else if(pfEmployeePassword.getPassword().length==0) JOptionPane.showMessageDialog(null, "Employee password is empty. Please fill-up the employee password.");
                            else if(tfAdminUsername.getText().toString().equals(tfEmployeeUsername.getText().toString())==true) JOptionPane.showMessageDialog(null, "Administrator username and employee username can not be the same. Please change one username.");
                            else if(!checkPassword(pfAdministratorPassword, pfAdministratorVerifyPassword)) JOptionPane.showMessageDialog(null, "<html><center>Password and verify password of administrator account does not match.<br>Please re-enter the verify password in administrator account.</center></html>");
                            else if(!checkPassword(pfEmployeePassword, pfEmployeeVerifyPassword)) JOptionPane.showMessageDialog(null, "<html><center>Password and verify password of employee account does not match.<br>Please re-enter the verify password in employee account.</center></html>");
                            else if(changeVATStatus()==false) JOptionPane.showMessageDialog(null, "<html><center>VAT percentage can not exceed 100% and can not be negative.<br>Please re-enter the VAT percentage.</center></html>");
                            else if(changeCreditLimitStatus()==false) JOptionPane.showMessageDialog(null, "<html><center>Credit limit percentage can not exceed 100% and can not be negative.<br>Please re-enter the credit limit percentage.</center></html>");
                            else if(changeTermsStatus()==false) JOptionPane.showMessageDialog(null, "<html><center>Terms can not exceed 2000 days and can not be negative.<br>Please re-enter the terms.</center></html>");
                            else
                            {
                                JOptionPane.showMessageDialog(null, "System installation successful.");
                                GUIcontroller.changePanelToLogin();
                                //put here
                                
                            }
                        }
                    });
	}
        
        public boolean changeVATStatus()
        {
            boolean status= true;
            try
            {
                if( Integer.parseInt(ftfVAT.getText())>100)
                {
                        lblSystemStatus.setText("VAT percentage can not exceed 100%.");
                        status=false;
                }else if(tfSystemName.getText().isEmpty())
                        lblSystemStatus.setText("Please fill-up everything.");
                else
                {
                    lblSystemStatus.setText("");
                    
                }

                if(ftfVAT.getText().toString().indexOf('-')==0)
                {
                    lblSystemStatus.setText("VAT percentage can not be negative.");
                    status=false;
                }
                    
            }catch(Exception e)
            {
                status=false;
            }
            return status;
       }
        
        public boolean changeCreditLimitStatus()
        {
            boolean status= true;
            try{
                if( Integer.parseInt(ftfCreditLimitAlert.getText())>100)
                {
                        lblSystemStatus.setText("Credit limit percentage can not exceed 100%.");
                        status=false;
                }
                else if(tfSystemName.getText().isEmpty())
                        lblSystemStatus.setText("Please fill-up everything.");
                else
                {
                    lblSystemStatus.setText("");
                    
                }
                  
                if(ftfCreditLimitAlert.getText().toString().indexOf('-')==0)
                {
                    lblSystemStatus.setText("Credit limit percentage can not be negative.");
                    status=false;
                }
            }catch(Exception e)
            {
                status=false;
            }
            return status;
       }
        
        public boolean changeTermsStatus()
        {
            boolean status= true;
            try
            {
                if( Integer.parseInt(ftfTermsAlert.getText())>2000)
                        lblSystemStatus.setText("Terms can not exceed 2000 days.");
                else if(tfSystemName.getText().isEmpty())
                        lblSystemStatus.setText("Please fill-up everything.");
                else
                {
                    lblSystemStatus.setText("");
                        
                }
                if(ftfTermsAlert.getText().toString().indexOf('-')==0)
                    lblSystemStatus.setText("Terms can not be negative.");
            }catch(Exception e)
            {
                status=false;
            }
            return status;
       }
       
        
        
        public boolean checkPassword(JPasswordField password, JPasswordField verifyPassword)
        {
            boolean temp= true;
          if(password.getPassword().length==verifyPassword.getPassword().length )
          {
              for(int i=0; i<verifyPassword.getPassword().length; i++)
                  if(verifyPassword.getPassword()[i]!= password.getPassword()[i] )
                  {
                      temp=false;
                      break;
                  }
          }else
              temp=false;
            
          return temp;
        }

        public void updateAdministratorStatus()
        {
            if(pfAdministratorPassword.getPassword().length<=45)
            {
                if(checkPassword(pfAdministratorPassword, pfAdministratorVerifyPassword))
                {
                    if(pfAdministratorPassword.getPassword().length==0 && pfAdministratorVerifyPassword.getPassword().length==0)
                        lblAdministratorStatus.setText("Please fill-up everything.");
                    else
                    {
                        lblAdministratorStatus.setForeground(Color.GREEN);
                        lblAdministratorStatus.setText("Passwords Match."); 
                    }
                }else
                {
                    lblAdministratorStatus.setForeground(Color.RED);
                    lblAdministratorStatus.setText("New and confirm password do not match.");
                }
            }else
            {
                lblAdministratorStatus.setText("Password can not exceed 45 characters.");
            }
        }
        
        public void updateEmployeeStatus()
        {
            if(pfEmployeePassword.getPassword().length<=45)
            {
                if(checkPassword(pfEmployeePassword, pfEmployeeVerifyPassword))
                {
                    if(pfEmployeePassword.getPassword().length==0 && pfEmployeeVerifyPassword.getPassword().length==0)
                        lblEmployeeStatus.setText("Please fill-up everything.");
                    else
                    {
                        lblEmployeeStatus.setForeground(Color.GREEN);
                        lblEmployeeStatus.setText("Passwords Match."); 
                    }
                }else
                {
                    lblEmployeeStatus.setForeground(Color.RED);
                    lblEmployeeStatus.setText("New and confirm password do not match.");
                }
            }else
            {
                lblEmployeeStatus.setText("Password can not exceed 45 characters.");
            }
            
        }
        
        public static void main(String args[])
        {
            GUIController temp=new GUIController();
            temp.changePanelToBasicSystemSettings();
        }
}

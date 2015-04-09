package SystemAccount;

import HailHydra.GUIController;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ModifyPasswordGUI extends JPanel {

	private JLabel lblHeader, lblUserType, lblCurrentPassword,
                lblNewPassword, lblConfirmPassword, lblStatus;
	private JPasswordField pfCurrPass, pfNewPass, pfConfirmPassword;
        private JRadioButton rdbtnAdministrator, rdbtnEmployee;
        private ButtonGroup searchBy;
	private JButton btnSubmit, btnCancel;
        private Font fntPlainText, fntHeaderText;
	private GUIController controller;
        private String type;
        private SystemAccountController mainController;
	

	public ModifyPasswordGUI(GUIController temp) 
        {
		controller = temp;
                
		fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);

                setVisible(true);
                setLayout(null);

                type= "Administrator";
                
		lblHeader = new JLabel("Modify Password");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

                lblUserType = new JLabel("User Type:");
		lblUserType.setFont(fntPlainText);
		lblUserType.setBounds(30, 90, 200, 30);
		add(lblUserType);

		lblCurrentPassword = new JLabel("Current Password:");
		lblCurrentPassword.setFont(fntPlainText);
		lblCurrentPassword.setBounds(30, 150, 200, 30);
		add(lblCurrentPassword);

		lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(fntPlainText);
		lblNewPassword.setBounds(30, 210, 200, 30);
		add(lblNewPassword);

		lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(fntPlainText);
		lblConfirmPassword.setBounds(30, 270, 200, 30);
		add(lblConfirmPassword);
                
                lblStatus = new JLabel("");
                lblStatus.setFont(fntPlainText);
		lblStatus.setBounds(220, 300, 400, 30);
		add(lblStatus);

		pfCurrPass = new JPasswordField();
                pfCurrPass.setFont(fntPlainText);
		pfCurrPass.setBounds(220, 150, 395, 30);
		add(pfCurrPass);

		pfNewPass = new JPasswordField();
                pfNewPass.setFont(fntPlainText);
		pfNewPass.setBounds(220, 210, 395, 30);
		add(pfNewPass);
                pfNewPass.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          updateStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          updateStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          updateStatus();
                        }
                        
                      });
                
                
		pfConfirmPassword = new JPasswordField();
                pfConfirmPassword.setFont(fntPlainText);
                pfConfirmPassword.setToolTipText("Enter the new password again for confirmation.");
		pfConfirmPassword.setBounds(220, 270, 395, 30);
		add(pfConfirmPassword);
                pfConfirmPassword.getDocument().addDocumentListener(new DocumentListener() 
                {
                        public void changedUpdate(DocumentEvent documentEvent) 
                        {
                          updateStatus();
                        }
                        public void insertUpdate(DocumentEvent documentEvent) 
                        {
                          updateStatus();
                        }
                        public void removeUpdate(DocumentEvent documentEvent) 
                        {
                          updateStatus();
                        }
                        
                      });

                rdbtnAdministrator = new JRadioButton("Administrator");
		rdbtnAdministrator.setFont(fntPlainText);
		rdbtnAdministrator.setSelected(true);
		rdbtnAdministrator.setBounds(220, 90, 158, 30);
		add(rdbtnAdministrator);
                rdbtnAdministrator.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        type="Administrator";
                    }
                });
                
		rdbtnEmployee = new JRadioButton("Employee");
		rdbtnEmployee.setFont(fntPlainText);
		rdbtnEmployee.setBounds(400, 90, 158, 30);
		add(rdbtnEmployee);
                rdbtnEmployee.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        type="Employee";
                    }
                });

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnAdministrator);
		searchBy.add(rdbtnEmployee);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(370, 350, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            boolean success=true;
                            if(checkNewPassword()==false){
                                JOptionPane temp= new JOptionPane("New and confirm password do not match.\n Please re-input new password.", JOptionPane.INFORMATION_MESSAGE);
                                JDialog dialog = temp.createDialog("New Password Mismatch");
                                dialog.setAlwaysOnTop(true);
                                dialog.setModal(true);
                                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);      
                                dialog.setVisible(true);
                                pfNewPass.setText("");
                                pfConfirmPassword.setText("");
                                success=false;
                                
                            }
                            
                            System.out.println(mainController.checkOldPassword());
                            if(mainController.checkOldPassword())
                            {
                                if(success==true){
                                    String password= new String(pfNewPass.getPassword());
                                    if(rdbtnEmployee.isSelected())
                                       mainController.changePassword(1,password);
                                    else if(rdbtnAdministrator.isSelected())
                                        mainController.changePassword(0,password);
                                    JOptionPane temp= new JOptionPane(""+type+" password successfully changed.", JOptionPane.INFORMATION_MESSAGE);
                                    JDialog dialog = temp.createDialog("Password Changed");
                                    dialog.setAlwaysOnTop(true);
                                    dialog.setModal(true);
                                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);      
                                    dialog.setVisible(true);
                                    controller.changePanelToMainMenu();
                                }
                            }
                            else
                            {
                                    JOptionPane temp= new JOptionPane("Current Password is Incorrect", JOptionPane.INFORMATION_MESSAGE);
                                    JDialog dialog = temp.createDialog("Incorect Current Password");
                                    dialog.setAlwaysOnTop(true);
                                    dialog.setModal(true);
                                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);      
                                    dialog.setVisible(true);
                            }
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(505, 350, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                JOptionPane temp= new JOptionPane("Password not changed.", JOptionPane.INFORMATION_MESSAGE);
                                JDialog dialog = temp.createDialog("Cancel");
                                dialog.setAlwaysOnTop(true);
                                dialog.setModal(true);
                                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);      
                                dialog.setVisible(true);
				controller.changePanelToMainMenu();
			}
		});

	}
        
        public boolean checkNewPassword()
        {
            boolean temp= true;
          if(pfConfirmPassword.getPassword().length==pfNewPass.getPassword().length )
          {
              for(int i=0; i<pfNewPass.getPassword().length; i++)
                  if(pfNewPass.getPassword()[i]!= pfConfirmPassword.getPassword()[i] )
                  {
                      temp=false;
                      break;
                  }
          }else
              temp=false;
            
          return temp;
        }

        public void updateStatus()
        {
            
            if(checkNewPassword())
            {
                if(pfNewPass.getPassword().length==0 && pfConfirmPassword.getPassword().length==0)
                    lblStatus.setText("");
                else
                {
                    lblStatus.setForeground(Color.GREEN);
                    lblStatus.setText("Passwords Match."); 
                }
            }else
            {
            lblStatus.setForeground(Color.RED);
            lblStatus.setText("New and confirm password do not match.");
            }
        }
        
        public boolean checkOldPassword()
        {
            String password = new String(pfCurrPass.getPassword());
            if(rdbtnEmployee.isSelected())
            {
                if(mainController.getPassword(1).equalsIgnoreCase(password))
                    return true;
            }
            else if(rdbtnAdministrator.isSelected())
            {
                
                if(mainController.getPassword(0).equalsIgnoreCase(password))
                    return true;
            }
                    
            return false;
        }
        
        public void setMainController(SystemAccountController temp){
            mainController=temp;
        }
        
        public static void main(String[] args){
            GUIController a= new GUIController();
           a.changePanelToModifyPassword();
        }
}

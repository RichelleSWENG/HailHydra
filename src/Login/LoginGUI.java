package Login;

import HailHydra.GUIController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class LoginGUI extends JPanel {
    
        private JLabel lblHeader, lblUsername, lblPassword;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JButton btnLogin;
        private Font fntPlainText, fntHeaderText;
        private GUIController controllerGUI;
        private LoginController mainController;

	public LoginGUI(GUIController temp) {
                controllerGUI=temp;
                setBounds(0, 0, 1000, 620);
                setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText=new Font("Arial", Font.BOLD, 40);
                
                lblHeader = new JLabel("Log-in");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(460, 130, 700, 86);
		add(lblHeader);
                
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(fntPlainText);
		lblUsername.setBounds(300, 220, 250, 30);
		add(lblUsername);
		
                tfUsername = new JTextField();
		tfUsername.setBounds(410, 220, 300, 30);
                tfUsername.setFont(fntPlainText);
                add(tfUsername);
                
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(fntPlainText);
		lblPassword.setBounds(300, 265, 250, 30);
		add(lblPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(410, 265, 300, 30);
                pfPassword.setFont(fntPlainText);
		add(pfPassword);
                pfPassword.addKeyListener(new KeyAdapter() 
                { 
                public void keyReleased(KeyEvent e) 
                {

                }

                public void keyTyped(KeyEvent e) 
                {

                }

                public void keyPressed(KeyEvent e) 
                {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        btnLogin.doClick();
                     }
                }
                });
                
		btnLogin = new JButton("Login");
                btnLogin.setFont(fntPlainText);
		btnLogin.setBounds(470, 320, 100, 40);
		add(btnLogin);
                btnLogin.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                            String password = new String(pfPassword.getPassword());
                            if(!tfUsername.getText().equals("")|| !password.equalsIgnoreCase(""))
                            {   
                                    if(mainController.validate(tfUsername.getText(), password)!=null)
                                    {
                                        if(mainController.validate(tfUsername.getText(), password).equals("1"))
                                            controllerGUI.setToEmployee();
                                        
                                        
                                            controllerGUI.changePanelToMainMenu();
                                            controllerGUI.changePanelToProfiles();
                                    }
                                    else
                                        JOptionPane.showMessageDialog(null, "Wrong username or password");
                            }else
                            {
                                JOptionPane.showMessageDialog(null, "Please fill up all fields.");
                            }
                                
                            
                       
                        }
                    });
		
		
	}
        
        public void setMainController(LoginController cont)
        {
            mainController=cont;
        }
        
        public static void main(String args[]){
            new GUIController();
        }
        
        
}

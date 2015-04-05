package SystemAccount;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import HailHydra.GUIController;
import Payables.PayablesController;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ModifySystemProfileGUI extends JPanel {

	private JLabel lblHeader, lblSystemName, lblSystemAddress;
        private JTextField tfSystemName;
	private JTextArea taAddress;
	private JButton btnSubmit, btnCancel;
        private JScrollPane spAddress;
        private Font fntPlainText, fntHeaderText;
	private GUIController controller;
        private SystemAccountController mainController;
	

	public ModifySystemProfileGUI(GUIController temp) 
        {  
		controller = temp;
                
		fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);

                setVisible(true);
                setLayout(null);
    
		lblHeader = new JLabel("Modify System Profile");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSystemName = new JLabel("System Name:");
		lblSystemName.setFont(fntPlainText);
		lblSystemName.setBounds(30, 100, 207, 30);
		add(lblSystemName);

		lblSystemAddress = new JLabel("System Address:");
		lblSystemAddress.setFont(fntPlainText);
		lblSystemAddress.setBounds(30, 170, 229, 30);
		add(lblSystemAddress);

		tfSystemName = new JTextField();
                tfSystemName.setFont(fntPlainText);
		tfSystemName.setBounds(200, 100, 415, 30);
		add(tfSystemName);
		
		taAddress = new JTextArea();
                taAddress.setFont(fntPlainText);
                taAddress.setWrapStyleWord(true);
                taAddress.setLineWrap(true);
		taAddress.setBounds(200, 170, 415, 150);
		add(taAddress);
                
                spAddress = new JScrollPane(taAddress);
                spAddress.setBounds(200, 170, 415, 150);
                add(spAddress);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(370, 350, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                mainController.changeInfo(tfSystemName.getText(),taAddress.getText());
				controller.changePanelToMainMenu();
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(505, 350, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changePanelToMainMenu();
                                
			}
		});
                
	}
        
        public void setMainController(SystemAccountController temp){
            mainController=temp;
        }
        
        public void setDetails()
        {
            taAddress.setText(mainController.getCompanyAdress());
            tfSystemName.setText(mainController.getCompanyName());
        }
        public static void main(String args[])
        {
            GUIController a= new GUIController();
            a.changePanelToModifySystemProfile();
            
        }
}

package SystemAccount;

import HailHydra.GUIController;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ModifyCompanyProfilePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel lblHeader, lblCompanyName, lblCompanyAddress;
	private JTextArea taAddress;
	private JTextField tfCompanyName;
	private JButton btnSubmit, btnCancel;
	private JScrollPane spAddress;
        private GUIController controller;
	
	public ModifyCompanyProfilePanel(GUIController temp) {
            
                controller=temp;
		setLayout(null);
		lblHeader = new JLabel("Modify Password");
		lblHeader.setFont(new Font("Arial", Font.BOLD, 40));
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
		
		lblCompanyName = new JLabel("New Company Name: ");
		lblCompanyName.setFont(new Font("Arial", Font.PLAIN, 21));
		lblCompanyName.setBounds(30, 128, 207, 50);
		add(lblCompanyName);
		
		lblCompanyAddress = new JLabel("New Company Address: ");
		lblCompanyAddress.setFont(new Font("Arial", Font.PLAIN, 21));
		lblCompanyAddress.setBounds(30, 189, 229, 50);
		add(lblCompanyAddress);
		
		tfCompanyName = new JTextField();
		tfCompanyName.setBounds(269, 142, 365, 30);
		add(tfCompanyName);
		tfCompanyName.setColumns(10);
		
		btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(655, 400, 110, 40);
        add(btnSubmit);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(830, 400, 110, 40);
        add(btnCancel);
        
        spAddress = new JScrollPane();
        spAddress.setBounds(269, 206, 365, 98);
        spAddress.
        add(spAddress);
        
        taAddress = new JTextArea();
        spAddress.setViewportView(taAddress);
	}
}

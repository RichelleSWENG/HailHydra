package SystemAccount;

import HailHydra.GUIController;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class ModifyPasswordPanel extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */
	private JLabel lblHeader;
	private JTextField tfUsername;
	private JPasswordField pfCurrPass;
	private JPasswordField pfNewPass;
	private JPasswordField pfConfPass;
	private JButton btnSubmit, btnCancel;
	private GUIController controller;
	private Font fntPlainText;

	public ModifyPasswordPanel(GUIController temp) {
		controller = temp;
		fntPlainText=new Font("Arial", Font.PLAIN, 21);

		setBounds(0, 0, 1000, 620);
		setLayout(null);

		lblHeader = new JLabel("Modify Password");
		lblHeader.setFont(new Font("Arial", Font.BOLD, 40));
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		JLabel lblCurrentUsername = new JLabel("Current Username:");
		lblCurrentUsername.setFont(new Font("Arial", Font.PLAIN, 21));
		lblCurrentUsername.setBounds(30, 134, 200, 50);
		add(lblCurrentUsername);

		JLabel lblCurrentPassword = new JLabel("Current Password:");
		lblCurrentPassword.setFont(new Font("Arial", Font.PLAIN, 21));
		lblCurrentPassword.setBounds(30, 195, 200, 50);
		add(lblCurrentPassword);

		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 21));
		lblNewPassword.setBounds(30, 256, 200, 50);
		add(lblNewPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 21));
		lblConfirmPassword.setBounds(30, 317, 200, 50);
		add(lblConfirmPassword);

		tfUsername = new JTextField();
		tfUsername.setBounds(240, 148, 253, 30);
		add(tfUsername);
		tfUsername.setColumns(10);

		pfCurrPass = new JPasswordField();
		pfCurrPass.setBounds(240, 209, 253, 30);
		add(pfCurrPass);

		pfNewPass = new JPasswordField();
		pfNewPass.setBounds(240, 270, 253, 30);
		add(pfNewPass);

		pfConfPass = new JPasswordField();
		pfConfPass.setBounds(240, 331, 253, 30);
		add(pfConfPass);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//submit
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changePanelToMainMenu();
			}
		});

	}
}

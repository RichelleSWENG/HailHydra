import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;


public class BasicSystemSettingsGUI extends JPanel {
	private JTextField tfCompanyName;
	private JTextField tfAdminUsername;
	private JPasswordField pfAdminPassword;
	private JPasswordField pfAdminVerifyPassword;
	private JTextField tfEmployeeUsername;
	private JPasswordField pfEmployeePassword;
	private JPasswordField pfEmployeeVerifyPassword;
	private JTextField tfVAT;
	private JTextField textField_1;
	private JTextField tfCreditLimitAlert;

	/**
	 * Create the panel.
	 */
	public BasicSystemSettingsGUI() {
		setBounds(0, 0, 700, 400);
		setLayout(null);
		
		JLabel lblHeader = new JLabel("Basic System Settings");
		lblHeader.setFont(new Font("Arial", Font.BOLD, 40));
		lblHeader.setBounds(20, 11, 600, 69);
		add(lblHeader);
		
		JLabel lblCompanyName = new JLabel("Company Name:");
		lblCompanyName.setFont(new Font("Arial", Font.PLAIN, 21));
		lblCompanyName.setBounds(20, 91, 154, 30);
		add(lblCompanyName);
		
		tfCompanyName = new JTextField();
		tfCompanyName.setFont(new Font("Arial", Font.PLAIN, 21));
		tfCompanyName.setBounds(184, 91, 230, 30);
		add(tfCompanyName);
		
		JLabel lblAdmin = new JLabel("Admin Username:");
		lblAdmin.setFont(new Font("Arial", Font.PLAIN, 21));
		lblAdmin.setBounds(20, 132, 168, 30);
		add(lblAdmin);
		
		tfAdminUsername = new JTextField();
		tfAdminUsername.setFont(new Font("Arial", Font.PLAIN, 21));
		tfAdminUsername.setBounds(184, 132, 230, 30);
		add(tfAdminUsername);
		
		JLabel lblAdminPassword = new JLabel("Admin Password:");
		lblAdminPassword.setFont(new Font("Arial", Font.PLAIN, 21));
		lblAdminPassword.setBounds(20, 173, 168, 30);
		add(lblAdminPassword);
		
		JLabel lblVerifyPassword = new JLabel("Verify Password:");
		lblVerifyPassword.setFont(new Font("Arial", Font.PLAIN, 21));
		lblVerifyPassword.setBounds(20, 214, 168, 30);
		add(lblVerifyPassword);
		
		pfAdminPassword = new JPasswordField();
		pfAdminPassword.setBounds(184, 173, 230, 30);
		add(pfAdminPassword);
		
		pfAdminVerifyPassword = new JPasswordField();
		pfAdminVerifyPassword.setBounds(184, 214, 230, 30);
		add(pfAdminVerifyPassword);
		
		JLabel lblEmployeeUsername = new JLabel("Employee Username:");
		lblEmployeeUsername.setFont(new Font("Arial", Font.PLAIN, 21));
		lblEmployeeUsername.setBounds(20, 256, 199, 30);
		add(lblEmployeeUsername);
		
		tfEmployeeUsername = new JTextField();
		tfEmployeeUsername.setFont(new Font("Arial", Font.PLAIN, 21));
		tfEmployeeUsername.setBounds(229, 255, 185, 30);
		add(tfEmployeeUsername);
		
		JLabel lblEmployeePassword = new JLabel("Employee Password:");
		lblEmployeePassword.setFont(new Font("Arial", Font.PLAIN, 21));
		lblEmployeePassword.setBounds(20, 297, 199, 30);
		add(lblEmployeePassword);
		
		JLabel lblVerifyPassword_1 = new JLabel("Verify Password:");
		lblVerifyPassword_1.setFont(new Font("Arial", Font.PLAIN, 21));
		lblVerifyPassword_1.setBounds(20, 338, 168, 30);
		add(lblVerifyPassword_1);
		
		pfEmployeePassword = new JPasswordField();
		pfEmployeePassword.setBounds(229, 296, 185, 30);
		add(pfEmployeePassword);
		
		pfEmployeeVerifyPassword = new JPasswordField();
		pfEmployeeVerifyPassword.setBounds(229, 338, 185, 30);
		add(pfEmployeeVerifyPassword);
		
		JLabel lblVat = new JLabel("VAT (%):");
		lblVat.setFont(new Font("Arial", Font.PLAIN, 21));
		lblVat.setBounds(424, 91, 88, 30);
		add(lblVat);
		
		tfVAT = new JTextField();
		tfVAT.setFont(new Font("Arial", Font.PLAIN, 21));
		tfVAT.setBounds(612, 91, 79, 30);
		add(tfVAT);
		
		JLabel lblTermsAlertdays = new JLabel("Terms Alert(days):");
		lblTermsAlertdays.setFont(new Font("Arial", Font.PLAIN, 21));
		lblTermsAlertdays.setBounds(418, 132, 171, 30);
		add(lblTermsAlertdays);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 21));
		textField_1.setBounds(612, 132, 79, 30);
		add(textField_1);
		
		JLabel lblCreditLimitAlert = new JLabel("Credit Limit Alert(%):");
		lblCreditLimitAlert.setFont(new Font("Arial", Font.PLAIN, 21));
		lblCreditLimitAlert.setBounds(418, 173, 196, 30);
		add(lblCreditLimitAlert);
		
		tfCreditLimitAlert = new JTextField();
		tfCreditLimitAlert.setFont(new Font("Arial", Font.PLAIN, 21));
		tfCreditLimitAlert.setBounds(612, 173, 78, 30);
		add(tfCreditLimitAlert);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSubmit.setBounds(435, 338, 110, 40);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 21));
		btnCancel.setBounds(581, 338, 110, 40);
		add(btnCancel);
	}
}

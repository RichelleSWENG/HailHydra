package Login;

import javax.swing.JPanel;
import javax.swing.JButton;

import HailHydra.GUIController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemSettingsGUI extends JPanel
{

	private JButton btnModifyPassword, btnModifySystemProfile, btnModifyVAT,
			btnModifyCreditLimitAlert, btnModifyTermsAlert;
	private Font fntPlainText;
	private GUIController controller;

	public SystemSettingsGUI(GUIController temp)
	{
		controller = temp;

		setBounds(5, 5, 580, 390);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);

		btnModifyPassword = new JButton("Modify Password");
		btnModifyPassword.setFont(fntPlainText);
		btnModifyPassword.setBounds(130, 50, 330, 40);
		add(btnModifyPassword);
		btnModifyPassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToModifyPassword();
			}
		});

		btnModifySystemProfile = new JButton("Modify System Profile");
		btnModifySystemProfile.setFont(fntPlainText);
		btnModifySystemProfile.setBounds(130, 110, 330, 40);
		add(btnModifySystemProfile);
		btnModifySystemProfile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToModifySystemProfile();
			}
		});

		btnModifyVAT = new JButton("Modify VAT");
		btnModifyVAT.setFont(fntPlainText);
		btnModifyVAT.setBounds(130, 170, 330, 40);
		add(btnModifyVAT);
		btnModifyVAT.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.getAlert("VAT");
			}
		});

		btnModifyCreditLimitAlert = new JButton("Modify Credit Limit Alert");
		btnModifyCreditLimitAlert.setFont(fntPlainText);
		btnModifyCreditLimitAlert.setBounds(130, 230, 330, 40);
		add(btnModifyCreditLimitAlert);
		btnModifyCreditLimitAlert.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.getAlert("CreditLimit");
			}
		});

		btnModifyTermsAlert = new JButton("Modify Terms Alert");
		btnModifyTermsAlert.setFont(fntPlainText);
		btnModifyTermsAlert.setBounds(130, 290, 330, 40);
		add(btnModifyTermsAlert);
		btnModifyTermsAlert.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.getAlert("Terms");
			}
		});

	}
}

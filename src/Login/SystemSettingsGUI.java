package Login;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JButton;

import HailHydra.GUIController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemSettingsGUI extends JPanel {

	private JButton adminAccBtn, empAccBtn, companyProfileBtn, vatBtn,
			credLimAlertBtn, termsAlertBtn;
	private Font fntPlainText;
	private GUIController controller;

	public SystemSettingsGUI(GUIController temp) {
		controller=temp;
		setBounds(0, 0, 700, 400);
		setBackground(SystemColor.controlHighlight);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);

		adminAccBtn = new JButton("Modify Administrator Account");
		adminAccBtn.setFont(fntPlainText);
		adminAccBtn.setBounds(130, 45, 330, 40);
		add(adminAccBtn);

		empAccBtn = new JButton("Modify Employee Account");
		empAccBtn.setFont(fntPlainText);
		empAccBtn.setBounds(130, 105, 330, 40);
		add(empAccBtn);

		companyProfileBtn = new JButton("Modify Company Profile");
		companyProfileBtn.setFont(fntPlainText);
		companyProfileBtn.setBounds(130, 165, 330, 40);
		add(companyProfileBtn);

		vatBtn = new JButton("Modify VAT");
		vatBtn.setFont(fntPlainText);
		vatBtn.setBounds(130, 225, 330, 40);
		add(vatBtn);
		vatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getAlert("VAT");
			}
		});

		credLimAlertBtn = new JButton("Modify Credit Limit Alert");
		credLimAlertBtn.setFont(fntPlainText);
		credLimAlertBtn.setBounds(130, 285, 330, 40);
		add(credLimAlertBtn);
		credLimAlertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getAlert("CreditLimit");
			}
		});

		termsAlertBtn = new JButton("Modify Terms Alert");
		termsAlertBtn.setFont(fntPlainText);
		termsAlertBtn.setBounds(130, 345, 330, 40);
		add(termsAlertBtn);
		termsAlertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getAlert("Terms");
			}
		});

	}
}

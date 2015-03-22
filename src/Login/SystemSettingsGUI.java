package Login;

import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;

public class SystemSettingsGUI extends JPanel {
	
	private JButton adminAccBtn, empAccBtn, companyProfileBtn, vatBtn, credLimAlertBtn, termAlertBtn;
        private Font fntPlainText;
        
	public SystemSettingsGUI() 
	{
		setBounds(0, 0, 700, 400);
		setBackground(SystemColor.controlHighlight);
		setLayout(null);
		
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
		
		adminAccBtn = new JButton("Modify Administrator Account");
		adminAccBtn.setFont(fntPlainText);
		adminAccBtn.setBounds(130, 70, 330, 40);
		add(adminAccBtn);
		
		empAccBtn = new JButton("Modify Employee Account");
		empAccBtn.setFont(fntPlainText);
		empAccBtn.setBounds(140, 130, 300, 40);
		add(empAccBtn);
		
		companyProfileBtn = new JButton("Modify Company Profile");
		companyProfileBtn.setFont(fntPlainText);
		companyProfileBtn.setBounds(140, 190, 300, 40);
		add(companyProfileBtn);
		
		vatBtn = new JButton("Modify VAT");
		vatBtn.setFont(fntPlainText);
		vatBtn.setBounds(190, 250, 200, 40);
		add(vatBtn);
		
		credLimAlertBtn = new JButton("Modify Alert");
		credLimAlertBtn.setFont(fntPlainText);
		credLimAlertBtn.setBounds(190, 310, 200, 40);
		add(credLimAlertBtn);
		
	}
}

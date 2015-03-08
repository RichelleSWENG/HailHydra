package Login;


import HailHydra.GUIModel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JPanel {

	private JLabel lblHeader, lblNotifications;
	private JPanel pnlNotifications, pnlSections;
	private JButton btnLogout, btnProfiles, btnPurchases, btnSales, btnPayments,
			systemSettingsBtn;
        private Font fntHeaderText, fntPlainText, fntMarkerText;
        private GUIModel controller;
        
	public MainMenuGUI(GUIModel temp) 
        {
            
                controller=temp;
		try 
                {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) 
                {
			e.printStackTrace();
		}
                
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);

                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntMarkerText = new Font("Arial", Font.BOLD, 30);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                
		lblHeader = new JLabel("Main Menu");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblNotifications = new JLabel("Notifications:");
		lblNotifications.setFont(fntMarkerText);
		lblNotifications.setBounds(30, 100, 200, 27);
		add(lblNotifications);

		pnlNotifications = new JPanel();
		pnlNotifications.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		pnlNotifications.setBounds(30, 127, 300, 450);
		add(pnlNotifications);

		pnlSections = new JPanel();
		pnlSections.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		pnlSections.setBounds(370, 177, 590, 400);
		add(pnlSections);
		pnlSections.setLayout(null);

		btnLogout = new JButton("Log-out");
		btnLogout.setFont(fntPlainText);
		btnLogout.setBounds(850, 30, 113, 40);
		add(btnLogout);
                btnLogout.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToLogin();
                        }
                    });

		btnProfiles = new JButton("Profiles");
		btnProfiles.setFont(fntPlainText);
		btnProfiles.setBackground(SystemColor.activeCaption);
		btnProfiles.setForeground(Color.BLACK);
		btnProfiles.setBounds(370, 97, 110, 82);
		add(btnProfiles);
                btnProfiles.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                controller.changePanelToProfiles();
                        }});
                btnProfiles.setSelected(true);

		btnPurchases = new JButton("Purchases");
		btnPurchases.setFont(fntPlainText);
		btnPurchases.setBackground(SystemColor.activeCaption);
		btnPurchases.setForeground(Color.BLACK);
		btnPurchases.setBounds(480, 97, 140, 82);
		add(btnPurchases);
                btnPurchases.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                controller.changePanelToPurchases();
                        }});
               
		btnSales = new JButton("Sales");
		btnSales.setFont(fntPlainText);
		btnSales.setBackground(SystemColor.activeCaption);
		btnSales.setForeground(Color.BLACK);
		btnSales.setBounds(620, 97, 100, 82);
		add(btnSales);
                btnSales.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                controller.changePanelToSales();
                        }});
                
		btnPayments = new JButton("Payments");
		btnPayments.setFont(fntPlainText);
		btnPayments.setBackground(SystemColor.activeCaption);
		btnPayments.setForeground(Color.BLACK);
		btnPayments.setBounds(720, 97, 130, 82);
		add(btnPayments);
                btnPayments.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                controller.changePanelToPayments();
                        }});

		systemSettingsBtn = new JButton("<html>System<br />Settings</html>");
		systemSettingsBtn.setFont(fntPlainText);
		systemSettingsBtn.setBackground(SystemColor.activeCaption);
		systemSettingsBtn.setForeground(Color.BLACK);
		systemSettingsBtn.setBounds(850, 97, 110, 82);
		add(systemSettingsBtn);
                systemSettingsBtn.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                controller.changePanelToSystemSettings();
                        }});
	}

        public JPanel getSectionsPanel()
        {
            return pnlSections;
        }
        
	public static void main(String args[])
        {
            GUIModel temp=new GUIModel();
            temp.changePanelToMainMenu();
        }
       
}

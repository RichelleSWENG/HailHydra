package Login;

import HailHydra.GUIController;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainMenuGUI extends JPanel
{

	private JLabel lblHeader, lblNotifications;
	private JPanel pnlNotifications, pnlSections;
	private JScrollPane spNotifications;
	private JButton btnLogout, btnProfiles, btnPurchases, btnSales,
			btnPayments, systemSettingsBtn;
	private Font fntHeaderText, fntPlainText, fntMarkerText;
	private GUIController GUIcontroller;
	private LoginController mainController;

	public MainMenuGUI(GUIController temp)
	{

		GUIcontroller = temp;
		try
		{
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		setBounds(0, 0, 1000, 620);
		setLayout(null);

		GUIcontroller.setTitle();

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
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
		pnlNotifications.setLayout(new BoxLayout(pnlNotifications,
				BoxLayout.PAGE_AXIS));
		pnlNotifications.setBounds(30, 127, 300, 450);
		add(pnlNotifications);

		spNotifications = new JScrollPane(pnlNotifications);
		spNotifications
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spNotifications.setBounds(30, 127, 300, 450);
		add(spNotifications);

		pnlSections = new JPanel();
		pnlSections.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		pnlSections.setBounds(370, 177, 590, 400);
		add(pnlSections);
		pnlSections.setLayout(null);

		btnLogout = new JButton("Log-out");
		btnLogout.setFont(fntPlainText);
		btnLogout.setBounds(850, 30, 113, 40);
		add(btnLogout);
		btnLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIcontroller.changePanelToLogin();
			}
		});

		btnProfiles = new JButton("Profiles");
		btnProfiles.setFont(fntPlainText);
		btnProfiles.setBounds(370, 97, 110, 82);
		add(btnProfiles);
		btnProfiles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIcontroller.changePanelToProfiles();
			}
		});
		btnProfiles.setSelected(true);

		btnPurchases = new JButton("Purchases");
		btnPurchases.setFont(fntPlainText);
		btnPurchases.setBounds(480, 97, 140, 82);
		add(btnPurchases);
		btnPurchases.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIcontroller.changePanelToPurchases();
			}
		});

		btnSales = new JButton("Sales");
		btnSales.setFont(fntPlainText);
		btnSales.setBounds(620, 97, 100, 82);
		add(btnSales);
		btnSales.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIcontroller.changePanelToSales();
			}
		});

		btnPayments = new JButton("Payments");
		btnPayments.setFont(fntPlainText);
		btnPayments.setBounds(720, 97, 130, 82);
		add(btnPayments);
		btnPayments.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIcontroller.changePanelToPayments();
			}
		});

		systemSettingsBtn = new JButton("<html>System<br />Settings</html>");
		systemSettingsBtn.setFont(fntPlainText);
		systemSettingsBtn.setBounds(850, 97, 110, 82);
		add(systemSettingsBtn);
		systemSettingsBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIcontroller.changePanelToSystemSettings();
			}
		});

	}

	public JPanel getSectionsPanel()
	{
		return pnlSections;
	}

	public void getNotifs()
	{
		ArrayList<Notification> notifs = mainController.getNotifs();
		pnlNotifications.removeAll();
		JPanel feedPanel = new JPanel();
		JLabel lblInfo = new JLabel();
		for (int i = 0; i < notifs.size(); i++)
		{
			feedPanel = new JPanel();
			feedPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

			JLabel lblNews = new JLabel("<html><center> <b>"
					+ notifs.get(i).getType() + " Alert:</b> <br> "
					+ notifs.get(i).getDescription() + "<br></center></html>");
			lblNews.setFont(fntPlainText);
			lblNews.setPreferredSize(new Dimension(240, 150));
			feedPanel.add(lblNews);
			feedPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
					Color.BLACK));

			pnlNotifications.add(feedPanel);
		}

		pnlNotifications.revalidate();

	}

	public void setMainController(LoginController mainController)
	{
		this.mainController = mainController;
		getNotifs();
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToMainMenu();
	}

}

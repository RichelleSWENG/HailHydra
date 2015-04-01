package SystemAccount;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import HailHydra.GUIController;

public class ModifyCompanyProfilePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel lblHeader, lblCompanyName, lblCompanyAddress;
	private JTextArea taAddress;
	private JTextField tfCompanyName;
	private JButton btnSubmit, btnCancel;
	private GUIController controller;
	private Font fntPlainText;

	public ModifyCompanyProfilePanel(GUIController temp) {

		controller = temp;
		fntPlainText=new Font("Arial", Font.PLAIN, 21);

		setBounds(0, 0, 1000, 620);
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
		
		taAddress = new JTextArea();
		taAddress.setBounds(30, 246, 600, 230);
		add(taAddress);

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

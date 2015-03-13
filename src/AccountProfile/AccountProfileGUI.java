package AccountProfile;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AccountProfileGUI extends JPanel{
     protected JLabel  lblHeader, lblType, lblName, lblAddress, lblCity, 
               lblPostCode, lblCountry, lblCreditLimit, lblTermsDays, 
               lblRequiredFields, lblPhone1, lblPhone2, lblPhone3, lblFaxNumber,
               lblEmailAddress, lblWebsite, lblContactPerson, 
               lblAsterisk1, lblAsterisk2, lblAsterisk3, lblAsterisk4, 
               lblAsterisk5;
        protected JTextField tfName, tfCity, tfPostCode, tfCountry, tfPhone1, 
                  tfPhone2, tfPhone3, tfFaxNumber, tfEmailAddress, tfWebsite, tfContactPerson;
        protected JFormattedTextField ftfCreditLimit, ftfTerms;
        protected JTextArea taAddress;
        protected JScrollPane spAddress;
	protected JCheckBox chckbxInactiveAccount;
	protected JRadioButton rdbtnRetailCustomer, rdbtnSisterCompanyCustomer,
		rdbtnWalkinCustomer, rdbtnSupplier;
	protected ButtonGroup type;
        protected Font fntPlainText, fntHeaderText, fntHeaderTableText;
    
        public AccountProfileGUI(){
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);
                
                lblHeader = new JLabel("");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86); 
		add(lblHeader);

		lblType = new JLabel("Type:");
		lblType.setFont(fntPlainText);
		lblType.setBounds(30, 115, 74, 30);
		add(lblType);

		lblName = new JLabel("Name:");
		lblName.setFont(fntPlainText);
		lblName.setBounds(30, 160, 74, 30);
		add(lblName);

		lblAddress = new JLabel("Address:");
		lblAddress.setFont(fntPlainText);
		lblAddress.setBounds(30, 205, 103, 30);
		add(lblAddress);

		lblCity = new JLabel("City:");
		lblCity.setFont(fntPlainText);
		lblCity.setBounds(30, 310, 52, 30);
		add(lblCity);

		lblPostCode = new JLabel("Post Code:");
		lblPostCode.setFont(fntPlainText);
		lblPostCode.setBounds(30, 355, 122, 30);
		add(lblPostCode);

		lblCountry = new JLabel("Country:");
		lblCountry.setFont(fntPlainText);
		lblCountry.setBounds(30, 400, 93, 30);
		add(lblCountry);

		lblCreditLimit = new JLabel("Credit Limit:");
		lblCreditLimit.setFont(fntPlainText);
		lblCreditLimit.setBounds(30, 445, 133, 30);
		add(lblCreditLimit);

		lblTermsDays = new JLabel("Terms (Days):");
		lblTermsDays.setFont(fntPlainText);
		lblTermsDays.setBounds(30, 490, 172, 30);
		add(lblTermsDays);

		lblRequiredFields = new JLabel("Required Fields");
		lblRequiredFields.setFont(fntPlainText);
		lblRequiredFields.setBounds(30, 545, 183, 30);
		add(lblRequiredFields);
                
		lblPhone1 = new JLabel("Phone #1:");
		lblPhone1.setFont(fntPlainText);
		lblPhone1.setBounds(510, 160, 115, 30);
		add(lblPhone1);

		lblPhone2 = new JLabel("Phone #2:");
		lblPhone2.setFont(fntPlainText);
		lblPhone2.setBounds(510, 205, 115, 30);
		add(lblPhone2);
                
                lblPhone3 = new JLabel("Phone #3:");
		lblPhone3.setFont(fntPlainText);
		lblPhone3.setBounds(510, 250, 115, 30);
		add(lblPhone3);

		lblFaxNumber = new JLabel("Fax Number:");
		lblFaxNumber.setFont(fntPlainText);
		lblFaxNumber.setBounds(510, 295, 140, 30);
		add(lblFaxNumber);
                
		lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setFont(fntPlainText);
		lblEmailAddress.setBounds(510, 340, 163, 30);
		add(lblEmailAddress);
                
                lblWebsite = new JLabel("Website:");
		lblWebsite.setFont(fntPlainText);
		lblWebsite.setBounds(510, 385, 145, 30);
		add(lblWebsite);
               
		lblContactPerson = new JLabel("Contact Person:");
		lblContactPerson.setFont(fntPlainText);
		lblContactPerson.setBounds(510, 430, 173, 30);
		add(lblContactPerson);
                
                lblAsterisk1 = new JLabel("*");
                lblAsterisk1.setFont(fntPlainText);
		lblAsterisk1.setForeground(Color.RED);
		lblAsterisk1.setBounds(20, 120, 46, 30);
                add(lblAsterisk1);

		lblAsterisk2 = new JLabel("*");
                lblAsterisk2.setFont(fntPlainText);
		lblAsterisk2.setForeground(Color.RED);
		lblAsterisk2.setBounds(20, 162, 46, 30);
		add(lblAsterisk2);

		lblAsterisk3 = new JLabel("*");
                lblAsterisk3.setFont(fntPlainText);
		lblAsterisk3.setForeground(Color.RED);
		lblAsterisk3.setBounds(20, 450, 46, 30);
		add(lblAsterisk3);

		lblAsterisk4 = new JLabel("*");
                lblAsterisk4.setFont(fntPlainText);
		lblAsterisk4.setForeground(Color.RED);
		lblAsterisk4.setBounds(20, 499, 46, 30);
		add(lblAsterisk4);

		lblAsterisk5 = new JLabel("*");
		lblAsterisk5.setFont(fntPlainText);
                lblAsterisk5.setForeground(Color.RED);
		lblAsterisk5.setBounds(20, 550, 46, 30);
		add(lblAsterisk5);
              
                tfName = new JTextField();
		tfName.setFont(fntPlainText);
		tfName.setBounds(100, 160, 380, 30);
		add(tfName);
             
		tfCity = new JTextField();
		tfCity.setFont(fntPlainText);
		tfCity.setBounds(80, 310, 400, 30);
		add(tfCity);
               
		tfPostCode = new JTextField();
		tfPostCode.setFont(fntPlainText);
		tfPostCode.setBounds(140, 355, 340, 30);
		add(tfPostCode);
                
                tfCountry = new JTextField();
		tfCountry.setFont(fntPlainText);
		tfCountry.setBounds(120, 400, 360, 30);
		add(tfCountry);
                
                ftfCreditLimit = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfCreditLimit.setFont(fntPlainText);
                ftfCreditLimit.setValue(new Float(00.0F));
                ftfCreditLimit.setBounds(152, 445, 330, 30);
                add(ftfCreditLimit);

                ftfTerms = new JFormattedTextField(new DecimalFormat("#,##0"));
                ftfTerms.setFont(fntPlainText);
                ftfTerms.setValue(new Float(00F));
                ftfTerms.setBounds(172, 490, 310, 30);
                add(ftfTerms);
                
		tfPhone1 = new JTextField();
		tfPhone1.setFont(fntPlainText);
		tfPhone1.setBounds(615, 160, 350, 30);
		add(tfPhone1);

		tfPhone2 = new JTextField();
		tfPhone2.setFont(fntPlainText);
		tfPhone2.setBounds(615, 205, 350, 30);
		add(tfPhone2);

		tfPhone3 = new JTextField();
		tfPhone3.setFont(fntPlainText);
		tfPhone3.setBounds(615, 250, 350, 30);
		add(tfPhone3);

		tfFaxNumber = new JTextField();
		tfFaxNumber.setFont(fntPlainText);
		tfFaxNumber.setBounds(640, 295, 325, 30);
		add(tfFaxNumber);

		tfEmailAddress = new JTextField();
		tfEmailAddress.setFont(fntPlainText);
		tfEmailAddress.setBounds(660, 340, 305, 30);
		add(tfEmailAddress);

		tfWebsite = new JTextField();
		tfWebsite.setFont(fntPlainText);
		tfWebsite.setBounds(605, 385, 360, 30);
		add(tfWebsite);
                
                tfContactPerson = new JTextField();
		tfContactPerson.setFont(fntPlainText);
		tfContactPerson.setBounds(670, 430, 295, 30);
		add(tfContactPerson);

                //The following codes are for JTextAreas
		taAddress = new JTextArea();
                taAddress.setWrapStyleWord(true);
                taAddress.setLineWrap(true);
                taAddress.setFont(fntPlainText);
		taAddress.setBounds(119, 210, 360, 80);
		add(taAddress);
                
                //The following codes are for ScrollPane
                spAddress=new JScrollPane(taAddress);
                spAddress.setBounds(119, 210, 360, 80);
                add(spAddress);
                
                
                //The following codes are for JCheckBoxes
                chckbxInactiveAccount = new JCheckBox("Inactive Account");
		chckbxInactiveAccount.setFont(fntPlainText);
                chckbxInactiveAccount.setBackground(SystemColor.textHighlight);
		chckbxInactiveAccount.setBounds(29, 80, 200, 30);
		add(chckbxInactiveAccount);
                
                //The following codes are for JRadioButton
		rdbtnSisterCompanyCustomer = new JRadioButton("Sister Company Customer ");
                rdbtnSisterCompanyCustomer.setActionCommand("Sister Company Customer");
		rdbtnSisterCompanyCustomer.setFont(fntPlainText);
                rdbtnSisterCompanyCustomer.setBackground(SystemColor.textHighlight);
                rdbtnSisterCompanyCustomer.setSelected(true);
		rdbtnSisterCompanyCustomer.setBounds(103, 115, 300, 30);
		add(rdbtnSisterCompanyCustomer);
                
                rdbtnRetailCustomer = new JRadioButton("Retail Customer");
                rdbtnRetailCustomer.setActionCommand("Retail Customer");
		rdbtnRetailCustomer.setFont(fntPlainText);
                rdbtnRetailCustomer.setBackground(SystemColor.textHighlight);
		rdbtnRetailCustomer.setBounds(405, 115, 200, 30);
		add(rdbtnRetailCustomer);

		rdbtnWalkinCustomer = new JRadioButton("Walk-in Customer");
                rdbtnWalkinCustomer.setActionCommand("Walk-in Customer");
		rdbtnWalkinCustomer.setFont(fntPlainText);
                rdbtnWalkinCustomer.setBackground(SystemColor.textHighlight);
		rdbtnWalkinCustomer.setBounds(601, 115, 213, 30);
		add(rdbtnWalkinCustomer);

		rdbtnSupplier = new JRadioButton("Supplier");
                rdbtnSupplier.setActionCommand("Supplier");
		rdbtnSupplier.setFont(fntPlainText);
                rdbtnSupplier.setBackground(SystemColor.textHighlight);
		rdbtnSupplier.setBounds(816, 115, 109, 30);
		add(rdbtnSupplier);

		type = new ButtonGroup();
		type.add(rdbtnRetailCustomer);
		type.add(rdbtnSisterCompanyCustomer);
		type.add(rdbtnWalkinCustomer);
		type.add(rdbtnSupplier);
        }
        

}

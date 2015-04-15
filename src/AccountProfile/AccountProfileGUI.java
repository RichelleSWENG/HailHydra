package AccountProfile;

import HailHydra.GUIController;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class AccountProfileGUI extends JPanel
{
    protected JLabel lblHeader, lblType, lblName, lblAddress, lblCity,
            lblPostCode, lblCountry, lblCreditLimit, lblTermsDays, lblDays,
            lblRequiredFields, lblPhone1, lblPhone2, lblPhone3, lblFaxNumber,
            lblEmailAddress, lblWebsite, lblContactPerson,
            lblAsterisk1, lblAsterisk2, lblAsterisk3, lblAsterisk4;
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
    private JPanel pnlCompanyDetails, pnlAlertDetails, pnlContactDetails;
    private TitledBorder tbCompanyDetails, tbAlertDetail, tbContactDetails;

    public AccountProfileGUI() 
    {
        setBounds(0, 0, 1000, 620);
        setLayout(null);

        fntPlainText = new Font("Arial", Font.PLAIN, 21);
        fntHeaderText = new Font("Arial", Font.BOLD, 40);
        fntHeaderTableText = new Font("Arial", Font.BOLD, 16);
        
        pnlCompanyDetails= new JPanel();
        pnlCompanyDetails.setBounds(30, 130, 460, 290);
        pnlCompanyDetails.setLayout(null);
        add(pnlCompanyDetails);
        tbCompanyDetails = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Company Details");
        tbCompanyDetails.setTitleJustification(TitledBorder.LEFT);
        tbCompanyDetails.setTitleFont(fntPlainText);
        pnlCompanyDetails.setBorder(tbCompanyDetails);
        
        pnlAlertDetails= new JPanel();
        pnlAlertDetails.setBounds(30, 430, 460, 110);
        pnlAlertDetails.setLayout(null);
        add(pnlAlertDetails);
        tbAlertDetail = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Alert Details");
        tbAlertDetail.setTitleJustification(TitledBorder.LEFT);
        tbAlertDetail.setTitleFont(fntPlainText);
        pnlAlertDetails.setBorder(tbAlertDetail);
        
        pnlContactDetails= new JPanel();
        pnlContactDetails.setBounds(505, 130, 460, 320);
        pnlContactDetails.setLayout(null);
        add(pnlContactDetails);
        tbContactDetails = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Contact Details");
        tbContactDetails.setTitleJustification(TitledBorder.LEFT);
        tbContactDetails.setTitleFont(fntPlainText);
        pnlContactDetails.setBorder(tbContactDetails);
        
        lblHeader = new JLabel("");
        lblHeader.setFont(fntHeaderText);
        lblHeader.setBounds(30, 0, 600, 86);
        add(lblHeader);

        lblType = new JLabel("Type:");
        lblType.setFont(fntPlainText);
        lblType.setBounds(40, 100, 74, 30);
        add(lblType);

        lblName = new JLabel("Name:");
        lblName.setFont(fntPlainText);
        lblName.setBounds(75, 30, 74, 30);
        pnlCompanyDetails.add(lblName);

        lblAddress = new JLabel("Address:");
        lblAddress.setFont(fntPlainText);
        lblAddress.setBounds(50, 70, 103, 30);
        lblAddress.setToolTipText("Address can not exceed 100 characters.");
        pnlCompanyDetails.add(lblAddress);

        lblCity = new JLabel("City:");
        lblCity.setFont(fntPlainText);
        lblCity.setBounds(90, 170, 52, 30);
        pnlCompanyDetails.add(lblCity);

        lblPostCode = new JLabel("Postal Code:");
        lblPostCode.setFont(fntPlainText);
        lblPostCode.setBounds(10, 210, 150, 30);
        lblPostCode.setToolTipText("Post Code can only accept integers.");
        pnlCompanyDetails.add(lblPostCode);

        lblCountry = new JLabel("Country:");
        lblCountry.setFont(fntPlainText);
        lblCountry.setBounds(50, 250, 93, 30);
        pnlCompanyDetails.add(lblCountry);

        lblCreditLimit = new JLabel("Credit Limit:");
        lblCreditLimit.setFont(fntPlainText);
        lblCreditLimit.setBounds(15, 30, 133, 30);
        lblCreditLimit.setToolTipText("Credit limit can not exceed 9,999,999.");
        pnlAlertDetails.add(lblCreditLimit);

        lblTermsDays = new JLabel("Terms:");
        lblTermsDays.setFont(fntPlainText);
        lblTermsDays.setBounds(60, 70, 172, 30);
        lblTermsDays.setToolTipText("Terms can not exceed 2,000 and can not be negative.");
        pnlAlertDetails.add(lblTermsDays);
        
        lblDays = new JLabel("Days");
        lblDays.setFont(fntPlainText);
        lblDays.setBounds(390, 70, 172, 30);
        pnlAlertDetails.add(lblDays);

        lblRequiredFields = new JLabel("* Required Fields");
        lblRequiredFields.setFont(fntPlainText);
        lblRequiredFields.setForeground(Color.RED);
        lblRequiredFields.setBounds(30, 545, 183, 30);
        add(lblRequiredFields);

        lblPhone1 = new JLabel("Phone #1:");
        lblPhone1.setFont(fntPlainText);
        lblPhone1.setBounds(65, 30, 115, 30);
        pnlContactDetails.add(lblPhone1);

        lblPhone2 = new JLabel("Phone #2:");
        lblPhone2.setFont(fntPlainText);
        lblPhone2.setBounds(65, 70, 115, 30);
        pnlContactDetails.add(lblPhone2);

        lblPhone3 = new JLabel("Phone #3:");
        lblPhone3.setFont(fntPlainText);
        lblPhone3.setBounds(65, 110, 115, 30);
        pnlContactDetails.add(lblPhone3);

        lblFaxNumber = new JLabel("Fax Number:");
        lblFaxNumber.setFont(fntPlainText);
        lblFaxNumber.setBounds(40, 150, 140, 30);
        pnlContactDetails.add(lblFaxNumber);

        lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setFont(fntPlainText);
        lblEmailAddress.setBounds(20, 190, 163, 30);
        pnlContactDetails.add(lblEmailAddress);

        lblWebsite = new JLabel("Website:");
        lblWebsite.setFont(fntPlainText);
        lblWebsite.setBounds(80, 230, 145, 30);
        pnlContactDetails.add(lblWebsite);

        lblContactPerson = new JLabel("Contact Person:");
        lblContactPerson.setFont(fntPlainText);
        lblContactPerson.setBounds(10, 270, 173, 30);
        pnlContactDetails.add(lblContactPerson);

        lblAsterisk1 = new JLabel("*");
        lblAsterisk1.setFont(fntPlainText);
        lblAsterisk1.setForeground(Color.RED);
        lblAsterisk1.setBounds(30, 100, 46, 30);
        add(lblAsterisk1);

        lblAsterisk2 = new JLabel("*");
        lblAsterisk2.setFont(fntPlainText);
        lblAsterisk2.setForeground(Color.RED);
        lblAsterisk2.setBounds(65, 30, 46, 30);
        pnlCompanyDetails.add(lblAsterisk2);

        lblAsterisk3 = new JLabel("*");
        lblAsterisk3.setFont(fntPlainText);
        lblAsterisk3.setForeground(Color.RED);
        lblAsterisk3.setBounds(5, 30, 46, 30);
        pnlAlertDetails.add(lblAsterisk3);

        lblAsterisk4 = new JLabel("*");
        lblAsterisk4.setFont(fntPlainText);
        lblAsterisk4.setForeground(Color.RED);
        lblAsterisk4.setBounds(50, 70, 46, 30);
        pnlAlertDetails.add(lblAsterisk4);

        tfName = new JTextField();
        tfName.setFont(fntPlainText);
        tfName.setBounds(140, 30, 300, 30);
        pnlCompanyDetails.add(tfName);

        tfCity = new JTextField();
        tfCity.setFont(fntPlainText);
        tfCity.setBounds(140, 170, 300, 30);
        pnlCompanyDetails.add(tfCity);

        tfPostCode = new JTextField();
        tfPostCode.setFont(fntPlainText);
        tfPostCode.setBounds(140, 210, 300, 30);
        pnlCompanyDetails.add(tfPostCode);

        tfCountry = new JTextField();
        tfCountry.setFont(fntPlainText);
        tfCountry.setBounds(140, 250, 300, 30);
        pnlCompanyDetails.add(tfCountry);

        ftfCreditLimit = new JFormattedTextField(new DecimalFormat("#,##0.00"));
        ftfCreditLimit.setFont(fntPlainText);
        ftfCreditLimit.setHorizontalAlignment(JTextField.RIGHT);
        ftfCreditLimit.setValue(new Float(00.0F));
        ftfCreditLimit.setBounds(140, 30, 300, 30);
        pnlAlertDetails.add(ftfCreditLimit);

        ftfTerms = new JFormattedTextField(new DecimalFormat("#,##0"));
        ftfTerms.setFont(fntPlainText);
        ftfTerms.setToolTipText("Terms can only have maximum of 2000 days.");
        ftfTerms.setHorizontalAlignment(JTextField.RIGHT);
        ftfTerms.setValue(new Float(00F));
        ftfTerms.setBounds(140, 70, 240, 30);
        pnlAlertDetails.add(ftfTerms);

        tfPhone1 = new JTextField();
        tfPhone1.setFont(fntPlainText);
        tfPhone1.setBounds(170, 30, 270, 30);
        pnlContactDetails.add(tfPhone1);

        tfPhone2 = new JTextField();
        tfPhone2.setFont(fntPlainText);
        tfPhone2.setBounds(170, 70, 270, 30);
        pnlContactDetails.add(tfPhone2);

        tfPhone3 = new JTextField();
        tfPhone3.setFont(fntPlainText);
        tfPhone3.setBounds(170, 110, 270, 30);
        pnlContactDetails.add(tfPhone3);

        tfFaxNumber = new JTextField();
        tfFaxNumber.setFont(fntPlainText);
        tfFaxNumber.setBounds(170, 150, 270, 30);
        pnlContactDetails.add(tfFaxNumber);

        tfEmailAddress = new JTextField();
        tfEmailAddress.setFont(fntPlainText);
        tfEmailAddress.setBounds(170, 190, 270, 30);
        pnlContactDetails.add(tfEmailAddress);

        tfWebsite = new JTextField();
        tfWebsite.setFont(fntPlainText);
        tfWebsite.setBounds(170, 230, 270, 30);
        pnlContactDetails.add(tfWebsite);

        tfContactPerson = new JTextField();
        tfContactPerson.setFont(fntPlainText);
        tfContactPerson.setBounds(170, 270, 270, 30);
        pnlContactDetails.add(tfContactPerson);

        //The following codes are for JTextAreas
        taAddress = new JTextArea();
        taAddress.setWrapStyleWord(true);
        taAddress.setLineWrap(true);
        taAddress.setFont(fntPlainText);
        add(taAddress);

        //The following codes are for ScrollPane
        spAddress = new JScrollPane(taAddress);
        spAddress.setBounds(140, 70, 300, 90);
        pnlCompanyDetails.add(spAddress);

        //The following codes are for JCheckBoxes
        chckbxInactiveAccount = new JCheckBox("Inactive Account");
        chckbxInactiveAccount.setFont(fntPlainText);
        chckbxInactiveAccount.setBounds(29, 70, 200, 30);
        add(chckbxInactiveAccount);

        //The following codes are for JRadioButton
        rdbtnSisterCompanyCustomer = new JRadioButton("Sister Company Customer ");
        rdbtnSisterCompanyCustomer.setActionCommand("Sister Company Customer");
        rdbtnSisterCompanyCustomer.setFont(fntPlainText);
        rdbtnSisterCompanyCustomer.setSelected(true);
        rdbtnSisterCompanyCustomer.setBounds(110, 100, 300, 30);
        add(rdbtnSisterCompanyCustomer);

        rdbtnRetailCustomer = new JRadioButton("Retail Customer");
        rdbtnRetailCustomer.setActionCommand("Retail Customer");
        rdbtnRetailCustomer.setFont(fntPlainText);
        rdbtnRetailCustomer.setBounds(410, 100, 183, 30);
        add(rdbtnRetailCustomer);

        rdbtnWalkinCustomer = new JRadioButton("Walk-in Customer");
        rdbtnWalkinCustomer.setActionCommand("Walk-in Customer");
        rdbtnWalkinCustomer.setFont(fntPlainText);
        rdbtnWalkinCustomer.setBounds(620, 100, 200, 30);
        add(rdbtnWalkinCustomer);

        rdbtnSupplier = new JRadioButton("Supplier");
        rdbtnSupplier.setActionCommand("Supplier");
        rdbtnSupplier.setFont(fntPlainText);
        rdbtnSupplier.setBounds(830, 100, 109, 30);
        add(rdbtnSupplier);

        type = new ButtonGroup();
        type.add(rdbtnRetailCustomer);
        type.add(rdbtnSisterCompanyCustomer);
        type.add(rdbtnWalkinCustomer);
        type.add(rdbtnSupplier);
    }
    
    public static void main(String args[])
    {
        GUIController a=new GUIController();
        a.changePanelToAddAccountProfile();
    }
}

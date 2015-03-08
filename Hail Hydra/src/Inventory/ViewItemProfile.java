package Inventory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

//import Database.DBConnection;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ViewItemProfile extends JFrame
{

    private JPanel contentPane;
    private JPanel statusBar;
    private JTextField tfPartNumber;
    private JTextField tfDescription;
    private JTextField tfRackLocation;
    private JTextField tfStockMinimum;
    private JTextField tfSisterPrice;
    private JTextField tfRetailPrice;
    private JTextField tfWalkPrice;
    private JTextField tfLastCost;
    private JTextArea taNotes;
    private String imageLocation;
    //private JLabel lblStatus;
    private boolean connectionStatus;
    private InventoryController ic;
    private JLabel statusLabel;
    private JCheckBox chckbxInactiveItem;
    private JLabel lblImage;
    private ImageIcon icon;
    private ArrayList<String> itemProfile;

    /**
     * Create the frame.
     */
    public ViewItemProfile(ArrayList<String> itemProfile) throws IOException
    {
        this.itemProfile = itemProfile;
        setTitle("View Item Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        contentPane.setLayout(null);
        getContentPane().add(contentPane);

        statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
        statusLabel = new JLabel("Database <<>>");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(statusLabel);

        chckbxInactiveItem = new JCheckBox("Inactive Item");
        chckbxInactiveItem.setEnabled(false);
        chckbxInactiveItem.setSelected(false);
        chckbxInactiveItem.setFont(new Font("Arial", Font.PLAIN, 14));
        chckbxInactiveItem.setBounds(30, 30, 113, 23);
        contentPane.add(chckbxInactiveItem);

        JLabel lblPartNumber = new JLabel("*Part Number:");
        lblPartNumber.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPartNumber.setBounds(30, 74, 113, 23);
        contentPane.add(lblPartNumber);

        JLabel lbldescription = new JLabel("*Description:");
        lbldescription.setFont(new Font("Arial", Font.PLAIN, 14));
        lbldescription.setBounds(30, 108, 113, 23);
        contentPane.add(lbldescription);

        JLabel lblRackLocation = new JLabel("Rack Location:");
        lblRackLocation.setFont(new Font("Arial", Font.PLAIN, 14));
        lblRackLocation.setBounds(30, 142, 113, 23);
        contentPane.add(lblRackLocation);

        JLabel lblstockMinimum = new JLabel("*Stock Minimum:");
        lblstockMinimum.setFont(new Font("Arial", Font.PLAIN, 14));
        lblstockMinimum.setBounds(30, 176, 123, 23);
        contentPane.add(lblstockMinimum);

        JLabel lblSisterCompanyPrice = new JLabel("Sister Company Price:");
        lblSisterCompanyPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSisterCompanyPrice.setBounds(30, 210, 146, 23);
        contentPane.add(lblSisterCompanyPrice);

        JLabel lblRetailPrice = new JLabel("Retail Price:");
        lblRetailPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        lblRetailPrice.setBounds(30, 244, 84, 23);
        contentPane.add(lblRetailPrice);

        JLabel lblWalkinPrice = new JLabel("Walk-in Price:");
        lblWalkinPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        lblWalkinPrice.setBounds(30, 278, 95, 23);
        contentPane.add(lblWalkinPrice);

        JLabel lblLastCost = new JLabel("Last Cost:");
        lblLastCost.setFont(new Font("Arial", Font.PLAIN, 14));
        lblLastCost.setBounds(30, 312, 84, 23);
        contentPane.add(lblLastCost);

        JLabel lblNotes = new JLabel("Notes:");
        lblNotes.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNotes.setBounds(30, 346, 52, 23);
        contentPane.add(lblNotes);

        tfPartNumber = new JTextField();
        tfPartNumber.setEditable(false);
        tfPartNumber.setBounds(139, 74, 331, 25);
        contentPane.add(tfPartNumber);
        tfPartNumber.setColumns(10);

        tfDescription = new JTextField();
        tfDescription.setEditable(false);
        tfDescription.setColumns(10);
        tfDescription.setBounds(139, 110, 331, 25);
        contentPane.add(tfDescription);

        tfRackLocation = new JTextField();
        tfRackLocation.setEditable(false);
        tfRackLocation.setColumns(10);
        tfRackLocation.setBounds(139, 144, 331, 25);
        contentPane.add(tfRackLocation);

        tfStockMinimum = new JTextField();
        tfStockMinimum.setEditable(false);
        tfStockMinimum.setColumns(10);
        tfStockMinimum.setBounds(159, 178, 311, 25);
        contentPane.add(tfStockMinimum);

        tfSisterPrice = new JTextField();
        tfSisterPrice.setEditable(false);
        tfSisterPrice.setColumns(10);
        tfSisterPrice.setBounds(181, 212, 289, 25);
        contentPane.add(tfSisterPrice);

        tfRetailPrice = new JTextField();
        tfRetailPrice.setEditable(false);
        tfRetailPrice.setColumns(10);
        tfRetailPrice.setBounds(139, 244, 331, 25);
        contentPane.add(tfRetailPrice);

        tfWalkPrice = new JTextField();
        tfWalkPrice.setEditable(false);
        tfWalkPrice.setColumns(10);
        tfWalkPrice.setBounds(139, 280, 331, 25);
        contentPane.add(tfWalkPrice);

        tfLastCost = new JTextField();
        tfLastCost.setEditable(false);
        tfLastCost.setColumns(10);
        tfLastCost.setBounds(139, 314, 331, 25);
        contentPane.add(tfLastCost);

        JLabel lblrequiredFields = new JLabel("*Required Fields");
        lblrequiredFields.setFont(new Font("Arial", Font.BOLD, 14));
        lblrequiredFields.setBounds(30, 506, 131, 28);
        contentPane.add(lblrequiredFields);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(524, 509, 95, 25);
        //contentPane.add(btnSubmit);

        JButton btnCancel = new JButton("Close");
        btnCancel.setBounds(662, 509, 95, 25);
        contentPane.add(btnCancel);

        JButton btnUpdateImage = new JButton("Update Image");
        btnUpdateImage.setBounds(588, 244, 146, 25);
        //contentPane.add(btnUpdateImage);

        lblImage = new JLabel("");
        lblImage.setBounds(560, 30, 200, 200);
        contentPane.add(lblImage);

        taNotes = new JTextArea();
        taNotes.setEditable(false);
        taNotes.setBounds(30, 366, 442, 129);
        contentPane.add(taNotes);
        //set TextFields
        tfPartNumber.setText(itemProfile.get(0));
        tfDescription.setText(itemProfile.get(1));
        tfRackLocation.setText(itemProfile.get(2));
        tfStockMinimum.setText(itemProfile.get(3));
        tfRetailPrice.setText(itemProfile.get(4)+".00");
        tfSisterPrice.setText(itemProfile.get(5)+".00");
        tfWalkPrice.setText(itemProfile.get(6)+".00");
        tfLastCost.setText(itemProfile.get(7)+".00");
        taNotes.setText(itemProfile.get(8));
        

        // set Image on JLabel
        File ImageFile = new File(itemProfile.get(9));
        Image image = null;
        image = ImageIO.read(ImageFile);
        Image resizedImage = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), 0);//resize pic to fit JLabel
        icon = new ImageIcon(resizedImage);
        lblImage.setIcon(icon);
        
        // set status
        if((itemProfile.get(10)).equals("0"))
         chckbxInactiveItem.setSelected(true);
        
            
        
        
        
        //btnUpdateImage.addActionListener(new AddItemProfile.btnUpdateImage_Action());
        //btnSubmit.addActionListener(new AddItemProfile.btnSubmit_Action());
        btnCancel.addActionListener(new ViewItemProfile.btnCancel_Action());

        //  chckbxInactiveItem.getState();
    }

    public void setController(InventoryController ic)
    {
        this.ic = ic;
    }

    public void setConnectionStatus()
    {
        this.connectionStatus = ic.getConnectionStatus();
        if (connectionStatus)
        {
            this.statusLabel.setText("Database<<Connected>>");
        } else
        {
            this.statusLabel.setText("Database<<Not Connected>>");
        }
    }
    
    class btnCancel_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ic.ivVisibleVIP(); //make inventory view visible
            } catch (Exception ex)
            {
                Logger.getLogger(AddItemProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



}

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

public class AddItemProfile extends JFrame
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

    /**
     * Create the frame.
     */
    public AddItemProfile()
    {
        setTitle("Add Item Profile");
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
        tfPartNumber.setBounds(139, 74, 331, 25);
        contentPane.add(tfPartNumber);
        tfPartNumber.setColumns(10);

        tfDescription = new JTextField();
        tfDescription.setColumns(10);
        tfDescription.setBounds(139, 110, 331, 25);
        contentPane.add(tfDescription);

        tfRackLocation = new JTextField();
        tfRackLocation.setColumns(10);
        tfRackLocation.setBounds(139, 144, 331, 25);
        contentPane.add(tfRackLocation);

        tfStockMinimum = new JTextField();
        tfStockMinimum.setColumns(10);
        tfStockMinimum.setBounds(159, 178, 311, 25);
        contentPane.add(tfStockMinimum);

        tfSisterPrice = new JTextField();
        tfSisterPrice.setText("0.00");
        tfSisterPrice.setColumns(10);
        tfSisterPrice.setBounds(181, 212, 289, 25);
        contentPane.add(tfSisterPrice);

        tfRetailPrice = new JTextField();
        tfRetailPrice.setText("0.00");
        tfRetailPrice.setColumns(10);
        tfRetailPrice.setBounds(139, 244, 331, 25);
        contentPane.add(tfRetailPrice);

        tfWalkPrice = new JTextField();
        tfWalkPrice.setText("0.00");
        tfWalkPrice.setColumns(10);
        tfWalkPrice.setBounds(139, 280, 331, 25);
        contentPane.add(tfWalkPrice);

        tfLastCost = new JTextField();
        tfLastCost.setText("0.00");
        tfLastCost.setColumns(10);
        tfLastCost.setBounds(139, 314, 331, 25);
        contentPane.add(tfLastCost);

        JLabel lblrequiredFields = new JLabel("*Required Fields");
        lblrequiredFields.setFont(new Font("Arial", Font.BOLD, 14));
        lblrequiredFields.setBounds(30, 506, 131, 28);
        contentPane.add(lblrequiredFields);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(524, 509, 95, 25);
        contentPane.add(btnSubmit);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(662, 509, 95, 25);
        contentPane.add(btnCancel);

        JButton btnUpdateImage = new JButton("Update Image");
        btnUpdateImage.setBounds(588, 244, 146, 25);
        contentPane.add(btnUpdateImage);

        lblImage = new JLabel("");
        lblImage.setBounds(560, 30, 200, 200);
        contentPane.add(lblImage);

        taNotes = new JTextArea();
        taNotes.setBounds(30, 366, 442, 129);
        contentPane.add(taNotes);
        btnUpdateImage.addActionListener(new AddItemProfile.btnUpdateImage_Action());
        btnSubmit.addActionListener(new AddItemProfile.btnSubmit_Action());
        btnCancel.addActionListener(new AddItemProfile.btnCancel_Action());

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

    class btnUpdateImage_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            //ic.AddImage();
             FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg","jpg");

        JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if(returnValue == fileChooser.APPROVE_OPTION)
            {
            File filePoster = fileChooser.getSelectedFile();
            //filePoster.getPath();
                Image image = null;
                try {
                        image = ImageIO.read(filePoster);
                         Image resizedImage = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), 0);//resize pic to fit JLabel
                         icon = new ImageIcon(resizedImage); 
                } catch (IOException ex) {
                        // TODO Auto-generated catch block
                        ex.printStackTrace();
                }
                try{
                    lblImage.setIcon(icon);
                    imageLocation = filePoster.getAbsolutePath();;
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Invalid file type.");
                }
            }
        }
    }

    class btnSubmit_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            boolean error = false;
            if (tfPartNumber.getText().equals("") || tfDescription.getText().equals("") || tfStockMinimum.getText().equals(""))
            {

                JOptionPane.showMessageDialog(null, "Please fill in the required fields");
                error = true;
            }
            if (!isInteger(tfStockMinimum.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid value for stock minimum");
                error = true;
            }
            if (!isFloat(tfSisterPrice.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Sister Company Price");
                error = true;
            }
            if (!isFloat(tfRetailPrice.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Retail Price");
                error = true;

            }
            if (!isFloat(tfWalkPrice.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Walk in Price");
                error = true;

            }
            if (!isFloat(tfLastCost.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Last Cost Price");
                error = true;

            }
            if (error == false)
            {
                try
                {
                    ic.AddItem(tfPartNumber.getText(), tfDescription.getText(), tfRackLocation.getText(), tfStockMinimum.getText(), tfSisterPrice.getText(), tfRetailPrice.getText(), tfWalkPrice.getText(), tfLastCost.getText(), taNotes.getText(), imageLocation, Boolean.toString(chckbxInactiveItem.isSelected()));
                    ic.ivVisibleAIP(); //make inventory view visible
                } catch (SQLException ex)
                {
                    Logger.getLogger(AddItemProfile.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex)
                {
                    Logger.getLogger(AddItemProfile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private boolean isInteger(String s)
        {

            try
            {
                Integer.parseInt(s);
            } catch (NumberFormatException e)
            {
                return false;
            }
            // only got here if we didn't return false
            return true;

        }

        private boolean isFloat(String s)
        {

            try
            {
                Float.parseFloat(s);
            } catch (NumberFormatException e)
            {
                return false;
            }
            return true;
        }

    }

    class btnCancel_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            try
            {   
                
                ic.ivVisibleAIP(); //make inventory view visible
            } catch (Exception ex)
            {
                Logger.getLogger(AddItemProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

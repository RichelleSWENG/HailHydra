package Inventory;

import HailHydra.GUIController;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddItemProfileGUI extends JPanel {

	private JLabel  lblHeader, lblPartNumber, lblDescription,
                        lblRackLocation, lblNotes, lblRequiredFields, lblImage,
                        lblStockMinimum, lblSisterCompanyPrice, lblRetailPrice, 
                        lblWalkinPrice, lblLastCost, lblAsterisk1, lblAsterisk2, 
                        lblAsterisk3, lblAsterisk4;
        private JTextField tfPartNumber, tfDescription, tfRackLocation;
        private JFormattedTextField ftfStockMinimum, ftfSisterCompanyPrice, ftfRetailPrice, ftfWalkinPrice, ftfLastCost;
        private JTextArea taNotes;
        private JCheckBox chckbxInactiveItem;
        private JButton btnSubmit, btnCancel, btnUpdateImage;
        private JScrollPane spNotes;
        private Font fntPlainText, fntHeaderText;
        private GUIController controller;
        private InventoryController mainController;
        private String imageLocation;
        private ArrayList<String> al;
	
	public AddItemProfileGUI(GUIController temp) {
            
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
		
                lblHeader = new JLabel("Add Item Profile");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);	

		lblPartNumber = new JLabel("Part Number:");
		lblPartNumber.setFont(fntPlainText);
		lblPartNumber.setBounds(30, 123, 145, 30);
		add(lblPartNumber);

		lblDescription = new JLabel("Description:");
		lblDescription.setFont(fntPlainText);
		lblDescription.setBounds(30, 158, 145, 30);
		add(lblDescription);

		lblRackLocation = new JLabel("Rack Location:");
		lblRackLocation.setFont(fntPlainText);
		lblRackLocation.setBounds(30, 193, 173, 30);
		add(lblRackLocation);
                
		lblStockMinimum = new JLabel("Stock Minimum:");
		lblStockMinimum.setFont(fntPlainText);
		lblStockMinimum.setBounds(30, 228, 173, 30);
		add(lblStockMinimum);
                
		lblSisterCompanyPrice = new JLabel("Sister Company Price:");
		lblSisterCompanyPrice.setFont(fntPlainText);
		lblSisterCompanyPrice.setBounds(30, 263, 238, 30);
		add(lblSisterCompanyPrice);
                
		lblRetailPrice = new JLabel("Retail Price:");
		lblRetailPrice.setFont(fntPlainText);
		lblRetailPrice.setBounds(30, 298, 130, 30);
		add(lblRetailPrice);

		lblWalkinPrice = new JLabel("Walk-in Price:");
		lblWalkinPrice.setFont(fntPlainText);
		lblWalkinPrice.setBounds(30, 333, 160, 30);
		add(lblWalkinPrice);

		lblLastCost = new JLabel("Last Cost:");
		lblLastCost.setFont(fntPlainText);
		lblLastCost.setBounds(30, 368, 113, 30);
		add(lblLastCost);
                
		lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(30, 400, 95, 30);
		add(lblNotes);

                lblRequiredFields = new JLabel("Required Fields");
		lblRequiredFields.setFont(fntPlainText);
		lblRequiredFields.setBounds(30, 545, 200, 28);
		add(lblRequiredFields);
                
		lblImage = new JLabel("");
		lblImage.setForeground(Color.GRAY);
		lblImage.setBackground(Color.WHITE);
		lblImage.setBounds(610, 80, 355, 355);
		add(lblImage);

                lblAsterisk1 = new JLabel("*");
		lblAsterisk1.setForeground(Color.RED);
		lblAsterisk1.setFont(fntPlainText);
		lblAsterisk1.setBounds(20, 120, 19, 21);
		add(lblAsterisk1);
		
		lblAsterisk2 = new JLabel("*");
		lblAsterisk2.setForeground(Color.RED);
		lblAsterisk2.setFont(fntPlainText);
		lblAsterisk2.setBounds(20, 158, 19, 21);
		add(lblAsterisk2);
		
		lblAsterisk3 = new JLabel("*");
		lblAsterisk3.setForeground(Color.RED);
		lblAsterisk3.setFont(fntPlainText);
		lblAsterisk3.setBounds(20, 229, 19, 21);
		add(lblAsterisk3);
		
		lblAsterisk4 = new JLabel("*");
		lblAsterisk4.setForeground(Color.RED);
		lblAsterisk4.setFont(fntPlainText);
		lblAsterisk4.setBounds(20, 549, 19, 21);
		add(lblAsterisk4);
                
		tfPartNumber = new JTextField();
		tfPartNumber.setFont(fntPlainText);
		tfPartNumber.setBounds(170, 123, 409, 30);
		add(tfPartNumber);

		tfDescription = new JTextField();
		tfDescription.setFont(fntPlainText);
		tfDescription.setBounds(160, 158, 419, 30);
		add(tfDescription);

		tfRackLocation = new JTextField();
		tfRackLocation.setFont(fntPlainText);
		tfRackLocation.setBounds(188, 193, 391, 30);
		add(tfRackLocation);
                
                ftfStockMinimum = new JFormattedTextField(new DecimalFormat("#,##0"));
                ftfStockMinimum.setFont(fntPlainText);
                ftfStockMinimum.setValue(new Float(0));
                ftfStockMinimum.setBounds(188, 228, 391, 30);
                add(ftfStockMinimum);
                
                ftfSisterCompanyPrice = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfSisterCompanyPrice.setFont(fntPlainText);
                ftfSisterCompanyPrice.setValue(new Float(00.0F));
                ftfSisterCompanyPrice.setBounds(258, 263, 321, 30);
                add(ftfSisterCompanyPrice);
                
                ftfRetailPrice = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfRetailPrice.setFont(fntPlainText);
                ftfRetailPrice.setValue(new Float(00.0F));
                ftfRetailPrice.setBounds(160, 298, 419, 30);
                add(ftfRetailPrice);
                
                ftfWalkinPrice = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfWalkinPrice.setFont(fntPlainText);
                ftfWalkinPrice.setValue(new Float(00.0F));
                ftfWalkinPrice.setBounds(181, 333, 398, 30);
                add(ftfWalkinPrice);

                ftfLastCost = new JFormattedTextField(new DecimalFormat("#,##0.00"));
                ftfLastCost.setFont(fntPlainText);
                ftfLastCost.setValue(new Float(00.0F));
                ftfLastCost.setBounds(135, 368, 444, 30);
                add(ftfLastCost);
                
		taNotes = new JTextArea();
		taNotes.setFont(fntPlainText);
                taNotes.setWrapStyleWord(true);
                taNotes.setLineWrap(true);
		taNotes.setBounds(30, 430, 550, 80);
		add(taNotes);
	
                spNotes=new JScrollPane(taNotes);
                spNotes.setBounds(30, 430, 550, 80);
                add(spNotes);
                
                chckbxInactiveItem = new JCheckBox("Inactive Item");
		chckbxInactiveItem.setFont(fntPlainText);
                chckbxInactiveItem.setBackground(SystemColor.textHighlight);
		chckbxInactiveItem.setBounds(30, 80, 182, 30);
		add(chckbxInactiveItem);
                
                btnUpdateImage = new JButton("Update Image");
		btnUpdateImage.setFont(fntPlainText);
		btnUpdateImage.setBounds(695, 450, 190, 40);
		add(btnUpdateImage);
                btnUpdateImage.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) 
                {
                         JFileChooser fileOpen = new JFileChooser();
                         String[] suffices = ImageIO.getReaderFileSuffixes();
                         for (int i = 0; i < suffices.length; i++)
                         {
                            FileFilter filter = new FileNameExtensionFilter(suffices[i] + " files", suffices[i]);
                            fileOpen.addChoosableFileFilter(filter);
                         }
                        int ret = fileOpen.showDialog(null, "Open file");
                        imageLocation = fileOpen.getSelectedFile().getAbsolutePath();
                        
                        BufferedImage img = null;
                        try 
                        {
                            img = ImageIO.read(new File(fileOpen.getSelectedFile().getAbsolutePath()));
                            Image dimg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_DEFAULT);
                            ImageIcon imageIcon = new ImageIcon(dimg);
                            lblImage.setIcon(imageIcon);
                        } catch (Exception ioe) 
                        {
                             JOptionPane.showMessageDialog(null, "Selected file is not an image. Please try again.");
                        }
                        
                }
                });
                
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(new ActionListener(){//Everytime All is selected 
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        //(part_num,description,rack_location,stock_minimum,sister_company_price,traders_price,walk_in_price,last_cost,notes,image,status)
                        //ftfStockMinimum, ftfSisterCompanyPrice, ftfRetailPrice, ftfWalkinPrice, ftfLastCost;
                            al = new ArrayList();
                            al.add(tfPartNumber.getText());
                            al.add(tfDescription.getText());
                            al.add(tfRackLocation.getText());
                            al.add(ftfStockMinimum.getText());
                            al.add(ftfSisterCompanyPrice.getText());
                            al.add(ftfRetailPrice.getText());
                            al.add(ftfWalkinPrice.getText());
                            al.add(ftfLastCost.getText());
                            al.add(taNotes.getText());
                            al.add(imageLocation);
                            if(chckbxInactiveItem.isSelected())
                                al.add("0");
                            else al.add("1");
                            
                            boolean error = false;

            if (tfPartNumber.getText().equals("") || tfDescription.getText().equals("") || ftfStockMinimum.getText().equals(""))
            {

                JOptionPane.showMessageDialog(null, "Please fill in the required fields");
                error = true;
            }

            if (!isInteger(ftfStockMinimum.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid value for stock minimum");
                error = true;
            }
            if (!isFloat(ftfSisterCompanyPrice.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Sister Company Price");
                error = true;
            }
            if (!isFloat(ftfRetailPrice.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Retail Price");
                error = true;

            }
            if (!isFloat(ftfWalkinPrice.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Walk in Price");
                error = true;

            }
            if (!isFloat(ftfLastCost.getText()))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Last Cost Price");
                error = true;

            }

            if (Integer.parseInt(ftfStockMinimum.getText())<0)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Stock Minimum");
                error = true;
            }
       
            if (Float.valueOf(ftfSisterCompanyPrice.getText())<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Sister Company Price");
                error = true;
            }
            if (Float.parseFloat(ftfRetailPrice.getText())<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Retail Price");
                error = true;
            }
            if (Float.parseFloat(ftfWalkinPrice.getText())<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Walk in Price");
                error = true;
            }
            if (Float.parseFloat(ftfLastCost.getText())<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Last Cost");
                error = true;
            }
            
            //if (hasSpecial())


            if (error == false)
            {
                try
                {
                    mainController.AddItem(tfPartNumber.getText(), tfDescription.getText(), tfRackLocation.getText(), ftfStockMinimum.getText(), ftfSisterCompanyPrice.getText(), ftfRetailPrice.getText(), ftfWalkinPrice.getText(), ftfLastCost.getText(), taNotes.getText(), imageLocation, Boolean.toString(chckbxInactiveItem.isSelected()));
                    controller.changePanelToInventory();
                } catch (SQLException ex)
                {
                    Logger.getLogger(AddItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex)
                {
                    Logger.getLogger(AddItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                       
                    } catch (Exception ex)
                    {
                        Logger.getLogger(AddItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
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
        
        private boolean hasSpecial(String s)
        {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            boolean b = m.find();

        if (b || s.contains(" "))
         return true;
        else 
         return false;
        }


                });
                
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
                btnCancel.addActionListener(new ActionListener(){//Everytime All is selected 
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        controller.changePanelToInventory();
                    } catch (Exception ex)
                    {
                        Logger.getLogger(AddItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                });
               
                
                
	}
        public static void main(String args[])
       {
                    GUIController temp=new GUIController();
                    temp.changePanelToAddItemProfile();
        }

    public void setMainController(InventoryController inventoryController)
    {
        this.mainController = inventoryController;
    }
        
}

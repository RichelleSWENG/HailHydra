package Inventory;

import HailHydra.GUIController;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AddItemProfileGUI extends ItemProfileGUI 
{

        private JButton btnSubmit, btnCancel, btnUpdateImage;
        private GUIController controller;
        private InventoryController mainController;
        private ArrayList<String> al;
	
	public AddItemProfileGUI(GUIController temp) 
        {
            
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
              
                lblHeader.setText("Add Item Profile");
                al = new ArrayList();
                
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
                           al.removeAll(al);
                            
                            boolean error = false;
           if (tfPartNumber.getText().equals("") || tfDescription.getText().equals("") || ftfStockMinimum.getText().equals(""))
            {

                JOptionPane.showMessageDialog(null, "Please fill in the required fields");
                error = true;
            }
            
            if(tfPartNumber.getText().length()>20)
            {
                JOptionPane.showMessageDialog(null, "Part Number can not exceed 20 characters. Please re-input the Part Number.");
                error = true;
                lblPartNumber.setForeground(Color.orange);
            }
            
            if(tfDescription.getText().length()>100)
            {
                JOptionPane.showMessageDialog(null, "Description can not exceed 100 characters. Please re-input the Description.");
                error = true;
                lblDescription.setForeground(Color.orange);
            }
                        
            if(tfRackLocation.getText().length()>45)
            {
                JOptionPane.showMessageDialog(null, "Rack Location can not exceed 45 characters. Please re-input the Rack Location.");
                error = true;
                lblRackLocation.setForeground(Color.orange);
            }
            
            if(ftfStockMinimum.getText().length()>11)
            {
                JOptionPane.showMessageDialog(null, "Stock Minimum can not exceed 11 characters. Please re-input the Stock Minimum.");
                error = true;
                lblStockMinimum.setForeground(Color.orange);
            }
                
            /*if(ftfSisterCompanyPrice.getText().length()>20)
            {
                JOptionPane.showMessageDialog(null, "Part Number can not exceed 20 characters. Please re-input the Part Number.");
                error = true;
            }
                        
            if(ftfRetailPrice.getText().length()>20)
            {
                JOptionPane.showMessageDialog(null, "Part Number can not exceed 20 characters. Please re-input the Part Number.");
                error = true;
            }
                        
            if(ftfWalkinPrice.getText().length()>20)
            {
                JOptionPane.showMessageDialog(null, "Part Number can not exceed 20 characters. Please re-input the Part Number.");
                error = true;
            }
                        
            if(ftfLastCost.getText().length()>20)
            {
                JOptionPane.showMessageDialog(null, "Part Number can not exceed 20 characters. Please re-input the Part Number.");
                error = true;
            }
            */             
            if(taNotes.getText().length()>500)
            {
                JOptionPane.showMessageDialog(null, "Notes can not exceed 500 characters. Please re-input the Notes.");
                error = true;
                lblNotes.setForeground(Color.orange);
            }

            if (!isInteger(ftfStockMinimum.getText().replaceAll(",", "")))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid value for stock minimum");
                error = true;
                lblStockMinimum.setForeground(Color.orange);
            }
            if (!isFloat(ftfSisterCompanyPrice.getText().replaceAll(",", "")))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Sister Company Price");
                error = true;
                lblSisterCompanyPrice.setForeground(Color.orange);
            }
            if (!isFloat(ftfRetailPrice.getText().replaceAll(",", "")))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Retail Price");
                error = true;
                lblRetailPrice.setForeground(Color.orange);

            }
            if (!isFloat(ftfWalkinPrice.getText().replaceAll(",", "")))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Walk in Price");
                error = true;
                lblWalkinPrice.setForeground(Color.orange);

            }
            if (!isFloat(ftfLastCost.getText().replaceAll(",", "")))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Last Cost Price");
                error = true;
                lblLastCost.setForeground(Color.orange);

            }

            if (Integer.parseInt(ftfStockMinimum.getText().replaceAll(",", ""))<0)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Stock Minimum");
                error = true;
                lblStockMinimum.setForeground(Color.orange);
            }
       
            if (Float.valueOf(ftfSisterCompanyPrice.getText().replaceAll(",", ""))<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Sister Company Price");
                error = true;
                lblSisterCompanyPrice.setForeground(Color.orange);
            }
            if (Float.parseFloat(ftfRetailPrice.getText().replaceAll(",", ""))<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Retail Price");
                error = true;
                lblRetailPrice.setForeground(Color.orange);
            }
            if (Float.parseFloat(ftfWalkinPrice.getText().replaceAll(",", ""))<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Walk in Price");
                error = true;
                lblWalkinPrice.setForeground(Color.orange);
            }
            if (Float.parseFloat(ftfLastCost.getText().replaceAll(",", ""))<0.00f)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid Last Cost");
                error = true;
                lblLastCost.setForeground(Color.orange);
            }
            if (error == false)
            {
                try
                {
                    
                    al.add(tfPartNumber.getText());
                    al.add(tfDescription.getText());
                    al.add(tfRackLocation.getText());
                    al.add(ftfStockMinimum.getText().replaceAll(",", ""));
                    al.add(ftfSisterCompanyPrice.getText().replaceAll(",", ""));
                    al.add(ftfRetailPrice.getText().replaceAll(",", ""));
                    al.add(ftfWalkinPrice.getText().replaceAll(",", ""));
                    al.add(ftfLastCost.getText().replaceAll(",", ""));
                    al.add(taNotes.getText());
                    if(imageLocation == null) 
                        al.add(imageLocation);    //set new
                    if(chckbxInactiveItem.isSelected())
                        al.add("0");
                    else al.add("1");
                    mainController.AddItem(tfPartNumber.getText(), tfDescription.getText(), tfRackLocation.getText(), ftfStockMinimum.getText(), ftfSisterCompanyPrice.getText(), ftfRetailPrice.getText(), ftfWalkinPrice.getText(), ftfLastCost.getText(), taNotes.getText(), imageLocation, Boolean.toString(chckbxInactiveItem.isSelected()));
                    controller.changePanelToInventory();
                    
                } catch (Exception ex)
                {
                    Logger.getLogger(AddItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                       
                    } catch (Exception ex)
                    {
                        Logger.getLogger(ViewItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
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
        
        public void setMainController(InventoryController inventoryController)
        {
                this.mainController = inventoryController;
        }
        
        public static void main(String args[])
        {
                GUIController temp=new GUIController();
                temp.changePanelToAddItemProfile();
        }
        
}

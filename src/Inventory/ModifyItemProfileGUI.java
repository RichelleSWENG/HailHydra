package Inventory;

import HailHydra.GUIController;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ModifyItemProfileGUI extends ItemProfileGUI
{
    private JButton btnSubmit, btnCancel;
    private ArrayList<String> al;
    private GUIController guiController;
    private InventoryController mainController;
    private String imagePath;
    
    public ModifyItemProfileGUI(GUIController temp, InventoryController InventoryController) throws IOException
    {
        setMainController(InventoryController);
                guiController= temp; 
                
                lblHeader.setText("Modify Item Profile");
               
                ftfSisterCompanyPrice.setEditable(false);
                ftfRetailPrice.setEditable(false);
                ftfWalkinPrice.setEditable(false);
                ftfLastCost.setEditable(false);
                
                ArrayList itemProfile = mainController.getItemProfile(); //get the created array list to be placed on this view

                tfPartNumber.setText(itemProfile.get(0).toString());
                tfDescription.setText(itemProfile.get(1).toString());
                tfRackLocation.setText(itemProfile.get(2).toString());
                ftfStockMinimum.setText(itemProfile.get(3).toString());
                ftfSisterCompanyPrice.setText(itemProfile.get(4).toString());
                ftfRetailPrice.setText(itemProfile.get(5).toString());
                ftfWalkinPrice.setText(itemProfile.get(6).toString());
                ftfLastCost.setText(itemProfile.get(7).toString());
                taNotes.setText(itemProfile.get(8).toString());
                ImageIcon icon;
                if(!"null".equals(itemProfile.get(9)))
                {
                File ImageFile = new File(itemProfile.get(9).toString());
            Image image = null;
            image = ImageIO.read(ImageFile);
            Image resizedImage = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), 0);//resize pic to fit JLabel
            icon = new ImageIcon(resizedImage);
            lblImage.setIcon(icon);
                }
                imagePath = itemProfile.get(9).toString();
                        
                btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(new ActionListener()
                {
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
                            if(imageLocation == null) //if image is not changed
                            al.add(imagePath);        //set previous
                            else 
                            al.add(imageLocation);    //set new
                            if(chckbxInactiveItem.isSelected())
                                al.add("0");
                            else al.add("1");
                            
                            boolean error = false;
            if (tfPartNumber.getText().equals("") || tfDescription.getText().equals("") || ftfStockMinimum.getText().equals(""))
            {

                JOptionPane.showMessageDialog(null, "Please fill in the required fields");
                error = true;
            }

            if (error == false)
            {
                try
                {
                    mainController.ModifyItemProfile(al);
                    mainController.setItemProfile(al);
                    guiController.changePanelToViewItemProfile();
                    
                } catch (Exception ex)
                {
                    Logger.getLogger(AddItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                       
                    } catch (Exception ex)
                    {
                        Logger.getLogger(ViewItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }});
                
                btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
                btnCancel.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            try
                            {
                                // if(JOptionPane.showConfirmDialog(null, "All inputted data will be disregarded. Are you sure you want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                                guiController.changePanelToViewItemProfile();
                            } catch (IOException ex)
                            {
                                Logger.getLogger(ModifyItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
        
    
}
    
    public void setMainController(InventoryController temp)
    {
        this.mainController = temp;        
    }
    
    public static void main(String args[]) throws IOException
    {
                    GUIController temp=new GUIController();
                    temp.changePanelToModifyItemProfile();
     }
}

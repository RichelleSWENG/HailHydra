package Inventory;

import HailHydra.GUIController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ViewItemProfileGUI extends ItemProfileGUI
{
    private JButton btnModify, btnClose;
    private ArrayList<String> al;
    private GUIController guiController;
    private InventoryController mainController;
        
    public ViewItemProfileGUI(GUIController temp,InventoryController InventoryController) throws IOException
    {
                setMainController(InventoryController);
                guiController= temp; 
                
                lblHeader.setText("View Item Profile");
                
                tfPartNumber.setEditable(false);
                tfDescription.setEditable(false);
                tfRackLocation.setEditable(false);
                ftfStockMinimum.setEditable(false);
                ftfSisterCompanyPrice.setEditable(false);
                ftfRetailPrice.setEditable(false);
                ftfWalkinPrice.setEditable(false);
                ftfLastCost.setEditable(false);
                taNotes.setEditable(false);
                chckbxInactiveItem.setEnabled(false);
                btnUpdateImage.setEnabled(false);
                //"SELECT part_num, description, rack_location, stock_minimum, sister_company_price, traders_price, walk_in_price, last_cost, notes, image, status FROM item WHERE part_num = '"+ID+"'";
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
                    try{
                        File ImageFile = new File(itemProfile.get(9).toString());
                        Image image = null;
                        image = ImageIO.read(ImageFile);
                        Image resizedImage = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), 0);//resize pic to fit JLabel
                        icon = new ImageIcon(resizedImage);
                        lblImage.setIcon(icon);
                    }catch(Exception e)
                    {
                        
                    }
                }
                if((itemProfile.get(10)).equals("0"))
                chckbxInactiveItem.setSelected(true);
                
                btnModify = new JButton("Modify");
		btnModify.setFont(fntPlainText);
		btnModify.setBounds(655, 545, 110, 40);
		add(btnModify);
                btnModify.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        guiController.changePanelToModifyItemProfile();
                    } catch (IOException ex)
                    {
                        Logger.getLogger(ViewItemProfileGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }});
                
                btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
                btnClose.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent e) 
                {
                    try {
                        guiController.changePanelToInventory();
                    } catch (Exception ex) {
                        ex.printStackTrace();
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
                    temp.changePanelToViewItemProfile();
     }
    
}

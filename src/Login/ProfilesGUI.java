package Login;


import HailHydra.GUIController;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfilesGUI extends JPanel {

	private JButton btnCompanyProfile, btnSetInventoryLast, btnInventory, btnSetInventorySellingPrice, btnSetInventoryQuantity;
	private Font fntPlainText;
        private GUIController controller;
        
	public ProfilesGUI(GUIController temp) {
                controller=temp;
            
		setBounds(0, 0, 700, 400);
		setBackground(SystemColor.controlHighlight);
		setLayout(null);
		
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                
		btnCompanyProfile = new JButton("Account Profile");
		btnCompanyProfile.setFont(fntPlainText);
		btnCompanyProfile.setBounds(165, 90, 300, 40);
		add(btnCompanyProfile);
                btnCompanyProfile.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToAccountProfile();
                       
                        }
                    });
		
		btnInventory = new JButton("Inventory");
		btnInventory.setFont(fntPlainText);
		btnInventory.setBounds(165, 150, 300, 40);
		add(btnInventory);
		btnInventory.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                            try
                            {
                                controller.changePanelToInventory();
                            } catch (Exception ex)
                            {
                               ex.printStackTrace();
                            }
                       
                        }
                    });
                        
		btnSetInventorySellingPrice = new JButton("Set Inventory Selling Price");
		btnSetInventorySellingPrice.setFont(fntPlainText);
		btnSetInventorySellingPrice.setBounds(165, 210, 300, 40);
		add(btnSetInventorySellingPrice);
                btnSetInventorySellingPrice.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToSetInventorySellingPrice();
                       
                        }
                    });
		
		btnSetInventoryQuantity = new JButton("Set Inventory Quantity");
		btnSetInventoryQuantity.setFont(fntPlainText);
		btnSetInventoryQuantity.setBounds(165, 270, 300, 40);
		add(btnSetInventoryQuantity);
		btnSetInventoryQuantity.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e){
                          controller.changePanelToSetInventoryQuantity();
                   
                    }
                });
		
		btnSetInventoryLast = new JButton("Set Inventory Last Cost");
		btnSetInventoryLast.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSetInventoryLast.setBounds(165, 330, 300, 40);
		add(btnSetInventoryLast);
		btnSetInventoryLast.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e){
                          controller.changePanelToSetInventoryLastCost();
                   
                    }
                });
	}
}

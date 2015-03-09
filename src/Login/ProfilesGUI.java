package Login;


import HailHydra.GUIController;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilesGUI extends JPanel {

	private JButton btnCompanyProfile, btnInventory, btnSetInventoryPrice, btnSetInventoryQuantity;
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
		btnCompanyProfile.setBounds(190, 90, 200, 40);
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
		btnInventory.setBounds(190, 150, 200, 40);
		add(btnInventory);
		btnInventory.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToInventory();
                       
                        }
                    });
                        
		btnSetInventoryPrice = new JButton("Set Inventory Price");
		btnSetInventoryPrice.setFont(fntPlainText);
		btnSetInventoryPrice.setBounds(165, 210, 250, 40);
		add(btnSetInventoryPrice);
                btnSetInventoryPrice.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToSetInventoryPrice();
                       
                        }
                    });
		
		btnSetInventoryQuantity = new JButton("Set Inventory Quantity");
		btnSetInventoryQuantity.setFont(fntPlainText);
		btnSetInventoryQuantity.setBounds(165, 270, 250, 40);
		add(btnSetInventoryQuantity);
                btnSetInventoryQuantity.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToSetInventoryQuantity();
                       
                        }
                    });
	}
}

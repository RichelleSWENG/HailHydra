package Login;

import HailHydra.GUIController;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilesGUI extends JPanel
{

	private JButton btnCompanyProfile, btnSetInventoryLastCost, btnInventory,
			btnSetInventorySellingPrice, btnSetInventoryQuantity;
	private Font fntPlainText;
	private GUIController controller;

	public ProfilesGUI(GUIController temp)
	{
		controller = temp;

		setBounds(5, 5, 580, 390);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);

		btnCompanyProfile = new JButton("Account Profile");
		btnCompanyProfile.setFont(fntPlainText);
		btnCompanyProfile.setBounds(150, 60, 300, 40);
		add(btnCompanyProfile);
		btnCompanyProfile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToAccountProfile();

			}
		});

		btnInventory = new JButton("Inventory");
		btnInventory.setFont(fntPlainText);
		btnInventory.setBounds(150, 120, 300, 40);
		add(btnInventory);
		btnInventory.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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
		btnSetInventorySellingPrice.setBounds(150, 180, 300, 40);
		add(btnSetInventorySellingPrice);
		btnSetInventorySellingPrice.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToSetInventorySellingPrice();

			}
		});

		btnSetInventoryLastCost = new JButton("Set Inventory Last Cost");
		btnSetInventoryLastCost.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSetInventoryLastCost.setBounds(150, 240, 300, 40);
		add(btnSetInventoryLastCost);
		btnSetInventoryLastCost.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToSetInventoryLastCost();

			}
		});

		btnSetInventoryQuantity = new JButton("Set Inventory Quantity");
		btnSetInventoryQuantity.setFont(fntPlainText);
		btnSetInventoryQuantity.setBounds(150, 300, 300, 40);
		add(btnSetInventoryQuantity);
		btnSetInventoryQuantity.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToSetInventoryQuantity();

			}
		});

	}
}

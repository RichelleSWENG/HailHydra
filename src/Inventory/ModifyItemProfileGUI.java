package Inventory;

import HailHydra.GUIController;
import java.awt.Color;
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
import javax.swing.JOptionPane;

public class ModifyItemProfileGUI extends ItemProfileGUI
{
	private JButton btnSubmit, btnCancel;
	private ArrayList<String> al;
	private GUIController guiController;
	private InventoryController mainController;
	private String imagePath;

	public ModifyItemProfileGUI(GUIController temp,
			InventoryController InventoryController) throws IOException
	{
		setMainController(InventoryController);
		guiController = temp;

		lblHeader.setText("Modify Item Profile");
		al = new ArrayList();

		tfPartNumber.setEditable(false);
		ftfSisterCompanyPrice.setEditable(false);
		ftfRetailPrice.setEditable(false);
		ftfWalkinPrice.setEditable(false);
		ftfLastCost.setEditable(false);
		ftfSisterCompanyPrice
				.setToolTipText("Sister company price can be edited in set inventory price.");
		ftfRetailPrice
				.setToolTipText("Retail price can be edited in set inventory price.");
		ftfWalkinPrice
				.setToolTipText("Walk-in price can be edited in set inventory price.");
		ftfLastCost
				.setToolTipText("Last Cost can be edited in set inventory last cost.");

		ArrayList itemProfile = mainController.getItemProfile(); // get the
																	// created
																	// array
																	// list to
																	// be placed
																	// on this
																	// view

		tfPartNumber.setText(itemProfile.get(0).toString());
		tfDescription.setText(itemProfile.get(1).toString());
		tfRackLocation.setText(itemProfile.get(2).toString());
		ftfStockMinimum.setValue(Double.parseDouble(itemProfile.get(3)
				.toString()));
		ftfSisterCompanyPrice.setValue(Double.parseDouble(itemProfile.get(4)
				.toString()));
		ftfRetailPrice.setValue(Double.parseDouble(itemProfile.get(5)
				.toString()));
		ftfWalkinPrice.setValue(Double.parseDouble(itemProfile.get(6)
				.toString()));
		ftfLastCost.setValue(Double.parseDouble(itemProfile.get(7).toString()));
		taNotes.setText(itemProfile.get(8).toString());
		ImageIcon icon;
		if (!"null".equals(itemProfile.get(9)))
		{
			try
			{
				File ImageFile = new File(itemProfile.get(9).toString());
				Image image = null;
				image = ImageIO.read(ImageFile);
				Image resizedImage = image.getScaledInstance(
						lblImage.getWidth(), lblImage.getHeight(), 0);// resize
																		// pic
																		// to
																		// fit
																		// JLabel
				icon = new ImageIcon(resizedImage);
				lblImage.setIcon(icon);
			} catch (Exception e)
			{

			}
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
					// (part_num,description,rack_location,stock_minimum,sister_company_price,traders_price,walk_in_price,last_cost,notes,image,status)
					// ftfStockMinimum, ftfSisterCompanyPrice, ftfRetailPrice,
					// ftfWalkinPrice, ftfLastCost;
					al.removeAll(al);

					boolean error = false;
					if (tfPartNumber.getText().equals("")
							|| tfDescription.getText().equals("")
							|| ftfStockMinimum.getText().equals(""))
					{

						JOptionPane.showMessageDialog(null,
								"Please fill in the required fields");
						error = true;
					}

					if (tfPartNumber.getText().length() > 20)
					{
						JOptionPane
								.showMessageDialog(null,
										"Part Number can not exceed 20 characters. Please re-input the Part Number.");
						error = true;
						lblPartNumber.setForeground(Color.orange);
					}

					if (tfDescription.getText().length() > 100)
					{
						JOptionPane
								.showMessageDialog(null,
										"Description can not exceed 100 characters. Please re-input the Description.");
						error = true;
						lblDescription.setForeground(Color.orange);
					}

					if (tfRackLocation.getText().length() > 45)
					{
						JOptionPane
								.showMessageDialog(
										null,
										"Rack Location can not exceed 45 characters. Please re-input the Rack Location.");
						error = true;
						lblRackLocation.setForeground(Color.orange);
					}

					if (ftfStockMinimum.getText().length() > 11)
					{
						JOptionPane
								.showMessageDialog(
										null,
										"Stock Minimum can not exceed 11 characters. Please re-input the Stock Minimum.");
						error = true;
						lblStockMinimum.setForeground(Color.orange);
					}

					/*
					 * if(ftfSisterCompanyPrice.getText().length()>20) {
					 * JOptionPane.showMessageDialog(null,
					 * "Part Number can not exceed 20 characters. Please re-input the Part Number."
					 * ); error = true; }
					 * 
					 * if(ftfRetailPrice.getText().length()>20) {
					 * JOptionPane.showMessageDialog(null,
					 * "Part Number can not exceed 20 characters. Please re-input the Part Number."
					 * ); error = true; }
					 * 
					 * if(ftfWalkinPrice.getText().length()>20) {
					 * JOptionPane.showMessageDialog(null,
					 * "Part Number can not exceed 20 characters. Please re-input the Part Number."
					 * ); error = true; }
					 * 
					 * if(ftfLastCost.getText().length()>20) {
					 * JOptionPane.showMessageDialog(null,
					 * "Part Number can not exceed 20 characters. Please re-input the Part Number."
					 * ); error = true; }
					 */
					if (taNotes.getText().length() > 500)
					{
						JOptionPane
								.showMessageDialog(null,
										"Notes can not exceed 500 characters. Please re-input the Notes.");
						error = true;
						lblNotes.setForeground(Color.orange);
					}

					if (!isInteger(ftfStockMinimum.getText()
							.replaceAll(",", "")))
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid value for stock minimum");
						error = true;
						lblStockMinimum.setForeground(Color.orange);
					}
					if (!isFloat(ftfSisterCompanyPrice.getText().replaceAll(
							",", "")))
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Sister Company Price");
						error = true;
						lblSisterCompanyPrice.setForeground(Color.orange);
					}
					if (!isFloat(ftfRetailPrice.getText().replaceAll(",", "")))
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Retail Price");
						error = true;
						lblRetailPrice.setForeground(Color.orange);

					}
					if (!isFloat(ftfWalkinPrice.getText().replaceAll(",", "")))
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Walk in Price");
						error = true;
						lblWalkinPrice.setForeground(Color.orange);

					}
					if (!isFloat(ftfLastCost.getText().replaceAll(",", "")))
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Last Cost Price");
						error = true;
						lblLastCost.setForeground(Color.orange);

					}

					if (Integer.parseInt(ftfStockMinimum.getText().replaceAll(
							",", "")) < 0)
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Stock Minimum");
						error = true;
						lblStockMinimum.setForeground(Color.orange);
					}

					if (Float.valueOf(ftfSisterCompanyPrice.getText()
							.replaceAll(",", "")) < 0.00f)
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Sister Company Price");
						error = true;
						lblSisterCompanyPrice.setForeground(Color.orange);
					}
					if (Float.parseFloat(ftfRetailPrice.getText().replaceAll(
							",", "")) < 0.00f)
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Retail Price");
						error = true;
						lblRetailPrice.setForeground(Color.orange);
					}
					if (Float.parseFloat(ftfWalkinPrice.getText().replaceAll(
							",", "")) < 0.00f)
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Walk in Price");
						error = true;
						lblWalkinPrice.setForeground(Color.orange);
					}
					if (Float.parseFloat(ftfLastCost.getText().replaceAll(",",
							"")) < 0.00f)
					{
						JOptionPane.showMessageDialog(null,
								"Please enter a valid Last Cost");
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
							al.add(ftfStockMinimum.getText()
									.replaceAll(",", ""));
							al.add(ftfSisterCompanyPrice.getText().replaceAll(
									",", ""));
							al.add(ftfRetailPrice.getText().replaceAll(",", ""));
							al.add(ftfWalkinPrice.getText().replaceAll(",", ""));
							al.add(ftfLastCost.getText().replaceAll(",", ""));
							al.add(taNotes.getText());
							if (imageLocation == null) // if image is not
														// changed
								al.add(imagePath); // set previous
							else
								al.add(imageLocation); // set new
							if (chckbxInactiveItem.isSelected())
								al.add("0");
							else
								al.add("1");
							mainController.setItemProfile(al);
							mainController.ModifyItemProfile(al);
							guiController.changePanelToViewItemProfile();

						} catch (Exception ex)
						{
							Logger.getLogger(AddItemProfileGUI.class.getName())
									.log(Level.SEVERE, null, ex);
						}
					}

				} catch (Exception ex)
				{
					Logger.getLogger(ViewItemProfileGUI.class.getName()).log(
							Level.SEVERE, null, ex);
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
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if (JOptionPane
							.showConfirmDialog(
									null,
									"All inputted data will be disregarded. Are you sure you want to cancel?",
									"Cancel", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						guiController.changePanelToViewItemProfile();
				} catch (IOException ex)
				{
					Logger.getLogger(ModifyItemProfileGUI.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void setMainController(InventoryController temp)
	{
		this.mainController = temp;
	}

}

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ItemProfileGUI extends JPanel
{
	private JPanel pnlItemDetails, pnlItemPrices;
	private TitledBorder tbItemDetails, tbItemPrices;
	private Border bImage;
	protected JLabel lblHeader, lblPartNumber, lblDescription, lblRackLocation,
			lblNotes, lblRequiredFields, lblImage, lblStockMinimum,
			lblSisterCompanyPrice, lblRetailPrice, lblWalkinPrice, lblLastCost,
			lblAsterisk1, lblAsterisk2, lblAsterisk3, lblAsterisk4;
	protected JTextField tfPartNumber, tfDescription, tfRackLocation;
	protected JFormattedTextField ftfStockMinimum, ftfSisterCompanyPrice,
			ftfRetailPrice, ftfWalkinPrice, ftfLastCost;
	protected JTextArea taNotes;
	protected JScrollPane spNotes;
	protected JCheckBox chckbxInactiveItem;
	protected JButton btnUpdateImage;
	protected Font fntPlainText, fntHeaderText;
	protected String imageLocation;

	public ItemProfileGUI()
	{
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);

		pnlItemDetails = new JPanel();
		pnlItemDetails.setBounds(30, 100, 460, 200);
		pnlItemDetails.setLayout(null);
		add(pnlItemDetails);
		tbItemDetails = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Item Details");
		tbItemDetails.setTitleJustification(TitledBorder.LEFT);
		tbItemDetails.setTitleFont(fntPlainText);
		pnlItemDetails.setBorder(tbItemDetails);

		pnlItemPrices = new JPanel();
		pnlItemPrices.setBounds(30, 310, 460, 200);
		pnlItemPrices.setLayout(null);
		add(pnlItemPrices);
		tbItemPrices = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Item Prices");
		tbItemPrices.setTitleJustification(TitledBorder.LEFT);
		tbItemPrices.setTitleFont(fntPlainText);
		pnlItemPrices.setBorder(tbItemPrices);

		lblHeader = new JLabel("");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblPartNumber = new JLabel("Part Number:");
		lblPartNumber.setFont(fntPlainText);
		lblPartNumber.setBounds(40, 30, 145, 30);
		pnlItemDetails.add(lblPartNumber);

		lblDescription = new JLabel("Description:");
		lblDescription.setFont(fntPlainText);
		lblDescription.setBounds(50, 70, 145, 30);
		pnlItemDetails.add(lblDescription);

		lblRackLocation = new JLabel("Rack Location:");
		lblRackLocation.setFont(fntPlainText);
		lblRackLocation.setBounds(20, 110, 173, 30);
		pnlItemDetails.add(lblRackLocation);

		lblStockMinimum = new JLabel("Stock Minimum:");
		lblStockMinimum.setFont(fntPlainText);
		lblStockMinimum.setBounds(10, 150, 173, 30);
		pnlItemDetails.add(lblStockMinimum);

		lblSisterCompanyPrice = new JLabel("Sister Company Price:");
		lblSisterCompanyPrice.setFont(fntPlainText);
		lblSisterCompanyPrice.setBounds(10, 30, 238, 30);
		pnlItemPrices.add(lblSisterCompanyPrice);

		lblRetailPrice = new JLabel("Retail Price:");
		lblRetailPrice.setFont(fntPlainText);
		lblRetailPrice.setBounds(105, 70, 130, 30);
		pnlItemPrices.add(lblRetailPrice);

		lblWalkinPrice = new JLabel("Walk-in Price:");
		lblWalkinPrice.setFont(fntPlainText);
		lblWalkinPrice.setBounds(88, 110, 160, 30);
		pnlItemPrices.add(lblWalkinPrice);

		lblLastCost = new JLabel("Last Cost:");
		lblLastCost.setFont(fntPlainText);
		lblLastCost.setBounds(125, 150, 113, 30);
		pnlItemPrices.add(lblLastCost);

		lblNotes = new JLabel("Notes:");
		lblNotes.setFont(fntPlainText);
		lblNotes.setBounds(515, 400, 95, 30);
		add(lblNotes);

		lblRequiredFields = new JLabel("* Required Fields");
		lblRequiredFields.setFont(fntPlainText);
		lblRequiredFields.setForeground(Color.RED);
		lblRequiredFields.setBounds(30, 520, 200, 28);
		add(lblRequiredFields);

		lblImage = new JLabel("");
		bImage = BorderFactory.createLineBorder(Color.BLACK);
		lblImage.setBorder(bImage);
		lblImage.setForeground(Color.GRAY);
		lblImage.setBackground(Color.WHITE);
		lblImage.setBounds(580, 80, 355, 270);
		add(lblImage);

		lblAsterisk1 = new JLabel("*");
		lblAsterisk1.setForeground(Color.RED);
		lblAsterisk1.setFont(fntPlainText);
		lblAsterisk1.setBounds(30, 30, 19, 21);
		pnlItemDetails.add(lblAsterisk1);

		lblAsterisk2 = new JLabel("*");
		lblAsterisk2.setForeground(Color.RED);
		lblAsterisk2.setFont(fntPlainText);
		lblAsterisk2.setBounds(40, 70, 19, 21);
		pnlItemDetails.add(lblAsterisk2);

		lblAsterisk3 = new JLabel("*");
		lblAsterisk3.setForeground(Color.RED);
		lblAsterisk3.setFont(fntPlainText);
		lblAsterisk3.setBounds(5, 150, 19, 21);
		pnlItemDetails.add(lblAsterisk3);

		tfPartNumber = new JTextField();
		tfPartNumber.setFont(fntPlainText);
		tfPartNumber.setBounds(170, 30, 280, 30);
		pnlItemDetails.add(tfPartNumber);

		tfDescription = new JTextField();
		tfDescription.setFont(fntPlainText);
		tfDescription.setBounds(170, 70, 280, 30);
		pnlItemDetails.add(tfDescription);

		tfRackLocation = new JTextField();
		tfRackLocation.setFont(fntPlainText);
		tfRackLocation.setBounds(170, 110, 280, 30);
		pnlItemDetails.add(tfRackLocation);

		ftfStockMinimum = new JFormattedTextField(new DecimalFormat("#,##0"));
		ftfStockMinimum.setFont(fntPlainText);
		ftfStockMinimum.setHorizontalAlignment(JTextField.RIGHT);
		ftfStockMinimum.setValue(new Float(0));
		ftfStockMinimum.setBounds(170, 150, 280, 30);
		pnlItemDetails.add(ftfStockMinimum);

		ftfSisterCompanyPrice = new JFormattedTextField(new DecimalFormat(
				"#,##0.00"));
		ftfSisterCompanyPrice.setFont(fntPlainText);
		ftfSisterCompanyPrice.setHorizontalAlignment(JTextField.RIGHT);
		ftfSisterCompanyPrice.setValue(new Float(00.0F));
		ftfSisterCompanyPrice.setBounds(230, 30, 220, 30);
		pnlItemPrices.add(ftfSisterCompanyPrice);

		ftfRetailPrice = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfRetailPrice.setFont(fntPlainText);
		ftfRetailPrice.setHorizontalAlignment(JTextField.RIGHT);
		ftfRetailPrice.setValue(new Float(00.0F));
		ftfRetailPrice.setBounds(230, 70, 220, 30);
		pnlItemPrices.add(ftfRetailPrice);

		ftfWalkinPrice = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfWalkinPrice.setFont(fntPlainText);
		ftfWalkinPrice.setHorizontalAlignment(JTextField.RIGHT);
		ftfWalkinPrice.setValue(new Float(00.0F));
		ftfWalkinPrice.setBounds(230, 110, 220, 30);
		pnlItemPrices.add(ftfWalkinPrice);

		ftfLastCost = new JFormattedTextField(new DecimalFormat("#,##0.00"));
		ftfLastCost.setFont(fntPlainText);
		ftfLastCost.setHorizontalAlignment(JTextField.RIGHT);
		ftfLastCost.setValue(new Float(00.0F));
		ftfLastCost.setBounds(230, 150, 220, 30);
		pnlItemPrices.add(ftfLastCost);

		taNotes = new JTextArea();
		taNotes.setFont(fntPlainText);
		taNotes.setWrapStyleWord(true);
		taNotes.setLineWrap(true);
		add(taNotes);

		spNotes = new JScrollPane(taNotes);
		spNotes.setBounds(515, 430, 450, 80);
		add(spNotes);

		chckbxInactiveItem = new JCheckBox("Inactive Item");
		chckbxInactiveItem.setFont(fntPlainText);
		chckbxInactiveItem.setBounds(30, 70, 182, 30);
		add(chckbxInactiveItem);

		btnUpdateImage = new JButton("Update Image");
		btnUpdateImage.setFont(fntPlainText);
		btnUpdateImage.setBounds(670, 360, 190, 40);
		add(btnUpdateImage);
		btnUpdateImage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					JFileChooser fileOpen = new JFileChooser();
					String[] suffices = ImageIO.getReaderFileSuffixes();
					for (int i = 0; i < suffices.length; i++)
					{
						FileFilter filter = new FileNameExtensionFilter(
								suffices[i] + " files", suffices[i]);
						fileOpen.addChoosableFileFilter(filter);
					}
					int ret = fileOpen.showDialog(null, "Open file");
					imageLocation = fileOpen.getSelectedFile()
							.getAbsolutePath();
					imageLocation = imageLocation.replace('\\', '/');
					BufferedImage img = null;
					try
					{
						img = ImageIO.read(new File(fileOpen.getSelectedFile()
								.getAbsolutePath()));
						Image dimg = img.getScaledInstance(lblImage.getWidth(),
								lblImage.getHeight(), Image.SCALE_DEFAULT);
						ImageIcon imageIcon = new ImageIcon(dimg);
						lblImage.setIcon(imageIcon);
					} catch (Exception ioe)
					{
						JOptionPane
								.showMessageDialog(null,
										"Selected file is not an image. Please try again.");
					}

				} catch (Exception exc)
				{

				}

			}
		});
	}

	public static void main(String args[]) throws IOException
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddItemProfile();
	}
}

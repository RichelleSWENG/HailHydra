package AccountProfile;

import HailHydra.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AddAccountProfileGUI extends AccountProfileGUI
{

	private JButton btnSubmit, btnCancel;
	private GUIController guiController;
	private AccountProfileController mainController;
	private ArrayList<String> al;

	public AddAccountProfileGUI(GUIController temp)
	{
		super();
		guiController = temp;

		lblHeader.setText("Add Account Profile");
		al = new ArrayList();

		// The following code are for JButtons
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean error = false;
                                
                                if (mainController.checkDuplicate(tfName.getText(), type.getSelection().getActionCommand()
								.toString()))
                                {
                                    JOptionPane.showMessageDialog(null,
							"There is already an account with the same name and type. Please change either its name or type.");
                                    error = true;
                                }

				if (tfName.getText().equals("")
						|| ftfCreditLimit.getText().equals("")
						|| ftfTerms.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,
							"Please fill-up all required fields.");
					error = true;
				}

				if (tfName.getText().length() > 50)
				{
					JOptionPane
							.showMessageDialog(null,
									"Name can not exceed 50 characters. Please re-input the name.");
					error = true;
				}

				if (taAddress.getText().length() > 100)
				{
					JOptionPane
							.showMessageDialog(null,
									"Address can not exceed 100 characters. Please re-input the address.");
					error = true;
				}

				if (tfCity.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"City can not exceed 45 characters. Please re-input the city.");
					error = true;
				}

				if (tfCountry.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Country can not exceed 45 characters. Please re-input the country.");
					error = true;
				}

				if (tfPostCode.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Postcode can not exceed 45 characters. Please re-input the postcode.");
					error = true;
				}

				if (Integer.parseInt(ftfTerms.getText().replaceAll(",", "")) > 2000)
				{
					JOptionPane
							.showMessageDialog(null,
									"Terms can not be greater than 2,000. Please re-input the terms.");
					error = true;
				}

				if (tfPhone1.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Phone #1 can not exceed 11 characters. Please re-input the phone #1.");
					error = true;
				}

				if (tfPhone2.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Phone #2 can not exceed 11 characters. Please re-input the phone #2.");
					error = true;
				}

				if (tfPhone3.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Phone #3 can not exceed 11 characters. Please re-input the phone #3.");
					error = true;
				}

				if (tfFaxNumber.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Fax Number can not exceed 11 characters. Please re-input fax number.");
					error = true;
				}

				if (tfEmailAddress.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Email address can not exceed 45 characters. Please re-input email.");
					error = true;
				}

				if (tfWebsite.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Website can not exceed 45 characters. Please re-input website.");
					error = true;
				}

				if (tfContactPerson.getText().length() > 45)
				{
					JOptionPane
							.showMessageDialog(null,
									"Contact person can not exceed 45 characters. Please re-input contact person.");
					error = true;
				}

				if (ftfCreditLimit.getText().length() > 12)
				{
					JOptionPane.showMessageDialog(null,
							"Credit Limit can not exceed 9,999,999");
					error = true;
				}

				if (Float.parseFloat(ftfCreditLimit.getText().replaceAll(",",
						"")) < 0.00f)
				{
					JOptionPane.showMessageDialog(null,
							"Credit Limit is invalid.");
					error = true;
				}

				if (!"".equals(tfPhone1.getText()))
					if (!isInteger(tfPhone1.getText())
							|| Integer.parseInt(tfPhone1.getText()) < 0)
					{
						JOptionPane.showMessageDialog(null,
								"Phone#1  is invalid.");
						error = true;
					}

				if (!"".equals(tfPhone2.getText()))
					if (!isInteger(tfPhone2.getText())
							|| Integer.parseInt(tfPhone2.getText()) < 0)
					{
						JOptionPane.showMessageDialog(null,
								"Phone#2 is invalid.");
						error = true;
					}

				if (!tfPhone3.getText().isEmpty())
					if (!isInteger(tfPhone3.getText())
							|| Integer.parseInt(tfPhone3.getText()) < 0)
					{
						JOptionPane.showMessageDialog(null,
								"Phone#3 is invalid.");
						error = true;
					}

				if (!"".equals(tfFaxNumber.getText()))
					if (!isInteger(tfFaxNumber.getText())
							|| Integer.parseInt(tfFaxNumber.getText()) < 0)
					{
						JOptionPane.showMessageDialog(null,
								"Fax Number is invalid.");
						error = true;
					}

				if (hasSpecial(tfPostCode.getText()))
				{
					JOptionPane.showMessageDialog(null,
							"Please enter a valid postal code.");
					error = true;
				}

				if (error == false)
				{
                                            int dialogButton = JOptionPane.YES_NO_OPTION;
                                            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to save the following information?","Confirmation Message",dialogButton);
                                            if(dialogResult == JOptionPane.YES_OPTION){
                                            try
                                            {
                                                    
                                                    al.removeAll(al);

                                                    al.add(tfName.getText().toString());
                                                    al.add(taAddress.getText().toString());
                                                    al.add(tfCity.getText().toString());
                                                    al.add(tfPostCode.getText().toString());
                                                    al.add(tfCountry.getText().toString());
                                                    al.add(ftfCreditLimit.getText().replaceAll(",", ""));
                                                    al.add(ftfTerms.getText().replaceAll(",", ""));
                                                    al.add(tfPhone1.getText().toString());
                                                    al.add(tfPhone2.getText().toString());
                                                    al.add(tfPhone3.getText().toString());
                                                    al.add(tfFaxNumber.getText().toString());
                                                    al.add(tfEmailAddress.getText().toString());
                                                    al.add(tfWebsite.getText().toString());
                                                    al.add(tfContactPerson.getText().toString());
                                                    al.add(type.getSelection().getActionCommand()
                                                                    .toString());

                                                    if (chckbxInactiveAccount.isSelected())
                                                            al.add("Inactive");
                                                    else
                                                            al.add("Active");

                                                    mainController.AddAccountProfile(al); // add the account
                                            } catch (Exception exception)
                                            {
                                                    exception.getStackTrace();
                                            }
                                            guiController.changePanelToAccountProfile();
                                        }
				}

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
				guiController.changePanelToAccountProfile();
			}
		});

	}

	public void setMainController(AccountProfileController temp)
	{
		mainController = temp;
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
		{
			return true;
		} else
		{
			return false;
		}
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddAccountProfile();
	}
}

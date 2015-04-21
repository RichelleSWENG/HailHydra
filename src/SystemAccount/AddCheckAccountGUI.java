package SystemAccount;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class AddCheckAccountGUI extends JPanel
{

	private JLabel lblHeader, lblBankName, lblAccountNumber, lblAccountName,
			lblBankBranch, lblAccountNumberStatus, lblRequiredFields,
			lblAsterisk1, lblAsterisk2, lblAsterisk3;
	private JTextField tfAccountName, tfAccountNumber, tfBankName;
	private JTextArea taBankBranch;
	private String strHeader[] = { "Account Name", "  Account Number  ",
			"Bank Name", "        Bank Branch       " };
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbCheckAccount;
	private JScrollPane spTable;
	private JButton btnSubmit, btnCancel;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController controller;
	private SystemAccountController sysController;
	private ArrayList<String> checkAccount;
	private JScrollPane spBankBranch;

	public AddCheckAccountGUI(GUIController temp, SystemAccountController sys)
	{
		controller = temp;
		sysController = sys;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Add Check Account");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblAccountName = new JLabel("Account Name:");
		lblAccountName.setFont(fntPlainText);
		lblAccountName.setBounds(30, 80, 155, 30);
		add(lblAccountName);

		lblAsterisk1 = new JLabel("*");
		lblAsterisk1.setForeground(Color.RED);
		lblAsterisk1.setFont(fntPlainText);
		lblAsterisk1.setBounds(30, 120, 180, 30);
		add(lblAsterisk1);

		lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(fntPlainText);
		lblAccountNumber.setBounds(40, 120, 180, 30);
		add(lblAccountNumber);

		lblAccountNumberStatus = new JLabel("");
		lblAccountNumberStatus.setFont(fntPlainText);
		lblAccountNumberStatus.setForeground(Color.RED);
		lblAccountNumberStatus.setBounds(720, 120, 250, 30);
		add(lblAccountNumberStatus);

		lblAsterisk2 = new JLabel("*");
		lblAsterisk2.setForeground(Color.RED);
		lblAsterisk2.setFont(fntPlainText);
		lblAsterisk2.setBounds(30, 160, 180, 30);
		add(lblAsterisk2);

		lblBankName = new JLabel("Bank Name:");
		lblBankName.setFont(fntPlainText);
		lblBankName.setBounds(40, 160, 128, 30);
		add(lblBankName);

		lblAsterisk3 = new JLabel("*");
		lblAsterisk3.setForeground(Color.RED);
		lblAsterisk3.setFont(fntPlainText);
		lblAsterisk3.setBounds(30, 200, 180, 30);
		add(lblAsterisk3);

		lblBankBranch = new JLabel("Bank Branch:");
		lblBankBranch.setFont(fntPlainText);
		lblBankBranch.setBounds(40, 200, 135, 30);
		add(lblBankBranch);

		lblRequiredFields = new JLabel("* Required Fields");
		lblRequiredFields.setForeground(Color.RED);
		lblRequiredFields.setFont(fntPlainText);
		lblRequiredFields.setBounds(30, 300, 183, 30);
		add(lblRequiredFields);

		tfAccountName = new JTextField();
		tfAccountName.setFont(fntPlainText);
		tfAccountName.setBounds(210, 80, 500, 30);
		add(tfAccountName);

		tfAccountNumber = new JTextField();
		tfAccountNumber.setFont(fntPlainText);
		tfAccountNumber
				.setToolTipText("Only accepts numeric values and dash. Can not start with a dash.");
		tfAccountNumber.setBounds(210, 120, 500, 30);
		add(tfAccountNumber);
		tfAccountNumber.getDocument().addDocumentListener(
				new DocumentListener()
				{
					public void changedUpdate(DocumentEvent documentEvent)
					{
						changeStatus();
					}

					public void insertUpdate(DocumentEvent documentEvent)
					{
						changeStatus();
					}

					public void removeUpdate(DocumentEvent documentEvent)
					{
						changeStatus();
					}

					private void changeStatus()
					{
						if (checkAccountNumber() == false)
							lblAccountNumberStatus
									.setText("Invalid account number.");
						else
							lblAccountNumberStatus.setText("");
					}
				});

		tfBankName = new JTextField();
		tfBankName.setFont(fntPlainText);
		tfBankName.setBounds(210, 160, 500, 30);
		add(tfBankName);

		taBankBranch = new JTextArea();
		taBankBranch.setFont(fntPlainText);
		taBankBranch.setWrapStyleWord(true);
		taBankBranch.setLineWrap(true);

		spBankBranch = new JScrollPane(taBankBranch);
		spBankBranch.setBounds(30, 230, 680, 60);
		add(spBankBranch);

		tbCheckAccount = new JTable()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}

			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column)
			{
				component = super.prepareRenderer(renderer, row, column);
				modelRow = convertRowIndexToModel(row);
				if (!isRowSelected(modelRow))
				{
					component.setBackground(Color.WHITE);
				} else
				{
					component.setBackground(Color.yellow);
				}
				return component;
			}
		};

		tbCheckAccount.getTableHeader().setFont(fntHeaderTableText);
		tbCheckAccount.getTableHeader()
				.setPreferredSize(new Dimension(100, 55));
		tbCheckAccount.getTableHeader().setResizingAllowed(false);
		tbCheckAccount.setFont(fntPlainText);

		spTable = new JScrollPane(tbCheckAccount);
		spTable.setBounds(30, 340, 935, 190);
		add(spTable);

		tbCheckAccount.getParent()
				.setBackground(tbCheckAccount.getBackground());
		tbCheckAccount.getTableHeader().setResizingAllowed(false);
		tbCheckAccount.getTableHeader().setReorderingAllowed(false);
		tbCheckAccount.setColumnSelectionAllowed(true);
		tbCheckAccount.setRowSelectionAllowed(true);
		tbCheckAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCheckAccount.setRowHeight(30);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(655, 545, 110, 40);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                                if (!sysController.duplicateAccNum(tfAccountNumber.getText()))
				{
					if (checkAccountNumber())
					{
						if (tfAccountNumber.getText().toString().isEmpty())
						{
							JOptionPane
									.showMessageDialog(
											null,
											"<html><center>Account number is empty.<br>Please input an account number. </center></html>");
						} else if (tfBankName.getText().toString().isEmpty())
						{
							JOptionPane
									.showMessageDialog(
											null,
											"<html><center>Bank name is empty.<br>Please input a bank name. </center></html>");
						} else if (taBankBranch.getText().toString().isEmpty())
						{
							JOptionPane
									.showMessageDialog(
											null,
											"<html><center>Bank branch is empty.<br>Please input a bank branch. </center></html>");
						} else
						{
							if (checkDataLimit())
							{
                                                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                                                int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to save the following information?","Confirmation Message",dialogButton);
                                                                if(dialogResult == JOptionPane.YES_OPTION){
                                                                    checkAccount = new ArrayList<>();
                                                                    checkAccount.add(tfAccountNumber.getText());
                                                                    checkAccount.add(tfAccountName.getText());
                                                                    checkAccount.add(tfBankName.getText());
                                                                    checkAccount.add(taBankBranch.getText()
                                                                                    .toString());
                                                                    checkAccount.add("0");
                                                                    sysController.AddSystemAccount(checkAccount);
                                                                    setTableModel(sysController
                                                                                    .getSystemAccounts("0"));
                                                                    tfAccountNumber.setText("");
                                                                    tfAccountName.setText("");
                                                                    tfBankName.setText("");
                                                                    taBankBranch.setText("");
                                                                }
							}
						}
					} else
					{
						JOptionPane
								.showMessageDialog(
										null,
										"<html><center>Account number is invalid.<br>Account number only accepts numeric values and dash.<br> Also, account number can not start with a dash </center></html>");
					}

				} else
				{
					JOptionPane
						.showMessageDialog(
								null,
								"<html><center>Account Number has already been used.<br>Please input another Account Number.</center></html>");
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
				controller.changePanelToMainMenu();
			}
		});
		setTableModel(sysController.getSystemAccounts("0"));
	}

	public void setTableModel(TableModel temp)
	{
		tbCheckAccount.setModel(temp);
		JTableHeader th = tbCheckAccount.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
		tbCellRenderer = tbCheckAccount.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCheckAccount.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbCheckAccount, tbColumn.getHeaderValue(), false, false, 0,
					j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbCheckAccount.repaint();
	}

	public boolean checkAccountNumber()
	{
		boolean valid = true, previousdash = false;
		if (!tfAccountNumber.getText().toString().startsWith("-"))
		{
			for (char c : tfAccountNumber.getText().toString().toCharArray())
			{
				if (!Character.isDigit(c) && c != '-')
				{
					valid = false;
					break;
				}

				if (c == '-' && previousdash == true)
					valid = false;
				else if (c == '-' && previousdash == false)
					previousdash = true;
				else if (c != '-' && previousdash == true)
					previousdash = false;
			}
		} else
		{
			valid = false;
		}
		return valid;

	}

	public boolean checkDataLimit()
	{
		if (tfAccountName.getText().length() > 45)
		{
			JOptionPane
					.showMessageDialog(
							null,
							"Account name reached "
									+ tfAccountName.getText().length()
									+ " characters. Account name can not exceed 45 characters.");
			return false;
		} else if (tfAccountNumber.getText().length() > 45)
		{
			JOptionPane
					.showMessageDialog(
							null,
							"Account number reached "
									+ tfAccountNumber.getText().length()
									+ " characters. Account number can not exceed 45 characters.");
			return false;
		} else if (tfBankName.getText().length() > 45)
		{
			JOptionPane.showMessageDialog(null, "Bank name reached "
					+ tfBankName.getText().length()
					+ " characters. Bank name can not exceed 45 characters.");
			return false;
		} else if (taBankBranch.getText().toString().length() > 250)
		{
			JOptionPane
					.showMessageDialog(
							null,
							"Bank branch reached "
									+ taBankBranch.getText().length()
									+ " characters. Bank branch can not exceed 250 characters.");
			return false;
		}
		return true;
	}

	public static void main(String args[])
	{
		GUIController temp = new GUIController();
		temp.changePanelToAddCheckAccount();
	}

}

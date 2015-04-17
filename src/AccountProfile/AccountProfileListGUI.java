package AccountProfile;

import HailHydra.GUIController;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class AccountProfileListGUI extends JPanel 
{
	private JLabel lblHeader, lblDisplay, lblName, lblAccountsFound,
			lblNumOfAccountsFound;
	private JTextField tfSearch;
	private String strHeader[] = {
			"               Name               ",
			"                    Type                    ",
			"     Credit Limit     ", "<html><center>Terms<br>(Days)</center></html>" };
	private DefaultTableModel tbModel;
	private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
	private TableColumnModel tbColumnRenderer;
	private TableColumn tbColumn;
	private Component component;
	private JTable tbAccountProfile;
	private JScrollPane spTable;
	private JCheckBox chckbxCustomer, chckbxSupplier;
	private JButton btnViewAllAccounts, btnViewCompanyProfile,
			btnAddAccountProfile, btnClose;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
	private int modelRow;
	private GUIController GUIController;
	private AccountProfileController mainController;
	private int type;
	private String name;
	private String AccountType;

	public AccountProfileListGUI(GUIController temp)
        {

		GUIController = temp;
		setBounds(0, 0, 1000, 620);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);
		fntHeaderText = new Font("Arial", Font.BOLD, 40);
		fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Account Profile");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblDisplay = new JLabel("Display:");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 80, 100, 30);
		add(lblDisplay);

		lblName = new JLabel("Name:");
		lblName.setFont(fntPlainText);
		lblName.setBounds(30, 120, 83, 30);
		add(lblName);

		lblAccountsFound = new JLabel("Account/s Found: ");
		lblAccountsFound.setFont(fntPlainText);
		lblAccountsFound.setBounds(30, 160, 190, 30);
		add(lblAccountsFound);

		lblNumOfAccountsFound = new JLabel("0");
		lblNumOfAccountsFound.setFont(fntPlainText);
		lblNumOfAccountsFound.setBounds(210, 160, 250, 30);
		add(lblNumOfAccountsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(120, 120, 450, 30);
		add(tfSearch);

		tfSearch.getDocument().addDocumentListener(new DocumentListener() 
                {
			public void insertUpdate(DocumentEvent de) 
                        {
				try 
                                {
					done();
				} catch (Exception ex) 
                                {
					Logger.getLogger(AccountProfileListGUI.class.getName())
							.log(Level.SEVERE, null, ex);
				}
			}

			public void removeUpdate(DocumentEvent de) 
                        {
				try 
                                {
					done();
				} catch (Exception ex) 
                                {
					Logger.getLogger(AccountProfileListGUI.class.getName())
							.log(Level.SEVERE, null, ex);
				}
			}

			public void changedUpdate(DocumentEvent de) 
                        {
				try 
                                {
					done();
				} catch (Exception ex) 
                                {
					Logger.getLogger(AccountProfileListGUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			public void done() throws Exception 
                        {
				
                                if (tfSearch.getText().length() > 0) 
                                {
					if (chckbxCustomer.isSelected()&& chckbxSupplier.isSelected()) // both
						type = 0;
					else if (chckbxCustomer.isSelected()
							&& !chckbxSupplier.isSelected()) // customer
						type = 1;
					else if (!chckbxCustomer.isSelected()
							&& chckbxSupplier.isSelected()) // supplier
						type = 2;
					mainController.SearchSomething(tfSearch.getText(), type); // if
				

				} else if (tfSearch.getText().length() == 0) // if nothing is
				{
					if (chckbxCustomer.isSelected()
							&& chckbxSupplier.isSelected()) // both
						mainController.ViewAll();
					else if (chckbxCustomer.isSelected()
							&& !chckbxSupplier.isSelected()) // customer
						mainController.ViewCustomer();
					else if (!chckbxCustomer.isSelected()
							&& chckbxSupplier.isSelected()) // supplier
						mainController.ViewSupplier();

				}
			}
		});
                
                tbModel = new DefaultTableModel() 
                {
			public boolean isCellEditable(int rowIndex, int mColIndex) 
                        {
				return false;
			}
		};

		tbModel.setRowCount(0);

		for (int i = 0; i < strHeader.length; i++) 
                {
			tbModel.addColumn(strHeader[i]);
		}
             
		tbAccountProfile = new JTable(tbModel) 
                {
                        public boolean isCellEditable(int row, int column) 
                        {
                                return false;
                        }
			
			public TableCellRenderer getCellRenderer(int row, int column) 
                        {
				return new TableRenderer();
			}
			
			public Component prepareRenderer(TableCellRenderer renderer,int row, int column) 
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

                tbAccountProfile.getTableHeader().setFont(fntHeaderTableText);
		tbAccountProfile.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbAccountProfile.getTableHeader().setResizingAllowed(false);
		tbAccountProfile.setFont(fntPlainText);
                
		spTable = new JScrollPane(tbAccountProfile);
		spTable.setBounds(30, 205, 935, 320);
		add(spTable);

		tbCellRenderer = tbAccountProfile.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbAccountProfile.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1) 
                {
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(
					tbAccountProfile, tbColumn.getHeaderValue(), false, false,
					0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbAccountProfile.setFont(fntPlainText);
		tbAccountProfile.getTableHeader().setFont(fntHeaderTableText);
		tbAccountProfile.getParent().setBackground(
				tbAccountProfile.getBackground());
		tbAccountProfile.getTableHeader().setResizingAllowed(false);
		tbAccountProfile.getTableHeader().setReorderingAllowed(false);
		tbAccountProfile.setColumnSelectionAllowed(true);
		tbAccountProfile.setRowSelectionAllowed(true);
		tbAccountProfile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbAccountProfile.setRowHeight(30);

		chckbxCustomer = new JCheckBox("Customer");
		chckbxCustomer.setFont(fntPlainText);
		chckbxCustomer.setSelected(true);
		chckbxCustomer.setBounds(120, 80, 137, 30);
		add(chckbxCustomer);

		chckbxSupplier = new JCheckBox("Supplier");
		chckbxSupplier.setFont(fntPlainText);
		chckbxSupplier.setSelected(true);
		chckbxSupplier.setBounds(280, 80, 167, 30);
		add(chckbxSupplier);

		chckbxCustomer.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent ae) 
                        {
				tfSearch.setText("");
				if (chckbxCustomer.isSelected() && chckbxSupplier.isSelected()) {
					mainController.ViewAll();
				} else if (chckbxCustomer.isSelected()
						&& !chckbxSupplier.isSelected()) 
                                {
					mainController.ViewCustomer();
				} else if (!chckbxCustomer.isSelected()
						&& chckbxSupplier.isSelected())
                                {
					mainController.ViewSupplier();
				} else if (!chckbxCustomer.isSelected()
						&& !chckbxSupplier.isSelected()) 
                                {

					tbAccountProfile.setModel(tbModel);
					lblNumOfAccountsFound.setText("0");
				}

			}

		});

		chckbxSupplier.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent ae) 
                        {
				tfSearch.setText("");
				if (chckbxCustomer.isSelected() && chckbxSupplier.isSelected()) 
                                {
					mainController.ViewAll();
				} else if (chckbxCustomer.isSelected()&& !chckbxSupplier.isSelected()) 
                                {
					mainController.ViewCustomer();
				} else if (!chckbxCustomer.isSelected()&& chckbxSupplier.isSelected())
                                {
					mainController.ViewSupplier();
				} else if (!chckbxCustomer.isSelected()&& !chckbxSupplier.isSelected())
                                {
					tbAccountProfile.setModel(tbModel);
					lblNumOfAccountsFound.setText("0");
				}

			}

		});

		btnViewAllAccounts = new JButton("View All Accounts");
		btnViewAllAccounts.setFont(fntPlainText);
		btnViewAllAccounts.setBounds(725, 150, 240, 40);
		add(btnViewAllAccounts);
		btnViewAllAccounts.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				try 
                                {
					chckbxCustomer.setSelected(true);
					chckbxSupplier.setSelected(true);
					mainController.ViewAll();
					tfSearch.setText("");
				} catch (Exception ex) 
                                {
				}
			}
		});

		btnViewCompanyProfile = new JButton("View Account Profile");
		btnViewCompanyProfile.setFont(fntPlainText);
		btnViewCompanyProfile.setBounds(30, 545, 250, 40);
		add(btnViewCompanyProfile);
		btnViewCompanyProfile.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				int row;
				row = tbAccountProfile.getSelectedRow();

				if (row == -1)
					JOptionPane.showMessageDialog(null,"Please select an item.");
				else 
                                {
					name = tbAccountProfile.getValueAt(row, 0).toString();
					AccountType = tbAccountProfile.getValueAt(row, 1).toString();

					try 
                                        {

						mainController.ViewAccountProfile(name, AccountType);
					} catch (SQLException ex) 
                                        {
						Logger.getLogger(AccountProfileListGUI.class.getName())
								.log(Level.SEVERE, null, ex);
					}

					GUIController.changePanelToViewAccountProfile();
				}
			}

		});

		btnAddAccountProfile = new JButton("Add Account Profile");
		btnAddAccountProfile.setFont(fntPlainText);
		btnAddAccountProfile.setBounds(430, 545, 230, 40);
		add(btnAddAccountProfile);
		btnAddAccountProfile.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				GUIController.changePanelToAddAccountProfile();
			}
		});

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
		btnClose.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				GUIController.changePanelToMainMenu();
			}
		});

	}

	public void setMainController(AccountProfileController temp) 
        {
		mainController = temp;
	}

	public void setItemCount(int itemcount)
        {
		lblNumOfAccountsFound.setText(Integer.toString(itemcount));
	}

	public void setTableModel(TableModel tbm) 
        { 
		tbAccountProfile.setModel(tbm);
		JTableHeader th = tbAccountProfile.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++) 
                {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
                tbCellRenderer = tbAccountProfile.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbAccountProfile.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbAccountProfile, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                
		tbAccountProfile.repaint();
	}

	public static void main(String args[]) 
        {
		GUIController temp = new GUIController();
		temp.changePanelToAccountProfile();
	}

	public void ViewAll() 
        {
		// chckbxCustomer.setSelected(true);
		// chckbxSupplier.setSelected(true);
		TableModel AllModel = mainController.getAllModel();
		tbAccountProfile.setModel(AllModel);
		JTableHeader th = tbAccountProfile.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int i = 0; i < strHeader.length; i++) 
                {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(strHeader[i]);
		}
                tbCellRenderer = tbAccountProfile.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbAccountProfile.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbAccountProfile, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
                
		tbAccountProfile.repaint();
	}

}

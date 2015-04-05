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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class AddCheckAccountGUI extends JPanel
{

	private JLabel  lblHeader, lblBankName, lblAccountNumber, lblAccountName, 
                        lblBankBranch;
	private JTextField tfSupplier, tfAccountNumber, tfBankName;
	private JTextArea taBankBranch;
        private String strHeader[] = { "Account Name", "Account Number", 
                "Bank Name", "Bank Branch" };
	private DefaultTableModel tbModel;
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
		controller=temp;
                sysController = sys;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);

                lblHeader = new JLabel("Add Check Account");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
                lblAccountName = new JLabel("Account Name:");
		lblAccountName.setFont(fntPlainText);
		lblAccountName.setBounds(30, 101, 155, 30);
		add(lblAccountName);

		lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(fntPlainText);
		lblAccountNumber.setBounds(30, 138, 180, 30);
		add(lblAccountNumber);
		
		lblBankName = new JLabel("Bank Name:");
		lblBankName.setFont(fntPlainText);
		lblBankName.setBounds(30, 175, 128, 30);
		add(lblBankName);
		
		lblBankBranch = new JLabel("Bank Branch:");
		lblBankBranch.setFont(fntPlainText);
		lblBankBranch.setBounds(30, 214, 135, 30);
		add(lblBankBranch);
                
                tfSupplier = new JTextField();
		tfSupplier.setFont(fntPlainText);
		tfSupplier.setBounds(210, 101, 540, 30);
		add(tfSupplier);
		
		tfAccountNumber = new JTextField();
		tfAccountNumber.setFont(fntPlainText);
		tfAccountNumber.setBounds(210, 138, 540, 30);
		add(tfAccountNumber);
		
		tfBankName = new JTextField();
		tfBankName.setFont(fntPlainText);
		tfBankName.setBounds(210, 175, 540, 30);
		add(tfBankName);
		
		taBankBranch = new JTextArea();
                taBankBranch.setFont(fntPlainText);
                taBankBranch.setWrapStyleWord(true);
                taBankBranch.setLineWrap(true);
		
                spBankBranch = new JScrollPane(taBankBranch);
		spBankBranch.setBounds(30, 245, 720, 61);
		add(spBankBranch);
                
		tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};

		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}
		

		tbCheckAccount = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}
                        
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
                         {
                            component = super.prepareRenderer(renderer, row, column);
                            modelRow = convertRowIndexToModel(row);
                            if (!isRowSelected(modelRow))
                            {
                                component.setBackground(Color.WHITE);
                            }else
                            {
                                component.setBackground(Color.yellow);
                            }
                            return component;
                            }
		};
		
		tbCheckAccount.getTableHeader().setFont(fntHeaderTableText);
		tbCheckAccount.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbCheckAccount.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbCheckAccount.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCheckAccount.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbCheckAccount, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbCheckAccount.setFont(fntPlainText);
		
		spTable = new JScrollPane(tbCheckAccount);
		spTable.setBounds(30, 334, 935, 190);
		add(spTable);

		tbCheckAccount.getParent().setBackground(tbCheckAccount.getBackground());
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
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            checkAccount = new ArrayList<>();
                            checkAccount.add(tfAccountNumber.getText());
                            checkAccount.add(tfSupplier.getText());
                            checkAccount.add(tfBankName.getText());
                            checkAccount.add(taBankBranch.getText());
                            checkAccount.add("0");
                            sysController.AddSystemAccount(checkAccount);
                            setTableModel(sysController.getSystemAccounts("0"));
                        }
                    });
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fntPlainText);
		btnCancel.setBounds(855, 545, 110, 40);
		add(btnCancel);
                btnCancel.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToMainMenu();
                        }
                    });
                setTableModel(sysController.getSystemAccounts("0"));
	}
        
        public void setTableModel(TableModel tbm)
        {                  
        tbCheckAccount.setModel(tbm);
        JTableHeader th = tbCheckAccount.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < 4; i++)
        {
            TableColumn tc = tcm.getColumn(i);
            tc.setHeaderValue(strHeader[i]);
        }
        th.repaint();
    }
        
        public static void main(String args[])
        {
           GUIController temp=new GUIController();
           temp.changePanelToAddCheckAccount();
        }

}

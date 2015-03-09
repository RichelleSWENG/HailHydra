package SystemAccount;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class AddBankAccountGUI extends JPanel
{

    private JLabel lblHeader, lblBankName, lblAccountNumber, lblAccountName,
            lblBankBranch;
    private JTextField tfSupplier, tfAccountNumber, tfBankName;
    private JTextArea taBankBranch;
    private String strHeader[] =
    {
        "Account Name", "Account Number",
        "Bank Name", "Bank Branch"
    };
    private DefaultTableModel tbModel;
    private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
    private TableColumnModel tbColumnRenderer;
    private TableColumn tbColumn;
    private Component component;
    private JTable tbBankAccount;
    private JScrollPane spTable;
    private Border border;
    private JButton btnSubmit, btnCancel;
    private Font fntPlainText, fntHeaderText, fntHeaderTableText;
    private int modelRow;
    private int accountCount;
    private GUIController controller;
    private SystemAccountController sysController;
    private ArrayList<String> bankAccount;

    public AddBankAccountGUI(GUIController temp, SystemAccountController sys)
    {
        controller = temp;
        sysController = sys;
        setBounds(0, 0, 1000, 620);
        setLayout(null);
        setBackground(SystemColor.textHighlight);

        fntPlainText = new Font("Arial", Font.PLAIN, 21);
        fntHeaderText = new Font("Arial", Font.BOLD, 40);
        fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

        lblHeader = new JLabel("Add Bank Account");
        lblHeader.setFont(fntHeaderText);
        lblHeader.setBounds(30, 0, 600, 86);
        add(lblHeader);

        lblAccountName = new JLabel("Account Name:");
        lblAccountName.setFont(fntPlainText);
        lblAccountName.setBounds(30, 73, 155, 30);
        add(lblAccountName);

        lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setFont(fntPlainText);
        lblAccountNumber.setBounds(30, 111, 178, 30);
        add(lblAccountNumber);

        lblBankName = new JLabel("Bank Name:");
        lblBankName.setFont(fntPlainText);
        lblBankName.setBounds(30, 152, 128, 30);
        add(lblBankName);

        lblBankBranch = new JLabel("Bank Branch:");
        lblBankBranch.setFont(fntPlainText);
        lblBankBranch.setBounds(30, 190, 135, 30);
        add(lblBankBranch);

        tfSupplier = new JTextField();
        tfSupplier.setFont(fntPlainText);
        tfSupplier.setBounds(178, 73, 567, 30);
        add(tfSupplier);

        tfAccountNumber = new JTextField();
        tfAccountNumber.setFont(fntPlainText);
        tfAccountNumber.setBounds(188, 114, 557, 30);
        add(tfAccountNumber);

        tfBankName = new JTextField();
        tfBankName.setFont(fntPlainText);
        tfBankName.setColumns(10);
        tfBankName.setBounds(149, 152, 596, 30);
        add(tfBankName);

        taBankBranch = new JTextArea();
        taBankBranch.setFont(fntPlainText);
        taBankBranch.setBounds(30, 231, 725, 61);
        add(taBankBranch);

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

        tbBankAccount = new JTable(tbModel)
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
                } else
                {
                    component.setBackground(Color.yellow);
                }
                return component;
            }
        };

        tbBankAccount.getTableHeader().setFont(fntHeaderTableText);
        tbBankAccount.getTableHeader().setPreferredSize(new Dimension(100, 55));
        tbBankAccount.getTableHeader().setResizingAllowed(false);
        tbCellRenderer = tbBankAccount.getTableHeader().getDefaultRenderer();
        tbColumnRenderer = tbBankAccount.getColumnModel();
        for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
        {
            tbColumn = tbColumnRenderer.getColumn(j);
            tbCellRendererColumn = tbColumn.getHeaderRenderer();
            if (tbCellRendererColumn == null)
            {
                tbCellRendererColumn = tbCellRenderer;
            }
            component = tbCellRendererColumn.getTableCellRendererComponent(tbBankAccount, tbColumn.getHeaderValue(), false, false, 0, j);
            tbColumn.setPreferredWidth(component.getPreferredSize().width);
        }
        tbBankAccount.setFont(fntPlainText);

        spTable = new JScrollPane(tbBankAccount);
        spTable.setBounds(30, 303, 935, 190);
        add(spTable);

        tbBankAccount.getParent().setBackground(tbBankAccount.getBackground());
        tbBankAccount.getTableHeader().setResizingAllowed(false);
        tbBankAccount.getTableHeader().setReorderingAllowed(false);
        tbBankAccount.setColumnSelectionAllowed(true);
        tbBankAccount.setRowSelectionAllowed(true);
        tbBankAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbBankAccount.setRowHeight(30);

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
                        bankAccount = new ArrayList<>();
                        bankAccount.add(tfAccountNumber.getText());
                        bankAccount.add(tfSupplier.getText());
                        bankAccount.add(tfBankName.getText());
                        bankAccount.add(taBankBranch.getText());
                        bankAccount.add("1");
                        sysController.AddSystemAccount(bankAccount);
                        setTableModel(sysController.getSystemAccounts("1"));
                    }
                });

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(fntPlainText);
        btnCancel.setBounds(855, 545, 110, 40);
        add(btnCancel);
        btnCancel.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        controller.changePanelToMainMenu();
                    }
                });
        setTableModel(sysController.getSystemAccounts("1"));
    }

    public void setTableModel(TableModel tbm)
    {                  // Setting the Headers
        tbBankAccount.setModel(tbm);
        JTableHeader th = tbBankAccount.getTableHeader();
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
        GUIController temp = new GUIController();
        temp.changePanelToAddBankAccount();
    }
}

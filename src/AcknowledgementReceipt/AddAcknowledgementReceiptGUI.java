package AcknowledgementReceipt;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class AddAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI implements TableModelListener
{

    private JButton btnAddItem, btnSubmit, btnCancel;
    private GUIController guiController;
    private AcknowledgementReceiptController mainController;
    private int numItems;
    private float totalBalance;
    private float totalItemPrice;
    private float tentativeTotal;
    private float discount;
    private float dedBalance;
    private String partNums[];

    public AddAcknowledgementReceiptGUI(GUIController temp, AcknowledgementReceiptController controller)
    {
        super();
        numItems = 0;
        totalItemPrice = 0;
        tentativeTotal = 0;
        discount = 0;
        dedBalance = 0;
        guiController = temp;
        mainController = controller;
        totalBalance = 0;
        cmbCustomer.setEditable(true);

        lblHeader.setText("Add Acknowledgement Receipt");

        btnAddItem = new JButton("Add Item");
        btnAddItem.setFont(fntPlainText);
        btnAddItem.setBounds(30, 545, 147, 40);
        add(btnAddItem);
        btnAddItem.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            mainController.addPendingItem(new ARLineItem("", Integer.parseInt(tbModel.getValueAt(numItems, 0).toString()), tbModel.getValueAt(numItems, 1).toString(), Float.parseFloat(tbModel.getValueAt(numItems, 3).toString()), Float.parseFloat(tbModel.getValueAt(numItems, 4).toString())));
                            numItems++;
                            tbModel.setRowCount(numItems + 1);
                            totalItemPrice = 0;
                            tentativeTotal = 0;
                        }
                        catch (NullPointerException exception)
                        {
                            JOptionPane.showMessageDialog(null, "Please fill in the required fields before adding. Fuck you po");
                        }
                    }
                });

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
                        mainController.addAR(tfARNum.getText(), mainController.getCustomer(cmbCustomer.getSelectedIndex() - 1).getId(), ftfDate.getText(), Float.parseFloat(ftfTotal.getText()), tfPONum.getText(), tfOrderedBy.getText(), tfSalesperson.getText(), tfDeliveredBy.getText(), taDeliveryNotes.getText(), tfDRNum.getText(), Float.parseFloat(ftfDiscount.getText()), Float.parseFloat(ftfBalance.getText()), "open", taAddress.getText());
                        guiController.changePanelToAcknowledgementReceipt();
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
                        mainController.removePending();
                        guiController.changePanelToAcknowledgementReceipt();
                    }
                });
        String[] customerNames = new String[mainController.getCustomers().size() + 1];
        int i;
        customerNames[0] = "";
        for (i = 1; i < mainController.getCustomers().size() + 1; i++)
        {
            customerNames[i] = mainController.getCustomers().get(i - 1).getName();
        }
        cmbCustomer.setModel(new DefaultComboBoxModel(customerNames));

        cmbCustomer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (cmbCustomer.getSelectedIndex() != 0)
                {
                    Company c = mainController.getCustomer(cmbCustomer.getSelectedIndex() - 1);
                    taAddress.setText(c.getAddressLoc());
                }
            }
        });
        
        ftfDiscount.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText())/100 * totalBalance; 
                ftfBalance.setText(String.valueOf(dedBalance));
            }
        });

        tbModel.setRowCount(1);
        
        partNums = new String[mainController.getItems().size() + 1];
        partNums[0] = "";
        for (i = 1; i < mainController.getItems().size() + 1; i++)
        {
            partNums[i] = mainController.getItems().get(i - 1).getPartNum();
        }
        
        TableColumn col = tbARReceipt.getColumnModel().getColumn(1);
        col.setCellEditor(new MyComboBoxEditor(partNums));
        col.setCellRenderer(new MyComboBoxRenderer(partNums));
        
        tbModel.addTableModelListener(this);
    
    }
    public void setController(AcknowledgementReceiptController temp)
    {
        mainController = temp;
    }
    
    public static void main(String args[])
    {
        GUIController temp = new GUIController();
        temp.changePanelToAddAcknowledgementReceipt();
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {
        if (e.getColumn() == 0)
        {
            if (tbModel.getValueAt(numItems, 1) != null)
            {
                String cmb = tbModel.getValueAt(numItems, 1).toString();
                if (tbModel.getValueAt(numItems, 0) != null && !cmb.equals("") && !tbModel.getValueAt(numItems, 0).toString().equals(""))
                {    
                    totalItemPrice = Integer.parseInt(tbModel.getValueAt(numItems, 0).toString()) * Float.parseFloat(tbModel.getValueAt(numItems, 3).toString());
                    tbModel.setValueAt(totalItemPrice, numItems, 4);
                    totalBalance = totalBalance + totalItemPrice - tentativeTotal;
                    dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText())/100 * totalBalance; 
                    tentativeTotal = totalItemPrice;
                    ftfTotal.setText(String.valueOf(totalBalance));
                    ftfBalance.setText(String.valueOf(dedBalance));
                }
            }
        }
        
        if (e.getColumn() == 1)
        {
            if (tbModel.getValueAt(numItems, 1) != null)
            {
                String cmb = tbModel.getValueAt(numItems, 1).toString();
                if (!cmb.equals(""))
                {
                    tbModel.setValueAt(mainController.getItems().get(Arrays.asList(partNums).indexOf(cmb)-1).getDescription(), numItems, 2);
                    tbModel.setValueAt(mainController.getItems().get(Arrays.asList(partNums).indexOf(cmb)-1).getPrice(), numItems, 3);
                }
                if (tbModel.getValueAt(numItems, 0) != null && !cmb.equals("") && !tbModel.getValueAt(numItems, 0).toString().equals(""))
                {    
                    totalItemPrice = Integer.parseInt(tbModel.getValueAt(numItems, 0).toString()) * Float.parseFloat(tbModel.getValueAt(numItems, 3).toString());
                    tbModel.setValueAt(totalItemPrice, numItems, 4);
                    totalBalance = totalBalance + totalItemPrice - tentativeTotal;
                    dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText())/100 * totalBalance; 
                    tentativeTotal = totalItemPrice;
                    ftfTotal.setText(String.valueOf(totalBalance));
                    ftfBalance.setText(String.valueOf(dedBalance));
                }
            }
        }
        
        if (e.getColumn() == 3)
        {
            if (tbModel.getValueAt(numItems, 1) != null)
            {
                String cmb = tbModel.getValueAt(numItems, 1).toString();
                if (tbModel.getValueAt(numItems, 0) != null && !cmb.equals("") && !tbModel.getValueAt(numItems, 0).toString().equals(""))
                {    
                    totalItemPrice = Integer.parseInt(tbModel.getValueAt(numItems, 0).toString()) * Float.parseFloat(tbModel.getValueAt(numItems, 3).toString());
                    tbModel.setValueAt(totalItemPrice, numItems, 4);
                    totalBalance = totalBalance + totalItemPrice - tentativeTotal;
                    dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText())/100 * totalBalance; 
                    tentativeTotal = totalItemPrice;
                    ftfTotal.setText(String.valueOf(totalBalance));
                    ftfBalance.setText(String.valueOf(dedBalance));
                }
            }
        }
    }

    class MyComboBoxRenderer extends JComboBox implements TableCellRenderer
    {

        public MyComboBoxRenderer(String[] items)
        {
            super(items);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } 
            else
            {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setSelectedItem(value);
            return this;
        }
    }

    class MyComboBoxEditor extends DefaultCellEditor
    {

        public MyComboBoxEditor(String[] str)
        {
            super(new JComboBox(str));
        }
    }

}

package AcknowledgementReceipt;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class AddAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI
{

    private JButton btnAddItem, btnSubmit, btnCancel;
    private GUIController guiController;
    private AcknowledgementReceiptController mainController;
    private int numItems;
    private float totalBalance;
    private JComboBox cmbPartNum;

    public AddAcknowledgementReceiptGUI(GUIController temp, AcknowledgementReceiptController controller)
    {
        super();
        numItems = 0;
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
                    public void actionPerformed(ActionEvent e)
                    {
                        mainController.addPendingItem(new ARLineItem("", Integer.parseInt(tbARReceipt.getValueAt(numItems - 1, 0).toString()), tbARReceipt.getValueAt(numItems - 1, 1).toString(), Float.parseFloat(tbARReceipt.getValueAt(numItems - 1, 3).toString()), Float.parseFloat(tbARReceipt.getValueAt(numItems - 1, 4).toString())));
                        numItems++;
                        tbModel.setRowCount(numItems + 1);
                    }
                });

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(fntPlainText);
        btnSubmit.setBounds(655, 545, 110, 40);
        add(btnSubmit);
        btnSubmit.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        mainController.addAR(tfARNum.getText(),mainController.getCustomer(cmbCustomer.getSelectedIndex()-1).getId(),ftfDate.getText(),Float.parseFloat(ftfTotal.getText()),tfPONum.getText(),tfOrderedBy.getText(),tfSalesperson.getText(),tfDeliveredBy.getText(),taDeliveryNotes.getText(),tfDRNum.getText(),Float.parseFloat(ftfDiscount.getText()),Float.parseFloat(ftfBalance.getText()),"open");
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
                    taAddress.setEditable(false);
                    taAddress.setText(c.getAddressLoc());
                }
            }
        });
        
        tbModel.setRowCount(numItems + 1);
        tbARReceipt = new JTable(tbModel)
        {
            @Override
            public TableCellEditor getCellEditor(int row, int column)
            {
                if (true)
                {
                    String[] partNums = new String[mainController.getItems().size() + 1];
                    partNums[0] = "";
                    int i;
                    System.out.println("hallo");
                    for (i = 1; i < mainController.getItems().size() + 1; i++)
                    {
                        partNums[i] = mainController.getItems().get(i - 1).getPartNum();
                    }
                    
                    cmbPartNum = new JComboBox(partNums);
                    cmbPartNum.addActionListener(
                        new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                tbModel.setValueAt(mainController.getItems().get(cmbPartNum.getSelectedIndex()-1).getDescription(), numItems, 2);
                                tbModel.setValueAt(mainController.getItems().get(cmbPartNum.getSelectedIndex()-1).getPrice(), numItems, 3);
                                float totalItemPrice = Integer.parseInt(tbModel.getValueAt(numItems, 0).toString()) * mainController.getItems().get(cmbPartNum.getSelectedIndex()-1).getPrice();
                                tbModel.setValueAt(totalItemPrice, numItems, 4);
                                totalBalance += totalItemPrice;
                                ftfTotal.setText(String.valueOf(totalBalance));
                            }
                        });
                    return new DefaultCellEditor(cmbPartNum);
                } 
                
                else
                {
                    System.out.println("hello");
                    return super.getCellEditor(row, column);
                }
            }
        };
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

}

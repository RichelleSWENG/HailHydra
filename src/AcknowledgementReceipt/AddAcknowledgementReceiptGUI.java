package AcknowledgementReceipt;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class AddAcknowledgementReceiptGUI extends AcknowledgementReceiptGUI
{

    private JButton btnAddItem, btnSubmit, btnCancel;
    private GUIController guiController;
    private AcknowledgementReceiptController mainController;

    public AddAcknowledgementReceiptGUI(GUIController temp, AcknowledgementReceiptController controller)
    {
        super();
        guiController = temp;
        mainController = controller;
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
                        AddItemPopUpGUI frame = new AddItemPopUpGUI();
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
                Company c = mainController.getCustomer(cmbCustomer.getSelectedIndex() - 1);
                taAddress.setEditable(false);
                taAddress.setText(c.getAddressLoc());
            }
        });
        
        tbModel.setRowCount(1);
        System.out.println("hallo");
        tbARReceipt = new JTable(tbModel)
        {
            @Override
            public TableCellEditor getCellEditor(int row, int column)
            {
                int modelColumn = convertColumnIndexToModel(column);

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
                    
                    JComboBox cmbPartNum = new JComboBox(partNums);
                    return new DefaultCellEditor(cmbPartNum);
                } 
                
                else
                {
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

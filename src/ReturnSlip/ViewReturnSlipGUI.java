package ReturnSlip;

import Classes.Company;
import HailHydra.GUIController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ViewReturnSlipGUI extends ReturnSlipGUI implements TableModelListener
{
        private JButton btnClose;
        private GUIController guiController;
        private ReturnSlipController mainController;
        private ReturnSlip slip;
       	private int numItems;
        private float totalItemPrice;
        private float totalBalance;
        private final float defaultVal = 0;
        private String partNums[];
        private Company c;
    
    public ViewReturnSlipGUI(GUIController temp)
    {
                super();
                guiController=temp;
                 ReturnSlip slip = new ReturnSlip();
		lblHeader.setText("View Return Slip");
                
                cmbSupplier.setEditable(false);
                tfRSNum.setEditable(false);
                tfPONum.setEditable(false);
                ftfDate.setEditable(false);
                tfReturnedBy.setEditable(false);
                tfApprovedBy.setEditable(false);
                tfReceivedBy.setEditable(false);
                ftfReturnedDate.setEditable(false);
                ftfApprovedDate.setEditable(false);
                ftfReceivedDate.setEditable(false);
                taAddress.setEditable(false);
                taNotes.setEditable(false);
                btnAddItem.setEnabled(false);
                btnDeleteItem.setEnabled(false);
		
                        
		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
                btnClose.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToReturnSlip();
                        }
                    });
    }
    
    public void setMainController(ReturnSlipController temp)
    {
            mainController=temp;
            slip = mainController.getSlipTarget();
    }

        public void setViewComponents() 
    {
        setDataComponents();
        cmbSupplier.setEnabled(false);
        cmbPTNum.setEnabled(false);
        tfRSNum.setText(slip.getReturn_slip_id());
       // tfPONum.setText(slip.getPurchase_order_num());
        cmbPTNum.setSelectedItem(Integer.toString(slip.getPurchase_transaction_num()));
         String PO;
               if(cmbPTNum.getSelectedIndex()!=0)
               {
               PO = mainController.getPurchaseOrderNumber(cmbPTNum.getSelectedItem().toString());
               tfPONum.setText(PO);
               }
        tfReturnedBy.setText(slip.getReturned_by());
        tfApprovedBy.setText(slip.getApproved_by());
        tfReceivedBy.setText(slip.getReceived_by());
        ftfReturnedDate.setText(slip.getReturned_date());
        ftfApprovedDate.setText(slip.getApproved_date());
        ftfReceivedDate.setText(slip.getReceived_date());
        taNotes.setText(slip.getNotes());
        ftfDate.setText(slip.getDate());
        /*rdbtnFunctional, rdbtnDefectiveWithOutDebitMemo, rdbtnDefectiveWithDebitMemo*/
        switch(slip.getType())
        {
            case "Functional": rdbtnFunctional.setSelected(true); break;
            case "Defective w/out Debit Memo": rdbtnDefectiveWithOutDebitMemo.setSelected(true); break;
            case "Defective w/Debit Memo":    rdbtnDefectiveWithDebitMemo.setSelected(true); break;
        }
        rdbtnFunctional.setEnabled(false);
        rdbtnDefectiveWithOutDebitMemo.setEnabled(false);
        rdbtnDefectiveWithDebitMemo.setEnabled(false);
        
        ftfTotal.setEditable(false);
       // ftfBalance.setText(String.valueOf(rcpt.getCurrent_balance()));
        taAddress.setEditable(false);
        //taDeliveryNotes.setText(rcpt.getDelivery_notes());
        
               cmbSupplier.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int i, rowCount;
                rowCount = tbModel.getRowCount();
                for (i = 0; i < rowCount; i++)
                    tbModel.removeRow(0);
                tbModel.setRowCount(1);
                mainController.removePending();
                if (cmbSupplier.getSelectedIndex() != 0)
                {
                    c = mainController.getSupplier(cmbSupplier.getSelectedIndex() - 1);
                    taAddress.setText(c.getAddressLoc());
                    
                    
                    partNums = new String[mainController.getItems(c.getType()).size() + 1];
                    partNums[0] = "";
                    for (i = 1; i < mainController.getItems(c.getType()).size() + 1; i++)
                    {
                        partNums[i] = mainController.getItems(c.getType()).get(i - 1).getPartNum();
                    }
        
               TableColumn col = tbReturnSlip.getColumnModel().getColumn(1);
                    col.setCellEditor(new ViewReturnSlipGUI.MyComboBoxEditor(partNums));
                    col.setCellRenderer(new ViewReturnSlipGUI.MyComboBoxRenderer(partNums));

                }
                numItems = 0;
                tbModel.setValueAt(defaultVal, numItems, 4);
            }


        });
        String name = null;
        try
        {
            name = mainController.getSupplierbyID(slip.getCompany_id());
        } catch (SQLException ex)
        {
            Logger.getLogger(ViewReturnSlipGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbSupplier.setSelectedItem(name);
        numItems = slip.getList().size();
        tbModel.setRowCount(numItems);
        tbModel.addTableModelListener(this);
        for (int i = 0; i < numItems; i++)
        {
           tbModel.setValueAt(slip.getList().get(i).getQuantity(), i, 0);
           tbModel.setValueAt(slip.getList().get(i).getPartNum(), i, 1);
           tbModel.setValueAt(slip.getList().get(i).getUnit_price(), i, 3);
        }
        tbReturnSlip.setEnabled(false);

    }
          public void setDataComponents()
    {
        String[] supplierNames = new String[mainController.getSuppliers().size() + 1];
        int i;
        supplierNames[0] = "";
        for (i = 1; i < mainController.getSuppliers().size() + 1; i++)
        {
            supplierNames[i] = mainController.getSuppliers().get(i - 1).getName();
        }
        cmbSupplier.setModel(new DefaultComboBoxModel(supplierNames));
        // PurchaseTransaction cmbobox
        String[] purchaseTransactionNumbers = new String[mainController.getPurchaseTransactionNumbers().size()+1];
        int j;
        purchaseTransactionNumbers[0] = "";
        for (j = 1; j < mainController.getPurchaseTransactionNumbers().size() + 1; j++)
        {
            purchaseTransactionNumbers[j] = mainController.getPurchaseTransactionNumbers().get(j - 1);
        }
        cmbPTNum.setModel(new DefaultComboBoxModel(purchaseTransactionNumbers));
    
    }
          
          

       public void calcTotalBalance()
    {
        int i;
        totalBalance = 0;
        for (i = 0; i < tbModel.getRowCount(); i ++)
        {
            if (tbModel.getValueAt(i, 4) != null)
                totalBalance += Float.parseFloat(tbModel.getValueAt(i, 4).toString());
        }
        //dedBalance = totalBalance - Float.parseFloat(ftfDiscount.getText());
        ftfTotal.setText(String.valueOf(totalBalance));
        //ftfBalance.setText(String.valueOf(dedBalance));
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
    
    public void tableChanged(TableModelEvent e)
    {
        if (e.getColumn() == 0)
        {
            
            if (tbModel.getValueAt(e.getFirstRow(), 1) != null)
            {
                String cmb = tbModel.getValueAt(e.getFirstRow(), 1).toString();
                if (Integer.valueOf(tbModel.getValueAt(e.getFirstRow(), 0).toString()) <= mainController.getAvailQuantity(Arrays.asList(partNums).indexOf(cmb)-1))
                {
                    if (tbModel.getValueAt(e.getFirstRow(), 0) != null && !cmb.equals("") && !tbModel.getValueAt(e.getFirstRow(), 0).toString().equals(""))
                    {    
                        totalItemPrice = Integer.parseInt(tbModel.getValueAt(e.getFirstRow(), 0).toString()) * Float.parseFloat(tbModel.getValueAt(e.getFirstRow(), 3).toString());
                        tbModel.setValueAt(totalItemPrice, e.getFirstRow(), 4);
                        calcTotalBalance();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You can not buy that many items!!!! You can only buy " + mainController.getAvailQuantity(Arrays.asList(partNums).indexOf(cmb)-1) + ". Pls do not test me");
                    tbModel.setValueAt("0", e.getFirstRow(), 0);
                }
            }
        }
        
        if (e.getColumn() == 1)
        {
            if (tbModel.getValueAt(e.getFirstRow(), 1) != null)
            {
                String cmb = tbModel.getValueAt(e.getFirstRow(), 1).toString();
                if (!cmb.equals(""))
                {
                    int i;
                    boolean unique = true;
                    for (i= 0; i < tbModel.getRowCount(); i++)
                        if (tbModel.getValueAt(i, 1) != null && tbModel.getValueAt(i, 1).toString().equals(cmb) && i != e.getFirstRow())
                            unique = false;
                    if (unique)
                    {
                        tbModel.setValueAt(mainController.getItems(c.getType()).get(Arrays.asList(partNums).indexOf(cmb)-1).getDescription(), e.getFirstRow(), 2);
                        tbModel.setValueAt(mainController.getItems(c.getType()).get(Arrays.asList(partNums).indexOf(cmb)-1).getPrice(), e.getFirstRow(), 3);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You've already chosen that item. Edit the quantity previously or select another pls", "Duplicate Items", JOptionPane.ERROR_MESSAGE);
                        tbModel.removeRow(e.getFirstRow());
                        tbModel.setRowCount(numItems+1);
                        tbModel.moveRow(numItems, numItems, e.getFirstRow());
                    }
                }
                if (tbModel.getValueAt(e.getFirstRow(), 0) != null && !cmb.equals("") && !tbModel.getValueAt(e.getFirstRow(), 0).toString().equals(""))
                {    
                    totalItemPrice = Integer.parseInt(tbModel.getValueAt(e.getFirstRow(), 0).toString()) * Float.parseFloat(tbModel.getValueAt(e.getFirstRow(), 3).toString());
                    tbModel.setValueAt(totalItemPrice, e.getFirstRow(), 4);
                    calcTotalBalance();
                }
            }
        }
        
        if (e.getColumn() == 3)
        {
            if (tbModel.getValueAt(e.getFirstRow(), 1) != null)
            {
                String cmb = tbModel.getValueAt(e.getFirstRow(), 1).toString();
                if (tbModel.getValueAt(e.getFirstRow(), 0) != null && !cmb.equals("") && !tbModel.getValueAt(e.getFirstRow(), 0).toString().equals(""))
                {    
                    totalItemPrice = Integer.parseInt(tbModel.getValueAt(e.getFirstRow(), 0).toString()) * Float.parseFloat(tbModel.getValueAt(e.getFirstRow(), 3).toString());
                    tbModel.setValueAt(totalItemPrice, e.getFirstRow(), 4);
                    calcTotalBalance();
                }
            }
            
        }
    }
        

}


   

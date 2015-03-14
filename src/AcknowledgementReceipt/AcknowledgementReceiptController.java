
package AcknowledgementReceipt;

import Sales.SalesInvoiceController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

public class AcknowledgementReceiptController 
{
    private AckReceiptModel ackReceiptModel;
    private AcknowledgementReceiptListGUI gui;
    private ArrayList<ARLineItem> pendingItems;
    private int itemcount;
    
    public AcknowledgementReceiptController(AckReceiptModel ackReceiptModel,AcknowledgementReceiptListGUI tempGUI)
    {
        this.ackReceiptModel = ackReceiptModel;
        pendingItems = new ArrayList<>();
        gui=tempGUI;
               
    }
    public ArrayList<Company> getCustomers()
    {
        return ackReceiptModel.getCustomers();
    }
    
    public Company getCustomer(int index)
    {
        return ackReceiptModel.getCustomer(index);
    }
    
     public ArrayList<Item> getItems(String customerType)
    {
        return ackReceiptModel.getItems(customerType);
    }
    
    public Item getItem(int index)
    {
        return ackReceiptModel.getItem(index);
    }
    
    public void addPendingItem(ARLineItem item)
    {
        pendingItems.add(item);
    }
    
    public void removePending()
    {
        pendingItems.clear();
    }
    
    public void addAR(String acknowledgement_receipt_id,int company_id,String date,float original_amount,String po_num,String ordered_by,String sales_person,String delivered_by,String delivery_notes,String delivery_receipt_num,float discount,float current_balance, String address, String status)
    {
        AcknowledgementReceipt rcpt = new AcknowledgementReceipt(acknowledgement_receipt_id,company_id,date,original_amount,po_num,ordered_by,sales_person,delivered_by,delivery_notes,delivery_receipt_num,discount, current_balance,status, address, pendingItems);
        ackReceiptModel.addDetail(rcpt);
        //ackReceiptModel.addDetail(rcpt);
    }
    
    TableModel getAllModel()
    {
            TableModel tbm = ackReceiptModel.myModel(ackReceiptModel.getAllDetail());
            this.itemcount = ackReceiptModel.getItemcount();
            gui.setItemCount(itemcount);
            return tbm;     
    }
    
    public void SearchSomething(String text, int type,String startDate,String endDate)
    {
            String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "acknowledgement number";
            TableModel tbm;
            tbm = ackReceiptModel.myModel(ackReceiptModel.searchDetail(text, searchBy,startDate,endDate));
            this.itemcount = ackReceiptModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    public void searchbyDate(String startDate,String endDate)
    {
            TableModel tbm;
            tbm = ackReceiptModel.myModel(ackReceiptModel.getAllDetailbyDate(startDate,endDate));
            this.itemcount =  ackReceiptModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public String getMaxYear()
    {
        ResultSet resultset =ackReceiptModel.getMaxYear();
          
        try {
            resultset.next();
            return resultset.getString("MAX(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(AcknowledgementReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset =ackReceiptModel.getMinYear();
        
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(AcknowledgementReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
}

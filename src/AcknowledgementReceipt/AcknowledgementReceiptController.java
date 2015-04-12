
package AcknowledgementReceipt;

import Classes.Company;
import Classes.Item;
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
    private AcknowledgementReceipt receipt;
    
    public AcknowledgementReceiptController(AckReceiptModel ackReceiptModel,AcknowledgementReceiptListGUI tempGUI)
    {
        this.ackReceiptModel = ackReceiptModel;
        pendingItems = new ArrayList<>();
        gui = tempGUI;
        receipt = null;
    }
    
    public AcknowledgementReceiptController(AckReceiptModel ackReceiptModel)
    {
        this.ackReceiptModel = ackReceiptModel;
        pendingItems = new ArrayList<>();
        receipt = null;
    }
    
    public ArrayList<Company> getCustomers()
    {
        return ackReceiptModel.getCustomers();
    }
    
    public Company getCustomer(int index)
    {
        return ackReceiptModel.getCustomer(index);
    }
    
     public ArrayList<ARLineItem> getItems(String customerType)
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
    
    public void addAR(String acknowledgement_receipt_id, String date,float original_amount,String po_num,String ordered_by,String sales_person,String delivered_by,String delivery_notes,String delivery_receipt_num,float discount,float current_balance, String status, Company company)
    {
        AcknowledgementReceipt rcpt = new AcknowledgementReceipt(acknowledgement_receipt_id, date,original_amount,po_num,ordered_by,sales_person,delivered_by,delivery_notes,delivery_receipt_num,discount, current_balance,status, pendingItems, company);
        ackReceiptModel.addDetail(rcpt);
        //ackReceiptModel.addDetail(rcpt);
    }
    
    public void editAR(String acknowledgement_receipt_id, String date,float original_amount,String po_num,String ordered_by,String sales_person,String delivered_by,String delivery_notes,String delivery_receipt_num,float discount,float current_balance, String status, Company company)
    {
        AcknowledgementReceipt rcpt = new AcknowledgementReceipt(acknowledgement_receipt_id, date,original_amount,po_num,ordered_by,sales_person,delivered_by,delivery_notes,delivery_receipt_num,discount, current_balance,status, pendingItems, company);
        ackReceiptModel.editDetail(rcpt);
        setReceiptTarget(getAR(rcpt.getAcknowledgement_receipt_id()));
        //ackReceiptModel.addDetail(rcpt);
    }
    
    public int getAvailQuantity(int index)
    {
        return ackReceiptModel.getAvailQuantity(index);
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
            else if(type == 2)
                searchBy = "part number";
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
    
    public void setReceiptTarget(AcknowledgementReceipt rcpt)
    {
        receipt = rcpt;
    }
    
    public AcknowledgementReceipt getReceiptTarget()
    {
        return receipt;
    }
    
    public AcknowledgementReceipt getAR(String ID)
    {
        return ackReceiptModel.getAR(ID);
    }
    
}

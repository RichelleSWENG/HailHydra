
package AcknowledgementReceipt;

import java.util.ArrayList;

public class AcknowledgementReceiptController 
{
    private AckReceiptModel ackReceiptModel;
    private ArrayList<ARLineItem> pendingItems;
    
    public AcknowledgementReceiptController(AckReceiptModel ackReceiptModel)
    {
        this.ackReceiptModel = ackReceiptModel;
        pendingItems = new ArrayList<>();
    }
    
    public ArrayList<Company> getCustomers()
    {
        return ackReceiptModel.getCustomers();
    }
    
    public Company getCustomer(int index)
    {
        return ackReceiptModel.getCustomer(index);
    }
    
    public ArrayList<Item> getItems()
    {
        return ackReceiptModel.getItems();
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
    
}

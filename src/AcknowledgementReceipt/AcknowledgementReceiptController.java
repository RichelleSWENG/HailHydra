
package AcknowledgementReceipt;

import java.util.ArrayList;

public class AcknowledgementReceiptController 
{
    private AckReceiptModel ackReceiptModel;
    
    public AcknowledgementReceiptController(AckReceiptModel ackReceiptModel)
    {
        this.ackReceiptModel = ackReceiptModel;
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
    
}

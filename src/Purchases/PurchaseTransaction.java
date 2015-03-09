package Purchases;

import java.util.ArrayList;


public class PurchaseTransaction 
{
    private String supplier, address, purchaseTransactionNum, date,
            poNum, siNum, orNum, orderedBy, receivedBy, receivingNotes;
    private ArrayList<PurchaseTransactionLine> item;
    private Float discount;
    
    public static class PurchaseTransactionBuilder
    {
        private final String supplier;
        
        public PurchaseTransactionBuilder(String supplier)
        {
            this.supplier=supplier;
        }
    }
    
    public PurchaseTransaction(PurchaseTransactionBuilder temp)
    {
        
    }
    
}

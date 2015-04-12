package Purchases;

import Classes.Company;
import Classes.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;


public class PurchaseTransactionController {
    private PurchasesModel purchasesModel;
    private PurchaseTransactionListGUI gui;
    private ArrayList<PTLineItem> pendingItems;
    private int itemcount;
    private PurchaseTransaction purchaseTransaction;
    
    public PurchaseTransactionController(PurchasesModel tempModel,PurchaseTransactionListGUI tempGUI)
    {
        this.purchasesModel = tempModel;
        pendingItems = new ArrayList<>();
        this.gui=tempGUI;
    }
    
    public PurchaseTransactionController(PurchasesModel tempModel)
    {
        this.purchasesModel = tempModel;
        pendingItems = new ArrayList<>();
    }
    
    TableModel getAllModel()
    {
       TableModel tbm = purchasesModel.myModel(purchasesModel.getAllDetail());
       this.itemcount = purchasesModel.getItemcount();
       gui.setItemCount(itemcount);
        return tbm;
    }
    
    public void SearchSomething(String text, int type,String startDate,String endDate)
    {
            String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "transaction number";
            else if(type==2)
                searchBy = "part number";
            TableModel tbm;
            tbm = purchasesModel.myModel(purchasesModel.searchDetail(text, searchBy,startDate,endDate));
            this.itemcount = purchasesModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public void searchbyDate(String startDate,String endDate)
    {
        TableModel tbm;
            tbm = purchasesModel.myModel(purchasesModel.getAllDetailbyDate(startDate,endDate));
            this.itemcount = purchasesModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public String getMaxYear()
    {
        ResultSet resultset =purchasesModel.getMaxYear();
          
        try {
            resultset.next();
            return resultset.getString("MAX(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset =purchasesModel.getMinYear();
        
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public ArrayList<Company> getSuppliers()
    {
        return purchasesModel.getSuppliers();
    }
    
    public Company getCustomer(int index)
    {
        return purchasesModel.getSupplier(index);
    }
    
     public ArrayList<PTLineItem> getItems()
    {
        return purchasesModel.getItems();
    }
    
    public Item getItem(int index)
    {
        return purchasesModel.getItem(index);
    }
    
    public void addPendingItem(PTLineItem item)
    {
        pendingItems.add(item);
    }
    
    public void removePending()
    {
        pendingItems.clear();
    }
    
    public int getAvailQuantity(int index)
    {
        return purchasesModel.getAvailQuantity(index);
    }
    
    public void addPT(String purchase_transaction_id, String date, float original_amount, String po_num, String received_by, String ordered_by, String receiving_notes, String delivery_receipt_num, String ref_sales_invoice_num, float discount, float vat, float current_balance, String status, Company company)
    {
        PurchaseTransaction pt = new PurchaseTransaction(purchase_transaction_id, date, original_amount, po_num, received_by, ordered_by, receiving_notes, delivery_receipt_num, ref_sales_invoice_num, discount, vat, current_balance, status, pendingItems, company);
        purchasesModel.addDetail(pt);
    }
    
    public void editPT(String purchase_transaction_id, String date, float original_amount, String po_num, String received_by, String ordered_by, String receiving_notes, String delivery_receipt_num, String ref_sales_invoice_num, float discount, float vat, float current_balance, String status, Company company)
    {
        PurchaseTransaction pt = new PurchaseTransaction(purchase_transaction_id, date, original_amount, po_num, received_by, ordered_by, receiving_notes, delivery_receipt_num, ref_sales_invoice_num, discount, vat, current_balance, status, pendingItems, company);
        purchasesModel.editDetail(pt);
        setPurchaseTransaction(getPT(pt.getPurchase_transaction_id()));
        //ackReceiptModel.addDetail(rcpt);
    }

    public PurchaseTransaction getPurchaseTransaction()
    {
        return purchaseTransaction;
    }

    public void setPurchaseTransaction(PurchaseTransaction purchaseTransaction)
    {
        this.purchaseTransaction = purchaseTransaction;
    }
    
    public PurchaseTransaction getPT(String ID)
    {
        return purchasesModel.getPT(ID);
    }

    public String getLastPTID()
    {
       return purchasesModel.getLastPTID();
    }
    
    
}

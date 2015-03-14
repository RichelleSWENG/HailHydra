package Purchases;

import Sales.SalesInvoiceController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;


public class PurchaseTransactionController {
    private PurchasesModel purchasetransactionModel;
    private PurchaseTransactionListGUI gui;
    private int itemcount;
    
    public PurchaseTransactionController(PurchasesModel tempModel,PurchaseTransactionListGUI tempGUI)
    {
        this.purchasetransactionModel=tempModel;
        this.gui=tempGUI;
    }
    
    TableModel getAllModel()
    {
       TableModel tbm = purchasetransactionModel.myModel(purchasetransactionModel.getAllDetail());
       this.itemcount = purchasetransactionModel.getItemcount();
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
            tbm = purchasetransactionModel.myModel(purchasetransactionModel.searchDetail(text, searchBy,startDate,endDate));
            this.itemcount = purchasetransactionModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    public void searchbyDate(String startDate,String endDate)
    {
        TableModel tbm;
            tbm = purchasetransactionModel.myModel(purchasetransactionModel.getAllDetailbyDate(startDate,endDate));
            this.itemcount = purchasetransactionModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public String getMaxYear()
    {
        ResultSet resultset =purchasetransactionModel.getMaxYear();
          
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
        ResultSet resultset =purchasetransactionModel.getMinYear();
        
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}

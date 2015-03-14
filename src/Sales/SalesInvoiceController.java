

package Sales;

import Payables.PayablesController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;


public class SalesInvoiceController {
    private SalesInvoiceModel salesinvoiceModel;
    private SalesInvoiceListGUI gui;
    private int itemcount;
    
    public SalesInvoiceController(SalesInvoiceModel tempModel,SalesInvoiceListGUI tempGUI)
    {
        this.salesinvoiceModel = tempModel;
        this.gui = tempGUI;
    }
    
    public SalesInvoiceController(SalesInvoiceModel tempModel)
    {
        this.salesinvoiceModel = tempModel;
    }
    
    TableModel getAllModel()
    {
       TableModel tbm = salesinvoiceModel.myModel(salesinvoiceModel.getAllDetail());
       this.itemcount = salesinvoiceModel.getItemcount();
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
            TableModel tbm;
            tbm = salesinvoiceModel.myModel(salesinvoiceModel.searchDetail(text, searchBy,startDate,endDate));
            this.itemcount = salesinvoiceModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public String getMaxYear()
    {
        ResultSet resultset =salesinvoiceModel.getMaxYear();
          
        try {
            resultset.next();
            return resultset.getString("MAX(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(SalesInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset =salesinvoiceModel.getMinYear();
        
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(SalesInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public void ViewSalesInvoicebyDate(String startDate,String endDate)
    {
       TableModel tbm = salesinvoiceModel.myModel(salesinvoiceModel.getAllDetailbyDate(startDate,endDate));
       this.itemcount = salesinvoiceModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }
}

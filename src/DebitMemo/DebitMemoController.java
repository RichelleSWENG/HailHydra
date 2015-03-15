package DebitMemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;


public class DebitMemoController {
    private DebitMemoModel debitmemoModel;
    private DebitMemoListGUI gui;
    private int itemcount;
    
    public DebitMemoController(DebitMemoModel tempModel, DebitMemoListGUI tempGUI)
    {
        this.debitmemoModel=tempModel;
        this.gui = tempGUI;
        
    }
    
    TableModel getAllModel()
    {
       TableModel tbm = debitmemoModel.myModel(debitmemoModel.getAllDetail());
       this.itemcount = debitmemoModel.getItemcount();
       gui.setItemCount(itemcount);
        return tbm;
    }
    
    public void SearchSomething(String text, int type,String startDate,String endDate)
    {
            String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "debit memo number";
            else if(type==2)
                searchBy = "part number";
            TableModel tbm;
            tbm = debitmemoModel.myModel(debitmemoModel.searchDetail(text, searchBy,startDate,endDate));
            this.itemcount = debitmemoModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public void searchbyDate(String startDate,String endDate)
    {
        TableModel tbm;
            tbm = debitmemoModel.myModel(debitmemoModel.getAllDetailbyDate(startDate,endDate));
            this.itemcount = debitmemoModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
     public String getMaxYear()
    {
        ResultSet resultset =debitmemoModel.getMaxYear();
          
        try {
            resultset.next();
            return resultset.getString("MAX(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(DebitMemoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset =debitmemoModel.getMinYear();
        
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(DebitMemoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
}

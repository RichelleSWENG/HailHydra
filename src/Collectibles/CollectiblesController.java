package Collectibles;

import Payables.PayablesController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;


public class CollectiblesController {
    private CollectiblesModel collectiblesModel;
    private CollectiblesListGUI gui;
    private int itemcount;
    
    public CollectiblesController(CollectiblesModel tempModel,CollectiblesListGUI tempGUI)
    {
        this.collectiblesModel = tempModel;
        this.gui = tempGUI;
    }

    public CollectiblesController(CollectiblesModel tempModel)
    {
        this.collectiblesModel = tempModel;
    }

    TableModel getAllModel()
    {
       TableModel tbm = collectiblesModel.myModel(collectiblesModel.getCollectibles());
       this.itemcount = collectiblesModel.getItemcount();
       gui.setItemCount(itemcount);
        return tbm;
    }

    public void ViewActiveCollectibles(String startDate,String endDate)
    {
       TableModel tbm = collectiblesModel.myModel(collectiblesModel.getAllActiveCollectibles(startDate,endDate));
       this.itemcount = collectiblesModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }

    public void ViewClosedCollectibles(String startDate,String endDate)
    {
       TableModel tbm = collectiblesModel.myModel(collectiblesModel.getAllClosedCollectibles(startDate,endDate));
       this.itemcount = collectiblesModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }

    public void SearchSomething(String text, int type,String startDate,String endDate)
    {
        
        String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "active";
            else if(type == 2)
                searchBy = "closed";
            TableModel tbm;
            tbm = collectiblesModel.myModel(collectiblesModel.searchCollectibles(text, searchBy,startDate,endDate));
            this.itemcount = collectiblesModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    public String getMaxYear()
    {
        ResultSet resultset=collectiblesModel.getMaxYear();
        try {
            resultset.next();
            return resultset.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(CollectiblesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset=collectiblesModel.getMinYear();
        try {
            resultset.next();
            return resultset.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(CollectiblesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "";
    }
    public void DateSearch(String startDate,String endDate)
    {
       TableModel tbm = collectiblesModel.myModel(collectiblesModel.getCollectiblesbyDate(startDate,endDate));
       this.itemcount = collectiblesModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }
}

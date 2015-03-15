/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReturnSlip;

import Purchases.PurchaseTransactionController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author Janine
 */
public class ReturnSlipController {
    private ReturnSlipModel returnslipModel;
    private ReturnSlipListGUI gui;
    private int itemcount;
    
    public ReturnSlipController(ReturnSlipModel tempModel, ReturnSlipListGUI tempGUI)
    {
        this.returnslipModel=tempModel;
        this.gui=tempGUI;
    }
    
    TableModel getAllModel()
    {
       TableModel tbm = returnslipModel.myModel(returnslipModel.getAllDetail());
       this.itemcount = returnslipModel.getItemcount();
       gui.setItemCount(itemcount);
        return tbm;
    }
    
    public void SearchSomething(String text, int type,String startDate,String endDate)
    {
            String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "return slip number";
            else if(type==2)
                searchBy = "part number";
            TableModel tbm;
            tbm = returnslipModel.myModel(returnslipModel.searchDetail(text, searchBy,startDate,endDate));
            this.itemcount = returnslipModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public void searchbyDate(String startDate,String endDate)
    {
        TableModel tbm;
            tbm = returnslipModel.myModel(returnslipModel.getAllDetailbyDate(startDate,endDate));
            this.itemcount = returnslipModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
     public String getMaxYear()
    {
        ResultSet resultset =returnslipModel.getMaxYear();
          
        try {
            resultset.next();
            return resultset.getString("MAX(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(ReturnSlipController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset =returnslipModel.getMinYear();
        
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(ReturnSlipController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}

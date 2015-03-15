/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreditMemo;

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
public class CreditMemoController {
    private CreditMemoModel creditmemoModel;
    private CreditMemoListGUI gui;
    private int itemcount;
    
    public CreditMemoController(CreditMemoModel tempModel, CreditMemoListGUI tempGUI)
    {
        this.creditmemoModel= tempModel;
        this.gui = tempGUI;
    }
    
    TableModel getAllModel()
    {
       TableModel tbm = creditmemoModel.myModel(creditmemoModel.getAllDetail());
       this.itemcount = creditmemoModel.getItemcount();
       gui.setItemCount(itemcount);
        return tbm;
    }
    
    public void SearchSomething(String text, int type,String startDate,String endDate)
    {
            String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "credit memo number";
            else if(type==2)
                searchBy = "return slip number";
            TableModel tbm;
            tbm = creditmemoModel.myModel(creditmemoModel.searchDetail(text, searchBy,startDate,endDate));
            this.itemcount = creditmemoModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public void searchbyDate(String startDate,String endDate)
    {
            TableModel tbm;
            tbm = creditmemoModel.myModel(creditmemoModel.getAllDetailbyDate(startDate,endDate));
            this.itemcount = creditmemoModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    
    public String getMaxYear()
    {
        ResultSet resultset =creditmemoModel.getMaxYear();
          
        try {
            resultset.next();
            return resultset.getString("MAX(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(CreditMemoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset =creditmemoModel.getMinYear();
        
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(CreditMemoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}

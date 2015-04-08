/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author Kingston
 */
public class PayablesController
{
    private PayablesModel payablesModel;
    private PayablesListGUI gui;
    private int itemcount;
    
    public PayablesController(PayablesModel tempModel,PayablesListGUI tempGUI)
    {
        this.payablesModel = tempModel;
        this.gui = tempGUI;
    }

    public PayablesController(PayablesModel tempModel)
    {
        this.payablesModel = tempModel;
    }

    TableModel getAllModel()
    {
       TableModel tbm = payablesModel.myModel(payablesModel.getPayables());
       this.itemcount = payablesModel.getItemcount();
       gui.setItemCount(itemcount);
        return tbm;
    }

    public void ViewActivePayables(String startDate,String endDate)
    {
       TableModel tbm = payablesModel.myModel(payablesModel.getAllActivePayables(startDate,endDate));
       this.itemcount = payablesModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }

    public void ViewClosedPayables(String startDate,String endDate)
    {
       TableModel tbm = payablesModel.myModel(payablesModel.getAllClosedPayables(startDate,endDate));
       this.itemcount = payablesModel.getItemcount();
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
            tbm = payablesModel.myModel(payablesModel.searchPayables(text, searchBy,startDate,endDate));
            this.itemcount = payablesModel.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
    }
    public String getMaxYear()
    {
        ResultSet resultset=payablesModel.getMaxYear();
        try {
            resultset.next();
            return resultset.getString("MAX(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(PayablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String getMinYear()
    {
        ResultSet resultset=payablesModel.getMinYear();
        try {
            resultset.next();
            return resultset.getString("MIN(YEAR(date))");
        } catch (SQLException ex) {
            Logger.getLogger(PayablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "";
    }
    public void DateSearch(String startDate,String endDate)
    {
        TableModel tbm = payablesModel.myModel(payablesModel.getPayablesbyDate(startDate,endDate));
       this.itemcount = payablesModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }
    
}

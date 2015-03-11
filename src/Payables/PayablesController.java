/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payables;

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

    public void ViewActivePayables()
    {
       TableModel tbm = payablesModel.myModel(payablesModel.getAllActivePayables());
       this.itemcount = payablesModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }

    public void ViewClosedPayables()
    {
       TableModel tbm = payablesModel.myModel(payablesModel.getAllClosedPayables());
       this.itemcount = payablesModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }

    public void SearchSomething(String text, int type)
    {
        
        String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "active";
            else if(type == 2)
                searchBy = "closed";
            TableModel tbm;
            tbm = payablesModel.myModel(payablesModel.searchPayables(text, searchBy));
            gui.setTableModel(tbm);
    }
    
}

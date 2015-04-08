/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inventory;

import java.math.BigDecimal;
import javax.swing.table.TableModel;

/**
 *
 * @author Janine
 */
public class LastCostController {
        private ItemModel model;
        private SetInventoryLastCostGUI gui;
        private int itemcount;
        
        public LastCostController(ItemModel model,SetInventoryLastCostGUI gui)
        {
            this.model=model;
            this.gui=gui;
        }
        
        TableModel getAllModel()
        {
           TableModel tbm = model.myModel(model.getAllLastCost());
           this.itemcount = model.getItemcount();
           gui.setItemCount(itemcount);
           return tbm;
        }
        
        public void searchSomething(String text,int type)
        {
            String searchBy = null;
            if(type == 0)
                searchBy = "part_num";
            else if(type == 1)
                searchBy = "description";
            TableModel tbm;
            tbm = model.myModel(model.searchLastCost(text, searchBy));
            this.itemcount = model.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
        }
        
        public void changeLastCost(String part_num,String last_cost)
        {
            model.changeLastCost(part_num, last_cost);
        }
        
        public void changeAllLastCost()
        {
            gui.changeLastCost();
        }
        
        
        
        
    
}

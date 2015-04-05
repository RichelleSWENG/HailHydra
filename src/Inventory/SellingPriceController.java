/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inventory;

import javax.swing.table.TableModel;

/**
 *
 * @author Janine
 */
public class SellingPriceController {
        private ItemModel model;
        private SetInventorySellingPriceGUI gui;
        private int itemcount;
        
        public SellingPriceController(ItemModel model,SetInventorySellingPriceGUI tempGUI)
        {
            this.model=model;
            this.gui=tempGUI;
        }
        
        TableModel getAllModel()
        {
           TableModel tbm = model.myModel(model.getAllPrices());
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
            tbm = model.myModel(model.searchPrices(text, searchBy));
            this.itemcount = model.getItemcount();
            gui.setItemCount(itemcount);
            gui.setTableModel(tbm);
        }
        
        public void changeWalkInPrices(String part_num,String wprice)
        {
            model.changeWalkinPrice(part_num, wprice);
        }
        
        public void changeTradersPrice(String part_num,String tprice)
        {
            model.changeTradersPrice(part_num, tprice);
        }
        
        public void changeSisterCompanyPrice(String part_num,String sprice)
        {
            model.changeSisterCompanyPrice(part_num, sprice);
        }
        
        public void changeAllPrices()
        {
            gui.changePrices();
        }
        
    
}

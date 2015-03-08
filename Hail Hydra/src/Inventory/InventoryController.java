/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Kingston
 */
public class InventoryController {
    
    private InventoryModel im;
    private InventoryView iv;
    private InventoryController ic;
    private SQLStatements sql;
    private int itemcount=0;
    private AddItemProfile aip;
    private ViewItemProfile vip;
    private Object btnAddPoster;

    
   public InventoryController(){
   sql= new SQLStatements(); 
   }
    
   public void setModel(InventoryModel im) {
        this.im=im;
    }
   public boolean getConnectionStatus(){
   return im.getConnectionStatus();
   }

    public void SearchSomething(String text, String searchBy) throws Exception {
        TableModel tbm;
        if(searchBy.equals("Description")){
            tbm=SearchDescription(text);
            iv.setTableModel(tbm);
            iv.setItemCount(im.getItemcount());
        }
       else if(searchBy.equals("Part Number")){
            tbm=SearchPartNumber(text);
            iv.setTableModel(tbm);
            iv.setItemCount(im.getItemcount());
        }
            }
        

   public TableModel getAllModel() throws Exception {
       TableModel tbm=im.myModel(sql.getAllInventory());
       this.itemcount=im.getItemcount();
       iv.setItemCount(itemcount);
        return tbm;
    }
   
   public TableModel SearchPartNumber(String text) throws Exception{
 
       TableModel tbm = im.myModel(sql.searchPartNumber(text));
       this.itemcount=im.getItemcount();
       iv.setItemCount(itemcount);
        return tbm;
   } 
   
   private TableModel SearchDescription(String text) throws Exception {
       TableModel tbm= im.myModel(sql.searchDescription(text));
       this.itemcount=itemcount;
       iv.setItemCount(itemcount);
        return tbm;
    }
   public void setView(InventoryView iv) {
        this.iv=iv;
    }

    public void AddItemProfileView() {
       iv.setVisible(false);
        aip= new AddItemProfile();
       aip.setVisible(true);
       aip.setController(ic);
       aip.setConnectionStatus();
    }

    public void setController(InventoryController ic) {
        this.ic=ic;
    }

    public void ivVisibleAIP() throws Exception {
        //vip.setVisible(false);
        aip.setVisible(false);
        iv.viewAll();
        iv.setVisible(true);
        
    }
    public void ivVisibleVIP() throws Exception {
        vip.setVisible(false);
        iv.viewAll();
        iv.setVisible(true);
        
    }

    public void AddItem(String partnumber, String description, String racklocation, String stockminimum, String sisterprice, String retailprice, String walkprice, String lastcost, String notes, String imagelocation, String status) throws SQLException {
        String tmp = null;
        if(imagelocation!=null)
        tmp = imagelocation.replace('\\', '/'); //replace \\ with /
        int statusBit;
        if(status=="false")
            statusBit = 1;
        else statusBit = 0;
            
       im.AddItemProfile(sql.addItemProfile(partnumber,description,racklocation,stockminimum,sisterprice,retailprice,walkprice,lastcost,notes,tmp,statusBit));
        
    }

    public void ViewAllItems() throws Exception {
        iv.viewAll();
    }


    public void DeleteItem(String pkey) throws SQLException
    {
        im.DeleteItemProfile(sql.deleteItemProfile(pkey));
    }

    public void ViewItemProfile(String pkey) throws SQLException, IOException
    {
        ArrayList<String> itemProfile = im.getItemProfile(sql.getItemProfile(pkey)); // get result of the selected row 
        vip = new ViewItemProfile(itemProfile);                     // create the view Profile
        iv.setVisible(false);
        vip.setVisible(true);
        vip.setController(ic);
        vip.setConnectionStatus();
    }
    }

   
    


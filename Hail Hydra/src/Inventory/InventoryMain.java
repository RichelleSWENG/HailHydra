/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

/**
 *
 * @author Kingston
 */
public class InventoryMain {
    	public static void main(String[] args) throws Exception 
        {
                
                InventoryModel im= new InventoryModel();           // create Model DBConnection is created also
                InventoryController ic= new InventoryController(); // create controller 
                ic.setController(ic);
                ic.setModel(im);                                   // set Model to the Controller
                InventoryView iv= new InventoryView();             // create View
                ic.setView(iv);
                iv.setController(ic);                              // set Controller to the View
                iv.setConnectionStatus();                          // set connection status from the connection created from the Model
                iv.viewAll();
                iv.setVisible(true);                               // display the View
                
                
                
                
	}

}

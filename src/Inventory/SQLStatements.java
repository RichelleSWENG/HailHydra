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
public class SQLStatements {
   
    
    public String getAllInventory(){
    return "SELECT part_num, description, quantity_functional,quantity_defective, last_cost,walk_in_price, traders_price,sister_company_price FROM item";
    }
    
    public String searchPartNumber(String text){
        return "SELECT part_num, description, quantity_functional,quantity_defective, last_cost,walk_in_price, traders_price,sister_company_price FROM item WHERE part_num LIKE '%"+text+"%'";
    
    }

    String searchDescription(String text) {
        return "SELECT part_num, description, quantity_functional,quantity_defective, last_cost,walk_in_price, traders_price,sister_company_price FROM item WHERE description LIKE '%"+text+"%'";
    }
    

    String addItemProfile(String partnumber, String description, String racklocation, String stockminimum, String sisterprice, String retailprice, String walkprice, String lastcost, String notes, String imagelocation, int status) {
       return "INSERT INTO item (part_num,description,stock_minimum,rack_location,last_cost,walk_in_price,traders_price,sister_company_price,notes,image,status) VALUES('"+partnumber+"','"+description+"','"+stockminimum+"','"+racklocation+"','"+lastcost+"','"+walkprice+"','"+retailprice+"','"+sisterprice+"','"+notes+"','"+imagelocation+"','"+status+"')";
    }

    String deleteItemProfile(String pkey) {
       return "DELETE FROM item WHERE part_num = '"+pkey+"'";
    }

    String getItemProfile(String pkey){
        return "SELECT part_num, description, rack_location, stock_minimum, sister_company_price, traders_price, walk_in_price, last_cost, notes, image, status FROM item WHERE part_num = '"+pkey+"'";
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BasicSystemSettings;

import Database.DBConnection;
import Payables.Payment;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Janine
 */
public class SystemModel {
    protected Connection db;
    protected Statement statement;
    
    public SystemModel(DBConnection db)
    {
        this.db=db.getConnection();
    }
    
    public void addAccount(String username,String password,String type)
    {
        try
        {
            statement = db.createStatement();
            String sql = "INSERT INTO account(user_name,password,type) VALUES('"+username+"','"+password+"','"+type+"')";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void addSystemInfo(String name,String vat,String credit,String terms)
    {
        try
        {
            statement = db.createStatement();
            String sql = "INSERT INTO systeminfo(company_name,vat_percentage,credit_alert,terms_report) VALUES('"+name+"','"+vat+"','"+credit+"','"+terms+"')";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

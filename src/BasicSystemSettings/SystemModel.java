/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BasicSystemSettings;

import Database.DBConnection;
import ModifyAlertVAT.FactoryModify;
import Payables.Payment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String sql = "INSERT INTO systeminfo(system_info_id,company_name,vat_percentage,credit_alert,terms_report) VALUES('1','"+name+"','"+vat+"','"+credit+"','"+terms+"')";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String getCompanyName()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT company_name FROM systeminfo WHERE system_info_id='1'";
            rs = statement.executeQuery(sql);

        } catch (Exception e)
        {
            e.getMessage();
        }
        try {
                if(!rs.next())
                    return null;
                else
                    return rs.getString("company_name");
            } catch (SQLException ex) {
                Logger.getLogger(FactoryModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payables;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Janine
 */
public class PaymentModel {
    protected Connection db;
    protected Statement statement;
    
    public PaymentModel(DBConnection db)
    {
        this.db=db.getConnection();
    }
    
    ResultSet getAllActivePayables()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT date,purchase_transaction_id,purchasetransaction.status,original_amount,current_balance,'' as applied FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND purchasetransaction.status = 'Open'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
    ResultSet searchActivePayables(String name)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT date,purchase_transaction_id,purchasetransaction.status,original_amount,current_balance,'' as applied FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND purchasetransaction.status = 'Open' AND name LIKE '"+name+"'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
    public void addPayment()
    {
         try
        {
            statement = db.createStatement();
            String sql = "INSERT INTO ";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public TableModel myModel(ResultSet rs)
    {     
        TableModel model = DbUtils.resultSetToTableModel(rs);
        return model;
    }
}

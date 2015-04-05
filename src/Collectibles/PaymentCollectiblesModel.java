/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collectibles;

import Database.DBConnection;
import Payables.Payment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Janine
 */
public class PaymentCollectiblesModel {
    protected Connection db;
    protected Statement statement;
    
    public PaymentCollectiblesModel(DBConnection db)
    {
        this.db=db.getConnection();
    }
    
    ResultSet searchActiveCollectibles(String name)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT date,salesinvoice.type,sales_invoice_id,salesinvoice.status,original_amount,current_balance,'0.00' as applied FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.status LIKE 'Open' AND name LIKE '"+name+"' UNION ALL SELECT date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.status,acknowledgementreceipt.original_amount,current_balance,'0.00' as applied FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.status LIKE 'Open' AND name LIKE '"+name+"'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
    public void addPayment(Collection obj)
    {
        try
        {
            statement = db.createStatement();
            String sql = "INSERT INTO collection(amount,receipt_type,number,notes,date,received_by,collection_type,debit_memo_id,received_date) VALUES('"+obj.getAmount()+"','"+obj.getReceipt_type()+"','"+obj.getNumber()+"','"+obj.getNotes()+"','"+obj.getDate()+"','"+obj.getReceived_by()+"','"+obj.getCollection_type()+"','"+obj.getDebit_memo_id()+"',"+obj.getReceived_date()+")";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public ResultSet getCustomer()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT name from company WHERE type LIKE '%Customer'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
    public TableModel myModel(ResultSet rs)
    {     
        TableModel model = DbUtils.resultSetToTableModel(rs);
        return model;
    }
    
    public void changeStatusSI(int number)
    {
        try
        {
            statement = db.createStatement();
            String sql = "UPDATE salesinvoice SET status='Closed' WHERE sales_invoice_id='"+number+"'";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void changeStatusAR(int number)
    {
        try
        {
            statement = db.createStatement();
            String sql = "UPDATE acknowledgementreceipt SET status='Closed' WHERE acknowledgement_receipt_id='"+number+"'";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateCurrentBalanceSI(int number,float currentbalance)
    {
        try
        {
            statement = db.createStatement();
            String sql = "UPDATE salesinvoice SET current_balance='"+currentbalance+"' WHERE sales_invoice_id='"+number+"'";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateCurrentBalanceAR(int number,float currentbalance)
    {
        try
        {
            statement = db.createStatement();
            String sql = "UPDATE acknowledgementreceipt SET current_balance='"+currentbalance+"' WHERE acknowledgement_receipt_id='"+number+"'";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

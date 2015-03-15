/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreditMemo;

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
public class CreditMemoModel {
    protected Connection db;
    protected Statement statement;
    private int itemCount=0;
    
    public CreditMemoModel(DBConnection db)
    {
        this.db = db.getConnection();
    }
    
    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id";
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
    public ResultSet getAllDetailbyDate(String startDate,String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"'";
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
    public ResultSet searchDetail(String field, String filter,String startDate,String endDate)
    {
         ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql="";
            if(filter.equalsIgnoreCase("name"))
            {
                    sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"' AND company.name LIKE '%"+field+"%'";
            }	
            else if(filter.equalsIgnoreCase("credit memo number"))
            {
                    sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"' AND creditmemo.credit_memo_id LIKE '%"+field+"%'";
            }
            else if(filter.equalsIgnoreCase("return slip number"))
            {
                    sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"' AND creditmemo.return_slip_id LIKE '%"+field+"%'";
            }
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
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
    
    public ResultSet getMinYear()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT MIN(YEAR(date)) FROM creditmemo";
            rs = statement.executeQuery(sql);
            
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
     public ResultSet getMaxYear()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT MAX(YEAR(date)) FROM creditmemo";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
     
    public int getItemcount()
    {
        return this.itemCount;
    }
}

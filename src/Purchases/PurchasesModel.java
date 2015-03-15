package Purchases;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class PurchasesModel 
{
    protected Connection db;
    protected Statement statement;
    private int itemCount=0;
    
    public PurchasesModel(DBConnection db)
    {
        this.db = db.getConnection();
    }

    public ResultSet getDetail(String ID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id=purchasetransaction.company_id";
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
            String sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id=purchasetransaction.company_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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
                    sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id= purchasetransaction.company_id AND company.name LIKE '%" + field + "%' AND purchasetransaction.date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            }	
            else if(filter.equalsIgnoreCase("transaction number"))
            {
                    sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id= purchasetransaction.company_id AND purchase_transaction_id LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                    System.out.println(sql);
            }
            else if(filter.equalsIgnoreCase("part number"))
            {
                    sql = "SELECT company.name, date, purchasetransaction.purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction,ptlineitem WHERE company.company_id= purchasetransaction.company_id AND purchasetransaction.purchase_transaction_id=ptlineitem.purchase_transaction_id AND part_num LIKE '%"+field+"%' AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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

    public void addDetail(ArrayList list)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editDetail(ArrayList list)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteDetail(String ID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            String sql = "SELECT MIN(YEAR(date)) FROM purchasetransaction";
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
            String sql = "SELECT MAX(YEAR(date)) FROM purchasetransaction";
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

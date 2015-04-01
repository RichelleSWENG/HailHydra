package Payables;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class PayablesModel 
{
    private int itemCount;
    protected Connection db;
    protected Statement statement;
    
    public PayablesModel(DBConnection db)
    {
        this.db=db.getConnection();
    }

    public ResultSet getDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT payment_id,payment_type_id,amount,purchase_transaction_id,notes,date,approved_by,prepared_by,received_by FROM payments WHERE id='" + ID + "'";
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
    
    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT payment_id,payment_type_id,amount,purchase_transaction_id,notes FROM payments";
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
    
    public void deleteDetail(String ID)
    {
        try
        {
            statement = db.createStatement();
            String sql = "DELETE FROM payments WHERE payment_id='" + ID + "'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

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
    

    public ResultSet getPayables()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id";
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
    public ResultSet getPayablesbyDate(String startDate,String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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

    public ResultSet searchPayables(String field, String filter,String startDate,String endDate)
    {
        ResultSet rs = null;
        String sql = "";
        try
        {
            statement = db.createStatement();
              if (filter.equalsIgnoreCase("name"))
            {
                    sql="SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"' AND name LIKE '%"+ field +"%'";
            }
              else if (filter.equalsIgnoreCase("active"))
            {
                    sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND purchasetransaction.status LIKE 'Open' AND name LIKE '%" + field + "%' AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
            } else if (filter.equalsIgnoreCase("closed"))
            {
                    sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND purchasetransaction.status LIKE 'Closed' AND name LIKE '%" + field + "%' AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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
    
    public int getItemcount()
    {
        return this.itemCount;
    }

    ResultSet getAllActivePayables(String startDate,String endDate)
    {
        
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND purchasetransaction.status = 'Open' AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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

    ResultSet getAllClosedPayables(String startDate,String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND purchasetransaction.status = 'Closed' AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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
    
    public void addPayment()
    {
        try
        {
            statement = db.createStatement();
            String sql = "";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

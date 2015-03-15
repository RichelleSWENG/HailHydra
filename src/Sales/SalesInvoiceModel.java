package Sales;

import AcknowledgementReceipt.ARLineItemModel;
import Classes.Company;
import Classes.Item;
import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class SalesInvoiceModel 
{   
    protected Connection db;
    protected Statement statement;
    
    private int itemCount = 0;
    private ArrayList<Company> customers;
    private ArrayList<Item> items;
    private SILineItemModel siLineItemModel;
    
    public SalesInvoiceModel(DBConnection db)
    {
        this.db = db.getConnection();
    }

    public ResultSet getDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "";
            rs = statement.executeQuery(sql);
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
            String sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id=salesinvoice.company_id";
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
    
    public ResultSet getAllDetailbyDate(String startDate, String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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
    
    public ResultSet searchDetail (String field, String filter, String startDate, String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql="";
            if(filter.equalsIgnoreCase("name"))
            {
                    sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id= salesinvoice.company_id AND company.name LIKE '%" + field + "%' AND salesinvoice.date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            }	
            else if(filter.equalsIgnoreCase("transaction number"))
            {
                    sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id= salesinvoice.company_id AND salesinvoice.sales_invoice_id LIKE '%" + field + "%' AND salesinvoice.date BETWEEN '" + startDate + "' AND '" + endDate + "'";
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
    
    
     public void addDetail(SalesInvoice obj)
     {
 
        SalesInvoice si = obj;
        try
        {
        
            statement = db.createStatement();
            String sql = "INSERT INTO salesinvoice(sales_invoice_id,company_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status) VALUES('" + si.getSales_invoice_id() + "','" + si.getCompany_id()  + "','" + si.getDate() + "','" + si.getPo_num() + "','" + si.getDelivery_receipt_num() + "','" + si.getSales_person() + "','" + si.getOrdered_by() + "','" + si.getDelivered_by() + "','" + si.getDelivery_notes() + "','" + si.getDiscount() + "','" + si.getOriginal_amount() + "','" + si.getCurrent_balance() + "','" + si.getStatus()+"')";
            System.out.println(sql);
            statement.executeUpdate(sql);
            int i;
            for (i = 0; i < si.getItems().size(); i++)
            {
                siLineItemModel.addDetail(si.getItems().get(i));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void editDetail(ArrayList list)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteDetail(String ID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ResultSet getMinYear()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT MIN(YEAR(date)) FROM salesinvoice";
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
            String sql = "SELECT MAX(YEAR(date)) FROM salesinvoice";
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
    
    public TableModel myModel(ResultSet rs)
    {     
        TableModel model = DbUtils.resultSetToTableModel(rs);
        return model;
    }
}

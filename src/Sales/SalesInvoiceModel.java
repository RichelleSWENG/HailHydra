package Sales;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SalesInvoiceModel extends Model
{

    public SalesInvoiceModel(DBConnection db)
    {
        super(db);
    }

    @Override
    public ResultSet getDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql = "";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    @Override
    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice";
            rs = statement.executeQuery(sql);
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
            statement = con.createStatement();
            String sql;
            if(filter.equalsIgnoreCase("name"))
            {
                if(startDate != null && endDate != null)
                    sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.name LIKE '%" + field + "%' AND salesinvoice.date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                else
                    sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.name LIKE '%" + field + "%'";
            }	
            else
            {
                if(startDate != null && endDate != null)
                    sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE salesinvoice.sales_invoice_id LIKE '%" + field + "%' AND salesinvoice.date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                else
                    sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE salesinvoice.sales_invoice_id LIKE '%" + field + "%'";
            }
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    @Override
    public ResultSet searchDetail(String field, String filter)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDetail(ArrayList list)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editDetail(ArrayList list)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDetail(String ID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

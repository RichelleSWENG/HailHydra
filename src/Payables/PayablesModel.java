package Payables;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PayablesModel extends Model
{

    public PayablesModel(DBConnection db)
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
            String sql = "SELECT payment_id,payment_type_id,amount,purchase_transaction_id,notes,date,approved_by,prepared_by,received_by FROM payments WHERE id='" + ID + "'";
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
            String sql = "SELECT payment_id,payment_type_id,amount,purchase_transaction_id,notes FROM payments";
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
    public void addDetail(ArrayList list)
    {
        try
        {
            statement = con.createStatement();
            String sql = "";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public void editDetail(ArrayList list)
    {
        try
        {
            statement = con.createStatement();
            String sql = "";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    @Override
    public void deleteDetail(String ID)
    {
        try
        {
            statement = con.createStatement();
            String sql = "DELETE FROM payments WHERE payment_id='" + ID + "'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    public ResultSet getPayables()
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet searchPayables(String field, String filter)
    {
        ResultSet rs = null;
        String sql = "";
        try
        {
            statement = con.createStatement();
            if (filter.equalsIgnoreCase("Active Payables"))
            {
                sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND status LIKE 'Open'";
            } else if (filter.equalsIgnoreCase("Closed Payables"))
            {
                sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND status LIKE 'Closed'";
            }
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet searchbyDatePayables(ArrayList list)
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql = "SELECT name,date,purchase_transaction_id,original_amount,current_balance,purchasetransaction.status FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND date>='" + list.get(0) + "' AND date <='" + list.get(1) + "'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

}

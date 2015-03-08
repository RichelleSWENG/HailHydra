package SystemAccount;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SystemAccountModel extends Model
{

    public SystemAccountModel(DBConnection db)
    {
        super(db);
    }

    @Override
    public ResultSet getDetail(String ID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getAllDetail()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet searchDetail(String field, String filter)
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql;
            sql = "SELECT account_name, account_num, bank_name, bank_branch FROM systemaccount WHERE type = '" + field + "'";
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
            String sql = "INSERT INTO systemaccount(account_num,account_name,bank_name,bank_branch,type) VALUES('" + list.get(0) + "','" + list.get(1) + "','" + list.get(2) + "','" + list.get(3) + "','" + list.get(4) + "')";
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
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

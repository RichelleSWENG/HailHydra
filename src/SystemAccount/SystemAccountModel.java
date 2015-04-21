package SystemAccount;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SystemAccountModel
{
	private int itemCount;
	protected Connection con;
	protected Statement statement;

	public SystemAccountModel(DBConnection db)
	{
		this.con = db.getConnection();
	}

	public ResultSet searchDetail(String field, String filter)
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql;
			sql = "SELECT account_name, account_num, bank_name, bank_branch FROM systemaccount WHERE type = "
					+ field + "";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public void addDetail(ArrayList list)
	{
		try
		{
			statement = con.createStatement();
			String sql = "INSERT INTO systemaccount(account_num,account_name,bank_name,bank_branch,type) VALUES('"
					+ list.get(0)
					+ "','"
					+ list.get(1)
					+ "','"
					+ list.get(2)
					+ "','" + list.get(3) + "','" + list.get(4) + "')";
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}
        
        public boolean duplicateAccNum(String accNum)
        {
            ResultSet rs = null;
            try
            {
                    statement = con.createStatement();
                    String sql = "SELECT * FROM systemaccount WHERE account_num = '" + accNum + "'";
                    rs = statement.executeQuery(sql);
                    if (!rs.isBeforeFirst())
                        return false;
            } catch (Exception e)
            {
                    e.printStackTrace();
            }
            return true;
        }

	public ResultSet getCompanyName()
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql;
			sql = "SELECT company_name FROM systeminfo WHERE system_info_id=1";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ResultSet getCompanyAdress()
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql;
			sql = "SELECT company_address FROM systeminfo WHERE system_info_id=1";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public void changeSystemProfile(String name, String address)
	{
		try
		{
			statement = con.createStatement();
			String sql = "UPDATE systeminfo SET company_name='" + name
					+ "',company_address='" + address
					+ "' WHERE system_info_id='1'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}

	public ResultSet getOldPassword(int type)
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql;
			sql = "SELECT password FROM account WHERE type='" + type + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public void changePassword(int type, String password)
	{
		try
		{
			statement = con.createStatement();
			String sql = "UPDATE account SET password='" + password
					+ "' WHERE type='" + type + "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}
}

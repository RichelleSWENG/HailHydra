package Purchases;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PTLineItemModel
{
	protected Connection db;
	protected Statement statement;

	public PTLineItemModel(DBConnection db)
	{
		this.db = db.getConnection();
	}

	public ResultSet getDetail(String ID, String partnum)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT quantity,part_num,unit_price,line_total FROM ptlineitem WHERE purchase_transaction_id='"
					+ ID + "' AND part_num='" + partnum + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ResultSet getAllDetail(String ID)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT quantity,part_num,unit_price,line_total FROM ptlineitem WHERE purchase_transaction_id='"
					+ ID + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public void addDetail(PTLineItem lineitem)
	{
		try
		{
			statement = db.createStatement();
			String sql = "INSERT INTO ptlineitem(purchase_transaction_id,quantity,part_num,unit_price,line_total) VALUES('"
					+ lineitem.getPurchase_transaction_id()
					+ "','"
					+ lineitem.getQuantity()
					+ "','"
					+ lineitem.getPartNum()
					+ "','"
					+ lineitem.getUnit_price()
					+ "','"
					+ lineitem.getLine_total() + "')";
			statement.executeUpdate(sql);
			System.out.println(sql);

		} catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void editDetail(PTLineItem lineitem)
	{
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE ptlineitem SET quantity='"
					+ lineitem.getQuantity() + "',unit_price='"
					+ lineitem.getUnit_price() + ",line_total='"
					+ lineitem.getLine_total()
					+ "' WHERE acknowledgement_receipt_id='"
					+ lineitem.getPurchase_transaction_id()
					+ "' AND part_num='" + lineitem.getPartNum() + "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

	}

	public void deleteDetail(String ID, String partnum)
	{
		try
		{
			statement = db.createStatement();
			String sql = "DELETE FROM ptlineitem WHERE purchase_transaction_id='"
					+ ID + "' AND part_num='" + partnum + "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

	}

	public void deleteAllDetail(String ID)
	{
		try
		{
			statement = db.createStatement();
			String sql = "DELETE FROM ptlineitem WHERE purchase_transaction_id='"
					+ ID + "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void updateQuantity(String partNum, int quantity)
	{
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE item SET quantity_functional ='" + quantity
					+ "' WHERE part_num = '" + partNum + "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}
}

package ReturnSlip;

import AcknowledgementReceipt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import Database.DBConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jolo Simeon
 */
public class RSLineItemModel
{
	protected Connection db;
	protected Statement statement;

	public RSLineItemModel(DBConnection db)
	{
		this.db = db.getConnection();
	}

	public ResultSet getDetail(String ID, String partnum)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT quantity,part_num,unit_price,line_total FROM rslineitem WHERE return_slip_id='"
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
			String sql = "SELECT quantity,part_num,unit_price,line_total FROM rslineitem WHERE return_slip_id='"
					+ ID + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public void addDetail(Object obj)
	{
		RSLineItem lineitem = (RSLineItem) obj;
		try
		{
			statement = db.createStatement();
			String sql = "INSERT INTO rslineitem(return_slip_id,quantity,part_num,unit_price,line_total,status) VALUES('"
					+ lineitem.getReturn_slip_id()
					+ "','"
					+ lineitem.getQuantity()
					+ "','"
					+ lineitem.getPartNum()
					+ "','"
					+ lineitem.getUnit_price()
					+ "','"
					+ lineitem.getLine_total() + "',0)"; // NAKAHARDCODEsss
			statement.executeUpdate(sql);
			System.out.println(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void editDetail(Object obj)
	{
		RSLineItem lineitem = (RSLineItem) obj;
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE rslineitem SET quantity='"
					+ lineitem.getQuantity() + "',unit_price='"
					+ lineitem.getUnit_price() + ",line_total='"
					+ lineitem.getLine_total()
					+ "' WHERE acknowledgement_receipt_id='"
					+ lineitem.getReturn_slip_id() + "' AND part_num='"
					+ lineitem.getPartNum() + "'";
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
			String sql = "DELETE FROM rslineitem WHERE return_slip_id='" + ID
					+ "' AND part_num='" + partnum + "'";
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
			String sql = "DELETE FROM rslineitem WHERE return_slip_id='" + ID
					+ "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}
}

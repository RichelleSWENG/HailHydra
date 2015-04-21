/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payables;

import Database.DBConnection;
import Purchases.PurchaseTransaction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Janine
 */
public class PaymentModel
{
	protected Connection db;
	protected Statement statement;

	public PaymentModel(DBConnection db)
	{
		this.db = db.getConnection();
	}

	ResultSet searchActivePayables(String name)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT date,purchase_transaction_id,purchasetransaction.status,original_amount,current_balance,'0.00' as applied FROM purchasetransaction,company WHERE purchasetransaction.company_id=company.company_id AND purchasetransaction.status = 'Open' AND name LIKE '"
					+ name + "' ORDER BY 1";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public void addPayment(Payment obj)
	{
		try
		{
			statement = db.createStatement();
			String sql = "INSERT INTO payments(amount,purchase_transaction_id,notes,date,approved_by,received_by,prepared_by,payments_type,credit_memo_id,approved_date,received_date,prepared_date) VALUES('"
					+ obj.getAmount()
					+ "','"
					+ obj.getPurchase_transaction_id()
					+ "','"
					+ obj.getNotes()
					+ "','"
					+ obj.getDate()
					+ "','"
					+ obj.getApproved_by()
					+ "','"
					+ obj.getReceived_by()
					+ "','"
					+ obj.getPrepared_by()
					+ "','"
					+ obj.getPayment_type()
					+ "','"
					+ obj.getCredit_memo_id()
					+ "',"
					+ obj.getApproved_date()
					+ ","
					+ obj.getReceived_date()
					+ "," + obj.getPrepared_date() + ")";
			statement.executeUpdate(sql);
			System.out.println(sql);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ResultSet getSupplier()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name from company WHERE type='Supplier' ORDER BY 1";
			rs = statement.executeQuery(sql);
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

	public void updateCurrentBalance(String pt, float currentbalance)
	{
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE purchasetransaction SET current_balance='"
					+ currentbalance + "' WHERE purchase_transaction_id='" + pt
					+ "'";
			statement.executeUpdate(sql);
			System.out.println(sql);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void changeStatus(String pt)
	{
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE purchasetransaction SET status='Closed' WHERE purchase_transaction_id='"
					+ pt + "'";
			statement.executeUpdate(sql);
			System.out.println(sql);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ResultSet viewPayment(String id)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT payment_id,date,amount,payments_type,credit_memo_id from payments where purchase_transaction_id='"
					+ id + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public Payment getOtherDetails(String id)
	{
		ResultSet rs = null;
		Payment pay = new Payment();
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * from payments where payment_id='" + id + "'";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				pay.setApproved_by(rs.getString("approved_by"));
				pay.setApproved_date(rs.getString("approved_date"));
				pay.setPrepared_by(rs.getString("prepared_by"));
				pay.setPrepared_date(rs.getString("prepared_date"));
				pay.setReceived_by(rs.getString("received_by"));
				pay.setReceived_date(rs.getString("received_date"));
				pay.setNotes(rs.getString("notes"));

			}
		} catch (Exception e)
		{
			e.getMessage();
		}
		return pay;
	}

	public PurchaseTransaction getPTDetails(String id)
	{
		ResultSet rs = null;
		PurchaseTransaction pt = new PurchaseTransaction();
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name,original_amount,current_balance from purchasetransaction,company where purchase_transaction_id='"
					+ id
					+ "' AND purchasetransaction.company_id=company.company_id";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				pt.setOriginal_amount(rs.getFloat("original_amount"));
				pt.setCurrent_balance(rs.getFloat("current_balance"));
				pt.setPo_num(rs.getString("name"));
			}
		} catch (Exception e)
		{
			e.getMessage();
		}
		return pt;
	}
}

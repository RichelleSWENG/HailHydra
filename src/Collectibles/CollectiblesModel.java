package Collectibles;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class CollectiblesModel
{
	private int itemCount = 0;
	protected Connection db;
	protected Statement statement;

	public CollectiblesModel(DBConnection db)
	{
		this.db = db.getConnection();
	}
        
	public ResultSet getMaxYear()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT MAX(YEAR(date)) FROM salesinvoice UNION ALL SELECT MAX(YEAR(date)) FROM acknowledgementreceipt order by 1 desc";
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
			String sql = "SELECT MIN(YEAR(date)) FROM salesinvoice UNION ALL SELECT MIN(YEAR(date)) FROM acknowledgementreceipt order by 1";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ResultSet getCollectibles()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name, date,salesinvoice.type,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id UNION ALL SELECT name,date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id ORDER BY 1";
			rs = statement.executeQuery(sql);
			rs.last(); // Get Item Count
			itemCount = rs.getRow();
			rs.beforeFirst();
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ResultSet getCollectiblesbyDate(String startDate, String endDate)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name, date,salesinvoice.type,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND date BETWEEN '"
					+ startDate
					+ "' AND '"
					+ endDate
					+ "' UNION ALL SELECT name,date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND date BETWEEN '"
					+ startDate + "' AND '" + endDate + "' ORDER BY 1";
			rs = statement.executeQuery(sql);
			rs.last(); // Get Item Count
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

	public ResultSet searchCollectibles(String field, String filter,
			String startDate, String endDate)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "";
			if (filter.equalsIgnoreCase("active"))
			{
				sql = "SELECT name, date,salesinvoice.type,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.status LIKE 'Open' AND salesinvoice.date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND name LIKE '%"
						+ field
						+ "%' UNION ALL SELECT name,date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.status LIKE 'Open' AND acknowledgementreceipt.date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND name LIKE '%" + field + "%' ORDER BY 1";
			} else if (filter.equalsIgnoreCase("closed"))
			{
				sql = "SELECT name, date,salesinvoice.type,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.status LIKE 'Closed' AND salesinvoice.date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "'AND name LIKE '%"
						+ field
						+ "%' UNION ALL SELECT name,date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.status LIKE 'Closed' AND acknowledgementreceipt.date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND name LIKE '%" + field + "%' ORDER BY 1";
			} else if (filter.equalsIgnoreCase("name"))
			{
				sql = "SELECT name, date,salesinvoice.type,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "'AND name LIKE '%"
						+ field
						+ "%' UNION ALL SELECT name,date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND name LIKE '%" + field + "%' ORDER BY 1";
			}
			rs = statement.executeQuery(sql);
			rs.last(); // Get Item Count
			itemCount = rs.getRow();
			rs.beforeFirst();
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	ResultSet getAllActiveCollectibles(String startDate, String endDate)
	{

		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name, date,salesinvoice.type,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.status LIKE 'Open' AND salesinvoice.date BETWEEN '"
					+ startDate
					+ "' AND '"
					+ endDate
					+ "' UNION ALL SELECT name,date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.status LIKE 'Open' AND acknowledgementreceipt.date BETWEEN '"
					+ startDate + "' AND '" + endDate + "' ORDER BY 1";
			rs = statement.executeQuery(sql);
			rs.last(); // Get Item Count
			itemCount = rs.getRow();
			rs.beforeFirst();
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	ResultSet getAllClosedCollectibles(String startDate, String endDate)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name, date,salesinvoice.type,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.status LIKE 'Closed' AND salesinvoice.date BETWEEN '"
					+ startDate
					+ "' AND '"
					+ endDate
					+ "' UNION ALL SELECT name,date,acknowledgementreceipt.type,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.status LIKE 'Closed' AND acknowledgementreceipt.date BETWEEN '"
					+ startDate + "' AND '" + endDate + "' ORDER BY 1";
			rs = statement.executeQuery(sql);
			rs.last(); // Get Item Count
			itemCount = rs.getRow();
			rs.beforeFirst();
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

}

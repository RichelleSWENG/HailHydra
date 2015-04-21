/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Janine
 */
public class ReportModel
{
	protected Connection db;
	protected Statement statement;
	private int itemCount = 0;

	public ReportModel(DBConnection db)
	{
		this.db = db.getConnection();
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

	public ResultSet OrderReport()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT part_num, description,stock_minimum, quantity_functional,last_cost,rack_location FROM item WHERE quantity_functional<=stock_minimum AND status!=0 ORDER BY 1";
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

	public ResultSet searchOrderReport(String field, String filter)
	{
		ResultSet rs = null;
		String sql = "";
		try
		{
			statement = db.createStatement();
			if (filter.equalsIgnoreCase("part number"))
				sql = "SELECT part_num, description,stock_minimum, quantity_functional,last_cost,rack_location FROM item WHERE quantity_functional<=stock_minimum AND part_num LIKE '%"
						+ field + "%' AND status!=0 ORDER BY 1";
			else if (filter.equalsIgnoreCase("description"))
				sql = "SELECT part_num, description,stock_minimum, quantity_functional,last_cost,rack_location FROM item WHERE quantity_functional<=stock_minimum AND description LIKE '%"
						+ field + "%' AND status!=0 ORDER BY 1";
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

	public ResultSet CreditReport()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name,credit_limit,(SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id) + (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM salesinvoice WHERE salesinvoice.company_id=company.company_id) AS c FROM company,systeminfo WHERE type LIKE '%customer' AND credit_limit>0 AND (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id) + (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM salesinvoice WHERE salesinvoice.company_id=company.company_id)>=credit_limit*(credit_alert/100) AND company.status='Active' ORDER BY 1";
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

	public ResultSet searchCreditReport(String name)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name,credit_limit, (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id) + (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM salesinvoice WHERE salesinvoice.company_id=company.company_id) FROM company,systeminfo WHERE type LIKE '%customer' AND name LIKE '%"
					+ name
					+ "%' AND credit_limit>0 AND (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id) + (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM salesinvoice WHERE salesinvoice.company_id=company.company_id)>=credit_limit*(credit_alert/100) AND company.status='Active' ORDER BY 1";
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

	public ResultSet TermsReport()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice,systeminfo WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt,systeminfo WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <terms_report AND terms>0 AND company.status='Active' ORDER BY 1";
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

	public ResultSet getNearTerms()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice,systeminfo WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt,systeminfo WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND terms>0 AND company.status='Active' ORDER BY 1";
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

	public ResultSet getExceededTerms()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND terms>0 AND company.status='Active' ORDER BY 1";
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

	public ResultSet SearchExceeded(String filter, String field)
	{
		ResultSet rs = null;
		String sql = "";
		try
		{
			statement = db.createStatement();
			if (filter.equalsIgnoreCase("customer"))
				sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND company.name LIKE '%"
						+ field
						+ "%' AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND company.name LIKE '%"
						+ field + "%' AND terms>0 AND company.status='Active' ORDEY BY 1";
			else if (filter.equalsIgnoreCase("receipt number"))
				sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND sales_invoice_id LIKE '%"
						+ field
						+ "%' AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND acknowledgement_receipt_id LIKE '%"
						+ field + "%' AND terms>0 AND company.status='Active' ORDER BY 1";
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

	public ResultSet SearchNear(String filter, String field)
	{
		ResultSet rs = null;
		String sql = "";
		try
		{
			statement = db.createStatement();
			if (filter.equalsIgnoreCase("customer"))
				sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice,systeminfo WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND company.name LIKE '%"
						+ field
						+ "%' AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt,systeminfo WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND company.name LIKE '%"
						+ field + "%' AND terms>0 AND company.status='Active' ORDER BY 1";
			else if (filter.equalsIgnoreCase("receipt number"))
				sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice,systeminfo WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND sales_invoice_id LIKE '%"
						+ field
						+ "%' AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt,systeminfo WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND acknowledgement_receipt_id LIKE '%"
						+ field + "%' AND terms>0 AND company.status='Active' ORDER BY 1";
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

	public ResultSet SearchAll(String filter, String field)
	{
		ResultSet rs = null;
		String sql = "";
		try
		{
			statement = db.createStatement();
			if (filter.equalsIgnoreCase("customer"))
				sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice,systeminfo WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND company.name LIKE '%"
						+ field
						+ "%' AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt,systeminfo WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND company.name LIKE '%"
						+ field + "%' AND terms>0 AND company.status='Active' ORDER BY 1";
			else if (filter.equalsIgnoreCase("receipt number"))
				sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice,systeminfo WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND sales_invoice_id LIKE '%"
						+ field
						+ "%' AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt,systeminfo WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND acknowledgement_receipt_id LIKE '%"
						+ field + "%' AND terms>0 AND company.status='Active' ORDER BY 1";
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

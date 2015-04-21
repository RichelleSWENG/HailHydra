package Login;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class LoginModel
{
	private Connection db;
	private Statement statement;
	private ArrayList<Notification> notifs;

	public LoginModel(DBConnection db)
	{
		this.db = db.getConnection();
	}

	public ResultSet checkUsername(String username, String password)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT type FROM account WHERE user_name='"
					+ username + "' AND password='" + password + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ArrayList<Notification> getNotifs()
	{
		Statement stm;
		ResultSet rs = null, rs2 = null;
		String sql, sql2;
		notifs = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("0.00");
		try
		{
			// Order Notifications
			statement = db.createStatement();
			sql = "SELECT part_num, stock_minimum, quantity_functional FROM item WHERE quantity_functional<=stock_minimum AND status!=0";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				// add to list of notifs
				notifs.add(new Notification("Stock Minimum Reached",
						"Item Part No. " + rs.getString("part_num")
								+ /*
								 * " (quantity: " +
								 * rs.getInt("quantity_functional") + ")
								 */" has reached stock minimimum."/*
																 * (" + rs.getInt("
																 * stock_minimum
																 * ") + ")."
																 */));
			}

			// Credit Limit Notifications
                        statement = db.createStatement();
			sql = "SELECT name,credit_limit, (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id) + (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM salesinvoice WHERE salesinvoice.company_id=company.company_id) AS c FROM company,systeminfo WHERE type LIKE '%customer' AND credit_limit>0 AND (credit_limit-(SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id) + (SELECT CASE WHEN SUM(current_balance) IS NULL THEN '0' ELSE SUM(current_balance) END FROM salesinvoice WHERE salesinvoice.company_id=company.company_id))<=credit_limit*(credit_alert/100) AND company.status='Active'";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
					// add to list of notifs
					notifs.add(new Notification("Credit Limit", rs
							.getString("name") +" is nearing credit limit."));
																				
			}

			// Exceeded Terms Notifications
			sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND terms>0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <0 AND terms>0 AND company.status='Active'";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				notifs.add(new Notification("Exceeded Terms", rs
						.getString("name") + " has exceeded terms."));
			}

			// Near Terms Notifications

			sql = "SELECT company.name,sales_invoice_id,salesinvoice.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,salesinvoice,systeminfo WHERE company.company_id=salesinvoice.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND company.status='Active' UNION ALL SELECT company.name,acknowledgement_receipt_id,acknowledgementreceipt.type,terms,DATE_ADD(date, INTERVAL terms DAY),current_balance FROM company,acknowledgementreceipt,systeminfo WHERE company.company_id=acknowledgementreceipt.company_id AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) <=terms_report AND DATEDIFF(DATE_ADD(date, INTERVAL terms DAY),now()) >=0 AND terms>0 AND company.status='Active'";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				// add to list of notifs
				notifs.add(new Notification("Near Terms", rs.getString("name")
						+ "is nearing its terms." /*
													 * : <br> SI/AR ID:
													 * " + rs.getString("
													 * id") + " Dated:
													 * " + rs.getString("
													 * date") + "<br> Balance to
													 * Pay:
													 * " + df.format(rs.getFloat("
													 * current_balance"))
													 */));
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return notifs;
	}

}

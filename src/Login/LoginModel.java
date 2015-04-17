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
			sql = "SELECT part_num, stock_minimum, quantity_functional FROM item WHERE quantity_functional<=stock_minimum";
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
			sql = "SELECT credit_alert FROM systeminfo WHERE system_info_id='1'";
			rs = statement.executeQuery(sql);
			rs.next();
			float creditLimitP = rs.getFloat("credit_alert");

			sql = "SELECT company_id, SUM(current_balance) AS total_bal\n"
					+ "FROM (\n" + "SELECT s.company_id, s.current_balance \n"
					+ "FROM company c, salesinvoice s \n"
					+ "WHERE c.company_id=s.company_id \n"
					+ "UNION ALL SELECT a.company_id, a.current_balance \n"
					+ "FROM company c ,acknowledgementreceipt a \n"
					+ "WHERE c.company_id=a.company_id) AS table1\n"
					+ "GROUP BY company_id";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				stm = db.createStatement();
				sql2 = "SELECT name, credit_limit FROM company WHERE company_id = '"
						+ rs.getString("company_id") + "'";
				rs2 = stm.executeQuery(sql2);
				rs2.next();
				float companyLimit = rs2.getFloat("credit_limit");
				if (companyLimit != 0
						&& companyLimit * creditLimitP / 100 <= rs
								.getFloat("total_bal"))
				{
					// add to list of notifs
					notifs.add(new Notification("Credit Limit", rs2
							.getString("name") + /*
												 * " (ID: " +
												 * rs.getString("company_id") +
												 * ")
												 */" is nearing credit limit. "/*
																				 * "<br>Total Balance: "
																				 * +
																				 * df
																				 * .
																				 * format
																				 * (
																				 * rs
																				 * .
																				 * getFloat
																				 * (
																				 * "total_bal"
																				 * )
																				 * )
																				 * +
																				 * " Credit Limit: "
																				 * +
																				 * df
																				 * .
																				 * format
																				 * (
																				 * rs2
																				 * .
																				 * getFloat
																				 * (
																				 * "credit_limit"
																				 * )
																				 * )
																				 */));
				}
			}

			// Exceeded Terms Notifications
			sql = "SELECT company_id, name, id, date, terms, current_balance\n"
					+ "FROM\n"
					+ "	(SELECT s.company_id, c.name, s.sales_invoice_id AS id, s.date , c.terms, s.current_balance\n"
					+ "	FROM company c, salesinvoice s\n"
					+ "	WHERE c.company_id=s.company_id AND s.status = 'Open'\n"
					+ "	UNION ALL SELECT a.company_id, c.name, a.acknowledgement_receipt_id AS id, a.date, c.terms, a.current_balance\n"
					+ "	FROM company c ,acknowledgementreceipt a \n"
					+ "	WHERE c.company_id=a.company_id AND a.status = 'Open') AS t1\n"
					+ "WHERE DATE_ADD(date, INTERVAL terms day) <= NOW() AND terms > 0";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				notifs.add(new Notification("Exceeded Terms", rs2
						.getString("name") + /*
											 * " (ID: " +
											 * rs.getString("company_id") + ")
											 */" has exceeded terms."/*
																	 * : <br>
																	 * SI/AR ID:
																	 * " + rs.getString("
																	 * id") + "
																	 * Dated:
																	 * " + rs.getString("
																	 * date
																	 * ") + "
																	 * <br>
																	 * Balance
																	 * to Pay:
																	 * " + df.format(rs.getFloat("
																	 * current_balance
																	 * "))
																	 */));
			}

			// Near Terms Notifications

			sql = "SELECT terms_report FROM systeminfo WHERE system_info_id='1'";
			rs = statement.executeQuery(sql);
			rs.next();
			int daysLimit = rs.getInt("terms_report");

			sql = "SELECT company_id, name, id, date, terms, current_balance\n"
					+ "FROM\n"
					+ "	(SELECT s.company_id, c.name, s.sales_invoice_id AS id, s.date , c.terms, s.current_balance\n"
					+ "	FROM company c, salesinvoice s\n"
					+ "	WHERE c.company_id=s.company_id AND s.status = 'Open'\n"
					+ "	UNION ALL SELECT a.company_id, c.name, a.acknowledgement_receipt_id AS id, a.date, c.terms, a.current_balance\n"
					+ "	FROM company c ,acknowledgementreceipt a \n"
					+ "	WHERE c.company_id=a.company_id AND a.status = 'Open') AS t1\n"
					+ "WHERE DATE_ADD(date, INTERVAL terms +"
					+ daysLimit
					+ " day) <= NOW() AND terms > 0 AND NOT DATE_ADD(date, INTERVAL terms day) <= NOW()";
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				// add to list of notifs
				notifs.add(new Notification("Near Terms", rs2.getString("name")
						+ " (ID: " + rs.getString("company_id")
						+ ") is nearing its terms." /*
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

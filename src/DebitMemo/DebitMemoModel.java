package DebitMemo;

import Classes.Company;
import Classes.Item;
import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class DebitMemoModel
{
	protected Connection db;
	protected Statement statement;
	private int itemCount = 0;
	private ArrayList<Company> customers;
	private ArrayList<DMLineItem> items;
	private DMLineItemModel dmLineItemModel;
	private DebitMemo mmo;

	public DebitMemoModel(DBConnection db)
	{
		this.db = db.getConnection();
		customers = new ArrayList<>();
		items = new ArrayList<>();
		dmLineItemModel = new DMLineItemModel(db);

	}

	public ResultSet getAllDetail()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT date, debitmemo.debit_memo_id, company.name, part_num,quantity,line_total FROM company,dmlineitem,debitmemo WHERE debitmemo.company_id=company.company_id AND debitmemo.debit_memo_id=dmlineitem.debit_memo_id";
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

	public ResultSet getAllDetailbyDate(String startDate, String endDate)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT date, debitmemo.debit_memo_id, company.name, part_num,quantity,line_total FROM company,dmlineitem,debitmemo WHERE debitmemo.company_id=company.company_id AND debitmemo.debit_memo_id=dmlineitem.debit_memo_id AND date BETWEEN '"
					+ startDate + "' AND '" + endDate + "'";
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

	public ResultSet searchDetail(String field, String filter,
			String startDate, String endDate)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "";
			if (filter.equalsIgnoreCase("name"))
			{
				sql = "SELECT date, debitmemo.debit_memo_id, company.name, part_num,quantity,line_total FROM company,dmlineitem,debitmemo WHERE debitmemo.company_id=company.company_id AND debitmemo.debit_memo_id=dmlineitem.debit_memo_id AND date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND company.name LIKE '%" + field + "%'";
			} else if (filter.equalsIgnoreCase("debit memo number"))
			{
				sql = "SELECT date, debitmemo.debit_memo_id, company.name, part_num,quantity,line_total FROM company,dmlineitem,debitmemo WHERE debitmemo.company_id=company.company_id AND debitmemo.debit_memo_id=dmlineitem.debit_memo_id AND date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND debitmemo.debit_memo_id LIKE '%"
						+ field
						+ "%'";
			} else if (filter.equalsIgnoreCase("part number"))
			{
				sql = "SELECT date, debitmemo.debit_memo_id, company.name, part_num,quantity,line_total FROM company,dmlineitem,debitmemo WHERE debitmemo.company_id=company.company_id AND debitmemo.debit_memo_id=dmlineitem.debit_memo_id AND date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND part_num LIKE '%" + field + "%'";
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

	public void addDetail(DebitMemo obj)
	{
		DebitMemo dm = obj;
		try
		{

			statement = db.createStatement();
			String sql = "INSERT INTO debitmemo(debit_memo_id, date,company_id,total_amount, receipt_type, number, approved_by, received_by,approved_date,received_date, notes, status, type) VALUES('"
					+ dm.getDebit_memo_id()
					+ "','"
					+ dm.getDate()
					+ "','"
					+ dm.getCompany_id()
					+ "','"
					+ dm.getTotal_amount()
					+ "','"
					+ dm.getReceipt_type()
					+ "','"
					+ dm.getReceipt_number()
					+ "','"
					+ dm.getApproved_by()
					+ "','"
					+ dm.getReceived_by()
					+ "','"
					+ dm.getApproved_date()
					+ "','"
					+ dm.getReceived_date()
					+ "','"
					+ dm.getNotes()
					+ "','"
					+ dm.getStatus() + "','" + dm.getType() + "')";
			System.out.println(sql);
			statement.executeUpdate(sql);
			int i;
			for (i = 0; i < dm.getItems().size(); i++)
			{
				dmLineItemModel.addDetail(dm.getItems().get(i));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void editDetail(Object obj)
	{
		DebitMemo dm = (DebitMemo) obj;
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE debitmemo SET debitmemo.company_id='"
					+ dm.getCompany_id() + "',date='" + dm.getDate()
					+ "',total_amount='" + dm.getTotal_amount()
					+ "',receipt_type='" + dm.getReceipt_type() + "',number='"
					+ dm.getReceipt_number() + "',approved_by='"
					+ dm.getApproved_by() + "',received_by='"
					+ dm.getReceived_by() + "',approved_date='"
					+ dm.getApproved_date() + "', notes='" + dm.getNotes()
					+ "',status='" + dm.getStatus() + "',type='" + dm.getType()
					+ "' WHERE debit_memo_id LIKE '" + dm.getDebit_memo_id()
					+ "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

	}

	public void deleteDetail(String ID)
	{
		try
		{
			statement = db.createStatement();
			String sql = "DELETE FROM debitmemo WHERE debit_memo_id='" + ID
					+ "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

	}

	public TableModel myModel(ResultSet rs)
	{
		TableModel model = DbUtils.resultSetToTableModel(rs);
		return model;
	}

	public ResultSet getMinYear()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT MIN(YEAR(date)) FROM debitmemo";
			rs = statement.executeQuery(sql);

		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ResultSet getMaxYear()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT MAX(YEAR(date)) FROM debitmemo";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public int getItemcount()
	{
		return this.itemCount;
	}

	public ArrayList<Company> getCustomers()
	{
		customers = new ArrayList<>();
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM company WHERE type LIKE '%customer%'";
			rs = statement.executeQuery(sql);
			Company tempCustomer;
			while (rs.next())
			{
				tempCustomer = new Company();
				tempCustomer.setId(rs.getInt("company_id"));
				tempCustomer.setName(rs.getString("name"));
				tempCustomer.setAddressLoc(rs.getString("address_location"));
				tempCustomer.setAddressCity(rs.getString("address_city"));
				tempCustomer.setAddressCountry(rs.getString("address_country"));
				tempCustomer.setPostalCode(rs.getString("address_postal_code"));
				tempCustomer.setPhone1(rs.getString("phone1"));
				tempCustomer.setPhone2(rs.getString("phone2"));
				tempCustomer.setPhone3(rs.getString("phone3"));
				tempCustomer.setFaxNum(rs.getString("fax_num"));
				tempCustomer.setWebsite(rs.getString("website"));
				tempCustomer.setEmail(rs.getString("email"));
				tempCustomer.setContactPerson(rs.getString("contact_person"));
				tempCustomer.setStatus(rs.getString("status"));
				tempCustomer.setCreditLimit(rs.getFloat("credit_limit"));
				tempCustomer.setTerms(rs.getInt("terms"));
				tempCustomer.setType(rs.getString("type"));
				customers.add(tempCustomer);
			}

		} catch (Exception e)
		{
			e.getMessage();
		}
		return customers;
	}

	public Company getCustomer(int index)
	{
		return customers.get(index);
	}

	public ArrayList<DMLineItem> getItems(String customerType)
	{
		items = new ArrayList<>();
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM item";
			rs = statement.executeQuery(sql);
			DMLineItem tempItem;
			while (rs.next())
			{
				tempItem = new DMLineItem();
				tempItem.setPartNum(rs.getString("part_num"));
				tempItem.setDescription(rs.getString("description"));

				if (customerType.equals("Walk-in Customer"))
				{
					tempItem.setPrice(rs.getFloat("walk_in_price"));
				}
				if (customerType.equals("Retail Customer"))
				{
					tempItem.setPrice(rs.getFloat("traders_price"));
				}
				if (customerType.equals("Sister Company Customer"))
				{
					tempItem.setPrice(rs.getFloat("sister_company_price"));
				}
				items.add(tempItem);

				tempItem.setMinimum(rs.getInt("stock_minimum"));
				tempItem.setQuantityFunc(rs.getInt("quantity_functional"));
			}

		} catch (Exception e)
		{
			e.getMessage();
		}
		return items;
	}

	public Item getItem(int index)
	{
		return items.get(index);
	}

	public int getAvailQuantity(int index)
	{
		return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
	}

	public DebitMemo getDM(String ID)
	{
		ArrayList<DMLineItem> stuff;
		// DebitMemo mmo = null;
		int company_id = -1;

		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM debitmemo WHERE debit_memo_id = '" + ID
					+ "'";
			System.out.println(sql);
			rs = statement.executeQuery(sql);

			if (rs.next())
			{
				mmo = new DebitMemo();
				mmo.setDebit_memo_id(rs.getString("debit_memo_id"));
				mmo.setDate(rs.getString("date"));
				mmo.setTotal_amount(rs.getFloat("total_amount"));
				mmo.setReceipt_type(rs.getString("receipt_type"));
				mmo.setReceipt_number(rs.getString("number"));
				mmo.setApproved_by(rs.getString("approved_by"));
				mmo.setReceived_by(rs.getString("received_by"));
				mmo.setApproved_date(rs.getString("approved_date"));
				mmo.setReceived_date(rs.getString("received_date"));
				mmo.setNotes(rs.getString("notes"));
				mmo.setStatus(rs.getInt("status"));
				mmo.setType(rs.getString("type"));
				mmo.setCompany_id(rs.getInt("company_id"));
			}

			if (mmo != null)
			{
				String query = "SELECT * FROM dmlineitem WHERE debit_memo_id = '"
						+ mmo.getDebit_memo_id() + "'";
				statement = db.createStatement();
				rs = statement.executeQuery(query);
				while (rs.next())
				{
					mmo.addItem(new DMLineItem(mmo.getDebit_memo_id(), rs
							.getInt("quantity"), rs.getString("part_num"), rs
							.getFloat("unit_price"), rs.getFloat("line_total")));
				}

				query = "SELECT * FROM company WHERE company_id = '"
						+ company_id + "'";
				statement = db.createStatement();
				rs = statement.executeQuery(query);
				Company tempCustomer = new Company();
				if (rs.next())
				{
					tempCustomer.setId(rs.getInt("company_id"));
					tempCustomer.setName(rs.getString("name"));
					tempCustomer
							.setAddressLoc(rs.getString("address_location"));
					tempCustomer.setAddressCity(rs.getString("address_city"));
					tempCustomer.setAddressCountry(rs
							.getString("address_country"));
					tempCustomer.setPostalCode(rs
							.getString("address_postal_code"));
					tempCustomer.setPhone1(rs.getString("phone1"));
					tempCustomer.setPhone2(rs.getString("phone2"));
					tempCustomer.setPhone3(rs.getString("phone3"));
					tempCustomer.setFaxNum(rs.getString("fax_num"));
					tempCustomer.setWebsite(rs.getString("website"));
					tempCustomer.setEmail(rs.getString("email"));
					tempCustomer.setContactPerson(rs
							.getString("contact_person"));
					tempCustomer.setStatus(rs.getString("status"));
					tempCustomer.setCreditLimit(rs.getFloat("credit_limit"));
					tempCustomer.setTerms(rs.getInt("terms"));
					tempCustomer.setType(rs.getString("type"));
				}
				mmo.setCompany(tempCustomer);
			}
		} catch (Exception e)
		{
			e.getMessage();
		}

		return mmo;
	}

	public ArrayList<String> getReceiptNumbersAR(String ID)
	{

		ResultSet rs = null;
		ArrayList<String> receiptNumbersAR = new ArrayList<String>();
		try
		{
			statement = db.createStatement();
			String sql = "SELECT acknowledgement_receipt_id FROM acknowledgementreceipt WHERE company_id = '"
					+ ID + "'";
			rs = statement.executeQuery(sql);

			while (rs.next())
			{
				receiptNumbersAR
						.add(rs.getString("acknowledgement_receipt_id"));
			}

		} catch (Exception e)
		{
			e.getMessage();
		}
		return receiptNumbersAR;

	}

	public ArrayList<String> getReceiptNumbersSI(String ID)
	{

		ResultSet rs = null;
		ArrayList<String> receiptNumbersSI = new ArrayList<String>();
		try
		{
			statement = db.createStatement();
			String sql = "SELECT sales_invoice_id FROM salesinvoice WHERE company_id = '"
					+ ID + "'";
			rs = statement.executeQuery(sql);

			while (rs.next())
			{
				receiptNumbersSI.add(rs.getString("sales_invoice_id"));
			}

		} catch (Exception e)
		{
			e.getMessage();
		}
		return receiptNumbersSI;

	}

	public String getLastDMID()
	{
		ResultSet rs = null;
		String DMid = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT debit_memo_id FROM debitmemo ORDER BY debit_memo_id DESC LIMIT 1;";
			rs = statement.executeQuery(sql);

			while (rs.next())
			{
				String tempID = rs.getString("debit_memo_id");
				DMid = tempID;
			}
		} catch (Exception e)
		{
			e.getMessage();
		}
		if (DMid == null)
			return "null";
		else
			return DMid;
	}

	public void updateFromDefec(String quantity, String partNum, int status)
	{
		int Qfunc = getQuantityFunc(partNum);
		int Qdef = getQuantityDef(partNum);
		if (status == 1)
			Qdef = Qdef + Integer.parseInt(quantity);
		else if (status == 0)
			Qfunc = Qfunc + Integer.parseInt(quantity);

		try
		{

			statement = db.createStatement();
			String sql = "UPDATE item SET quantity_functional = '" + Qfunc
					+ "', quantity_defective = '" + Qdef
					+ "' WHERE part_num ='" + partNum + "'";
			statement.executeUpdate(sql);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updateFromType(String quantity, String partNum, String type)
	{
		int Qfunc = getQuantityFunc(partNum);
		int Qdef = getQuantityDef(partNum);
		if (type.equals("Replacement"))
			Qfunc = Qfunc - Integer.parseInt(quantity);
		try
		{

			statement = db.createStatement();
			String sql = "UPDATE item SET quantity_functional = '" + Qfunc
					+ "', quantity_defective = '" + Qdef
					+ "' WHERE part_num ='" + partNum + "'";
			System.out.println(sql);
			statement.executeUpdate(sql);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getQuantityFunc(String partNum)
	{

		ResultSet rs = null;
		String quantity = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT quantity_functional FROM item WHERE part_num='"
					+ partNum + "'";
			rs = statement.executeQuery(sql);
			String temp;

			while (rs.next())
			{
				temp = rs.getString("quantity_functional");
				quantity = temp;
			}
		} catch (Exception e)
		{
			e.getMessage();
		}
		return Integer.parseInt(quantity);

	}

	public int getQuantityDef(String partNum)
	{
		ResultSet rs = null;
		String quantity = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT quantity_defective FROM item WHERE part_num='"
					+ partNum + "'";
			rs = statement.executeQuery(sql);
			String temp;
			while (rs.next())
			{
				temp = rs.getString("quantity_defective");
				quantity = temp;
			}
		} catch (Exception e)
		{
			e.getMessage();
		}
		return Integer.parseInt(quantity);
	}

	String getCustomerbyID(int company_id) throws SQLException
	{
		ResultSet rs = null;
		String name = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT name FROM company WHERE company_id = '"
					+ company_id + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		while (rs.next())
		{
			String tempName = rs.getString("name");
			name = tempName;
		}
		return name;
	}
}

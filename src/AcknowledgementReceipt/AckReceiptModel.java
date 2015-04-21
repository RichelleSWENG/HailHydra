package AcknowledgementReceipt;

import Classes.Company;
import Classes.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Database.DBConnection;
import java.sql.SQLException;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class AckReceiptModel
{

	protected Connection db;
	protected Statement statement;

	private int itemCount = 0;
	private ArrayList<Company> customers;
	private ArrayList<ARLineItem> items;
	private ARLineItemModel arLineItemModel;

	public AckReceiptModel(DBConnection db)
	{
		this.db = db.getConnection();
		customers = new ArrayList<>();
		items = new ArrayList<>();
		arLineItemModel = new ARLineItemModel(db);
	}

	public ResultSet getDetail(String ID)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT (SELECT name FROM company WHERE company.company_id=acknowledgementreceipt.company_id),acknowledgementreceipt.address,acknowledgement_receipt_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status WHERE acknowledgement_receipt_id LIKE '"
					+ ID + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ResultSet getAllDetail()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT company.name,date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id";
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
			String sql = "SELECT company.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id AND date BETWEEN '"
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
				sql = "SELECT company.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id AND company.name LIKE '%"
						+ field
						+ "%' AND date BETWEEN '"
						+ startDate
						+ "' AND '" + endDate + "'";
			} else if (filter.equalsIgnoreCase("acknowledgement number"))
			{
				sql = "SELECT company.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id AND acknowledgement_receipt_id LIKE '%"
						+ field
						+ "%' AND date BETWEEN '"
						+ startDate
						+ "' AND '" + endDate + "'";
			} else if (filter.equalsIgnoreCase("part number"))
			{
				sql = "SELECT company.name, date,acknowledgementreceipt.acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt,arlineitem WHERE acknowledgementreceipt.company_id=company.company_id AND arlineitem.acknowledgement_receipt_id=acknowledgementreceipt.acknowledgement_receipt_id AND arlineitem.part_num LIKE '%"
						+ field
						+ "%' AND date BETWEEN '"
						+ startDate
						+ "' AND '" + endDate + "'";
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

	public void addDetail(AcknowledgementReceipt obj)
	{
		AcknowledgementReceipt ar = obj;
		try
		{

			statement = db.createStatement();
			String sql = "INSERT INTO acknowledgementreceipt(acknowledgement_receipt_id,company_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status) VALUES('"
					+ ar.getAcknowledgement_receipt_id()
					+ "','"
					+ ar.getCompany_id()
					+ "','"
					+ ar.getDate()
					+ "','"
					+ ar.getPo_num()
					+ "','"
					+ ar.getDelivery_receipt_num()
					+ "','"
					+ ar.getSales_person()
					+ "','"
					+ ar.getOrdered_by()
					+ "','"
					+ ar.getDelivered_by()
					+ "','"
					+ ar.getDelivery_notes()
					+ "','"
					+ ar.getDiscount()
					+ "','"
					+ ar.getOriginal_amount()
					+ "','"
					+ ar.getCurrent_balance()
					+ "','" + ar.getStatus() + "')";
			System.out.println(sql);
			statement.executeUpdate(sql);
			int i;
			for (i = 0; i < ar.getItems().size(); i++)
			{
				arLineItemModel.addDetail(ar.getItems().get(i));
			}
		} catch(SQLException e)
                {
                    //MYSQL_DUPLICATE_PK
                    if(e.getErrorCode() == 1062)
                    {
                        //duplicate primary key 
                    }
		}
	}

	public void editDetail(Object obj)
	{
		AcknowledgementReceipt ar = (AcknowledgementReceipt) obj;
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE acknowledgementreceipt SET acknowledgementreceipt.company_id='"
					+ ar.getCompany_id()
					+ "',date='"
					+ ar.getDate()
					+ "',po_num='"
					+ ar.getPo_num()
					+ "',delivery_receipt_num='"
					+ ar.getDelivery_receipt_num()
					+ "',sales_person='"
					+ ar.getSales_person()
					+ "',ordered_by='"
					+ ar.getOrdered_by()
					+ "',delivered_by='"
					+ ar.getDelivered_by()
					+ "',delivery_notes='"
					+ ar.getDelivery_notes()
					+ "',discount='"
					+ ar.getDiscount()
					+ "',original_amount='"
					+ ar.getOriginal_amount()
					+ "',current_balance='"
					+ ar.getCurrent_balance()
					+ "' WHERE acknowledgement_receipt_id LIKE '"
					+ ar.getAcknowledgement_receipt_id() + "'";
			statement.executeUpdate(sql);

			// Delete all items in PTLineItem
			statement = db.createStatement();
			sql = "DELETE FROM arlineitem WHERE acknowledgement_receipt_id = '"
					+ ar.getAcknowledgement_receipt_id() + "'";
			statement.executeUpdate(sql);
                        //System.out

			// Add new items
			int i;
			for (i = 0; i < ar.getItems().size(); i++)
			{
				arLineItemModel.addDetail(ar.getItems().get(i));
				// arLineItemModel.updateQuantity(ar.getItems().get(i).getPartNum(),
				// ar.getItems().get(i).getQuantity() + getAvailQuantity(i));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void deleteDetail(String ID)
	{
		try
		{
			statement = db.createStatement();
			String sql = "DELETE FROM acknowledgementreceipt WHERE acknowledgement_receipt_id='"
					+ ID + "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

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
			String sql = "SELECT * FROM company WHERE type LIKE '%customer%' ORDER BY name ASC";
			rs = statement.executeQuery(sql);
			Company tempCustomer;
			while (rs.next())
			{
				tempCustomer = new Company();
				tempCustomer.setStatus(rs.getString("status"));
				if (tempCustomer.getStatus().equals("Active"))
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
					tempCustomer.setCreditLimit(rs.getFloat("credit_limit"));
					tempCustomer.setTerms(rs.getInt("terms"));
					tempCustomer.setType(rs.getString("type"));
					customers.add(tempCustomer);
				}
			}

		} catch (Exception e)
		{
			e.getMessage();
		}
		return customers;
	}

	public ArrayList<ARLineItem> getItems(String customerType)
	{
		items = new ArrayList<>();
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM item";
			rs = statement.executeQuery(sql);
			ARLineItem tempItem;
			while (rs.next())
			{
				tempItem = new ARLineItem();
				tempItem.setStatus(rs.getInt("status"));
				if (tempItem.getStatus() == 1)
				{
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
			}

		} catch (Exception e)
		{
			e.getMessage();
		}
		return items;
	}

	public Company getCustomer(int index)
	{
		return customers.get(index);
	}

	public Item getItem(int index)
	{
		return items.get(index);
	}

	public int getAvailQuantity(int index)
	{
		return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
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
			String sql = "SELECT MIN(YEAR(date)) FROM acknowledgementreceipt";
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
			String sql = "SELECT MAX(YEAR(date)) FROM acknowledgementreceipt";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public AcknowledgementReceipt getAR(String ID)
	{
		ArrayList<ARLineItem> stuff;
		AcknowledgementReceipt rcpt = null;
		int company_id = -1;

		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM acknowledgementreceipt WHERE acknowledgement_receipt_id = '"
					+ ID + "'";
			rs = statement.executeQuery(sql);

			if (rs.next())
			{
				rcpt = new AcknowledgementReceipt();
				rcpt.setAcknowledgement_receipt_id(rs
						.getString("acknowledgement_receipt_id"));
				rcpt.setDate(rs.getString("date"));
				rcpt.setOriginal_amount(rs.getFloat("original_amount"));
				rcpt.setPo_num(rs.getString("po_num"));
				rcpt.setOrdered_by(rs.getString("ordered_by"));
				rcpt.setSales_person(rs.getString("sales_person"));
				rcpt.setDelivered_by(rs.getString("delivered_by"));
				rcpt.setDelivery_notes(rs.getString("delivery_notes"));
				rcpt.setDelivery_receipt_num(rs
						.getString("delivery_receipt_num"));
				rcpt.setDiscount(rs.getFloat("discount"));
				rcpt.setCurrent_balance(rs.getFloat("current_balance"));
				rcpt.setStatus(rs.getString("status"));
				company_id = rs.getInt("company_id");
			}

			if (rcpt != null)
			{
				String query = "SELECT * FROM arlineitem WHERE acknowledgement_receipt_id = '"
						+ rcpt.getAcknowledgement_receipt_id() + "'";
				statement = db.createStatement();
				rs = statement.executeQuery(query);
				while (rs.next())
				{
					rcpt.addItem(new ARLineItem(rcpt
							.getAcknowledgement_receipt_id(), rs
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
				rcpt.setCompany(tempCustomer);
			}
		} catch (Exception e)
		{
			e.getMessage();
		}

		return rcpt;
	}

}

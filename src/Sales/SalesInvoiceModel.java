package Sales;

import AcknowledgementReceipt.ARLineItemModel;
import Classes.Company;
import Classes.Item;
import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class SalesInvoiceModel
{
	protected Connection db;
	protected Statement statement;

	private int itemCount = 0;
	private ArrayList<Company> customers;
	private ArrayList<SILineItem> items;
	private SILineItemModel siLineItemModel;

	public SalesInvoiceModel(DBConnection db)
	{
		this.db = db.getConnection();
		customers = new ArrayList<>();
		items = new ArrayList<>();
		siLineItemModel = new SILineItemModel(db);
	}

	public ResultSet getDetail(String ID)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "";
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
			String sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id=salesinvoice.company_id";
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
			String sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.date BETWEEN '"
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
				sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id= salesinvoice.company_id AND company.name LIKE '%"
						+ field
						+ "%' AND salesinvoice.date BETWEEN '"
						+ startDate + "' AND '" + endDate + "'";
			} else if (filter.equalsIgnoreCase("sales invoice number"))
			{
				sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice WHERE company.company_id= salesinvoice.company_id AND salesinvoice.sales_invoice_id LIKE '%"
						+ field
						+ "%' AND salesinvoice.date BETWEEN '"
						+ startDate + "' AND '" + endDate + "'";
			} else if (filter.equalsIgnoreCase("part number"))
				sql = "SELECT company.name, salesinvoice.date, salesinvoice.sales_invoice_id, salesinvoice.original_amount, salesinvoice.current_balance FROM company, salesinvoice,silineitem WHERE company.company_id= salesinvoice.company_id AND silineitem.sales_invoice_id=salesinvoice.sales_invoice_id AND silineitem.part_num LIKE '%"
						+ field
						+ "%' AND salesinvoice.date BETWEEN '"
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

	public void addDetail(SalesInvoice obj)
	{

		SalesInvoice si = obj;
		try
		{

			statement = db.createStatement();
			String sql = "INSERT INTO salesinvoice(sales_invoice_id,company_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status,pwd_id_number_notes, vat) VALUES('"
					+ si.getSales_invoice_id()
					+ "','"
					+ si.getCompany_id()
					+ "','"
					+ si.getDate()
					+ "','"
					+ si.getPo_num()
					+ "','"
					+ si.getDelivery_receipt_num()
					+ "','"
					+ si.getSales_person()
					+ "','"
					+ si.getOrdered_by()
					+ "','"
					+ si.getDelivered_by()
					+ "','"
					+ si.getDelivery_notes()
					+ "','"
					+ si.getDiscount()
					+ "','"
					+ si.getOriginal_amount()
					+ "','"
					+ si.getCurrent_balance()
					+ "','"
					+ si.getStatus()
					+ "','"
					+ si.getPwd_id_number_notes() + "','" + si.getVat() + "')";
			System.out.println(sql);
			statement.executeUpdate(sql);
			int i;
			for (i = 0; i < si.getItems().size(); i++)
			{
				siLineItemModel.addDetail(si.getItems().get(i));
				siLineItemModel.updateQuantity(si.getItems().get(i)
						.getPartNum(), si.getItems().get(i).getQuantityFunc()
						- si.getItems().get(i).getQuantity());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void editDetail(SalesInvoice obj)
	{
		SalesInvoice si = obj;
		try
		{
			statement = db.createStatement();
			String sql = "UPDATE salesinvoice SET salesinvoice.company_id='"
					+ si.getCompany_id() + "',date='" + si.getDate()
					+ "',po_num='" + si.getPo_num()
					+ "',delivery_receipt_num='" + si.getDelivery_receipt_num()
					+ "',sales_person='" + si.getSales_person()
					+ "',ordered_by='" + si.getOrdered_by()
					+ "',delivered_by='" + si.getDelivered_by()
					+ "',delivery_notes='" + si.getDelivery_notes()
					+ "',discount='" + si.getDiscount() + "',original_amount='"
					+ si.getOriginal_amount() + "',current_balance='"
					+ si.getCurrent_balance()
					+ "' WHERE sales_invoice_id LIKE '"
					+ si.getSales_invoice_id() + "'";
			statement.executeUpdate(sql);

			// Delete all items in PTLineItem
			statement = db.createStatement();
			sql = "DELETE FROM silineitem WHERE sales_invoice_id = '"
					+ si.getSales_invoice_id() + "'";
			statement.executeUpdate(sql);

			// Add new items
			int i;
			for (i = 0; i < si.getItems().size(); i++)
			{
				siLineItemModel.addDetail(si.getItems().get(i));
				siLineItemModel.updateQuantity(si.getItems().get(i)
						.getPartNum(), si.getItems().get(i).getQuantity()
						+ getAvailQuantity(i));
			}
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
			String sql = "DELETE FROM salesinvoice WHERE sales_invoice_id='"
					+ ID + "'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

	}

	public ResultSet getMinYear()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT MIN(YEAR(date)) FROM salesinvoice";
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
			String sql = "SELECT MAX(YEAR(date)) FROM salesinvoice";
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

	public TableModel myModel(ResultSet rs)
	{
		TableModel model = DbUtils.resultSetToTableModel(rs);
		return model;
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

	public ArrayList<SILineItem> getItems(String customerType)
	{
		items = new ArrayList<>();
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM item";
			rs = statement.executeQuery(sql);
			SILineItem tempItem;
			while (rs.next())
			{
				tempItem = new SILineItem();
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

	public SILineItem getItem(int index)
	{
		return items.get(index);
	}

	public int getAvailQuantity(int index)
	{
		return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
	}

	public SalesInvoice getSI(String ID)
	{
		SalesInvoice si = null;
		int company_id = -1;

		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM salesinvoice WHERE sales_invoice_id = '"
					+ ID + "'";
			rs = statement.executeQuery(sql);

			if (rs.next())
			{
				si = new SalesInvoice();
				si.setSales_invoice_id(rs.getString("sales_invoice_id"));
				si.setDate(rs.getString("date"));
				si.setOriginal_amount(rs.getFloat("original_amount"));
				si.setPo_num(rs.getString("po_num"));
				si.setOrdered_by(rs.getString("ordered_by"));
				si.setSales_person(rs.getString("sales_person"));
				si.setDelivered_by(rs.getString("delivered_by"));
				si.setDelivery_notes(rs.getString("delivery_notes"));
				si.setDelivery_receipt_num(rs.getString("delivery_receipt_num"));
				si.setDiscount(rs.getFloat("discount"));
				si.setCurrent_balance(rs.getFloat("current_balance"));
				si.setStatus(rs.getString("status"));
				company_id = rs.getInt("company_id");
			}

			if (si != null)
			{
				String query = "SELECT * FROM silineitem WHERE sales_invoice_id = '"
						+ si.getSales_invoice_id() + "'";
				statement = db.createStatement();
				rs = statement.executeQuery(query);
				while (rs.next())
				{
					si.addItem(new SILineItem(si.getSales_invoice_id(), rs
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
				si.setCompany(tempCustomer);
			}
		} catch (Exception e)
		{
			e.getMessage();
		}
		return si;
	}

	public float getCurrentVat()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT vat_percentage FROM systeminfo WHERE system_info_id='1'";
			rs = statement.executeQuery(sql);

		} catch (Exception e)
		{
			e.getMessage();
		}
		try
		{
			if (!rs.next())
			{
				return 12;
			} else
			{
				return rs.getFloat("vat_percentage");
			}
		} catch (SQLException ex)
		{

		}
		return 12;
	}
}

package AccountProfile;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class AccountModel extends Model
{
	private int itemCount = 0;

	public AccountModel(DBConnection db)
	{
		super(db);
	}

	public ResultSet getDetail(String name, String AccountType)
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql = "SELECT name,address_location,address_city,address_postal_code,address_country,credit_limit,terms,phone1,phone2,phone3,fax_num,email,website,contact_person,type,status FROM company WHERE name='"
					+ name + "' AND type= '" + AccountType + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	@Override
	public ResultSet getAllDetail()
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql = "SELECT name,type, credit_limit, terms FROM company";
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

	@Override
	public ResultSet searchDetail(String field, String filter)
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql = "";
			if (filter.equalsIgnoreCase("name"))
			{
				sql = "SELECT name, type, credit_limit, terms FROM company WHERE name LIKE '%"
						+ field + "%'";
			} else if (filter.equalsIgnoreCase("customer"))
			{
				sql = "SELECT name,type, credit_limit, terms FROM company WHERE type != 'Supplier' AND name LIKE '%"
						+ field + "%'";
			} else if (filter.equalsIgnoreCase("supplier"))
			{
				sql = "SELECT name,type, credit_limit, terms FROM company WHERE type ='Supplier' AND name LIKE '%"
						+ field + "%'";
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

	@Override
	public void addDetail(ArrayList list)
	{
		try
		{
			statement = con.createStatement();
			String sql = "INSERT INTO company(name,address_location,address_city,address_postal_code,address_country,credit_limit,terms,phone1,phone2,phone3,fax_num,email,website,contact_person,type,status) VALUES('"
					+ list.get(0)
					+ "','"
					+ list.get(1)
					+ "','"
					+ list.get(2)
					+ "','"
					+ list.get(3)
					+ "','"
					+ list.get(4)
					+ "','"
					+ list.get(5)
					+ "','"
					+ list.get(6)
					+ "','"
					+ list.get(7)
					+ "','"
					+ list.get(8)
					+ "','"
					+ list.get(9)
					+ "','"
					+ list.get(10)
					+ "','"
					+ list.get(11)
					+ "','"
					+ list.get(12)
					+ "','"
					+ list.get(13)
					+ "','"
					+ list.get(14) + "','" + list.get(15) + "')";
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void editDetail(ArrayList list, String name, String AccountType)
	{
		System.out.println(list.get(0));
		try
		{
			statement = con.createStatement();
			String sql = "UPDATE company SET name='" + list.get(0)
					+ "',address_location='" + list.get(1) + "',address_city='"
					+ list.get(2) + "',address_postal_code='" + list.get(3)
					+ "',address_country='" + list.get(4) + "',credit_limit='"
					+ list.get(5) + "',terms='" + list.get(6) + "',phone1='"
					+ list.get(7) + "',phone2='" + list.get(8) + "',phone3='"
					+ list.get(9) + "',fax_num='" + list.get(10) + "',email='"
					+ list.get(11) + "',website='" + list.get(12)
					+ "',contact_person='" + list.get(13) + "',type='"
					+ list.get(14) + "',status='" + list.get(15)
					+ "' WHERE name='" + name + "' AND type= '" + AccountType
					+ "'";
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

	}

	@Override
	public void deleteDetail(String ID)
	{
		try
		{
			statement = con.createStatement();
			String sql = "DELETE FROM company WHERE company_id='" + ID + "'";
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

	public int getItemcount()
	{
		return this.itemCount;
	}

	public ResultSet getAllSupplier()
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql = "SELECT name,type, credit_limit, terms FROM company WHERE type = 'Supplier'";
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

	public ResultSet getAllCustomer()
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql = "SELECT name,type, credit_limit, terms FROM company WHERE type != 'Supplier'";
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

	@Override
	public ResultSet getDetail(String ID)
	{
		ResultSet rs = null;
		try
		{
			statement = con.createStatement();
			String sql = "SELECT name,address_location,address_city,address_postal_code,address_country,credit_limit,terms,phone1,phone2,phone3,fax_num,email,website,contact_person,type,status FROM company WHERE company_id='"
					+ ID + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;

	}

	@Override
	public void editDetail(ArrayList list)
	{
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}
}

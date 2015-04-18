package ReturnSlip;

import AcknowledgementReceipt.ARLineItem;
import AcknowledgementReceipt.ARLineItemModel;
import AcknowledgementReceipt.AcknowledgementReceipt;
import Classes.Company;
import Classes.Item;
import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class ReturnSlipModel
{
	protected Connection db;
	protected Statement statement;
	private int itemCount = 0;
	private ArrayList<Company> suppliers;
	private ArrayList<String> ptnums;
	private ArrayList<RSLineItem> items;
	private RSLineItemModel rsLineItemModel;

	public ReturnSlipModel(DBConnection db)
	{
		this.db = db.getConnection();
		suppliers = new ArrayList<>();
		items = new ArrayList<>();
		rsLineItemModel = new RSLineItemModel(db);
	}

	public ResultSet getAllDetail()
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id ";
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
			String sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"
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
				sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND company.name LIKE '%" + field + "%'";
			} else if (filter.equalsIgnoreCase("return slip number"))
			{
				sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"
						+ startDate
						+ "' AND '"
						+ endDate
						+ "' AND returnslip.return_slip_id LIKE '%"
						+ field
						+ "%'";
			} else if (filter.equalsIgnoreCase("part number"))
			{
				sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"
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
			String sql = "SELECT MIN(YEAR(date)) FROM returnslip";
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
			String sql = "SELECT MAX(YEAR(date)) FROM returnslip";
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

	public ArrayList<Company> getSuppliers()
	{
		suppliers = new ArrayList<>();
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM company WHERE type LIKE '%supplier%'";
			rs = statement.executeQuery(sql);
			Company tempSupplier;
			while (rs.next())
			{
				tempSupplier = new Company();
				tempSupplier.setId(rs.getInt("company_id"));
				tempSupplier.setName(rs.getString("name"));
				tempSupplier.setAddressLoc(rs.getString("address_location"));
				tempSupplier.setAddressCity(rs.getString("address_city"));
				tempSupplier.setAddressCountry(rs.getString("address_country"));
				tempSupplier.setPostalCode(rs.getString("address_postal_code"));
				tempSupplier.setPhone1(rs.getString("phone1"));
				tempSupplier.setPhone2(rs.getString("phone2"));
				tempSupplier.setPhone3(rs.getString("phone3"));
				tempSupplier.setFaxNum(rs.getString("fax_num"));
				tempSupplier.setWebsite(rs.getString("website"));
				tempSupplier.setEmail(rs.getString("email"));
				tempSupplier.setContactPerson(rs.getString("contact_person"));
				tempSupplier.setStatus(rs.getString("status"));
				tempSupplier.setCreditLimit(rs.getFloat("credit_limit"));
				tempSupplier.setTerms(rs.getInt("terms"));
				tempSupplier.setType(rs.getString("type"));
				suppliers.add(tempSupplier);
			}
			System.out.println(suppliers.size());

		} catch (Exception e)
		{
			e.getMessage();
		}
		return suppliers;
	}

	public ArrayList<RSLineItem> getItems(String supplier)
	{
		items = new ArrayList<>();
		supplier = "Supplier";
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM item";
			rs = statement.executeQuery(sql);
			RSLineItem tempItem;
			while (rs.next())
			{
				tempItem = new RSLineItem();
				tempItem.setPartNum(rs.getString("part_num"));
				tempItem.setDescription(rs.getString("description"));

				tempItem.setPrice(0);
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

	public Company getSupplier(int index)
	{
		return suppliers.get(index);
	}

	public Item getItem(int index)
	{
		return items.get(index);
	}

	public int getAvailQuantity(int index)
	{
		return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
	}

	public ReturnSlip getRS(String ID)
	{
		ArrayList<RSLineItem> stuff;
		ReturnSlip slip = null;
		int company_id = -1;

		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT * FROM returnslip WHERE return_slip_id = '"
					+ ID + "'";
			rs = statement.executeQuery(sql);

			if (rs.next())
			{
				slip = new ReturnSlip();
				slip.setReturn_slip_id(rs.getString("return_slip_id"));
				slip.setDate(rs.getString("date"));
				slip.setTotal_amount(rs.getFloat("total_amount"));
				slip.setCompany_id(rs.getInt("company_id"));
				slip.setPurchase_transaction_num(rs
						.getInt("purchase_transaction_num"));
				slip.setReturned_by(rs.getString("returned_by"));
				slip.setReturned_date(rs.getString("returned_date"));
				slip.setApproved_by(rs.getString("approved_by"));
				slip.setApproved_date(rs.getString("approved_date"));
				slip.setReceived_by(rs.getString("received_by"));
				slip.setReceived_date(rs.getString("received_date"));
				slip.setNotes(rs.getString("notes"));
				slip.setType(rs.getString("type"));
				company_id = rs.getInt("company_id");

			}

			if (slip != null)
			{
				String query = "SELECT * FROM rslineitem WHERE return_slip_id = '"
						+ slip.getReturn_slip_id() + "'";
				statement = db.createStatement();
				rs = statement.executeQuery(query);
				while (rs.next())
				{
					slip.addItem(new RSLineItem(slip.getReturn_slip_id(), rs
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
				slip.setCompany(tempCustomer);
			}
		} catch (Exception e)
		{
			e.getMessage();
		}

		return slip;
	}

	public ArrayList<String> getPurchaseTransactionNumbers()
	{
		ptnums = new ArrayList<>();
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT purchase_transaction_id FROM purchasetransaction";
			rs = statement.executeQuery(sql);
			String tempPTnum;
			while (rs.next())
			{
				tempPTnum = rs.getString("purchase_transaction_id");
				ptnums.add(tempPTnum);
			}

		} catch (Exception e)
		{
			e.getMessage();
		}
		return ptnums;
	}

	public void addDetail(ReturnSlip slp)
	{
		ReturnSlip ar = slp;
		try
		{

			statement = db.createStatement();
			String sql = "INSERT INTO returnslip(return_slip_id,date,total_amount,company_id,purchase_transaction_num,returned_by,returned_date,approved_by,approved_date,received_by,received_date,type) VALUES('"
					+ ar.getReturn_slip_id()
					+ "','"
					+ ar.getDate()
					+ "','"
					+ ar.getTotal_amount()
					+ "','"
					+ ar.getCompany_id()
					+ "','"
					+ ar.getPurchase_transaction_num()
					+ "','"
					+ ar.getReturned_by()
					+ "','"
					+ ar.getReturned_date()
					+ "','"
					+ ar.getApproved_by()
					+ "','"
					+ ar.getApproved_date()
					+ "','"
					+ ar.getReceived_by()
					+ "','"
					+ ar.getReceived_date()
					+ "','"
					+ ar.getType()
					+ "')";
			statement.executeUpdate(sql);
			int i;
			for (i = 0; i < ar.getItems().size(); i++)
			{
				rsLineItemModel.addDetail(ar.getItems().get(i));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getPurchaseTransactionNumbers(String PTNum)
	{
		String PO = null;
		ResultSet rs;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT po_num FROM purchasetransaction WHERE purchase_transaction_id ='"
					+ PTNum + "'";

			rs = statement.executeQuery(sql);
			String tempPO;

			while (rs.next())
			{
				tempPO = rs.getString("po_num");
				PO = tempPO;
			}

		} catch (Exception e)
		{
			e.getMessage();
		}

		return PO;
	}

	public ResultSet getDetailbyID(String returnslipID)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND returnslip.return_slip_id = '"
					+ returnslipID + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	public ResultSet getDetailbyIDCount(String returnslipID)
	{
		ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT COUNT(*) AS count FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND returnslip.return_slip_id = '"
					+ returnslipID + "'";
			rs = statement.executeQuery(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
		return rs;
	}

	String getSupplierbyID(int company_id) throws SQLException
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

	public void DeductFuncAddDef(String quantity, String partNum)
	{
		int Qfunc = getQuantityFunc(partNum) - Integer.parseInt(quantity);
		int Qdef = getQuantityDef(partNum) + Integer.parseInt(quantity);
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

	private int getQuantityFunc(String partNum)
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

	private int getQuantityDef(String partNum)
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

	public String getLastRSID()
	{

		ResultSet rs = null;
		String RSid = null;
		try
		{
			statement = db.createStatement();
			String sql = "SELECT return_slip_id FROM returnslip ORDER BY return_slip_id DESC LIMIT 1;";
			rs = statement.executeQuery(sql);

			while (rs.next())
			{
				String tempID = rs.getString("return_slip_id");
				RSid = tempID;
			}
		} catch (Exception e)
		{
			e.getMessage();
		}
		if (RSid == null)
			return "null";
		else
			return RSid;

	}
            public ArrayList<String> getPurchaseTransactionNumbersbySupplier(String ID)
    {
        
        ResultSet rs = null;
        ArrayList<String> PTNumbers = new ArrayList<String>();
        try
        {
            statement = db.createStatement();
            String sql = "SELECT purchase_transaction_id FROM purchasetransaction WHERE company_id = '" + ID + "'";
            rs = statement.executeQuery(sql);

            while (rs.next())
            {
                PTNumbers.add(rs.getString("purchase_transaction_id"));
            }
    
        } catch (Exception e)
        {
            e.getMessage();
        }
        return PTNumbers;
    }


}

package Inventory;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class ItemModel extends Model
{
    private Connection con;
    private int itemCount=0;
	public ItemModel(DBConnection db)
	{
		super(db);
                
	}

	@Override
	public ResultSet getDetail(String ID)
	{
		ResultSet rs = null;
		try
		{   
                        con = db.getConnection();
			statement = con.createStatement();
			String sql = "SELECT part_num, description, rack_location, stock_minimum, sister_company_price, traders_price, walk_in_price, last_cost, notes, image, status FROM item WHERE part_num = '"+ID+"'";
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
			con = db.getConnection();
			statement = con.createStatement();
			String sql = "SELECT part_num, description, quantity_functional,quantity_defective, last_cost,walk_in_price, traders_price,sister_company_price FROM item";
			rs = statement.executeQuery(sql);
                        rs.last();                        // Get Item Count
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
		String sql = "";
		try
		{
			con = db.getConnection();
			statement = con.createStatement();
			if ("Part Number".equals(filter))
				sql = "SELECT part_num, description, quantity_functional,quantity_defective, last_cost,walk_in_price, traders_price,sister_company_price FROM item WHERE part_num LIKE '%"
						+ field + "%'";
			else
				sql = "SELECT part_num, description, quantity_functional,quantity_defective, last_cost,walk_in_price, traders_price,sister_company_price FROM item WHERE description LIKE '%"
						+ field + "%'";
			rs = statement.executeQuery(sql);
                        rs.last();                        // Get Item Count
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
			con = db.getConnection();
			statement = con.createStatement();
			String sql = "INSERT INTO item(part_num,description,rack_location,stock_minimum,sister_company_price,traders_price,walk_in_price,last_cost,notes,image,status) VALUES('"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"','"+list.get(8)+"','"+list.get(9)+"','"+list.get(10)+"')";
                        statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}
	}

	@Override
	public void editDetail(ArrayList list)
	{
		try
		{
			con = db.getConnection();
			statement = con.createStatement();
			String sql = "UPDATE item SET description='"+list.get(1)+"',rack_location='"+list.get(2)+"',stock_minimum='"+list.get(3)+"',sister_company_price='"+list.get(4)+"',traders_price='"+list.get(5)+"',walk_in_price='"+list.get(6)+"',last_cost='"+list.get(7)+"',notes='"+list.get(8)+"',image='"+list.get(9)+"',status='"+list.get(10)+"' WHERE part_num='"+list.get(0)+"'" ;
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
			con = db.getConnection();
			statement = con.createStatement();
			String sql = "DELETE FROM item WHERE part_num='"+ID+"'";
			statement.executeUpdate(sql);
		} catch (Exception e)
		{
			e.getMessage();
		}

	}

         public TableModel myModel(ResultSet rs) 
         {
                TableModel model=DbUtils.resultSetToTableModel(rs);
                return model;
          }

    public boolean getConnectionStatus()
    {
        return db.getConnectionStatus();
    }
    
    public int getItemcount()
    {
        return this.itemCount;
    }

    public ResultSet getAllSupplier()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public ResultSet getAllCustomer()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet getDetail(String name, String AccountType)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editDetail(ArrayList al, String name, String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

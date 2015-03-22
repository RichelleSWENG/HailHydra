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
public class ReportModel {
    protected Connection db;
    protected Statement statement;
    private int itemCount=0;
    
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
			String sql = "SELECT part_num, description,stock_minimum,quantity_functional,last_cost,rack_location FROM item WHERE quantity_functional<=stock_minimum";
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
	public ResultSet searchOrderReport(String field, String filter)
	{
		ResultSet rs = null;
		String sql = "";
		try
		{
			statement = db.createStatement();
			if (filter.equalsIgnoreCase("part number"))
				sql = "SELECT part_num, description,stock_minimum,quantity_functional,last_cost,rack_location FROM item WHERE quantity_functional<=stock_minimum AND part_num LIKE '%"
						+ field + "%'";
                        else if(filter.equalsIgnoreCase("description"))
				sql = "SELECT part_num, description,stock_minimum,quantity_functional,last_cost,rack_location FROM item WHERE quantity_functional<=stock_minimum AND description LIKE '%"
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
        public ResultSet TermsReport()
        {
            ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "";
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
        public ResultSet CreditReport()
        {
            ResultSet rs = null;
		try
		{
			statement = db.createStatement();
			String sql = "";
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
    
}

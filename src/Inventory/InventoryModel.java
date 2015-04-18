/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import Database.DBConnection;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 *
 * @author Kingston
 */
public class InventoryModel
{
	private final boolean connectionStatus;
	private final Connection con;
	private int itemcount = 0;

	public InventoryModel()
	{
		DBConnection dbc = new DBConnection();// creating Database Connection
		this.connectionStatus = dbc.getConnectionStatus(); // connection status
															// after connecting
															// to
		this.con = dbc.getConnection();

	}

	public boolean getConnectionStatus()
	{

		return this.connectionStatus;
	}

	public TableModel myModel(String sql) throws Exception
	{

		// System.out.println("in MyModel");

		Statement st = con.createStatement();
		ResultSet rs;
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery(sql);
		rs.last(); // Get Item Count
		itemcount = rs.getRow();
		rs.beforeFirst();
		TableModel model = DbUtils.resultSetToTableModel(rs);
		return model;
	}

	public int getItemcount()
	{
		return this.itemcount;
	}

	public void AddItemProfile(String sql) throws SQLException
	{
		// System.out.println(sql);
		// Statement st = con.createStatement();
		// ResultSet rs;
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		pst.executeUpdate(sql);
	}

	public void DeleteItemProfile(String sql) throws SQLException
	{
		System.out.println(sql);
		Statement st = con.createStatement();
		st.executeUpdate(sql);
	}

	public ArrayList<String> getItemProfile(String sql) throws SQLException
	{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		ResultSetMetaData metadata = rs.getMetaData();
		int numberOfColumns = metadata.getColumnCount();
		ArrayList<String> arrayList = new ArrayList<String>();
		while (rs.next())
		{
			int i = 1;
			while (i <= numberOfColumns)
			{
				arrayList.add(rs.getString(i++));
			}
		}
		return arrayList;
	}
}

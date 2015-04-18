/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import HailHydra.Model;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author Kingston
 */
public class InventoryController
{

	private int itemcount = 0;
	private ItemModel inventoryModel;
	private InventoryListGUI gui;
	private ArrayList<String> itemProfile = new ArrayList<>();

	public InventoryController(ItemModel tempModel, InventoryListGUI tempGUI)
	{
		this.inventoryModel = tempModel;
		this.gui = tempGUI;
	}

	public TableModel getAllModel() throws Exception
	{
		TableModel tbm = inventoryModel.myModel(inventoryModel.getAllDetail());
		this.itemcount = inventoryModel.getItemcount();
		gui.setItemCount(itemcount);
		return tbm;
	}

	public void SearchSomething(String text, String searchBy) throws Exception
	{
		TableModel tbm;
		tbm = inventoryModel.myModel(inventoryModel
				.searchDetail(text, searchBy));
		gui.setTableModel(tbm);
		gui.setItemCount(inventoryModel.getItemcount());

	}

	public void AddItem(String partnumber, String description,
			String racklocation, String stockminimum, String sisterprice,
			String retailprice, String walkprice, String lastcost,
			String notes, String imagelocation, String status)
			throws SQLException
	{
		String tmp = null;
		if (imagelocation != null)

			tmp = imagelocation.replace('\\', '/'); // replace \\ with /
		String statusBit;
		if (status == "false")
			statusBit = "1";
		else
			statusBit = "0";
		ArrayList<String> al = new ArrayList();
		// part_num,description,rack_location,stock_minimum,sister_company_price,traders_price,walk_in_price,last_cost,notes,image,status
		al.add(partnumber);
		al.add(description);
		al.add(racklocation);
		al.add(stockminimum);
		al.add(sisterprice);
		al.add(retailprice);
		al.add(walkprice);
		al.add(lastcost);
		al.add(notes);
		al.add(tmp);
		al.add(statusBit);

		inventoryModel.addDetail(al);
	}

	public void ViewAllItems() throws Exception
	{
		gui.ViewAll();
	}

	public void DeleteItem(String pkey) throws SQLException
	{
		inventoryModel.deleteDetail(pkey);
	}

	public void ViewItemProfile(String pkey) throws SQLException, IOException
	{
		ResultSet rs;
		rs = inventoryModel.getDetail(pkey);
		ResultSetMetaData metadata = rs.getMetaData();
		int numberOfColumns = metadata.getColumnCount();
		ArrayList<String> temp = new ArrayList<>();

		while (rs.next())
		{
			int i = 1;
			while (i <= numberOfColumns)
			{
				temp.add(rs.getString(i++));
			}
		}
		this.itemProfile = temp;

	}

	public ArrayList getItemProfile()
	{
		return this.itemProfile;
	}

	public void setItemProfile(ArrayList<String> itemProfile)
	{
		this.itemProfile = itemProfile;
	}

	public void ModifyItemProfile(ArrayList<String> al)
	{
		inventoryModel.editDetail(al);
	}
}

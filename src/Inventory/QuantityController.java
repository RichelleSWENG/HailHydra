/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inventory;

import javax.swing.table.TableModel;

/**
 *
 * @author Janine
 */
public class QuantityController
{
	private ItemModel model;
	private SetInventoryQuantityGUI gui;
	private int itemcount;

	public QuantityController(ItemModel model, SetInventoryQuantityGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}

	TableModel getAllModel()
	{
		TableModel tbm = model.myModel(model.getAllQuantity());
		this.itemcount = model.getItemcount();
		gui.setItemCount(itemcount);
		return tbm;
	}

	public void searchSomething(String text, int type)
	{
		String searchBy = null;
		if (type == 0)
			searchBy = "part_num";
		else if (type == 1)
			searchBy = "description";
		TableModel tbm;
		tbm = model.myModel(model.searchQuantity(text, searchBy));
		this.itemcount = model.getItemcount();
		gui.setItemCount(itemcount);
		gui.setTableModel(tbm);
	}

	public void changeQuantity(String part_num, String funtionl,
			String defective)
	{
		model.changeQuantity(part_num, funtionl, defective);
	}

	public void changeAllQuantity()
	{
		gui.changeAllQuantity();
	}

}

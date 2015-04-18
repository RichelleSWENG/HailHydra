package ReturnSlip;

import Classes.Company;
import Classes.Item;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

public class ReturnSlipController
{
	private ReturnSlipModel returnslipModel;
	private ReturnSlipListGUI gui;
	private ArrayList<RSLineItem> pendingItems;
	private int itemcount;
	private ReturnSlip slip;
	private ArrayList<String> alReturnSlip = new ArrayList<>();
	private String returnslipItem;

	public ReturnSlipController(ReturnSlipModel rsModel,
			ReturnSlipListGUI tempGUI)
	{
		this.returnslipModel = rsModel;
		pendingItems = new ArrayList<>();
		this.gui = tempGUI;
		slip = null;
	}

	TableModel getAllModel()
	{
		TableModel tbm = returnslipModel
				.myModel(returnslipModel.getAllDetail());
		this.itemcount = returnslipModel.getItemcount();
		gui.setItemCount(itemcount);
		return tbm;
	}

	public void SearchSomething(String text, int type, String startDate,
			String endDate)
	{
		String searchBy = null;
		if (type == 0)
			searchBy = "name";
		else if (type == 1)
			searchBy = "return slip number";
		else if (type == 2)
			searchBy = "part number";
		TableModel tbm;
		tbm = returnslipModel.myModel(returnslipModel.searchDetail(text,
				searchBy, startDate, endDate));
		this.itemcount = returnslipModel.getItemcount();
		gui.setItemCount(itemcount);
		gui.setTableModel(tbm);
	}

	public void searchbyDate(String startDate, String endDate)
	{
		TableModel tbm;
		tbm = returnslipModel.myModel(returnslipModel.getAllDetailbyDate(
				startDate, endDate));
		this.itemcount = returnslipModel.getItemcount();
		gui.setItemCount(itemcount);
		gui.setTableModel(tbm);
	}

	public String getMaxYear()
	{
		ResultSet resultset = returnslipModel.getMaxYear();

		try
		{
			resultset.next();
			return resultset.getString("MAX(YEAR(date))");
		} catch (SQLException ex)
		{
			Logger.getLogger(ReturnSlipController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public String getMinYear()
	{
		ResultSet resultset = returnslipModel.getMinYear();

		try
		{
			resultset.next();
			return resultset.getString("MIN(YEAR(date))");
		} catch (SQLException ex)
		{
			Logger.getLogger(ReturnSlipController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public ArrayList<Company> getSuppliers()
	{
		return returnslipModel.getSuppliers();
	}

	public Company getSupplier(int index)
	{
		return returnslipModel.getSupplier(index);
	}

	public ArrayList<RSLineItem> getItems(String customerType)
	{
		return returnslipModel.getItems(customerType);
	}

	public Item getItem(int index)
	{
		return returnslipModel.getItem(index);
	}

	public void addPendingItem(RSLineItem item)
	{
		pendingItems.add(item);
	}

	public void removePending()
	{
		pendingItems.clear();
	}

	public ArrayList<RSLineItem> getPending()
	{
		return this.pendingItems;
	}

	public int getAvailQuantity(int index)
	{
		return returnslipModel.getAvailQuantity(index);
	}

	public void setSlipTarget(ReturnSlip slip)
	{
		this.slip = slip;
	}

	public ReturnSlip getSlipTarget()
	{
		return slip;
	}

	public ReturnSlip getRS(String ID)
	{
		return returnslipModel.getRS(ID);
	}

	public void addRS(String return_slip_id, int company_id, String date,
			float total, int PTNum, String PONum, String Returned_by,
			String Returned_date, String Approved_by, String Approved_date,
			String Received_by, String Received_date, String notes, String type)
	{
		/*
		 * public ReturnSlip( String return_slip_id, int company_id, String
		 * date, float total_amount, String sales_invoice_num, int
		 * purchase_transaction_num, String returned_by, String returned_date,
		 * String approved_by, String approved_date, String received_by, String
		 * received_date, String notes, String type, ArrayList<RSLineItem> list)
		 */
		ReturnSlip slp = new ReturnSlip(return_slip_id, company_id, date,
				total, PTNum, PONum, Returned_by, Returned_date, Approved_by,
				Approved_date, Received_by, Received_date, notes, type,
				pendingItems);
		returnslipModel.addDetail(slp);
	}

	public ArrayList<String> getPurchaseTransactionNumbers()
	{
		return returnslipModel.getPurchaseTransactionNumbers();
	}

	public String getPurchaseOrderNumber(String PTNum)
	{
		return returnslipModel.getPurchaseTransactionNumbers(PTNum);
	}

	public void ViewReturnSlip(String returnslipID) throws SQLException
	{
		// get rsLineItem count
		int numberofRSLineItem = 0;
		ResultSet rscount;
		rscount = returnslipModel.getDetailbyIDCount(returnslipID);
		while (rscount.next())
			numberofRSLineItem = rscount.getInt("count");

	}

	public String getSupplierbyID(int company_id) throws SQLException
	{
		return returnslipModel.getSupplierbyID(company_id);
	}

	public void DeductFuncAddDef(String quantity, String partNum)
	{
		returnslipModel.DeductFuncAddDef(quantity, partNum);
	}

	public void setReturnSlipItem(String returnslipItem)
	{
		this.returnslipItem = returnslipItem;
	}

	public String getReturnSlipItem()
	{
		return this.returnslipItem;
	}

	public String getLastRSID()
	{
		return returnslipModel.getLastRSID();
	}

        public ArrayList<String> getPurchaseTransactionNumbersbySupplier(Company c)
        {
        return returnslipModel.getPurchaseTransactionNumbersbySupplier(String.valueOf(c.getId()));

        }

}

package DebitMemo;

import AcknowledgementReceipt.ARLineItem;
import AcknowledgementReceipt.AckReceiptModel;
import AcknowledgementReceipt.AcknowledgementReceipt;
import AcknowledgementReceipt.AcknowledgementReceiptListGUI;
import Classes.Company;
import Classes.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

public class DebitMemoController
{
	private DebitMemoModel debitmemoModel;
	private DebitMemoListGUI gui;
	private int itemcount;
	private ArrayList<DMLineItem> pendingItems;
	private DebitMemo memo;
	private String partNum;

	public DebitMemoController(DebitMemoModel tempModel,
			DebitMemoListGUI tempGUI)
	{
		this.debitmemoModel = tempModel;
		this.gui = tempGUI;
		pendingItems = new ArrayList<>();
		memo = null;

	}

	public DebitMemoController(DebitMemoModel debitMemoModel)
	{
		this.debitmemoModel = debitMemoModel;
		pendingItems = new ArrayList<>();
		memo = null;
	}

	public ArrayList<Company> getCustomers()
	{
		return debitmemoModel.getCustomers();
	}

	public Company getCustomer(int index)
	{
		return debitmemoModel.getCustomer(index);
	}

	public ArrayList<DMLineItem> getItems(String customerType)
	{
		return debitmemoModel.getItems(customerType);
	}

	public Item getItem(int index)
	{
		return debitmemoModel.getItem(index);
	}

	public void addPendingItem(DMLineItem item)
	{
		pendingItems.add(item);
	}

	public void removePending()
	{
		pendingItems.clear();
	}

	public void addDM(String debit_memo_id, int company_id, String date,
			float total_amount, String receipt_type, String receipt_number,
			String approved_by, String received_by, String approved_date,
			String received_date, String notes, int status, String type)
	{
		DebitMemo mmo = new DebitMemo(debit_memo_id, company_id, date,
				total_amount, receipt_type, receipt_number, approved_by,
				received_by, approved_date, received_date, notes, status, type,
				pendingItems);
		debitmemoModel.addDetail(mmo);

	}

	public int getAvailQuantity(int index)
	{
		return debitmemoModel.getAvailQuantity(index);
	}

	TableModel getAllModel()
	{
		TableModel tbm = debitmemoModel.myModel(debitmemoModel.getAllDetail());
		this.itemcount = debitmemoModel.getItemcount();
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
			searchBy = "debit memo number";
		else if (type == 2)
			searchBy = "part number";
		TableModel tbm;
		tbm = debitmemoModel.myModel(debitmemoModel.searchDetail(text,
				searchBy, startDate, endDate));
		this.itemcount = debitmemoModel.getItemcount();
		gui.setItemCount(itemcount);
		gui.setTableModel(tbm);
	}

	public void searchbyDate(String startDate, String endDate)
	{
		TableModel tbm;
		tbm = debitmemoModel.myModel(debitmemoModel.getAllDetailbyDate(
				startDate, endDate));
		this.itemcount = debitmemoModel.getItemcount();
		gui.setItemCount(itemcount);
		gui.setTableModel(tbm);
	}

	public String getMaxYear()
	{
		ResultSet resultset = debitmemoModel.getMaxYear();

		try
		{
			resultset.next();
			return resultset.getString("MAX(YEAR(date))");
		} catch (SQLException ex)
		{
			Logger.getLogger(DebitMemoController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public String getMinYear()
	{
		ResultSet resultset = debitmemoModel.getMinYear();

		try
		{
			resultset.next();
			return resultset.getString("MIN(YEAR(date))");
		} catch (SQLException ex)
		{
			Logger.getLogger(DebitMemoController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public void setMemoTarget(DebitMemo mmo)
	{
		this.memo = mmo;
	}

	public DebitMemo getMemoTarget()
	{
		return memo;
	}

	public DebitMemo getDM(String ID)
	{
		return debitmemoModel.getDM(ID);
	}

	public ArrayList<String> getReceiptNumbersAR(Company c)
	{
		return debitmemoModel.getReceiptNumbersAR(String.valueOf(c.getId()));
	}

	public ArrayList<String> getReceiptNumbersSI(Company c)
	{
		return debitmemoModel.getReceiptNumbersSI(String.valueOf(c.getId()));
	}

	public String getLastDMID()
	{
		return debitmemoModel.getLastDMID();
	}

	public void updateFromDefec(String quantity, String partNum, int status)
	{
		debitmemoModel.updateFromDefec(quantity, partNum, status);
	}

	public void updateFromType(String quantity, String partNum, String type)
	{
		debitmemoModel.updateFromType(quantity, partNum, type);
	}

	public void setPartNumTarget(String partNum)
	{
		this.partNum = partNum;
	}

	public String getPartNumTarget()
	{
		return partNum;
	}

	public String getCustomerbyID(int company_id) throws SQLException
	{
		return debitmemoModel.getCustomerbyID(company_id);
	}

}

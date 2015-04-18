package Sales;

import Classes.Company;
import Classes.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

public class SalesInvoiceController
{
	private SalesInvoiceModel salesinvoiceModel;
	private SalesInvoiceListGUI gui;
	private ArrayList<SILineItem> pendingItems;
	private int itemcount;
	private SalesInvoice SITarget;

	public SalesInvoiceController(SalesInvoiceModel tempModel,
			SalesInvoiceListGUI tempGUI)
	{
		this.salesinvoiceModel = tempModel;
		this.gui = tempGUI;
		pendingItems = new ArrayList<>();
		itemcount = 0;
	}

	public SalesInvoiceController(SalesInvoiceModel tempModel)
	{
		this.salesinvoiceModel = tempModel;
		pendingItems = new ArrayList<>();
		itemcount = 0;
	}

	TableModel getAllModel()
	{
		TableModel tbm = salesinvoiceModel.myModel(salesinvoiceModel
				.getAllDetail());
		this.itemcount = salesinvoiceModel.getItemcount();
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
			searchBy = "sales invoice number";
		else if (type == 2)
			searchBy = "part number";
		TableModel tbm;
		tbm = salesinvoiceModel.myModel(salesinvoiceModel.searchDetail(text,
				searchBy, startDate, endDate));
		this.itemcount = salesinvoiceModel.getItemcount();
		gui.setItemCount(itemcount);
		gui.setTableModel(tbm);
	}

	public String getMaxYear()
	{
		ResultSet resultset = salesinvoiceModel.getMaxYear();

		try
		{
			resultset.next();
			return resultset.getString("MAX(YEAR(date))");
		} catch (SQLException ex)
		{
			Logger.getLogger(SalesInvoiceController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public String getMinYear()
	{
		ResultSet resultset = salesinvoiceModel.getMinYear();

		try
		{
			resultset.next();
			return resultset.getString("MIN(YEAR(date))");
		} catch (SQLException ex)
		{
			Logger.getLogger(SalesInvoiceController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public void ViewSalesInvoicebyDate(String startDate, String endDate)
	{
		TableModel tbm = salesinvoiceModel.myModel(salesinvoiceModel
				.getAllDetailbyDate(startDate, endDate));
		this.itemcount = salesinvoiceModel.getItemcount();
		gui.setItemCount(itemcount);
		gui.setTableModel(tbm);
	}

	public ArrayList<Company> getCustomers()
	{
		return salesinvoiceModel.getCustomers();
	}

	public Company getCustomer(int index)
	{
		return salesinvoiceModel.getCustomer(index);
	}

	public ArrayList<SILineItem> getItems(String customerType)
	{
		return salesinvoiceModel.getItems(customerType);
	}

	public Item getItem(int index)
	{
		return salesinvoiceModel.getItem(index);
	}

	public void addPendingItem(SILineItem item)
	{
		pendingItems.add(item);
	}

	public void removePending()
	{
		pendingItems.clear();
	}

	public void addSI(String sales_invoice_id, String date,
			float original_amount, String po_num, String ordered_by,
			String sales_person, String delivered_by, String delivery_notes,
			String delivery_receipt_num, float discount, float current_balance,
			String status, String pwd_id_number_notes, float vat,
			Company company)
	{
		SalesInvoice si = new SalesInvoice(sales_invoice_id, date,
				original_amount, po_num, ordered_by, sales_person,
				delivered_by, delivery_notes, delivery_receipt_num, discount,
				current_balance, status, pwd_id_number_notes, vat,
				pendingItems, company);
		salesinvoiceModel.addDetail(si);
	}

	public void editSI(String sales_invoice_id, String date,
			float original_amount, String po_num, String ordered_by,
			String sales_person, String delivered_by, String delivery_notes,
			String delivery_receipt_num, float discount, float current_balance,
			String status, String pwd_id_number_notes, float vat,
			Company company)
	{
		SalesInvoice si = new SalesInvoice(sales_invoice_id, date,
				original_amount, po_num, ordered_by, sales_person,
				delivered_by, delivery_notes, delivery_receipt_num, discount,
				current_balance, status, pwd_id_number_notes, vat,
				pendingItems, company);
		salesinvoiceModel.editDetail(si);
		setTarget(getSI(si.getSales_invoice_id()));
	}

	public int getAvailQuantity(int index)
	{
		return salesinvoiceModel.getAvailQuantity(index);
	}

	public void setTarget(SalesInvoice si)
	{
		SITarget = si;
	}

	public SalesInvoice getTarget()
	{
		return SITarget;
	}

	public SalesInvoice getSI(String ID)
	{
		return salesinvoiceModel.getSI(ID);
	}

	public float getCurrentVat()
	{
		return salesinvoiceModel.getCurrentVat();
	}
}

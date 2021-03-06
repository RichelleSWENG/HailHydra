package Purchases;

import Classes.Company;
import java.util.ArrayList;

public class PurchaseTransaction
{
	private String purchase_transaction_id, date, po_num, received_by,
			ordered_by, receiving_notes, delivery_receipt_num,
			ref_sales_invoice_num, status;
	private float discount, original_amount, vat, current_balance;
	private ArrayList<PTLineItem> list;
	private Company company;

	public PurchaseTransaction()
	{
		this.purchase_transaction_id = "";
		this.date = "";
		this.original_amount = 0;
		this.po_num = "";
		this.received_by = "";
		this.ordered_by = "";
		this.receiving_notes = "";
		this.delivery_receipt_num = "";
		this.discount = 0;
		this.vat = 0;
		this.current_balance = 0;
		this.ref_sales_invoice_num = "";
		this.status = "";
		this.list = new ArrayList<>();
	}

	public PurchaseTransaction(String purchase_transaction_id, String date,
			float original_amount, String po_num, String received_by,
			String ordered_by, String receiving_notes,
			String delivery_receipt_num, String ref_sales_invoice_num,
			float discount, float vat, float current_balance, String status,
			ArrayList<PTLineItem> list, Company company)
	{
		this.purchase_transaction_id = purchase_transaction_id;
		this.company = company;
		this.date = date;
		this.original_amount = original_amount;
		this.po_num = po_num;
		this.received_by = received_by;
		this.ordered_by = ordered_by;
		this.receiving_notes = receiving_notes;
		this.delivery_receipt_num = delivery_receipt_num;
		this.ref_sales_invoice_num = ref_sales_invoice_num;
		this.discount = discount;
		this.vat = vat;
		this.current_balance = current_balance;
		this.status = status;
		this.list = list;
	}

	public String getPurchase_transaction_id()
	{
		return purchase_transaction_id;
	}

	public void setPurchase_transaction_id(String purchase_transaction_id)
	{
		this.purchase_transaction_id = purchase_transaction_id;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public float getOriginal_amount()
	{
		return original_amount;
	}

	public void setOriginal_amount(float original_amount)
	{
		this.original_amount = original_amount;
	}

	public String getPo_num()
	{
		return po_num;
	}

	public void setPo_num(String po_num)
	{
		this.po_num = po_num;
	}

	public String getReceived_by()
	{
		return received_by;
	}

	public void setReceived_by(String received_by)
	{
		this.received_by = received_by;
	}

	public String getOrdered_by()
	{
		return ordered_by;
	}

	public void setOrdered_by(String ordered_by)
	{
		this.ordered_by = ordered_by;
	}

	public String getReceiving_notes()
	{
		return receiving_notes;
	}

	public void setReceiving_notes(String receiving_notes)
	{
		this.receiving_notes = receiving_notes;
	}

	public String getDelivery_receipt_num()
	{
		return delivery_receipt_num;
	}

	public void setDelivery_receipt_num(String delivery_receipt_num)
	{
		this.delivery_receipt_num = delivery_receipt_num;
	}

	public float getDiscount()
	{
		return discount;
	}

	public void setDiscount(float discount)
	{
		this.discount = discount;
	}

	public float getVat()
	{
		return vat;
	}

	public void setVat(float vat)
	{
		this.vat = vat;
	}

	public float getCurrent_balance()
	{
		return current_balance;
	}

	public void setCurrent_balance(float current_balance)
	{
		this.current_balance = current_balance;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public ArrayList<PTLineItem> getItems()
	{
		return list;
	}

	public void setList(ArrayList<PTLineItem> list)
	{
		this.list = list;
	}

	public void addItem(PTLineItem line)
	{
		list.add(line);
	}

	public void removeItem(PTLineItem line)
	{
		list.remove(line);
	}

	public String getRef_sales_invoice_num()
	{
		return ref_sales_invoice_num;
	}

	public void setRef_sales_invoice_num(String ref_sales_invoice_num)
	{
		this.ref_sales_invoice_num = ref_sales_invoice_num;
	}

	public void setCompany(Company c)
	{
		company = c;
	}

	public Company getCompany()
	{
		return company;
	}

	public int getCompany_id()
	{
		return company.getId();
	}

	public String getCompany_name()
	{
		return company.getName();
	}

}

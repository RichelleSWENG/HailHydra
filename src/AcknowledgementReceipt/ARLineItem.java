package AcknowledgementReceipt;

import Classes.Item;

public class ARLineItem extends Item
{
	private String acknowledgement_receipt_id;
	private int quantity;
	private float unit_price;
	private float line_total;

	public ARLineItem()
	{
		super();
		this.acknowledgement_receipt_id = "";
		this.quantity = 0;
		this.partNum = "";
		this.unit_price = 0;
		this.line_total = 0;
	}

	public ARLineItem(String acknowledgement_receipt_id, int quantity,
			String part_num, float unit_price, float line_total)
	{
		super();
		this.acknowledgement_receipt_id = acknowledgement_receipt_id;
		this.quantity = quantity;
		this.partNum = part_num;
		this.unit_price = unit_price;
		this.line_total = line_total;
	}

	public String getAcknowledgement_receipt_id()
	{
		return acknowledgement_receipt_id;
	}

	public void setAcknowledgement_receipt_id(String acknowledgement_receipt_id)
	{
		this.acknowledgement_receipt_id = acknowledgement_receipt_id;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public float getUnit_price()
	{
		return unit_price;
	}

	public void setUnit_price(float unit_price)
	{
		this.unit_price = unit_price;
	}

	public float getLine_total()
	{
		return line_total;
	}

	public void setLine_total(float line_total)
	{
		this.line_total = line_total;
	}

}

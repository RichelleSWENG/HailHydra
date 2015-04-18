package ReturnSlip;

import Classes.Item;

public class RSLineItem extends Item
{
	private String return_slip_id;
	private int quantity;
	private float unit_price, line_total;

	public RSLineItem()
	{
		super();
		this.return_slip_id = "";
		this.quantity = 0;
		this.partNum = "";
		this.unit_price = 0;
		this.line_total = 0;
	}

	public RSLineItem(String return_slip_id, int quantity, String part_num,
			float unit_price, float line_total)
	{
		super();
		this.return_slip_id = return_slip_id;
		this.quantity = quantity;
		this.partNum = part_num;
		this.unit_price = unit_price;
		this.line_total = line_total;
	}

	public String getReturn_slip_id()
	{
		return return_slip_id;
	}

	public void setReturn_slip_id(String return_slip_id)
	{
		this.return_slip_id = return_slip_id;
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

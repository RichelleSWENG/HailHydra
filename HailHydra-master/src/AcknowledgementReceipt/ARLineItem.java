package AcknowledgementReceipt;

public class ARLineItem
{
	private String acknowledgement_receipt_id;
	private int quantity;
	private String part_num;
	private float unit_price;
	private float line_total;
	
	public ARLineItem(String acknowledgement_receipt_id,int quantity,String part_num,float unit_price,float line_total)
	{
		this.acknowledgement_receipt_id=acknowledgement_receipt_id;
		this.quantity=quantity;
		this.part_num=part_num;
		this.unit_price=unit_price;
		this.line_total=line_total;
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

	public String getPart_num()
	{
		return part_num;
	}

	public void setPart_num(String part_num)
	{
		this.part_num = part_num;
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

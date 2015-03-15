package Sales;

import Classes.Item;

public class SILineItem extends Item
{
	private String sales_invoice_id;
	private int quantity;
        private float unit_price;
	private float line_total;

        public SILineItem()
        {
                super();
                this.sales_invoice_id="";
                this.quantity=0;
                this.partNum = "";
		this.unit_price=0;
		this.line_total=0;
        }
	
        public SILineItem(String sales_invoice_id,int quantity,String part_num,float unit_price,float line_total)
	{
                super();
		this.sales_invoice_id=sales_invoice_id;
		this.quantity=quantity;
		this.partNum = part_num;
		this.unit_price=unit_price;
		this.line_total=line_total;
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

        public String getSales_invoice_id()
        {
            return sales_invoice_id;
        }

        public void setSales_invoice_id(String sales_invoice_id)
        {
            this.sales_invoice_id = sales_invoice_id;
        }
	
	
}

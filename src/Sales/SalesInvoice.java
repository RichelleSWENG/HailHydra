package Sales;

import AccountProfile.AccountProfile;
import AccountProfile.AccountProfile.AccountProfileBuilder;

public class SalesInvoice
{

	private String partNum, description;
	private Float unitPrice, total;
	private int quantity;

	public static class SalesInvoiceBuilder
	{
		private String partNum, description;
		private final Float unitPrice, total;
		private final int quantity;
		
		public SalesInvoiceBuilder(String partNum, String description, Float unitPrice, Float total, int quantity)
		{
			this.partNum=partNum;
			this.description=description;
			this.unitPrice=unitPrice;
			this.total=total;
			this.quantity=quantity;
		}
		
		public SalesInvoiceBuilder setPartNumber(String temp)
        {
            this.partNum=temp;
            return this;
        }
		
		public SalesInvoiceBuilder setDescription(String temp)
        {
            this.description=temp;
            return this;
        }
		
		public SalesInvoice build()
        {
            return new SalesInvoice(this);
        }
	}
	
	public SalesInvoice(SalesInvoiceBuilder temp)
    {
    	this.partNum=temp.partNum;
        this.description=temp.description;
        this.unitPrice=temp.unitPrice;
        this.total=temp.total;
        this.quantity=temp.quantity;

    }

	public String getPartNum()
	{
		return this.partNum;
	}

	public String getDescription()
	{
		return this.description;
	}

	public Float getUnitPrice()
	{
		return this.unitPrice;
	}

	public Float getTotal()
	{
		return this.total;
	}

	public int getQuantity()
	{
		return this.quantity;
	}

}

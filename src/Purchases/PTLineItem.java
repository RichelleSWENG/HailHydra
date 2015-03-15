package Purchases;

import Classes.Item;

public class PTLineItem extends Item
{

    private String purchase_transaction_id;
    private int quantity;
    private float unit_price;
    private float line_total;

    public PTLineItem()
    {
        super();
        this.purchase_transaction_id = "";
        this.quantity = 0;
        this.partNum = "";
        this.unit_price = 0;
        this.line_total = 0;
    }

    public PTLineItem(String purchase_transaction_id, int quantity, float unit_price, float line_total)
    {
        super();
        this.purchase_transaction_id = purchase_transaction_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.line_total = line_total;
    }

    public String getPurchase_transaction_id()
    {
        return purchase_transaction_id;
    }

    public void setPurchase_transaction_id(String purchase_transaction_id)
    {
        this.purchase_transaction_id = purchase_transaction_id;
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

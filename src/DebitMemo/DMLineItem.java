/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DebitMemo;

/**
 *
 * @author Kingston
 */
import Classes.Item;

public class DMLineItem extends Item
{
	private String debit_memo_id;
	private int quantity;
	private float unit_price, line_total;

	public DMLineItem()
	{
		super();
		this.debit_memo_id = "";
		this.quantity = 0;
		this.partNum = "";
		this.unit_price = 0;
		this.line_total = 0;
	}

	public DMLineItem(String debit_memo_id, int quantity, String part_num,
			float unit_price, float line_total)
	{
		super();
		this.debit_memo_id = debit_memo_id;
		this.quantity = quantity;
		this.partNum = part_num;
		this.unit_price = unit_price;
		this.line_total = line_total;
	}

	public String getDebit_memo_id()
	{
		return debit_memo_id;
	}

	public void setDebit_memo_id(String debit_memo_id)
	{
		this.debit_memo_id = debit_memo_id;
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

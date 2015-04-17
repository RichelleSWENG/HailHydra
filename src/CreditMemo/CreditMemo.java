/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreditMemo;

/**
 *
 * @author Kingston
 */
public class CreditMemo
{
	private String id;
	private String date;
	private String rsNum;
	private int status;
	private String type;
	private String partNum;

	public CreditMemo()
	{
		this.id = "";
		this.date = "";
		this.rsNum = "";
		this.status = -1;
		this.type = "";
		this.partNum = "";
	}

	public CreditMemo(String id, String date, String rsNum, int status,
			String type, String partNum)
	{
		this.id = id;
		this.date = date;
		this.rsNum = rsNum;
		this.status = status;
		this.type = type;
		this.partNum = partNum;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getRsNum()
	{
		return rsNum;
	}

	public void setRsNum(String rsNum)
	{
		this.rsNum = rsNum;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setPartNumber(String partNum)
	{
		this.partNum = partNum;
	}

	public String getPartNumber()
	{
		return this.partNum;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payables;

/**
 *
 * @author Janine
 */
public class Payment
{
	private String purchase_transaction_id, notes, date, approved_by,
			received_by, prepared_by, payment_type, credit_memo_id,
			approved_date, received_date, prepared_date, payment_id;
	private float amount;

	public Payment(String pt, float amount, String received_date,
			String approved_date, String prepared_date, String received_by,
			String approved_by, String prepared_by, String payment_type,
			String credit_memo_id, String date, String notes)
	{
		this.amount = amount;
		this.purchase_transaction_id = pt;
		this.notes = notes;
		this.date = date;
		this.approved_by = approved_by;
		this.received_by = received_by;
		this.prepared_by = prepared_by;
		this.payment_type = payment_type;
		this.credit_memo_id = credit_memo_id;
		this.approved_date = approved_date;
		this.received_date = received_date;
		this.prepared_date = prepared_date;

	}

	public Payment()
	{

	}

	/**
	 * @return the purchase_transaction_id
	 */
	public String getPurchase_transaction_id()
	{
		return purchase_transaction_id;
	}

	/**
	 * @param purchase_transaction_id
	 *            the purchase_transaction_id to set
	 */
	public void setPurchase_transaction_id(String purchase_transaction_id)
	{
		this.purchase_transaction_id = purchase_transaction_id;
	}

	/**
	 * @return the amount
	 */
	public float getAmount()
	{
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(float amount)
	{
		this.amount = amount;
	}

	/**
	 * @return the notes
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	/**
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * @return the approved_by
	 */
	public String getApproved_by()
	{
		return approved_by;
	}

	/**
	 * @param approved_by
	 *            the approved_by to set
	 */
	public void setApproved_by(String approved_by)
	{
		this.approved_by = approved_by;
	}

	/**
	 * @return the received_by
	 */
	public String getReceived_by()
	{
		return received_by;
	}

	/**
	 * @param received_by
	 *            the received_by to set
	 */
	public void setReceived_by(String received_by)
	{
		this.received_by = received_by;
	}

	/**
	 * @return the prepared_by
	 */
	public String getPrepared_by()
	{
		return prepared_by;
	}

	/**
	 * @param prepared_by
	 *            the prepared_by to set
	 */
	public void setPrepared_by(String prepared_by)
	{
		this.prepared_by = prepared_by;
	}

	/**
	 * @return the payment_type
	 */
	public String getPayment_type()
	{
		return payment_type;
	}

	/**
	 * @param payment_type
	 *            the payment_type to set
	 */
	public void setPayment_type(String payment_type)
	{
		this.payment_type = payment_type;
	}

	/**
	 * @return the credit_memo_id
	 */
	public String getCredit_memo_id()
	{
		return credit_memo_id;
	}

	/**
	 * @param credit_memo_id
	 *            the credit_memo_id to set
	 */
	public void setCredit_memo_id(String credit_memo_id)
	{
		this.credit_memo_id = credit_memo_id;
	}

	/**
	 * @return the approved_date
	 */
	public String getApproved_date()
	{

		if (approved_date.equals(""))
			return "NULL";
		else
			return "'" + approved_date + "'";

	}

	/**
	 * @param approved_date
	 *            the approved_date to set
	 */
	public void setApproved_date(String approved_date)
	{
		this.approved_date = approved_date;
	}

	/**
	 * @return the received_date
	 */
	public String getReceived_date()
	{
		if (received_date.equals(""))
			return "NULL";
		else
			return "'" + received_date + "'";
	}

	/**
	 * @param received_date
	 *            the received_date to set
	 */
	public void setReceived_date(String received_date)
	{
		this.received_date = received_date;
	}

	/**
	 * @return the prepared_date
	 */
	public String getPrepared_date()
	{
		if (prepared_date.equals(""))
			return "NULL";
		else
			return "'" + prepared_date + "'";
	}

	/**
	 * @param prepared_date
	 *            the prepared_date to set
	 */
	public void setPrepared_date(String prepared_date)
	{
		this.prepared_date = prepared_date;
	}

	/**
	 * @return the payment_id
	 */
	public String getPayment_id()
	{
		return payment_id;
	}

	/**
	 * @param payment_id
	 *            the payment_id to set
	 */
	public void setPayment_id(String payment_id)
	{
		this.payment_id = payment_id;
	}

	public String getRDate()
	{
		if (received_date == null)
			return "";
		else
			return received_date;
	}

	public String getADate()
	{
		if (approved_date == null)
			return "";
		else
			return approved_date;
	}

	public String getPDate()
	{
		if (prepared_date == null)
			return "";
		else
			return prepared_date;
	}
}

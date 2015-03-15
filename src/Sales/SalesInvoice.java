package Sales;

import java.util.ArrayList;

public class SalesInvoice
{

    private String sales_invoice_id;
    private int company_id;
    private String date;
    private float original_amount;
    private String po_num;
    private String ordered_by;
    private String sales_person;
    private String delivered_by;
    private String delivery_notes;
    private String delivery_receipt_num;
    private float discount;
    private float current_balance;
    private String status;
    private String pwd_id_number_notes;
    private ArrayList<SILineItem> list;

    public SalesInvoice(String sales_invoice_id, int company_id, String date, float original_amount, String po_num, String ordered_by, String sales_person, String delivered_by, String delivery_notes, String delivery_receipt_num, float discount, float current_balance, String status, String pwd_id_number_notes, ArrayList<SILineItem> list)
    {
        this.sales_invoice_id = sales_invoice_id;
        this.company_id = company_id;
        this.date = date;
        this.original_amount = original_amount;
        this.po_num = po_num;
        this.ordered_by = ordered_by;
        this.sales_person = sales_person;
        this.delivered_by = delivered_by;
        this.delivery_notes = delivery_notes;
        this.delivery_receipt_num = delivery_receipt_num;
        this.discount = discount;
        this.current_balance = current_balance;
        this.status = status;
        this.pwd_id_number_notes = pwd_id_number_notes;
        this.list = list;
    }

    public String getSales_invoice_id()
    {
        return sales_invoice_id;
    }

    public void setSales_invoice_id(String sales_invoice_id)
    {
        this.sales_invoice_id = sales_invoice_id;
    }

    public int getCompany_id()
    {
        return company_id;
    }

    public void setCompany_id(int company_id)
    {
        this.company_id = company_id;
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

    public String getOrdered_by()
    {
        return ordered_by;
    }

    public void setOrdered_by(String ordered_by)
    {
        this.ordered_by = ordered_by;
    }

    public String getSales_person()
    {
        return sales_person;
    }

    public void setSales_person(String sales_person)
    {
        this.sales_person = sales_person;
    }

    public String getDelivered_by()
    {
        return delivered_by;
    }

    public void setDelivered_by(String delivered_by)
    {
        this.delivered_by = delivered_by;
    }

    public String getDelivery_notes()
    {
        return delivery_notes;
    }

    public void setDelivery_notes(String delivery_notes)
    {
        this.delivery_notes = delivery_notes;
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

    public String getPwd_id_number_notes()
    {
        return pwd_id_number_notes;
    }

    public void setPwd_id_number_notes(String pwd_id_number_notes)
    {
        this.pwd_id_number_notes = pwd_id_number_notes;
    }

    public ArrayList<SILineItem> getItems()
    {
        return list;
    }

    public void setList(ArrayList<SILineItem> list)
    {
        this.list = list;
    }

    public void addItem(SILineItem line)
    {
        list.add(line);
    }

    public void removeItem(SILineItem line)
    {
        list.remove(line);
    }

}

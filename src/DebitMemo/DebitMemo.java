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

import Classes.Company;
import java.util.ArrayList;
class DebitMemo
{
    private String debit_memo_id;
    private String date;
    private float total_amount;
    private String receipt_type;
    private String receipt_number;
    private String approved_by;
    private String received_by;
    private String approved_date;
    private String notes;
    private int status;
    private String type;
    private ArrayList<DMLineItem> list;
    private Company company;
    private int company_id;

    public DebitMemo()
    {
        this.debit_memo_id = "";
        this.date = "";
        this.total_amount = 0;
        this.receipt_type = "";
        this.receipt_number = "";
        this.approved_by = "";
        this.received_by = "";
        this.approved_date = "";
        this.notes = "";
        this.status = 0;
        this.type = "";
        this.company_id = 0;
        this.list = new ArrayList<>();
    }

    public DebitMemo(String debit_memo_id,int company_id, String date, float total_amount, String receipt_type, String receipt_number, String approved_by, String received_by, String approved_date, String notes, int status, String type, ArrayList<DMLineItem> list)
    {
        this.debit_memo_id = debit_memo_id;
        this.date = date;
        this.total_amount = total_amount;
        this.receipt_type = receipt_type;
        this.receipt_number = receipt_number;
        this.approved_by = approved_by;
        this.received_by = received_by;
        this.approved_date = approved_date;
        this.notes = notes;
        this.status = status;
        this.type = type;
        this.list = list;
        this.company_id = company_id;
    }

    public void setCompany_id(int company_id)
    {
        this.company_id = company_id;
    }

    public String getDebit_memo_id()
    {
        return debit_memo_id;
    }

    public void setDebit_memo_id(String debit_memo_id)
    {
        this.debit_memo_id = debit_memo_id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public float getTotal_amount()
    {
        return total_amount;
    }

    public void setTotal_amount(float total_amount)
    {
        this.total_amount = total_amount;
    }

    public String getReceipt_type()
    {
        return receipt_type;
    }

    public void setReceipt_type(String receip_type)
    {
        this.receipt_type = receip_type;
    }

    public String getReceipt_number()
    {
        return receipt_number;
    }

    public void setReceipt_number(String receipt_number)
    {
        this.receipt_number = receipt_number;
    }

    public String getApproved_by()
    {
        if(approved_date==null)
            return "NULL";
        else
        return approved_date;
    }

    public void setApproved_by(String approved_by)
    {
        this.approved_by = approved_by;
    }

    public String getReceived_by()
    {
        return received_by;
    }

    public void setReceived_by(String received_by)
    {
        this.received_by = received_by;
    }

    public String getApproved_date()
    {
        return approved_date;
    }

    public void setApproved_date(String approved_date)
    {
        this.approved_date = approved_date;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
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

    public ArrayList<DMLineItem> getList()
    {
        return list;
    }

    public void setList(ArrayList<DMLineItem> list)
    {
        this.list = list;
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }

   public void addItem(DMLineItem line)
    {
        list.add(line);
    }

    public void removeItem(DMLineItem line)
    {
        list.remove(line);
    }

    public ArrayList<DMLineItem> getItems()
    {
        return list;
    }

    public int getCompany_id()
    {
        return this.company_id;
    }
    
    public String getCompany_name()
    {
        return company.getName();
    }
    
}

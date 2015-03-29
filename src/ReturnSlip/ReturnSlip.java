package ReturnSlip;
import java.util.ArrayList;


public class ReturnSlip
{
    private String return_slip_id;
    private String date;
    private float total_amount;
    private int company_id;
    private int purchase_transaction_num;
    private String purchase_order_num;
    private String returned_by;
    private String returned_date;
    private String approved_by;
    private String approved_date;
    private String received_by;
    private String received_date;
    private String notes;
    private String type;
    private ArrayList<RSLineItem> list;
    
    public ReturnSlip()
    {
        this.return_slip_id = "";
        this.date = "";
        this.total_amount = 0;
        this.company_id = 0;
        this.purchase_transaction_num = 0;
        this.returned_by = "";
        this.returned_date = "";
        this.approved_by = "";
        this.approved_date="";
        this.received_by = "";
        this.received_date="";
        this.notes = "";
        this.type="";
        
        this.list = new ArrayList<>();;
    }

    public ReturnSlip( String return_slip_id, int company_id, String date, float total_amount, int purchase_transaction_num, String purchase_order_num, String returned_by, String returned_date, String approved_by, String approved_date, String received_by, String received_date, String notes, String type, ArrayList<RSLineItem> list)
    {
        this.return_slip_id = return_slip_id;
        this.date = date;
        this.total_amount = total_amount;
        this.company_id = company_id;
        this.purchase_transaction_num = purchase_transaction_num;
        this.purchase_order_num = purchase_order_num;
        this.returned_by = returned_by;
        this.returned_date = returned_date;
        this.approved_by = approved_by;
        this.approved_date = approved_date;
        this.received_by = received_by;
        this.received_date = received_date;
        this.notes = notes;
        this.type = type;
        this.list = list;
    }

    public String getReturn_slip_id()
    {
        return return_slip_id;
    }

    public void setReturn_slip_id(String return_slip_id)
    {
        this.return_slip_id = return_slip_id;
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

    public int getCompany_id()
    {
        return company_id;
    }

    public void setCompany_id(int company_id)
    {
        this.company_id = company_id;
    }

    public int getPurchase_transaction_num()
    {
        return purchase_transaction_num;
    }

    public void setPurchase_transaction_num(int purchase_transaction_num)
    {
        this.purchase_transaction_num = purchase_transaction_num;
    }
    
    public String getPurchase_order_num()
    {
        return purchase_order_num;
    }

    public void setPurchase_order_num(String purchase_order_num)
    {
        this.purchase_order_num = purchase_order_num;
    }

    public String getReturned_by()
    {
        return returned_by;
    }

    public void setReturned_by(String returned_by)
    {
        this.returned_by = returned_by;
    }

    public String getReturned_date()
    {
        if(returned_date.equals(""))
            return "NULL";
        else
            return "'"+returned_date+"'";
    }

    public void setReturned_date(String returned_date)
    {
        this.returned_date = returned_date;
    }

    public String getApproved_by()
    {
        return approved_by;
    }

    public void setApproved_by(String approved_by)
    {
        this.approved_by = approved_by;
    }

    public String getApproved_date()
    {
        if(approved_date.equals(""))
            return "NULL";
        else
        return "'"+approved_date+"'";
    }

    public void setApproved_date(String approved_date)
    {
        this.approved_date = approved_date;
    }

    public String getReceived_by()
    {
        return received_by;
    }

    public void setReceived_by(String received_by)
    {
        this.received_by = received_by;
    }

    public String getReceived_date()
    {
        if(received_date.equals(""))
            return "NULL";
        else
            return "'"+received_date+"'";
    }

    public void setReceived_date(String received_date)
    {
        this.received_date = received_date;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ArrayList<RSLineItem> getItems()
    {
        return list;
    }

    public void setList(ArrayList<RSLineItem> list)
    {
        this.list = list;
    }

   
}

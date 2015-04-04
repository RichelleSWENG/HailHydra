package AcknowledgementReceipt;

import Classes.Company;
import Classes.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Database.DBConnection;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class AckReceiptModel
{

    protected Connection db;
    protected Statement statement;

    private int itemCount = 0;
    private ArrayList<Company> customers;
    private ArrayList<ARLineItem> items;
    private ARLineItemModel arLineItemModel;

    public AckReceiptModel(DBConnection db)
    {
        this.db = db.getConnection();
        customers = new ArrayList<>();
        items = new ArrayList<>();
        arLineItemModel = new ARLineItemModel(db);
    }

    public ResultSet getDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT (SELECT name FROM company WHERE company.company_id=acknowledgementreceipt.company_id),acknowledgementreceipt.address,acknowledgement_receipt_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status WHERE acknowledgement_receipt_id LIKE '" + ID + "'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT company.name,date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id";
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
    public ResultSet getAllDetailbyDate(String startDate,String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT company.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    public ResultSet searchDetail(String field, String filter, String startDate, String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "";
            if (filter.equalsIgnoreCase("name"))
            {
                    sql = "SELECT company.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id AND company.name LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            } else if(filter.equalsIgnoreCase("acknowledgement number"))
            {
                    sql = "SELECT company.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt WHERE acknowledgementreceipt.company_id=company.company_id AND acknowledgement_receipt_id LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            }else if(filter.equalsIgnoreCase("part number"))
            {
                    sql = "SELECT company.name, date,acknowledgementreceipt.acknowledgement_receipt_id,original_amount,current_balance FROM company,acknowledgementreceipt,arlineitem WHERE acknowledgementreceipt.company_id=company.company_id AND arlineitem.acknowledgement_receipt_id=acknowledgementreceipt.acknowledgement_receipt_id AND arlineitem.part_num LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            }
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public void addDetail(AcknowledgementReceipt obj)
    {
 
        AcknowledgementReceipt ar = obj;
        try
        {
        
            statement = db.createStatement();
            String sql = "INSERT INTO acknowledgementreceipt(acknowledgement_receipt_id,company_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status) VALUES('" + ar.getAcknowledgement_receipt_id() + "','" + ar.getCompany_id()  + "','" + ar.getDate() + "','" + ar.getPo_num() + "','" + ar.getDelivery_receipt_num() + "','" + ar.getSales_person() + "','" + ar.getOrdered_by() + "','" + ar.getDelivered_by() + "','" + ar.getDelivery_notes() + "','" + ar.getDiscount() + "','" + ar.getOriginal_amount() + "','" + ar.getCurrent_balance() + "','" + ar.getStatus()+"')";
            System.out.println(sql);
            statement.executeUpdate(sql);
            int i;
            for (i = 0; i < ar.getItems().size(); i++)
            {
                arLineItemModel.addDetail(ar.getItems().get(i));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void editDetail(Object obj)
    {
        AcknowledgementReceipt ar = (AcknowledgementReceipt) obj;
        try
        {
            statement = db.createStatement();
            String sql = "UPDATE acknowledgementreceipt SET acknowledgementreceipt.company_id='" + ar.getCompany_id() + "',date='" + ar.getDate() + "',po_num='" + ar.getPo_num() + "',delivery_receipt_num='" + ar.getDelivery_receipt_num() + "',sales_person='" + ar.getSales_person() + "',ordered_by='" + ar.getOrdered_by() + "',delivered_by='" + ar.getDelivered_by() + "',delivery_notes='" + ar.getDelivery_notes() + "',discount='" + ar.getDiscount() + "',original_amount='" + ar.getOriginal_amount() + "',current_balance='" + ar.getCurrent_balance() + "' WHERE acknowledgement_receipt_id LIKE '" + ar.getAcknowledgement_receipt_id() + "'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void deleteDetail(String ID)
    {
        try
        {
            statement = db.createStatement();
            String sql = "DELETE FROM acknowledgementreceipt WHERE acknowledgement_receipt_id='" + ID + "'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    public int getItemcount()
    {
        return this.itemCount;
    }

    public ArrayList<Company> getCustomers()
    {
        customers = new ArrayList<>();
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM company WHERE type LIKE '%customer%'";
            rs = statement.executeQuery(sql);
            Company tempCustomer;
            while (rs.next())
            {
                tempCustomer = new Company();
                tempCustomer.setId(rs.getInt("company_id"));
                tempCustomer.setName(rs.getString("name"));
                tempCustomer.setAddressLoc(rs.getString("address_location"));
                tempCustomer.setAddressCity(rs.getString("address_city"));
                tempCustomer.setAddressCountry(rs.getString("address_country"));
                tempCustomer.setPostalCode(rs.getString("address_postal_code"));
                tempCustomer.setPhone1(rs.getString("phone1"));
                tempCustomer.setPhone2(rs.getString("phone2"));
                tempCustomer.setPhone3(rs.getString("phone3"));
                tempCustomer.setFaxNum(rs.getString("fax_num"));
                tempCustomer.setWebsite(rs.getString("website"));
                tempCustomer.setEmail(rs.getString("email"));
                tempCustomer.setContactPerson(rs.getString("contact_person"));
                tempCustomer.setStatus(rs.getString("status"));
                tempCustomer.setCreditLimit(rs.getFloat("credit_limit"));
                tempCustomer.setTerms(rs.getInt("terms"));
                tempCustomer.setType(rs.getString("type"));
                customers.add(tempCustomer);
            }
            System.out.println(customers.size());

        } catch (Exception e)
        {
            e.getMessage();
        }
        return customers;
    }
    
     public ArrayList<ARLineItem> getItems(String customerType)
    {
        items = new ArrayList<>();
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM item";
            rs = statement.executeQuery(sql);
            ARLineItem tempItem;
            while (rs.next())
            {
                tempItem = new ARLineItem();
                tempItem.setPartNum(rs.getString("part_num"));
                tempItem.setDescription(rs.getString("description"));
                
                if (customerType.equals("Walk-in Customer"))
                    tempItem.setPrice(rs.getFloat("walk_in_price"));
                if (customerType.equals("Retail Customer"))
                    tempItem.setPrice(rs.getFloat("traders_price"));
                if (customerType.equals("Sister Company Customer"))
                    tempItem.setPrice(rs.getFloat("sister_company_price"));
                items.add(tempItem);
                
                tempItem.setMinimum(rs.getInt("stock_minimum"));
                tempItem.setQuantityFunc(rs.getInt("quantity_functional"));
            }

        } catch (Exception e)
        {
            e.getMessage();
        }
        return items;
    }
    
    public Company getCustomer(int index)
    {
        return customers.get(index);
    }
    
    public Item getItem(int index)
    {
        return items.get(index);
    }
    
    public int getAvailQuantity(int index)
    {
        return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
    }
    
    public TableModel myModel(ResultSet rs)
    {     
        TableModel model = DbUtils.resultSetToTableModel(rs);
        return model;
    }
    
    public ResultSet getMinYear()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT MIN(YEAR(date)) FROM acknowledgementreceipt";
            rs = statement.executeQuery(sql);
            
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
     public ResultSet getMaxYear()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT MAX(YEAR(date)) FROM acknowledgementreceipt";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
     
     public AcknowledgementReceipt getAR(String ID)
     {
        ArrayList<ARLineItem> stuff; 
        AcknowledgementReceipt rcpt = null;
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * WHERE acknowledgement_receipt_id = '" + ID + "'";
            rs = statement.executeQuery(sql);
            
            if (rs.next())
            {
                user = new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("user_type"), rs.getInt("manager_id"), rs.getString("password"));
            }
            
            if (rcpt != null)
            {
                query = "SELECT org_id, org_name, org_password FROM manager m, organization o WHERE m.user_id = '" + user.getUser_id() + "' AND o.org_id = m.org_id";
                statement = db.getConnection().prepareStatement(query);
                rs = statement.executeQuery();
                while (rs.next())
                {
                    user.addOrg(new Organization(rs.getInt("org_id"), rs.getString("org_name"), rs.getString("org_password")));
                }
            }
        } 
        
        catch (Exception e)
        {
            e.getMessage();
        }
        return rcpt;
     }
}

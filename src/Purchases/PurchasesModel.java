package Purchases;

import Classes.Company;
import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class PurchasesModel 
{
    protected Connection db;
    protected Statement statement;
    private int itemCount = 0;
    
    private ArrayList<Company> suppliers;
    private ArrayList<PTLineItem> items;
    private PTLineItemModel ptLineItemModel;
    
    public PurchasesModel(DBConnection db)
    {
        this.db = db.getConnection();
    }

    public ResultSet getDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "";
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
            String sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id=purchasetransaction.company_id";
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
            String sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id=purchasetransaction.company_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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
    
    public ResultSet searchDetail(String field, String filter,String startDate,String endDate)
    {
         ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql="";
            if(filter.equalsIgnoreCase("name"))
            {
                    sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id= purchasetransaction.company_id AND company.name LIKE '%" + field + "%' AND purchasetransaction.date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            }	
            else if(filter.equalsIgnoreCase("transaction number"))
            {
                    sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id= purchasetransaction.company_id AND purchase_transaction_id LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                    System.out.println(sql);
            }
            else if(filter.equalsIgnoreCase("part number"))
            {
                    sql = "SELECT company.name, date, purchasetransaction.purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction,ptlineitem WHERE company.company_id= purchasetransaction.company_id AND purchasetransaction.purchase_transaction_id=ptlineitem.purchase_transaction_id AND part_num LIKE '%"+field+"%' AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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

     public void addDetail(PurchaseTransaction obj)
     {
 
        PurchaseTransaction pt = obj;
        try
        {
        
            statement = db.createStatement();
            String sql = "INSERT INTO purchasetransaction(purchase_transaction_id,company_id,date,original_amount,discount,ref_sales_invoice_num, ordered_by, po_num, received_by, rceiving_notes, vat, delivery_receipt_num, current_balance, status) VALUES('" + pt.getPurchase_transaction_id() + "','" + pt.getCompany_id()  + "','" + pt.getDate() + "','" + pt.getOriginal_amount() + "','" + pt.getDiscount() + "','" + pt.getRef_sales_invoice_num() + "','" + pt.getOrdered_by() + "','" + pt.getPo_num() + "','" + pt.getReceived_by() + "','" + pt.getReceiving_notes() + "','" + pt.getVat() + "','" + pt.getDelivery_receipt_num() + "','" + pt.getCurrent_balance()+ "','" + pt.getStatus() + "')";
            System.out.println(sql);
            statement.executeUpdate(sql);
            int i;
            for (i = 0; i < pt.getItems().size(); i++)
            {
                ptLineItemModel.addDetail(pt.getItems().get(i));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void editDetail(ArrayList list)
    {
        try
        {
            statement = db.createStatement();
            String sql = "";
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
            String sql = "DELETE FROM purchasetransaction WHERE purchase_transaction_id='" + ID + "'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
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
            String sql = "SELECT MIN(YEAR(date)) FROM purchasetransaction";
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
            String sql = "SELECT MAX(YEAR(date)) FROM purchasetransaction";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
     
    public int getItemcount()
    {
        return this.itemCount;
    }
    
    public ArrayList<Company> getSuppliers()
    {
        suppliers = new ArrayList<>();
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM company WHERE type LIKE '%supplier%'";
            rs = statement.executeQuery(sql);
            Company tempSupplier;
            while (rs.next())
            {
                tempSupplier = new Company();
                tempSupplier.setId(rs.getInt("company_id"));
                tempSupplier.setName(rs.getString("name"));
                tempSupplier.setAddressLoc(rs.getString("address_location"));
                tempSupplier.setAddressCity(rs.getString("address_city"));
                tempSupplier.setAddressCountry(rs.getString("address_country"));
                tempSupplier.setPostalCode(rs.getString("address_postal_code"));
                tempSupplier.setPhone1(rs.getString("phone1"));
                tempSupplier.setPhone2(rs.getString("phone2"));
                tempSupplier.setPhone3(rs.getString("phone3"));
                tempSupplier.setFaxNum(rs.getString("fax_num"));
                tempSupplier.setWebsite(rs.getString("website"));
                tempSupplier.setEmail(rs.getString("email"));
                tempSupplier.setContactPerson(rs.getString("contact_person"));
                tempSupplier.setStatus(rs.getString("status"));
                tempSupplier.setCreditLimit(rs.getFloat("credit_limit"));
                tempSupplier.setTerms(rs.getInt("terms"));
                tempSupplier.setType(rs.getString("type"));
                suppliers.add(tempSupplier);
            }
            System.out.println(suppliers.size());

        } catch (Exception e)
        {
            e.getMessage();
        }
        return suppliers;
    }
    
     public ArrayList<PTLineItem> getItems()
    {
        items = new ArrayList<>();
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM item";
            rs = statement.executeQuery(sql);
            PTLineItem tempItem;
            while (rs.next())
            {
                tempItem = new PTLineItem();
                tempItem.setPartNum(rs.getString("part_num"));
                tempItem.setDescription(rs.getString("description"));
                tempItem.setPrice(rs.getFloat("last_cost"));
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
    
    public Company getSupplier(int index)
    {
        return suppliers.get(index);
    }
    
    public PTLineItem getItem(int index)
    {
        return items.get(index);
    }
    
    public int getAvailQuantity(int index)
    {
        return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
    }
    
}

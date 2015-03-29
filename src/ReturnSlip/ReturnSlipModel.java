package ReturnSlip;

import AcknowledgementReceipt.ARLineItem;
import AcknowledgementReceipt.ARLineItemModel;
import AcknowledgementReceipt.AcknowledgementReceipt;
import Classes.Company;
import Classes.Item;
import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class ReturnSlipModel {
    protected Connection db;
    protected Statement statement;
    private int itemCount = 0;
    private ArrayList<Company> suppliers;
    private ArrayList<String> ptnums;
    private ArrayList<RSLineItem> items;
    private RSLineItemModel rsLineItemModel;
    
    public ReturnSlipModel(DBConnection db)
    {
        this.db = db.getConnection();
        suppliers = new ArrayList<>();
        items = new ArrayList<>();
        rsLineItemModel = new RSLineItemModel(db);
    }
    
    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id ";
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
            String sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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
                    sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"' AND company.name LIKE '%"+field+"%'";
            }	
            else if(filter.equalsIgnoreCase("return slip number"))
            {
                    sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"' AND returnslip.return_slip_id LIKE '%"+field+"%'";
            }
            else if(filter.equalsIgnoreCase("part number"))
            {
                    sql = "SELECT date, returnslip.return_slip_id, company.name, part_num,quantity,line_total FROM company,rslineitem,returnslip WHERE returnslip.company_id=company.company_id AND returnslip.return_slip_id=rslineitem.return_slip_id AND date BETWEEN '"+startDate+"' AND '"+endDate+"' AND part_num LIKE '%"+field+"%'";
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
            String sql = "SELECT MIN(YEAR(date)) FROM returnslip";
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
            String sql = "SELECT MAX(YEAR(date)) FROM returnslip";
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
    
       public ArrayList<RSLineItem> getItems(String supplier)
    {
        items = new ArrayList<>();
        supplier = "Supplier";
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM item";
            rs = statement.executeQuery(sql);
            RSLineItem tempItem;
            while (rs.next())
            {
                tempItem = new RSLineItem();
                tempItem.setPartNum(rs.getString("part_num"));
                tempItem.setDescription(rs.getString("description"));
                
                tempItem.setPrice(0);
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
    
    public Item getItem(int index)
    {
        return items.get(index);
    }
    
    public int getAvailQuantity(int index)
    {
        return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
    }
    
     
     public ReturnSlip getRS(String ID)
     {
        ArrayList<RSLineItem> stuff; 
        ReturnSlip slip = new ReturnSlip();
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * WHERE return_slip_id = '" + ID + "'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return slip;
     }

    public ArrayList<String> getPurchaseTransactionNumbers()
    {
        ptnums = new ArrayList<>();
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT purchase_transaction_id FROM purchasetransaction";
            rs = statement.executeQuery(sql);
            String tempPTnum;
            while (rs.next())
            {
                tempPTnum = rs.getString("purchase_transaction_id");
                ptnums.add(tempPTnum);
            }
            System.out.println(ptnums.size());

        } catch (Exception e)
        {
            e.getMessage();
        }
        return ptnums;
    }

    public void addDetail(ReturnSlip slp)
    {
        ReturnSlip ar = slp;
        try
        {
        
            statement = db.createStatement();
            String sql = "INSERT INTO returnslip(return_slip_id,date,total_amount,company_id,purchase_transaction_num,returned_by,returned_date,approved_by,approved_date,received_by,received_date,type) VALUES('"+ar.getReturn_slip_id()+"','"+ar.getDate()+"','"+ar.getTotal_amount()+"','"+ar.getCompany_id()+"','"+ar.getPurchase_transaction_num()+"','"+ar.getReturned_by()+"',"+ar.getReturned_date()+",'"+ar.getApproved_by()+"',"+ar.getApproved_date()+",'"+ar.getReceived_by()+"',"+ar.getReceived_date()+",'"+ar.getType()+"')";
            statement.executeUpdate(sql);
            int i;
            for (i = 0; i < ar.getItems().size(); i++)
            {
                rsLineItemModel.addDetail(ar.getItems().get(i));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getPurchaseTransactionNumbers(String PTNum)
    {
        String PO = null;
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT po_num FROM purchasetransaction WHERE purchase_transaction_id ='"+PTNum+"'";

            rs = statement.executeQuery(sql);
            String tempPO;

            while (rs.next())
            {
                tempPO = rs.getString("po_num");
                PO=tempPO;
            }

        } catch (Exception e)
        {
            e.getMessage();
        }
        
        return PO;
    }
}

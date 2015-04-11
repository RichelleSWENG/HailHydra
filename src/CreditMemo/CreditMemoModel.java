package CreditMemo;

import Classes.Company;
import Database.DBConnection;
import ReturnSlip.RSLineItem;
import ReturnSlip.ReturnSlip;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class CreditMemoModel 
{
    protected Connection db;
    protected Statement statement;
    private int itemCount=0;
    private CreditMemo cm;
    
    public CreditMemoModel(DBConnection db)
    {
        this.db = db.getConnection();
    }
    
    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,creditmemo.total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id";
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
            String sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,creditmemo.total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"'";
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
                    sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,creditmemo.total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"' AND company.name LIKE '%"+field+"%'";
            }	
            else if(filter.equalsIgnoreCase("credit memo number"))
            {
                    sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,creditmemo.total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"' AND creditmemo.credit_memo_id LIKE '%"+field+"%'";
            }
            else if(filter.equalsIgnoreCase("return slip number"))
            {
                    sql = "SELECT creditmemo.date, creditmemo.credit_memo_id, company.name, creditmemo.return_slip_id,creditmemo.total_amount FROM company,returnslip,creditmemo WHERE returnslip.company_id=company.company_id AND creditmemo.return_slip_id=returnslip.return_slip_id AND creditmemo.date BETWEEN '"+startDate+"' AND '"+endDate+"' AND creditmemo.return_slip_id LIKE '%"+field+"%'";
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
            String sql = "SELECT MIN(YEAR(date)) FROM creditmemo";
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
            String sql = "SELECT MAX(YEAR(date)) FROM creditmemo";
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

    public void addCreditMemo(String cmID, String date, String rsNum, int status, String type, String partNum, String total)
    {
       
        try
        {
        
            statement = db.createStatement();
            String sql = "INSERT INTO creditmemo(credit_memo_id,date,return_slip_id,part_number,status,type,total_amount) VALUES('"+cmID+"','"+date+"','"+rsNum+"','"+partNum+"','"+status+"', '"+type+"', '"+total+"')";
            statement.executeUpdate(sql);
           
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public CreditMemo getCM(String ID)
    {


        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM creditmemo WHERE credit_memo_id = '" + ID + "'";
            rs = statement.executeQuery(sql);

            if (rs.next())
            {
                cm = new CreditMemo();
                cm.setId(rs.getString("credit_memo_id"));
                cm.setDate(rs.getString("date"));
                cm.setRsNum(rs.getString("return_slip_id"));
                cm.setStatus(rs.getInt("status"));
                cm.setType(rs.getString("type"));
                cm.setPartNumber(rs.getString("part_number"));
            }


        } catch (Exception e)
        {
            e.getMessage();
        }

        return cm;
    }

    public String getLastCMID()
    {
        ResultSet rs = null;
            String CMid = null;
            try
            {
                statement = db.createStatement();
                String sql = "SELECT credit_memo_id FROM creditmemo ORDER BY credit_memo_id DESC LIMIT 1;";
                rs = statement.executeQuery(sql);
           
            while (rs.next())
            {
                String tempID = rs.getString("credit_memo_id");
                CMid=tempID;
            }
             } catch (Exception e)
            {
                e.getMessage();
            }
            if(CMid==null)
                return "null";
            else
                return CMid;
    }

    public void updateFromDefec(String quantity, String partNum, int status)
    {
        int Qfunc = getQuantityFunc(partNum);
        int Qdef = getQuantityDef(partNum);
        if(status == 1)        
            Qdef = Qdef - Integer.parseInt(quantity);
        else if(status == 0)
            Qfunc = Qfunc - Integer.parseInt(quantity);    
        
         try
        {
        
            statement = db.createStatement();
            String sql = "UPDATE item SET quantity_functional = '"+Qfunc+"', quantity_defective = '"+Qdef+"' WHERE part_num ='"+partNum+"'";
            statement.executeUpdate(sql);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    

    public void updateFromType(String quantity, String partNum, String type)
    {
        int Qfunc = getQuantityFunc(partNum);
        int Qdef = getQuantityDef(partNum);
        if(type.equals("Replacement"))
            Qfunc = Qfunc + Integer.parseInt(quantity);
          try
        {
        
            statement = db.createStatement();
            String sql = "UPDATE item SET quantity_functional = '"+Qfunc+"', quantity_defective = '"+Qdef+"' WHERE part_num ='"+partNum+"'";
            statement.executeUpdate(sql);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int getQuantityFunc(String partNum)
    {
        
        ResultSet rs = null;
        String quantity = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT quantity_functional FROM item WHERE part_num='"+partNum+"'";
            rs = statement.executeQuery(sql);
            String temp;

            while (rs.next())
            {
                temp = rs.getString("quantity_functional");
                quantity=temp;
            }
        } catch (Exception e)
        {
            e.getMessage();
        }
        return Integer.parseInt(quantity);
   
    }

    public int getQuantityDef(String partNum)
    {
         ResultSet rs = null;
         String quantity = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT quantity_defective FROM item WHERE part_num='"+partNum+"'";
            rs = statement.executeQuery(sql);
            String temp;
            while (rs.next())
            {
                temp = rs.getString("quantity_defective");
                quantity=temp;
            }
        } catch (Exception e)
        {
            e.getMessage();
        }
        return Integer.parseInt(quantity);
    }

}

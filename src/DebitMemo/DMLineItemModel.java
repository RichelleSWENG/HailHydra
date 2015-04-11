/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DebitMemo;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Kingston
 */
class DMLineItemModel
{

   protected Connection db;
	protected Statement statement;

    public DMLineItemModel(DBConnection db)
    {
        this.db=db.getConnection();
    }

    public ResultSet getDetail(String ID,String partnum)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT quantity,part_num,unit_price,line_total FROM dmlineitem WHERE debit_memo_id='"+ID+"' AND part_num='"+partnum+"'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet getAllDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT quantity,part_num,unit_price,line_total FROM dmlineitem WHERE debit_memo_id='"+ID+"'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public void addDetail(Object obj)
    {
    	DMLineItem lineitem=(DMLineItem)obj;
        try
        {
            statement = db.createStatement();
            String sql = "INSERT INTO dmlineitem(debit_memo_id,quantity,part_num,unit_price,line_total) VALUES('"+lineitem.getDebit_memo_id()+"','"+lineitem.getQuantity()+"','"+lineitem.getPartNum()+"','"+lineitem.getUnit_price()+"','"+lineitem.getLine_total()+"')";
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void editDetail(Object obj)
    {
    	DMLineItem lineitem=(DMLineItem)obj;
        try
        {
            statement = db.createStatement();
            String sql = "UPDATE dmlineitem SET quantity='"+lineitem.getQuantity()+"',unit_price='"+lineitem.getUnit_price()+",line_total='"+lineitem.getLine_total()+"' WHERE acknowledgement_receipt_id='"+lineitem.getDebit_memo_id()+"' AND part_num='"+lineitem.getPartNum()+"'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void deleteDetail(String ID,String partnum)
    {
        try
        {
            statement = db.createStatement();
            String sql = "DELETE FROM dmlineitem WHERE debit_memo_id='"+ID+"' AND part_num='"+partnum+"'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }
    public void deleteAllDetail(String ID)
    {
    	try
        {
            statement = db.createStatement();
            String sql = "DELETE FROM dmlineitem WHERE debit_memo_id='"+ID+"'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }
    
}

package AcknowledgementReceipt;

import Database.DBConnection;
import HailHydra.Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jolo Simeon
 */
public class AcknowledgementReceiptModel extends Model
{

    public AcknowledgementReceiptModel(DBConnection db)
    {
        super(db);
    }

    @Override
    public ResultSet getDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql = "SELECT (SELECT name FROM company WHERE company.company_id=acknowledgementreceipt.company_id),(SELECT address_location FROM company WHERE company.company_id=acknowledgementreceipt.company_id),acknowledgement_receipt_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status WHERE acknowledgement_receipt_id LIKE '"+ID+"'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    @Override
    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql = "SELECT customer.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    @Override
    public ResultSet searchDetail(String field, String filter)
    {
        ResultSet rs = null;
        try
        {
            statement = con.createStatement();
            String sql="";
            if(filter=="name")
            	sql = "SELECT customer.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id and customer.name LIKE '%"+field+"%'";
            else
            	sql="SELECT customer.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id and acknowledgement_receipt_id LIKE '%"+field+"%'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    @Override
    public void addDetail(ArrayList list)
    {
        try
        {
            statement = con.createStatement();
            String sql = "INSERT INTO acknowledgementreceipt(acknowledgementreceipt.company_id,acknowledgement_receipt_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status) VALUES((SELECT company_id FROM company WHERE name LIKE '"+list.get(0)+"'),'"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"','"+list.get(8)+"','"+list.get(9)+"','"+list.get(10)+"','"+list.get(11)+"',status='"+list.get(12)+"')";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public void editDetail(ArrayList list)
    {
        try
        {
            statement = con.createStatement();
            String sql = "UPDATE acknowledgementreceipt SET acknowledgementreceipt.company_id=(SELECT company_id FROM company WHERE name LIKE '"+list.get(0)+"',date='"+list.get(2)+"',po_num='"+list.get(3)+"',delivery_receipt_num='"+list.get(4)+"',sales_person='"+list.get(5)+"',ordered_by='"+list.get(6)+"',delivered_by='"+list.get(7)+"',delivery_notes='"+list.get(8)+"',discount='"+list.get(9)+"',original_amount='"+list.get(10)+"',current_balance='"+list.get(11)+"' WHERE acknowledgement_receipt_id LIKE '"+list.get(1)+"'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    @Override
    public void deleteDetail(String ID)
    {
        try
        {
            statement = con.createStatement();
            String sql = "DELETE FROM acknowledgementreceipt WHERE acknowledgement_receipt_id='"+ID+"'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    @Override
    public boolean getConnectionStatus()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

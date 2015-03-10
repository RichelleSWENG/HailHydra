package AcknowledgementReceipt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Database.DBConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jolo Simeon
 */
public class AckReceiptModel
{

    protected Connection db;
    protected Statement statement;
    

    private int itemCount = 0;

    public AckReceiptModel(DBConnection db)
    {
        this.db = db.getConnection();
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
            String sql = "SELECT customer.name, address, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id";
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
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
            if (filter == "name")
            {
                if (startDate != null && endDate != null)
                {
                    sql = "SELECT customer.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id AND customer.name LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                } else
                {
                    sql = "SELECT customer.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id AND customer.name LIKE '%" + field + "%'";
                }
            } else
            {
                if (startDate != null && endDate != null)
                {
                    sql = "SELECT customer.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id AND acknowledgement_receipt_id LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                } else
                {
                    sql = "SELECT customer.name, date,acknowledgement_receipt_id,original_amount,current_balance FROM customer,acknowledgementreceipt WHERE acknowledgementreceipt.customer_id=customer.customer_id AND acknowledgement_receipt_id LIKE '%" + field + "%'";
                }
            }
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public void addDetail(Object obj)
    {
        AcknowledgementReceipt ar = (AcknowledgementReceipt) obj;
        try
        {
            statement = db.createStatement();
            String sql = "INSERT INTO acknowledgementreceipt(acknowledgementreceipt.company_id,acknowledgement_receipt_id,date,po_num,delivery_receipt_num,sales_person,ordered_by,delivered_by,delivery_notes,discount,original_amount,current_balance,status,address) VALUES('" + ar.getCompany_id() + "','" + ar.getAcknowledgement_receipt_id() + "','" + ar.getDate() + "','" + ar.getPo_num() + "','" + ar.getDelivery_receipt_num() + "','" + ar.getSales_person() + "','" + ar.getOrdered_by() + "','" + ar.getDelivered_by() + "','" + ar.getDelivery_notes() + "','" + ar.getDiscount() + "','" + ar.getOriginal_amount() + "','" + ar.getCurrent_balance() + "','" + ar.getStatus() + "','" + ar.getAddress() + "')";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void editDetail(Object obj)
    {
        AcknowledgementReceipt ar = (AcknowledgementReceipt) obj;
        try
        {
            statement = db.createStatement();
            String sql = "UPDATE acknowledgementreceipt SET acknowledgementreceipt.company_id='" + ar.getCompany_id() + "',date='" + ar.getDate() + "',po_num='" + ar.getPo_num() + "',delivery_receipt_num='" + ar.getDelivery_receipt_num() + "',sales_person='" + ar.getSales_person() + "',ordered_by='" + ar.getOrdered_by() + "',delivered_by='" + ar.getDelivered_by() + "',delivery_notes='" + ar.getDelivery_notes() + "',discount='" + ar.getDiscount() + "',original_amount='" + ar.getOriginal_amount() + "',current_balance='" + ar.getCurrent_balance() + "',address='" + ar.getAddress() + "' WHERE acknowledgement_receipt_id LIKE '" + ar.getAcknowledgement_receipt_id() + "'";
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
    
    public void getCustomers()
    {
        
    }
}

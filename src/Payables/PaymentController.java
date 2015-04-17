/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payables;

import Purchases.PurchaseTransaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author Janine
 */
public class PaymentController {
    private PaymentModel paymentModel;
    private AddPaymentPayablesGUI gui;
    
    public PaymentController(PaymentModel tempModel,AddPaymentPayablesGUI tempGUI)
    {
        this.paymentModel = tempModel;
        this.gui = tempGUI;
    }

    public PaymentController(PaymentModel paymentModel) {
        this.paymentModel = paymentModel;
    }
    
    TableModel getAllModel(String id)
    {
        TableModel tbm = paymentModel.myModel(paymentModel.viewPayment(id));
        return tbm;
    }
    
    public void searchActivePayables(String name)
    {
       TableModel tbm = paymentModel.myModel(paymentModel.searchActivePayables(name));
       gui.setTableModel(tbm);
    }
    
    public void changeCurrentBalance(String pt,float currentbalance)
    {
        paymentModel.updateCurrentBalance(pt, currentbalance);
    }
    
    public ArrayList<String> getSupplier() throws SQLException
    {
        ResultSet rs = paymentModel.getSupplier();
        ArrayList<String> supplier = new ArrayList<String>();
        while(rs.next())
        {
            supplier.add(rs.getString("name"));
        }
        return supplier;
    }
    
    public void addPayment(Payment obj)
    {
        paymentModel.addPayment(obj);
    }
    
    public void changeStatus(String pt)
    {
        paymentModel.changeStatus(pt);
    }
    
    public Payment getOtherDetails(String payment_id)
    {
        return paymentModel.getOtherDetails(payment_id);
    }
    
    public PurchaseTransaction getPTDetails(String id)
    {
        return paymentModel.getPTDetails(id);
    }
    
}

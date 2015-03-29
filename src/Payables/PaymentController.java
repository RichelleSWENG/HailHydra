/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payables;

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
    
    TableModel getAllModel()
    {
       TableModel tbm = paymentModel.myModel(paymentModel.getAllActivePayables());
        return tbm;
    }
    
    public void searchActivePayables(String name)
    {
       TableModel tbm = paymentModel.myModel(paymentModel.searchActivePayables(name));
       gui.setTableModel(tbm);
    }
    
}

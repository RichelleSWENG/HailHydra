/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collectibles;

import AcknowledgementReceipt.AcknowledgementReceipt;
import Payables.Payment;
import Sales.SalesInvoice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author Janine
 */
public class PaymentCollectiblesController
{
	private PaymentCollectiblesModel paymentCollectiblesModel;
	private AddPaymentCollectiblesGUI gui;

	public PaymentCollectiblesController(PaymentCollectiblesModel model,
			AddPaymentCollectiblesGUI tempGUI)
	{
		paymentCollectiblesModel = model;
		gui = tempGUI;
	}

	public PaymentCollectiblesController(PaymentCollectiblesModel model)
	{
		paymentCollectiblesModel = model;
	}

	public void searchActivePayables(String name)
	{
		TableModel tbm = paymentCollectiblesModel
				.myModel(paymentCollectiblesModel
						.searchActiveCollectibles(name));
		gui.setTableModel(tbm);
	}

	public ArrayList<String> getCustomer() throws SQLException
	{
		ResultSet rs = paymentCollectiblesModel.getCustomer();
		ArrayList<String> customer = new ArrayList<String>();
		while (rs.next())
		{
			customer.add(rs.getString("name"));
		}
		return customer;
	}

	public void addPayment(Collection obj)
	{
		paymentCollectiblesModel.addPayment(obj);
	}

	public void changeStatus(int number, String type)
	{
		if (type.equalsIgnoreCase("Acknowledgement Receipt"))
			paymentCollectiblesModel.changeStatusAR(number);
		else if (type.equalsIgnoreCase("Sales Invoice"))
			paymentCollectiblesModel.changeStatusSI(number);

	}

	public void changeCurrentBalance(int number, String type,
			float currentbalance)
	{
		if (type.equalsIgnoreCase("Acknowledgement Receipt"))
			paymentCollectiblesModel.updateCurrentBalanceAR(number,
					currentbalance);
		else if (type.equalsIgnoreCase("Sales Invoice"))
			paymentCollectiblesModel.updateCurrentBalanceSI(number,
					currentbalance);
	}

	TableModel getAllModel(String id, String type)
	{
		TableModel tbm = paymentCollectiblesModel
				.myModel(paymentCollectiblesModel.viewPayment(id, type));
		return tbm;
	}

	public Collection getOtherDetails(String id)
	{
		return paymentCollectiblesModel.getOtherDetails(id);
	}

	public AcknowledgementReceipt getARDetails(String id)
	{
		return paymentCollectiblesModel.getARDetails(id);
	}

	public SalesInvoice getSIDetails(String id)
	{
		return paymentCollectiblesModel.getSIDetails(id);
	}
}

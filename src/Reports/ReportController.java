/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import javax.swing.table.TableModel;

/**
 *
 * @author Janine
 */
public class ReportController
{
	private ReportModel reportModel;
	private CreditLimitReportGUI creditgui;
	private OrderReportGUI ordergui;
	private TermsReportGUI termsgui;
	private int itemcount = 0;

	public ReportController(ReportModel model, OrderReportGUI gui)
	{
		reportModel = model;
		ordergui = gui;
	}

	public ReportController(ReportModel model, TermsReportGUI gui)
	{
		reportModel = model;
		termsgui = gui;
	}

	public ReportController(ReportModel model, CreditLimitReportGUI gui)
	{
		reportModel = model;
		creditgui = gui;
	}

	TableModel getAllCreditModel()
	{
		TableModel tbm = reportModel.myModel(reportModel.CreditReport());
		this.itemcount = reportModel.getItemcount();
		creditgui.setItemCount(itemcount);
		return tbm;
	}

	TableModel getAllTermsModel()
	{
		TableModel tbm = reportModel.myModel(reportModel.TermsReport());
		this.itemcount = reportModel.getItemcount();
		termsgui.setItemCount(itemcount);
		return tbm;
	}

	TableModel getAllOrderModel()
	{
		TableModel tbm = reportModel.myModel(reportModel.OrderReport());
		this.itemcount = reportModel.getItemcount();
		ordergui.setItemCount(itemcount);
		return tbm;
	}

	public void SearchSomethingfromOrder(String text, int type)
	{
		String searchBy = null;
		if (type == 0)
			searchBy = "part number";
		else if (type == 1)
			searchBy = "description";
		TableModel tbm;
		tbm = reportModel
				.myModel(reportModel.searchOrderReport(text, searchBy));
		this.itemcount = reportModel.getItemcount();
		ordergui.setItemCount(itemcount);
		ordergui.setTableModel(tbm);
	}

	public void SearchSomethingfromCredit(String name)
	{
		TableModel tbm;
		tbm = reportModel.myModel(reportModel.searchCreditReport(name));
		this.itemcount = reportModel.getItemcount();
		creditgui.setItemCount(itemcount);
		creditgui.setTableModel(tbm);
	}

	public void ViewNearTerms()
	{
		TableModel tbm;
		tbm = reportModel.myModel(reportModel.getNearTerms());
		this.itemcount = reportModel.getItemcount();
		termsgui.setItemCount(itemcount);
		termsgui.setTableModel(tbm);
	}

	public void ViewExceededTerms()
	{
		TableModel tbm;
		tbm = reportModel.myModel(reportModel.getExceededTerms());
		this.itemcount = reportModel.getItemcount();
		termsgui.setItemCount(itemcount);
		termsgui.setTableModel(tbm);
	}

	public void SearchAll(String filter, String field)
	{
		TableModel tbm;
		tbm = reportModel.myModel(reportModel.SearchAll(filter, field));
		this.itemcount = reportModel.getItemcount();
		termsgui.setItemCount(itemcount);
		termsgui.setTableModel(tbm);
	}

	public void SearchNear(String filter, String field)
	{
		TableModel tbm;
		tbm = reportModel.myModel(reportModel.SearchNear(filter, field));
		this.itemcount = reportModel.getItemcount();
		termsgui.setItemCount(itemcount);
		termsgui.setTableModel(tbm);
	}

	public void SearchExceeded(String filter, String field)
	{
		TableModel tbm;
		tbm = reportModel.myModel(reportModel.SearchExceeded(filter, field));
		this.itemcount = reportModel.getItemcount();
		termsgui.setItemCount(itemcount);
		termsgui.setTableModel(tbm);
	}
}

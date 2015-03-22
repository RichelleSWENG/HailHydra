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
public class ReportController {
    private ReportModel reportModel;
    private CreditLimitReportGUI creditgui;
    private OrderReportGUI ordergui;
    private TermsReportGUI termsgui;
    private int itemcount=0;
    
    public ReportController(ReportModel model,OrderReportGUI gui)
    {
        reportModel=model;
        ordergui=gui;
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
            if(type == 0)
                searchBy = "part number";
            else if(type == 1)
                searchBy = "description";
            TableModel tbm;
            tbm = reportModel.myModel(reportModel.searchOrderReport(text, searchBy));
            this.itemcount = reportModel.getItemcount();
            ordergui.setItemCount(itemcount);
            ordergui.setTableModel(tbm);
    }
    
    
}

package ModifyAlertVAT;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.Statement;

public class FactoryModify {
        protected Connection db;
        protected Statement statement;
	public void createProduct(String modify,DBConnection dbc){
                this.db=dbc.getConnection();
		if ("VAT"==modify){
                        String vat=new ModifyVAT().getInput();
                        if(vat!="")
                        {
                            try
                            {
                                statement = db.createStatement();
                                String sql = "UPDATE systeminfo SET vat_percentage='"+vat+"' WHERE company_name='Hydraforce Enterprises'";
                                statement.executeUpdate(sql);
                            } catch (Exception e)
                            {
                                e.getMessage();
                            }
                        }
		}	
		else if ("Terms"==modify){
			 String terms=new ModifyTerms().getInput();
                         if(terms!="")
                         {
                            try
                            {
                                statement = db.createStatement();
                                String sql = "UPDATE systeminfo SET credit_alert='"+terms+"' WHERE company_name='Hydraforce Enterprises'";
                                statement.executeUpdate(sql);
                            } catch (Exception e)
                            {
                                e.getMessage();
                            }
                         }
		}
			
		else if ("CreditLimit"==modify){
			String creditlimit=new ModifyCreditLimit().getInput();
                        if(creditlimit!="")
                        {
                            try
                            {
                                statement = db.createStatement();
                                String sql = "UPDATE systeminfo SET terms_report='"+creditlimit+"' WHERE company_name='Hydraforce Enterprises'";
                                statement.executeUpdate(sql);
                            } catch (Exception e)
                            {
                                e.getMessage();
                            }
                        }
		}
    }
}

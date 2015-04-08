package ModifyAlertVAT;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryModify {
        protected Connection db;
        protected Statement statement;
	public void createProduct(String modify,DBConnection dbc){
                this.db=dbc.getConnection();
		if ("VAT"==modify){
                        String vat=new ModifyVAT(getCurrentVat()).getInput();
                        if(vat!="")
                        {
                            try
                            {
                                statement = db.createStatement();
                                String sql = "UPDATE systeminfo SET vat_percentage='"+vat+"' WHERE system_info_id='1'";
                                statement.executeUpdate(sql);
                            } catch (Exception e)
                            {
                                e.getMessage();
                            }
                        }
		}	
		else if ("Terms"==modify){
			 String terms=new ModifyTerms(getCurrentTerms()).getInput();
                         if(terms!="")
                         {
                            try
                            {
                                statement = db.createStatement();
                                String sql = "UPDATE systeminfo SET credit_alert='"+terms+"' WHERE system_info_id='1'";
                                statement.executeUpdate(sql);
                            } catch (Exception e)
                            {
                                e.getMessage();
                            }
                         }
		}
			
		else if ("CreditLimit"==modify){
			String creditlimit=new ModifyCreditLimit(getCreditLimit()).getInput();
                        if(creditlimit!="")
                        {
                            try
                            {
                                statement = db.createStatement();
                                String sql = "UPDATE systeminfo SET terms_report='"+creditlimit+"' WHERE system_info_id='1'";
                                statement.executeUpdate(sql);
                            } catch (Exception e)
                            {
                                e.getMessage();
                            }
                        }
		}
    }
            public String getCurrentVat()
            {
                ResultSet rs = null;
                try
                {
                    statement = db.createStatement();
                    String sql = "SELECT vat_percentage FROM systeminfo WHERE system_info_id='1'";
                    rs = statement.executeQuery(sql);

                } catch (Exception e)
                {
                    e.getMessage();
                }
                try {
                    if(!rs.next())
                        return null;
                    else
                        return rs.getString("vat_percentage");
                } catch (SQLException ex) {
                    Logger.getLogger(FactoryModify.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "";
            }   
            
            public String getCurrentTerms()
            {
                ResultSet rs = null;
                try
                {
                    statement = db.createStatement();
                    String sql = "SELECT terms_report FROM systeminfo WHERE system_info_id='1'";
                    rs = statement.executeQuery(sql);

                } catch (Exception e)
                {
                    e.getMessage();
                }
                try {
                    if(!rs.next())
                        return null;
                    else
                        return rs.getString("terms_report");
                } catch (SQLException ex) {
                    Logger.getLogger(FactoryModify.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "";
            }
            
            public String getCreditLimit()
            {
                ResultSet rs = null;
                try
                {
                    statement = db.createStatement();
                    String sql = "SELECT credit_alert FROM systeminfo WHERE system_info_id='1'";
                    rs = statement.executeQuery(sql);

                } catch (Exception e)
                {
                    e.getMessage();
                }
                try {
                    if(!rs.next())
                        return null;
                    else
                        return rs.getString("credit_alert");
                } catch (SQLException ex) {
                    Logger.getLogger(FactoryModify.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "";
            }
}

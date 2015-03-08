import java.sql.ResultSet;
import java.util.ArrayList;


public class CollectionModel extends Model
{
	public CollectionModel(DBConnection db)
	{
		super(db);
	}
	@Override
	public ResultSet getDetail(String ID)
	{
		 ResultSet rs = null;
		 try {
	            statement = db.createStatement();
	            String sql = "";
	            rs = statement.executeQuery(sql);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	        return rs;
	}
	@Override
	public ResultSet getAllDetail()
	{
		 ResultSet rs = null;
		 try {
	            statement = db.createStatement();
	            String sql = "";
	            rs = statement.executeQuery(sql);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	        return rs;
	}
	@Override
	public ResultSet searchDetail(String field, String filter)
	{
		 ResultSet rs = null;
		 try {
	            statement = db.createStatement();
	            String sql = "";
	            rs = statement.executeQuery(sql);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	        return rs;
	}
	@Override
	public void addDetail(ArrayList list)
	{
		  try {
	            statement = db.createStatement();
	            String sql = "";
	            statement.executeUpdate(sql);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	}
	@Override
	public void editDetail(ArrayList list)
	{
		try {
            statement = db.createStatement();
            String sql = "";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.getMessage();
        }
		
	}
	@Override
	public void deleteDetail(String ID)
	{
		try {
            statement = db.createStatement();
            String sql = "DELETE FROM collection WHERE collection_id='"+ID+"'";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.getMessage();
        }
		
	}
	public ResultSet getCollectibles()
	{
		 ResultSet rs = null;
		 try {
	            statement = db.createStatement();
	            String sql = "SELECT name, date,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id UNION ALL SELECT name,date,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id";
	            rs = statement.executeQuery(sql);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	        return rs;
	}
	public ResultSet searchCollectibles(String field, String filter)
	{
		 ResultSet rs = null;
		 try {
	            statement = db.createStatement();
	            String sql="";
	            if(filter.equalsIgnoreCase("Active Collectibles"))
	            	sql = "SELECT name, date,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.status LIKE 'Open' UNION ALL SELECT name,date,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.status LIKE 'Open'";
	            else 
	            	sql ="SELECT name, date,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND salesinvoice.status LIKE 'Closed' UNION ALL SELECT name,date,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND acknowledgementreceipt.status LIKE 'Closed'";
	            rs = statement.executeQuery(sql);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	        return rs;
	}
	public ResultSet searchbyDateCollectibles(ArrayList list)
	{
		ResultSet rs = null;
		 try {
	            statement = db.createStatement();
	            String sql = "SELECT name, date,sales_invoice_id,original_amount,current_balance,salesinvoice.status FROM company,salesinvoice WHERE company.company_id=salesinvoice.company_id AND date>='"+list.get(0)+"' AND date<='"+list.get(1)+"' UNION ALL SELECT name,date,acknowledgement_receipt_id,acknowledgementreceipt.original_amount,current_balance,acknowledgementreceipt.status FROM company,acknowledgementreceipt WHERE company.company_id=acknowledgementreceipt.company_id AND date>='"+list.get(0)+"' AND date<='"+list.get(1)+"'";
	            rs = statement.executeQuery(sql);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	        return rs;
	}
}

package HailHydra;

import Database.DBManager;
import Login.Account.AccountBuilder;
import java.sql.SQLException;

public class MainModel 
{
    private DBManager database;
    
    public MainModel()
    {
	database = DBManager.getInstance();
    }
    
    public void addAccount(String username, String password, String type) throws SQLException{
        AccountBuilder tempBuilder=new AccountBuilder();
        if(type.equalsIgnoreCase("administrator"))
            database.addAccount(tempBuilder.AdministratorAccount(username, password));
        else
            database.addAccount(tempBuilder.EmployeeAccount(username, password));
    }
    
}

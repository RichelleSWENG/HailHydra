package Database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection
{
private String url, username, password;
private boolean connectionStatus=false;
private Connection connection;
private static DBConnection instance;
    
    public  DBConnection()
    {
        this.username="root";
        this.password="1861096";
        this.url="jdbc:mysql://localhost:3306/hydraforce_db";   

            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                this.connection= DriverManager.getConnection(url, username, password);
                connectionStatus=true;
            } catch (Exception e) 
            {
               
            }
    

    }

    public boolean getConnectionStatus()
    {
        return this.connectionStatus;
    }

    public Connection getConnection() 
    {
        return this.connection;
    }

    public static DBConnection getInstance() 
    {
		if(instance == null) 
                    instance = new DBConnection();
		
        return instance;
    }





}



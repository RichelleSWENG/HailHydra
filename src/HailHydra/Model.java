package HailHydra;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jolo Simeon
 */;
public abstract class Model
{

    protected Statement statement;
    protected DBConnection db;
    protected Connection con;
    protected boolean connectionStatus;

    public Model(DBConnection db)
    {
        this.db = db;
        con = db.getConnection();
        this.connectionStatus = db.getConnectionStatus();
        
    }
    
    public boolean getConnectionSatus()
    {
        return db.getConnectionStatus();
    }
    
    abstract public ResultSet getDetail(String ID);

    abstract public ResultSet getAllDetail();

    abstract public ResultSet searchDetail(String field, String filter);

    abstract public void addDetail(ArrayList list);

    abstract public void editDetail(ArrayList list);

    abstract public void deleteDetail(String ID);


}

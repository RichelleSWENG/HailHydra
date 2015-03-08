package Model;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;

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
    protected boolean connectionStatus;

    public Model(DBConnection db)
    {
        this.db = db;
        this.connectionStatus = db.getConnectionStatus();
        
    }
    
    abstract public ResultSet getDetail(String ID);

    abstract public ResultSet getAllDetail();

    abstract public ResultSet searchDetail(String field, String filter);

    abstract public void addDetail(ArrayList list);

    abstract public void editDetail(ArrayList list);

    abstract public void deleteDetail(String ID);
    
    abstract public boolean getConnectionStatus();
    
    abstract public int getItemcount();
    
    abstract public TableModel myModel(ResultSet rs);

    abstract public ResultSet getAllSupplier();
    
    abstract public ResultSet getAllCustomer();

    abstract public ResultSet getDetail(String name, String AccountType);

    abstract public void editDetail(ArrayList al, String name, String type);
    

    


}

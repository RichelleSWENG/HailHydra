/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HailHydra;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Jolo Simeon
 */
public abstract class Model
{

    protected PreparedStatement statement;
    protected Connection db;

    public Model(DBConnection db)
    {
        this.db = db.getConnection();
    }
    
    abstract public ResultSet getDetail(String ID);

    abstract public ResultSet getAllDetail();

    abstract public ResultSet searchDetail(String field, String filter);

    abstract public void addDetail(ArrayList list);

    abstract public void editDetail(ArrayList list);

    abstract public void deleteDetail(String ID);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Janine
 */
public class LoginModel {
    protected Connection db;
    protected Statement statement;
    
    public LoginModel(DBConnection db)
    {
        this.db=db.getConnection();
    }
    
    public ResultSet checkUsername(String username,String password)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT type FROM account WHERE user_name='"+username+"' AND password='"+password+"'";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import Payables.PayablesController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Janine
 */
public class LoginController {
    private LoginModel model;
    
    public LoginController(LoginModel model)
    {
        this.model=model;
    }
    
    public String validate(String usernmae,String password)
    {
        
        ResultSet resultset=model.checkUsername(usernmae, password);
        try {
            if (!resultset.next() ) {
                return null;
            }
            else 
            {
                return resultset.getString("type");
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "";
    }
    
}

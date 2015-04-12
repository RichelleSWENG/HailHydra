/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BasicSystemSettings;

/**
 *
 * @author Janine
 */
public class SystemController {
    private SystemModel model;
    
    public SystemController(SystemModel model)
    {
        this.model=model;
    }
    
    public void addAccount(String username,String password,String type)
    {
        model.addAccount(username, password, type);
    }
    
    public void addSystemInfo(String name,String vat,String credit,String terms)
    {
        model.addSystemInfo(name, vat, credit, terms);
    }
    
    
    public String getInfo()
    {
        return model.getCreditLimit();
    }
}

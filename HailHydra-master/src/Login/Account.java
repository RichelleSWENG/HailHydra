
package Login;


public class Account 
{
    private String username, password;
    private int type;
    
    public static class AccountBuilder
    {
        
        public Account EmployeeAccount(String username, String password)
        {   
           return new Account(username, password,1);
        }
        
        public Account AdministratorAccount(String username, String password)
        {   
           return new Account(username, password,0);
        }
    }
    
    
    public Account(String username, String password, int type)
    {
        this.username=username;
        this.password=password;
        this.type=type;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public int getType()
    {   
     return this.type;   
    }
    
    public void  modifyUsername(String username)
    {
        this.username=username;
    }
    
    public void modifyPassword(String password){
        this.password=password;
    }
    
    
    
}

package HailHydra;

import java.sql.SQLException;


public class MainController 
{
    private GUIModel view;
    private MainModel model;
    
    public MainController(GUIModel view)
    {
        model=new MainModel();
        this.view=view;
        view.setController(this);
        
    }
    
    public void login(String username, String password)throws SQLException
    {
        try
        {
            model.addAccount(username, password, "employee");
        }
        catch (SQLException e)  
        {
            e.printStackTrace();
        }
    }
    
    
    
    
}

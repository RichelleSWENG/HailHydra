package AccountProfile;

import Model.Model;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.TableModel;

public class AccountProfileController {
    
    private Model accountModel;
    private AccountProfileListGUI gui;
    private int itemcount;
    private ArrayList<String> accountProfile = new ArrayList<>();

    public AccountProfileController(Model tempModel,AccountProfileListGUI tempGUI)
    {
        this.accountModel = tempModel;
        this.gui = tempGUI;
    }

    public AccountProfileController(Model tempModel)
    {
        this.accountModel = tempModel;
    }


    TableModel getAllModel()
    {
       
       TableModel tbm=accountModel.myModel(accountModel.getAllDetail());
       this.itemcount=accountModel.getItemcount();
       gui.setItemCount(itemcount);
        return tbm;
    
    }
    
      

    public void SearchSomething(String text, int type)
    {
            String searchBy = null;
            if(type == 0)
                searchBy = "name";
            else if(type == 1)
                searchBy = "customer";
            else if(type == 2)
                searchBy = "supplier";
            TableModel tbm;
            tbm = accountModel.myModel(accountModel.searchDetail(text, searchBy));
            gui.setTableModel(tbm);
    }

   public void ViewAll()
    {
       gui.ViewAll();
    }

   public void ViewSupplier()
    {
       TableModel tbm=accountModel.myModel(accountModel.getAllSupplier());
       this.itemcount=accountModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }

   public void ViewCustomer()
    {
       TableModel tbm=accountModel.myModel(accountModel.getAllCustomer());
       this.itemcount=accountModel.getItemcount();
       gui.setItemCount(itemcount);
       gui.setTableModel(tbm);
    }

    public void AddAccountProfile(ArrayList<String> al)
    {
        System.out.println(al.get(0));
        accountModel.addDetail(al);
    }



    public void ViewAccountProfile(String name, String AccountType) throws SQLException
    {
        //ResultSet rs = accountModel.getDetail(name,AccountType);
                ResultSet rs;

        rs = accountModel.getDetail(name,AccountType);
        ResultSetMetaData metadata = rs.getMetaData();
        int numberOfColumns = metadata.getColumnCount();

        System.out.println(numberOfColumns);
        while (rs.next()) 
        {              
        int i = 1;
        while(i <= numberOfColumns) 
            {
            this.accountProfile.add(rs.getString(i++));
            }
        }
        System.out.println(accountProfile.get(15));

    }

    public ArrayList getAccountProfile()
    {
        return this.accountProfile;
    }

   public void ModifyAccountProfile(ArrayList al, String name, String type)
    {
        accountModel.editDetail(al,name,type);
    }
    
    
    
}

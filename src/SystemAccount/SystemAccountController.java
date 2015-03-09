package SystemAccount;

import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class SystemAccountController
{
    private SystemAccountModel systemAccountModel;
    
    public SystemAccountController(SystemAccountModel tempModel)
    {
        this.systemAccountModel = tempModel;
    }
    
    public void AddSystemAccount(ArrayList<String> str)
    {
        systemAccountModel.addDetail(str);
    }
    
    public TableModel getSystemAccounts(String type)
    {
        TableModel tbm = DbUtils.resultSetToTableModel(systemAccountModel.searchDetail(type, "type"));
        return tbm;
    }
}



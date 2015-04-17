package SystemAccount;

import Payables.PayablesController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class SystemAccountController
{
	private SystemAccountModel systemAccountModel;
	private ModifySystemProfileGUI gui;
	private ModifyPasswordGUI gui2;

	public SystemAccountController(SystemAccountModel tempModel)
	{
		this.systemAccountModel = tempModel;
	}

	public SystemAccountController(SystemAccountModel tempModel,
			ModifySystemProfileGUI gui)
	{
		this.gui = gui;
		systemAccountModel = tempModel;
	}

	public SystemAccountController(SystemAccountModel tempModel,
			ModifyPasswordGUI gui)
	{
		this.gui2 = gui;
		systemAccountModel = tempModel;
	}

	public void AddSystemAccount(ArrayList<String> str)
	{
		systemAccountModel.addDetail(str);
	}

	public TableModel getSystemAccounts(String type)
	{
		TableModel tbm = DbUtils.resultSetToTableModel(systemAccountModel
				.searchDetail(type, "type"));
		return tbm;
	}

	public String getCompanyName()
	{
		ResultSet resultset = systemAccountModel.getCompanyName();
		try
		{
			resultset.next();
			return resultset.getString("company_name");
		} catch (SQLException ex)
		{
			Logger.getLogger(SystemAccountController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public String getCompanyAdress()
	{
		ResultSet resultset = systemAccountModel.getCompanyAdress();
		try
		{
			resultset.next();
			return resultset.getString("company_address");
		} catch (SQLException ex)
		{
			Logger.getLogger(SystemAccountController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public void changeInfo(String name, String address)
	{
		systemAccountModel.changeSystemProfile(name, address);
	}

	public String getPassword(int type)
	{
		ResultSet resultset = systemAccountModel.getOldPassword(type);
		try
		{
			resultset.next();
			return resultset.getString("password");
		} catch (SQLException ex)
		{
			Logger.getLogger(SystemAccountController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return "";
	}

	public boolean checkOldPassword()
	{
		return gui2.checkOldPassword();
	}

	public void changePassword(int type, String password)
	{
		systemAccountModel.changePassword(type, password);
	}
}

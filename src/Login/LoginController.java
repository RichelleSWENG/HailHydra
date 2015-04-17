/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import Payables.PayablesController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Janine
 */
public class LoginController implements Observer
{
	private LoginModel model;
	private MainMenuGUI gui;
	private Subject notifgrabber;

	public LoginController(LoginModel model)
	{
		this.model = model;
	}

	public LoginController(LoginModel model, MainMenuGUI gui, Subject grabber)
	{
		this.model = model;
		this.gui = gui;
		notifgrabber = grabber;
		grabber.register(this);
	}

	public String validate(String username, String password)
	{

		ResultSet resultset = model.checkUsername(username, password);
		try
		{
			if (resultset.next())
			{
				return resultset.getString("type");
			} else
			{
				return null;
			}
		} catch (SQLException ex)
		{
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return "";
	}

	public ArrayList<Notification> getNotifs()
	{
		return model.getNotifs();
	}

	@Override
	public void update()
	{
		gui.getNotifs();
	}

}

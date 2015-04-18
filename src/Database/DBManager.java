package Database;

import Login.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager
{
	private static DBManager instance = new DBManager();
	private DBConnection connector;
	private Connection connection;

	private DBManager()
	{
		connector = DBConnection.getInstance();
		connection = connector.getConnection();
	}

	public static DBManager getInstance()
	{
		return instance;
	}

	public void addAccount(Account account) throws SQLException
	{
		PreparedStatement ps = connection
				.prepareStatement("INSERT INTO account(user_name, password, type) VALUES (?, ?, ?)");

		ps.setString(1, account.getUsername());
		ps.setString(2, account.getPassword().toString());
		ps.setInt(3, account.getType());
		ps.executeUpdate();
	}

}

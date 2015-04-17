package Database;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class DatabaseGUI extends JPanel
{
	private JLabel lblStatus;
	private boolean connectionStatus;
	private Font fntDatabase;

	public DatabaseGUI()
	{
		fntDatabase = new Font("Arial", Font.PLAIN, 14);
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setPreferredSize(new Dimension(this.getWidth(), 20));
		lblStatus = new JLabel("");
		lblStatus.setFont(fntDatabase);
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(lblStatus);

		DBConnection dbc = new DBConnection();
		this.connectionStatus = dbc.getConnectionStatus();
		if (connectionStatus)
			lblStatus.setText("Database Connected.");
		else
			lblStatus.setText("Database Not Connected.");
	}

}

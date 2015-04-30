package Database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class DatabaseGUI extends JPanel
{
	private JLabel lblStatus;
	private boolean connectionStatus;
	private Font fntDatabase;
        private JProgressBar progressBar;

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
                
               /* progressBar = new JProgressBar(0, 0);
                // Set progressBar color
                progressBar.setForeground(new Color(0,176,80));

                // Edit progress bar height
                Dimension prefSize = progressBar.getPreferredSize();
                prefSize.height = 50;
                progressBar.setPreferredSize(prefSize);

                // Set the layout
                progressBar.setLayout(new BorderLayout(5, 5));

                // Set progress bar value
                progressBar.setValue(38);

                // Construct the label
                JLabel progressLabel = new JLabel("");
                // Set alignment
                progressLabel.setHorizontalAlignment(JLabel.CENTER);
                progressLabel.setVerticalAlignment(JLabel.CENTER);

                // Set the borders
                progressLabel.setBorder(new EmptyBorder(15, 15, 15, 15));


                // Add label to the progress bar
                progressBar.add(progressLabel, BorderLayout.CENTER);

                // Add progress bar to the frame
                add(progressBar);
*/

                                DBConnection dbc = new DBConnection();
                                this.connectionStatus = dbc.getConnectionStatus();
                                if (connectionStatus)
                                        lblStatus.setText("Database Connected.");
                                else
                                        lblStatus.setText("Database Not Connected.");
                        }

                }

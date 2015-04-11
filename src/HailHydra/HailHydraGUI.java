package HailHydra;


import Database.DBConnection;
import Database.DatabaseGUI;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HailHydraGUI extends JFrame
{
    private Connection db;
    private JPanel pnlContent, pnlDatabase;
    
    
    public HailHydraGUI()
    {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle();
                this.setResizable(false);
		setBounds(100, 100, 1000, 650);
                setVisible(true);
                
		pnlContent = new JPanel();
		pnlContent.setLayout(null);
		getContentPane().add(pnlContent);
		add(pnlContent);
                
                pnlDatabase=new DatabaseGUI();
                getContentPane().add(pnlDatabase, BorderLayout.SOUTH);
              
                
                
    }
    
    public void setTitle()
    {
        DBConnection dbc = new DBConnection();
                db=dbc.getConnection();
                try
                {
                Statement statement= db.createStatement();
                String sql="SELECT company_name FROM systeminfo WHERE system_info_id=1 ";
                ResultSet rs= statement.executeQuery(sql);
                ResultSetMetaData metadata = rs.getMetaData();
                if(rs.next())
                {
                this.setTitle(""+rs.getString(1));
                }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
    }
    
    public JPanel getContentPanel()
    {
        return pnlContent;
    }
    
    
    
    
}

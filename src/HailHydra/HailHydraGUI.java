package HailHydra;


import Database.DBConnection;
import Database.DatabaseGUI;
import ModifyAlertVAT.FactoryModify;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HailHydraGUI extends JFrame
{
    private Connection db;
    private JPanel pnlContent, pnlDatabase;
    
    
    public HailHydraGUI(DBConnection db)
    {
                this.db=db.getConnection();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle();
                //setIconImage(Toolkit.getDefaultToolkit().createImage("hailhydra.PNG"));
                this.setResizable(false);
		setBounds(100, 100, 1000, 650);
                setVisible(true);
                
		pnlContent = new JPanel();
		pnlContent.setLayout(null);
		getContentPane().add(pnlContent);
		getContentPane().add(pnlContent);
                
                pnlDatabase=new DatabaseGUI();
                getContentPane().add(pnlDatabase, BorderLayout.SOUTH);
    }
    
    public void setTitle()
    {
        ResultSet rs = null;
        try
        {
            Statement statement = db.createStatement();
            String sql = "SELECT company_name FROM systeminfo WHERE system_info_id='1'";
            rs = statement.executeQuery(sql);

        } catch (Exception e)
        {
            e.getMessage();
        }
        try {
                if(!rs.next())
                {
                    this.setTitle("");
                    return;
                }
                else
                    this.setTitle(rs.getString("company_name"));
            } catch (SQLException ex) {
                Logger.getLogger(FactoryModify.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public JPanel getContentPanel()
    {
        return pnlContent;
    }
}

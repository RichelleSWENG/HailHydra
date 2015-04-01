package HailHydra;


import Database.DatabaseGUI;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HailHydraGUI extends JFrame
{
    private JPanel pnlContent, pnlDatabase;
    
    
    public HailHydraGUI()
    {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    
    public JPanel getContentPanel()
    {
        return pnlContent;
    }
    
    
    
    
}

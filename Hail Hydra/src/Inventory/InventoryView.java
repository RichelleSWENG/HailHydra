package Inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import TableRenderer.TableRenderer;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryView extends JFrame
{

    private JPanel contentPane;
    private JTextField tfSearch;
    private JRadioButton rdbtnPartNumber;
    private JRadioButton rdbtnDescription;
    //private JRadioButton rdbtnAll;
    private JButton btnClose;
    private JButton btnAddItemProfile;
    private JButton btnViewItemProfile;
    private JButton btnViewAllItems;
    private JLabel lblSearchBy;
    private JLabel lblSearch;
    private JLabel lblItemsFound;
    private JLabel lblNumofItems;
    private DefaultTableModel table;
    private JTable inventoryTable;
    private JScrollPane scrollPane;
    private String headers[] =
    {
        "Part Number", "Description", "<html>Quantity<br>(Functional)</html>",
			"<html>Quantity<br>(Defective)</html>", "Last Cost",
			"<html>Sister<br>Company<br>Price</html>", "Traders Price",
			"Walk-in Price"
    };
    private JButton btnDeleteItemProfile;
    private boolean connectionStatus = false;
    private InventoryController ic;
    private JLabel statusLabel;
    private String pkey; // for delete
    private JLabel lblInventory;

    public InventoryView() throws Exception
    {
        //this.connectionStatus=ic.getConnectionStatus();
        setResizable(false);
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        contentPane.setLayout(null);
        this.add(contentPane);

        JPanel statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
        this.statusLabel = new JLabel("Database <<>>");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(statusLabel);

        lblSearchBy = new JLabel("Search By: ");
        lblSearchBy.setFont(new Font("Arial", Font.PLAIN, 21));
        lblSearchBy.setBounds(20, 90, 130, 30);
        contentPane.add(lblSearchBy);

        rdbtnPartNumber = new JRadioButton("Part Number");
	rdbtnPartNumber.setFont(new Font("Arial", Font.PLAIN, 21));
        rdbtnPartNumber.setActionCommand("Part Number");
	rdbtnPartNumber.setSelected(true);
	rdbtnPartNumber.setBounds(142, 91, 158, 30);
	contentPane.add(rdbtnPartNumber);

        rdbtnPartNumber.addActionListener(new ActionListener()
        {//Everytime All is selected 
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    tfSearch.setText(null);
                } catch (Exception ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        rdbtnDescription = new JRadioButton("Description");
	rdbtnDescription.setFont(new Font("Arial", Font.PLAIN, 21));
        rdbtnDescription.setActionCommand("Description");
	rdbtnDescription.setBounds(309, 90, 158, 30);
	contentPane.add(rdbtnDescription);

        rdbtnDescription.addActionListener(new ActionListener()
        {//Everytime All is selected 
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    tfSearch.setText(null);
                } catch (Exception ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        final ButtonGroup searchBy = new ButtonGroup();
        //searchBy.add(rdbtnAll);
        searchBy.add(rdbtnPartNumber);
        searchBy.add(rdbtnDescription);
                //System.out.println(searchBy.getSelection());

        tfSearch = new JTextField();
	tfSearch.setFont(new Font("Arial", Font.PLAIN, 21));
	tfSearch.setBounds(113, 128, 404, 30);
	contentPane.add(tfSearch);
	tfSearch.setColumns(10);

 	lblSearch = new JLabel("Search:");
	lblSearch.setFont(new Font("Arial", Font.PLAIN, 21));
	lblSearch.setBounds(20, 128, 99, 30);
	contentPane.add(lblSearch);

        lblItemsFound = new JLabel("Item/s Found: ");
	lblItemsFound.setFont(new Font("Arial", Font.PLAIN, 21));		
        lblItemsFound.setBounds(20, 172, 147, 30);
	contentPane.add(lblItemsFound);

       	lblNumofItems = new JLabel("0");
	lblNumofItems.setFont(new Font("Arial", Font.PLAIN, 21));
	lblNumofItems.setBounds(167, 172, 46, 30);
	contentPane.add(lblNumofItems);

	btnAddItemProfile = new JButton("Add Item Profile");
	btnAddItemProfile.setFont(new Font("Arial", Font.PLAIN, 21));
	btnAddItemProfile.setBounds(20, 543, 217, 40);
	contentPane.add(btnAddItemProfile);
                
        btnDeleteItemProfile = new JButton("Delete Item Profile");
	btnDeleteItemProfile.setFont(new Font("Arial", Font.PLAIN, 21));
	btnDeleteItemProfile.setBounds(288, 543, 243, 40);
	contentPane.add(btnDeleteItemProfile);

       	btnViewItemProfile = new JButton("View Item Profile");
	btnViewItemProfile.setFont(new Font("Arial", Font.PLAIN, 21));
	btnViewItemProfile.setBounds(575, 543, 235, 40);
	contentPane.add(btnViewItemProfile);

	btnClose = new JButton("Close");
	btnClose.setFont(new Font("Arial", Font.PLAIN, 21));
	btnClose.setBounds(852, 543, 118, 40);
	contentPane.add(btnClose);

	btnViewAllItems = new JButton("View All Items");
	btnViewAllItems.setFont(new Font("Arial", Font.PLAIN, 21));
	btnViewAllItems.setBounds(770, 167, 199, 40);
	contentPane.add(btnViewAllItems);

        table = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };

        table.setRowCount(15);

        for (int i = 0; i < 8; i++)
        {
            table.addColumn(headers[i]);
        }

        inventoryTable = new JTable(table)
        {
            public TableCellRenderer getCellRenderer(int row, int column)
            {
                return new TableRenderer();
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component comp = super.prepareRenderer(renderer, row, column);
                int modelRow = convertRowIndexToModel(row);
                if (!isRowSelected(modelRow))
                {
                    comp.setBackground(Color.WHITE);
                } 
                else
                {
                    comp.setBackground(Color.LIGHT_GRAY);
                }
                return comp;
            }
            
            @Override
            public boolean isCellEditable (int row, int column)
            {
                return false;
            }
        };
        
        	inventoryTable.getTableHeader().setFont(
		new Font("Arial", Font.PLAIN, 14));
		inventoryTable.getTableHeader().setPreferredSize(new Dimension(100, 55));
		inventoryTable.getTableHeader().setResizingAllowed(false);
		TableCellRenderer rend = inventoryTable.getTableHeader().getDefaultRenderer();
		TableColumnModel tcm = inventoryTable.getColumnModel();
		for (int j = 0; j < tcm.getColumnCount(); j += 1)
		{
			TableColumn tc = tcm.getColumn(j);
			TableCellRenderer rendCol = tc.getHeaderRenderer();
			if (rendCol == null)
				rendCol = rend;
			Component c = rendCol.getTableCellRendererComponent(inventoryTable,
					tc.getHeaderValue(), false, false, 0, j);
			tc.setPreferredWidth(c.getPreferredSize().width);
		}
	inventoryTable.setFont(new Font("Arial", Font.PLAIN, 21));

	scrollPane = new JScrollPane(inventoryTable);
	scrollPane.setBounds(20, 225, 949, 305);
	contentPane.add(scrollPane);
        

       	inventoryTable.getParent().setBackground(inventoryTable.getBackground());
	inventoryTable.getTableHeader().setResizingAllowed(false);
	inventoryTable.getTableHeader().setReorderingAllowed(false);
        inventoryTable.setColumnSelectionAllowed(true);
	inventoryTable.setRowSelectionAllowed(true);
        inventoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	inventoryTable.setRowHeight(30);

        lblInventory = new JLabel("Inventory");
	lblInventory.setFont(new Font("Arial", Font.BOLD, 45));
	lblInventory.setBounds(20, 11, 345, 80);
	contentPane.add(lblInventory);

        btnAddItemProfile.addActionListener(new btnAddItemProfile_Action());
        btnDeleteItemProfile.addActionListener(new btnDeleteItemProfile_Action());
        btnClose.addActionListener(new btnClose_Action());
        btnViewAllItems.addActionListener(new btnViewAll_Action());
        btnViewItemProfile.addActionListener(new btnViewItemProfile_Action());
        
        // Listen for changes in the text
        tfSearch.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent de)
            {
                try
                {
                    done();
                } catch (Exception ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de)
            {
                try
                {
                    done();
                } catch (Exception ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de)
            {
                try
                {
                    done();
                } catch (Exception ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void done() throws Exception
            {
                if (tfSearch.getText().length() > 0)
                {
                    ic.SearchSomething(tfSearch.getText(), searchBy.getSelection().getActionCommand()); //if a key is typed search

                } else if (tfSearch.getText().length() == 0)  //if nothing is typed display all
                {
                    viewAll();
                }
            }
        });

    }

    public void setConnectionStatus()
    {
        this.connectionStatus = ic.getConnectionStatus();
        if (connectionStatus)
        {
            this.statusLabel.setText("Database<<Connected>>");
        } else
        {
            this.statusLabel.setText("Database<<Not Connected>>");
        }
    }

    public void setItemCount(int itemcount)
    {
        lblNumofItems.setText(Integer.toString(itemcount));
    }

    public void confirmMessage(String question) throws SQLException, Exception
    {
        //JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, question, "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION)
        {
            System.out.println("No button clicked");
        } else if (response == JOptionPane.YES_OPTION)
        {
            System.out.println("Yes button clicked");
            ic.DeleteItem(pkey);
            //System.out.println("the key "+pkey);
            viewAll();
            infoMessage("You have DELETED "+pkey+"");
        } else if (response == JOptionPane.CLOSED_OPTION)
        {
            System.out.println("JOptionPane closed");
        }
    }

    public void infoMessage(String info)
    {
        JFrame frame = null;
        JOptionPane.showMessageDialog(frame, info);
    }

    public void setController(InventoryController ic)
    {
        //System.out.println("controller set");
        this.ic = ic;
    }

    public void viewAll() throws Exception
    {
        TableModel AllModel = ic.getAllModel();
        inventoryTable.setModel(AllModel);

        JTableHeader th = inventoryTable.getTableHeader();      // Setting the Headers
        TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < 8; i++)
        {
            TableColumn tc = tcm.getColumn(i);
            tc.setHeaderValue(headers[i]);
        }
        th.repaint();
    }

    public void setTableModel(TableModel tbm)
    {                  // Setting the Headers
        inventoryTable.setModel(tbm);
        JTableHeader th = inventoryTable.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < 8; i++)
        {
            TableColumn tc = tcm.getColumn(i);
            tc.setHeaderValue(headers[i]);
        }
        th.repaint();
    }

    class btnAddItemProfile_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            ic.AddItemProfileView();

        }
    }

    class btnDeleteItemProfile_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            pkey = null;
            int row;
            row = inventoryTable.getSelectedRow();
            
            if(row == -1 )
            JOptionPane.showMessageDialog(null, "Please select an item.");
            else {
              pkey = inventoryTable.getValueAt(row, 0).toString();
                try
                {
                    confirmMessage("Are you sure you want to delete Item Profile# "+ pkey +" of Inventory?\n Deleted Item Profile can not be recovered. ");
                } catch (SQLException ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        }
    }

    class btnClose_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            contentPane.setVisible(false);
        }
    }

    class btnViewAll_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ic.ViewAllItems();
                tfSearch.setText("");
            } catch (Exception ex)
            {
                Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    class  btnViewItemProfile_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            pkey = null;
            int row;
            row = inventoryTable.getSelectedRow();
            
            if(row == -1 )
            JOptionPane.showMessageDialog(null, "Please select an item.");
            else {
              pkey = inventoryTable.getValueAt(row, 0).toString();
                try
                {
                    ic.ViewItemProfile(pkey);
                } catch (SQLException ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex)
                {
                    Logger.getLogger(InventoryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        }
    }
}

package Inventory;
import HailHydra.GUIModel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import TableRenderer.TableRenderer;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetInventoryQuantityGUI extends JPanel
{

	private JLabel  lblHeader, lblSearch, lblSearchBy, lblItemsFound,
			lblNumOfItemsFound;
	private JTextField tfSearch;
	private JButton btnViewAllItems, btnSubmit;
	private JRadioButton rdbtnPartNumber, rdbtnDescription;
	private ButtonGroup searchBy;
	private JScrollPane spQuantityTable;
	private String headers[] ={ "Part Number", "Description", 
                        "<html><center>Quantity<br>(Functional)</center></html>", 
                        "Counted","<html><center>Quantity<br>(Defective)</center></html>",
                        "Counted" };
	private DefaultTableModel table;
	private JTable setQuantityTable;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private GUIModel controller;

	
	public SetInventoryQuantityGUI(GUIModel temp)
	{
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Set Inventory Quantity");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 119, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search:");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 120, 78, 30);
		add(lblSearch);

		lblItemsFound = new JLabel("Item/s Found:");
		lblItemsFound.setFont(fntPlainText);
		lblItemsFound.setBounds(30, 160, 131, 30);
		add(lblItemsFound);

		lblNumOfItemsFound = new JLabel("0");
		lblNumOfItemsFound.setFont(fntPlainText);
		lblNumOfItemsFound.setBounds(165, 160, 250, 30);
		add(lblNumOfItemsFound);
                
                tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(110, 120, 360, 30);
		add(tfSearch);

		table = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				if (mColIndex == 3 || mColIndex == 5)
					return true;
				else
					return false;
			}
		};

		table.setRowCount(15);

		for (int i = 0; i < 6; i++)
		{
			table.addColumn(headers[i]);
		}

		setQuantityTable = new JTable(table)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				return new TableRenderer();
			}
		};
		
		setQuantityTable.getTableHeader().setFont(fntHeaderTableText);
		setQuantityTable.getTableHeader().setPreferredSize(new Dimension(100, 55));
		setQuantityTable.getTableHeader().setResizingAllowed(false);
		TableCellRenderer rend = setQuantityTable.getTableHeader().getDefaultRenderer();
		TableColumnModel tcm = setQuantityTable.getColumnModel();
		for (int j = 0; j < tcm.getColumnCount(); j += 1)
		{
			TableColumn tc = tcm.getColumn(j);
			TableCellRenderer rendCol = tc.getHeaderRenderer();
			if (rendCol == null)
				rendCol = rend;
			Component c = rendCol.getTableCellRendererComponent(
					setQuantityTable, tc.getHeaderValue(), false, false, 0, j);
			tc.setPreferredWidth(c.getPreferredSize().width);
		}
		setQuantityTable.setFont(fntPlainText);

		spQuantityTable = new JScrollPane(setQuantityTable);
		spQuantityTable.setBounds(30, 205, 935, 320);
		add(spQuantityTable);

		setQuantityTable.getParent().setBackground(setQuantityTable.getBackground());
		setQuantityTable.getTableHeader().setResizingAllowed(false);
		setQuantityTable.getTableHeader().setReorderingAllowed(false);
		setQuantityTable.setColumnSelectionAllowed(true);
		setQuantityTable.setRowSelectionAllowed(true);
		setQuantityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setQuantityTable.setRowHeight(30);
                
                rdbtnPartNumber = new JRadioButton("Part Number");
		rdbtnPartNumber.setFont(fntPlainText);
                rdbtnPartNumber.setBackground(SystemColor.textHighlight);
		rdbtnPartNumber.setBounds(155, 80, 169, 30);
		add(rdbtnPartNumber);

		rdbtnDescription = new JRadioButton("Description");
		rdbtnDescription.setFont(fntPlainText);
                rdbtnDescription.setBackground(SystemColor.textHighlight);
		rdbtnDescription.setBounds(341, 80, 157, 30);
		add(rdbtnDescription);
		
		searchBy = new ButtonGroup();
		searchBy.add(rdbtnPartNumber);
		searchBy.add(rdbtnDescription);
                
                btnViewAllItems = new JButton("View All Items");
		btnViewAllItems.setFont(fntPlainText);
		btnViewAllItems.setBounds(725, 150, 240, 40);
		add(btnViewAllItems);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(fntPlainText);
		btnSubmit.setBounds(855, 545, 110, 40);
		add(btnSubmit);
                btnSubmit.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToMainMenu();
                        }
                    });
	}
        
        public static void main(String args[]){
           GUIModel temp=new GUIModel();
           temp.changePanelToSetInventoryQuantity();
        }
}

package Reports;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderReportGUI extends JPanel {

	private JLabel lblHeader, lblSearchBy, lblSearch, lblItemsFound, 
                        lblNumOfItemsFound;
	private JTextField tfSearch;
        private String strHeader[] = { "Part Number", "Description", 
                        "<html><center>Stock<br>Minimum</center></html>",
			"<html><center>Quantity<br>(Functional)</center></html>", 
                        "<html><center>Last<br>Cost</center></html>", 
                        "<html><center>Rack<br>Location</center></html>" };
	private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbOrderReport;
	private JScrollPane spReportsTable;
        private JRadioButton rdbtnPartNumber, rdbtnDescription;
        private ButtonGroup searchBy;
	private JButton btnAddPurchaseTransaction, btnClose, btnViewAllItems;
	private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIController controller;
	

	
	public OrderReportGUI(GUIController temp) {
            
                controller=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);

		lblHeader = new JLabel("Order Report");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
		lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 130, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search:");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 120, 89, 30);
		add(lblSearch);

		lblItemsFound = new JLabel("Item/s Found: ");
		lblItemsFound.setFont(fntPlainText);
		lblItemsFound.setBounds(30, 160, 142, 30);
		add(lblItemsFound);

		lblNumOfItemsFound = new JLabel("0");
		lblNumOfItemsFound.setFont(fntPlainText);
		lblNumOfItemsFound.setBounds(165, 160, 250, 30);
		add(lblNumOfItemsFound);

		tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(110, 120, 339, 30);
		add(tfSearch);

                tbModel = new DefaultTableModel();
                
		tbModel.setRowCount(15);

		for (int i = 0; i < strHeader.length; i++) 
                {
			tbModel.addColumn(strHeader[i]);
		}

		tbOrderReport = new JTable(tbModel) 
                {
			public TableCellRenderer getCellRenderer(int row, int column) {
				return new TableRenderer();
			}
                        
                         public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
                         {
                            component = super.prepareRenderer(renderer, row, column);
                            modelRow = convertRowIndexToModel(row);
                            if (!isRowSelected(modelRow))
                            {
                                component.setBackground(Color.WHITE);
                            }else
                            {
                                component.setBackground(Color.yellow);
                            }
                            return component;
                            }
                        
		};
		
		tbOrderReport.getTableHeader().setFont(fntHeaderTableText);
		tbOrderReport.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbOrderReport.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbOrderReport.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbOrderReport.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbOrderReport, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbOrderReport.setFont(fntPlainText);
		
		spReportsTable = new JScrollPane(tbOrderReport);
		spReportsTable.setBounds(30, 205, 935, 320);
		add(spReportsTable);

		tbOrderReport.getParent().setBackground(tbOrderReport.getBackground());
		tbOrderReport.getTableHeader().setResizingAllowed(false);
		tbOrderReport.getTableHeader().setReorderingAllowed(false);
		tbOrderReport.setColumnSelectionAllowed(true);
		tbOrderReport.setRowSelectionAllowed(true);
		tbOrderReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbOrderReport.setRowHeight(30);
                
                rdbtnPartNumber = new JRadioButton("Part Number");
		rdbtnPartNumber.setFont(fntPlainText);
                rdbtnPartNumber.setBackground(SystemColor.textHighlight);
		rdbtnPartNumber.setSelected(true);
		rdbtnPartNumber.setBounds(144, 76, 157, 30);
		add(rdbtnPartNumber);

		rdbtnDescription = new JRadioButton("Description");
		rdbtnDescription.setFont(fntPlainText);
                rdbtnDescription.setBackground(SystemColor.textHighlight);
		rdbtnDescription.setBounds(299, 76, 157, 30);
		add(rdbtnDescription);

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnPartNumber);
		searchBy.add(rdbtnDescription);

		btnViewAllItems = new JButton("View All Items");
		btnViewAllItems.setFont(fntPlainText);
		btnViewAllItems.setBounds(725, 150, 240, 40);
		add(btnViewAllItems);

                btnAddPurchaseTransaction = new JButton("Add Purchase Transaction");
		btnAddPurchaseTransaction.setFont(fntPlainText);
		btnAddPurchaseTransaction.setBounds(455, 545, 310, 40);
		add(btnAddPurchaseTransaction);

		tbModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
                
		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
                btnClose.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToMainMenu();
                        }
                    });
		
		
	}
        
        public static void main(String args[]){
          GUIController temp=new GUIController();
           temp.changePanelToOrderReport();
        }
}

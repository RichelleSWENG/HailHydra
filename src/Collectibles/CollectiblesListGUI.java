package Collectibles;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.*;

import HailHydra.GUIModel;
import TableRenderer.TableRenderer;
import java.awt.Color;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollectiblesListGUI extends JPanel
{
        private JLabel  lblHeader, lblDisplay, lblCustomer, lblRange, lblCollectiblesFound,
                        lblNumOfPayablesFound, lblTo;
	private JTextField tfCustomer;
        private String strMonths[] = { "January", "February", "March", "April", "May", 
                "June", "July", "August", "September", "October", "November", "December" },
                strHeader[] = { "Customer Name", "Date", 
                "<html><center>Receipt<br>Number</center></html>",
		"<html><center>Original<br>Amount</center></html>", 
                "<html><center>Current<br>Balance</center></html>", "Status" };
        private JComboBox cmbToYear, cmbToMonth, cmbFromMonth, cmbFromYear;
	private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbCollectibles;
	private JScrollPane spTable;
        private JCheckBox chckbxActiveCollectibles, chckbxClosedPayables;
        private JButton btnClose, btnViewCollectible, btnViewAllCollectibles,
			btnAddCollectible;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
        private GUIModel guiController;
	
	public CollectiblesListGUI(GUIModel temp)
	{
                guiController=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);

                lblHeader = new JLabel("Collectibles");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);
                
		lblDisplay = new JLabel("Display: ");
		lblDisplay.setFont(fntPlainText);
		lblDisplay.setBounds(30, 73, 91, 30);
		add(lblDisplay);

		tfCustomer = new JTextField();
		tfCustomer.setFont(fntPlainText);
		tfCustomer.setBounds(140, 114, 550, 30);
		add(tfCustomer);

		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(fntPlainText);
		lblCustomer.setBounds(30, 114, 111, 30);
		add(lblCustomer);

		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 155, 80, 30);
		add(lblRange);
                
                lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(382, 155, 38, 30);
		add(lblTo);

		lblCollectiblesFound = new JLabel("Collectible/s Found: ");
		lblCollectiblesFound.setFont(fntPlainText);
		lblCollectiblesFound.setBounds(30, 196, 203, 30);
		add(lblCollectiblesFound);

		lblNumOfPayablesFound = new JLabel("0");
		lblNumOfPayablesFound.setFont(fntPlainText);
		lblNumOfPayablesFound.setBounds(220, 196, 250, 30);
		add(lblNumOfPayablesFound);

		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(104, 156, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(272, 156, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(419, 155, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(588, 155, 100, 30);
		add(cmbToYear);

		for (int i = 0; i < strMonths.length; i++)
		{
			cmbFromMonth.addItem(strMonths[i]);
			cmbToMonth.addItem(strMonths[i]);
		}

		Calendar date = Calendar.getInstance();
		int yr = date.get(Calendar.YEAR);
		for (int j = 0; j < 5; j++)
		{
			cmbFromYear.addItem(Integer.toString(yr - 2));
			cmbToYear.addItem(Integer.toString(yr - 2));
			yr++;
		}

		String yearBefore = String.valueOf(date.get(Calendar.YEAR) - 1);
		String yearToday = String.valueOf(date.get(Calendar.YEAR));
		cmbFromMonth.setSelectedIndex(0);
		cmbFromYear.setSelectedItem(yearBefore);
		cmbToMonth.setSelectedIndex(date.get(Calendar.MONTH));
		cmbToYear.setSelectedItem(yearToday);

                tbModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};
                tbModel.setRowCount(15);
                
		for (int i = 0; i < strHeader.length; i++)
		{
			tbModel.addColumn(strHeader[i]);
		}

		tbCollectibles = new JTable(tbModel)
		{
			public TableCellRenderer getCellRenderer(int row, int column)
			{
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

		tbCollectibles.getTableHeader().setFont(fntHeaderTableText);
		tbCollectibles.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbCollectibles.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbCollectibles.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbCollectibles.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
				tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbCollectibles, tbColumn.getHeaderValue(), false, false, 0,j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbCollectibles.setFont(fntPlainText);

		spTable = new JScrollPane(tbCollectibles);
		spTable.setBounds(30, 238, 935, 290);
		add(spTable);

		tbCollectibles.getParent().setBackground(tbCollectibles.getBackground());
		tbCollectibles.getTableHeader().setResizingAllowed(false);
		tbCollectibles.getTableHeader().setReorderingAllowed(false);
		tbCollectibles.setColumnSelectionAllowed(true);
		tbCollectibles.setRowSelectionAllowed(true);
		tbCollectibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCollectibles.setRowHeight(30);
                
		 chckbxActiveCollectibles = new JCheckBox("Active Collectibles");
		chckbxActiveCollectibles.setFont(fntPlainText);
                chckbxActiveCollectibles.setBackground(SystemColor.textHighlight);
		chckbxActiveCollectibles.setBounds(110, 73, 222, 30);
		add(chckbxActiveCollectibles);

		chckbxClosedPayables = new JCheckBox("Closed Collectibles");
		chckbxClosedPayables.setFont(fntPlainText);
                chckbxClosedPayables.setBackground(SystemColor.textHighlight);
		chckbxClosedPayables.setBounds(334, 73, 228, 30);
		add(chckbxClosedPayables);

                btnViewAllCollectibles = new JButton("View All Collectibles");
		btnViewAllCollectibles.setFont(fntPlainText);
		btnViewAllCollectibles.setBounds(725, 191, 240, 40);
		add(btnViewAllCollectibles);
                
		btnViewCollectible = new JButton("View Payment");
		btnViewCollectible.setFont(fntPlainText);
		btnViewCollectible.setBounds(30, 545, 203, 40);
		add(btnViewCollectible);

		btnAddCollectible = new JButton("Add Payment");
		btnAddCollectible.setFont(fntPlainText);
		btnAddCollectible.setBounds(436, 545, 213, 40);
		add(btnAddCollectible);
                btnAddCollectible.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToAddPaymentCollectibles();
                        }
                    });

		btnClose = new JButton("Close");
		btnClose.setFont(fntPlainText);
		btnClose.setBounds(855, 545, 110, 40);
		add(btnClose);
                btnClose.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToMainMenu();
                        }
                    });
	}
        
        public static void main(String args[]){
           GUIModel temp=new GUIModel();
           temp.changePanelToCollectibles();
        }
}

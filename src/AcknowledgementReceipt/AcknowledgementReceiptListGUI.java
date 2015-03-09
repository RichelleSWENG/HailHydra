package AcknowledgementReceipt;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import HailHydra.GUIModel;
import TableRenderer.TableRenderer;
import java.awt.Color;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcknowledgementReceiptListGUI extends JPanel 
{

	private JLabel  lblHeader, lblSearchBy, lblSearch, lblRange, 
                        lblReceiptsFound, lblNumOfReceiptsFound, lblTo;
        private JTextField tfSearch;
        private String strMonths[] = {"January", "February", "March", "April", 
                "May", "June", "July", "August", "September", "October", 
                "November", "December" }, strHeader[] = {"Customer Name", "Date", 
                "<html><center>Acknowledgement<br>Receipt Number</center></html>",
                "<html><center>Original Amount<br>Number</center></html>", 
                "<html><center>Current Balance<br>Number</center></html>" };
        private JComboBox cmbFromMonth, cmbFromYear, cmbToMonth, cmbToYear;
        private DefaultTableModel tbModel;
        private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
        private TableColumnModel tbColumnRenderer;
        private TableColumn tbColumn;
        private Component component;
	private JTable tbAckReceipt;
        private JScrollPane spTable;
        private JRadioButton rdbtnName, rdbtnAckReceiptNum;
        private ButtonGroup searchBy;
        private JButton btnClose, btnViewAckReceipt, btnViewAllReceipts, 
                btnAddAckReceipt;
        private Font fntPlainText, fntHeaderText, fntHeaderTableText;
        private int modelRow;
	private GUIModel guiController;
        private AcknowledgementReceiptController mainController;

	public AcknowledgementReceiptListGUI(GUIModel temp) {
		
                guiController=temp;
                setBounds(0, 0, 1000, 620);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
                
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                fntHeaderText = new Font("Arial", Font.BOLD, 40);
                fntHeaderTableText= new Font("Arial", Font.BOLD, 16);

                lblHeader = new JLabel("Acknowledgement Receipt");
		lblHeader.setFont(fntHeaderText);
		lblHeader.setBounds(30, 0, 600, 86);
		add(lblHeader);

		lblSearchBy = new JLabel("Search By: ");
		lblSearchBy.setFont(fntPlainText);
		lblSearchBy.setBounds(30, 80, 121, 30);
		add(lblSearchBy);

		lblSearch = new JLabel("Search:");
		lblSearch.setFont(fntPlainText);
		lblSearch.setBounds(30, 120, 93, 30);
		add(lblSearch);
		
		lblRange = new JLabel("Range:");
		lblRange.setFont(fntPlainText);
		lblRange.setBounds(30, 160, 85, 30);
		add(lblRange);
		
		lblTo = new JLabel("TO");
		lblTo.setFont(fntPlainText);
		lblTo.setBounds(384, 160, 30, 30);
		add(lblTo);
                
                lblReceiptsFound = new JLabel("Receipt/s Found: ");
		lblReceiptsFound.setFont(fntPlainText);
		lblReceiptsFound.setBounds(30, 200, 179, 30);
		add(lblReceiptsFound);

		lblNumOfReceiptsFound = new JLabel("0");
		lblNumOfReceiptsFound.setFont(fntPlainText);
		lblNumOfReceiptsFound.setBounds(193, 200, 250, 30);
		add(lblNumOfReceiptsFound);
                
                tfSearch = new JTextField();
		tfSearch.setFont(fntPlainText);
		tfSearch.setBounds(115, 120, 580, 30);
		add(tfSearch);
		
		cmbFromMonth = new JComboBox();
		cmbFromMonth.setFont(fntPlainText);
		cmbFromMonth.setBounds(109, 160, 155, 30);
		add(cmbFromMonth);

		cmbFromYear = new JComboBox();
		cmbFromYear.setFont(fntPlainText);
		cmbFromYear.setBounds(274, 160, 100, 30);
		add(cmbFromYear);

		cmbToMonth = new JComboBox();
		cmbToMonth.setFont(fntPlainText);
		cmbToMonth.setBounds(422, 160, 155, 30);
		add(cmbToMonth);

		cmbToYear = new JComboBox();
		cmbToYear.setFont(fntPlainText);
		cmbToYear.setBounds(592, 160, 100, 30);
		add(cmbToYear);

		for(int i = 0; i < strMonths.length; i++) {
			cmbFromMonth.addItem(strMonths[i]);
			cmbToMonth.addItem(strMonths[i]);
		}
		
		int yr = 1985;
		for (int j = 0; j < 100; j++) {
			cmbFromYear.addItem(new Integer(yr).toString());
			cmbToYear.addItem(new Integer(yr).toString());
			yr++;
		}
		
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

		tbAckReceipt = new JTable(tbModel) 
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
		
		tbAckReceipt.getTableHeader().setFont(fntHeaderTableText);
		tbAckReceipt.getTableHeader().setPreferredSize(new Dimension(100, 55));
		tbAckReceipt.getTableHeader().setResizingAllowed(false);
		tbCellRenderer = tbAckReceipt.getTableHeader().getDefaultRenderer();
		tbColumnRenderer = tbAckReceipt.getColumnModel();
		for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
		{
			tbColumn = tbColumnRenderer.getColumn(j);
			tbCellRendererColumn = tbColumn.getHeaderRenderer();
			if (tbCellRendererColumn == null)
                            tbCellRendererColumn = tbCellRenderer;
			component = tbCellRendererColumn.getTableCellRendererComponent(tbAckReceipt,
			tbColumn.getHeaderValue(), false, false, 0, j);
			tbColumn.setPreferredWidth(component.getPreferredSize().width);
		}
		tbAckReceipt.setFont(fntPlainText);

		spTable = new JScrollPane(tbAckReceipt);
		spTable.setBounds(30, 242, 935, 290);
		add(spTable);

		tbAckReceipt.getParent().setBackground(tbAckReceipt.getBackground());
		tbAckReceipt.getTableHeader().setResizingAllowed(false);
		tbAckReceipt.getTableHeader().setReorderingAllowed(false);
		tbAckReceipt.setColumnSelectionAllowed(true);
		tbAckReceipt.setRowSelectionAllowed(true);
		tbAckReceipt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbAckReceipt.setRowHeight(30);
		
                rdbtnName = new JRadioButton("Name");
                rdbtnName.setBackground(SystemColor.textHighlight);
		rdbtnName.setFont(fntPlainText);
		rdbtnName.setSelected(true);
		rdbtnName.setBounds(140, 80, 93, 30);
		add(rdbtnName);

		rdbtnAckReceiptNum = new JRadioButton("Acknowledgement Receipt Number");
                rdbtnAckReceiptNum.setBackground(SystemColor.textHighlight);
		rdbtnAckReceiptNum.setFont(fntPlainText);
		rdbtnAckReceiptNum.setBounds(235, 80, 401, 30);
		add(rdbtnAckReceiptNum);

		searchBy = new ButtonGroup();
		searchBy.add(rdbtnName);
		searchBy.add(rdbtnAckReceiptNum);
                
                btnViewAllReceipts = new JButton("View All Receipts");
		btnViewAllReceipts.setFont(fntPlainText);
		btnViewAllReceipts.setBounds(725, 190, 240, 40);
		add(btnViewAllReceipts);
		
		btnViewAckReceipt = new JButton("View Acknowledgement Receipt");
		btnViewAckReceipt.setFont(fntPlainText);
		btnViewAckReceipt.setBounds(30, 545, 358, 40);
		add(btnViewAckReceipt);
                btnViewAckReceipt.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToViewAcknowledgementReceipt();
                        }
                    });
                        
                btnAddAckReceipt = new JButton("Add Acknowledgement Receipt");
		btnAddAckReceipt.setFont(fntPlainText);
		btnAddAckReceipt.setBounds(450, 545, 358, 40);
		add(btnAddAckReceipt);
                btnAddAckReceipt.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                guiController.changePanelToAddAcknowledgementReceipt();
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
        
        public void setController(AcknowledgementReceiptController temp)
        {
            mainController=temp;
        }
        
        public static void main(String args[]){
            GUIModel temp=new GUIModel();
           temp.changePanelToAcknowledgementReceiptList();
        }
}

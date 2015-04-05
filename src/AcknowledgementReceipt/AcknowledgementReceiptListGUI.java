package AcknowledgementReceipt;

import HailHydra.GUIController;
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

import TableRenderer.TableRenderer;
import java.awt.Color;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Calendar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class AcknowledgementReceiptListGUI extends JPanel 
{

    private JLabel lblHeader, lblSearchBy, lblSearch, lblRange,
            lblReceiptsFound, lblNumOfReceiptsFound, lblTo;
    private JTextField tfSearch;
    private String strMonths[]
            = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"}, strHeader[]
            = {"Customer Name", "Date",
                "<html><center>Acknowledgement<br>Receipt Number</center></html>",
                "<html><center>Original Amount<br>Number</center></html>",
                "<html><center>Current Balance<br>Number</center></html>"};
    private JComboBox cmbFromMonth, cmbFromYear, cmbToMonth, cmbToYear;
    private DefaultTableModel tbModel;
    private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
    private TableColumnModel tbColumnRenderer;
    private TableColumn tbColumn;
    private Component component;
    private JTable tbAckReceipt;
    private JScrollPane spTable;
    private JRadioButton rdbtnName, rdbtnAckReceiptNum, rdbtnPartNumber;
    private ButtonGroup searchBy;
    private JButton btnClose, btnViewAckReceipt, btnViewAllReceipts,
            btnAddAckReceipt;
    private Font fntPlainText, fntHeaderText, fntHeaderTableText;
    private int modelRow;
    private GUIController guiController;
    private AcknowledgementReceiptController mainController;

    public AcknowledgementReceiptListGUI(GUIController temp) {

        guiController = temp;
        setBounds(0, 0, 1000, 620);
        setLayout(null);

        fntPlainText = new Font("Arial", Font.PLAIN, 21);
        fntHeaderText = new Font("Arial", Font.BOLD, 40);
        fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

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
        lblTo.setBounds(490, 160, 30, 30);
        add(lblTo);

        lblReceiptsFound = new JLabel("Receipt/s Found: ");
        lblReceiptsFound.setFont(fntPlainText);
        lblReceiptsFound.setBounds(30, 194, 165, 30);
        add(lblReceiptsFound);

        lblNumOfReceiptsFound = new JLabel("0");
        lblNumOfReceiptsFound.setFont(fntPlainText);
        lblNumOfReceiptsFound.setBounds(215, 194, 49, 30);
        add(lblNumOfReceiptsFound);

        tfSearch = new JTextField();
        tfSearch.setFont(fntPlainText);
        tfSearch.setBounds(215, 120, 580, 30);
        add(tfSearch);

        cmbFromMonth = new JComboBox();
        cmbFromMonth.setFont(fntPlainText);
        cmbFromMonth.setBounds(215, 160, 155, 30);
        add(cmbFromMonth);

        cmbFromYear = new JComboBox();
        cmbFromYear.setFont(fntPlainText);
        cmbFromYear.setBounds(380, 160, 100, 30);
        add(cmbFromYear);

        cmbToMonth = new JComboBox();
        cmbToMonth.setFont(fntPlainText);
        cmbToMonth.setBounds(530, 160, 155, 30);
        add(cmbToMonth);

        cmbToYear = new JComboBox();
        cmbToYear.setFont(fntPlainText);
        cmbToYear.setBounds(695, 160, 100, 30);
        add(cmbToYear);

        for (int i = 0; i < strMonths.length; i++) 
        {
            cmbFromMonth.addItem(strMonths[i]);
            cmbToMonth.addItem(strMonths[i]);
        }

        cmbToYear.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                tfSearch.setText("");
                mainController.searchbyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
            }

        });
        cmbToMonth.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                tfSearch.setText("");
                mainController.searchbyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
            }

        });
        cmbFromMonth.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                tfSearch.setText("");
                mainController.searchbyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
            }

        });
        cmbFromYear.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                tfSearch.setText(null);
                mainController.searchbyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
            }
        });

        tfSearch.getDocument().addDocumentListener(new DocumentListener() 
        {
            @Override
            public void insertUpdate(DocumentEvent de) 
            {
                try {
                    done();
                } catch (Exception ex) {

                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) 
            {
                try {
                    done();
                } catch (Exception ex) {

                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) 
            {
                try {
                    done();
                } catch (Exception ex) {

                }
            }

            public void done() throws Exception 
            {
                if (tfSearch.getText().length() > 0) {
                    if (rdbtnName.isSelected()) {
                        mainController.SearchSomething(tfSearch.getText(), 0, cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                    } else if (rdbtnAckReceiptNum.isSelected()) {
                        mainController.SearchSomething(tfSearch.getText(), 1, cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                    } else if (rdbtnPartNumber.isSelected()) {
                        mainController.SearchSomething(tfSearch.getText(), 2, cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                    }
                } else if (tfSearch.getText().length() == 0) //if nothing is typed display all
                {
                    mainController.searchbyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                }
            }
        });

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

        tbAckReceipt = new JTable(tbModel) {
            public TableCellRenderer getCellRenderer(int row, int column) 
            {
                return new TableRenderer();
            }

            public Component prepareRenderer(TableCellRenderer renderer,
                    int row, int column) 
            {
                component = super.prepareRenderer(renderer, row, column);
                modelRow = convertRowIndexToModel(row);
                if (!isRowSelected(modelRow)) {
                    component.setBackground(Color.WHITE);
                } else {
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
            if (tbCellRendererColumn == null) {
                tbCellRendererColumn = tbCellRenderer;
            }
            component = tbCellRendererColumn
                    .getTableCellRendererComponent(tbAckReceipt,
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
        rdbtnName.setFont(fntPlainText);
        rdbtnName.setSelected(true);
        rdbtnName.setBounds(215, 80, 93, 30);
        add(rdbtnName);
        rdbtnName.addActionListener(new ActionListener() {// Everytime All is selected
            public void actionPerformed(ActionEvent e) {
                tfSearch.setText(null);
            }
        });

        rdbtnAckReceiptNum = new JRadioButton("Acknowledgement Receipt Number");
        rdbtnAckReceiptNum.setFont(fntPlainText);
        rdbtnAckReceiptNum.setBounds(310, 80, 350, 30);
        add(rdbtnAckReceiptNum);
        rdbtnAckReceiptNum.addActionListener(new ActionListener() {// Everytime All is selected
            public void actionPerformed(ActionEvent e) {
                tfSearch.setText(null);
            }
        });

        rdbtnPartNumber = new JRadioButton("Part Number");
        rdbtnPartNumber.setFont(fntPlainText);
        rdbtnPartNumber.setBounds(620, 80, 170, 30);
        add(rdbtnPartNumber);
        rdbtnPartNumber.addActionListener(new ActionListener() {// Everytime All is selected
            public void actionPerformed(ActionEvent e) {
                tfSearch.setText(null);
            }
        });

        searchBy = new ButtonGroup();
        searchBy.add(rdbtnName);
        searchBy.add(rdbtnAckReceiptNum);
        searchBy.add(rdbtnPartNumber);

        btnViewAllReceipts = new JButton("View All Receipts");
        btnViewAllReceipts.setFont(fntPlainText);
        btnViewAllReceipts.setBounds(725, 194, 240, 40);
        add(btnViewAllReceipts);
        btnViewAllReceipts.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                ViewAll();
            }
        });

        btnViewAckReceipt = new JButton("View Acknowledgement Receipt");
        btnViewAckReceipt.setFont(fntPlainText);
        btnViewAckReceipt.setBounds(30, 545, 358, 40);
        add(btnViewAckReceipt);
        btnViewAckReceipt.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                int row;
                row = tbAckReceipt.getSelectedRow();
                if(row == -1 )
                    JOptionPane.showMessageDialog(null, "Please select an item.");
                else
                {
                    mainController.setReceiptTarget(mainController.getAR(tbAckReceipt.getValueAt(row, 0).toString()));
                    guiController.changePanelToViewAcknowledgementReceipt();
                }
                
            }
        });

        btnAddAckReceipt = new JButton("Add Acknowledgement Receipt");
        btnAddAckReceipt.setFont(fntPlainText);
        btnAddAckReceipt.setBounds(450, 545, 358, 40);
        add(btnAddAckReceipt);
        btnAddAckReceipt.addActionListener(new ActionListener()
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
        btnClose.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                guiController.changePanelToMainMenu();
            }
        });

    }

    public void setItemCount(int itemcount) 
    {
        lblNumOfReceiptsFound.setText(Integer.toString(itemcount));
    }

    public void setComboBox() 
    {
        cmbToYear.removeAllItems();
        cmbFromYear.removeAllItems();
        int cnt = 0;
        if(mainController.getMaxYear()!=null&&mainController.getMinYear()!=null)
        {
            for (int i = Integer.parseInt(mainController.getMinYear()); i <= Integer
                    .parseInt(mainController.getMaxYear()); i++) 
            {
                cmbToYear.addItem(i);
                cmbFromYear.addItem(i);
                cnt++;
            }
            cmbToYear.setSelectedIndex(cnt - 1);
            cmbFromYear.setSelectedIndex(0);
            cmbFromMonth.setSelectedIndex(0);
            cmbToMonth.setSelectedIndex(11);
        }
        else
        {
            cmbToYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
            cmbFromYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
        }
    }

    public void setTableModel(TableModel tbm) 
    { // Setting the Headers
        tbAckReceipt.setModel(tbm);
        JTableHeader th = tbAckReceipt.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < 5; i++) 
        {
            TableColumn tc = tcm.getColumn(i);
            tc.setHeaderValue(strHeader[i]);
        }
        th.repaint();
    }

    public void ViewAll() 
    {
        TableModel AllModel = mainController.getAllModel();
        tbAckReceipt.setModel(AllModel);

        JTableHeader th = tbAckReceipt.getTableHeader(); // Setting the Headers
        TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < 5; i++) 
        {
            TableColumn tc = tcm.getColumn(i);
            tc.setHeaderValue(strHeader[i]);
        }
        th.repaint();
        setComboBox();
    }

    public void setMainController(AcknowledgementReceiptController temp) 
    {
        mainController = temp;
    }

    public static void main(String args[]) 
    {
        GUIController temp = new GUIController();
        temp.changePanelToAcknowledgementReceipt();
    }
}
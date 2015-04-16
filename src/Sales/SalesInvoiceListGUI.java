package Sales;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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

import HailHydra.GUIController;
import TableRenderer.TableRenderer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class SalesInvoiceListGUI extends JPanel
{

    private JLabel lblHeader, lblSearchBy, lblSearch, lblRange,
            lblInvoicesFound, lblNumofTransactions, lblTo;
    private String strHeader[] =
    {
        "Customer Name", "Date",
        "<html><center>Sales Invoice<br>Number</center></html>",
        "<html><center>Original<br>Amount</center></html>",
        "<html><center>Current<br>Balance</center></html>"
    },
            strMonths[] =
            {
                "January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October",
                "November", "December"
            };
    private DefaultTableModel tbModel;
    private TableCellRenderer tbCellRenderer, tbCellRendererColumn;
    private TableColumnModel tbColumnRenderer;
    private TableColumn tbColumn;
    private Component component;
    private JTable tbSalesInvoice;
    private JScrollPane spSalesInvoice;
    private JRadioButton rdbtnCustomerName, rdbtnSalesInvoiceNo, rdbtnPartNumber;
    private ButtonGroup searchBy;
    private JTextField tfSearch;
    private JComboBox cmbToMonth, cmbToYear, cmbFromMonth, cmbFromYear;
    private JButton btnViewAllInvoices, btnAddSalesInvoice,
            btnViewSalesInvoice, btnClose;
    private Font fntPlainText, fntHeaderText, fntHeaderTableText;
    private int modelRow;
    private GUIController guiController;
    private SalesInvoiceController mainController;

    public SalesInvoiceListGUI(GUIController temp)
    {

        guiController = temp;
        setBounds(0, 0, 1000, 620);
        setLayout(null);

        fntPlainText = new Font("Arial", Font.PLAIN, 21);
        fntHeaderText = new Font("Arial", Font.BOLD, 40);
        fntHeaderTableText = new Font("Arial", Font.BOLD, 16);

        lblHeader = new JLabel("Sales Invoice");
        lblHeader.setFont(fntHeaderText);
        lblHeader.setBounds(30, 0, 600, 86);
        add(lblHeader);

        lblSearchBy = new JLabel("Search By: ");
        lblSearchBy.setFont(fntPlainText);
        lblSearchBy.setBounds(30, 80, 120, 30);
        add(lblSearchBy);

        lblSearch = new JLabel("Search: ");
        lblSearch.setFont(fntPlainText);
        lblSearch.setBounds(30, 160, 84, 30);
        add(lblSearch);

        lblRange = new JLabel("Range:");
        lblRange.setFont(fntPlainText);
        lblRange.setBounds(30, 120, 84, 30);
        add(lblRange);

        lblTo = new JLabel("TO");
        lblTo.setFont(fntPlainText);
        lblTo.setBounds(415, 120, 36, 30);
        add(lblTo);

        lblInvoicesFound = new JLabel("Invoice/s Found:");
        lblInvoicesFound.setFont(fntPlainText);
        lblInvoicesFound.setBounds(30, 200, 182, 30);
        add(lblInvoicesFound);

        lblNumofTransactions = new JLabel("0");
        lblNumofTransactions.setFont(fntPlainText);
        lblNumofTransactions.setBounds(200, 200, 46, 30);
        add(lblNumofTransactions);

        tbSalesInvoice = new JTable()
        {
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
            public TableCellRenderer getCellRenderer(int row, int column)
            {
                return new TableRenderer();
            }

            public Component prepareRenderer(TableCellRenderer renderer,
                    int row, int column)
            {
                component = super.prepareRenderer(renderer, row, column);
                modelRow = convertRowIndexToModel(row);
                if (!isRowSelected(modelRow))
                {
                    component.setBackground(Color.WHITE);
                } else
                {
                    component.setBackground(Color.yellow);
                }
                return component;
            }
        };

        tbSalesInvoice.getTableHeader().setFont(fntHeaderTableText);
        tbSalesInvoice.getTableHeader()
                .setPreferredSize(new Dimension(100, 55));
        tbSalesInvoice.getTableHeader().setResizingAllowed(false);
        tbCellRenderer = tbSalesInvoice.getTableHeader().getDefaultRenderer();
        tbColumnRenderer = tbSalesInvoice.getColumnModel();
        for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
        {
            tbColumn = tbColumnRenderer.getColumn(j);
            tbCellRendererColumn = tbColumn.getHeaderRenderer();
            if (tbCellRendererColumn == null)
            {
                tbCellRendererColumn = tbCellRenderer;
            }
            component = tbCellRendererColumn.getTableCellRendererComponent(
                    tbSalesInvoice, tbColumn.getHeaderValue(), false, false, 0,
                    j);
            tbColumn.setPreferredWidth(component.getPreferredSize().width);
        }
        tbSalesInvoice.setFont(fntPlainText);

        spSalesInvoice = new JScrollPane(tbSalesInvoice);
        spSalesInvoice.setBounds(30, 245, 934, 285);
        add(spSalesInvoice);

        tbSalesInvoice.getParent()
                .setBackground(tbSalesInvoice.getBackground());
        tbSalesInvoice.getTableHeader().setResizingAllowed(false);
        tbSalesInvoice.getTableHeader().setReorderingAllowed(false);
        tbSalesInvoice.setColumnSelectionAllowed(true);
        tbSalesInvoice.setRowSelectionAllowed(true);
        tbSalesInvoice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbSalesInvoice.setRowHeight(30);

        tfSearch = new JTextField();
        tfSearch.setFont(fntPlainText);
        tfSearch.setBounds(140, 160, 590, 30);
        add(tfSearch);

        cmbFromMonth = new JComboBox();
        cmbFromMonth.setFont(fntPlainText);
        cmbFromMonth.setBounds(140, 120, 148, 30);
        add(cmbFromMonth);

        cmbFromYear = new JComboBox();
        cmbFromYear.setFont(fntPlainText);
        cmbFromYear.setBounds(300, 120, 100, 30);
        add(cmbFromYear);

        cmbToMonth = new JComboBox();
        cmbToMonth.setFont(fntPlainText);
        cmbToMonth.setBounds(460, 120, 148, 30);
        add(cmbToMonth);

        cmbToYear = new JComboBox();
        cmbToYear.setFont(fntPlainText);
        cmbToYear.setBounds(620, 120, 100, 30);
        add(cmbToYear);

        for (int i = 0; i < strMonths.length; i++)
        {
            cmbFromMonth.addItem(strMonths[i]);
            cmbToMonth.addItem(strMonths[i]);
        }

        cmbToYear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tfSearch.setText("");
                mainController.ViewSalesInvoicebyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");

            }

        });
        cmbToMonth.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tfSearch.setText("");
                mainController.ViewSalesInvoicebyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");

            }

        });
        cmbFromMonth.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tfSearch.setText("");
                mainController.ViewSalesInvoicebyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");

            }

        });
        cmbFromYear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tfSearch.setText(null);
                mainController.ViewSalesInvoicebyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");

            }

        });

        tfSearch.getDocument().addDocumentListener(new DocumentListener()
        {
            public void insertUpdate(DocumentEvent de)
            {
                try
                {
                    done();
                } catch (Exception ex)
                {

                }
            }

            public void removeUpdate(DocumentEvent de)
            {
                try
                {
                    done();
                } catch (Exception ex)
                {

                }
            }

            public void changedUpdate(DocumentEvent de)
            {
                try
                {
                    done();
                } catch (Exception ex)
                {

                }
            }

            public void done() throws Exception
            {
                if (tfSearch.getText().length() > 0)
                {
                    if (rdbtnCustomerName.isSelected())
                    {
                        mainController.SearchSomething(tfSearch.getText(), 0, cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                    } else if (rdbtnSalesInvoiceNo.isSelected())
                    {
                        mainController.SearchSomething(tfSearch.getText(), 1, cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                    } else if (rdbtnPartNumber.isSelected())
                    {
                        mainController.SearchSomething(tfSearch.getText(), 2, cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                    }
                } else if (tfSearch.getText().length() == 0)  //if nothing is typed display all
                {
                    mainController.ViewSalesInvoicebyDate(cmbFromYear.getSelectedItem() + "-" + (cmbFromMonth.getSelectedIndex() + 1) + "-01", cmbToYear.getSelectedItem() + "-" + (cmbToMonth.getSelectedIndex() + 1) + "-31");
                }
            }
        });

        rdbtnCustomerName = new JRadioButton("Customer Name");
        rdbtnCustomerName.setFont(fntPlainText);
        rdbtnCustomerName.setSelected(true);
        rdbtnCustomerName.setBounds(140, 80, 182, 30);
        add(rdbtnCustomerName);
        rdbtnCustomerName.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tfSearch.setText(null);
            }
        });

        rdbtnSalesInvoiceNo = new JRadioButton("Sales Invoice Number");
        rdbtnSalesInvoiceNo.setFont(fntPlainText);
        rdbtnSalesInvoiceNo.setBounds(330, 80, 240, 30);
        add(rdbtnSalesInvoiceNo);
        rdbtnSalesInvoiceNo.addActionListener(new ActionListener()
        {// Everytime
            public void actionPerformed(ActionEvent e)
            {
                tfSearch.setText(null);
            }
        });

        rdbtnPartNumber = new JRadioButton("Part Number");
        rdbtnPartNumber.setBounds(580, 80, 148, 30);
        rdbtnPartNumber.setFont(fntPlainText);
        add(rdbtnPartNumber);
        rdbtnPartNumber.addActionListener(new ActionListener()
        {// Everytime												// selected
            public void actionPerformed(ActionEvent e)
            {
                tfSearch.setText(null);
            }
        });

        searchBy = new ButtonGroup();
        searchBy.add(rdbtnCustomerName);
        searchBy.add(rdbtnSalesInvoiceNo);
        searchBy.add(rdbtnPartNumber);

        btnViewAllInvoices = new JButton("View All Invoices");
        btnViewAllInvoices.setFont(fntPlainText);
        btnViewAllInvoices.setBounds(724, 195, 240, 40);
        add(btnViewAllInvoices);
        btnViewAllInvoices.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                rdbtnCustomerName.setSelected(true);
                ViewAll();
            }
        });

        btnViewSalesInvoice = new JButton("View Sales Invoice");
        btnViewSalesInvoice.setFont(fntPlainText);
        btnViewSalesInvoice.setBounds(30, 545, 244, 40);
        add(btnViewSalesInvoice);
        btnViewSalesInvoice.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int row;
                row = tbSalesInvoice.getSelectedRow();
                if (row == -1)
                {
                    JOptionPane.showMessageDialog(null, "Please select an item.");
                } else
                {
                    mainController.setTarget(mainController.getSI(tbSalesInvoice.getValueAt(row, 2).toString()));
                    guiController.changePanelToViewSalesInvoice();
                }

            }
        });

        btnAddSalesInvoice = new JButton("Add Sales Invoice");
        btnAddSalesInvoice.setFont(fntPlainText);
        btnAddSalesInvoice.setBounds(400, 545, 244, 40);
        add(btnAddSalesInvoice);
        btnAddSalesInvoice.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                guiController.changePanelToAddSalesInvoice();
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
        lblNumofTransactions.setText(Integer.toString(itemcount));
    }

    public void setComboBox()
    {
        cmbToYear.removeAllItems();
        cmbFromYear.removeAllItems();
        int cnt = 0;
        if (mainController.getMaxYear() != null && mainController.getMinYear() != null)
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
        } else
        {
            cmbToYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
            cmbFromYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
        }
    }

    public void ViewAll()
    {
        TableModel AllModel = mainController.getAllModel();
        tbSalesInvoice.setModel(AllModel);
        JTableHeader th = tbSalesInvoice.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < strHeader.length; i++) 
        {
                TableColumn tc = tcm.getColumn(i);
                tc.setHeaderValue(strHeader[i]);
        }
        tbCellRenderer = tbSalesInvoice.getTableHeader().getDefaultRenderer();
        tbColumnRenderer = tbSalesInvoice.getColumnModel();
        for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
        {
                tbColumn = tbColumnRenderer.getColumn(j);
                tbCellRendererColumn = tbColumn.getHeaderRenderer();
                if (tbCellRendererColumn == null)
                        tbCellRendererColumn = tbCellRenderer;
                component = tbCellRendererColumn.getTableCellRendererComponent(tbSalesInvoice, tbColumn.getHeaderValue(), false, false, 0,j);
                tbColumn.setPreferredWidth(component.getPreferredSize().width);
        }

        tbSalesInvoice.repaint();
        setComboBox();
    }

    public void setTableModel(TableModel tbm)
    { // Setting the Headers
        tbSalesInvoice.setModel(tbm);
        JTableHeader th = tbSalesInvoice.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < strHeader.length; i++) 
        {
                TableColumn tc = tcm.getColumn(i);
                tc.setHeaderValue(strHeader[i]);
        }
        tbCellRenderer = tbSalesInvoice.getTableHeader().getDefaultRenderer();
        tbColumnRenderer = tbSalesInvoice.getColumnModel();
        for (int j = 0; j < tbColumnRenderer.getColumnCount(); j += 1)
        {
                tbColumn = tbColumnRenderer.getColumn(j);
                tbCellRendererColumn = tbColumn.getHeaderRenderer();
                if (tbCellRendererColumn == null)
                        tbCellRendererColumn = tbCellRenderer;
                component = tbCellRendererColumn.getTableCellRendererComponent(tbSalesInvoice, tbColumn.getHeaderValue(), false, false, 0,j);
                tbColumn.setPreferredWidth(component.getPreferredSize().width);
        }

        tbSalesInvoice.repaint();
    }

    public void setMainController(SalesInvoiceController temp)
    {
        mainController = temp;
    }

    public static void main(String args[])
    {
        GUIController temp = new GUIController();
        temp.changePanelToSalesInvoice();
    }
}

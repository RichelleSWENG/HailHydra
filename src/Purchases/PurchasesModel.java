package Purchases;

import Classes.Company;
import Database.DBConnection;
import HailHydra.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class PurchasesModel
{

    protected Connection db;
    protected Statement statement;
    private int itemCount = 0;

    private ArrayList<Company> suppliers;
    private ArrayList<PTLineItem> items;
    private PTLineItemModel ptLineItemModel;

    public PurchasesModel(DBConnection db)
    {
        this.db = db.getConnection();
        suppliers = new ArrayList<>();
        items = new ArrayList<>();
        ptLineItemModel = new PTLineItemModel(db);
    }

    public ResultSet getDetail(String ID)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet getAllDetail()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id=purchasetransaction.company_id";
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet getAllDetailbyDate(String startDate, String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id=purchasetransaction.company_id AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet searchDetail(String field, String filter, String startDate, String endDate)
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "";
            if (filter.equalsIgnoreCase("name"))
            {
                sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id= purchasetransaction.company_id AND company.name LIKE '%" + field + "%' AND purchasetransaction.date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            } else if (filter.equalsIgnoreCase("transaction number"))
            {
                sql = "SELECT company.name, date, purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction WHERE company.company_id= purchasetransaction.company_id AND purchase_transaction_id LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                //System.out.println(sql);
            } else if (filter.equalsIgnoreCase("part number"))
            {
                sql = "SELECT company.name, date, purchasetransaction.purchase_transaction_id, original_amount, current_balance FROM company, purchasetransaction,ptlineitem WHERE company.company_id= purchasetransaction.company_id AND purchasetransaction.purchase_transaction_id=ptlineitem.purchase_transaction_id AND part_num LIKE '%" + field + "%' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
            }
            rs = statement.executeQuery(sql);
            rs.last();                        // Get Item Count
            itemCount = rs.getRow();
            rs.beforeFirst();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;

    }

    public void addDetail(PurchaseTransaction obj)
    {

        PurchaseTransaction pt = obj;
        try
        {

            statement = db.createStatement();
            String sql = "INSERT INTO purchasetransaction(purchase_transaction_id,company_id,date,original_amount,discount,ref_sales_invoice_num, ordered_by, po_num, received_by, receiving_notes, vat, delivery_receipt_num, current_balance, status) VALUES('" + pt.getPurchase_transaction_id() + "','" + pt.getCompany_id() + "','" + pt.getDate() + "','" + pt.getOriginal_amount() + "','" + pt.getDiscount() + "','" + pt.getRef_sales_invoice_num() + "','" + pt.getOrdered_by() + "','" + pt.getPo_num() + "','" + pt.getReceived_by() + "','" + pt.getReceiving_notes() + "','" + pt.getVat() + "','" + pt.getDelivery_receipt_num() + "','" + pt.getCurrent_balance() + "','" + pt.getStatus() + "')";
            //System.out.println(sql);
            statement.executeUpdate(sql);
            int i;
            for (i = 0; i < pt.getItems().size(); i++)
            {
                ptLineItemModel.addDetail(pt.getItems().get(i));
                ptLineItemModel.updateQuantity(pt.getItems().get(i).getPartNum(), pt.getItems().get(i).getQuantity() + getAvailQuantity(i));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void editDetail(PurchaseTransaction pt)
    {
        try
        {
            statement = db.createStatement();
            String sql;
            sql = "UPDATE purchasetransaction SET company_id = '" + pt.getCompany_id() + "', date = '" + pt.getDate() + "',original_amount = '" + pt.getOriginal_amount() + "',discount = '" + pt.getDiscount() + "', ref_sales_invoice_num = '" + pt.getRef_sales_invoice_num() + "', ordered_by = '" + pt.getOrdered_by() + "', po_num = '" + pt.getPo_num() + "', received_by = '" + pt.getReceived_by() + "', receiving_notes = '" + pt.getReceiving_notes() + "', vat = '" + pt.getVat() + "', delivery_receipt_num = '" + pt.getDelivery_receipt_num() + "', current_balance = '" + pt.getCurrent_balance() + "', status = '" + pt.getStatus() + "' WHERE purchase_transaction_id = '" + pt.getPurchase_transaction_id() + "'";
            statement.executeUpdate(sql);

            //Delete all items in PTLineItem
            statement = db.createStatement();
            sql = "DELETE FROM ptlineitem WHERE purchase_transaction_id = '" + pt.getPurchase_transaction_id() + "'";
            statement.executeUpdate(sql);

            //Add new items
            int i;
            for (i = 0; i < pt.getItems().size(); i++)
            {
                ptLineItemModel.addDetail(pt.getItems().get(i));
                ptLineItemModel.updateQuantity(pt.getItems().get(i).getPartNum(), pt.getItems().get(i).getQuantity() + getAvailQuantity(i));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteDetail(String ID)
    {
        try
        {
            statement = db.createStatement();
            String sql = "DELETE FROM purchasetransaction WHERE purchase_transaction_id='" + ID + "'";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    public TableModel myModel(ResultSet rs)
    {
        TableModel model = DbUtils.resultSetToTableModel(rs);
        return model;
    }

    public ResultSet getMinYear()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT MIN(YEAR(date)) FROM purchasetransaction";
            rs = statement.executeQuery(sql);

        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public ResultSet getMaxYear()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT MAX(YEAR(date)) FROM purchasetransaction";
            rs = statement.executeQuery(sql);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return rs;
    }

    public int getItemcount()
    {
        return this.itemCount;
    }

    public ArrayList<Company> getSuppliers()
    {
        suppliers = new ArrayList<>();
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM company WHERE type LIKE '%supplier%'";
            rs = statement.executeQuery(sql);
            Company tempSupplier;
            while (rs.next())
            {
                
                tempSupplier = new Company();
                tempSupplier.setStatus(rs.getString("status"));
                if (tempSupplier.getStatus().equals("Active"))
                {
                    tempSupplier.setId(rs.getInt("company_id"));
                    tempSupplier.setName(rs.getString("name"));
                    tempSupplier.setAddressLoc(rs.getString("address_location"));
                    tempSupplier.setAddressCity(rs.getString("address_city"));
                    tempSupplier.setAddressCountry(rs.getString("address_country"));
                    tempSupplier.setPostalCode(rs.getString("address_postal_code"));
                    tempSupplier.setPhone1(rs.getString("phone1"));
                    tempSupplier.setPhone2(rs.getString("phone2"));
                    tempSupplier.setPhone3(rs.getString("phone3"));
                    tempSupplier.setFaxNum(rs.getString("fax_num"));
                    tempSupplier.setWebsite(rs.getString("website"));
                    tempSupplier.setEmail(rs.getString("email"));
                    tempSupplier.setContactPerson(rs.getString("contact_person"));
                    tempSupplier.setCreditLimit(rs.getFloat("credit_limit"));
                    tempSupplier.setTerms(rs.getInt("terms"));
                    tempSupplier.setType(rs.getString("type"));
                    suppliers.add(tempSupplier);
                }
                
            }

        } catch (Exception e)
        {
            e.getMessage();
        }
        return suppliers;
    }

    public ArrayList<PTLineItem> getItems()
    {
        items = new ArrayList<>();
        ResultSet rs;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM item";
            rs = statement.executeQuery(sql);
            PTLineItem tempItem;
            while (rs.next())
            {
                tempItem = new PTLineItem();
                tempItem.setStatus(rs.getInt("status"));
                if (tempItem.getStatus() == 1)
                {
                    tempItem.setPartNum(rs.getString("part_num"));
                    tempItem.setDescription(rs.getString("description"));
                    tempItem.setPrice(rs.getFloat("last_cost"));
                    items.add(tempItem);
                    tempItem.setMinimum(rs.getInt("stock_minimum"));
                    tempItem.setQuantityFunc(rs.getInt("quantity_functional"));
                }
            }

        } catch (Exception e)
        {
            e.getMessage();
        }
        return items;
    }

    public Company getSupplier(int index)
    {
        return suppliers.get(index);
    }

    public PTLineItem getItem(int index)
    {
        return items.get(index);
    }

    public int getAvailQuantity(int index)
    {
        return items.get(index).getQuantityFunc() /*- items.get(index).getMinimum()*/;
    }

    public PurchaseTransaction getPT(String ID)
    {
        PurchaseTransaction pt = null;
        int company_id = -1;

        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT * FROM purchasetransaction WHERE purchase_transaction_id = '" + ID + "'";
            rs = statement.executeQuery(sql);

            if (rs.next())
            {
                pt = new PurchaseTransaction();
                pt.setPurchase_transaction_id(rs.getString("purchase_transaction_id"));
                pt.setDate(rs.getString("date"));
                pt.setOriginal_amount(rs.getFloat("original_amount"));
                pt.setPo_num(rs.getString("po_num"));
                pt.setOrdered_by(rs.getString("ordered_by"));
                pt.setReceived_by(rs.getString("received_by"));
                pt.setRef_sales_invoice_num(rs.getString("ref_sales_invoice_num"));
                pt.setReceiving_notes(rs.getString("receiving_notes"));
                pt.setDelivery_receipt_num(rs.getString("delivery_receipt_num"));
                pt.setDiscount(rs.getFloat("discount"));
                pt.setVat(rs.getFloat("vat"));
                pt.setCurrent_balance(rs.getFloat("current_balance"));
                pt.setStatus(rs.getString("status"));
                company_id = rs.getInt("company_id");
            }

            if (pt != null)
            {
                String query = "SELECT * FROM ptlineitem WHERE purchase_transaction_id = '" + pt.getPurchase_transaction_id() + "'";
                statement = db.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next())
                {
                    pt.addItem(new PTLineItem(pt.getPurchase_transaction_id(), rs.getInt("quantity"), rs.getString("part_num"), rs.getFloat("unit_price"), rs.getFloat("line_total")));
                }

                query = "SELECT * FROM company WHERE company_id = '" + company_id + "'";
                statement = db.createStatement();
                rs = statement.executeQuery(query);
                Company tempCustomer = new Company();
                if (rs.next())
                {
                    tempCustomer.setId(rs.getInt("company_id"));
                    tempCustomer.setName(rs.getString("name"));
                    tempCustomer.setAddressLoc(rs.getString("address_location"));
                    tempCustomer.setAddressCity(rs.getString("address_city"));
                    tempCustomer.setAddressCountry(rs.getString("address_country"));
                    tempCustomer.setPostalCode(rs.getString("address_postal_code"));
                    tempCustomer.setPhone1(rs.getString("phone1"));
                    tempCustomer.setPhone2(rs.getString("phone2"));
                    tempCustomer.setPhone3(rs.getString("phone3"));
                    tempCustomer.setFaxNum(rs.getString("fax_num"));
                    tempCustomer.setWebsite(rs.getString("website"));
                    tempCustomer.setEmail(rs.getString("email"));
                    tempCustomer.setContactPerson(rs.getString("contact_person"));
                    tempCustomer.setStatus(rs.getString("status"));
                    tempCustomer.setCreditLimit(rs.getFloat("credit_limit"));
                    tempCustomer.setTerms(rs.getInt("terms"));
                    tempCustomer.setType(rs.getString("type"));
                }
                pt.setCompany(tempCustomer);
            }
        } catch (Exception e)
        {
            e.getMessage();
        }

        return pt;
    }

    public String getLastPTID()
    {
        ResultSet rs = null;
        String PTid = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT purchase_transaction_id FROM purchasetransaction ORDER BY purchase_transaction_id DESC LIMIT 1;";
            rs = statement.executeQuery(sql);

            while (rs.next())
            {
                String tempID = rs.getString("purchase_transaction_id");
                PTid = tempID;
            }
        } catch (Exception e)
        {
            e.getMessage();
        }
        if (PTid == null)
        {
            return "null";
        } else
        {
            return PTid;
        }
    }

    public float getCurrentVat()
    {
        ResultSet rs = null;
        try
        {
            statement = db.createStatement();
            String sql = "SELECT vat_percentage FROM systeminfo WHERE system_info_id='1'";
            rs = statement.executeQuery(sql);

        } catch (Exception e)
        {
            e.getMessage();
        }
        try
        {
            if (!rs.next())
            {
                return 12;
            } else
            {
                return rs.getFloat("vat_percentage");
            }
        } catch (SQLException ex)
        {

        }
        return 12;
    }

}

package HailHydra;

import AccountProfile.AccountModel;
import AccountProfile.AccountProfileController;
import AccountProfile.AccountProfileListGUI;
import AccountProfile.AddAccountProfileGUI;
import AccountProfile.ModifyAccountProfileGUI;
import AccountProfile.ViewAccountProfileGUI;
import AcknowledgementReceipt.AckReceiptModel;
import AcknowledgementReceipt.AcknowledgementReceiptController;
import AcknowledgementReceipt.AcknowledgementReceiptListGUI;
import AcknowledgementReceipt.AddAcknowledgementReceiptGUI;
import AcknowledgementReceipt.ModifyAcknowledgementReceiptGUI;
import AcknowledgementReceipt.ViewAcknowledgementReceiptGUI;
import BasicSystemSettings.BasicSystemSettingsGUI;
import BasicSystemSettings.SystemController;
import BasicSystemSettings.SystemModel;
import Collectibles.AddPaymentCollectiblesGUI;
import Collectibles.CollectiblesController;
import Collectibles.CollectiblesListGUI;
import Collectibles.CollectiblesModel;
import Collectibles.PaymentCollectiblesController;
import Collectibles.PaymentCollectiblesModel;
import Collectibles.ViewPaymentCollectiblesGUI;
import CreditMemo.AddCreditMemoGUI;
import CreditMemo.CreditMemoController;
import CreditMemo.CreditMemoListGUI;
import CreditMemo.CreditMemoModel;
import CreditMemo.ViewCreditMemoGUI;
import Database.DBConnection;
import DebitMemo.AddDebitMemoGUI;
import DebitMemo.DebitMemoController;
import DebitMemo.DebitMemoListGUI;
import DebitMemo.DebitMemoModel;
import DebitMemo.ViewDebitMemoGUI;
import Inventory.AddItemProfileGUI;
import Inventory.InventoryController;
import Inventory.InventoryListGUI;
import Inventory.ItemModel;
import Inventory.LastCostController;
import Inventory.ModifyItemProfileGUI;
import Inventory.QuantityController;
import Inventory.SellingPriceController;
import Inventory.SetInventoryLastCostGUI;
import Inventory.SetInventoryQuantityGUI;
import Inventory.SetInventorySellingPriceGUI;
import Inventory.ViewItemProfileGUI;
import Login.LoginController;
import Login.LoginGUI;
import Login.LoginModel;
import Login.MainMenuGUI;
import Login.PaymentsGUI;
import Login.ProfilesGUI;
import Login.PurchasesGUI;
import Login.SalesGUI;
import Login.SystemSettingsGUI;
import ModifyAlert.FactoryModify;
import Payables.AddPaymentPayablesGUI;
import Payables.PayablesController;
import Payables.PayablesListGUI;
import Payables.PayablesModel;
import Payables.PaymentController;
import Payables.PaymentModel;
import Payables.ViewPaymentPayablesGUI;
import Purchases.AddPurchaseTransactionGUI;
import Purchases.ModifyPurchaseTransactionGUI;
import Purchases.PurchaseTransactionController;
import Purchases.PurchaseTransactionListGUI;
import Purchases.PurchasesModel;
import Purchases.ViewPurchaseTransactionGUI;
import Reports.CreditLimitReportGUI;
import Reports.OrderReportGUI;
import Reports.ReportController;
import Reports.ReportModel;
import Reports.TermsReportGUI;
import ReturnSlip.AddReturnSlipGUI;
import ReturnSlip.ReturnSlipController;
import ReturnSlip.ReturnSlipListGUI;
import ReturnSlip.ReturnSlipModel;
import ReturnSlip.ViewReturnSlipGUI;
import Sales.AddSalesInvoiceGUI;
import Sales.ModifySalesInvoiceGUI;
import Sales.SalesInvoiceController;
import Sales.SalesInvoiceListGUI;
import Sales.SalesInvoiceModel;
import Sales.ViewSalesInvoiceGUI;
import SystemAccount.AddBankAccountGUI;
import SystemAccount.AddCheckAccountGUI;
import SystemAccount.ModifyPasswordGUI;
import SystemAccount.ModifySystemProfileGUI;
import SystemAccount.SystemAccountController;
import SystemAccount.SystemAccountModel;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIController
{

    private HailHydraGUI frame;
    private MainMenuGUI main;
    private InventoryListGUI inventoryGUI;

    private AccountProfileController accountProfileController;
    private InventoryController inventoryController;
    private SystemAccountController systemAccountController;
    private AcknowledgementReceiptController ARController;
    private ReturnSlipController RSController;
    private PurchaseTransactionController PTController;
    private SalesInvoiceController SIController;
    
    private ItemModel inventoryModel;
    private DBConnection dbc;

    private JDialog modal;
    private CreditMemoController CMController;

    private DebitMemoController DMController;

    private boolean administrator;

    public GUIController()
    {
        administrator = true;

        dbc = new DBConnection();
        frame = new HailHydraGUI(dbc);

        inventoryModel = new ItemModel(dbc);
        inventoryGUI = new InventoryListGUI(this);
        inventoryController = new InventoryController(inventoryModel, inventoryGUI);

        modal = new JDialog(frame);

        
        if(new SystemController(new SystemModel(dbc)).getInfo()==null)
        {
             changePanelToBasicSystemSettings();
        }
        else 
        {
            changePanelToLogin();
        }

    }

    public void setTitle()
    {
        frame.setTitle();
    }
    public void setToEmployee()
    {
        administrator = false;
    }

    public void setToAdmin()
    {
        administrator = true;
    }

    public JPanel getContentPanel()
    {
        frame.getContentPanel().removeAll();
        return frame.getContentPanel();
    }

    public JPanel getSectionsPanel()
    {
        main.getSectionsPanel().removeAll();
        return main.getSectionsPanel();
    }

    public void frameRevalidate()
    {
        frame.validate();
        frame.repaint();
        frame.setVisible(true);

    }

    public void mainMenuRevalidate()
    {
        main.validate();
        main.repaint();
        main.setVisible(true);
    }

    public void dialogRevalidate(JPanel temp)
    {
        modal = new JDialog(frame);
        modal.add(temp);
        modal.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        modal.setResizable(false);
        modal.setModal(true);
        modal.setAlwaysOnTop(true);
        modal.setSize(650, 450);
        modal.setLocationRelativeTo(frame);
        modal.setVisible(true);
    }

    public void changePanelToMainMenu()
    {
        modal.dispose();
        main.setMainController(new LoginController(new LoginModel(dbc),main));
        getContentPanel().add(main);
        frameRevalidate();
    }

    public void changePanelToBasicSystemSettings()
    {
        main = new MainMenuGUI(this);
        BasicSystemSettingsGUI tempGUI = new BasicSystemSettingsGUI(this);
        tempGUI.setMainController(new SystemController(new SystemModel(dbc)));
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToLogin()
    {
        main = new MainMenuGUI(this);
        LoginGUI tempGUI = new LoginGUI(this);
        tempGUI.setMainController(new LoginController(new LoginModel(dbc)));
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void AccessRestricted()
    {
        JOptionPane.showMessageDialog(null, "<html><center>You do not have access to this feature.<br>Please contact the administrator.</center></html>");
    }

    public void changePanelToPayablesList()
    {
        PayablesListGUI tempGUI = new PayablesListGUI(this);
        tempGUI.setMainController(new PayablesController(new PayablesModel(dbc), tempGUI));
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddPaymentPayables()
    {
        if (administrator)
        {
            AddPaymentPayablesGUI tempGUI = new AddPaymentPayablesGUI(this);
            tempGUI.setMainController(new PaymentController(new PaymentModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewPaymentPayables(String id)
    {
        ViewPaymentPayablesGUI tempGUI = new ViewPaymentPayablesGUI(this);
        tempGUI.setMainController(new PaymentController(new PaymentModel(dbc)));
        tempGUI.setId(id);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToPurchaseTransactionList()
    {
        PurchaseTransactionListGUI tempGUI = new PurchaseTransactionListGUI(this);
        PTController = new PurchaseTransactionController(new PurchasesModel(dbc), tempGUI);
        tempGUI.setMainController(PTController);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddPurchaseTransaction()
    {
        if (administrator)
        {
            PTController = new PurchaseTransactionController(new PurchasesModel(dbc));
            AddPurchaseTransactionGUI tempGUI = new AddPurchaseTransactionGUI(this);
            tempGUI.setController(PTController);
            tempGUI.setViewComponents();
            //tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToModifyPurchaseTransaction()
    {
        if (administrator)
        {
            ModifyPurchaseTransactionGUI tempGUI = new ModifyPurchaseTransactionGUI(this);
            tempGUI.setController(PTController);
            tempGUI.setViewComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewPurchaseTransaction()
    {
        ViewPurchaseTransactionGUI tempGUI = new ViewPurchaseTransactionGUI(this);
        tempGUI.setController(PTController);
        tempGUI.setViewComponents();
        //tempGUI.setDataComponents();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToInventory() throws Exception
    {
        inventoryGUI.setMainController(inventoryController);
        inventoryGUI.ViewAll();
        getContentPanel().add(inventoryGUI);
        frameRevalidate();
    }

    public void changePanelToAddItemProfile()
    {
        if (administrator)
        {
            AddItemProfileGUI tempGUI = new AddItemProfileGUI(this);
            tempGUI.setMainController(inventoryController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewItemProfile() throws IOException
    {
        ViewItemProfileGUI tempGUI = new ViewItemProfileGUI(this, inventoryController);
        tempGUI.setMainController(inventoryController);
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToModifyItemProfile() throws IOException
    {
        if (administrator)
        {
            ModifyItemProfileGUI tempGUI = new ModifyItemProfileGUI(this, inventoryController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToSetInventoryLastCost()
    {
        if (administrator)
        {
            SetInventoryLastCostGUI tempGUI = new SetInventoryLastCostGUI(this);
            tempGUI.setMainController(new LastCostController(new ItemModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToSetInventorySellingPrice()
    {
        if (administrator)
        {
            SetInventorySellingPriceGUI tempGUI = new SetInventorySellingPriceGUI(this);
            tempGUI.setMainController(new SellingPriceController(new ItemModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToSetInventoryQuantity()
    {
        if (administrator)
        {
            SetInventoryQuantityGUI tempGUI = new SetInventoryQuantityGUI(this);
            tempGUI.setMainController(new QuantityController(new ItemModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToAcknowledgementReceipt()
    {
        AcknowledgementReceiptListGUI tempGUI = new AcknowledgementReceiptListGUI(this);
        ARController = new AcknowledgementReceiptController(new AckReceiptModel(dbc), tempGUI);
        tempGUI.setMainController(ARController);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddAcknowledgementReceipt()
    {
        if (administrator)
        {
            ARController = new AcknowledgementReceiptController(new AckReceiptModel(dbc));
            AddAcknowledgementReceiptGUI tempGUI = new AddAcknowledgementReceiptGUI(this);
            tempGUI.setMainController(ARController);
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewAcknowledgementReceipt()
    {
        ViewAcknowledgementReceiptGUI tempGUI = new ViewAcknowledgementReceiptGUI(this);
        tempGUI.setMainController(ARController);
        tempGUI.setViewComponents();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToModifyAcknowledgementReceipt()
    {
        if (administrator)
        {
            ModifyAcknowledgementReceiptGUI tempGUI = new ModifyAcknowledgementReceiptGUI(this);
            tempGUI.setMainController(ARController);
            tempGUI.setViewComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToCollectibles()
    {
        CollectiblesListGUI tempGUI = new CollectiblesListGUI(this);
        tempGUI.setMainController(new CollectiblesController(new CollectiblesModel(dbc), tempGUI));
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();

    }

    public void changePanelToSalesInvoice()
    {
        SalesInvoiceListGUI tempGUI = new SalesInvoiceListGUI(this);
        SIController = new SalesInvoiceController(new SalesInvoiceModel(dbc), tempGUI);
        tempGUI.setMainController(SIController);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddSalesInvoice()
    {
        if (administrator)
        {
            SIController = new SalesInvoiceController(new SalesInvoiceModel(dbc));
            AddSalesInvoiceGUI tempGUI = new AddSalesInvoiceGUI(this);
            tempGUI.setController(SIController);
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }

    }

    public void changePanelToViewSalesInvoice()
    {
        ViewSalesInvoiceGUI tempGUI = new ViewSalesInvoiceGUI(this);
        tempGUI.setMainController(SIController);
        tempGUI.setViewComponents();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToModifySalesInvoice()
    {
        if (administrator)
        {
            ModifySalesInvoiceGUI tempGUI = new ModifySalesInvoiceGUI(this);
            tempGUI.setMainController(SIController);
            tempGUI.setViewComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToAddPaymentCollectibles()
    {
        if (administrator)
        {
            AddPaymentCollectiblesGUI tempGUI = new AddPaymentCollectiblesGUI(this);
            tempGUI.setMainController(new PaymentCollectiblesController(new PaymentCollectiblesModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewPaymentCollectibles(String id, String type)
    {
        ViewPaymentCollectiblesGUI tempGUI = new ViewPaymentCollectiblesGUI(this);
        tempGUI.setMainController(new PaymentCollectiblesController(new PaymentCollectiblesModel(dbc)));
        tempGUI.setId(id, type);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAccountProfile()
    {
        AccountProfileListGUI tempGUI = new AccountProfileListGUI(this);
        accountProfileController = new AccountProfileController(new AccountModel(dbc), tempGUI);
        tempGUI.setMainController(accountProfileController);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddAccountProfile()
    {
        if (administrator)
        {
            Model tempModel = new AccountModel(dbc);
            AddAccountProfileGUI tempGUI = new AddAccountProfileGUI(this);
            tempGUI.setMainController(accountProfileController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewAccountProfile()
    {
        ViewAccountProfileGUI tempGUI = new ViewAccountProfileGUI(this, accountProfileController); // needed access to Account controller beacuse it contains what to display
        tempGUI.setMainController(accountProfileController);
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToModifyAccountProfile()
    {
        if (administrator)
        {
            ModifyAccountProfileGUI tempGUI = new ModifyAccountProfileGUI(this, accountProfileController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToCreditLimitReport()
    {
        CreditLimitReportGUI tempGUI = new CreditLimitReportGUI(this);
        tempGUI.setMainController(new ReportController(new ReportModel(dbc), tempGUI));
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToReturnSlip()
    {
        ReturnSlipListGUI tempGUI = new ReturnSlipListGUI(this);
        RSController = new ReturnSlipController(new ReturnSlipModel(dbc), tempGUI);
        tempGUI.setMainController(RSController);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddReturnSlip()
    {
        if (administrator)
        {
            AddReturnSlipGUI tempGUI = new AddReturnSlipGUI(this);
            tempGUI.setMainController(RSController);
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewReturnSlip()
    {
        ViewReturnSlipGUI tempGUI = new ViewReturnSlipGUI(this);
        tempGUI.setMainController(RSController);
        tempGUI.setViewComponents();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToOrderReport()
    {
        OrderReportGUI tempGUI = new OrderReportGUI(this);
        tempGUI.setMainController(new ReportController(new ReportModel(dbc), tempGUI));
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToTermsReport()
    {
        TermsReportGUI tempGUI = new TermsReportGUI(this);
        tempGUI.setMainController(new ReportController(new ReportModel(dbc), tempGUI));
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToDebitMemo()
    {
         DebitMemoListGUI tempGUI = new DebitMemoListGUI(this);
        //tempGUI.setRSController(RSController);
        DMController = new DebitMemoController(new DebitMemoModel(dbc),tempGUI);
        tempGUI.setMainController(DMController);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddDebitMemo()
    {

        if (administrator)
        {
            DMController = new DebitMemoController(new DebitMemoModel(dbc));
            AddDebitMemoGUI tempGUI = new AddDebitMemoGUI(this);
            tempGUI.setMainController(DMController);
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToViewDebitMemo()
    {       
        ViewDebitMemoGUI tempGUI = new ViewDebitMemoGUI(this);
        tempGUI.setMainController(DMController);
        tempGUI.setViewComponents();
        getContentPanel().add(tempGUI);
        frameRevalidate();
        
    }

    public void changePanelToCreditMemo()
    {
        ReturnSlipListGUI tempGUIList = new ReturnSlipListGUI(this);
        RSController = new ReturnSlipController(new ReturnSlipModel(dbc), tempGUIList);

        CreditMemoListGUI tempGUI = new CreditMemoListGUI(this);
        tempGUI.setRSController(RSController);
        CMController = new CreditMemoController(new CreditMemoModel(dbc), tempGUI);
        tempGUI.setMainController(CMController);
        tempGUI.ViewAll();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddCreditMemo()
    {
        if (administrator)
        {
            CreditMemoListGUI tempListGUI = new CreditMemoListGUI(this);

            CMController = new CreditMemoController(new CreditMemoModel(dbc), tempListGUI);
            AddCreditMemoGUI tempGUI = new AddCreditMemoGUI(this);
            tempGUI.setRSController(RSController);
            tempGUI.setMainController(CMController);

            tempGUI.setViewComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();

        } else
        {
            AccessRestricted();
        }

    }

    public void changePanelToViewCreditMemo()
    {
        ViewCreditMemoGUI tempGUI = new ViewCreditMemoGUI(this);
        tempGUI.setRSController(RSController);
        tempGUI.setMainController(CMController);
        tempGUI.setViewComponents();
        getContentPanel().add(tempGUI);
        frameRevalidate();
    }

    public void changePanelToAddBankAccount()
    {
        if (administrator)
        {
            if (systemAccountController == null)
            {
                systemAccountController = new SystemAccountController(new SystemAccountModel(dbc));
            }
            AddBankAccountGUI tempGUI = new AddBankAccountGUI(this, systemAccountController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToAddCheckAccount()
    {
        if (administrator)
        {
            if (systemAccountController == null)
            {
                systemAccountController = new SystemAccountController(new SystemAccountModel(dbc));
            }
            AddCheckAccountGUI tempGUI = new AddCheckAccountGUI(this, systemAccountController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToModifyPassword()
    {
        if (administrator)
        {
            ModifyPasswordGUI tempGUI = new ModifyPasswordGUI(this);
            tempGUI.setMainController(new SystemAccountController(new SystemAccountModel(dbc), tempGUI));
            dialogRevalidate(tempGUI);
        } else
        {
            AccessRestricted();
        }
    }

    public void changePanelToModifySystemProfile()
    {
        if (administrator)
        {
            ModifySystemProfileGUI tempGUI = new ModifySystemProfileGUI(this);
            tempGUI.setMainController(new SystemAccountController(new SystemAccountModel(dbc)));
            tempGUI.setDetails();
            dialogRevalidate(tempGUI);
        } else
        {
            AccessRestricted();
        }
    }

    public void getAlert(String type)
    {
        if (administrator)
        {
            FactoryModify fm = new FactoryModify();
            fm.createProduct(type, dbc);
        } else
        {
            AccessRestricted();
        }
    }

    /**
     * **MAIN MENU SECTION PANELS **
     */
    public void changePanelToPayments()
    {
        getSectionsPanel().add(new PaymentsGUI(this));
        mainMenuRevalidate();
    }

    public void changePanelToProfiles()
    {
        getSectionsPanel().add(new ProfilesGUI(this));
        mainMenuRevalidate();
    }

    public void changePanelToPurchases()
    {
        getSectionsPanel().add(new PurchasesGUI(this));
        mainMenuRevalidate();
    }

    public void changePanelToSales()
    {
        getSectionsPanel().add(new SalesGUI(this));
        mainMenuRevalidate();
    }

    public void changePanelToSystemSettings()
    {
        if (administrator)
        {
            getSectionsPanel().add(new SystemSettingsGUI(this));
            mainMenuRevalidate();
        } else
        {
            AccessRestricted();
        }

    }

    public static void main(String args[])
    {
        new GUIController();
    }

}

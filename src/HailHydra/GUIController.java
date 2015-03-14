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
import Collectibles.AddPaymentCollectiblesGUI;
import Collectibles.CollectiblesController;
import Collectibles.CollectiblesListGUI;
import Collectibles.CollectiblesModel;
import CreditMemo.AddCreditMemoGUI;
import CreditMemo.CreditMemoListGUI;
import Database.DBConnection;
import DebitMemo.AddDebitMemoGUI;
import DebitMemo.DebitMemoListGUI;
import Inventory.AddItemProfileGUI;
import Inventory.InventoryController;
import Inventory.InventoryListGUI;
import Inventory.SetInventoryPriceGUI;
import Inventory.SetInventoryQuantityGUI;
import Login.LoginGUI;
import Login.MainMenuGUI;
import Login.PaymentsGUI;
import Login.ProfilesGUI;
import Login.PurchasesGUI;
import Login.SalesGUI;
import Login.SystemSettingsGUI;
import Inventory.ItemModel;
import Inventory.ModifyItemProfileGUI;
import Inventory.ViewItemProfileGUI;
import Payables.AddPaymentPayablesGUI;
import Payables.PayablesController;
import Payables.PayablesListGUI;
import Payables.PayablesModel;
import Purchases.AddPurchaseTransactionGUI;
import Purchases.ModifyPurchaseTransactionGUI;
import Purchases.PurchaseTransactionController;
import ReturnSlip.AddReturnSlipGUI;
import Purchases.PurchaseTransactionListGUI;
import Purchases.PurchasesModel;
import Purchases.ViewPurchaseTransactionGUI;
import ReturnSlip.ReturnSlipListGUI;
import Reports.CreditLimitReportGUI;
import Reports.OrderReportGUI;
import Reports.TermsReportGUI;
import Sales.AddSalesInvoiceGUI;
import Sales.ModifySalesInvoiceGUI;
import Sales.SalesInvoiceController;
import Sales.SalesInvoiceListGUI;
import Sales.SalesInvoiceModel;
import Sales.ViewSalesInvoiceGUI;
import SystemAccount.AddBankAccountGUI;
import SystemAccount.AddCheckAccountGUI;
import SystemAccount.SystemAccountController;
import SystemAccount.SystemAccountModel;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JPanel;

public class GUIController 
{
    
    private HailHydraGUI frame;
    private MainMenuGUI main;
    private InventoryListGUI inventoryGUI;
    
    private MainController controller;
    private AccountProfileController accountProfileController;
    private InventoryController inventoryController;
    private SystemAccountController systemAccountController;
    
    private ItemModel inventoryModel;
    private DBConnection dbc;
    
    public GUIController()
    {
            dbc = new DBConnection();
            controller= new MainController(this);
            frame=new HailHydraGUI();
            
            inventoryModel = new ItemModel(dbc);
            inventoryGUI= new InventoryListGUI (this);
            inventoryController=new InventoryController(inventoryModel,inventoryGUI);
            
            
            changePanelToLogin();
    }
     
    public void setController(MainController temp){
            controller=temp;
    }
    
    public void login(String username, String password) throws SQLException{
            try {
                controller.login(username,password);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
    
    public void changePanelToMainMenu()
    {
            getContentPanel().add(main);
            frameRevalidate();
    }
    
    public void changePanelToLogin()
    {
            main=new MainMenuGUI(this);
            getContentPanel().add(new LoginGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToPayablesList()
    {
            PayablesListGUI tempGUI= new PayablesListGUI(this);
            tempGUI.setMainController(new PayablesController(new PayablesModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddPaymentPayables()
    {
            getContentPanel().add(new AddPaymentPayablesGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToPurchaseTransactionList()
    {
            PurchaseTransactionListGUI tempGUI= new PurchaseTransactionListGUI(this);
            tempGUI.setMainController(new PurchaseTransactionController(new PurchasesModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddPurchaseTransaction()
    {
            getContentPanel().add(new AddPurchaseTransactionGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToModifyPurchaseTransaction()
    {
            getContentPanel().add(new ModifyPurchaseTransactionGUI(this));
            frameRevalidate();
    }
    public void changePanelToViewPurchaseTransaction()
    {
            getContentPanel().add(new ViewPurchaseTransactionGUI(this));
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
            AddItemProfileGUI tempGUI= new AddItemProfileGUI(this);
            tempGUI.setMainController(inventoryController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewItemProfile() throws IOException
    {
            ViewItemProfileGUI tempGUI= new ViewItemProfileGUI(this,inventoryController);
            tempGUI.setMainController(inventoryController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToModifyItemProfile() throws IOException
    {
            ModifyItemProfileGUI tempGUI = new ModifyItemProfileGUI(this,inventoryController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToSetInventoryPrice()
    {
            getContentPanel().add(new SetInventoryPriceGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToSetInventoryQuantity()
    {
            getContentPanel().add(new SetInventoryQuantityGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToAcknowledgementReceipt()
    {       
            AcknowledgementReceiptListGUI tempGUI = new AcknowledgementReceiptListGUI(this);
            tempGUI.setMainController(new AcknowledgementReceiptController(new AckReceiptModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddAcknowledgementReceipt()
    {
            AddAcknowledgementReceiptGUI tempGUI = new AddAcknowledgementReceiptGUI(this);
            tempGUI.setMainController(new AcknowledgementReceiptController(new AckReceiptModel(dbc),null));
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewAcknowledgementReceipt()
    {
            ViewAcknowledgementReceiptGUI tempGUI = new ViewAcknowledgementReceiptGUI(this);
            tempGUI.setMainController(new AcknowledgementReceiptController(new AckReceiptModel(dbc),null));
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToModifyAcknowledgementReceipt()
    {
            ModifyAcknowledgementReceiptGUI tempGUI = new ModifyAcknowledgementReceiptGUI(this);
            tempGUI.setMainController(new AcknowledgementReceiptController(new AckReceiptModel(dbc),null));
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToCollectibles()
    {
            CollectiblesListGUI tempGUI= new CollectiblesListGUI(this);
            tempGUI.setMainController(new CollectiblesController(new CollectiblesModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
            
    }
    
    public void changePanelToSalesInvoice()
    {
            SalesInvoiceListGUI tempGUI= new SalesInvoiceListGUI(this);
            tempGUI.setMainController(new SalesInvoiceController(new SalesInvoiceModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddSalesInvoice()
    {
            AddSalesInvoiceGUI tempGUI= new AddSalesInvoiceGUI(this);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewSalesInvoice()
    {
            ViewSalesInvoiceGUI tempGUI= new ViewSalesInvoiceGUI(this);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToModifySalesInvoice()
    {
            ModifySalesInvoiceGUI tempGUI= new ModifySalesInvoiceGUI(this);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddPaymentCollectibles()
    {
            getContentPanel().add(new AddPaymentCollectiblesGUI(this));
            frameRevalidate();
    }
    public void changePanelToAccountProfile()
    {
            AccountProfileListGUI tempGUI= new AccountProfileListGUI(this);
            tempGUI.setMainController(new AccountProfileController(new AccountModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddAccountProfile()
    {
            Model tempModel = new AccountModel(dbc);
            AddAccountProfileGUI tempGUI= new AddAccountProfileGUI(this);
            tempGUI.setMainController(accountProfileController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewAccountProfile()
    {
            ViewAccountProfileGUI tempGUI = new ViewAccountProfileGUI (this,accountProfileController); // needed access to Account controller beacuse it contains what to display
            //tempGUI.setMainController(AccountProfileController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToModifyAccountProfile()
    {
            ModifyAccountProfileGUI tempGUI = new ModifyAccountProfileGUI(this,accountProfileController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToCreditLimitReport()
    {
            getContentPanel().add(new CreditLimitReportGUI(this));
            frameRevalidate();
    }
    public void changePanelToReturnSlip()
    {
            getContentPanel().add(new ReturnSlipListGUI(this));
            frameRevalidate();
    }
    public void changePanelToAddReturnSlip()
    {
            getContentPanel().add(new AddReturnSlipGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToOrderReport()
    {
            getContentPanel().add(new OrderReportGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToTermsReport()
    {
            getContentPanel().add(new TermsReportGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToDebitMemo()
    {
            getContentPanel().add(new DebitMemoListGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToAddDebitMemo()
    {
            getContentPanel().add(new AddDebitMemoGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToCreditMemo()
    {
            getContentPanel().add(new CreditMemoListGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToAddCreditMemo()
    {
            AddCreditMemoGUI tempGUI = new AddCreditMemoGUI(this);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    public void changePanelToAddBankAccount()
    {
            if (systemAccountController == null)
                systemAccountController = new SystemAccountController(new SystemAccountModel(dbc));
            AddBankAccountGUI tempGUI = new AddBankAccountGUI(this, systemAccountController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    public void changePanelToAddCheckAccount()
    {
             if (systemAccountController == null)
                systemAccountController = new SystemAccountController(new SystemAccountModel(dbc));
            AddCheckAccountGUI tempGUI = new AddCheckAccountGUI(this, systemAccountController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    /****MAIN MENU SECTION PANELS ***/
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
            getSectionsPanel().add(new SystemSettingsGUI());
            mainMenuRevalidate();
    }
    
    public static void main(String args[]){
        new GUIController();
        
    }
    
}

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
import ModifyAlertVAT.FactoryModify;
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
import java.awt.Dialog;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JDialog;
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
    private AcknowledgementReceiptController ARController;
    private PurchaseTransactionController PTController;
    
    private ItemModel inventoryModel;
    private DBConnection dbc;
    
    private JDialog modal;
    
    public GUIController()
    {
            dbc = new DBConnection();
            controller= new MainController(this);
            frame=new HailHydraGUI();
            
            inventoryModel = new ItemModel(dbc);
            inventoryGUI= new InventoryListGUI (this);
            inventoryController = new InventoryController(inventoryModel,inventoryGUI);
            
           
            modal= new JDialog(frame);
            
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
    
    public void dialogRevalidate(JPanel temp){
        modal= new JDialog(frame);
        modal.add(temp);
        modal.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        modal.setModal(true);
        modal.setAlwaysOnTop(true);
        modal.setSize(650,450);
        modal.setLocationRelativeTo(frame);
        modal.setVisible(true);
    }
    
    public void changePanelToMainMenu()
    {
            modal.dispose();
            getContentPanel().add(main);
            frameRevalidate();
    }
    
    public void changePanelToLogin()
    {
            main=new MainMenuGUI(this);
            LoginGUI tempGUI =new LoginGUI(this);
            tempGUI.setMainController(new LoginController(new LoginModel(dbc)));
            getContentPanel().add(tempGUI);
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
            AddPaymentPayablesGUI tempGUI = new AddPaymentPayablesGUI(this);
            tempGUI.setMainController(new PaymentController(new PaymentModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewPaymentPayables()
    {
            ViewPaymentPayablesGUI tempGUI = new ViewPaymentPayablesGUI(this);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToPurchaseTransactionList()
    {       
            PurchaseTransactionListGUI tempGUI= new PurchaseTransactionListGUI(this);
            PTController = new PurchaseTransactionController(new PurchasesModel(dbc),tempGUI);
            tempGUI.setMainController(PTController);
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddPurchaseTransaction()
    {
            AddPurchaseTransactionGUI tempGUI= new AddPurchaseTransactionGUI(this);
            tempGUI.setController(PTController);
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToModifyPurchaseTransaction()
    {
            getContentPanel().add(new ModifyPurchaseTransactionGUI(this));
            frameRevalidate();
    }
    public void changePanelToViewPurchaseTransaction()
    {
            ViewPurchaseTransactionGUI tempGUI = new ViewPurchaseTransactionGUI(this);
            tempGUI.setController(PTController);
            tempGUI.setViewComponents();
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
    
    public void changePanelToSetInventoryLastCost()
    {
            SetInventoryLastCostGUI tempGUI = new SetInventoryLastCostGUI(this);
            tempGUI.setMainController(new LastCostController(new ItemModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToSetInventorySellingPrice()
    {
            SetInventorySellingPriceGUI tempGUI=new SetInventorySellingPriceGUI(this);
            tempGUI.setMainController(new SellingPriceController(new ItemModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToSetInventoryQuantity()
    {
            SetInventoryQuantityGUI tempGUI = new SetInventoryQuantityGUI(this);
            tempGUI.setMainController(new QuantityController(new ItemModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAcknowledgementReceipt()
    {       
            AcknowledgementReceiptListGUI tempGUI = new AcknowledgementReceiptListGUI(this);
            ARController = new AcknowledgementReceiptController(new AckReceiptModel(dbc),tempGUI);
            tempGUI.setMainController(ARController);
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddAcknowledgementReceipt()
    {
            AddAcknowledgementReceiptGUI tempGUI = new AddAcknowledgementReceiptGUI(this);
            tempGUI.setMainController(ARController);
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
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
            tempGUI.setController(new SalesInvoiceController(new SalesInvoiceModel(dbc), null));
            tempGUI.setDataComponents();
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
            AddPaymentCollectiblesGUI tempGUI= new AddPaymentCollectiblesGUI(this);
            tempGUI.setMainController(new PaymentCollectiblesController(new PaymentCollectiblesModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewPaymentCollectibles()
    {
    		getContentPanel().add(new ViewPaymentCollectiblesGUI(this));
    		frameRevalidate();
    }
    
    public void changePanelToAccountProfile()
    {
            AccountProfileListGUI tempGUI= new AccountProfileListGUI(this);
            accountProfileController = new AccountProfileController(new AccountModel(dbc),tempGUI);
            tempGUI.setMainController(accountProfileController);
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
            tempGUI.setMainController(accountProfileController);
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
            CreditLimitReportGUI tempGUI = new CreditLimitReportGUI(this);
            tempGUI.setMainController(new ReportController(new ReportModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    public void changePanelToReturnSlip()
    {
            ReturnSlipListGUI tempGUI = new ReturnSlipListGUI(this);
            tempGUI.setMainController(new ReturnSlipController(new ReturnSlipModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddReturnSlip()
    {
            AddReturnSlipGUI tempGUI = new AddReturnSlipGUI(this);
            tempGUI.setMainController(new ReturnSlipController(new ReturnSlipModel(dbc), null));
            tempGUI.setDataComponents();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewReturnSlip()
    {
            getContentPanel().add(new ViewReturnSlipGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToOrderReport()
    {
            OrderReportGUI tempGUI=new OrderReportGUI(this);
            tempGUI.setMainController(new ReportController(new ReportModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToTermsReport()
    {
            TermsReportGUI tempGUI = new TermsReportGUI(this);
            tempGUI.setMainController(new ReportController(new ReportModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToDebitMemo()
    {
            DebitMemoListGUI tempGUI = new DebitMemoListGUI(this);
            tempGUI.setMainController(new DebitMemoController(new DebitMemoModel(dbc),tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddDebitMemo()
    {
            getContentPanel().add(new AddDebitMemoGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToViewDebitMemo()
    {
            getContentPanel().add(new ViewDebitMemoGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToCreditMemo()
    {
            CreditMemoListGUI tempGUI = new CreditMemoListGUI(this);
            tempGUI.setMainController(new CreditMemoController(new CreditMemoModel(dbc), tempGUI));
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddCreditMemo()
    {
            AddCreditMemoGUI tempGUI = new AddCreditMemoGUI(this);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewCreditMemo()
    {
            ViewCreditMemoGUI tempGUI= new ViewCreditMemoGUI(this);
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
    
    public void changePanelToModifyPassword()
    {
            ModifyPasswordGUI tempGUI=new ModifyPasswordGUI(this);
            tempGUI.setMainController(new SystemAccountController(new SystemAccountModel(dbc),tempGUI));
            dialogRevalidate(tempGUI);
    }
    
    public void changePanelToModifySystemProfile()
    {
            ModifySystemProfileGUI tempGUI =new ModifySystemProfileGUI(this);
            tempGUI.setMainController(new SystemAccountController(new SystemAccountModel(dbc)));
            tempGUI.setDetails();
            dialogRevalidate(tempGUI);
    }
    
    public void getAlert(String type)
    {
    	FactoryModify fm= new FactoryModify();
    	fm.createProduct(type,dbc);
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
            getSectionsPanel().add(new SystemSettingsGUI(this));
            mainMenuRevalidate();
    }
    
    
    
    public static void main(String args[]){
        new GUIController();
        
    }
    
}

package HailHydra;

import AccountProfile.AccountModel;
import AccountProfile.AccountProfileController;
import AccountProfile.AccountProfileListGUI;
import AccountProfile.AddAccountProfileGUI;
import AccountProfile.ModifyAccountProfileGUI;
import AccountProfile.ViewAccountProfileGUI;
import AcknowledgementReceipt.AcknowledgementReceiptListGUI;
import AcknowledgementReceipt.AddAcknowledgementReceiptGUI;
import AcknowledgementReceipt.ModifyAcknowledgementReceiptGUI;
import AcknowledgementReceipt.ViewAcknowledgementReceiptGUI;
import Collectibles.AddPaymentCollectiblesGUI;
import Collectibles.CollectiblesListGUI;
import CreditMemo.AddCreditMemoGUI;
import CreditMemo.CreditMemoListGUI;
import Database.DBConnection;
import DebitMemo.AddDebitMemoGUI;
import DebitMemo.DebitMemoListGUI;
import Inventory.AddItemProfileGUI;
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
import Payables.AddPaymentPayablesGUI;
import Payables.PayablesListGUI;
import Purchases.AddPurchaseTransactionGUI;
import ReturnSlip.AddReturnSlipGUI;
import Purchases.PurchaseTransactionListGUI;
import ReturnSlip.ReturnSlipListGUI;
import Reports.CreditLimitReportGUI;
import Reports.OrderReportGUI;
import Reports.TermsReportGUI;
import Sales.AddSalesInvoiceGUI;
import Sales.ModifySalesInvoiceGUI;
import Sales.SalesInvoiceListGUI;
import Sales.ViewSalesInvoiceGUI;
import SystemAccount.AddBankAccountGUI;
import SystemAccount.AddCheckAccountGUI;
import SystemAccount.SystemAccountController;
import SystemAccount.SystemAccountModel;
import java.sql.SQLException;
import javax.swing.JPanel;

public class GUIController 
{
    
    private HailHydraGUI frame;
    private MainMenuGUI main;
    private MainController controller;
    private AccountProfileController AccountProfileController;
    private SystemAccountController systemAccountController;
    private DBConnection dbc;
    
    public GUIController()
    {
            dbc = new DBConnection();
            controller= new MainController(this);
            frame=new HailHydraGUI();
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
            PayablesListGUI temp= new PayablesListGUI(this);
            getContentPanel().add(temp);
            frameRevalidate();
    }
    
    public void changePanelToAddPaymentPayables()
    {
            getContentPanel().add(new AddPaymentPayablesGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToPurchaseTransactionList()
    {
            getContentPanel().add(new PurchaseTransactionListGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToAddPurchaseTransaction()
    {
            getContentPanel().add(new AddPurchaseTransactionGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToInventory()
    {
            getContentPanel().add(new InventoryListGUI(this));
            frameRevalidate();
    }
    public void changePanelToAddItemProfile()
    {
            getContentPanel().add(new AddItemProfileGUI(this));
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
            getContentPanel().add(new AcknowledgementReceiptListGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToAddAcknowledgementReceipt()
    {
            getContentPanel().add(new AddAcknowledgementReceiptGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToViewAcknowledgementReceipt()
    {
            getContentPanel().add(new ViewAcknowledgementReceiptGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToModifyAcknowledgementReceipt()
    {
            getContentPanel().add(new ModifyAcknowledgementReceiptGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToCollectibles()
    {
            getContentPanel().add(new CollectiblesListGUI(this));
            frameRevalidate();
    }
    
    public void changePanelToSalesInvoice()
    {
            SalesInvoiceListGUI tempGUI= new SalesInvoiceListGUI(this);
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
            AccountProfileController tempController=new AccountProfileController(new AccountModel(dbc),tempGUI); 
            this.AccountProfileController = tempController;
            //please connect controller with gui 
            tempGUI.setMainController(AccountProfileController);
            tempGUI.ViewAll();
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToAddAccountProfile()
    {
            Model tempModel = new AccountModel(dbc);
            AddAccountProfileGUI tempGUI= new AddAccountProfileGUI(this);
            tempGUI.setMainController(AccountProfileController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToViewAccountProfile()
    {
            ViewAccountProfileGUI tempGUI = new ViewAccountProfileGUI (this,AccountProfileController); // needed access to Account controller beacuse it contains what to display
            //tempGUI.setMainController(AccountProfileController);
            getContentPanel().add(tempGUI);
            frameRevalidate();
    }
    
    public void changePanelToModifyAccountProfile()
    {
            ModifyAccountProfileGUI tempGUI = new ModifyAccountProfileGUI(this,AccountProfileController);
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

package Login;

import HailHydra.GUIController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PaymentsGUI extends JPanel{
    
    private JButton btnPayables, btnCollectibles, btnAddCheckAccount, btnAddBankAccount, btnCreditLimitReport, btnTermsReport;
    private Font fntPlainText;
    private GUIController controller;
    
    public PaymentsGUI(GUIController temp){
                controller=temp;
                setBounds(5, 5, 580, 390);
		setLayout(null);
		
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                
                btnPayables = new JButton("Payables");
		btnPayables.setFont(fntPlainText);
		btnPayables.setBounds(165, 30, 250, 40);
		add(btnPayables);
                btnPayables.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToPayablesList();
                        }
                    });
                
                btnCollectibles = new JButton("Collectibles");
		btnCollectibles.setFont(fntPlainText);
		btnCollectibles.setBounds(165, 90, 250, 40);
		add(btnCollectibles);
                btnCollectibles.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToCollectibles();
                        }
                    });
                
                btnAddCheckAccount = new JButton("Add Check Account");
		btnAddCheckAccount.setFont(fntPlainText);
		btnAddCheckAccount.setBounds(165, 150, 250, 40);
		add(btnAddCheckAccount);
                btnAddCheckAccount.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToAddCheckAccount();
                        }
                    });
                
                btnAddBankAccount = new JButton("Add Bank Account");
		btnAddBankAccount.setFont(fntPlainText);
		btnAddBankAccount.setBounds(165, 210, 250, 40);
		add(btnAddBankAccount);
                btnAddBankAccount.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToAddBankAccount();
                        }
                    });
                
                btnCreditLimitReport = new JButton("Credit Limit Report");
		btnCreditLimitReport.setFont(fntPlainText);
		btnCreditLimitReport.setBounds(165, 270, 250, 40);
		add(btnCreditLimitReport);
                btnCreditLimitReport.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToCreditLimitReport();
                        }
                    });
                
                btnTermsReport = new JButton("Terms Report");
		btnTermsReport.setFont(fntPlainText);
		btnTermsReport.setBounds(165, 330, 250, 40);
		add(btnTermsReport);
                btnTermsReport.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                                controller.changePanelToTermsReport();
                        }
                    });
    }
    
}

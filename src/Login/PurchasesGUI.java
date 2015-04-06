package Login;

import HailHydra.GUIController;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchasesGUI extends JPanel 
{

	private JButton purchaseTransBtn, creditMemoBtn, returnSlipBtn, orderReportBtn;
	private Font fntPlainText;
        private GUIController controller;
        
	public PurchasesGUI(GUIController temp) 
	{
                controller=temp;
                
		setBounds(5, 5, 580, 390);
		setLayout(null);
		
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                
		purchaseTransBtn = new JButton("Purchase Transaction");
		purchaseTransBtn.setFont(fntPlainText);
		purchaseTransBtn.setBounds(165, 90, 260, 40);
		add(purchaseTransBtn);
                purchaseTransBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                              controller.changePanelToPurchaseTransactionList();
                       
                        }
                    });
		
                orderReportBtn = new JButton("Order Report");
		orderReportBtn.setFont(fntPlainText);
		orderReportBtn.setBounds(165, 150, 260, 40);
		add(orderReportBtn);
                orderReportBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                              controller.changePanelToOrderReport();
                       
                        }
                    });
                
                returnSlipBtn = new JButton("Return Slip");
		returnSlipBtn.setFont(fntPlainText);
		returnSlipBtn.setBounds(165, 210, 260, 40);
		add(returnSlipBtn);
                returnSlipBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                              controller.changePanelToReturnSlip();
                        }
                    });
                
		creditMemoBtn = new JButton("Credit Memo");
		creditMemoBtn.setFont(fntPlainText);
		creditMemoBtn.setBounds(165,270 , 260, 40);
		add(creditMemoBtn);
                creditMemoBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                              controller.changePanelToCreditMemo();
                       
                        }
                    });
		
		
		
		
	}

}

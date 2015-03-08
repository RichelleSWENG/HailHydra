package Login;

import HailHydra.GUIModel;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchasesGUI extends JPanel {

	private JButton purchaseTransBtn, creditMemoBtn, returnSlipBtn, orderReportBtn;
	private Font fntPlainText;
        private GUIModel controller;
        
	public PurchasesGUI(GUIModel temp) {
                controller=temp;
                
		setBounds(0, 0, 700, 400);
		setBackground(SystemColor.controlHighlight);
		setLayout(null);
		
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                
		purchaseTransBtn = new JButton("Purchase Transactions");
		purchaseTransBtn.setFont(fntPlainText);
		purchaseTransBtn.setBounds(170, 90, 260, 40);
		add(purchaseTransBtn);
                purchaseTransBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToPurchaseTransactionList();
                       
                        }
                    });
		
                orderReportBtn = new JButton("Order Report");
		orderReportBtn.setFont(fntPlainText);
		orderReportBtn.setBounds(200, 150, 200, 40);
		add(orderReportBtn);
                orderReportBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToOrderReport();
                       
                        }
                    });
                
                returnSlipBtn = new JButton("Return Slip");
		returnSlipBtn.setFont(fntPlainText);
		returnSlipBtn.setBounds(200, 210, 200, 40);
		add(returnSlipBtn);
                returnSlipBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToReturnSlip();
                       
                        }
                    });
                
		creditMemoBtn = new JButton("Credit Memo");
		creditMemoBtn.setFont(fntPlainText);
		creditMemoBtn.setBounds(200,270 , 200, 40);
		add(creditMemoBtn);
                creditMemoBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToCreditMemo();
                       
                        }
                    });
		
		
		
		
	}

}

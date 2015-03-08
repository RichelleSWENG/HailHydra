package Login;

import HailHydra.GUIModel;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesGUI extends JPanel {
	
	private JButton salesInvoiceBtn, ackReceiptBtn, debitMemoBtn;
        private Font fntPlainText;
        private GUIModel controller;
        
	public SalesGUI(GUIModel temp) 
	{
                controller=temp;
		setBounds(0, 0, 700, 400);
		setBackground(SystemColor.controlHighlight);
		setLayout(null);
		
                fntPlainText=new Font("Arial", Font.PLAIN, 21);
                
		salesInvoiceBtn = new JButton("Sales Invoice");
		salesInvoiceBtn.setFont(fntPlainText);
		salesInvoiceBtn.setBounds(190, 100, 200, 40);
		add(salesInvoiceBtn);
                salesInvoiceBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToSalesInvoice();
                       
                        }
                    });
		
		ackReceiptBtn = new JButton("Acknowledgement Receipt");
		ackReceiptBtn.setFont(fntPlainText);
		ackReceiptBtn.setBounds(150, 160, 290, 40);
		add(ackReceiptBtn);
                ackReceiptBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToAcknowledgementReceipt();
                       
                        }
                    });
                
		
		debitMemoBtn = new JButton("Debit Memo");
		debitMemoBtn.setFont(fntPlainText);
		debitMemoBtn.setBounds(200, 220, 200, 40);
		add(debitMemoBtn);
                debitMemoBtn.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e){
                              controller.changePanelToDebitMemo();
                       
                        }
                    });
	}
}

package Login;

import HailHydra.GUIController;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesGUI extends JPanel
{

	private JButton salesInvoiceBtn, ackReceiptBtn, debitMemoBtn;
	private Font fntPlainText;
	private GUIController controller;

	public SalesGUI(GUIController temp)
	{
		controller = temp;
		setBounds(5, 5, 580, 390);
		setLayout(null);

		fntPlainText = new Font("Arial", Font.PLAIN, 21);

		salesInvoiceBtn = new JButton("Sales Invoice");
		salesInvoiceBtn.setFont(fntPlainText);
		salesInvoiceBtn.setBounds(150, 100, 300, 40);
		add(salesInvoiceBtn);
		salesInvoiceBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToSalesInvoice();

			}
		});

		ackReceiptBtn = new JButton("Acknowledgement Receipt");
		ackReceiptBtn.setFont(fntPlainText);
		ackReceiptBtn.setBounds(150, 160, 300, 40);
		add(ackReceiptBtn);
		ackReceiptBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToAcknowledgementReceipt();

			}
		});

		debitMemoBtn = new JButton("Debit Memo");
		debitMemoBtn.setFont(fntPlainText);
		debitMemoBtn.setBounds(150, 220, 300, 40);
		add(debitMemoBtn);
		debitMemoBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.changePanelToDebitMemo();

			}
		});
	}
}

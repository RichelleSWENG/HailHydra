package AcknowledgementReceipt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class AddItemPopUpGUI extends JFrame
{

	private JPanel contentPane;
	private JTextField tfQuantity, tfUnitPrice, tfTotal;
	private JButton btnSubmit, btnCancel;
	private JLabel lblItem, lblQuantity, lblPartNumber, lblUnitPrice, lblTotal;
	private JComboBox cmbPartNumber;

	public AddItemPopUpGUI()
	{
		instantiate();
	}
	
	public AddItemPopUpGUI(String qty, String partNumber, String unitPrice)
	{
		instantiate();
		tfQuantity.setText(qty);
		tfUnitPrice.setText(unitPrice);
		cmbPartNumber.setSelectedItem((Object)partNumber);
	}
	
	public void instantiate(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		lblItem = new JLabel("Item Profile");
		lblItem.setFont(new Font("Arial", Font.BOLD, 45));
		lblItem.setBounds(30, 0, 600, 86);
		contentPane.add(lblItem);
		
		lblQuantity = new JLabel("Quantity: ");
		lblQuantity.setFont(new Font("Arial", Font.PLAIN, 21));
		lblQuantity.setBounds(30, 100, 99, 30);
		contentPane.add(lblQuantity);
		
		lblPartNumber = new JLabel("Part Number:");
		lblPartNumber.setFont(new Font("Arial", Font.PLAIN, 21));
		lblPartNumber.setBounds(30, 150, 139, 30);
		contentPane.add(lblPartNumber);
		
		lblUnitPrice = new JLabel("Unit Price:");
		lblUnitPrice.setFont(new Font("Arial", Font.PLAIN, 21));
		lblUnitPrice.setBounds(30, 204, 119, 30);
		contentPane.add(lblUnitPrice);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 21));
		lblTotal.setBounds(30, 254, 66, 30);
		contentPane.add(lblTotal);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 21));
		btnCancel.setBounds(294, 361, 119, 40);
		contentPane.add(btnCancel);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSubmit.setBounds(134, 361, 119, 40);
		contentPane.add(btnSubmit);
		
		tfQuantity = new JTextField();
		tfQuantity.setBounds(124, 100, 255, 30);
		contentPane.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		tfUnitPrice = new JTextField();
		tfUnitPrice.setBounds(139, 204, 240, 30);
		contentPane.add(tfUnitPrice);
		tfUnitPrice.setColumns(10);
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setBounds(91, 254, 288, 30);
		contentPane.add(tfTotal);
		tfTotal.setColumns(10);
		
		cmbPartNumber = new JComboBox();
                AutoCompleteDecorator.decorate(cmbPartNumber);
                cmbPartNumber.setEditable(true);
		cmbPartNumber.setBounds(167, 150, 212, 30);
		contentPane.add(cmbPartNumber);
	}
}

package ModifyAlert;

import javax.swing.JOptionPane;

public class ModifyVAT extends PopUp
{

	public ModifyVAT(String vat)
	{
		input = JOptionPane.showInputDialog("The Current VAT Percentage is: "
				+ vat + "\nEnter New VAT Percentage:");
	}

}

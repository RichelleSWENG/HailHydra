package ModifyAlert;

import javax.swing.JOptionPane;

public class ModifyCreditLimit extends PopUp
{

	public ModifyCreditLimit(String credit)
	{
		input = JOptionPane
				.showInputDialog("The Current Credit Limit Percentage is: "
						+ credit + "\nEnter New Credit Limit Percentage:");
	}
}

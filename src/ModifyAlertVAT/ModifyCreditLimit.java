package ModifyAlertVAT;

import javax.swing.JOptionPane;

public class ModifyCreditLimit extends PopUp{
	
	public ModifyCreditLimit(){
		input = JOptionPane.showInputDialog("Enter New Credit Limit Percentage:");
	}
}

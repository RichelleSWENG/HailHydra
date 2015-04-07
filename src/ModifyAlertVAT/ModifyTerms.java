package ModifyAlertVAT;

import javax.swing.JOptionPane;

public class ModifyTerms extends PopUp{
	
	public ModifyTerms(String terms){
		input = JOptionPane.showInputDialog("The Current Terms (Days) is: "+terms+"\nEnter New Terms (Days):");
	}
}

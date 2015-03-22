package ModifyAlertVAT;

public class FactoryModify {
	public String createProduct(String modify){
		if ("VAT"==modify){
			return new ModifyVAT().getInput();
		}	
		else if ("Terms"==modify){
			return new ModifyTerms().getInput();
		}
			
		else if ("CreditLimit"==modify){
			return new ModifyCreditLimit().getInput();
		}
        return null; 
    }
}

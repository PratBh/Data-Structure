package LLD.ATMDesign.AtmStates;

import LLD.ATMDesign.ATM;
import LLD.ATMDesign.Card;

public class CheckBalanceState extends ATMState {
	public CheckBalanceState() {
		
	}
	
	
	@Override
	public void displayBalance(ATM atm, Card card) {
		System.out.println("Balance: "+card.getBankBalance());
		exit(atm);
	}


	@Override
	public void returnCard() {
		System.out.println("Please collect your card..");
	}

	@Override
	public void exit(ATM atm) {
		returnCard();
		atm.setCurrentATMState(new IdleState());
		System.out.println("Exit..");
	}
}

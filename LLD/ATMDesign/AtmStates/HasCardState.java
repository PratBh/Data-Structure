package LLD.ATMDesign.AtmStates;

import LLD.ATMDesign.ATM;
import LLD.ATMDesign.Card;

public class HasCardState extends ATMState {
	public HasCardState() {
		System.out.println("please enter your PIN...");
	}

	@Override
	public void authenticatePin(ATM atm, Card card, int pin) {
		boolean isCorrectPin = card.isCorrectPin(pin);
		if(isCorrectPin) {
			atm.setCurrentATMState(new SelectOperationState());
		}else {
			System.out.println("Invalid PIN...");
			exit(atm);
		}
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

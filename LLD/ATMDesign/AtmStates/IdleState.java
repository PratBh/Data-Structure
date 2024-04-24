package LLD.ATMDesign.AtmStates;

import LLD.ATMDesign.ATM;
import LLD.ATMDesign.Card;

public class IdleState extends ATMState {
	@Override
	public void insertCard(ATM atm,Card card) {
		System.out.println("Card inserted");
		atm.setCurrentATMState(new HasCardState());
	}
}

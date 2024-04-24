package LLD.ATMDesign.AtmStates;

import LLD.ATMDesign.ATM;
import LLD.ATMDesign.Card;
import LLD.ATMDesign.TransactionType;

public class SelectOperationState extends ATMState {
	
	public SelectOperationState() {
		showOperations();
	}
	
	@Override
	public void selectOperation(ATM atm, Card card, TransactionType txnType) {
		switch (txnType) {
		case CASH_WITHDRAWAL:
			atm.setCurrentATMState(new CastWithdrawalState());
			break;
		case BALANCE_CHECK:
			atm.setCurrentATMState(new CheckBalanceState());
			break;
		default:
			System.out.println("Invalid operation...");
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
	
	private void showOperations() {
		System.out.println("Select the operation...");
		TransactionType.showAllTransactionTypes();
	}
}

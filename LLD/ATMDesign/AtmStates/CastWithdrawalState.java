package LLD.ATMDesign.AtmStates;

import LLD.ATMDesign.ATM;
import LLD.ATMDesign.Card;
import LLD.ATMDesign.AmountWithdrawal.CashWithdrawProcessor;
import LLD.ATMDesign.AmountWithdrawal.FiveHundredCashWithdrawProcessor;
import LLD.ATMDesign.AmountWithdrawal.OneHundredCashWithdrawProcessor;
import LLD.ATMDesign.AmountWithdrawal.TwoThousandCashWithdrawProcessor;

public class CastWithdrawalState extends ATMState {
	
	public CastWithdrawalState() {
		System.out.println("Please enter amount..");
	}

	@Override
	public void cashWithdrawal(ATM atm, Card card, int withdrawAmount) {
		if(atm.getAtmBalance()<withdrawAmount) {
			System.out.println("Insufficent fund at ATM..");
			exit(atm);
		}else if(card.getBankBalance()<withdrawAmount) {
			System.out.println("Insufficent fund in account..");
			exit(atm);
		}else {
			card.deductBankBalance(withdrawAmount);
			atm.deductATMBalance(withdrawAmount);
			CashWithdrawProcessor processor = new TwoThousandCashWithdrawProcessor(new FiveHundredCashWithdrawProcessor(new OneHundredCashWithdrawProcessor(null)));
			processor.withdraw(atm, withdrawAmount);
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

package LLD.ATMDesign.AmountWithdrawal;

import LLD.ATMDesign.ATM;

public class FiveHundredCashWithdrawProcessor extends CashWithdrawProcessor {

	public FiveHundredCashWithdrawProcessor(CashWithdrawProcessor cashWithdrawalProcessor) {
		super(cashWithdrawalProcessor);
		// TODO Auto-generated constructor stub
	}
	public void withdraw(ATM atm,int amount) {
		int required = amount/500;
		int balance = amount%500;
		
		if(required<=atm.getNoOfFiveHundredNotes())
			atm.deductFiveHundredNotes(required);
		else {
			atm.deductFiveHundredNotes(atm.getNoOfFiveHundredNotes());
			balance+=(required-atm.getNoOfFiveHundredNotes())*500;
		}
		if(balance!=0)
			nextCashWithdrawalProcessor.withdraw(atm, balance);
	}
}

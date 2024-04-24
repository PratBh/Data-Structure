package LLD.ATMDesign.AmountWithdrawal;

import LLD.ATMDesign.ATM;

public class OneHundredCashWithdrawProcessor extends CashWithdrawProcessor {

	public OneHundredCashWithdrawProcessor(CashWithdrawProcessor cashWithdrawalProcessor) {
		super(cashWithdrawalProcessor);
	}
	
	public void withdraw(ATM atm,int amount) {
		int required = amount/100;
		int balance = amount%100;
		
		if(required<=atm.getNoOfOneHundredNotes())
			atm.deductOneHundredNotes(required);
		else {
			atm.deductOneHundredNotes(atm.getNoOfOneHundredNotes());
			balance+=(required-atm.getNoOfOneHundredNotes())*100;
		}
		if(balance!=0)
			super.withdraw(atm, balance);
	}

}

package LLD.ATMDesign.AmountWithdrawal;

import LLD.ATMDesign.ATM;

public class TwoThousandCashWithdrawProcessor extends CashWithdrawProcessor {

	public TwoThousandCashWithdrawProcessor(CashWithdrawProcessor nextCashWithdrawProcessor) {
		super(nextCashWithdrawProcessor);
	}
	public void withdraw(ATM atm,int amount) {
		int required = amount/2000;
		int balance = amount%2000;
		
		if(required<=atm.getNoOfTwoThousandNotes())
			atm.deductTwoThousandNotes(required);
		else {
			atm.deductTwoThousandNotes(atm.getNoOfTwoThousandNotes());
			balance+=(required-atm.getNoOfTwoThousandNotes())*2000;
		}
		if(balance!=0)
			super.withdraw(atm, balance);
	}

}

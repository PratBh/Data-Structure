package LLD.ATMDesign.AmountWithdrawal;

import LLD.ATMDesign.ATM;

public abstract class CashWithdrawProcessor {
	CashWithdrawProcessor nextCashWithdrawalProcessor;
	CashWithdrawProcessor(CashWithdrawProcessor cashWithdrawalProcessor) {

        this.nextCashWithdrawalProcessor = cashWithdrawalProcessor;

    }
	
	public void withdraw(ATM atm,int amount) {
		if(nextCashWithdrawalProcessor!=null)
			nextCashWithdrawalProcessor.withdraw(atm, amount);
	}

}

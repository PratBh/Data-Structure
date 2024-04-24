package LLD.ATMDesign;

public class Card {
	private int cardNumber;
    private int cvv;
    private int expiryDate;
    private int holderName;
    static int PIN_NUMBER = 112211;
    private UserBankAccount bankAccount;
    
    public boolean isCorrectPin(int pin) {
    	if(pin==PIN_NUMBER)
    		return true;
    	return false;
    }
    
    public int getBankBalance() {
    	return bankAccount.balance;
    }
    
    public void deductBankBalance(int amount) {
    	bankAccount.withdrawalBalance(amount);
    }

	public UserBankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(UserBankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
    
}

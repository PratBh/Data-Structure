package LLD.ATMDesign;

public class ATMRoom {
	ATM atm;
	User user;
	
	public static void main(String args[]) {
		ATMRoom room = new ATMRoom();
		room.initialize();
		room.atm.printCurrentATMStatus();
		room.atm.getCurrentATMState().insertCard(room.atm, room.user.card);
		room.atm.getCurrentATMState().authenticatePin(room.atm, room.user.card, 112211);
		room.atm.getCurrentATMState().selectOperation(room.atm, room.user.card, TransactionType.CASH_WITHDRAWAL);
		room.atm.getCurrentATMState().cashWithdrawal(room.atm, room.user.card, 2700);
		room.atm.printCurrentATMStatus();
	}
	
	private void initialize() {
		atm = ATM.getATMObject();
		atm.setAtmBalance(3500, 1, 2, 5);
		this.user=createUser();
	}
	
	private User createUser() {
		User user = new User();
		user.setCard(createCard());
		return user;
	}
	
	private Card createCard() {
		Card card =new Card();
		card.setBankAccount(createAccount());
		return card;
	}
	
	private UserBankAccount createAccount() {
		UserBankAccount account = new UserBankAccount();
		account.balance=3000;
		return account;
	}
}

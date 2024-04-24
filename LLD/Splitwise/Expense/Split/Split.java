package LLD.Splitwise.Expense.Split;

import LLD.Splitwise.User.User;

public class Split {
	User user;
	double amount;
	public Split(User user, double amount) {
		super();
		this.user = user;
		this.amount = amount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}

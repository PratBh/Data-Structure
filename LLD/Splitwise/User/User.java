package LLD.Splitwise.User;

import LLD.Splitwise.UserExpenseBalanceSheet;

public class User {
	String userId;
	String userName;
	UserExpenseBalanceSheet userExpenseBalanceSheet;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserExpenseBalanceSheet getUserExpenseBalanceSheet() {
		return userExpenseBalanceSheet;
	}
	public void setUserExpenseBalanceSheet(UserExpenseBalanceSheet userExpenseBalanceSheet) {
		this.userExpenseBalanceSheet = userExpenseBalanceSheet;
	}
	
	
}

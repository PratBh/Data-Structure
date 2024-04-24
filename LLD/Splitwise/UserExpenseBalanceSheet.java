package LLD.Splitwise;

import java.util.HashMap;
import java.util.Map;

public class UserExpenseBalanceSheet {
	Map<String,Balance> userVsBalance;
	double totalYourExpense;

    double totalPayment;

    double totalYouOwe;
    double totalYouGetBack;
	public UserExpenseBalanceSheet() {
		this.userVsBalance = new HashMap<String, Balance>();
		this.totalYourExpense = 0;
		this.totalPayment = 0;
		this.totalYouOwe = 0;
		this.totalYouGetBack = 0;
	}
    
    

}

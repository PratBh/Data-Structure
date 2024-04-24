package LLD.Splitwise;

import java.util.List;
import java.util.Map.Entry;

import LLD.Splitwise.Expense.Split.Split;
import LLD.Splitwise.User.User;

public class BalanceSheetControlle {
	
	public void updateUserExpenseBalanceSheet(User expensePaidBy, List<Split> splits, double totalExpenseAmount){
		UserExpenseBalanceSheet paidByUserBalanceSheet = expensePaidBy.getUserExpenseBalanceSheet();
		paidByUserBalanceSheet.totalPayment=paidByUserBalanceSheet.totalPayment+totalExpenseAmount;
		
		for(Split split:splits) {
			User userOwe = split.getUser();
			UserExpenseBalanceSheet oweUserBalanceSheet = userOwe.getUserExpenseBalanceSheet();
			double oweAmount = split.getAmount();
			if(expensePaidBy.getUserId().equals(userOwe.getUserId())) {
				paidByUserBalanceSheet.totalYourExpense+=oweAmount;
			}else {
				paidByUserBalanceSheet.totalYouGetBack+=oweAmount;
				Balance useroweBalance;
				if(paidByUserBalanceSheet.userVsBalance.containsKey(userOwe.getUserId())) {
					useroweBalance = paidByUserBalanceSheet.userVsBalance.get(userOwe.getUserId());
				}else {
					useroweBalance = new Balance();
					paidByUserBalanceSheet.userVsBalance.put(userOwe.getUserId(), useroweBalance);
				}
				
				useroweBalance.amountGetBack+=oweAmount;
				oweUserBalanceSheet.totalYouOwe+=oweAmount;
				oweUserBalanceSheet.totalYourExpense+=oweAmount;
				Balance userPaidBalance;
				if(oweUserBalanceSheet.userVsBalance.containsKey(expensePaidBy.getUserId()))
					userPaidBalance=oweUserBalanceSheet.userVsBalance.get(expensePaidBy.getUserId());
				else {
					userPaidBalance=new Balance();
					oweUserBalanceSheet.userVsBalance.put(expensePaidBy.getUserId(), userPaidBalance);
				}
				userPaidBalance.amountOwe+=oweAmount;
			}
		}
	}
	
	public void showBalanceSheetOfUser(User user){

        System.out.println("---------------------------------------");

        System.out.println("Balance sheet of user : " + user.getUserId());

        UserExpenseBalanceSheet userExpenseBalanceSheet =  user.getUserExpenseBalanceSheet();

        System.out.println("TotalYourExpense: " + userExpenseBalanceSheet.totalYourExpense);
        System.out.println("TotalGetBack: " + userExpenseBalanceSheet.totalYouGetBack);
        System.out.println("TotalYourOwe: " + userExpenseBalanceSheet.totalYouOwe);
        System.out.println("TotalPaymnetMade: " + userExpenseBalanceSheet.totalPayment);
        for(Entry<String, Balance> entry : userExpenseBalanceSheet.userVsBalance.entrySet()){

            String userID = entry.getKey();
            Balance balance = entry.getValue();

            System.out.println("userID:" + userID + " YouGetBack:" + balance.amountGetBack + " YouOwe:" + balance.amountOwe);
        }

        System.out.println("---------------------------------------");

    }

}

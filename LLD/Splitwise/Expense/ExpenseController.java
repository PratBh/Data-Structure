package LLD.Splitwise.Expense;

import java.util.List;

import LLD.Splitwise.BalanceSheetControlle;
import LLD.Splitwise.Expense.Split.ExpenseSplit;
import LLD.Splitwise.Expense.Split.Split;
import LLD.Splitwise.User.User;

public class ExpenseController {
	BalanceSheetControlle balanceSheetControlle;
	public ExpenseController(){
        balanceSheetControlle = new BalanceSheetControlle();
    }
	
	public Expense createExpense(String id,String desc,double amount,List<Split> splits,SplitType splitType, User paidByUser) {
		ExpenseSplit exSplit = SplitFactory.getSplitObject(splitType);
		exSplit.validateSplitRequest(splits, amount);
		Expense expense = new Expense(id, desc, amount, paidByUser, splitType, splits);
		balanceSheetControlle.updateUserExpenseBalanceSheet(paidByUser, splits, amount);
		return expense;
	}

}

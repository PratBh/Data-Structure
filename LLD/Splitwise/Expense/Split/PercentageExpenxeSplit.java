package LLD.Splitwise.Expense.Split;

import java.util.List;

public class PercentageExpenxeSplit implements ExpenseSplit {

	@Override
	public void validateSplitRequest(List<Split> splits, double totalAmount) {
		double amount = totalAmount/splits.size();
		for(Split split:splits) {
			if(split.getAmount()!=amount)
				System.out.println("Wrong division");
		}

	}

}

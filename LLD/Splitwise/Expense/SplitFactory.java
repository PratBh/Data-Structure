package LLD.Splitwise.Expense;

import LLD.Splitwise.Expense.Split.EqualExpenseSplit;
import LLD.Splitwise.Expense.Split.ExpenseSplit;
import LLD.Splitwise.Expense.Split.PercentageExpenxeSplit;
import LLD.Splitwise.Expense.Split.UnequalExppenseSplit;

public class SplitFactory {
	public static ExpenseSplit getSplitObject(SplitType type) {
		 switch (type) {
         case EQUAL:
             return new EqualExpenseSplit();
         case UNEQUAL:
             return new UnequalExppenseSplit();
         case PERCENTAGE:
             return new PercentageExpenxeSplit();
         default:
             return null;
     }

	}
}

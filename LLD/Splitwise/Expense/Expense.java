package LLD.Splitwise.Expense;

import java.util.ArrayList;
import java.util.List;

import LLD.Splitwise.Expense.Split.Split;
import LLD.Splitwise.User.User;

public class Expense {
	String expenseId;
    String description;
    double expenseAmount;
    User paidByUser;
    SplitType splitType;
    List<Split> splitDetails = new ArrayList<Split>();
	public Expense(String expenseId, String description, double expenseAmount, User paidByUser, SplitType splitType,
			List<Split> splitDetails) {
		super();
		this.expenseId = expenseId;
		this.description = description;
		this.expenseAmount = expenseAmount;
		this.paidByUser = paidByUser;
		this.splitType = splitType;
		this.splitDetails = splitDetails;
	}
    
    

}

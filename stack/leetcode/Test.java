package stack.leetcode;

public class Test {

	public static void main(String[] args) {
		Stackpractice sp = new Stackpractice();
		String[]tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
		System.out.println(sp.calculate("(1+(4+5+2)-3)+(6+8)"));
	}

}

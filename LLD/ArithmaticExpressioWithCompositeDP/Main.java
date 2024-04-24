package LLD.ArithmaticExpressioWithCompositeDP;

public class Main {

	public static void main(String[] args) {
		ArthmaticExpression two = new Number(2);
		ArthmaticExpression one = new Number(1);
		ArthmaticExpression seven = new Number(7);
		
		ArthmaticExpression plus = new Expression(one, seven, Operation.ADD);
		ArthmaticExpression multiply = new Expression(two, plus, Operation.MULTIPLY);
		System.out.println(multiply.evaluate());

	}

}

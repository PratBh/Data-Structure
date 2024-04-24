package LLD.ArithmaticExpressioWithCompositeDP;

public class Expression implements ArthmaticExpression {
	ArthmaticExpression left;
	ArthmaticExpression right;
	Operation op;
	
	
	public Expression(ArthmaticExpression left, ArthmaticExpression right, Operation op) {
		super();
		this.left = left;
		this.right = right;
		this.op = op;
	}


	@Override
	public int evaluate() {
		int value =0 ;
		switch (op) {
		case ADD: 
			value = left.evaluate()+right.evaluate();
			break;
		case SUBTRACT: 
			value = left.evaluate()-right.evaluate();
			break;
		case MULTIPLY: 
			value = left.evaluate()*right.evaluate();
			break;
		case DIVIDE: 
			value = left.evaluate()/right.evaluate();
			break;
		}
		return value;
	}

}

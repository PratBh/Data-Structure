package LLD.ArithmaticExpressioWithCompositeDP;

public class Number implements ArthmaticExpression {
	int value ;
	public Number(int v) {
		this.value=v;
	}
	
	@Override
	public int evaluate() {
		System.out.println(value);
		return value;
	}

}

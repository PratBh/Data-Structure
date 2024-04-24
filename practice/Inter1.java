package practice;

@FunctionalInterface
public interface Inter1 {
	int a =10;
	public void sum(int a);
	default void print(int a) {
		a+=10;
		System.out.println(a);
	}
	
}

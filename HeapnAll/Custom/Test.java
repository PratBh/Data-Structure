package custom;

public class Test {

	public static void main(String[] args) {
		CustomQueue q=new CustomQueue();
		q.add(1);
		q.add(2);
		System.out.println(q.peek());
		System.out.println(q.peek());
	}

}

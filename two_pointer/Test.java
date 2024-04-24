package two_pointer;

public class Test {
	int a;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public static void main(String[] args) {
		LeetCode lc = new LeetCode();
		int[] gas = {1,8,6,2,5,4,8,3,7};
		String[] cost = {"This", "is", "an", "example", "of", "text", "justification."};
		System.out.print(lc.maxArea(gas));

	}

}

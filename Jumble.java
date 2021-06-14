import java.util.Random;

public class Jumble {
	public void shuffle(int[]arr) {
		Random ran=new Random();
		int k=0,n=arr.length;
		while(k<arr.length) {
			int i=ran.nextInt(n);
			System.out.println(arr[i]);
			int t=arr[i];
			arr[i]=arr[n-1];
			arr[n-1]=t;
			n--;
			k++;
		}
	}
}

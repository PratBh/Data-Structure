package leetcode;

import java.util.Random;

public class Testing {

	public static void main(String[] args) {
		int[] songs=new int[100];
			Random random=new Random();
			int n=songs.length,pointer=n,k=0;
			while(k<n&&pointer>=0) {
				int i=random.nextInt(pointer);
				System.out.println(songs[i]);
				 int temp=songs[i];
				 songs[i]=songs[pointer-1];
				 songs[pointer-1]=temp;
				 pointer--;
				 k++;
				 
			}
			

	}
	
	

}

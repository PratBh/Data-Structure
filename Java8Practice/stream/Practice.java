package stream;

public class Practice {
	public void print(int n) {
		if(n==1)
			System.out.println(n);
		else {
			print(n-1);
			System.out.println(n);
		}
	}
	//i am good -> d oo gmaI
	public String convert(String str) {
		if(str.length()==0)
			return "";
		StringBuilder sb=new StringBuilder();
		int pointer=0;
		for(int i=str.length()-1;i>=0;i--) {
			if(str.charAt(pointer)==' ') {
				sb.append(' ');
				pointer++;
			}
			 if(str.charAt(i)!=' ') {
			 sb.append(str.charAt(i));
			 pointer++;
			}
		}
		return sb.toString();
	}
}

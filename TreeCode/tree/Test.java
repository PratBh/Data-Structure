package tree;

public class Test {

	public static void main(String[] args) {
		Test mn=new Test();
       // mn.lastLiving(2);
		String b=null;
        System.out.println(mn.parse("adce"));
		//returns 0324.because a-0,b-1......
	}
	class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
        }
    }
    
	
	//Below two problems were asked in Paypal.First one is josephus problem and second one is to turn string into nums.Please refer example.
     void lastLiving(int n){
        Node head=new Node(1);
        Node temp=null,root=head;
        for(int i=2;i<=n;i++){
            temp=new Node(i);
            head.next=temp;
            head=temp;
        }
        temp.next=root;
        
        while(root.next!=null && root.next!=root){
            root.next=root.next.next;
            root=root.next;
        }
        System.out.println(root.data);
    }
     
     String parse(String str) {
    	 if(str == null || str.length()==0)
    		 return "NONE";
    	 StringBuilder sb=new StringBuilder();
    	 for(int i=0;i<str.length();i++) {
    		 if(Character.isLowerCase(str.charAt(i))) {
    			 int k=(int)str.charAt(i);
    			 if(k-97<10) {
    				 sb.append(k-97);
    			 }
    		 }
    		 else 
    			return "NONE";
    	 }
    	 return sb.toString();
     }
 }

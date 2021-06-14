import java.util.ArrayList;

public class Node {
	public int val;
    public Node prev;
    public Node next;
    public Node child;
 
    
    public Node flatten(Node head,ArrayList<Integer> res) {
        if(head == null)
            return null;
        res.add(head.val);
        if(head.child != null){
            Node earlyNext = head.next;
            if(earlyNext!=null)
            earlyNext.prev=null;
            Node child = flatten(head.child,res);
            head.next=child;
            while(child.next != null){
                child=child.next;
            }
            if(earlyNext !=null){
                earlyNext.prev=child;
            child.next=flatten(earlyNext,res);
            }
            return head;
        }else if(head.child == null && head.next== null) {
            return head;
        }
        else{
            head.next=flatten(head.next,res);
            return head;
            }
            
        }
}


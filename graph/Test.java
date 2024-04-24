package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		Leetcode lc =new Leetcode();
		int [][] grid = 
			{{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
		String[] bank = {"hot","dot","dog","lot","log","cog"};
		//lc.ladderLength("hit", "cog", Arrays.asList(bank));
		lc.snakesAndLadders(grid);
//		Queue<String> fifoQueue = new LinkedList<>();
//
//        // Enqueue (add) elements to the queue
//        fifoQueue.offer("Element 1");
//        fifoQueue.offer("Element 2");
//        fifoQueue.offer("Element 3");
//
//        // Display elements in the FIFO queue
//        System.out.println("FIFO Queue: " + fifoQueue);
//
//        // Dequeue (remove) elements from the front of the queue
//        String removedElement = fifoQueue.poll();
//        System.out.println("Removed Element: " + removedElement);
//
//        // Display elements in the FIFO queue after removal
//        System.out.println("FIFO Queue after removal: " + fifoQueue);
//
//        // Peek at the front element without removing it
//        String frontElement = fifoQueue.peek();
//        System.out.println("Front Element: " + frontElement);
//
//        // Display elements in the FIFO queue after peek
//        System.out.println("FIFO Queue after peek: " + fifoQueue);
	}

}

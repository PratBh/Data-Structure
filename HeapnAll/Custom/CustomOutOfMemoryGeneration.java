package custom;

public class CustomOutOfMemoryGeneration {

	public static void main(String[] args) throws InterruptedException {
		int dummyArraySize=15;
		System.out.println("Max memory: "+Runtime.getRuntime().maxMemory());
		long memoryConsumed=0;
		long[] momoryAllocated=null;
		try {
		for(int loop=0;loop<Integer.MAX_VALUE;loop++) {
			momoryAllocated=new long[dummyArraySize];
			momoryAllocated[0]=0;
			memoryConsumed +=dummyArraySize*Long.SIZE;
			System.out.println("Memory consumed: "+memoryConsumed);
			dummyArraySize *=dummyArraySize*2;
			Thread.sleep(500);
			}
		} catch (OutOfMemoryError error) {
			System.out.println("Catching outofmemmory error");
		}

	}

}

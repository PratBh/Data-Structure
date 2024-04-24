package LLD.chainOfResponsibilityLogger;

public class DebugLogPresessor extends LogProcessor {

	public DebugLogPresessor(LogProcessor nextLogProcessor) {
		super(nextLogProcessor);
	}
	
	public void log(int logLevel,String message) {
		System.out.println("in debug");
		if(logLevel == DEBUG)
			System.out.println("DEBUG:" + message);
		else
			super.log(logLevel, message);
	}

}

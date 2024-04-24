package LLD.chainOfResponsibilityLogger;

public class ErrorLogPressor extends LogProcessor {
	public ErrorLogPressor(LogProcessor nextLogProcessor) {
		super(nextLogProcessor);
	}
	
	public void log(int logLevel,String message) {
		System.out.println("in error");
		if(logLevel == ERROR)
			System.out.println("ERROR:" + message);
		else
			super.log(logLevel, message);
	}
}

package LLD.chainOfResponsibilityLogger;

public class Main {

	public static void main(String[] args) {
		LogProcessor logger = new InfoLogProcessor(new DebugLogPresessor(new ErrorLogPressor(null)));
		logger.log(LogProcessor.ERROR, "Error");
//		logger.log(LogProcessor.INFO, "Info");
		//logger.log(LogProcessor.DEBUG, "Debug");
	}

}

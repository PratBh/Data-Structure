package LLD.FlyweightDesignPattern;

public class FlyWeightHumanRobot implements IRobot {
	private String type;
	private String body;
	
	
	public FlyWeightHumanRobot(String type, String body) {
		this.type = type;
		this.body = body;
	}

	public String getType() {
		return type;
	}
	
	public String getBody() {
		return body;
	}
	
	@Override
	public void display(int x, int y) {
		System.out.println("I am human at "+x+" , "+y);

	}

}

package LLD.FlyweightDesignPattern;

public class FlyweightDogRobot implements IRobot {
	private String type;
	private String body;
	
	
	public FlyweightDogRobot(String type, String body) {
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
		System.out.println("I am dog at "+x+" , "+y);
	}

}

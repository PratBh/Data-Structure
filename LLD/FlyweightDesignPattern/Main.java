package LLD.FlyweightDesignPattern;

public class Main {

	public static void main(String[] args) {
		for(int i=1;i<=10;i++) {
			IRobot humanRobo = RobotFactory.getRobotInstance("HUMAN");
			humanRobo.display(i, i);
		}
		for(int i=11;i<=20;i++) {
			IRobot dogRobo = RobotFactory.getRobotInstance("DOG");
			dogRobo.display(i, i);
		}
	}

}

package LLD.FlyweightDesignPattern;

import java.util.HashMap;
import java.util.Map;

public class RobotFactory {
	private static Map<String, IRobot> cache = new HashMap<String, IRobot>();
	
	public static IRobot getRobotInstance(String type) {
		if(cache.containsKey(type))
			return cache.get(type);
		else {
			if(type.equals("HUMAN")) {
				IRobot human = new FlyWeightHumanRobot(type, "humanStripe");
				cache.put(type, human);
				return human;
			}else if(type.equals("DOG")) {
				IRobot dog = new FlyweightDogRobot(type, "dogStripe");
				cache.put(type, dog);
				return dog;
			}
		}
		return null;
	}
}

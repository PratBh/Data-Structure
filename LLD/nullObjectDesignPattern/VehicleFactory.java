package LLD.nullObjectDesignPattern;

public class VehicleFactory {
	static Vehicle getVehicleObject(String type) {
		switch (type) {
		case "CAR": {
			
			return new Car();
		}
		default:
			return new NullObject();
		}
	}
}

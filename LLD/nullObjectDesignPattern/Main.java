package LLD.nullObjectDesignPattern;

public class Main {

	public static void main(String[] args) {
		Vehicle v = VehicleFactory.getVehicleObject("CAR");
		printVehicleDetails(v);
	}
	
	private static void printVehicleDetails(Vehicle vehicle) {
		System.out.println("Seats: "+vehicle.getSeatingCapacity());
		System.out.println("Tank size: "+vehicle.getTankCapacity());
	}

}

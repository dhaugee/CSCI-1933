import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VehicleSorter {
	public static void main(String[] args) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Car voiture = new Car(20);
		Train traine = new Train(250);
		Helicopter heli = new Helicopter(21);
		vehicles.add(voiture);
		vehicles.add(traine);
		vehicles.add(heli);
		Collections.sort(vehicles);
		for (Vehicle v : vehicles) {
			System.out.println(v);
		}
	}
}

public class VehicleTester {
    public static void main(String[] args){
        Bike myBike = new Bike();
        myBike.movingForward();
        Scooter myScoot = new Scooter();
        myScoot.movingBackward();
        Car car = new Car(30.0);
        System.out.println(car);
        car.movingForward();
        car.movingBackward();
        System.out.println("Total Number of Vehicles: " + Vehicle.getNumVehicles());
    }
}

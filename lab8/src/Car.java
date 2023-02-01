public class Car extends Vehicle {

    private double mpg = 30.0;

    public Car(double mpg){
        this.mpg = mpg;
        nVehicles += 1;
    }

    @Override
    public void movingForward() {
        System.out.println("Car Moving Forward");
    }

    @Override
    public void movingBackward() {
        System.out.println("Car Moving Backward");
    }

    @Override
    public double getMPG() {
        return mpg;
    }

    public String toString(){
        return "Car: " + mpg;
    }
}

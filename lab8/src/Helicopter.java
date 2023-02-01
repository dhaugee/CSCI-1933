public class Helicopter extends Vehicle {

    private double mpg = 0.3;

    public Helicopter(double mpg){
        this.mpg = mpg;
        nVehicles += 1;
    }

    @Override
    public void movingForward() {
        System.out.println("Helicopter Moving Forward");
    }

    @Override
    public void movingBackward() {
        System.out.println("Helicopter Moving Backward");
    }

    @Override
    public double getMPG() {
        return mpg;
    }

    public void movingUp(){
        System.out.println("Helicopter Moving Up");
    }

    public void movingDown(){
        System.out.println("Helicopter Moving Down");
    }

    public String toString(){
        return "Helicopter: " + mpg;
    }
}

public class Train extends Vehicle {

    private double mpg = 470.0;

    public Train(double mpg){
        this.mpg = mpg;
        nVehicles += 1;
    }

    @Override
    public void movingForward() {
        System.out.println("Train Moving Forward");
    }

    @Override
    public void movingBackward() {
        System.out.println("Train Moving Backward");
    }

    @Override
    public double getMPG() {
        return mpg;
    }

    public void enteringStation(){
        System.out.println("Train Entering Station");
    }

    public void leavingStation(){
        System.out.println("train Leaving Station");
    }

    public String toString(){
        return "Train: " + mpg;
    }
}

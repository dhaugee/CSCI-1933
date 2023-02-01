public class Bus {
    private int capacity = 40;
    private Passenger[] bus;

    public Bus(){
        bus = Passenger[capacity];
    }

    public Bus(int newCap){
        this.capacity = newCap;
        bus = Passenger[capacity];

    }

    public void addPassenger(Passenger p){

        for(int i = 0; i <= bus.length -1; i++){
            if (bus[i] = null){
                bus[i] = p;
                return;
            }
        }
    }

    public int numberOfPassengers(){
       int passNum = 0;
       for(int i = bus.length -1; i >= 0; i--) {
           if (bus[i] != null){
               passNum += 1;
           }
       }
       return passNum;
    }
}

// use insertion sort to ensure stability because we're considering bus capacity
// AND the time at which they arrived


// 2.
// f(n) = (n^2) + 1
// the function checks if two subsequent integers in an array add to some double

// 3.
// a. double cannot equal int; "this.data" must be specified; second method should
//    not be static
// b. string cannot be added to an int; == cannot be used on string;
// c. fuck if i know

// 4.
// main(1): w: 1, i: 2, d: 3
// doWhatever(1): w: 1, i: 2, d: 3
// doWhatever(2): w: 2, i: 2, d: 2
// main(2): w: 2, i: 2, d: 2
// main(3): w: 2, i: 2, d: 0.5

// 5.
// public static int powerK(int k, int n) {
//
//      if k == 1 {
//          return k
//      else if n == 1 {
//          return k
//      else {
//          return k * powerK(k, n-1)
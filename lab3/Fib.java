import java.util.Scanner;

public class Fib {

  public static void main(String[] args) {
    Fib myFib = new Fib();
    System.out.println("enter an integer and we'll tell you what number it corresponds with as a Fibonnaci term:");
    Scanner myScanner = new Scanner(System.in);
    int n = myScanner.nextInt();
    System.out.println("term number " + n + " in the Fibonnaci sequence is " + myFib.fibonacciRecursive(n) + ", found recursively");
    System.out.println("term number " + n + " in the Fibonnaci sequence is " + myFib.fibonacciIterative(n) + ", found iteratively");
  }

  public static int fibonacciRecursive(int n) {
    if (n == 0) {
      return 0;
    }
    else if (n == 1) {
      return 1;
    }
    else {
      return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }
  }

  public static int fibonacciIterative(int n) {
    int tZero = 0;
    int tWun = 1;
    int mediator = 0;
    int i = 0;
    while(i < n) {
      mediator = tZero + tWun;
      tZero = tWun;
      tWun = mediator;
      i++;
    }
    return tZero;
  }
}

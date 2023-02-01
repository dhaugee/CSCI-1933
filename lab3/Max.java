import java.util.Scanner;

public class Max {

  public static void main(String[] args) {
    Max max = new Max();
    System.out.println("enter a number and we'll tell you its largest digit");
    Scanner myScanner = new Scanner(System.in);
    int num = myScanner.nextInt();
    System.out.println(max.maxDigRec(num) + " is the largest digit in " + num + ". Tested recursively");
    System.out.println(max.maxDigIte(num) + " is the largest digit in " + num + ". Tested iteratively");
    if (max.maxDigRec(num) == max.maxDigIte(num)) {
      System.out.println("Results match.");
    }
    else {
      System.out.println("ERROR: results do not match. You have a bug!");
    }
  }

 public static int maxDigRec(int num) {
   if (num/10 == 0) {
     return num;
   }
   else {
     return Math.max(maxDigRec(num/10),(num % 10));
   }
  }

 public static int maxDigIte(int num) {
   int maxSoFar = 0;
   if (num/10 == 0) {
     return num;
   }
   else {
     while (num > 0) {
       if (num%10 > maxSoFar) {
         maxSoFar = num%10;
         num = num/10;
       }
       else {
         num = num/10;
       }
     }
     return maxSoFar;
   }
 }
}

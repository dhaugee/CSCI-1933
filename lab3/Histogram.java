import java.util.Scanner;

public class Histogram {
  public int lBound;
  public int uBound;
  public int intArray[];

  public static void main(String[] args) {
    Histogram histo = new Histogram(0, 5);
    histo.add(3);
    histo.add(2);
    histo.add(1);
    histo.add(2);
    histo.add(3);
    histo.add(0);
    histo.add(1);
    histo.add(5);
    histo.add(3);
    System.out.println(histo);
  }

  public Histogram(int lowerBound, int upperBound) {
    this.lBound = lowerBound;
    this.uBound = upperBound;
    this.intArray = new int[upperBound-lowerBound+1];
  }

  public boolean add(int i) {
    if (i >= lBound & i <= uBound) {
      intArray[i-lBound] = i;
      return true;
    }
    else {
      return false;
    }
  }

  public String toString() {
    String result = "";
    for (int i =0; i < intArray.length; i++) {
      result += i + lBound +":";
      for (int j = intArray[i]; j > 0; j--) {
        result += "*";
      }
      result += "\n";
    }
    return result;
    }
}

import java.util.Arrays;
import java.lang.Math;
public class BinaryTree {

    // TODO complete this method
    public static boolean isValid(int[] arr) {
        if (arr.length == 0) {
            return false;
        }
        else if ((Math.log(arr.length) / Math.log(2)) % 1 != 0 ) {
            return false;
        }
        else {
            return validHelp(arr, 1, 1000, -1000);
            }
    }

    public static boolean validHelp(int[] arr, int idx, int max, int min) {
        if (idx >= arr.length) {
            return true;
        }
        else if (max < arr[idx] || min > arr[idx]) {
            return false;
        }
        else {
            return validHelp(arr, idx * 2, arr[idx], min) && validHelp(arr, idx * 2 + 1, max, arr[idx]);
        }
    }

    public static void main (String args[]) {
      // milestone 1
      int[] arr1 = new int[]{-1,7,4,10,3,6,8,15};
      int[] arr2 = new int[]{-1,20,12,32,5,18,25,38};
      int[] arr3 = new int[]{-1,11,3,33,2,8,10,44};
      int[] arr4 = new int[]{-1,55,44,77,33,48,54,95,22,34,45,57,53,70,85,98};

      System.out.println("arr1 valid: " + isValid(arr1));  // expected: true
      System.out.println("arr2 valid: " + isValid(arr2));  // expected: true
      System.out.println("arr3 valid: " + isValid(arr3));  // expected: false
      System.out.println("arr4 valid: " + isValid(arr4));  // expected: false
    }
}

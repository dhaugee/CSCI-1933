import java.util.Queue;
import java.util.LinkedList;

public class ColorPath {
    public static int[][] colorPath(int[][] arr, int sourceRow, int sourceCol, int newColor) {
        dfs(arr, sourceCol, sourceRow, newColor);
        return arr;
    }

    public static void bfs(int[][] arr, int col, int row, int newColor) {
        Queue<int[]> queue = new LinkedList<>();
        int[] sArr = new int[2];
        sArr[0] = row;
        sArr[1] = col;
        queue.add(sArr);
        int oldColor = arr[row][col];
        while (!queue.isEmpty()) {
            int[] cords = queue.poll();
            int x = cords[0];
            int y = cords[1];
            arr[x][y] = newColor;
            if (x >= 0 && x < arr.length && y - 1 >= 0 && y - 1 < arr[0].length && arr[x][y - 1] == oldColor) {
                int[] lArr = new int[2];
                lArr[0] = x;
                lArr[1] = y - 1;
                queue.add(lArr);
            }
            if (x >= 0 && x < arr.length && y + 1 >= 0 && y + 1 < arr[0].length && arr[x][y + 1] == oldColor) {
                int[] rArr = new int[2];
                rArr[0] = x;
                rArr[1] = y + 1;
                queue.add(rArr);
            }
            if (x + 1 >= 0 && x + 1 < arr.length && y >= 0 && y < arr[0].length && arr[x + 1][y] == oldColor) {
                int[] bArr = new int[2];
                bArr[0] = x + 1;
                bArr[1] = y;
                queue.add(bArr);
            }
            if (x - 1 >= 0 && x - 1 < arr.length && y >= 0 && y < arr[0].length && arr[x - 1][y] == oldColor) {
                int[] aArr = new int[2];
                aArr[0] = x - 1;
                aArr[1] = y;
                queue.add(aArr);
            }
        }
    }

    public static void dfs(int[][] arr, int col, int row, int newColor) {
        if(row < 0 || row >= arr.length || col < 0 || col >= arr[0].length) {
            return;
        }
        int oldColor = arr[row][col];
        if(arr[row][col] != oldColor) {
            return;
        }
        else {
            arr[row][col] = newColor;
            ColorPath.dfs(arr, col, row - 1, newColor);
            ColorPath.dfs(arr, col, row + 1, newColor);
            ColorPath.dfs(arr, col - 1, row, newColor);
            ColorPath.dfs(arr, col + 1, row, newColor);
        }
    }
}

//    public static void dfs(int[][] arr, int col, int row, int newColor) {
//        if (row < 0 || row >= arr.length || col < 0 || col >= arr[0].length || arr[row][col] == newColor) {
//            ColorPath.dfs(arr, col, row - 1, newColor);
//            ColorPath.dfs(arr, col, row + 1, newColor);
//            ColorPath.dfs(arr, col - 1, row, newColor);
//            ColorPath.dfs(arr, col + 1, row, newColor);
//        } else return;
//    }
//}
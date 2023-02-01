public class Matrix {

    private int nrows;
    private int ncols;
    private int[][] matrix;

    public Matrix(int nrows, int ncols) {
        this.nrows = nrows;
        this.ncols = ncols;
        this.matrix = new int[nrows][ncols];
    }

    public Matrix(int[][] arr) {
        this.matrix = arr;
        this.nrows = arr.length;
        this.ncols = arr[0].length;
    }

    public Matrix transpose() {
        Matrix newMatrix = new Matrix(ncols, nrows);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length;  j++){
                newMatrix.matrix[i][j] = this.matrix[j][i];
            }
        }
        return newMatrix;
    }

    public String toString() {
        String printMatrix = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                printMatrix = printMatrix + matrix[i][j];
            }
            printMatrix = printMatrix + "\n";
        }
        return printMatrix;
    }

    public static void main(String args[]){
        Matrix keanu = new Matrix(2, 2);
        keanu.matrix[0][0] = 9;
        keanu.matrix[0][1] = 8;
        keanu.matrix[1][0] = 3;
        keanu.matrix[1][1] = 6;
        System.out.println(keanu);
        Matrix wick = keanu.transpose();
        System.out.println(wick);


    }
}

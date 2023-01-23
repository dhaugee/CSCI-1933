// Names: Dylan Haugee, Peter Yi
// x500s: hauge919, yi000087
import java.util.Random;
import java.util.Scanner;

public class MyMaze {
    Cell[][] maze; // 2D array representing every cell in the maze
    int startRow; // row in which the maze begins
    int endRow; // row in which the maze ends

    public MyMaze(int rows, int cols, int startRow, int endRow) {
         maze = new Cell [rows][cols]; // array assigned with size being determined by input rows and cols
         this.startRow = startRow;
         this.endRow = endRow;
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++){
                 maze[i][j] = new Cell(); // maze array filled with cells, each carrying attributes
             }
         }
    }

    public static MyMaze makeMaze() { // method to create the maze
        Scanner rowCol = new Scanner(System.in); // scanner to get user input for rows and cols values
        System.out.println("Enter [# of rows][space][# of columns] (between 5 and 20 inclusive): ");
        int rows = rowCol.nextInt();
        int cols = rowCol.nextInt();
        if (rows > 20 || rows < 5 || cols > 20 || cols < 5) { // if any of the input values are out of bounds
            System.out.println("Out of bounds. Try again:");
            return makeMaze(); // alert user of their mishap return the function call
        }
        Random rand = new Random(); // startRow and endRow are randomized
        int startRow = rand.nextInt(rows);
        int endRow = rand.nextInt(rows);
        MyMaze theMaze = new MyMaze(rows, cols, startRow, endRow); // maze is initialized with garnered values
        Stack1Gen<int[]> mazeStack = new Stack1Gen<>();
        mazeStack.push(new int[] {startRow, 0}); // making the stack and pushing the start position onto it
        theMaze.maze[startRow][0].setVisited(true); // the starting cell is visited
        while (!mazeStack.isEmpty()) { // while the stack representing the maze is not empty
            int[] coord = mazeStack.top(); // create array representing current position, set equal to the top/current cell in the stack
            int currentRow = coord[0]; // integers representing the current row and column
            int currentCol = coord[1];
            boolean deadEnd = true; // assume we are at a dead end
            if (currentRow - 1 >= 0 && !theMaze.maze[currentRow - 1][currentCol].getVisited()) {
                deadEnd = false; // if the above cell is in bounds and unvisited, we are not at a dead end
            }
            if (currentRow + 1 <= rows - 1 && !theMaze.maze[currentRow + 1][currentCol].getVisited()) {
                deadEnd = false; // if the below cell is in bounds and unvisited, we are not at a dead end
            }
            if (currentCol - 1 >= 0 && !theMaze.maze[currentRow][currentCol - 1].getVisited()) {
                deadEnd = false; // if the cell to the left is in bounds and unvisited, we are not at a dead end
            }
            if (currentCol + 1 <= cols - 1 && !theMaze.maze[currentRow][currentCol + 1].getVisited()) {
                deadEnd = false; // if the cell to the right is in bounds and unvisited, we are not at a dead end
            }
            if (deadEnd) { mazeStack.pop(); } // if no if statement is entered, we're at a dead end, and we move to the next cell in the stack
            while (!deadEnd) { // while loop is entered if current cell is not a dead end
                String[] neighbors = new String[] {"above", "below", "left", "right"};
                int randIdx = new Random().nextInt(4); // randomly select a neighbor
                if (neighbors[randIdx].equals("above") && (currentRow - 1) >= 0 && !theMaze.maze[currentRow - 1][currentCol].getVisited()) { // if the above cell is in bounds and unvisited
                    theMaze.maze[currentRow - 1][currentCol].setBottom(false); // remove the above cell's bottom barrier
                    theMaze.maze[currentRow - 1][currentCol].setVisited(true); // set the above cell to visited
                    currentRow--; // change our currentRow to the above row
                    int[] newCoord = {currentRow, currentCol}; // create a temporary array holding the above cell
                    mazeStack.push(newCoord); // push this cell onto the stack
                    deadEnd = true; // set deadEnd to true, breaking us out of the inner while loop
                } else if (neighbors[randIdx].equals("below") && (currentRow + 1) <= rows - 1 && !theMaze.maze[currentRow + 1][currentCol].getVisited()) { // if the below cell is in bounds and unvisited
                    theMaze.maze[currentRow][currentCol].setBottom(false); // remove the current cell's bottom barrier
                    theMaze.maze[currentRow + 1][currentCol].setVisited(true); // set the below cell to visited
                    currentRow++; // change our currentRow to the below row
                    int [] newCoord = {currentRow, currentCol};
                    mazeStack.push(newCoord);
                    deadEnd = true;
                } else if (neighbors[randIdx].equals("left") && (currentCol - 1) >= 0 && !theMaze.maze[currentRow][currentCol - 1].getVisited()) { // if the cell to the left is in bounds and unvisited
                    theMaze.maze[currentRow][currentCol - 1].setRight(false); // remove the left cell's right barrier
                    theMaze.maze[currentRow][currentCol - 1].setVisited(true); // set the left cell to visited
                    currentCol--; // change our currentCol to the cell to the left
                    int [] newCoord = {currentRow, currentCol};
                    mazeStack.push(newCoord);
                    deadEnd = true;
                } else if (neighbors[randIdx].equals("right") && (currentCol + 1) <= cols - 1 && !theMaze.maze[currentRow][currentCol + 1].getVisited()){ // if the cell to the right is in bounds and unvisited
                    theMaze.maze[currentRow][currentCol].setRight(false); // remove the current cell's right barrier
                    theMaze.maze[currentRow][currentCol + 1].setVisited(true); // set the right cell to visited
                    currentCol++; // change our currentCol to the cell to the right
                    int[] newCoord = {currentRow, currentCol};
                    mazeStack.push(newCoord);
                    deadEnd = true;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                theMaze.maze[i][j].setVisited(false); // loop through the entire maze and set all cells to not visited
            }
        }
        return theMaze; // return the created maze
    }

    public void printMaze() { // method to print a visual representation of the maze
        String topLine = "I"; // the top border of our maze starts with a vertical barrier
        int rows = maze.length; // establish amount of rows
        int cols = maze[0].length; // establish amount of columns
        for (int firstLine = 0; firstLine < cols; firstLine++) {
            topLine += "===I"; // loop through each column, adding a horizontal and vertical barrier at each cell
        }
        System.out.println(topLine); // print entire top line
        for (int i = 0; i < rows; i++) { // loop through each row
            String visitLine = ""; // string representing the traversable part of a row
            String borderLine = "I"; // string representing the barrier between a row
            if (i == startRow) { // if at startRow
                visitLine += " "; // begin that row with no vertical barrier, creating an entry point
            } else {
                visitLine += "|"; // vertical barrier
            }
            for (int j = 0; j < cols; j++) { // loop through each cell
                if (!maze[i][j].getVisited()) { // if that cell has not been visited
                    visitLine += "   "; // add 3 spaces to visitLine, representing an empty cell
                } else {
                    visitLine += " * "; // if the cell has been visited, add an asterisk between two spaces, representing the path of the player
                }
                if ((j == cols - 1) && (i == endRow)) { // if the current cell is at the last column and on the endRow
                    visitLine += " ";  // end that row with no vertical barrier, creating an exit point
                } else { // not at exit point
                    if (!maze[i][j].getRight()) {  // if the current cell has no right barrier
                        visitLine += " "; // add a space so that the player can travel into the cell to right
                    } else {
                        visitLine += "|"; // if there is a right barrier, add the vertical barrier
                    }
                }
                if (!maze[i][j].getBottom()) { // if the current cell has no bottom barrier
                    borderLine += "   I"; // add three spaces before the vertical barrier so the player can travel into the cell beneath it
                } else {
                    borderLine += "===I"; // if it has a bottom barrier, add the horizontal barrier
                }
            }
            System.out.println(visitLine); // before we go to the next row, we print both lines
            System.out.println(borderLine);
        }
    }

    public void solveMaze() { // method to have program solve maze
        Q1Gen mazeQueue = new Q1Gen();
        int rows = maze.length; // establish amount of rows
        int cols = maze[0].length; // establish amount of columns
        int[] coord = {startRow, 0}; // create an array holding the starting coordinates
        mazeQueue.add(coord); // add the starting coordinates to a queue
        while (mazeQueue.length() != 0) { // while the queue representing the maze is not empty
            coord = (int[]) mazeQueue.remove(); // set the coord array to the top position in the queue and dequeue it
            int currentRow = coord[0]; // integers representing the current row and column
            int currentCol = coord[1];
            maze[currentRow][currentCol].setVisited(true); // mark the current position as visited
            if (currentRow == endRow && currentCol == cols - 1) { break; } // if the current cell is the exit point, break from while loop; maze has been traversed
            else {
                if (currentRow - 1 >= 0 && !maze[currentRow - 1][currentCol].getVisited() && !maze[currentRow - 1][currentCol].getBottom()) { // if the above cell is in bounds, not yet visited, and does not have a bottom barrier
                    int[] temp = {currentRow - 1, currentCol}; // create a temporary array holding the above cell
                    mazeQueue.add(temp); // the above cell is added to the queue
                }
                if (currentRow + 1 <= rows - 1 && !maze[currentRow + 1][currentCol].getVisited() && !maze[currentRow][currentCol].getBottom()) { // if the below cell is in bounds, not yet visited, and the current cell has no bottom barrier
                    int[] temp = {currentRow + 1, currentCol}; // create a temporary array holding the below cell
                    mazeQueue.add(temp); // the below cell is added to the queue
                }
                if (currentCol - 1 >= 0 && !maze[currentRow][currentCol - 1].getVisited() && !maze[currentRow][currentCol - 1].getRight()) { // if the cell to the left is in bounds, not yet visited, and does not have a right barrier
                    int[] temp = {currentRow, currentCol - 1}; // create a temporary array holding the cell to the left
                    mazeQueue.add(temp); // the cell to the left is added to the queue
                }
                if (currentCol + 1 <= cols - 1 && !maze[currentRow][currentCol + 1].getVisited() && !maze[currentRow][currentCol].getRight()) { // if the cell to the right is in bounds, not yet visited, and the current cell has no right barrier
                    int[] temp = {currentRow, currentCol + 1}; // create a temporary array holding the cell to the right
                    mazeQueue.add(temp); // the cell to the right is added to the queue
                }
            }
        }
        printMaze(); // now that the whole maze has been traversed, print the maze
    }

    public static void main(String[] args){
        MyMaze myMaze = makeMaze(); // make a new maze
        myMaze.solveMaze(); // solve the maze
    }
}

// Dylan Haugee - hauge919
// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;
import java.util.Scanner;

public class FractalDrawer {
    private double totalArea=0;  // member variable for tracking the total area

    public FractalDrawer() {}  // constructor

    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal
    public double drawFractal(String type) {
        Canvas vas = new Canvas(1000, 1000); // creating the canvas
        Color c1 = new Color(53, 0, 107); // initial color for the selected shape
        if (type.equals("triangle")) { // testing which shape was input
           drawTriangleFractal(250, 250, 375, 625, c1, vas, 7); // calling function to begin the fractal
           return totalArea;
        }
            else if (type.equals("circle")) {
                drawCircleFractal(75, 450, 450, c1, vas, 7); // calling function to begin the fractal
                return totalArea;
            }
                else if (type.equals("rectangle")) {
                    drawRectangleFractal(80, 80, 460, 420, c1, vas, 7); // calling function to begin the fractal
                    return totalArea;
                }
                    else { // establishing what happens if no valid shape is input
                        System.out.println("something went wrong and it's not our fault... enter a valid input");
                        return -1; // necessary return statement
                    }
    }

    // drawTriangleFractal draws a triangle fractal using recursive techniques
    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level){
        if (level == 0) { // base case if fractal has reached level 0
            return;      // variable level is set to 7 and decreases by 1 with every level of triangle
        }
        Triangle newTri = new Triangle(x, y, width, height); // creating a triangle with the specifications given when drawTriangleFractal was called
        totalArea = totalArea + newTri.calculateArea(); // whenever a triangle is created, calculate its area and add it to the total
        newTri.setColor(c); // setting the color to c, which initially represents c1
        can.drawShape(newTri); // drawing the triangle

        Color c2 = new Color(204,153,255); // creating 6 more colors using rbg 0-255
        Color c3 = new Color(102,102,255);
        Color c4 = new Color(127,0,255);
        Color c5 = new Color(0,0,153);
        Color c6 = new Color(0,51,25);
        Color c7 = new Color(117,209,26);

        Color colorArray[] = {Color.BLACK,Color.DARK_GRAY, c7, c6, c5, c4, c3, c2}; // creating an array of colors c2-c7 plus black and dark gray
        c = colorArray[level]; // with level initially being 7, c changes from c1 to c2, then to c3 and so on as level decreases

        drawTriangleFractal(width/2, height/2, x + width/4, y-height, c, can, level-1);
        // calling the function for a half sized triangle on the top of the current triangle, with the level minus one
        drawTriangleFractal(width/2, height/2, x - width/2, y, c, can, level-1);
        // calling the function for a half sized triangle on the lower left corner of the current triangle, with the level minus one
        drawTriangleFractal(width/2, height/2, x + width, y, c, can, level-1);
        // calling the function for a half sized triangle on the lower right corner of the current triangle, with the level minus one
        }

    // drawCircleFractal draws a circle fractal using recursive techniques
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        if (level == 0) { // base case if fractal has reached level 0
            return;      // variable level is set to 7 and decreases by 1 with every level of circle
        }
        Circle newCir = new Circle(x, y, radius); // creating a circle with the specifications given when drawCircleFractal was called
        totalArea = totalArea + newCir.calculateArea(); // whenever a circle is created, calculate its area and add it to the total
        newCir.setColor(c); // setting the color to c, which initially represents c1
        can.drawShape(newCir); // drawing the circle

        Color c2 = new Color(204,153,255); // creating 6 more colors using rbg 0-255
        Color c3 = new Color(102,102,255);
        Color c4 = new Color(127,0,255);
        Color c5 = new Color(0,0,153);
        Color c6 = new Color(0,51,25);
        Color c7 = new Color(117,209,26);

        Color colorArray[] = {Color.BLACK,Color.DARK_GRAY, c7, c6, c5, c4, c3, c2}; // creating an array of colors c2-c7 plus black and dark gray
        c = colorArray[level]; // with level initially being 7, c changes from c1 to c2, then to c3 and so on as level decreases

        drawCircleFractal(radius/2, x - radius, y + radius, c, can, level-1);
        // calling the function for a half sized circle on the lower left corner of the current circle, with the level minus one
        drawCircleFractal(radius/2, x - radius, y - radius, c, can, level-1);
        // calling the function for a half sized circle on the upper left corner of the current circle, with the level minus one
        drawCircleFractal(radius/2, x + radius, y + radius, c, can, level-1);
        // calling the function for a half sized circle on the lower right corner of the current circle, with the level minus one
        drawCircleFractal(radius/2, x + radius, y - radius, c, can, level-1);
        // calling the function for a half sized circle on the upper right corner of the current circle, with the level minus one
    }

    // drawRectangleFractal draws a rectangle fractal using recursive techniques
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        if (level == 0) { // base case if fractal has reached level 0
            return;      // variable level is set to 7 and decreases by 1 with every level of rectangle
        }
        Rectangle newRec = new Rectangle(x, y, width, height); // creating a rectangle with the specifications given when drawRectangleFractal was called
        totalArea = totalArea + newRec.calculateArea(); // whenever a rectangle is created, calculate its area and add it to the total
        newRec.setColor(c); // setting the color to c, which initially represents c1
        can.drawShape(newRec); // drawing the rectangle

        Color c2 = new Color(204,153,255); // creating 6 more colors using rbg 0-255
        Color c3 = new Color(102,102,255);
        Color c4 = new Color(127,0,255);
        Color c5 = new Color(0,0,153);
        Color c6 = new Color(0,51,25);
        Color c7 = new Color(117,209,26);

        Color colorArray[] = {Color.BLACK,Color.DARK_GRAY, c7, c6, c5, c4, c3, c2}; // creating an array of colors c2-c7 plus black and dark gray
        c = colorArray[level]; // with level initially being 7, c changes from c1 to c2, then to c3 and so on as level decreases

        drawRectangleFractal(width/2, height/2, x - width/2, y+height, c, can, level-1);
        // calling the function for a half sized rectangle on the upper left corner of the current rectangle, with the level minus one
        drawRectangleFractal(width/2, height/2, x - width/2, y-height/2, c, can, level-1);
        // calling the function for a half sized rectangle on the lower left corner of the current rectangle, with the level minus one
        drawRectangleFractal(width/2, height/2, x + width, y+height, c, can, level-1);
        // calling the function for a half sized rectangle on the upper right corner of the current rectangle, with the level minus one
        drawRectangleFractal(width/2, height/2, x + width, y-height/2, c, can, level-1);
        // calling the function for a half sized rectangle on the lower right corner of the current rectangle, with the level minus one

    }

    public static void main(String[] args){
        System.out.println("triangle, rectangle, or circle fractal?"); // prompt for user
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.nextLine(); // creates scanner and takes in input
        FractalDrawer frac = new FractalDrawer(); // creates a FractalDrawer instance, frac
        System.out.println("\nThe total area of all shapes in this fractal is:\n" + frac.drawFractal(input) + " units\nThank you, come again!");
        // calls drawFractal for the user's input, creating the fractal drawing and returning the area
    }
}

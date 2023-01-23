// Dylan Haugee - hauge919
import java.awt.Color;

public class Triangle {
  public double xPos;
  public double yPos;
  public double width;
  public double height;
  Color color = Color.PINK;
  // establish 4 member variables and set a color

  public static void main(String[] args) { // main method with some testing
  Triangle myTri = new Triangle(35, 35, 5, 5);
  System.out.println(myTri.calculateArea());
  System.out.println(myTri.getColor());
  myTri.setWidth(6);
  myTri.setHeight(4);
  System.out.println(myTri.calculatePerimeter());
  }

  // constructor for member variables
  public Triangle(double initXPos, double initYPos, double initWidth, double initHeight) {
    this.xPos = initXPos;
    this.yPos = initYPos;
    this.width = initWidth;
    this.height = initHeight;
  }

  // equation for perimeter using width and height
  public double calculatePerimeter() {
    return width + Math.sqrt((Math.pow(width, 2)) + (4*(Math.pow(height, 2))));
  }

  public double calculateArea() {
    return (width * height)/2;
  } // equation for area using width and height

  public void setColor(Color shade) {
    this.color = shade;
  } // method for setting the color

  public void setPos(double newXPos, double newYPos) { // method for setting a new x, y coordinate
    this.xPos = newXPos;
    this.yPos = newYPos;
  }

  public void setHeight(double newHeight) {
    this.height = newHeight;
  }
  // 2 methods for setting a new height and width
  public void setWidth(double newWidth) {
    this.width = newWidth;
  }

  // 5 methods for getting different variables
  public Color getColor() {
    return color;
  }

  public double getXPos() {
    return xPos;
  }

  public double getYPos() {
    return yPos;
  }

  public double getHeight(){
    return height;
  }

  public double getWidth(){
    return width;
  }

}

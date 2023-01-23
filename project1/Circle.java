// Dylan Haugee - hauge919

import java.awt.Color;

public class Circle {
  public double xPos;
  public double yPos;
  public double radius;
  Color color = Color.PINK;
  // establish 3 member variables and set a color

  public static void main(String[] args) { // main method with some testing
  Circle myCircle = new Circle(35, 35, 2.5);
  myCircle.setColor(Color.BLACK);
  System.out.println(myCircle.calculatePerimeter());
  System.out.println(myCircle.calculateArea());
  System.out.println(myCircle.getColor());
  }

  // constructor for member variables
  public Circle(double initXPos, double initYPos, double initRad) {
    this.xPos = initXPos;
    this.yPos = initYPos;
    this.radius = initRad;
  }

  public double calculatePerimeter() { // equation for perimeter using radius
    double circumference = (2 * Math.PI * radius);
    return circumference;
  }

  public double calculateArea() { // equation for area using radius
    double area = Math.PI * Math.pow(radius, 2);
    return area;
  }

  public void setColor(Color shade) {
    this.color = shade;
  } // method for setting the color

  public void setPos(double newXPos, double newYPos) { // method for setting a new x, y coordinate
    this.xPos = newXPos;
    this.yPos = newYPos;
  }

  public void setRadius(double newRad) {
    this.radius = newRad;
  } // method for setting a new radius

  // 4 methods for getting different variables
  public Color getColor() {
    return color;
  }

  public double getXPos() {
    return xPos;
  }

  public double getYPos() {
    return yPos;
  }

  public double getRadius() {
    return radius;
  }
}

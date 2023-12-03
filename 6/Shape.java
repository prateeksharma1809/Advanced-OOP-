package h6;


/**
 * Author - Prateek Sharma, Kirti Sharma
 * The abstract class - Shape has two methods without implementation.
 * Abstract keyword restricts object creation.
 * If any abstract method, class should be abstract.
 * In situations,where different classes would give different implementation for same method,
 * we use abstract class and the child classes extend the abstract class and completes the implementation.
 * 
 */
public abstract class Shape {

	private double x;
    private double y;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // Abstract methods to calculate area 
    public abstract double calculateArea();
    
    //Abstract methods to calculate perimeter
    public abstract double calculatePerimeter();
    // Getter for x and y coordinates
    public double getX() {
        return x;
    }
    //Setter for x and y coordinates
    public double getY() {
        return y;
    }
}

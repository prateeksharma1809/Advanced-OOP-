package h6;


/**
 *  Author - Prateek Sharma, Kirti Sharma
 * Concrete class for a specific shape - Circle
 * The child class (if it is not abstract) is responsible to implement the methods in Shape class.
 */
public class Circle extends Shape{
	private double radius;

    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

}

package h6;


/**
 * @author prateek sharma, Kirti Sharma
 * Concrete class for a specific shape - Rectangle
 * The child class (if it is not abstract) is responsible to implement the methods in Shape class.
 */
public class Rectangle extends Shape{
	
	private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}




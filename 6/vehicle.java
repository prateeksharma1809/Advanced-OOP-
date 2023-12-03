package vehicle;


/**
 * Author - Prateek Sharma, Kirti Sharma
 * The abstract class - vehicle has two methods without implementation.
 * Abstract keyword restricts object creation.
 * If any abstract method, class should be abstract.
 * In situations,where different classes would give different implementation for same method,
 * we use abstract class and the child classes extend the abstract class and completes the implementation.
 * 
 */
public abstract class vehicle {
	
	public abstract int getNoOfWheels();

	public abstract int getAverage();

}

/**
 * The child class (if it is not abstract) is responsible to implement the methods in Vehicle class.
 * Bicycle(child class) extends Vehicle(Abstract class) and implements both of its methods.
 */
class bicycle extends vehicle{
	public int getNoOfWheels() {
		return 2;
	}
	
	public int getAverage() {	
		return 2;
	}
}

/**
 * The child class (if it is not abstract) is responsible to implement the methods in Vehicle class.
 * Car(child class) extends Vehicle(Abstract class) and implements both of its methods.
 */
class car extends vehicle{

	public int getNoOfWheels() {
		return 4;
	}

	public int getAverage() {
		return 25;
	}
	
}

/**
 * After completing the implementation in the child classes, we can now create objects of child classes
 * and call the methods.
 */
class result{
	
	public static void main(String[] args) {
		bicycle b = new bicycle();
		System.out.println(b.getNoOfWheels());
		System.out.println(b.getNoOfWheels());
				
		car c = new car();
		System.out.println(c.getNoOfWheels());
		System.out.println(c.getNoOfWheels());
	}
	
}
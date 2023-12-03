package h6;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 * Concrete class for a specific animal - Eagle
 * which exhibit the animal behavior but also able to fly
 *
 */
public class Eagle implements Animal, Fly {

	// Implementation for flying behavior
	@Override
	public void fly() {
		System.out.println("Eagle Fly");
		
	}

	// Implementation for eating behavior
	@Override
	public void eat() {
		System.out.println("Eagle eat");
		
	}

	// Implementation for sleeping behavior
	@Override
	public void sleep() {
		System.out.println("Eagle sleep");
		
	}

	// Implementation for making sound
	@Override
	public void makeSound() {
		System.out.println("Eagle whistles");
		
	}

}

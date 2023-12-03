package h6;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 * Concrete class for a specific animal - Dolphin
 * which exhibit the animal behavior but also able to swim
 *
 */
public class Dolphin implements Animal, Swim  {
	// Implementation for swimming behavior
	@Override
	public void swim() {
		System.out.println("Dolphin Swims");
		
	}

	// Implementation for sleeping behavior
	@Override
	public void eat() {
		System.out.println("Dolphin eats");
		
	}
	
	// Implementation for eating behavior
	@Override
	public void sleep() {
		System.out.println("Dolphin sleeps");
		
	}
	// Implementation for making sound
	@Override
	public void makeSound() {
		System.out.println("Dolphin makes ultrasonic sounds");
		
	}

}

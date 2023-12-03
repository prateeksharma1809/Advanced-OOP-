package hw.h11;

import java.util.Iterator;


public class Test {
	public static void testInteger0(StorageInterface<Integer> aStorageInterfaceInteger) throws InterruptedException	{
		final StorageInterface<Integer> nStorageInterfaceInteger = aStorageInterfaceInteger;
		System.out.println("Test 0");
		Iterator<Integer> aIterator =  aStorageInterfaceInteger.iterator();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				String[] array = {"2","3","4"};
				for (String string : array) {
					System.out.println("adding :" +string);
					nStorageInterfaceInteger.add(Integer.valueOf(string));
				}
				
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				String[] array = { "5","6","7"};
				for (String string : array) {
					System.out.println("adding :" +string);
					nStorageInterfaceInteger.add(Integer.valueOf(string));
				}
				
			}
		});
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				String[] array = { "10","11","12"};
				for (String string : array) {
					System.out.println("adding :" +string);
					nStorageInterfaceInteger.add(Integer.valueOf(string));
				}
				
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(aStorageInterfaceInteger);
		t3.start();
		while ( aIterator.hasNext() )
			System.out.println("in Test: " + aIterator.next());
		t3.join();
		
		
//		aStorageInterfaceInteger.add(null);
//
//		aStorageInterfaceInteger.add(Integer.valueOf("2"));
//		aStorageInterfaceInteger.add(Integer.valueOf("3"));
//		aStorageInterfaceInteger.add(Integer.valueOf("4"));
//		System.out.println("3: " + aStorageInterfaceInteger);
//		
//		aStorageInterfaceInteger.add(Integer.valueOf("5"));
	}
	public static void testInteger1(StorageInterface<Integer> aStorageInterfaceInteger)	{
		System.out.println("Test 2");
		Iterator<Integer> aIterator =  aStorageInterfaceInteger.iterator();

		aStorageInterfaceInteger.add(Integer.valueOf("1"));
		aStorageInterfaceInteger.add(Integer.valueOf("2"));
		aStorageInterfaceInteger.add(Integer.valueOf("3"));
		aIterator.hasNext();
		aIterator.remove();
		System.out.println("in Test removed: " + aIterator.next());
		while ( aIterator.hasNext() )
			System.out.println("in Test: " + aIterator.next());
	}
	public static void testInteger2(StorageInterface<Integer> aStorageInterfaceInteger)	{
		System.out.println("Test 1");
		Iterator<Integer> aIterator =  aStorageInterfaceInteger.iterator();

		aStorageInterfaceInteger.add(Integer.valueOf("1"));
		aStorageInterfaceInteger.add(Integer.valueOf("2"));
		aIterator.hasNext();
		aStorageInterfaceInteger.add(Integer.valueOf("3"));
		System.out.println("in Test: " + aIterator.next());
		System.out.println("This line should not be printed");
	}
	public static void main(String args[] ) throws InterruptedException	{
		try {
			StorageInterface<Integer> aStorageInterfaceInteger = new SortedStorage<Integer>(false);
			testInteger0(aStorageInterfaceInteger);

//			aStorageInterfaceInteger = new SortedStorage<Integer>(false);
//			testInteger1(aStorageInterfaceInteger);
//
//			aStorageInterfaceInteger = new SortedStorage<Integer>(false);
//			testInteger2(aStorageInterfaceInteger);
		} catch ( StorageHasBeenModifiedException e )	{
			System.out.println(e);
		}
	}
}

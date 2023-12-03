package h8;

import java.util.Comparator;

public class Test3 {
	MyList<String> aList = new MyList<String>();

	@SuppressWarnings("unused")
	public void init() {
		String[] theWords = { "a", "c", "b", "m", "n", "o" };
		String[] gotTheWords; // not used, hence warning
		for (int index = 0; index < theWords.length; index++)
			aList.add(theWords[index]);
	}

	public void testNatural() {
		System.out.println("natural before sort - aList: " + aList);
		MyCollections.sort(aList);
		System.out.println("natural after  sort - aList: " + aList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testReverse() {
		System.out.println("reverse aList: " + aList);
		/*
		 * MyComparator is a raw type. References to generic type MyComparator<T> should
		 * be parameterized
		 * 
		 */
		Comparator myComparator = new MyComparator();
		/*
		 * Type safety: Unchecked invocation sort(MyList<String>, Comparator) of the
		 * generic method sort(MyList<T>, Comparator<T>) of type MyCollections
		 */
		MyCollections.sort(aList, myComparator);
		System.out.println("reverse aList: " + aList);
	}

	public static void main(String args[]) {
		Test3 natural = new Test3();
		natural.init();
		natural.testNatural();
		Test3 reverse = new Test3();
		reverse.init();
		reverse.testReverse();
	}

}
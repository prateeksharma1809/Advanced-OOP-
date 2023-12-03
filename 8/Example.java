package h8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Example {

	/**
	 * A public static method named `sort` that accepts a list of elements of type
	 * `T`. This type `T` must implement or extend the `Comparable` interface, which
	 * means these elements can be compared to themselves or their superclasses. The
	 * method is intended to sort the list based on the natural ordering of its
	 * elements.
	 * 
	 * @param <T>
	 * @param list
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> list) {

	}

	/**
	 * A public static method named `sort` that accepts a list of elements of type
	 * `T` and a `Comparator` for type `T` or its superclasses. This method sorts
	 * the list based on the ordering provided by the given comparator.
	 * 
	 * @param <T>
	 * @param list
	 * @param c
	 */
	public static <T> void sort(List<T> list, Comparator<? super T> c) {

	}

	/**
	 * A public static method named `binarySearch` that accepts a list and a key,
	 * both of the same type `T`. The elements in the list must be of a type that
	 * implements or extends the `Comparable` interface (meaning they can be
	 * compared to type `T` or its superclasses). The method performs a binary
	 * search on the list to find the given key and returns the index of the key if
	 * found, or a negative value if not found.
	 *
	 * @param <T>
	 * @param list
	 * @param key
	 * @return
	 */
	public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
		return 0;
	}

	/**
	 * A public static method named `shuffle` that accepts a list of any type (`?`
	 * denotes wildcard, which means any type is acceptable). This method rearranges
	 * the elements of the list in a random order.
	 * @param list
	 */
	public static void shuffle(List<?> list) {

	}

	/**
	 * A public static method named `copy` that accepts two lists: a destination
	 * list (`dest`) and a source list (`src`). The source list contains elements of
	 * type `T` or its subclasses, while the destination list can hold elements of
	 * type `T` or its superclasses. The method copies elements from the source list
	 * to the destination list.
	 * 
	 * @param <T>
	 * @param dest
	 * @param src
	 */
	public static <T> void copy(List<? super T> dest, List<? extends T> src) {

	}
	
	public static void main(String[] args) {
		List<String> strlist = new ArrayList<>();
		List<Node<String>> nodeList = new ArrayList<>();
		//1
		Example.sort(strlist);
		//below will result in an error as the Node class does not impliment comparable interface
		Example.sort(nodeList);
		
		//2
		Example.sort(nodeList, new NodeComparator<String>());
		
		//3
		Example.binarySearch(strlist, "search me");
		//This will fail 
		Example.binarySearch(nodeList,new Node<String>("search me", null) );
		
		//4
		Example.shuffle(strlist);
		Example.shuffle(nodeList);
		
		List<ChildNode<String>> childNodeList = new ArrayList<>();
		List<SuperNode<String>> superNodeList = new ArrayList<>();

		//5
		Example.copy(superNodeList, childNodeList);
		Example.copy(nodeList, childNodeList);
		Example.copy(nodeList, nodeList);
		//error 
		Example.copy(nodeList, superNodeList);
		Example.copy(childNodeList,superNodeList );
		
		
	}
	class  NodeComparator<T> implements Comparator<Node<T>>{


		@Override
		public int compare(Node<T> o1, Node<T> o2) {
			//some comparison logic
			return 0;
		}
		
	}
	
}

package h8;

import java.util.Comparator;

public class MyCollections {

	public static <T> void sort(MyList<T> aList, Comparator<T> myComparator) {
		aList.sort(myComparator);
		
	}

	public static <T> void sort(MyList<T> aList) {
		aList.sort();
	}

}

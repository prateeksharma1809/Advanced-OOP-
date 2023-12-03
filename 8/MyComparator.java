package h8;

import java.util.Comparator;

public class MyComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		int returnvalue =0;
		if(o1.equals(o2)) {
			returnvalue= 0;
		}else if(o1.hashCode()>o2.hashCode()) {
			returnvalue= -1;
		}else {
			returnvalue= 1;
		}
		return returnvalue;
	}

}

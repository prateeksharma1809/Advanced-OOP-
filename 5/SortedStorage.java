package h5;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class SortedStorage<T extends Comparable<T>> implements Comparable<SortedStorage<T>> {

	private Node<T> head = null;
	private int nullCount = 0;

	/**
	 * Check if the storage includes a null element
	 * 
	 * @return true if there are null elements in the storage
	 */
	public boolean includesNull() {
		return nullCount > 0;
	}

	/**
	 * Find a string or null in the storage
	 * 
	 * @param data value to find
	 * @return true if the object value is present in the storage
	 */
	public boolean find(T data) {
		if (data == null) {
			return nullCount > 0;
		}
		Node<T> current = head;
		while (current != null) {
			if (current.getData().equals(data)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	/**
	 * deletes the entry from the storage and returns false if not present
	 * 
	 * @param data -> entry to be deleted
	 * @return true if deleted else false
	 */
	public boolean delete(T data) {
		if (data == null) {
			if (nullCount > 0) {
				nullCount--;
				return true;
			}
			return false;
		}
		if (head == null) {
			return false;
		}
		if (head.getData().equals(data)) {
			head = head.getNext();
			return true;
		}
		Node<T> current = head;
		while (current.getNext() != null && 0 != current.getNext().getData().compareTo(data)) {
			current = current.getNext();
		}

		if (current.getNext() != null) {
			current.setNext(current.getNext().getNext());
			return true;
		}
		return false;
	}

	/**
	 * Add a string or null to the storage
	 * 
	 * @param data -> value to be added to storage
	 * @return true if added else false
	 */
	public boolean add(T data) {
		if (data == null) {
			nullCount++;
			return true;
		}
		if (head == null || 0 < head.getData().compareTo(data)) {
			head = new Node<T>(data, head);
			return true;
		} else {
			Node<T> current = head;
			while (current.getNext() != null && 0 > current.getNext().getData().compareTo(data)) {
				current = current.getNext();
			}

			if ((current.getNext() == null && !current.getData().equals(data)) || !find(data)) {
				current.setNext(new Node<T>(data, current.getNext()));
				return true;
			}
		}
		return false;
	}

	/**
	 * method to convert the storage to string representation
	 */
	@Override
	public String toString() {
		String sortedStorageString = "SortedStorage [";
		Node<T> current = head;
		while (current != null) {
			sortedStorageString += "( " + current.getData().toString() + " )";
			current = current.getNext();
		}
		for (int index = 0; index < nullCount; index++) {
			sortedStorageString += "( null )";
		}

		return sortedStorageString + "]";
	}

	/**
	 * calculates the length of the sorted storage and returns it
	 * 
	 * @return length of sorted storage
	 */
	private int length() {
		int sum = 0;
		if (head != null) {
			Node<T> current = head;
			while (current.getNext() != null) {
				sum++;
				current = current.getNext();
			}
		}
		return sum + nullCount;

	}

	/**
	 * overides the compare to method from comparable interface 
	 * compares them based on length and then if length is equal are they actually same object 
	 * @param object from which it needs to compare
	 * @return value based on the comparison 
	 */
	@Override
	public int compareTo(SortedStorage<T> obj) {
		if (this.length() > obj.length()) {
			return 1;
		} else if (this.length() < obj.length()) {
			return -1;
		} else {
			return compareObjects(obj); // cannot return 0 if 2 sorted storage with same length
		}
	}

	/**
	 * compares the object address 
	 * @param obj -> obejct from which to compare
	 * @return -> 0 if matched else 1
	 */
	private int compareObjects(SortedStorage<T> obj) {
		if (this == obj) {
			return 0;
		}
		return 1;
	}

}

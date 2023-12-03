package h6;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class SortedStorageImpl<T extends Comparable<T>> implements SortedStorage<T> {

	private Node<T> head = null;
	private int nullCount = 0;
	private boolean allowDuplication;
	
	public SortedStorageImpl(boolean allowDuplication) {
		this.allowDuplication = allowDuplication;
	}
	
	/**
	 *  allows to set the duplication flag change in run time 
	 * @param allowDuplication
	 */
	public void setAllowDuplication(boolean allowDuplication) {
		if(!allowDuplication) {
			removeDuplicates();
		}
		this.allowDuplication=allowDuplication;
			
	}

	/**
	 * remove duplicates if the storage is converted from normal to set 
	 */
	private void removeDuplicates() {
		if(head!=null) {
			Node<T> current = head;
			Node<T> previous = head;
			while(previous.getNext()!=null) {
				while(current.getNext()!=null && previous.getData()==current.getData()) {
					current=current.getNext();
				}
				previous.setNext(current.getNext());
				previous =previous.getNext();
			}
		}
	}
	

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

			if ((current.getNext() == null && !current.getData().equals(data)) || (!find(data) || allowDuplication)) {
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
	public int length() {
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
		if (this.hashCode() < obj.hashCode()) {
			return 1;
		} else if (this.hashCode() > obj.hashCode()) {
			return -1;
		} else {
			return 0;
		}

	}

	/**
	 * use to calculate unique code for every unique object and it will be same for each
	 * @param object from which it needs to compare
	 * @return value based on the comparison 
	 */
	@Override
	public int hashCode() {
	    int result = 17; // Start with a prime number as an initial value
	    result = 31 * result + (head != null ? head.hashCode() : 0); // Include the head node's hash code if not null
	    result = 31 * result + nullCount; // Include the nullCount field
	    result = 31 * result + this.length();
	    return result;
	}

}

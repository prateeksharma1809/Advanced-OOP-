package h4;

public class SortedStorage {

	private Node head = null;
	private int nullCount = 0;

	/**
	 *  Check if the storage includes a null element
	 * @return true if there are null elements in the storage
	 */
	public boolean includesNull() {
		return nullCount > 0;
	}

	/**
	 *  Find a string or null in the storage
	 * @param string value to find 
	 * @return true if the object value is present in the storage 
	 */
	public boolean find(String string) {
		if (string == null) {
			return nullCount > 0;
		}
		Node current = head;
		while (current != null) {
			if (current.getData().equals(string)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	/**
	 * deletes the entry from the storage and returns false if not present
	 * @param string -> entry to be deleted 
	 * @return true if deleted else false
	 */
	public boolean delete(String string) {
		if (string == null) {
			if (nullCount > 0) {
				nullCount--;
				return true;
			}
			return false;
		}
		if (head == null) {
			return false;
		}
		if (head.getData().equals(string)) {
			head = head.getNext();
			return true;
		}
		Node current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(string)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            return true;
        }
        return false;
	}

	/**
	 *  Add a string or null to the storage
	 *  @param string -> value to be added to storage 
	 *  @return true if added else false
	 *  */
	public boolean add(String string) {
//		System.out.println("Calling add for : "+string);
		if (string == null) {
			nullCount++;
			return true;
		}
		if (head == null || head.getData().compareTo(string)>0) {
			head = new Node(string, head);
			return true;
		} else {
			Node current = head;
			Node previous = head;
			while (current.getNext() != null && current.getData().compareTo(string) < 0) {
				previous=current;
				current = current.getNext();
			}
			if ((current.getNext()==null && !current.getData().equals(string)) || !current.getData().equals(string)) {
				previous.setNext(new Node(string, current));
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
		Node current = head;
		while(current!=null) {
			sortedStorageString+="( "+current.getData()+" )";
			current=current.getNext();
		}
		for(int index=0;index<nullCount;index++) {
			sortedStorageString+="( null )";
		}
		
		return sortedStorageString+"]";
	}

}

package h6;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class Node2{

	private Comparable data;
	private Node2 next;

	/**
	 * getter method 
	 * @return data value for the object calling 
	 */
	public Comparable getData() {
		return data;
	}

	/**
	 * setter method
	 * @param data sets the data value for the calling object 
	 */
	public void setData(Comparable data) {
		this.data = data;
	}

	/**
	 * getter method
	 * @return the next node value for the instance of object 
	 */
	public Node2 getNext() {
		return next;
	}

	/**
	 * setter method
	 * @param next sets the next node reference value for the calling object 
	 */
	public void setNext(Node2 next) {
		this.next = next;
	}

	/**
	 * constructor for the node object 
	 * @param data -> initial value of the data object 
	 * @param next -> initial value for the next  
	 */
	Node2(Comparable data, Node2 next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * method to print the string value of an object 
	 * @return string value of the object 
	 */
	@Override
	public String toString() {
		return "Node [data=" + data.toString() + ", next=" + next + "]";
	}
}

package h4;

public class Node {

	private String data;
	private Node next;

	/**
	 * getter method 
	 * @return data value for the object calling 
	 */
	public String getData() {
		return data;
	}

	/**
	 * setter method
	 * @param data sets the data value for the calling object 
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * getter method
	 * @return the next node value for the instance of object 
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * setter method
	 * @param next sets the next node reference value for the calling object 
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * constructor for the node object 
	 * @param data -> initial value of the data object 
	 * @param next -> initial value for the next  
	 */
	Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * method to print the string value of an object 
	 * @return string value of the object 
	 */
	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}
}

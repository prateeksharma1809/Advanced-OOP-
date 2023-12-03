package hw.h11;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class Node<T>{

	private T data;
	private Node<T> next;

	/**
	 * getter method 
	 * @return data value for the object calling 
	 */
	public T getData() {
		return data;
	}

	/**
	 * setter method
	 * @param data sets the data value for the calling object 
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * getter method
	 * @return the next node value for the instance of object 
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * setter method
	 * @param next sets the next node reference value for the calling object 
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * constructor for the node object 
	 * @param data -> initial value of the data object 
	 * @param next -> initial value for the next  
	 */
	Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * method to print the string value of an object 
	 * @return string value of the object 
	 */
//	@Override
	public String toString() {
		return "Node [data=" + data.toString() + ", next=" + next + "]";
	}
}

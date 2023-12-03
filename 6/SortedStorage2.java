package h6;


/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *	 interface for sorted storage with its methods 
 */
public interface SortedStorage2 extends Comparable{

	boolean includesNull();

	boolean find(Comparable data);

	boolean delete(Comparable data);

	boolean add(Comparable data);

	int length();

	int hashCode();

}

package h8;
/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 * @param <T> generic type 
 * interface for sorted storage with its methods 
 */
public interface StorageInterface<T extends Comparable<T>> extends Comparable<StorageInterface<T>>, Iterable<T> {

	boolean includesNull();

	boolean find(T data);

	boolean delete(T data);

	boolean add(T data);

	int length();

	int hashCode();

}

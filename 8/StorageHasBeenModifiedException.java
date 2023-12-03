package h8;

public class StorageHasBeenModifiedException extends RuntimeException {
	/**
	 * for serialization purpose
	 */
	private static final long serialVersionUID = 1L;

	StorageHasBeenModifiedException(){
		super();
	}
	
	StorageHasBeenModifiedException(String msg){
		super(msg);
	}

}

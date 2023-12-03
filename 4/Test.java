package h4;

public class Test {

	/**
	 * method to test the SortedStorage class
	 * @param aSortedStorage -> instance of the storage class
	 */
	public static void testIt(SortedStorage aSortedStorage)	{

		String toInsert[] = { "8", null, "1","10" ,"8","100","2","1","10",null,null,"100"};
		String toDelete[] = { "1", null, "1","200","8"};
		String toFind[]   = { "1", null, "1","100","10","8"};


		for (int index = 0; index < toInsert.length; index ++ )	{
			System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
		}

		System.out.println("- includesNull: "  + aSortedStorage.includesNull());
		System.out.println("- toString: "  + aSortedStorage.toString());

		for (int index = 0; index < toFind.length; index ++ )	{
			System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
		}
	}
	public static void main(String args[] )	{
		SortedStorage aSortedStorage = new SortedStorage();
		testIt(aSortedStorage);
	}

}

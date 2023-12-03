package h6;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class Test {
	public static void testItWithString(SortedStorage aSortedStorage)	{

		String toInsert[] = { "8", null, "1", "2","8","1","2"};
		String toDelete[] = { "1", null, "1"};
		String toFind[]   = { "1", null, "1"};

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
		System.out.println(aSortedStorage.toString());
	}
	public static void testItWithIntegers(SortedStorage aSortedStorage)	{

		Integer toInsert[] = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2),  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};
		Integer toDelete[] = {  Integer.valueOf(8), null,  Integer.valueOf(1) };
		Integer toFind[]   = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};


		for (int index = 0; index < toInsert.length; index ++ )	{
			System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
		}

		System.out.println("- includesNull: "  + aSortedStorage.includesNull());
		System.out.println("- toIntegers: "  + aSortedStorage.toString());

		for (int index = 0; index < toFind.length; index ++ )	{
			System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
		}
		System.out.println(aSortedStorage.toString());
	}
	public static void testItWithSortedStorage(SortedStorage aSortedStorage,  SortedStorage[] theSortedStorages)	
	{
		SortedStorage<String> s = new SortedStorageImpl<>(true);
		s.add("1"); // this will be deleted if the check in compare to is not well written 
		SortedStorage toInsert[] = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1], s };
		SortedStorage toDelete[] = { new SortedStorageImpl<>(true) , theSortedStorages[0], theSortedStorages[1], null};
		SortedStorage toFind[]   = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1] };

		for (int index = 0; index < toInsert.length; index ++ )	{
			System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
		}

		System.out.println("- includesNull: "  + aSortedStorage.includesNull());
		System.out.println("- toIntegers: "  + aSortedStorage.toString());

		for (int index = 0; index < toFind.length; index ++ )	{
			System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
		}
		System.out.println(aSortedStorage.toString());
	}
	public void test()	{
		SortedStorage aSortedStringStorage = new SortedStorageImpl<>(false);
		testItWithString(aSortedStringStorage);

		SortedStorage aSortedIntegerStorage = new SortedStorageImpl<>(false);
		testItWithIntegers(aSortedIntegerStorage);

		SortedStorage aSortedSortedStorageStorage = new SortedStorageImpl<>(false);
		SortedStorage[] theSortedStorages = { aSortedStringStorage, aSortedIntegerStorage, aSortedSortedStorageStorage };
		testItWithSortedStorage(aSortedSortedStorageStorage, theSortedStorages);
	}
	public static void main(String args[] )	{
		new Test().test();	
	}
}


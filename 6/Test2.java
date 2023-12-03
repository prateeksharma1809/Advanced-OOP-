package h6;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class Test2 {
	public static void testItWithString(SortedStorage2 aSortedStringStorage)	{

		String toInsert[] = { "8", null, "1", "2","8","1","2"};
		String toDelete[] = { "1", null, "1"};
		String toFind[]   = { "1", null, "1"};

		for (int index = 0; index < toInsert.length; index ++ )	{
			System.out.println("- add(" + toInsert[index] + "): "  + aSortedStringStorage.add(toInsert[index]));
		}
		System.out.println("- includesNull: "  + aSortedStringStorage.includesNull());
		System.out.println("- toString: "  + aSortedStringStorage.toString());

		for (int index = 0; index < toFind.length; index ++ )	{
			System.out.println("- find(" + toFind[index] + "): "  + aSortedStringStorage.find(toFind[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStringStorage.delete(toDelete[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStringStorage.delete(toDelete[index]));
		}
		System.out.println(aSortedStringStorage.toString());
	}
	public static void testItWithIntegers(SortedStorage2 aSortedIntegerStorage)	{

		Integer toInsert[] = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2),  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};
		Integer toDelete[] = {  Integer.valueOf(8), null,  Integer.valueOf(1) };
		Integer toFind[]   = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};


		for (int index = 0; index < toInsert.length; index ++ )	{
			System.out.println("- add(" + toInsert[index] + "): "  + aSortedIntegerStorage.add(toInsert[index]));
		}

		System.out.println("- includesNull: "  + aSortedIntegerStorage.includesNull());
		System.out.println("- toIntegers: "  + aSortedIntegerStorage.toString());

		for (int index = 0; index < toFind.length; index ++ )	{
			System.out.println("- find(" + toFind[index] + "): "  + aSortedIntegerStorage.find(toFind[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedIntegerStorage.delete(toDelete[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedIntegerStorage.delete(toDelete[index]));
		}
		System.out.println(aSortedIntegerStorage.toString());
	}
	public static void testItWithSortedStorage(SortedStorage2 aSortedSortedStorageStorage,  SortedStorage2[] theSortedStorages)	
	{
		SortedStorage2 s = new SortedStorageImpl2(true);
		s.add("1"); // this will be deleted if the check in compare to is not well written 
		SortedStorage2 toInsert[] = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1], s };
		SortedStorage2 toDelete[] = { new SortedStorageImpl2(true) , theSortedStorages[0], theSortedStorages[1], null};
		SortedStorage2 toFind[]   = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1] };

		for (int index = 0; index < toInsert.length; index ++ )	{
			System.out.println("- add(" + toInsert[index] + "): "  + aSortedSortedStorageStorage.add(toInsert[index]));
		}

		System.out.println("- includesNull: "  + aSortedSortedStorageStorage.includesNull());
		System.out.println("- toIntegers: "  + aSortedSortedStorageStorage.toString());

		for (int index = 0; index < toFind.length; index ++ )	{
			System.out.println("- find(" + toFind[index] + "): "  + aSortedSortedStorageStorage.find(toFind[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedSortedStorageStorage.delete(toDelete[index]));
		}
		for (int index = 0; index < toDelete.length; index ++ )	{
			System.out.println("- delete(" + toDelete[index] + "): "  + aSortedSortedStorageStorage.delete(toDelete[index]));
		}
		System.out.println(aSortedSortedStorageStorage.toString());
	}
	public void test()	{
		SortedStorage2 aSortedStringStorage = new SortedStorageImpl2(true);
		testItWithString(aSortedStringStorage);
//		System.out.println("====================");
//		aSortedStringStorage.setAllowDuplication(false);
//		System.out.println(aSortedStringStorage);

		SortedStorage2 aSortedIntegerStorage = new SortedStorageImpl2(true);
		testItWithIntegers(aSortedIntegerStorage);

		SortedStorage2 aSortedSortedStorageStorage = new SortedStorageImpl2(true);
		SortedStorage2[] theSortedStorages = { aSortedStringStorage, aSortedIntegerStorage, aSortedSortedStorageStorage };
		testItWithSortedStorage(aSortedSortedStorageStorage, theSortedStorages);
	}
	public static void main(String args[] )	{
		new Test2().test();	
	}
}


package h7;
import h7.SortedStorage;
import h7.SortedStorageImpl;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class Test {
	public static void testItWithString(SortedStorage<String> aSortedStorage)	{

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
	public static void testItWithIntegers(SortedStorage<Integer> aSortedStorage)	{

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
	public static void testItWithMusicStorageOfThePast(SortedStorage<MusicStorageOfThePast> aSortedStorage)	{

		MusicStorageOfThePast album1 = new MusicStorageOfThePast(1967, "The Beatles", "Sgt. Pepper's Lonely Hearts Club Band", 39.52f, 13);
        MusicStorageOfThePast album2 = new MusicStorageOfThePast(1971, "Pink Floyd", "Meddle", 46.42f, 6);
        MusicStorageOfThePast album3 = new MusicStorageOfThePast(1982, "Michael Jackson", "Thriller", 42.19f, 9);
        MusicStorageOfThePast album4 = new MusicStorageOfThePast(1991, "Nirvana", "Nevermind", 49.23f, 12);
        MusicStorageOfThePast album5 = new MusicStorageOfThePast(2000, "Coldplay", "Parachutes", 41.45f, 10);

        MusicStorageOfThePast toInsert[] = { album1, null, album2, album2,album3, album4, album5, album4, album1, null};
        MusicStorageOfThePast toDelete[] = { album2, null, album2};
        MusicStorageOfThePast toFind[]   = { album3, null, album4, album2};

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
	public static void testItWithOldFashionedEmailAddress(SortedStorage<OldFashionedEmailAddress> aSortedStorage)	{

		OldFashionedEmailAddress address1 = new OldFashionedEmailAddress(123, "Main St", "Springfield", "IL", 62701);
        OldFashionedEmailAddress address2 = new OldFashionedEmailAddress(456, "Oak Rd", "Shelbyville", "IL", 62565);
        OldFashionedEmailAddress address3 = new OldFashionedEmailAddress(789, "Pine Ln", "Capital City", "IL", 62777);
        OldFashionedEmailAddress address4 = new OldFashionedEmailAddress(101, "Birch Dr", "Ogdenville", "IL", 62520);
        OldFashionedEmailAddress address5 = new OldFashionedEmailAddress(112, "Cedar Ave", "North Haverbrook", "IL", 62545);

        OldFashionedEmailAddress toInsert[] = {  address1, address2, address3, address4, address5, address1, null, null};
        OldFashionedEmailAddress toDelete[] = { address1,  null,  address1 };
        OldFashionedEmailAddress toFind[]   = {  address2, null,  address4,  address1};


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
		SortedStorage<MusicStorageOfThePast> aSortedStringStorage = new SortedStorageImpl<>(false);
		testItWithMusicStorageOfThePast(aSortedStringStorage);

		SortedStorage<OldFashionedEmailAddress> aSortedIntegerStorage = new SortedStorageImpl<>(false);
		testItWithOldFashionedEmailAddress(aSortedIntegerStorage);

		SortedStorage aSortedSortedStorageStorage = new SortedStorageImpl<>(false);
		SortedStorage[] theSortedStorages = { aSortedStringStorage, aSortedIntegerStorage, aSortedSortedStorageStorage };
		testItWithSortedStorage(aSortedSortedStorageStorage, theSortedStorages);
	}
	public static void main(String args[] )	{
		new Test().test();	
	}
}


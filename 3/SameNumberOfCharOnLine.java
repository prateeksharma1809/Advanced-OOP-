
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */
public class SameNumberOfCharOnLine {

	static int soManyWordToPLayWith = 0;
	/***
	 * main method to call the logic to sort string after removing whitespace and
	 * converting them to lowercase
	 */
	public static void main(String args[]) {
		Scanner sc = null;
		try {
			countNoOfElementsInFile();
			String[][] theWordArray = new String[soManyWordToPLayWith+1][2];
			sc = populateArrayWithFileElements(theWordArray);
			sort(theWordArray);
			printArray(theWordArray, soManyWordToPLayWith);
		} catch (FileNotFoundException e) {
			System.out.println("File input_hw_3.txt is not found at location ./src/main/resources!");
		} finally {
			if (null != sc)
				sc.close();
		}
	}
    
	/***
	 * 
	 * @param theWordArray
	 * @return
	 * @throws FileNotFoundException
	 */
	private static Scanner populateArrayWithFileElements(String[][] theWordArray) throws FileNotFoundException {
		Scanner sc;
		sc = new Scanner(new File("./src/main/resources/input_hw_3.txt"));
		int counter = 0;
		while (sc.hasNext()) {
			theWordArray[counter][0] = sc.nextLine();
			theWordArray[counter][1] = convertToLowerCaseAndRemoveWhiteSpaces(
					theWordArray[counter][0]);
			counter++;
		}
		return sc;
	}
	/***
	 * 
	 * @throws FileNotFoundException
	 */
	private static void countNoOfElementsInFile() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("./src/main/resources/input_hw_3.txt"));
		while (sc.hasNext()) {
			sc.nextLine();
			soManyWordToPLayWith++;
		}
		sc.close();
	}

	/***
	 * method to convert the input string to lowercase and also remove any white
	 * spaces in-between
	 * 
	 * @param str - input string that need to be transformed
	 * @return String with applied transformation
	 */
	private static String convertToLowerCaseAndRemoveWhiteSpaces(String str) {
		String msg = toLowerCaseWithoutWhiteSpaces(str);
		char[] outputArray = convertStringToCharArray(msg);
		sort(outputArray);
		return new String(outputArray);
	}

	/***
	 * method to print an array till a given length
	 * 
	 * @param arrayOfStrings - array to be printed
	 * @param length         - length till it needs to be printed
	 */
	private static void printArray(String[][] arrayOfStrings, int length) {
		for (int i = 0; i < length; i++) {
			// uncomment below to see the array view
//			System.out.println("[ "+arrayOfStrings[i][0]+" , "+arrayOfStrings[i][1]+" ]");
			System.out.println(arrayOfStrings[i][0]);
		}
	}

	/***
	 * sort method to sort the Array of strings based on the sorted words in it and
	 * also sorts the 2nd array with it based on the first array
	 */
	private static void sort(String[][] theWords) {
		for (int i = 0; i < soManyWordToPLayWith; i++) {
			for (int j = 0; j < soManyWordToPLayWith; j++) {
				if (0 > theWords[i][1].compareTo(theWords[j][1])) {
					String[] temp = theWords[i];
					theWords[i] = theWords[j];
					theWords[j] = temp;
				}
			}
		}
	}

	/***
	 * this method converts the input string to a char array
	 * 
	 * @param msg - input string to be converted into array
	 * @return a character array of the input string
	 */
	private static char[] convertStringToCharArray(String msg) {
		return msg.toCharArray();
	}

	/***
	 * method to convert the provided string to lowercase and remove any whitespace
	 * 
	 * @param str input string for transformation
	 * @return transformed string
	 */
	private static String toLowerCaseWithoutWhiteSpaces(String str) {
		return str.replaceAll("\\s*", "").toLowerCase();
	}

	/***
	 * method to sort the provided char array
	 * 
	 * @param outputArray the array to be sorted
	 */
	private static void sort(char[] outputArray) {
		for (int i = 0; i < outputArray.length; i++) {
			for (int j = 0; j < outputArray.length; j++) {
				if (0 > Character.compare(outputArray[i], outputArray[j])) {
					char temp = outputArray[i];
					outputArray[i] = outputArray[j];
					outputArray[j] = temp;
				}
			}
		}
	}

}

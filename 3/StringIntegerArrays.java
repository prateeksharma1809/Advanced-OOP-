/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */
public class StringIntegerArrays {
	static boolean firstTime = true;

	static String resturnsAstring(String arg) {
		String rValue;

		if (firstTime)
			rValue = "";
		else
			rValue = arg;
		firstTime = false;
		return rValue;
	}

	public static void main(String args[]) {
		String a, b;
		String aString = null;
		String bString = null;
		String cString = null;
		String dString = null;
		String eString = null;
		String fString = null;
		String gString = null;
		String hString = null;
		String iString = null;

		if (args.length == 1) {
			aString = "Ab" + "ba"; // memory location 1, value Abba, defined in string pool
			bString = "Abba"; // memory location 1, value Abba, defined in string pool
			cString = "A"; // memory location 2, value A, defined in string pool
			dString = cString + "b" + "b" + aString.substring(aString.length() - 1); // mem 3, value Abba, defined in non-pool
			fString = "Pink FLoyd"; // mem 4, value Pink FLoyd , defined in string pool
			gString = "Abba" + resturnsAstring(""); // mem 5, value Abba, defined in non pool
			hString = "Ab" + resturnsAstring("ba"); // mem 6, value Abba, defined in non pool
		} else {
			aString = "Ot" + "to"; // mem 1,Otto,  defined in string pool
			bString = "Otto"; // mem 1, Otto,defined in string pool
			cString = "O"; // mem 2, O,defined in string pool 
			dString = cString + "t" + "t" + aString.substring(aString.length() - 1); // mem 3,Otto, defined in non pool
			fString = "Led ZeppeLin"; // mem 4,Led ZeppeLin, defined in string pool
			gString = "Otto" + resturnsAstring(""); // mem 5,Otto, defined in non pool
			hString = "Ot" + resturnsAstring("to"); // mem 6, Otto,defined in non pool
		}
		int counter = 0;
		String array[] = { "I", "II", "III", "IV", "V", "VI" };
		// code for I-VI
		System.out.println(args.length == 1? "For args.length==1":"For args.length!=1");
		System.out.println("" + array[counter] + ".	" + (aString.equals(bString)));// 1. true, 2. true
		System.out.println("" + array[++counter] + ".	" + (aString == bString));//1. true 2. true
		System.out.println("" + array[++counter] + ".	" + (cString.equals(dString))); //1. false, 2.false
		System.out.println("" + array[++counter] + ".	" + (cString == dString)); //1. false 2. false
		System.out.println("" + array[++counter] + ".	" + (gString.equals(hString)));//1.true 2. true
		System.out.println("" + array[++counter] + ".	" + (gString==hString));//1. false 2. false
		/***
		 * For test case 
		 * args.length==1
			I.	true
			II.	true
			III.false
			IV.	false
			V.	true
			VI.	false
		 */
			
		/***
		 * For test case 
		 * args.length!=1
			I.	true
			II.	true
			III.false
			IV.	false
			V.	true
			VI.	false
		 */

	// code for VII
		System.out.println(new String(sort(replace(toWithoutWhiteSpaces(fString), "L", "l").toCharArray())));

	}

	/***
	 * to replace x character in string with y character
	 * 
	 * @param msg - input string on which replace to be done
	 * @param x   - char to be replaced
	 * @param y   - with the char to be placed insted
	 * @return - updated string
	 */
	private static String replace(String msg, String x, String y) {
		return msg.replace(x, y);
	}

	/***
	 * method to remove whiteSpaces
	 * 
	 * @param str - on which transformation needs to be applied
	 * @return updated string
	 */
	private static String toWithoutWhiteSpaces(String str) {
		str.strip();
		String[] local = str.split("\\s");
		String msg = "";
		for (String string : local) {
			msg += string;
		}
		return msg;
	}

	/***
	 * method to sort the provided char array
	 * 
	 * @param outputArray the array to be sorted
	 */
	private static char[] sort(char[] outputArray) {
		for (int i = 0; i < outputArray.length; i++) {
			for (int j = 0; j < outputArray.length; j++) {
				if (0 > Character.compare(outputArray[i], outputArray[j])) {
					char temp = outputArray[i];
					outputArray[i] = outputArray[j];
					outputArray[j] = temp;
				}
			}
		}
		return outputArray;
	}

}

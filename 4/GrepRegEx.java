package h4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GrepRegEx {

	//delimiter value if any will be populated in this 
	public static String delimiter = null;

	/**
	 * method check against the pattern 
	 * all vowels in order and non vowels can be between two vowels 
	 * @param input -> string to be checked against the pattern 
	 * @return -> true if matches else false
	 */
	private boolean vowelsCheck(String input) {
		String pattern1 = "[a]?([^aeiou])?[e]?([^aeiou])?[i]?([^aeiou])?[o]?([^aeiou])?[u]?";
		return Pattern.matches(pattern1, input);
	}
	/**
	 * method check against the pattern 
	 * 4 or 5 letter palindrom
	 * @param input -> string to be checked against the pattern 
	 * @return -> true if matches else false
	 */
	private boolean fourOrFiveCharPalindromeCheck(String input) {
		String char4Palindrome = "^(.)(.)\\2\\1$";
		String char5Palindrome = "^(.)(.).\\2\\1$";
		return Pattern.matches(char4Palindrome, input) || Pattern.matches(char5Palindrome, input);
	}

	/**
	 * method check against the pattern 
	 * 2 different date formats DD/MM/YY or MM/DD/YY
	 * @param input -> string to be checked against the pattern 
	 * @return -> true if matches else false
	 */
	private boolean dateValidation(String input) {
		// need to add validation for month and days
		String dateMMDDYY = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{2}$";
		String dateDDMMYY = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{2}$";
		return Pattern.matches(dateDDMMYY, input) || Pattern.matches(dateMMDDYY, input);

	}

	/**
	 * method check against the pattern 
	 * check for palindrom bw 1 to 30 char long
	 * @param input -> string to be checked against the pattern 
	 * @return -> true if matches else false
	 */
	private boolean oneToThirtyCharPalindrome(String input) {
		if (input.length() == 1)
			return true;
		String pattern1 = createPalindromeString(input);
		return Pattern.matches(pattern1, input);
	}

	/**
	 * method to generate pattern string for palindrom check 
	 * @param input -> string to be checked against the pattern 
	 * @return -> pattern string
	 */
	private String createPalindromeString(String input) {
		String pattern1 = "^";
		for (int i = 0; i < input.length() / 2; i++) {
			pattern1 += "(.)";
		}
		if (input.length() % 2 != 0) {
			pattern1 += ".";
		}
		for (int i = input.length() / 2; i > 0; i--) {
			pattern1 += "\\" + i;
		}
		pattern1 += "$";
		return pattern1;
	}

	/**
	 * method check against the pattern 
	 * [n-k]|(nl) where n,k,l are single digits 
	 * @param input -> string to be checked against the pattern 
	 * @return -> true if matches else false
	 */
	private boolean randomPattern(String input) {
		String pattern1 = "\\[(\\d)-\\d\\]\\|\\(\\1\\d\\)";
		return Pattern.matches(pattern1, input);
	}

	/**
	 * main method to call the logic method and to take input from file 
	 * @param args-> with input from command line regarding delimiter and filename
	 */
	public static void main(String[] args) {
		String[] input = takeInputFromUserOrFile(args);
		GrepRegEx g = new GrepRegEx();
		for (String string : input) {
			if (string != null) {
				if (delimiter != null) {
					for (String str : string.split(delimiter)) {
						g.checkForAllPatterns(str);
					}
				} else {
					g.checkForAllPatterns(string);
				}
			}
		}

	}
	
	/***
	 * method that calls all the pattern methods to check the given string against all patterns and print if found
	 * @param str -> string to be checked against the patterns 
	 */
	private void checkForAllPatterns(String str) {
		boolean isMAtched = false;
		if (this.vowelsCheck(str)) {
			System.out.println("Line: [a]?([^aeiou])?[e]?([^aeiou])?[i]?([^aeiou])?[o]?([^aeiou])?[u]? " + str);
			isMAtched = true;
		}
		if (this.fourOrFiveCharPalindromeCheck(str)) {
			System.out.println("Line:  ^(.)(.)\\2\\1$ or ^(.)(.).\\2\\1$ " + str);
			isMAtched = true;
		}
		if (this.dateValidation(str)) {
			System.out.println("Line: " + "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{2}$ or "
					+ "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{2}$ " + str);
			isMAtched = true;
		}
		if (this.oneToThirtyCharPalindrome(str)) {
			System.out.println("Line: " + createPalindromeString(str) + " " + str);
			isMAtched = true;
		}
		if (this.randomPattern(str)) {
			System.out.println("Line: " + "\\[(\\d)-\\d\\]\\|\\(\\1\\d\\) " + str);
			isMAtched = true;
		}
		if (!isMAtched) {
			System.out.println("String '" + str + "' is not matched with any pattern!");
		}

	}
	/**
	 * method that reads the input file and if the file name is not given takes input from user 
	 * @param args -> arguments that has been passed from command line
	 * @return -> string[] obtained from reading the file or user input 
	 */
	private static String[] takeInputFromUserOrFile(String[] args) {
		if (args.length < 1) {
			System.out.println("Please enter a word to check ");
			System.out.print("> ");
			try (Scanner sc = new Scanner(System.in)) {
				if (sc.hasNextLine()) {
					return new String[] { sc.nextLine() };
				}
			}
		} else {
			String fileName = "";
			for (int index = 0; index < args.length; index++) {
				if (args[index].equals("-d") && index < args.length - 1) {
					delimiter = args[index + 1];
				} else if (args[index].equals("-f") && index < args.length - 1) {
					fileName = args[index + 1];
				}
//				System.out.println(args[index]);
			}
			if (!fileName.isEmpty()) {
				int length = countNoOfWordsInFile(fileName);
				return readWordsFromFile(fileName, length);
			}
		}
		return new String[] {};
	}

	/**
	 * Reads all words from the filename and makes the words available
	 * Utility.getRandomlySelectedWord(). This method will terminate the program if
	 * the file does not exist.
	 * 
	 * @param fileName is the name of the file containing words of length 5. Each
	 *                 word is on a line.
	 * @return String array with all the words
	 */
	public static String[] readWordsFromFile(String fileName, int noOfElements) {
		String[] theWords = new String[noOfElements + 1];
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
			int counter = 0;
			while ((theWords[counter++] = input.readLine()) != null)
				;
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return theWords;
	}

	/**
	 * method used to count the number of lines in the file
	 * @param fileName -> file from which we need to read
	 * @return number of lines in the file
	 */
	private static int countNoOfWordsInFile(String fileName) {
		int soManyWordToPLayWith = 0;
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
			while (input.readLine() != null)
				soManyWordToPLayWith++;
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return soManyWordToPLayWith;
	}

}

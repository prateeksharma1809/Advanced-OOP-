package h4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Grep {
	
	public static void main(String[] args) {
		String[] userInputs = takeInputFromUserOrFile(args);
		Grep grep = new Grep();
		for (String string : userInputs) {
			grep.checkAgainstEachPattern(string.toCharArray());
		}
	}
	
	/**
	 * check against all the patterns input by the user in the form of command line or text file.
	 */
	private void checkAgainstEachPattern(char[] charArray) {
		if(ab(charArray)) {
			System.out.println("Line:^ab$ " + new String(charArray) );
		}
		if(aPlusb(charArray)) {
			System.out.println("Line:.a+b. " + new String(charArray));
		}
		if(dotABdot(charArray)) {
			System.out.println("Line:.ab. " + new String(charArray));
		}
		if(aORbANDc(charArray)) {
			System.out.println("Line:^[ab]c$ " + new String(charArray));
		}
		if(aORbANDcORc(charArray)) {
			System.out.println("Line:^[ab]?c$ " + new String(charArray));
		}
		if(OneOrMoreaORbAtStartORcAtEnd(charArray)) {
			System.out.println("Line:^[ab]?|c?$ " + new String(charArray));
		}
		if(palindrome(charArray)) {
			System.out.println("Line:^..\\2\\1b$ " + new String(charArray));
		}
	}

	/**
	 * Check for the Pattern --> ^ab$
	 * @return true if the input matches the pattern
	 * @return false if the input does not match the pattern
	 */
	public boolean ab(char[] input) {
		int state=0;
		for(int index =0; (state!=-1 && index<input.length);index++) {
			if(index==0 && state==0 && input[index]=='a') {
				state=1;
			}else if(index==1 && state==1 && input[index]=='b') {
				state=2;
			}else {
				state=-1;
			}
		}
		if(state==2) {
			return true;
		}
		return false;
	}

	/**
	 * Check for the Pattern --> .a+b.
	 * @return true if the input matches the pattern
	 * @return false if the input does not match the pattern
	 */
	public boolean aPlusb(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && input[index]!='\0') {
				state=1;
			}else if(state==1 && input[index]=='a') {
				state=2;
			}else if(state==2 && input[index]=='a') {
				state=2;
			}else if(state==2 && input[index]=='b') {
				state=3;
			}else if(state==3 && input[index]!='\0') {
				state=4;
			}else {
				state=-1;
			}
		}
		if(state==4) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check for the Pattern --> .ab.
	 * @return true if the input matches the pattern
	 * @return false if the input does not match the pattern
	 */
	public boolean dotABdot(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && input[index]!='\0') {
				state=1;
			}else if(state==1 && input[index]=='a') {
				state=2;
			}else if(state==2 && input[index]=='b') {
				state=3;
			}else if(state==3 && input[index]!='\0') {
				state=4;
			}else {
				state=-1;
			}
		}
		if(state==4) {
			return true;
		}
		return false;
	}
	
	
	 /**
	 * Check for the Pattern --> ^[ab]c$
	 * @return true if the input matches the pattern
	 * @return false if the input does not match the pattern
	 */
	public boolean aORbANDc(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && (input[index] == 'a'|| input[index]=='b')) {
				state=1;
			}else if(state==1 && input[index] == 'c') {
				state=2;
			}else if(state==0 && input[index]=='c') {
				state=2;
			}else {
				state=-1;
			}
		}
		if(state==2) {
			return true;
		}
		return false;
	}
	
	 /**
	 * Check for the Pattern --> ^[ab]?c$
	 * @return true if the input matches the pattern
	 * @return false if the input does not match the pattern
	 * Assumption : starts with either a or b and ends with c
	 */
	public boolean aORbANDcORc(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && (input[index] == 'a'|| input[index]=='b')) {
				state=1;
			}else if(state==1 && input[index] == 'c') {
				state=2;
			}else if(state==0 && input[index]=='c') {
				state=2;
			}else {
				state=-1;
			}
		}
		if(state==2) {
			return true;
		}
		return false;
	}


	/**
	 * Check for the Pattern --> ^[ab]?|c?$
	 * @return true if the input matches the pattern
	 * @return false if the input does not match the pattern
	 */
	public boolean OneOrMoreaORbAtStartORcAtEnd(char[] input) {
		int state =0;
		if(input.length > 0) {
		//for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && (input[0] == 'a'|| input[0]=='b')) {
				state=2;
			}else if(input[input.length-1] == 'c') {
				state=2;
			}
		//}
		}
		if(state==2) {
			return true;
		}
		return false;
	}
		

	 /**
	 * Check for the Pattern --> ^..\2\1$
	 * @return true if the input matches the pattern
	 * @return false if the input does not match the pattern
	 */
	public boolean palindrome(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(input[index]==input[input.length-index-1]) {
				state+=1;
			}else {
				state=-1;
			}
		}
		if(state==4) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Takes input from the user in the form of command line argument
	 *      or text file.
	 * 
	 * @return String array with the words or the file name.
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
			String fileName = args[0];
			
			if (!fileName.isEmpty()) {
				int length = countNumberOfWordsinFile(fileName);
				return readPatternsFromFile(fileName, length);
			}
		}
		return new String[] {};
	}

	/**
	 * Reads all patterns from the filename and makes the patterns available
	 * 
	 * @param fileName is the name of the file containing words. Each
	 *                 word is on a line
	 * @param soManyWordsToCheckPattern is the total number of words in the file
	 * @return String array with all the words
	 */
	private static String[] readPatternsFromFile(String fileName, int soManyWordsToCheckPattern  ) {
		String[] thePatterns = new String[soManyWordsToCheckPattern+1];
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
			int counter = 0;
			while ((thePatterns[counter++] = input.readLine()) != null);
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return thePatterns;
		
	}
	
	/**
	 * method used to count the number of lines in the file
	 * 
	 * @param fileName is the  file from which we need to read
	 */
	private static int countNumberOfWordsinFile(String fileName) {
		int soManyWordsToCheckPattern = 0;
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
			while (input.readLine() != null)
				soManyWordsToCheckPattern++;
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return soManyWordsToCheckPattern;
	}
		
}
	



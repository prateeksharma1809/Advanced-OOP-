package com.example.demoart.hp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author ps2798, ks7581
 *
 */
public class Wordle {
	static int soManyWordToPLayWith = 0;
	static final String[] theWords = new String[10231];
	static final Scanner readGuess = new Scanner(System.in);

	
	/**
	 *  Reads all words from the filename and makes the words available Utility.getRandomlySelectedWord().
     *  This method will terminate the program if the file does not exist.
	 * @param fileName is the name of the file containing words of length 5. Each word is on a line.
	 */
	public static void readWordsFromFile(String fileName) {
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
//			String word;
			int counter = 0;
			while ((theWords[counter++] = input.readLine()) != null)
				soManyWordToPLayWith++;
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Reads a word of length 5 and returns this word. The method will continue until a word of length 5 is provided.
	 * @return The word read from the terminal.
	 */
	public static String readUserInput() { //
		String guess = "";
		do {
			System.out.print("> ");
			if (readGuess.hasNext()) {
				guess = readGuess.nextLine();
			}
		} while (guess.length() != 5);
		return guess;
	}

	/**
	 * Return a randomly selected word read with Utility.readWordsFromFile().
	 * @return The randomly selected word.
	 */
	public static String getWord() {
		return theWords[new Random().nextInt(soManyWordToPLayWith)];
	}

	/**
	 * Starts the game by printing initial messages about the output and calls for the main logic
	 */
	public static void playWordle() {
		System.out.println("_ indicates the letter is in the word but in the wrong spot.");
		System.out.println("* indicates the letter is in the word and correct spot.");
		System.out.println("x indicates the letter is not in the word.");
		System.out.println("Try to guess the word in 5 tries.");
		guessWordLogic();

	}
	
	/**
	 *  main logic to be performed by making the call to mark words and handing the correct and incorrect outputs 
	 */
	private static void guessWordLogic() {
		String userInput = "";
		String word = "";
		int trys = 0;
		boolean doYouWantToReplay =true;
		boolean fetchNewWordFlag=true;
		do {
			userInput = readUserInput(); // reads user input, the input has to be 5 characters long
			trys++;
			if(theWordsIsNotEmpty() && fetchNewWordFlag) {
				word = getWord();
				fetchNewWordFlag=false;
			}
			String wordString = "";
			System.out.println(wordString = checkAndMarkPosistions(userInput, word)); // checks the current input with word
			if (wordString.equals("*****")) {
				System.out.println("well done.\r\n" + "\r\n" + "Let’s play again ...");
				System.out.println("Do you want to play again?");
				doYouWantToReplay = getYesOrNo();
				removeTheGuessedWordFromArray(word);
				fetchNewWordFlag=true;
				trys=0;
			}
			if(trys>=5) { //max no or try's
				System.out.println("Out of trys.\r\n" + "Better luck next time!\r\n" + "Do you want to play again?");
				doYouWantToReplay = getYesOrNo();
				removeTheGuessedWordFromArray(word);
				fetchNewWordFlag=true;
				trys=0;
			}
		} while (theWordsIsNotEmpty() && doYouWantToReplay );
		if(!theWordsIsNotEmpty() && doYouWantToReplay) {
			System.out.println("Sorry! No words left to guess");
		}
	}

	/**
	 * 	it takes user input for continuing with the game, Y will return true and N will return false
	 *  @return true or false based on user input
	 */
	private static boolean getYesOrNo() {
		String ans = "";
		do {
			System.out.print("only (Y/N)> ");
			if (readGuess.hasNext()) {
				ans = readGuess.nextLine();
			}
		} while (ans.length() != 1);
		return "Y".equalsIgnoreCase(ans);
	}

	/**
	 * 	handles the array that is created by reading the file,
	 *  then removing the word that is passed from the array and rearranging 
	 *  @param word that needs to be removed from the array 
	 */
	private static void removeTheGuessedWordFromArray(String word) {
		int index = -1;
		for (int i = 0; i < theWords.length; i++) {
			if(word==theWords[i]) {
				index = i;
			}
		}
		for (int i = index; i < theWords.length-1; i++) {
			theWords[i]=theWords[i+1];
		}
		soManyWordToPLayWith-=1;
	}

	/**
	 * 	checks if the word list is empty
	 *  @return true if not empty and false if empty
	 */
	private static boolean theWordsIsNotEmpty() {
		return soManyWordToPLayWith>0;
	}

	/**
	 * 	checks and mark the positions with *,x and _
	 *  @return true if not empty and false if empty
	 */
	private static String checkAndMarkPosistions(String userInput, String word) {
		char[] wordArray = new char[word.length()];
		char[] outputArray = new char[word.length()];
		String finalWord = "";
		for (int i = 0; i < word.length(); i++) {
			if (0 == Character.compare(userInput.charAt(i), word.charAt(i))) {
				wordArray[i] = 't';
				outputArray[i] = '*';
			}
		}
		for (int i = 0; i < outputArray.length; i++) {
			if (outputArray[i] != '*') {
				boolean found = false;
				for (int j = 0; j < wordArray.length; j++) {
					if (wordArray[j] != 't' && !found) {
						if (0 == Character.compare(userInput.charAt(i), word.charAt(j))) {
							outputArray[i] = '_';
							wordArray[j] = 't';
							found = true;
						}
					}
				}
				if (!found) {
					outputArray[i] = 'x';
				}
			}
		}
		for (int i = 0; i < outputArray.length; i++) {
			finalWord += outputArray[i];
		}
		return finalWord;

	}

	/**
	 * 	main method to call the logic function
	 *  @param args array, if any additional input is passed while running the program
	 */
	public static void main(String args[]) {
		readWordsFromFile("5_char_word.txt"); // reads the text file - this file has to be in the local directory
		playWordle();
	}
}

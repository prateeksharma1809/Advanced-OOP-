package h5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */
public class Dictionary {

	static int soManyWordToPLayWith = 0;
	static String[] words;

	/**
	 * method to initialize the dictionary class with the words from the file
	 * @param fileName -> the name of the file to reed from 
	 */
	public static void initializeDisctionary(String fileName) {
		countNoOfWordsInFile(fileName);
		readWordsFromFile(fileName);
	}
	
	/**
	 * checks if the word list is empty
	 * @return true if not empty and false if empty
	 */
	public static boolean theWordsIsNotEmpty() {
		return soManyWordToPLayWith > 0;
	}
	
	/**
	 * method used to count the number of lines in the file
	 * @param fileName -> file from which we need to read
	 */
	public static void countNoOfWordsInFile(String fileName) {
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
			while (input.readLine() != null)
				soManyWordToPLayWith++;
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * Reads all words from the filename and makes the words available
	 * Utility.getRandomlySelectedWord(). This method will terminate the program if
	 * the file does not exist.
	 * 
	 * @param fileName is the name of the file containing words
	 */
	public static void readWordsFromFile(String fileName) {
		words = new String[soManyWordToPLayWith + 1];
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
			int counter = 0;
			while ((words[counter++] = input.readLine()) != null)
				;
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return a randomly selected word read with Utility.readWordsFromFile().
	 * @return The randomly selected word.
	 */
	public static String chooseRandomWordFromInputFile() {
		return words[new Random().nextInt(soManyWordToPLayWith)];
	}
	
	/**
	 * handles the array that is created by reading the file, then removing the word
	 * that is passed from the array and rearranging
	 * 
	 * @param word   that needs to be removed from the array
	 */
	public static void removeTheGuessedWordFromArray( String word) {
		int indexOfWordToRemove = -1;
		for (int index = 0; index < words.length; index++) {
			if (word == words[index]) {
				indexOfWordToRemove = index;
			}
		}
		for (int index = indexOfWordToRemove; index < words.length - 1; index++) {
			words[index] = words[index + 1];
		}
		soManyWordToPLayWith -= 1;
	}
	
	/**
	 * logic to get a input from user and checks weather it is present in the word to be guessed, if yes then it increments the 
	 * guessed counter else it increments the wrong attempts counter 
	 * @param game -> object of the game instance that is currently been played
	 * @param guessed -> word that is guessed by the user
	 */
	public static void checkEnteredWordAgainstCurrentWord(Game game, String guessed) {
		if (guessed.length() == 1) {
			if (game.getCurrentWord().contains(guessed)) {
				char [] wordsSoFarGuessed = game.getWordsSoFarGuessed();
				for (int index = 0; index < wordsSoFarGuessed.length; index++) {
					if (Character.compare(wordsSoFarGuessed[index], '.') == 0
							&& 0 == Character.compare(game.getCurrentWord().charAt(index), guessed.charAt(0))) {
						wordsSoFarGuessed[index] = guessed.charAt(0);
						game.incrementCorrectGuesses();
					} else if (Character.compare(wordsSoFarGuessed[index], '.') != 0
							&& 0 == Character.compare(game.getCurrentWord().charAt(index), guessed.charAt(0))) {
						System.out.println("Already guessed");
					}
				}
			} else {
				game.incrementWrongGuesses();
			}
		}
	}


}

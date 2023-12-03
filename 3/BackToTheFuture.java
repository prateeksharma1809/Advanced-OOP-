
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */
public class BackToTheFuture {
	
	static String man[] = {   "                ###"
							, "                ###"
							, "                 #"
							, "               #####"
							, "              # ### #"
							, "                ###"
							, "               #  #"
							, "              ##   ##"
							, "########################"
							, "##                    ##"
							, "##                    ##"};
	
	static int soManyWordToPLayWith = 0;
//	static final String[] theWords = new String[10231];
	static final Scanner readGuess = new Scanner(System.in);
	
	/***
	 * main method which calls the game till the words are exausted or the user sets a no flag 
	 *
	 */
	
	public static void main(String args[]) {
		countNoOfWordsInFile(args[0]);
		String[] theWords = readWordsFromFile(args[0]); //read from file words
		boolean isContinue = true;
		do {
			String word = chooseRandomWordFromInputFile(theWords);
			int wrongGusses = 0;
			char wordsSoFarGuessed[] = new char[word.length()];
			for (int i = 0; i < wordsSoFarGuessed.length; i++) {
				wordsSoFarGuessed[i]='.';
			}
			int guessedCount=0;
			checkGuessRightOrWrong(word, wrongGusses, wordsSoFarGuessed, guessedCount);
			System.out.println("The word was: "+word);
			removeTheGuessedWordFromArray(theWords, word);
			if(theWordsIsNotEmpty()) {
				isContinue = isYesOrNo();
			}else {
				System.out.println("Sorry no more words to play with!");
			}
			
		} while (isContinue && theWordsIsNotEmpty());

	}
	
	/**
	 * 	checks if the word list is empty
	 *  @return true if not empty and false if empty
	 */
	private static boolean theWordsIsNotEmpty() {
		return soManyWordToPLayWith>0;
	}


	/***
	 * logic to get a input from user and checks weather it is present in the word to be guessed, if yes then it increments the 
	 * guessed counter else it increments the wrong attempts counter 
	 * @param word - word to be guessed
	 * @param wrongGusses - no of wrong guesses so far
	 * @param wordsSoFarGuessed - char array of no of words guessed so far
	 * @param guessedCount - no of correct guesses
	 */
	private static void checkGuessRightOrWrong(String word, int wrongGusses, char[] wordsSoFarGuessed, int guessedCount) {
		do {
			printDisapperingMan(wrongGusses);
			System.out.println("# wrong guesses: " + wrongGusses + " -- Word to guess: " + new String(wordsSoFarGuessed));
			if (wrongGusses < 9) {
				System.out.print(">");
				if (readGuess.hasNext()) {
					String guessed = readGuess.nextLine();
					if (guessed.length() == 1) {
						if (word.contains(guessed)) {
							for (int i = 0; i < wordsSoFarGuessed.length; i++) {
								if (Character.compare(wordsSoFarGuessed[i], '.') == 0
										&& 0 == Character.compare(word.charAt(i), guessed.charAt(0))) {
									wordsSoFarGuessed[i] = guessed.charAt(0);
									guessedCount++;
								}else if(Character.compare(wordsSoFarGuessed[i], '.') != 0
										&& 0 == Character.compare(word.charAt(i), guessed.charAt(0))) {
									System.out.println("Already guessed");
								}
							}
						}else {
							wrongGusses++;
						}
					} 
				}
			} else {
				wrongGusses++;
			}
		} while (wrongGusses <=9 && guessedCount<wordsSoFarGuessed.length && theWordsIsNotEmpty());
	}

	/***
	 * prints the disappering man based on the number of wrong guesses that has been done so far 
	 * @param wrongGusses
	 */
	private static void printDisapperingMan(int wrongGusses) {
		int reamingMan = (int) (man.length * wrongGusses / 9);
		for (int i = 0; i < man.length; i++) {
			if (reamingMan > i) {
				System.out.println();
			} else {
				System.out.println(man[i]);
			}
		}
	}
	
	
	/**
	 * 	handles the array that is created by reading the file,
	 *  then removing the word that is passed from the array and rearranging 
	 * @param theWords -> array from which we need to remove 
	 *  @param word that needs to be removed from the array 
	 */
	private static void removeTheGuessedWordFromArray(String[] theWords, String word) {
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
	 * 	it takes user input for continuing with the game, yes will return true and no will return false
	 *  @return true or false based on user input
	 */
	private static boolean isYesOrNo() {
		String ans = "";
		boolean flag =true;
		while (flag) {
			System.out.println("Do you want to continue (yes/no)?");
			if (readGuess.hasNext()) {
				ans = readGuess.nextLine();
			}
			System.out.println(ans);
			if("yes".equals(ans) || "no".equals(ans)) {
				flag=false;
			}
		}
		return "yes".equals(ans);
	}

	/**
	 * Return a randomly selected word read with Utility.readWordsFromFile().
	 * @param theWords -> input array from which it need to choose a word at random
	 * @return The randomly selected word.
	 */
	private static String chooseRandomWordFromInputFile(String[] theWords) {
		return theWords[new Random().nextInt(soManyWordToPLayWith)];
	}
	
	
	
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
	 *  Reads all words from the filename and makes the words available Utility.getRandomlySelectedWord().
     *  This method will terminate the program if the file does not exist.
	 * @param fileName is the name of the file containing words of length 5. Each word is on a line.
	 * @return String array with all the words
	 */
	public static String[] readWordsFromFile(String fileName) {
		String[] theWords = new String[soManyWordToPLayWith+1];
		try (BufferedReader input = new BufferedReader(new FileReader(fileName));) {
			int counter = 0;
			while ((theWords[counter++] = input.readLine()) != null);
		} catch (Exception e) {
			System.out.println("ExceptionType occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return theWords;
	}
	
}

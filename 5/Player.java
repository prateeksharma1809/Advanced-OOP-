package h5;

import java.util.Scanner;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */
public class Player {
	
	static final Scanner readGuess = new Scanner(System.in);
	
	//main method to run the program hangman
	public static void main(String[] args) {
		Dictionary.initializeDisctionary(args[0]);
		boolean isContinue = true;
		while(isContinue) {
			Game newGame = new Game();
			isContinue = newGame.startGame();
			System.out.println("The word was: " + newGame.getCurrentWord());
			Dictionary.removeTheGuessedWordFromArray(newGame.getCurrentWord());
			isContinue = isYesOrNo();
		}
	}

	/**
	 * it takes user input for continuing with the game, yes will return true and no
	 * will return false
	 * 
	 * @return true or false based on user input
	 */
	private static boolean isYesOrNo() {
		String ans = "";
		boolean flag = true;
		while (flag) {
			System.out.println("Do you want to continue (yes/no)?");
			if (readGuess.hasNext()) {
				ans = readGuess.nextLine();
			}
			System.out.println(ans);
			if ("yes".equals(ans) || "no".equals(ans)) {
				flag = false;
			}
		}
		return "yes".equals(ans);
	}



}

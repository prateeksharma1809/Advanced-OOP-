package h5;

/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */
public class Game {

	private int wrongGusses = 0;

	private char wordsSoFarGuessed[];

	private String currentWord = "";

	private int correctGuessedCount = 0;
	
	private Picture picture;

	//getter for picture instance variable
	public Picture getPicture() {
		return picture;
	}
	//setter method for picture variable 
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	//getter method for current word 
	public String getCurrentWord() {
		return this.currentWord;
	}
	// getter method for correct number of guesses
	public int getCorrectGuessedCount() {
		return correctGuessedCount;
	}
	//method to increment the count by one 
	public void incrementCorrectGuesses() {
		this.correctGuessedCount++;
	}
	//setter method for words guessed 
	public void setWordsSoFarGuessed(char[] wordsSoFarGuessed) {
		this.wordsSoFarGuessed = wordsSoFarGuessed;
	}
	//getter method for wrong guesses
	public int getWrongGusses() {
		return wrongGusses;
	}
	//increment the variable wrong guess by 1
	public void incrementWrongGuesses() {
		this.wrongGusses++;
	}
	// getter method for words guessed so far
	public char[] getWordsSoFarGuessed() {
		return wordsSoFarGuessed;
	}
	/**
	 * method which starts the game 
	 * @return boolean value if the game is finished 
	 */
	public boolean startGame() {
		if (!Dictionary.theWordsIsNotEmpty()) {
			System.out.println("Sorry no more words to play with!");
		} else {
			this.chooseRandomWordAndInitializeInstanceVariables();
			while (wrongGusses <= 9 && correctGuessedCount < wordsSoFarGuessed.length) {
				picture.printDisapperingMan(wrongGusses);
				System.out.println(
						"# wrong guesses: " + wrongGusses + " -- Word to guess: " + new String(wordsSoFarGuessed));
				if(wrongGusses!=9) {
					System.out.print(">");
					if (Player.readGuess.hasNext()) {
						String guessed = Player.readGuess.nextLine();
						Dictionary.checkEnteredWordAgainstCurrentWord(this, guessed);
					}
				}else {
					incrementWrongGuesses();
				}
				
			}

		}
		return false;

	}
	
	/**
	 * method to initialize and calls Dictionary.chooseRandomWordFromInputFile() to choose a random word
	 */
	private void chooseRandomWordAndInitializeInstanceVariables() {
		this.currentWord = Dictionary.chooseRandomWordFromInputFile();
		this.initializeWordGuessedSoFarArray(this.currentWord);
		this.setPicture(new Picture());
	}

	/**
	 * method to initialize the char array 
	 * @param currentWord -> word that is randomly choosen
	 */
	private void initializeWordGuessedSoFarArray(String currentWord) {
		this.wordsSoFarGuessed = new char[currentWord.length()];
		for (int index = 0; index < this.wordsSoFarGuessed.length; index++) {
			this.wordsSoFarGuessed[index] = '.';
		}
	}

}

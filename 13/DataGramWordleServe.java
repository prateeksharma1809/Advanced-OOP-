import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class DataGramWordleServe {
	static int soManyWordToPLayWith = 0;
	static String[] words;
	static int port;
	static String hostname;
	DatagramSocket datagramSocket;
	int counter = 0;
	String[] strArray;// make dynamic
	int[] tryCounter;

	public DataGramWordleServe() throws IOException {
		datagramSocket = new DatagramSocket(port);
		strArray=new String[soManyWordToPLayWith];
		tryCounter = new int[soManyWordToPLayWith];
	}

	public static void main(String[] args) throws IOException {
		String fileName = parsArgv(args);
		initializeDisctionary(fileName);
		DataGramWordleServe ws = new DataGramWordleServe();
		ws.startServer();

	}
	
	public boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}

	private void startServer() throws IOException {
		System.out.println("Wordle datagram Server is running...");
		while(soManyWordToPLayWith>0) {
			DatagramPacket receivePacket = receiveMsg(datagramSocket);
			String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
			System.out.println("Client : " + data);
			String[] array = data.strip().split(";");
			String toSend = "";
			if(array[0].equals("init")) {
				toSend = init();
			}else if(isInteger(array[0])) {
				int index = Integer.parseInt(array[0]);
				toSend = checkAndMarkPosistions(array[1], index);
				if(toSend.equals("*****")) {
					toSend = "guessed";
				}
			}else {
				toSend="default";
			}
			sendMsg(toSend, datagramSocket, receivePacket);
		}
		
	}
	
	/**
	 * checks and mark the positions with *,x and _
	 * 
	 * @return true if not empty and false if empty
	 */
	private String checkAndMarkPosistions(String userInput, int index) {
		String currentWord = strArray[index];
		System.out.println(userInput);
		if (userInput.length() != 5 || "No".equals(userInput) || userInput.length()!=currentWord.length()) {
			if("No".equals(userInput)) {
				return userInput;
			}else {
				return "default";
			}
		}
		tryCounter[index]+=1;
		char[] wordArray = new char[currentWord.length()];
		char[] outputArray = new char[currentWord.length()];
		String finalWord = "";
		markSame(userInput, currentWord, wordArray, outputArray);
		markChars(userInput, currentWord, wordArray, outputArray);
		for (int i = 0; i < outputArray.length; i++) {
			finalWord += outputArray[i];
		}
		if(tryCounter[index]>=5) {
			return finalWord+";outOfTrys";
		}
		return finalWord;

	}

	private void markSame(String userInput, String currentWord, char[] wordArray, char[] outputArray) {
		for (int i = 0; i < currentWord.length(); i++) {
			if (0 == Character.compare(userInput.charAt(i), currentWord.charAt(i))) {
				wordArray[i] = 't';
				outputArray[i] = '*';
			}
		}
	}

	private void markChars(String userInput, String currentWord, char[] wordArray, char[] outputArray) {
		for (int i = 0; i < outputArray.length; i++) {
			if (outputArray[i] != '*') {
				boolean found = false;
				for (int j = 0; j < wordArray.length; j++) {
					if (wordArray[j] != 't' && !found) {
						if (0 == Character.compare(userInput.charAt(i), currentWord.charAt(j))) {
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
	}
	
	private String init() {
		String currentWord = chooseRandomWordFromInputFile();
		removeTheGuessedWordFromArray(currentWord);
		strArray[counter] = currentWord;
		char[] wordsGuessedSoFar = new char[currentWord.length()];
		for (int i = 0; i < wordsGuessedSoFar.length; i++) {
			wordsGuessedSoFar[i] = '_';
		}
		String g = new String(wordsGuessedSoFar);
		System.err.println("sending counter " + counter + ";" +  g +" :: current word is :"+currentWord);
		String return_value = counter + ";" + g;
		tryCounter[counter]=0;
		counter++;
		return return_value;
		
	}

	/**
	 * method to receive message
	 * @param datagramSocket - socket for UDP
	 * @return - packet with details 
	 * @throws IOException
	 */
	private DatagramPacket receiveMsg(DatagramSocket datagramSocket) throws IOException {
		byte[] receiveBuffer = new byte[1024];
//		System.out.println("Waiting to read from server");
		DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
		datagramSocket.receive(receivePacket);
		return receivePacket;
	}
	
	/**
	 * method that is used to send message 
	 * @param toSend - string to be send
	 * @param datagramSocket - socket on which needs to be send
	 * @param datagramPacket - packet for the receivers info 
	 * @throws IOException
	 */
	public static void sendMsg(String toSend, DatagramSocket datagramSocket, DatagramPacket datagramPacket)
			throws IOException {
		DatagramPacket sendPacket = new DatagramPacket(toSend.getBytes(), toSend.getBytes().length,
				datagramPacket.getAddress(), datagramPacket.getPort());
		datagramSocket.send(sendPacket);
	}


	// method to parse arguments from command line
	public static String parsArgv(String[] args) {
		if (args.length < 6) {
			throw new RuntimeException("Usage java WordleServer -port {int} -file {filepath} -hostname {hostname}");
		}
		String file = "";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-port")) {
				port = Integer.parseInt(args[++i]);
			} else if (args[i].equals("-file")) {
				file = args[++i];
			} else if (args[i].equals("-hostname")) {
				hostname = args[++i];
			}
		}
		return file;
	}

	/**
	 * method to initialize the dictionary class with the words from the file
	 * 
	 * @param fileName -> the name of the file to reed from
	 */
	public static void initializeDisctionary(String fileName) {
		countNoOfWordsInFile(fileName);
		readWordsFromFile(fileName);
	}

	/**
	 * method used to count the number of lines in the file
	 * 
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
	 * 
	 * @return The randomly selected word.
	 */
	public synchronized static String chooseRandomWordFromInputFile() {
		return words[new Random().nextInt(soManyWordToPLayWith)];
	}

	/**
	 * handles the array that is created by reading the file, then removing the word
	 * that is passed from the array and rearranging
	 * 
	 * @param word that needs to be removed from the array
	 */
	public synchronized static void removeTheGuessedWordFromArray(String word) {
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
}

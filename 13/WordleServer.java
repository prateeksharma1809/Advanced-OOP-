import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * 
 * @author ps2798, ks7581
 *
 */
public class WordleServer {
	static int soManyWordToPLayWith = 0;
	static String[] words;
	static int port;
	static String hostname;
	ServerSocket serverSocket;

	public WordleServer() throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public static void main(String[] args) throws IOException {
		String fileName = parsArgv(args);
		initializeDisctionary(fileName);
		WordleServer ws = new WordleServer();
		ws.startServer();

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

	private void startServer() throws IOException {
		while (soManyWordToPLayWith > 0) {
			Socket socket = null;

			System.out.println("Wordle Server is running...");
			// Socket object to receive incoming client requests
			socket = serverSocket.accept();
			System.out.println("A new client is connected : " + socket);
			// Obtaining input and output streams
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			System.out.println("Assigning new thread for this client");
			// Create a new thread object
			String currentWord = chooseRandomWordFromInputFile();
			removeTheGuessedWordFromArray(currentWord);
			Thread t = new ClientHandler(socket, dis, dos, currentWord);
			// Invoking the start() method
			t.start();
		}
		System.out.println("Sorry no more words to play with!");
		System.out.println("Shutting down server");
		this.serverSocket.close();

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

}

//class to handle one client 
class ClientHandler extends Thread {
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket socket;
	final String currentWord;

	// Constructor
	public ClientHandler(Socket s, DataInputStream dis2, DataOutputStream dos2, String currentWord) {
		this.socket = s;
		this.dis = dis2;
		this.dos = dos2;
		this.currentWord = currentWord;
	}

	/**
	 * method to handle the inputs and outputs via client
	 */
	@Override
	public void run() {
		int trys = 0;
		String received = "";
		System.out.println("current Word : "+currentWord);
		do {
			try {
				received = dis.readUTF();
				String value = checkRecievedMegAndProcessAccordingly(received);
				if (value.equals("*****")) {
					System.out.println("well done.\r\n" + "\r\n" + "Let’s play again ...");
//					System.out.println("Do you want to play again?");
					value = "guessed";
					received="No";
				} else if (value.equals("No")) {
					received = value;
				} else if (value.equals("default")) {
					System.out.println("Wrong input : " + received);
					value = "wrongInput";
				} else {
					trys++;
					System.out.println("value : " + value);
					if (trys >= 5) { // max no or try's
						System.out
								.println("Out of trys.\r\n" + "Better luck next time!\r\n" + "Do you want to play again?");
						value = value+";outOfTrys";
						received="No";
					}
				}
				dos.writeUTF(value);
			} catch (IOException e) {
				received="No";
				e.printStackTrace();
			}
		} while (!received.equals("No"));

		try {
			// Closing resources
			socket.close();
			this.dis.close();
			this.dos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String checkRecievedMegAndProcessAccordingly(String received) {
		String[] array = received.strip().split(";");
		switch (array[0]) {
		case "check":
			return checkAndMarkPosistions(array[1]);
		default:
			return "default";
		}

	}

	/**
	 * checks and mark the positions with *,x and _
	 * 
	 * @return true if not empty and false if empty
	 */
	private String checkAndMarkPosistions(String userInput) {
		System.out.println(userInput);
		if (userInput.length() != 5 || "No".equals(userInput) || userInput.length()!=currentWord.length()) {
			if("No".equals(userInput)) {
				return userInput;
			}else {
				return "default";
			}
		}
		char[] wordArray = new char[this.currentWord.length()];
		char[] outputArray = new char[this.currentWord.length()];
		String finalWord = "";
		for (int i = 0; i < this.currentWord.length(); i++) {
			if (0 == Character.compare(userInput.charAt(i), this.currentWord.charAt(i))) {
				wordArray[i] = 't';
				outputArray[i] = '*';
			}
		}
		for (int i = 0; i < outputArray.length; i++) {
			if (outputArray[i] != '*') {
				boolean found = false;
				for (int j = 0; j < wordArray.length; j++) {
					if (wordArray[j] != 't' && !found) {
						if (0 == Character.compare(userInput.charAt(i), this.currentWord.charAt(j))) {
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
}

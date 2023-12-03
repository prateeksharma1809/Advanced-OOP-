import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class WordleClient0 {

	static Scanner scn = new Scanner(System.in);
	static String hostname = "localhost";
	static int port = 8000;

	public static void main(String[] args) {
		parsArgv(args);
		boolean isContinue = true;
		while (isContinue) {
			playGame();
			isContinue = isYesOrNo();
		}
	}

	// method to parse arguments from command line
	private static void parsArgv(String[] args) {
		if (args.length < 1) {
			throw new RuntimeException("Usage java WordleServer -port {int} [-hostname {hostname}]");
		}
		for (int i = 0; i < args.length; i++) {

			if (args[i].equals("-port")) {
				port = Integer.parseInt(args[++i]);
			} else if (args[i].equals("-hostname")) {
				hostname = args[++i];
			}
		}
	}

	/**
	 * staring the game
	 */
	private static void playGame() {
		Socket socket;
		try {
			socket = new Socket(hostname, port);
			// Obtaining input and output streams
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			// The following loop performs the exchange of information between client and
			// client handler
			playWordle();
			callServerAndCheck(dis, dos);
			// Closing resources
			socket.close();
			dis.close();
			dos.close();
		} catch (IOException e) {
			System.out.println("Sorry No more Words to play with or some exception occured");
			e.printStackTrace();
		}
	}
	
	private static void callServerAndCheck(DataInputStream dis, DataOutputStream dos) throws IOException {
		String toSend = "";
		while(!toSend.equals("No")) {
			toSend = scn.nextLine();
			dos.writeUTF("check;"+toSend);
			String word = dis.readUTF();
			if(word.contains("outOfTrys")) {
				System.out.println(word.split(";")[0]);
				System.out.println("Out of trys.\r\n" + "Better luck next time!");
				toSend="No";
			}else if(word.equals("guessed")) {
				System.out.println("well done.\r\n" + "\r\n" + "Lets play again ...");
				toSend="No";
			}else if(word.equals("wrongInput")) {
				System.out.println("Wrong input!");
				toSend="";
			}else {
				System.out.println(word);
			}
			
		}
		
	}

	/**
	 * Starts the game by printing initial messages about the output and calls for the main logic
	 */
	public static void playWordle() {
		System.out.println("_ indicates the letter is in the word but in the wrong spot.");
		System.out.println("* indicates the letter is in the word and correct spot.");
		System.out.println("x indicates the letter is not in the word.");
		System.out.println("Try to guess the word in 5 tries.");

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
			if (scn.hasNext()) {
				ans = scn.nextLine();
			}
			System.out.println(ans);
			if ("yes".equals(ans) || "no".equals(ans)) {
				flag = false;
			}
		}
		return "yes".equals(ans);
	}

}

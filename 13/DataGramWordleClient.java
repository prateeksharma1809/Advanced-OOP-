import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DataGramWordleClient {

	static Scanner scn = new Scanner(System.in);
	static String hostname = "localhost";
	static int port = 8000;

	public static void main(String[] args) throws IOException {
		parsArgv(args);
		boolean isContinue = true;
		while (isContinue) {
			playWordle();
			playGame();
			isContinue = isYesOrNo();
		}
	}

	private static void playGame() throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket();
		String toSend = "init";
//		System.err.println("sending :" + toSend);
		// send ready signal
		sendMsg(toSend, datagramSocket);
		// reciever number
		String data = receiveMsg(datagramSocket);
		String number = data.strip().split(";")[0];
//		System.err.println("reciving :" + number);
		while (!toSend.equals("No")) {

			toSend = scn.nextLine();

			sendMsg(number + ";" + toSend, datagramSocket);
			String rec = receiveMsg(datagramSocket);
			if (rec.equals("guessed")) {
				System.out.println("well done.\r\n" + "\r\n" + "Lets play again ...");
				toSend = "No";
			} else if (rec.contains("outOfTrys")) {
				System.out.println(rec.split(";")[0]);
				System.out.println("Out of trys.\r\n" + "Better luck next time!");
				toSend = "No";
			} else if (rec.equals("default")) {
				System.out.println("Wrong input!");
			}else {
				System.out.println(rec);
			}
		}

	}

	/**
	 * method to receive message
	 * 
	 * @param datagramSocket - socket for UDP
	 * @return - recieved message
	 * @throws IOException
	 */
	private static String receiveMsg(DatagramSocket datagramSocket) throws IOException {
		byte[] receiveBuffer = new byte[1024];
//		System.out.println("Waiting to read from server");
		DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
		datagramSocket.receive(receivePacket);
		return new String(receivePacket.getData(), 0, receivePacket.getLength());
	}

	/**
	 * method that is used to send message
	 * 
	 * @param toSend         - string to be send
	 * @param datagramSocket - socket on which needs to be send
	 * @param serverAddress  - address of the server
	 * @throws IOException
	 */
	private static void sendMsg(String toSend, DatagramSocket datagramSocket) throws IOException {
		InetAddress serverAddress = InetAddress.getByName(hostname);
		byte[] sendBuffer = toSend.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, port);
		datagramSocket.send(sendPacket);
	}

	/**
	 * Starts the game by printing initial messages about the output and calls for
	 * the main logic
	 */
	public static void playWordle() {
		System.out.println("_ indicates the letter is in the word but in the wrong spot.");
		System.out.println("* indicates the letter is in the word and correct spot.");
		System.out.println("x indicates the letter is not in the word.");
		System.out.println("Try to guess the word in 5 tries.");

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

package h9;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 *
 *         C:\Users\prate\eclipse-workspace\hw\src>java h9.Wc ../if.txt
 */
public class Wc {

	String option = "";
	String fileName = "";
//	byte[] buffer = new byte[1024];

	public static void main(String[] args) {
		Wc w = new Wc();
		w.startProcess(args);
	}

	/**
	 * 
	 * @param args-> parameters from the command line and then getting all the info
	 *               line word count byte and lines
	 */
	public void startProcess(String[] args) {
		this.checkAndProcessInput(args);
		try {
			long bytes = this.countBytes();
			int lines = this.countLines();
			int chars = this.countChars();
			this.displayLogic(bytes, lines, chars);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * method to display based on the input given in the command line
	 * 
	 * @throws a runtime exception if the flag value is not valid
	 * @param bytes -> byte count of the file
	 * @param lines -> line count of the file
	 * @param words -> word count of the file
	 */
	public void displayLogic(long bytes, int lines, int words) {
		if (option.isEmpty()) {
			System.out.println(lines + "  " + words + "  " + bytes + "  " + fileName);
		} else if ("-l".equals(option)) {
			System.out.println(words + "  " + bytes + "  " + fileName);
		} else if ("-m".equals(option)) {
			System.out.println(lines + "  " + bytes + "  " + fileName);
		} else if ("-c".equals(option)) {
			System.out.println(lines + "  " + words + "  " + fileName);
		} else {
			throw new RuntimeException("Invalid flag. Supported flags: -l, -c, -w, -b");
		}
	}

	/**
	 * method to fetch the inputs from the command line
	 * 
	 * @throws runtime exception if the program is not run with proper arguments
	 * 
	 */
	public void checkAndProcessInput(String[] args) {
		if (args.length > 2 || args.length < 1) {
			throw new RuntimeException("Usage : java Wc [flag] [filename] or java Wc [filename]");
		} else if (args.length == 2) {
			option = args[0];
			fileName = args[1];
		} else {
			fileName = args[0];
		}
	}

	/**
	 * method to count the lines in the file
	 * 
	 * @return line count
	 * @throws IOException
	 */
	public int countLines() throws IOException {
		int lines = 0;
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
			while (bufferReader.readLine() != null) {
				lines++;
			}

		} catch (IOException e) {
			throw e;
		}
		return lines;

	}

	/**
	 * method to count words in the file
	 * 
	 * @return line count
	 * @throws IOException
	 */
	public int countChars() throws IOException {
		int words = 0;
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
			while (bufferReader.read() != -1) {
				words++;
			}
		} catch (IOException e) {
			throw e;
		}
		return words;
	}

	/**
	 * method to count bytes in the file
	 * 
	 * @return byte count
	 * @throws IOException
	 */
	public long countBytes() throws IOException {
		try (BufferedInputStream bufferInputStream = new BufferedInputStream(new FileInputStream(fileName))) {
			return bufferInputStream.readAllBytes().length;
		} catch (IOException e) {
			throw e;
		}
	}
//	public int countChars(BufferedReader reader) throws IOException {
//	int chars = 0;
//	try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
//		while (reader.read() != -1) {
//			chars++;
//		}
//	}catch(IOException e){
//		throw e;
//	}
//	
//	return chars;
//}

}

package h9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 *
 *javac -cp ./src ./src/h9/MyScramble.java
 java h9.DiskDump if=../if.txt of=- bs=1 skip=2
 */
public class DiskDump {

	String inputFile = null;
	String outputFile = null;
	int blockSize = 512; // Default block size
	int skipBlocks = 0;
	long totalBytesRead = 0;
	long totalBytesWritten = 0;

	public static void main(String[] args) {
		long start1 = System.currentTimeMillis();
		DiskDump dd = new DiskDump();
		dd.populateInstanceVariables(args);
		dd.startProcess();
		
		dd.displayLogic(start1);

	}
	/**
	 * method to initialize the input and output streams and then call the copy logic 
	 */
	private void startProcess() {
		try (InputStream in = ("-".equals(inputFile)) ? System.in : new DataInputStream(new FileInputStream(inputFile));
				OutputStream out = ("-".equals(outputFile)) ? System.out
						: new DataOutputStream(new FileOutputStream(outputFile))) {
			sipAndDoCopy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method reponisble for the skip and copy logic 
	 * @param in - input stram 
	 * @param out -> output stream 
	 * @throws IOException
	 */
	private void sipAndDoCopy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[blockSize];
		long bytesToSkip = (long) blockSize * skipBlocks;
		in.skipNBytes(bytesToSkip);
		int n;
		while ((n = in.read(buffer)) != -1) {
			totalBytesRead+=n;
			out.write(buffer, 0, n);
			totalBytesWritten+=n;
		}
	}

	/**
	 * method to display the data
	 * @param start1 
	 */
	private void displayLogic(long start1) {
		System.out.println(totalBytesRead / blockSize + "+0 records in");
		System.out.println(totalBytesWritten / blockSize + "+0 records out");
		double diff = (System.currentTimeMillis()-start1)/Math.pow(10, 3);
		System.out.println(totalBytesRead + " bytes transferred in "+diff+" secs ("+(totalBytesRead/diff)+" bytes/sec)");
	}

	/**
	 * populates the instance variables based on the cmd line argumants 
	 * @param args- command line arguments
	 */
	public void populateInstanceVariables(String[] args) {
		for (String arg : args) {
			if (arg.startsWith("if=")) {
				inputFile = arg.substring(3);
			} else if (arg.startsWith("of=")) {
				outputFile = arg.substring(3);
			} else if (arg.startsWith("bs=")) {
				blockSize = Integer.parseInt(arg.substring(3));
			} else if (arg.startsWith("skip=")) {
				skipBlocks = Integer.parseInt(arg.substring(5));
			}
		}
		System.out.println("input : "+inputFile+", output: "+outputFile+", bs: "+blockSize+", skip: "+skipBlocks);
	}

}

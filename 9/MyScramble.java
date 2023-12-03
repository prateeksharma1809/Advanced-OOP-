package h9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */

/**
 * Custom class for storing chars in an dynamic array like ds
 */
class DynamicCharArray{
	char[] dataList = new char[1];
	int length = 0;
	
	/**
	 * to add data to the ds
	 * @param data, value to be added 
	 */
	public void add(char data) {
		if(length<=dataList.length-1) {
			dataList[length]=data;
			length++;
		}else {
			createNewListAndCopyDataWithNewData(data);
		}
	}

	/**
	 * if the array is full method create a new array and adds the data to that array
	 * @param data-> value to be added
	 */
	private void createNewListAndCopyDataWithNewData(char data) {
		char[] tempList = new char[dataList.length*2];
		for (int index = 0; index < length; index++) {
			tempList[index]=dataList[index];
		}
		tempList[length]=data;
		length++;
		dataList=tempList;
	}

}

/**
 * Class to scramble and de-scramble the text
 */
public class MyScramble {
	boolean isScramble=false;
	String inputFile ="";
	String outputFile="";
	DynamicCharArray dc = new DynamicCharArray();
	
	//main method
	public static void main(String[] args) {
		MyScramble ms = new MyScramble();
		ms.processArgs(args);
		ms.callScramble();
	}

	/**
	 * method that reads the file and then call scramble or descramble based on the command line input
	 */
	public void callScramble() {
		try (InputStream in =  new DataInputStream(new FileInputStream(inputFile));
				OutputStream out = new DataOutputStream(new FileOutputStream(outputFile))
						){
			int c;
	        while ((c = in.read()) != -1) {
	        	dc.add((char)c);
	        }
//	        System.out.println(dc.length);
	        if(this.isScramble) {
	        	scramble(out);
	        }else {
	        	deScramble(out);
	        }
		} catch (FileNotFoundException e) {
			System.err.println("File not found Occured: "+e.getMessage());
		} catch (IOException e) {
			System.err.println("IO Exception Occured: "+e.getMessage());
		}
	}

	/**
	 * de-scrambles the file data based on the scramble logic 
	 * @param out 
	 * @param out -> output stream to which the text needs to be written 
	 * @throws IOException
	 */
	public void deScramble(OutputStream out) throws IOException {
		for(int index=0; index<dc.length-1;index+=2) {
			char temp = dc.dataList[index];
			dc.dataList[index]=dc.dataList[index+1];
			dc.dataList[index+1]=temp;
		}
		for(int index=0;index<dc.length-4;index+=4) {
			char temp = dc.dataList[index];
			char temp1 = dc.dataList[index+1];
			dc.dataList[index]=dc.dataList[index+2];
			dc.dataList[index+1]=dc.dataList[index+3];
			dc.dataList[index+3]=temp1;
			dc.dataList[index+2]=temp;
		}
		for(int index=0; index<dc.length;index++) {
//			System.out.print(dc.dataList[index]);
			out.write(dc.dataList[index]);
		}
	}

	
	/**
	 * scrambles the file data based on the scramble logic 
	 * @param out 
	 * @param out -> output stream to which the text needs to be written 
	 * @throws IOException
	 */

	public void scramble(OutputStream out) throws IOException {
		for(int index=0; index<dc.length-1;index+=2) {
			char temp = dc.dataList[index];
			dc.dataList[index]=dc.dataList[index+1];
			dc.dataList[index+1]=temp;
		}
		for(int index=0;index<dc.length-4;index+=4) {
			char temp = dc.dataList[index];
			char temp1 = dc.dataList[index+1];
			dc.dataList[index]=dc.dataList[index+2];
			dc.dataList[index+1]=dc.dataList[index+3];
			dc.dataList[index+3]=temp1;
			dc.dataList[index+2]=temp;
		}
		for(int index=0; index<dc.length;index++) {
//			System.out.print(dc.dataList[index]);
			out.write(dc.dataList[index]);
			
		}
		
	}

	/**
	 * method to read and initialize the instance variable from command line args 
	 * @param args-> command line arguments
	 */
	public void processArgs(String[] args) {
//		for (String string : args) {
//			System.out.println(string);
//		}
		if(args.length==4 && (args[0].equals("-scramble") || args[0].equals("-descramble")) && args[2].equals("to")) {
			isScramble=args[0].equals("-scramble");
			inputFile = args[1];
			outputFile=args[3];
		}else {
			throw new RuntimeException("Usage MyScramble [-scramble|-descramble] [input file] to [output file]");
		}
	}

}

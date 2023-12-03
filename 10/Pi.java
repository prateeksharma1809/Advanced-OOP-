
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 
 * @author prateek sharma, kirti sharma
 *
 */
class Runner extends Thread {
	int xOrig;
	int yOrig;
	int color;
	Pi pi;

	/**
	 * constructor to initialize the runner class
	 * 
	 * @param x              -> start x coordicate of the square
	 * @param y              -> start y coordicate of the square
	 * @param color          -> color of the square
	 * @param lengthOfSquare -> length of the square
	 * @param theImage       -> the buffer image object
	 */
	public Runner(int x, int y, int color, Pi pi) {
		this.xOrig = x;
		this.yOrig = y;
		this.color = color;
		this.pi = pi;
	}

	/**
	 * run method will be called when Runner.start is invoked
	 */
	public void run() {
		for (int x = 0; x < pi.getLENGTH_OF_SQUARE(); x++)
			for (int y = 0; y < pi.getLENGTH_OF_SQUARE(); y++)
				pi.getTheImage().setRGB(xOrig + x, yOrig + y, color);
	}
}

public class Pi extends JFrame {

	private static final long serialVersionUID = -4563249860418304248L;

	private final int LENGTH_OF_SQUARE = 100;
	private final int LENGTH = 5;
	private final int LENGTH_OF_WINDOW = LENGTH * LENGTH_OF_SQUARE;
	private BufferedImage theImage;
	static long startTime;
	static long endTime;

	// getter for the image object
	public BufferedImage getTheImage() {
		return theImage;
	}

	// getter for the length of square object
	public int getLENGTH_OF_SQUARE() {
		return LENGTH_OF_SQUARE;
	}

	final int red = Color.RED.getRGB();
	final int blue = Color.BLUE.getRGB();
	Reader input;

	// constructor
	public Pi() {
		super("Pi");

		setBounds(100, 100, LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			input = new InputStreamReader(System.in);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * method to save the image to a file
	 * 
	 * @param theImage - image object
	 */
	public void saveImage(BufferedImage theImage) {
		try {
			String suffix = "png";
			File outputfile = new File("output." + suffix);
			ImageIO.write(theImage, suffix, outputfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method to start creating the image
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void createImage() throws IOException, InterruptedException {
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		readInputAndPopulateImage();
		repaint();
		saveImage(theImage);
	}

	/**
	 * method to read from the input and then start the painting by calling the
	 * helper method startPainting this also waits for all the threads to complete
	 * before returning
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void readInputAndPopulateImage() throws IOException, InterruptedException {
		try {
			Thread[] threads = new Thread[(getHeight() * getWidth()) / (LENGTH_OF_SQUARE * LENGTH_OF_SQUARE)];
			startPainting(input, threads);
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * method to initialize the thread and start them
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void startPainting(Reader input2, Thread[] threads) throws IOException {
		int counter = 0;
		for (int y = 0; y < getHeight(); y += LENGTH_OF_SQUARE) {
			for (int x = 0; x < getWidth(); x += LENGTH_OF_SQUARE) {
				int digit = input2.read();
				if (digit == -1) {
					throw new RuntimeException("Not enough characters to read for building an image atleast "
							+ threads.length + " number of inputs required!");
				}
				Runner r = new Runner(x, y, (char) digit % 2 == 0 ? red : blue, this);
				r.start();
				threads[counter] = r;
				counter++;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Pi aPi = new Pi();
		if (args.length > 0) {
			aPi.input = new BufferedReader(new FileReader(args[0]));
		}
		aPi.setVisible(true);
		startTime = System.currentTimeMillis();
		aPi.createImage();
		endTime = System.currentTimeMillis();
		System.out.println("Total Execution time : " + (endTime - startTime) / Math.pow(10, 3) + " sec");
//		System.exit(0);

	}
}

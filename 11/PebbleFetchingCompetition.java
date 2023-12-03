package hw.h11;


/**
 * 
 * @author Prateek Sharma,Kirti Sharma
 *
 */
public class PebbleFetchingCompetition {
	private static Object pebble = new Object();
	private static int soManyStudentsInWait;
	private static int soManyStudents;
	private static int soManyRounds;
//	private static Student[] students;
	private static boolean lightIsBlue = false;
	private static int pebblesHeldByTeacher = 0;
	private static int[] pebblesHeldByStudents;
	private static boolean isRoundOver;
	private volatile static int soManyPebbels;
	private static int nPebbles =1;
	private volatile static boolean isRunEnded = false;
	private static boolean quiet;

	/**
	 * main method to start the game
	 * @param args cmd line arguments
	 */
	public static void main(String[] args) {
		getInputs(args);
//		students = new Student[soManyStudents];
		pebblesHeldByStudents = new int[soManyStudents];
		soManyPebbels = nPebbles;
		init();
		startGame();
		endThreads();
		soManyPebbels = nPebbles;
		if(checkResults()) {
			System.out.println("All marbles are accounted for is true.");
		}else {
			System.err.println("All marbles are not accounted for!");
		}

	}

	/**
	 * initializing the variables from the command line arguments
	 * @param args- cmd line arguments
	 */
	private static void getInputs(String[] args) {
		for (int index = 0; index < args.length; index++) {
			if(args[index].contains("nPlayers")) {
				soManyStudents=Integer.valueOf(args[index+1]);
			}else if(args[index].contains("nRounds")) {
				soManyRounds = Integer.valueOf(args[index+1]);
			}else if(args[index].contains("quiet")) {
				quiet=true;
			}
		}
	}

	/**
	 * method to start the game
	 */
	private static void startGame() {
		for (int i = 0; i < soManyRounds; i++) {
			soManyPebbels = nPebbles;// resetting pebble count
			isRoundOver = false;
			while (!isRoundOver) {
				synchronized (pebble) {
					lightIsBlue = true;
					pebble.notifyAll();
				}
			}
		}
	}

	/**
	 * method to end the game
	 */
	private static void endThreads() {
		if (isRoundOver) {
			synchronized (pebble) {
//				System.out.println("Round complete");
				isRunEnded = true;
				lightIsBlue = true;
				pebble.notifyAll();

			}
		}
	}

	/**
	 * method to check if the total no of pebbles are equal to to total no of pebbles played with 
	 * @return true if pass else false
	 */
	private static boolean checkResults() {
		int totalPebbles = pebblesHeldByTeacher;
		if(!quiet)
			System.out.println("master\thold on to so many\t"+pebblesHeldByTeacher+" marbles");
		for (int index=0; index<pebblesHeldByStudents.length;index++) {
			if(!quiet)
				System.out.println("student "+index+"\tgrabbed so many:\t"+pebblesHeldByStudents[index]+" marbles");
			totalPebbles += pebblesHeldByStudents[index];
		}
		return totalPebbles == soManyRounds*soManyPebbels;
	}

	/**
	 * method to initialize and start the threads
	 */
	private static void init() {
		soManyStudentsInWait = 0;
		new Teacher().start();
//		teacher.start();
		for (int i = 0; i < soManyStudents; i++) {
//			students[i] = new Student(i);
//			students[i].start();
			new Student(i).start();
		}
		while (soManyStudentsInWait != soManyStudents + 1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("waiting for all students and teacher to wait!" + soManyStudentsInWait);
		}
	}

	/**
	 * method to simulate grabbing behaviour 
	 * @param id id of the student or teacher that is trying to grab the pebble
	 */
	public static void grabPebble(int id) {
		synchronized (pebble) {
			if (id == -1) {
//				System.out.println("pebble grabbed by Teacher " + id);
				pebblesHeldByTeacher++;
			} else {
//				System.out.println("pebble grabbed by student " + id);
				pebblesHeldByStudents[id]++;
			}
			lightIsBlue = false;
			soManyPebbels--;
			soManyStudentsInWait--;
			if (soManyPebbels <= 0) {
//				System.out.println("setting round over to true");
				isRoundOver = true;

			}
		}

	}
	/**
	 * class to simulate students
	 *
	 */
	static class Student extends Thread {

		int studentId;

		public Student(int id) {

			this.studentId = id;
		}

		public void run() {
			while (!isRunEnded) {
				synchronized (pebble) {
//					System.out.println("incremanting count " + studentId);
					soManyStudentsInWait++;
					try {
						// Teacher waits until the light turns blue
						while (!lightIsBlue) {

							pebble.wait();
						}
						if (!isRoundOver) {
							grabPebble(studentId);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
//			System.out.println("run ended for " + studentId);
		}
	}

	/**
	 * class to simulate teacher
	 *
	 */
	static class Teacher extends Thread {

		public void run() {
			while (!isRunEnded) {
				synchronized (pebble) {
//					System.out.println("incremanting count " + -1);
					soManyStudentsInWait++;
					try {
						// Student waits until the light turns blue
						while (!lightIsBlue) {

							pebble.wait();
						}
						if (!isRoundOver) {
//						Thread.sleep(new Random().nextInt(100, 1000));
							grabPebble(-1);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
//			System.out.println("run ended for Teacher");
		}
	}

}

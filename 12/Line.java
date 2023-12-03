
/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
class FireFighter extends Thread {

	int fireFighterNo;
	Object sync;
	Object nextSync;
	int rounds;
	int soManyFireFighters;

	public FireFighter(int fireFighterNo, Object sync, Object nextSync, int rounds, int soManyFireFighters) {
		this.fireFighterNo = fireFighterNo;
		this.sync = sync;
		this.nextSync = nextSync;
		this.rounds = rounds;
		this.soManyFireFighters = soManyFireFighters;
	}
	/**
	 * method simulating the firefighters doing work
	 */
	public void run() {
		for (int i = 0; i < rounds; i++) {
			synchronized (sync) {
				if (fireFighterNo != 1 || i > 0) {
					try {
//						System.out.println("Waiting "+fireFighterNo);
						sync.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (fireFighterNo == 1) {
					System.out.println("Bucket is filled by firefighter 1");

				} else {
					System.out.println("\tbucket  is handed to firefighter " + fireFighterNo);
				}
				if (fireFighterNo == soManyFireFighters - 1) {
					System.out.println("\tand firefighter " + fireFighterNo + " empties bucket and\r\n"
							+ "\tdrops bucket and firefighter 1 catches the bucket.");
				}
//				System.out.println(info);
				synchronized (nextSync) {
//					System.out.println("got sync lock for next "+fireFighterNo);
					nextSync.notify();
				}
			}
		}

	}
}

public class Line {
	private static int nThreads;
	private static int soOften;

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-soManyFireFighters"))
				nThreads = Integer.valueOf(args[i + 1]);
			else if (args[i].equals("-soOften"))
				soOften = Integer.valueOf(args[i + 1]);
		}
		Object[] objects = new Object[nThreads - 1];
		//creating objects to sync over 
		for (int i = 0; i < nThreads - 1; i++) {
			objects[i] = new Object();
		}
		//passing each object with its sync and thenext object sync 
		for (int i = 1; i < nThreads; i++) {
			new FireFighter(i, objects[i-1], objects[(i)%(objects.length)], soOften, nThreads).start();
			
		}
	}
}

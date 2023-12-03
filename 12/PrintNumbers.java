
/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
class Runner extends Thread {

	int info;
	Object sync;
	Object nextSync;
	int rounds;

	public Runner(int info, Object sync, Object nextSync, int rounds) {
		this.info = info;
		this.sync = sync;
		this.nextSync = nextSync;
		this.rounds=rounds;
	}

	//simulating the sequence 
	public void run() {
		for (int i = 0; i < rounds; i++) {
			synchronized (sync) {
				if (info != 0 || i>0) {
					try {
						sync.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(info==0)
					System.out.println("# round = "+i+"   -------");
				System.out.println(info);
				synchronized (nextSync) {
					nextSync.notify();
				}
			}
		}

	}
}

public class PrintNumbers {

	public static void main(String[] args) {
		int nThreads=0;
		int nRounds=0;
		for (int i = 0; i < args.length; i++) {
			if(args[i].equals("-nThreads"))
				nThreads=Integer.valueOf(args[i+1]);
			else if(args[i].equals("-soOften"))
				nRounds=Integer.valueOf(args[i+1]);
		}
		Object[] objects = new Object[nThreads];
		//creating objects for each object to sync over 
		for (int i = 0; i < nThreads; i++) {
			objects[i] = new Object();
		}
		//each thread gets its sync object and the next objects sync 
		for (int i = 0; i < nThreads; i++) {
			new Runner(i, objects[i], objects[(i + 1) % nThreads], nRounds).start();

		}

	}
}

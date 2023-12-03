

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class ConsumerProducer extends Thread {

	public static void main(String args[]) {
		int soManyP = Integer.valueOf(args[1]);
		int soManyC = Integer.valueOf(args[3]);
		Storage theStorage = new Storage();

		System.out.println("# producer = " + soManyP);
		System.out.println("# consumer = " + soManyC);

		for (int id = 1; id <= soManyP; id++) {
			new Producer(id, theStorage).start();
		}
		for (int id = 1; id <= soManyC; id++) {
			new Consumer(id, theStorage).start();
		}
	}
}

class Storage {
	static final int N = 100;
	static int soManyAreIn = 0;
	int soMany = 0; // counter, used for verification
	private Object lock1 = new Object();

	void test() {
		if (soManyAreIn != 1) {
			System.out.println("soManyAreIn " + soManyAreIn);
			System.exit(0);
		}
		if (soMany > N) {
			System.out.println("overflow " + soMany);
			System.exit(0);
		}
		if (soMany < 0) {
			System.out.println("underflow " + soMany);
			System.exit(0);
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
/**
 * adding items to the so many variable
 * @param id -> id of the producer 
 * @throws InterruptedException
 */
	void addItem(int id) throws InterruptedException {

		synchronized (lock1) {
			System.err.println("--->");
			soManyAreIn++;
			if (soMany >= N) {
				System.out.println("Producer " + id + " Waiting");
				 soManyAreIn--;
				lock1.wait();
				 soManyAreIn++;
				System.out.println("Producer " + id + " woke up");
			}
			if (soMany >= N) {
				System.out.println("Producer " + id+" storage full skipping");
			}else {
				System.out.println("Producer " + id+" Producing");
				soMany++;
//				Thread.sleep(100);//simulating doing some work
			}
			test();
			lock1.notify();
			soManyAreIn--;
			System.err.println("<---"+soMany);
			if(soMany==N) {
				System.out.println("Producers Produced "+soMany+"items stopping");
				System.exit(0);
			}
		}

	}
	/**
	 * simulating the removing of an item 
	 * @param id -> id of the consumer
	 * @throws InterruptedException
	 */
	void consume(int id) throws InterruptedException {

		synchronized (lock1) {
			System.err.println("	---->");
			soManyAreIn++;
			if (soMany <= 0) {
				System.out.println("Consumer " + id + " Waiting");
				soManyAreIn--;
				lock1.wait();
				soManyAreIn++;
				System.out.println("Consumer " + id + " woke up");

			}
			if (soMany <= 0) {
				System.out.println("Consumer " + id + " storage empty skipping");
			}else {
				System.out.println("Consumer " + id + " Consuming");
				soMany--;
//				Thread.sleep(100);//simulating doing some work
			}
			test();
			lock1.notify();
			 soManyAreIn--;
			System.err.println("	<---"+soMany);
		}

	}
}

class Consumer extends Thread {
	int id;
	Storage thisStorage;

	Consumer(int id, Storage thisStorage) {
		this.id = id;
		this.thisStorage = thisStorage;
		setName("Consumer: " + id);
		System.out.println("C: " + id);
	}

	public void run() {
		while (true) {
			try {
				thisStorage.consume(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Producer extends Thread {
	int id;
	Storage thisStorage;
	boolean isRunning=true;

	Producer(int id, Storage thisStorage) {
		this.id = id;
		this.thisStorage = thisStorage;
		setName("Producer: " + id);
		System.out.println("P: " + id);
	}

	public void run() {
		while (true) {
			try {
				thisStorage.addItem(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
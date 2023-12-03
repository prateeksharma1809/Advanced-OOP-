package hw.h11;



/**
 * 
 * @author CS-RIT, Kirti Sharma, Prateek Sharma
 *
 */
public class Hw11 extends Thread {

	private String info;
	static Object o = new Object();
	public Hw11 (String info) {
		/**
		 * since the static object is modified every-time a thread is created, multiple threads can take lock on this object 
		 * in order to fix this we can make the locking object final while declaring and hence cannot be modified further in
		 * the code 
		 */
//		this.o = new Object();
		this.info    = info;
	}
	static String zero = "0";
	public void run () {
		if(!info.equals(zero)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * since the object is modified each thread will have separate instances of the lock 
		 * object and hence can enter the synchronized block with its own locks 
		 */
		synchronized ( o ) {
			/**
			 * also there is a problem in the notify and wait block as the notify is called once our 
			 * execution is completed or is about 
			 * to complete, wait called after notify will, wait for another thread to call notify but it is not called 
			 * after calling wait and hence both threads will be stuck in a deadlock, waiting for another thread to call notify 
			 */
			if(!info.equals(zero))
				o.notify();
			
			System.out.println(info);
			try {
				if(info.equals(zero))
					o.wait();
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
	}
	public static void main (String args []) {
		new Hw11("0").start();
		new Hw11("1").start();
	}
}


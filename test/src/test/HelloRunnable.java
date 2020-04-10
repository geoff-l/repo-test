package test;

public class HelloRunnable implements Runnable {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new HelloRunnable(), "Test thread 001");
		t1.start();
		System.out.printf("ID: %d, NAME: %s, Threads: %d\n", Thread.currentThread().getId(),
				Thread.currentThread().getName(),
				Thread.activeCount());
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.interrupt();
		*/
		try {
			System.out.printf("[%s]: entering join.\n", Thread.currentThread().getName());
			t1.join();
			System.out.printf("[%s]: after join. \n", Thread.currentThread().getName());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Hello from a thread!");
		System.out.printf("ID: %d, NAME: %s\n", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		try {
			System.out.printf("[%s]: waiting for 3 seconds.\n", Thread.currentThread().getName());
			Thread.sleep(3000);
			System.out.printf("[%s]: waiting over. \n", Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		Object sync = new Object();
		synchronized(sync) {
			try {
				sync.wait();
			} catch (InterruptedException e) {
				System.out.println("Interrupted occured.");
				//e.printStackTrace();
			}
		}
		*/
		
	}

}

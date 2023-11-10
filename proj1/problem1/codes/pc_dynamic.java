package proj1_1;

public class pc_dynamic {
	private static int NUM_END = 200000;
	private static int NUM_THREADS = 4;
	public static void main(String[] args) throws InterruptedException{
		if(args.length==2) {
			NUM_THREADS = Integer.parseInt(args[0]);
			NUM_END = Integer.parseInt(args[1]);
		}
		threadDynamic thread[] = new threadDynamic[NUM_THREADS];
		for (int t = 0; t < NUM_THREADS; t++) {
            thread[t] = new threadDynamic(t);
        }
		threadDynamic.workLoad = NUM_THREADS*10-10;
		long startTime = System.currentTimeMillis();
		for (int t = 0; t < NUM_THREADS; t++) {
			thread[t].start();
	    }
	    for (int t = 0; t < NUM_THREADS; t++) {
	    	thread[t].join();
	    }
		long endTime = System.currentTimeMillis();
		long timeDiff = endTime - startTime;
		System.out.println("Total thread number : " + NUM_THREADS);
		System.out.println();
		for (int t = 0; t < NUM_THREADS; t++) {
            System.out.println("Thread-" + t + "'s Execution Time : " + thread[t].timeDiff + "ms");
        }
		System.out.println("Execution Time : " + timeDiff + "ms");
        System.out.println("1..." + (NUM_END - 1) + " prime# counter=" + threadDynamic.counter + "\n");

	}
	private static boolean isPrime(int x) {
		int i;
		if(x<=1) return false;
		for (i=2; i<x; i++) {
			if(x%i == 0) return false;
		}
		return true;
	}
	static class threadDynamic extends Thread{
		static int counter;
		static int workLoad;
		int x;
		int TN;
	    int temp = 0;
	    long startTime;
	    long endTime;
	    long timeDiff;
		public threadDynamic(int n) {
			TN = n;
			x = n*10;
	    }
		public void run() {
			startTime = System.currentTimeMillis();
			while(workLoad<NUM_END) {
				if(x+10>NUM_END) {
					for(int i = x; i<NUM_END; i++)
						if (isPrime(i)) temp++;
				}else {
					for(int i = x; i<x+10; i++)
						if (isPrime(i)) temp++;
				}
				x = queuePop();
			}
	        counter += temp;
	        endTime = System.currentTimeMillis();
	        timeDiff = endTime - startTime;
		}	
		static synchronized int queuePop() {
			workLoad += 10;
            return workLoad;
        }
	}
}

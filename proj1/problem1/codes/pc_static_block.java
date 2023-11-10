package proj1_1;

public class pc_static_block {
	private static int NUM_END = 200000;
	private static int NUM_THREADS = 4;
	public static void main(String[] args) throws InterruptedException{
		if(args.length==2) {
			NUM_THREADS = Integer.parseInt(args[0]);
			NUM_END = Integer.parseInt(args[1]);
		}
		threadStatic thread[] = new threadStatic[NUM_THREADS];
		for (int t = 0; t < NUM_THREADS; t++) {
            thread[t] = new threadStatic(t);
        }
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
		System.out.println("");
		
		for (int t = 0; t < NUM_THREADS; t++) {
            System.out.println("Thread-" + t + "'s Execution Time : " + thread[t].timeDiff + "ms");
        }
		System.out.println("Execution Time : " + timeDiff + "ms");
        System.out.println("1..." + (NUM_END - 1) + " prime# counter=" + threadStatic.counter + "\n");
	}
	private static boolean isPrime(int x) {
		int i;
		if(x<=1) return false;
		for (i=2; i<x; i++) {
			if(x%i == 0) return false;
		}
		return true;
	}
	static class threadStatic extends Thread{
		static int counter; 
	    int temp = 0;
	    int threadNum;
	    int block_size;
	    long startTime;
	    long endTime;
	    long timeDiff;
		public threadStatic(int n) {
			threadNum = n;
			block_size = (NUM_END/NUM_THREADS);
	    }
		public void run() {
			startTime = System.currentTimeMillis();
			if(threadNum==NUM_THREADS-1) {
				for (int i = block_size*threadNum; i < NUM_END; i++) {
		            if (isPrime(i)) temp++;
		        }
			}else {
				for (int i = block_size*threadNum; i < block_size*(threadNum+1); i++) {
		            if (isPrime(i)) temp++;
		        }
			}
	        counter += temp;
	        endTime = System.currentTimeMillis();
	        timeDiff = endTime - startTime;
		}
	}


}
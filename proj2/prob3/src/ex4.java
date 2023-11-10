package ex4;

import java.util.concurrent.CyclicBarrier;

public class ex4 {
	
	public static void main(String[] args) {
		try {
			CyclicBarrier barrier = new CyclicBarrier(2, () -> {
				System.out.println("*********************************************************************************");
				System.out.println("			All threads passed the barrier");
	        });
			Counter[] counter = new Counter[2];
			for (int i = 0; i < 2; i++) {
				counter[i] = new Counter(barrier, 10, i);
				counter[i].start();
			}
		}catch(Exception e) {
			
		}
	}
	
	static class Counter extends Thread{
		private CyclicBarrier barrier;
		private int num;
		private int id;
		public Counter(CyclicBarrier b, int n, int i) {
			barrier = b;
			num = n;
			id = i;
		}
		public void run() {
			for(int i = 0; i<num; i++) {
				if(id==0) {
					System.out.println("Thread " +id+" - count: "+i);
				}else {
					System.out.println("						Thread " +id+" - count: "+i);
				}
				try {
					sleep((int)(Math.random()*2000));
				}catch(Exception e) {
					
				}
				
			}
			try {
				if(id==0) {
					System.out.println("Thread " +id+" - reached the barrier");
				}else {
					System.out.println("						Thread "+id+" - reached the barrier");
				}
				barrier.await();
			}catch(Exception e) {
				
			}
		}	
	}
	

}

package ex3;
import java.util.concurrent.atomic.*;

public class ex3 {

	public static void main(String[] args) {
		AtomicInteger atomic_int = new AtomicInteger(0);
		int inc_num1 = 50;
		int inc_num2 = 50;
		Thread ang = new AddAndGetInc(atomic_int, inc_num1);
		Thread gna = new GetAndAddInc(atomic_int, inc_num2);
		ang.start();
		gna.start();
		try {
			ang.join();
			gna.join();
		}catch(Exception e) {
			
		}finally {
			System.out.println("------------------");
			System.out.println("final result: "+atomic_int);
			System.out.println("------------------");
		}
	}
	
	static class AddAndGetInc extends Thread{
		private AtomicInteger atomic_int;
		private int num;
		public AddAndGetInc(AtomicInteger i, int n) {
			atomic_int = i;
			num = n;
		}
		public void run() {
			for(int i = 0; i<num; i++) {
				System.out.println("Trying to add&get: "+atomic_int);
				int x = atomic_int.addAndGet(1);
				System.out.println("add&get done: "+x);
				try {
					sleep((int)(Math.random()*2000));
				}catch(InterruptedException e) {
					
				}
				
			}
		}	
	}
	static class GetAndAddInc extends Thread{
		private AtomicInteger atomic_int;
		private int num;
		public GetAndAddInc(AtomicInteger i, int n) {
			atomic_int = i;
			num = n;
		}
		public void run() {
			for(int i = 0; i<num; i++) {
				System.out.println(" 					Trying to get&add: "+atomic_int);
				int x = atomic_int.getAndAdd(1);
				System.out.println(" 					get&add done: "+x);
				try {
					sleep((int)(Math.random()*2000));
				}catch(InterruptedException e) {
					
				}
			}
		}	
	}
	
}

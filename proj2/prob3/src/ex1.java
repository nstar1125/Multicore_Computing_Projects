package ex1;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class ex1 {

	public static void main(String[] args) {
		BlockingQueue queue = new ArrayBlockingQueue<Integer>(3);
		Thread producer = new Producer(queue);
		Thread consumer = new Consumer(queue);
		producer.start();
		consumer.start();
	}
	
	static class Producer extends Thread{
		private BlockingQueue queue;
		
		public Producer(BlockingQueue q) {
			queue = q;
		}
		public void run() {
			for(int i = 0; i<50;i++) 
			{
				try {
					System.out.println("Producer "+i+": Trying to put "+i);
					queue.put(i);
					System.out.println("Producer "+i+": Put "+i+" done. Queue size: "+queue.size());
					sleep((int)(Math.random()*1000));
				}catch(Exception e) {
					
				}
				
			}
		}
		
	}
	
	static class Consumer extends Thread{
		private BlockingQueue queue;
		
		public Consumer(BlockingQueue q) {
			queue = q;
		}
		public void run() {
			for(int i = 0; i<50;i++) 
			{
				try {
					System.out.println("Consumer "+i+": 					Trying to take");
					int x = (int)queue.take();
					System.out.println("Consumer "+i+": 					Take "+x+" done. Queue size: "+queue.size());
					sleep((int)(Math.random()*1000));
				}catch(Exception e) {
					
				}
				
			}
		}
		
	}

}

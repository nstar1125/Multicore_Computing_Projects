package ex2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedVar{
	private ReadWriteLock lock;
	private int shared_var = 0;

	public SharedVar(ReadWriteLock l) {
		lock = l;
	}

	public void read(int id) {
		try {
			lock.readLock().lock();
			System.out.println("Reader "+id+": read locked");
			System.out.println("Reader "+id+": read: "+shared_var);
		} catch (Exception e) {
			
		}finally{
			lock.readLock().unlock();
			System.out.println("Reader "+id+": read unlocked");
		}
	}

	public void write(int id) {
		try{
			lock.writeLock().lock();
			System.out.println("Writer "+id+": "+"			write locked");
			shared_var += 1;
			System.out.println("Writer "+id+": "+"			write: "+shared_var);
		}catch(Exception e) {
			
		}finally {
			lock.writeLock().unlock();
			System.out.println("Writer "+id+": "+"			write unlocked");
		}
	}
}

public class ex2 {

	public static void main(String[] args) {
		ReadWriteLock lock = new ReentrantReadWriteLock();
		SharedVar var = new SharedVar(lock);
	
		Reader[] reader = new Reader[2];
		Writer[] writer = new Writer[2];
		for(int i = 0; i<2 ; i++) {
			reader[i] = new Reader(var, i);
			writer[i] = new Writer(var, i);
			reader[i].start();
			writer[i].start();
		}
		try {
			for(int i = 0; i<2 ; i++) {
				reader[i].join();
				writer[i].join();
			}
		}catch(Exception e) {
			
		}
		

	}
	static class Reader extends Thread{
		SharedVar var;
		int id;

		public Reader(SharedVar v, int i) {
			var = v;
			id = i;
		}
		public void run(){
			for(int i = 0; i<10; i++) {
				try {
					var.read(id);
				}catch(Exception e) {
					
				}
			}
		}
	}
	static class Writer extends Thread{
		SharedVar var;
		int id;

		public Writer(SharedVar v, int i) {
			var = v;
			id = i;
		}
		public void run(){
			for(int i = 0; i<10; i++) {
				try {
					var.write(id);
				}catch(Exception e) {
					
				}
			}
		}
	}

		
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package os2finalhope;

/**
 *
 * @author mahamed abdelshafey
 */
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
      
public class Code {
private static Buffer buffer = new Buffer();
 
  public static void main(String[] args) throws InterruptedException {
  // Create a thread pool with two threads
  ExecutorService executor = Executors.newFixedThreadPool(2);
  executor.execute(new ProducerTask());
Thread.sleep(1000);
  executor.execute(new ConsumerTask());
  Thread.sleep(1000);
  executor.execute(new ConsumerTask());
  Thread.sleep(1000);

  executor.shutdown();
  }
 
  // A task for adding an int to the buffer
  private static class ProducerTask implements Runnable {
  public void run() {
  try {
 int i = 1;
  while (true) {
  System.out.println("Producer writes " + i+ " the remaining is " + buffer.tot);
  buffer.write(i++); // Add a value to the buffer
 // Put the thread into sleep
  Thread.sleep(500);
  }
  }
  catch (InterruptedException ex) {
  ex.printStackTrace();
  }
  }
  
  }
 
  // A task for reading and deleting an int from the buffer
 private static class ConsumerTask implements Runnable {
  public void run() {
       try {
  while (true) {
  System.out.println("\t\t\tConsumer reads " + buffer.read());
  // Put the thread into sleep
  Thread.sleep(1000);
  }
  }
  catch (InterruptedException ex) {
  ex.printStackTrace();
  }
  }
  }
 
  // An inner class for buffer
  private static class Buffer {
  private static final int CAPACITY = 2; // buffer size
   private int tot = 1;
  private java.util.LinkedList<Integer> queue =
  new java.util.LinkedList<>();
 
  // Create a new lock
  private static Lock lock = new ReentrantLock();
 
  // Create two conditions
  private static Condition notEmpty = lock.newCondition();
  private static Condition notFull = lock.newCondition();
 
  public void write(int value) {
  lock.lock(); // Acquire the lock
  try {
  while (queue.size() == CAPACITY) {
  System.out.println("Wait for notFull condition");
  notFull.await();
  }
 
  queue.offer(value);
  tot++;
  notEmpty.signal(); // Signal notEmpty condition
  }
  catch (InterruptedException ex) {
  ex.printStackTrace();
  }
  finally {
  lock.unlock(); // Release the lock
  }
  }
  public int read() {
  int value = 0;
  lock.lock(); // Acquire the lock
  try {
  while (queue.isEmpty()) {
  System.out.println("\t\t\tWait for notEmpty condition");
  notEmpty.await();
  }
 
  value = queue.remove();
  tot--;
  notFull.signal(); // Signal notFull condition
  }
  catch (InterruptedException ex) {
  ex.printStackTrace();
  } 
 finally {
 lock.unlock(); // Release the lock
 return value;
 }
}
  }  }
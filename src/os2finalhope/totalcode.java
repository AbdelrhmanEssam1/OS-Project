/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package os2finalhope;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
      

/**
 *
 * @author mahamed abdelshafey
 */
public class totalcode {
   public static Buffer buffer = new Buffer();
 
  public static void main(String[] args) throws InterruptedException {
  // Create a thread pool with two threads

  }

  // A task for adding an int to the buffer
  public static class ProducerTask implements Runnable {
 static int slP = 500;
  public void run() {
  try {
 int i = 1;
  while (true) {
  System.out.println("Producer writes " + i+ " the remaining is " + buffer.tot);
  buffer.write(i++); // Add a value to the buffer
 // Put the thread into sleep
  Thread.sleep(slP);
  }
  }
  catch (InterruptedException ex) {
  ex.printStackTrace();
  }
  }
  public  static void slch(int a){
  slP = a;}
  }
 
 
  // A task for reading and deleting an int from the buffer
 public static class ConsumerTask implements Runnable {
  static int x = 1000;
     public void run() {
       try {
  while (true) {
  System.out.println("\t\t\tConsumer reads " + buffer.read());
  // Put the thread into sleep
  Thread.sleep(x);
  }
  }
  catch (InterruptedException ex) {
  ex.printStackTrace();
  }
  }
    public static void slch2(int a){
  x = a;}
  }

  // An inner class for buffer
  public static class Buffer {
  public static  int CAPACITY = 2; // buffer size

       public static void changeCAPACITY (int x) {
            CAPACITY = x;
       }
  public static int  tot = 0;
  public java.util.LinkedList<Integer> queue =
  new java.util.LinkedList<>();
 
  // Create a new lock
  public static Lock lock = new ReentrantLock();
 
  // Create two conditions
  public static Condition notEmpty = lock.newCondition();
  public static Condition notFull = lock.newCondition();
  public void write(int value) {
  lock.lock(); // Acquire the lock
  try {
  while (queue.size() == CAPACITY) {
  System.out.println("Wait for notFull condition");
  notFull.await();
  }
   Login.addrowtotable(value);

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
   Login.editrowtotable(value,tot);
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

  
  }  
  
}

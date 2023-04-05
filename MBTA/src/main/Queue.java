/**
* This class provides the implementation of a generic queue
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/


package main;
import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;
	
	/**
	 * Initializes an empty queue
	 * 
	 * Running time: O(1)
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		this.head = 0;
		this.tail = 0;
		this.numEntries = 0;
	}
	
	/**
	 * Adds an element at the end of this queue
	 * 
	 * Running time: O(n) worst case
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public void enqueue(T element) {
		if (element != null) {
			if (q.length == 0) {		//when queue is empty
				q = (T[]) new Object[1];
			}
			q[tail] = element;
			numEntries++;	
			if (tail == q.length -1 && numEntries < q.length) {				//when the tail is at the end and queue is not full
				tail = 0;
			} else if (tail == q.length -1 && numEntries == q.length){		//when the tail is at the end and queue is full
				T[] update = (T[]) new Object[q.length + 1];
				for (int i = 0; i < q.length; i++) {
					update[i] = q[i];
				}
				q = update;
				tail++;
			} else if (tail != q.length -1 && numEntries == q.length) {		//when the tail is in the middle and queue is full
				T[] update = (T[]) new Object[q.length + 1];
				for (int i = 0; i <= tail; i++) {
					update[i] = q[i];
				}
				for (int i = tail + 2; i < q.length + 1; i++) {
					update[i] = q[i - 1];
				}
				q = update;
				head++;
				tail++;
			} else {			//when the tail is in the middle and queue is not full
				tail++;			
			}
		}		
	}
	
	/**
	 * Deletes the first element in the queue
	 * 
	 * Running time: O(1)
	 * @throws NoSuchElementException
	 */
	public void dequeue() { 
		if (numEntries == 0) {
			throw new NoSuchElementException("Queue is empty");
		}
		if (head == q.length -1) {
			head = 0;
		} else {			
			head++;
		}
		numEntries--;
	}
	
	/**
	 * Returns the first element in the queue
	 * 
	 * Running time: O(1)
	 * @return q[head]
	 * @throws NoSuchElementException
	 */
	public T front() {
		if (numEntries == 0) {
			throw new NoSuchElementException("Queue is empty");
		}
		return q[head];
	}
	
	/**
	 * Returns the size of the queue
	 * 
	 * Running time: O(1)
	 * @return numEntries
	 */
	public int size() {
		return numEntries;
	}
	
	/**
	 * Returns a String representation of this queue
	 * 
	 * Running time: O(n)
	 * @return output
	 */
	@Override
	public String toString() {
		if (numEntries == 0) {
			return "[]";
		}
		String output = "[";
		if (head < tail) {
			for(int i = head; i < tail; i++) {					
				output += q[i] + " ";
			}
		} else {										//when tail is in the front of head
			for(int i = head; i < q.length; i++) {
				output += q[i] + " ";
			}
			for(int i = 0; i < tail; i++) {					
				output += q[i] + " ";
			}
		}
		return output.trim() + "]";
	}
}

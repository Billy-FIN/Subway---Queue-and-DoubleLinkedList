/**
* This class tests the Queue class
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Queue;


class StudentQueueTest {

	Queue<Integer> a = new Queue<Integer>(0);
	Queue<Integer> b = new Queue<Integer>(5);
	
	@Test
	void test1() {
		a.enqueue(1);
		b.enqueue(1);
		a.enqueue(null);
		assertEquals("[1]", a.toString());
		assertEquals("[1]", b.toString());
		a.enqueue(2);
		a.enqueue(3);
		a.enqueue(4);
		a.enqueue(5);
		assertEquals("[1 2 3 4 5]", a.toString());			//test some normal situations
		assertEquals(5, a.size());
	}

	@Test
	void test2() {
		a.enqueue(1);
		a.enqueue(2);
		a.enqueue(3);
		a.enqueue(4);
		a.enqueue(5);
		a.dequeue();
		a.dequeue();
		assertEquals("[3 4 5]", a.toString());
		assertEquals(3, a.size());
		a.enqueue(5);
		assertEquals("[3 4 5 5]", a.toString());
		assertTrue(a.size() == 4);
		a.enqueue(6);
		a.enqueue(7);
		assertEquals("[3 4 5 5 6 7]", a.toString());		//edge case
		assertTrue(a.size() == 6);
	}
}

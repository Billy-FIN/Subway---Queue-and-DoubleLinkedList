/**
* This class tests the DoubleLinkedList class
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
import main.DoubleLinkedList;

class StudentDLLTest {

	DoubleLinkedList<Integer> a = new DoubleLinkedList<Integer>();
	
	@Test
	void testInsert() {
        a.insert(1);
        a.insert(2);
        a.insert(null);				//edge case
        a.insert(3);                            
        a.insert(4);
        a.insert(5);
        assertEquals(a.size(), 5);
        assertEquals(a.toString(), "[1, 2, 3, 4, 5]");
        assertEquals(a.getFirst().toString(), "1");
        a.delete(1);
        assertEquals(a.toString(), "[2, 3, 4, 5]");
        assertEquals(a.size(), 4);
        assertEquals(a.getFirst().toString(), "2");
        a.delete(4);
        assertEquals(a.toString(), "[2, 3, 5]");
        assertEquals(a.size(), 3);
        assertEquals(a.getFirst().toString(), "2");
	}

	@Test
	void testDelete() {
		assertEquals(a.delete(1), null);		//edge case
		a.insert(1);
		assertEquals(a.delete(1), 1);			//when there is only one node
		assertEquals(a.getFirst(), null);
		a.insert(1);
		a.insert(2);
		a.insert(3);
		assertEquals(a.delete(4), null);
		assertEquals(a.delete(3), 3);			//delete in the middle
		a.insert(1);
		a.insert(2);
		a.insert(3);
		assertEquals(a.toString(), "[1, 2, 1, 2, 3]");
		assertEquals(a.delete(2), 2);
		assertEquals(a.toString(), "[1, 1, 2, 3]");		//delete in the front
		assertEquals(a.size(), 4);
		a.delete(1);
		assertEquals(a.toString(), "[1, 2, 3]");
		assertEquals(a.size(), 3);
		a.delete(3);
		assertEquals(a.toString(), "[1, 2]");		//delete in the end
	}
	
	@Test
	void testGet() {
		assertEquals(a.get(1), null);		//edge case
		a.insert(1);
		assertEquals(a.get(2), null);
		assertEquals(a.get(1), 1);
		a.insert(1);
		a.insert(2);
		a.insert(3);
		assertEquals(a.get(6), null);
		assertEquals(a.get(2), 2);
	}
}

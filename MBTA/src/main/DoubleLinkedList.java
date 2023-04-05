/**
* This class provides the implementation of a generic 
* non-circular DDL
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/

package main;

public class DoubleLinkedList<T> {

	public Node<T> head;
	public int size;
	
	/**
	 * Initializes a DDL to have 0 elements
	 * 
	 * Running time: O(1)
	 */
	public DoubleLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Gets the first node in the list or null if it does not exist
	 * 
	 * Running time: O(1)
	 * @return null or head
	 */
	public Node<T> getFirst() {
		if(size == 0) {
			return null;
		}
		return head;
	}
	
	/**
	 * Adds an element to the end of the list
	 * 
	 * Running time: O(n)
	 * @param element
	 */
	public void insert(T element) {
		if(element != null) {
			Node<T> newNode = new Node<T>(element);
			if(head == null) {
				head = newNode;
			} else {
				Node<T> curr = head;
				while (curr.getNext() != null) {
					curr = curr.getNext();
				}
				curr.setNext(newNode);
				newNode.setPrev(curr);			
			}
			size++;			
		}
	}
	
	/**
	 * Deletes the first element from this list that matches the provided key.
	 * If the provided key does not exist, return null
	 * 
	 * Running time: O(n)
	 * @param key
	 * @return null or curr.getData()
	 */
	public T delete(T key) {
		if (head == null) {
			return null;
		} else {
			Node<T> curr = head;
			while (curr.getNext() != null && curr.getData() != key) {			//find the expected node to delete
				curr = curr.getNext();
			}
			if (curr.getNext() == null && curr.getData() == key && size != 1) {			//delete the node in the end
				curr.getPrev().setNext(null);
			} else if(curr.getPrev() == null && curr.getData() == key) {			//delete the node in the front
				head = curr.getNext();
			} else if(curr.getData() == key){				//delete the node in the middle of the DDL
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
			} else {			//no such node with the data
				return null;				
			}
			size--;
			return curr.getData();
		}
	}
	
	/**
	 * Returns the first element in the list that matches the provided key 
	 * or null if one cannot be found
	 * 
	 * Running time: O(n)
	 * @param key
	 * @return null or curr.getData()
	 */
	public T get(T key) {
		if(head == null) {
			return null;
		}
		Node<T> curr = head;
		while (curr.getNext() != null && !curr.getData().equals(key)) {			//find the expected node
			curr = curr.getNext();
		}
		if(curr.getData().equals(key)) {
			return curr.getData();
		} else {
			return null;			
		}
	}
	
	/**
	 * Returns the number of elements in the list
	 * 
	 * Running time: O(1)
	 * @return size
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns a String representation of this list’s elements
	 * 
	 * Running time: O(n)
	 * @return output
	 */
	@Override
	public String toString() {
		String output = "[";
		if(size > 0) {
			Node<T> curr = head;
			while(curr.getNext() != null) {
				output += curr.toString() + ", ";
				curr = curr.getNext();
			}
			output += curr.toString();
		}
		output += "]";
		return output;
	}
}

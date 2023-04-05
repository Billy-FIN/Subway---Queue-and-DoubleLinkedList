/**
* This class provides the implementation of a generic 
* node  in support of the implementation of a DDL.
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/

package main;

public class Node<T> {
	
	private T key;
	private Node<T> nextPointer;
	private Node<T> prevPointer;
	
	/**
	 * Initializes a node with no previous or next node pointer
	 * 
	 * Running time: O(1)
	 * @param data
	 */
	public Node(T data) {
		this.key = data;
		this.nextPointer = null;
		this.prevPointer = null;
	}
	
	/**
	 * Sets the data for this node
	 * 
	 * Running time: O(1)
	 * @param data
	 */
	public void setData(T data) {
		key = data;
	}
	
	/**
	 * Points this node to another as its next node
	 * 
	 * Running time: O(1)
	 * @param next
	 */
	public void setNext(Node<T> next) {
		nextPointer = next;
	}
	
	/**
	 * Points this node to another as its previous node
	 * 
	 * Running time: O(1)
	 * @param prev
	 */
	public void setPrev(Node<T> prev) {
		prevPointer = prev;
	}
	
	/**
	 * Obtains the next node of this node
	 * 
	 * Running time: O(1)
	 * @return nextPointer
	 */
	public Node<T> getNext() {
		return nextPointer;
	}
	
	/**
	 * Obtains the previous node of this node
	 * 
	 * Running time: O(1)
	 * @return prevPointer
	 */
	public Node<T> getPrev() {
		return prevPointer;
	}
	
	/**
	 * Obtains this node’s key
	 * 
	 * Running time: O(1)
	 * @return key
	 */
	public T getData() {
		return key;
	}
	
	/**
	 * Returns a String representation of this node
	 * 
	 * Running time: O(1)
	 * @return output
	 */
	@Override
	public String toString() {
		String output = "" + key;
		return output;
	}
}

package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		// check if the element is null
		// if it is, throw error
		if (element == null) {
			throw new NullPointerException("could not add null element.");
		}
		// create a new node
		// set the data of the new node to element
		LLNode<E> newNode = new LLNode<E>(element);
		// set n.next = tail
		newNode.next = tail;
		// set n.prev = n.next.prev
		newNode.prev = newNode.next.prev;
		// set n.next.prev = n;
		newNode.next.prev = newNode;
		// set n.prev.next = n;
		newNode.prev.next = newNode;
		
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		// check if the index is smaller than the size 
		// if not, throw error
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index out of bound.");
		}
		// for every LLNode in MyLinkedList, where i <= index
		// use LLNode.next to get the element
		LLNode<E> tempNode = head.next;
		for (int i = 1; i <= index; i++) {
			tempNode = tempNode.next;
		}
		return tempNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		// check if the element is null
		// if it is, throw error
		if (element == null) {
			throw new NullPointerException("could not add null element.");
		}
		else if (index < 0 || (index >= size && index != 0)) {
			throw new IndexOutOfBoundsException("Index out of bound.");
		}
		
		// create a new Node
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> tempNode = head.next;
		for (int i = 1; i <= index; i++) {
			tempNode = tempNode.next;
		}
		newNode.next = tempNode;
		newNode.prev = tempNode.prev;
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		// find the remove node
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound.");
		}
		
		LLNode<E> removeNode = head.next;
		for (int i = 1; i <= index; i++) {
			removeNode = removeNode.next;
		}
		removeNode.prev.next = removeNode.next;
		removeNode.next.prev = removeNode.prev;
		size--;
		return removeNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index < 0 || (index >= size && index != 0)) {
			throw new IndexOutOfBoundsException("Index out of bound.");
		}
		else if (element == null) {
			throw new NullPointerException("could not add null element.");
		}
		// find the node to replace
		LLNode<E> removeNode = head.next;
		for (int i = 1; i <= index; i++) {
			removeNode = removeNode.next;
		}
		E oldValue = removeNode.data;
		removeNode.data = element;
		//  return the old value held at that index
		return oldValue;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		// Ensure that you can't remove an element at too low of an index. 
		try {
			emptyList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		// Ensure that you can't remove an element at too high of an index. 
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		emptyList.add(10);
		int b = emptyList.remove(0);
		assertEquals("Remove: check b is correct ", 10, b);
		assertEquals("The size after remove ", (Integer)0, (Integer)emptyList.size());
		
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		int originalSize = list1.size();
		// add an element to the list
		list1.add(99);
		// check the size, is it + 1
		assertEquals("The size after add ", originalSize+1, list1.size());
		// check the last element (index size - 1)
		assertEquals("The last element after add ", (Integer)99, list1.get(list1.size()-1));
		// check the second last element (index size - 2)
		assertEquals("The original last element ", (Integer)42, list1.get(list1.size()-2));
		
		emptyList.add(12);
		assertEquals("Add the an empty list ", (Integer)12, emptyList.get(0));
		
		// Check that you can't add a null element.  
		try {
			list1.add(null);
			fail("Check null.");
		}
		catch (NullPointerException e) {
				
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Empty List size ", (Integer)emptyList.size(), (Integer)0);
		assertEquals("List with 3 elements ", (Integer)list1.size(), (Integer)3);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		int originalSize = list1.size();
		list1.add(1, 10);
		int changedSize = list1.size();
		assertEquals("The size after add at index", originalSize+1, changedSize);
		assertEquals("The specific element after add ", (Integer)10, list1.get(1));
		assertEquals("The original element after specific add ", (Integer)21, list1.get(2));
		
		// Make sure that you can't add an element at too low of an index. 
		try {
			emptyList.add(-1, 10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
				
		}
		// Make sure that you can't add an element at too high of an index. 
		try {
			emptyList.add(1, 10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
				
		}
	
		// Check that you can't add a null element.  
		try {
			list1.add(0, null);
			fail("Check null.");
		}
		catch (NullPointerException e) {
						
		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		int originalSize = list1.size();
		list1.set(1, 10);
		int changedSize = list1.size();
		
		assertEquals("The size after add at index", originalSize, changedSize);
		assertEquals("The specific element after set ", (Integer)10, list1.get(1));
		
		// Check that you can't set an element at too low of an index. 
		try {
			emptyList.set(-1, 10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		// Check that you can't set an element at too high of an index. 
		try {
			emptyList.set(1, 10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		// Check that you can't set an element to null. 
		try {
			list1.set(1, null);
			fail("Check null.");
		}
		catch (NullPointerException e) {
		
		}
	}
	
	
	// TODO: Optionally add more test methods.
	
}

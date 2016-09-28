/********************************************************************
 * Sara Cronin
 * JHED: scronin2
 * scronin2@jhu.edu
 *
 * Ryan Demo
 * JHED: rdemo1
 * rdemo1@jhu.edu
 *
 * Henrik Schuh
 * JHED: hschuh2
 * hschuh@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures | Section 1
 * Project 4 - Part B - Minimally Weighted Spanning Trees
 *
 *******************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/** JUnit test class for PQHeap.
 */
public class PQHeapTest {
    
    /** PQHeap of integers.
     */
    private static PQHeap<Integer> pqint;
    
    /** PQHeap of strings.
     */
    private static PQHeap<String> pqstr;
    
    /** Array of integers to add to integer priority queue.
     */
    private static Integer[] intvals = {1, 11, 2, 12, 6, 3, 9, 24};
    
    /** Array of strings to add to string priority queue.
     */
    private static String[] strvals = {"one", "eleven", "two", "twelve", "six",
        "three", "nine", "twenty-four"};

    /** Initialize queues.
     */
    @BeforeClass
    public static void init() {
        // create Max Priority Queues of Integers and Strings
        pqint = new PQHeap<Integer>();
        pqstr = new PQHeap<String>();
    }
    
    /** Setup tests by clearing both queues before each test.
     */
    @Before
    public void setup() {
        // clear queues before each test
        pqint.clear();
        pqstr.clear();
    }
    

    /** Test size() on empty and non-empty queues. Relies on insert and remove.
     */
    @Test
    public void testSize() {
        // check that the size of an empty list is zero
        assertTrue("PQ should be empty", pqint.isEmpty());
        assertTrue("PQ should be empty", pqstr.isEmpty());
        assertEquals("Size of empty lists should be 0", 0, pqint.size(),
                     pqstr.size());
        
        // add one thing to each
        pqint.insert(intvals[0]);
        pqstr.insert(strvals[0]);
        
        // check that size increased
        assertEquals("Size should be 1", pqint.size(), 1);
        assertEquals("Size should be 1", pqstr.size(), 1);
        
        // add one more thing, check again
        pqint.insert(intvals[1]);
        pqstr.insert(strvals[1]);
        assertEquals("Size should be 2", pqint.size(), 2);
        assertEquals("Size should be 2", pqstr.size(), 2);
        
        // remove, check size decrements
        pqint.remove();
        pqstr.remove();
        assertEquals("Size should be 1", pqint.size(), 1);
        assertEquals("Size should be 1", pqstr.size(), 1);
        
        // remove, check size decrements
        pqint.remove();
        pqstr.remove();
        assertEquals("Size should be 0", pqint.size(), 0);
        assertEquals("Size should be 0", pqstr.size(), 0);
        
        for (int i = 1; i < 5; i++) {
            pqint.insert(i);
            assertEquals("Size updated after each insert call", i,
                         pqint.size());
        }
    }
    
    /** Test isEmpty() on empty queues relies on size.
     */
    @Test
    public void testIsEmpty() {
        // check that the size of an empty list is zero
        assertEquals("Size should be zero", 0, pqint.size(), pqstr.size());
        
        // check that both return true that they are empty
        assertTrue("Inetegr PQ should bs empty", pqint.isEmpty());
        assertTrue("String PQ should be empty", pqstr.isEmpty());
    }
    
    /** Test clear() on empty queues.
     */
    @Test
    public void testClearOnEmpty() {
        // check that queue can be cleared twice (once in setup)
        pqint.clear();
        pqstr.clear();
        
        // check that size after clear is zero
        assertEquals("0 size after clearing", 0, pqint.size(), pqstr.size());

        // check all heaps are currently empty
        assertTrue("Integer PQ should be empty", pqint.isEmpty());
        assertTrue("String PQ should be empty", pqstr.isEmpty());
    }
    
    /** Test clear() on queues with contents relies on insert, remove, 
     *  size, isEmpty.
     */
    @Test
    public void testClearWithContents() {
        // add then remove
        pqint.insert(intvals[0]);
        pqstr.insert(strvals[0]);
        pqint.remove();
        pqstr.remove();
        
        // check that heaps are empty again
        assertTrue("PQ should be empty", pqint.isEmpty());
        assertTrue("PQ should be empty", pqstr.isEmpty());
        assertEquals("Size should be 0", pqint.size(), 0);
        assertEquals("Size should be 0", pqstr.size(), 0);
        
        // add then clear all, relies on clear
        for (Integer i :  intvals) {
            pqint.insert(i);
        }
        
        // check that the queue is now not empty
        assertFalse(pqint.isEmpty());
        
        // clear contents
        pqint.clear();
        assertTrue("PQ should be empty", pqint.isEmpty());
        assertEquals("Size should be 0", pqint.size(), 0);
        
        // repeat for string
        for (String s : strvals) {
            pqstr.insert(s);
        }
        // check that the queue is now not empty
        assertFalse(pqstr.isEmpty());
        
        // clear contents
        pqstr.clear();
        assertTrue("PQ should be empty", pqstr.isEmpty());
        assertEquals("Size should be 0", pqstr.size(), 0);
    }
    
    /** Test peek() on non-empty queues, relies on insert, clear, remove.
     */
    @Test
    public void testPeek() {
        // variables to hold returned values
        int intreturned = 0;
        String strreturned = "";
        int i;
        
        // insert one value to each queue
        pqint.insert(intvals[0]);
        pqstr.insert(strvals[0]);
        
        // get the max value
        intreturned = pqint.peek();
        strreturned = pqstr.peek();
        
        // check that max is inserted value
        assertTrue("max value should be only value",
                   intvals[0].equals(intreturned));
        assertTrue("max value should be only value",
                   strvals[0].equals(strreturned));
        
        // check that size is unchanged
        assertEquals("size should be one", 1, pqint.size(), pqstr.size());
        
        // insert rest of values
        for (i = 1; i <= intvals.length - 1; i++) {
            pqint.insert(intvals[i]);
            pqstr.insert(strvals[i]);
        }
        // check that max value returned is correct
        assertTrue("max of int shoudl be 24", pqint.peek().equals(24));
        assertTrue("max string shoudl be two", pqstr.peek().equals("two"));
        
        // check twice to ensure no remove
        assertTrue("max of int shoudl be 24", pqint.peek().equals(24));
        assertTrue("max string shoudl be two",
                   pqstr.peek().equals("two"));
        
        // check that size is unchanged
        assertEquals("size for both shoudl be eight", 8,
                     pqint.size(), pqstr.size());
        
        // clear queues
        pqint.clear();
        pqstr.clear();
        
        
        // insert Integers
        pqint.insert(3);
        pqint.insert(8);
        pqint.insert(5);
        assertEquals("Max should be 8 regardless of position",
                     (Integer) 8, pqint.peek());
        pqint.remove();
        assertEquals("Max should be 5 regardless of position",
                     (Integer) 5, pqint.peek());
        pqint.remove();
        assertEquals("Max should be 3 regardless of position",
                     (Integer) 3, pqint.peek());
        
        // insert Strings
        pqstr.insert("a");
        pqstr.insert("c");
        pqstr.insert("b");
        assertEquals("Max should be c regardless of position",
                     "c", pqstr.peek());
        pqstr.remove();
        assertEquals("Max should be b regardless of position",
                     "b", pqstr.peek());
        pqstr.remove();
        assertEquals("Max should be a regardless of position",
                     "a", pqstr.peek());
    }
    
    /**
     * Test peek() on empty MaxPQHeap.
     */
    @Test(expected = QueueEmptyException.class)
    public void testEmptyPeek() {
        // check that exception is thrown on empty queue (after clear)
        assertEquals("Unable to peek at empty PQHeap", null, pqint.peek());
        assertEquals("Unable to peek at empty PQHeap", null, pqstr.peek());

        // check that exception is thrown after values inserted and then deleted
        pqint.insert(intvals[0]);
        pqstr.insert(strvals[0]);
        pqint.remove();
        pqstr.remove();
        
        // check that exception is thrown
        pqint.peek();
        pqstr.peek();
    }
    
    /**
     * Test remove() on non-empty PQHeaps. Relies on functional insert,
     * remove for some tests.
     */
    @Test
    public void testRemove() {
        // variables to hold removed values
        int intremoved = 0;
        String strremoved = "";
        int i;
        
        // insert one value to each queue
        pqint.insert(intvals[0]);
        pqstr.insert(strvals[0]);
        
        // remove one item
        intremoved = pqint.remove();
        strremoved = pqstr.remove();
        
        // check that removed item was the one inserted
        assertTrue(intvals[0].equals(intremoved));
        assertTrue(strvals[0].equals(strremoved));
        
        // check that size is decremented
        assertEquals(0, pqint.size(), pqstr.size());
        
        // insert the rest of the values
        for (i = 1; i <= intvals.length - 1; i++) {
            pqint.insert(intvals[i]);
            pqstr.insert(strvals[i]);
        }
        // check that max values removed are correct
        assertTrue(24 == pqint.remove());
        assertTrue(pqstr.remove().equals("two"));
        
        // check that the size was decremented correctly
        assertEquals(6, pqint.size(), pqstr.size());
        
        // remove the remaining values
        for (i = pqint.size(); i > 0; i--) {
            pqint.remove();
            pqstr.remove();
        }
        
        // check that size correct and queues empty
        assertEquals(0, pqint.size(), pqstr.size());
        assertTrue(pqint.isEmpty());
        assertTrue(pqstr.isEmpty());
        
        // insert values in pqint
        Integer[] vals = {1, 2, 3, 4, 5, 5, 8, 6, -1};
        pqint.init(Arrays.asList(vals));
        
        // test that correct values removed.
        assertTrue("Maximum value returned", 8 == pqint.remove());
        assertTrue("Second-highest value after removal", 6 == pqint.remove());
        assertTrue(5 == pqint.remove()); //remove all remaining elements
        assertTrue(5 == pqint.remove());
        assertTrue(4 == pqint.remove());
        assertTrue(3 == pqint.remove());
        assertTrue(2 == pqint.remove());
        assertTrue(1 == pqint.remove());
        
        // insert values into pqstr.
        pqstr.insert("A");
        pqstr.insert("B");
        pqstr.insert("C");
        
        // check that correct values removed.
        assertEquals("Max value for non-numeric data", "C", pqstr.remove());
        assertEquals("Second-highest value after removal",
                     "B", pqstr.remove());
        assertEquals("Third-highest value after removal",
                     "A", pqstr.remove());
    }
    
    /** Test empty queue exception when removing from an empty queue.
     *  Relies on insert, clear, and remove.
     */
    @Test (expected = QueueEmptyException.class)
    public void testRemoveException() {
        // check that exception is thrown on empty queue (after clear)
        pqint.remove();
        pqstr.remove();
        
        // check that exception is thrown after values inserted and then deleted
        pqint.insert(intvals[0]);
        pqstr.insert(strvals[0]);
        pqint.remove();
        pqstr.remove();
        
        // check that exception is thrown
        pqint.remove();
        pqstr.remove();
    }
    
    /** Test of insert, relies on size, init, remove, is empty.
     */
    @Test
    public void testInsert() {
        // check all heaps are currently empty
        assertTrue("PQ should be empty", pqint.isEmpty());
        assertTrue("PQ should be empty", pqstr.isEmpty());
        
        // check size is originally zero
        assertEquals("PQ should be size 0", pqint.size(), 0);
        assertEquals("PQ should be size 0", pqstr.size(), 0);
        
        // create int to store size
        int size = 0;
        
        // add things to integer PQ
        for (Integer i : intvals) {
            pqint.insert(i);
            size++;
            
            assertEquals("Size did not increment", pqint.size(), size);
            assertFalse("PQ should not be empty", pqint.isEmpty());
        }
        
        // reinitialize size
        size = 0;
        
        // repeat for string PQ
        for (String s : strvals) {
            pqstr.insert(s);
            size++;
            
            assertEquals("Size did not increment", pqstr.size(), size);
            assertFalse("PQ should not be empty", pqstr.isEmpty());
        }
        
        // clear both queues.
        pqint.clear();
        pqstr.clear();
        
        
        // add values to int queue
        Integer[] vals = {1, 2, 3, 4, 5, 5, 8, 6, -1, 10, 0, -10};
        pqint.init(Arrays.asList(vals));
        
        // check all inserted and proper removal order
        assertTrue("Size updated", 12 == pqint.size());
        assertTrue(10 == pqint.remove());
        assertTrue(8 == pqint.remove());
        assertTrue(6 == pqint.remove());
        assertTrue(5 == pqint.remove());
        assertTrue(5 == pqint.remove());
        assertTrue(4 == pqint.remove());
        assertTrue(3 == pqint.remove());
        assertTrue(2 == pqint.remove());
        assertTrue(1 == pqint.remove());
        assertTrue(0 == pqint.remove());
        assertTrue(-1 == pqint.remove());
        assertTrue(-10 == pqint.remove());
        
        //insert on empty list
        pqstr.insert("X");
        pqstr.insert("Y");
        pqstr.insert("A");
        
        // check size and whether empty
        assertEquals("Size updated", 3, pqstr.size());
        assertFalse("No longer empty", pqstr.isEmpty());
        
        // check all were inserted
        assertEquals("Y", pqstr.remove());
        assertEquals("X", pqstr.remove());
        assertEquals("A", pqstr.remove());
    }
    
    /** Test of init, relies on remove, size, isEmpty.
     */
    @Test
    public void testInit() {
        // create array, already ordered heap
        Integer[] a1 = {5, 4, 3, 2, 1};
        List<Integer> v1 = Arrays.asList(a1);
        
        // initialize pqint from list
        pqint.init(v1);
        
        // check size and whether empty
        assertEquals("Size after init", 5, pqint.size());
        assertFalse("Nonempty after init", pqint.isEmpty());
        
        // check that values are correct
        assertTrue(5 == pqint.remove());
        assertTrue(4 == pqint.remove());
        assertTrue(3 == pqint.remove());
        assertTrue(2 == pqint.remove());
        assertTrue(1 == pqint.remove());
        
        // integer array, non-ordered heap
        Integer[] a2 = {1, 2, 3, 4, 5};
        List<Integer> v2 = Arrays.asList(a2);
        
        // initialize non-empty heap from list
        pqint.init(v2);
        
        // check size and whether empty
        assertTrue("Size after init", 5 == pqint.size());
        assertFalse("Nonempty after init", pqint.isEmpty());
        
        // check that values are correct
        assertTrue(5 == pqint.remove());
        assertTrue(4 == pqint.remove());
        assertTrue(3 == pqint.remove());
        assertTrue(2 == pqint.remove());
        assertTrue(1 == pqint.remove());
        
        // create string array, already ordered
        String[] a3 = {"Z", "X", "Y", "W", "V"};
        List<String> v3 = Arrays.asList(a3);
        
        // initialize queue
        pqstr.init(v3);
        
        // check size and emptiness
        assertEquals("Size after init", 5, pqstr.size());
        assertFalse("Nonempty after init", pqstr.isEmpty());
        
        // check values
        assertEquals("Z", pqstr.remove());
        assertEquals("Y", pqstr.remove());
        assertEquals("X", pqstr.remove());
        assertEquals("W", pqstr.remove());
        assertEquals("V", pqstr.remove());
        
        // non ordered string array
        String[] a4 = {"A", "B", "C", "D"};
        List<String> v4 = Arrays.asList(a4);
        
        // initialize non empty string queue
        pqstr.init(v4);
        
        // check size and emptiness
        assertEquals("Size after init", 4, pqstr.size());
        assertFalse("Nonempty after init", pqstr.isEmpty());
        
        // check for correct values
        assertEquals("D", pqstr.remove());
        assertEquals("C", pqstr.remove());
        assertEquals("B", pqstr.remove());
        assertEquals("A", pqstr.remove());
    }
    
    /** Test of toString, relies on insert.
     */
    @Test
    public void testToString() {
        int i;
        
        // insert values into queues.
        for (i = 0; i <= intvals.length - 1; i++) {
            pqint.insert(intvals[i]);
            pqstr.insert(strvals[i]);
        }
        
        // check that toString correct
        assertEquals(pqint.toString(), "[24, 12, 9, 11, 6, 2, 3, 1]");
        assertEquals(pqstr.toString(),
                "[two, twenty-four, three, twelve, six, one, nine, eleven]");
    }
}

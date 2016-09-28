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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * P4B PQHeap implementation using an ArrayList.
 * @param <T> Data type
 */
public class PQHeap<T extends Comparable<? super T>>
    implements PriorityQueue<T> {

    /** Default comparator to return .compareTo ordering of two objects.
     *  Overrides the compareTo method.
     *  @param <T> data type
     */
    private static class DefaultComp<T extends Comparable<? super T>>
        implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
    
    /** ArrayList to store heap elements. */
    private ArrayList<T> heap;
    /** Comparator to use for heap operations. */
    private Comparator<T> comp;
    
    /**
     * Initializes an empty MaxPQHeap using the default comparator.
     */
    public PQHeap() {
        this(new DefaultComp<T>());
    }
    
    /**
     * Initializes an empty MaxPQHeap using a specified Comparator object.
     * @param c Comparator to use
     */
    public PQHeap(Comparator<T> c) {
        this.comp = c;
        this.clear();
    }
    
    @Override
    public int size() {
        return (this.heap.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.heap.size() == 1;
    }

    @Override
    public void clear() {
        this.heap = new ArrayList<T>();
        this.heap.add(0, null);
    }

    @Override
    public T peek() throws QueueEmptyException {
        if (this.heap.size() <= 1) {
            throw new QueueEmptyException(); 
        }
        return this.heap.get(1);
    }

    @Override
    public T remove() throws QueueEmptyException {
        if (this.heap.size() <= 1) {
            throw new QueueEmptyException(); 
        }
        
        T best = this.heap.get(1);
        this.heap.set(1, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);
        
        if (this.heap.size() > 2) {
            int i = 1, child;
            T last = this.heap.get(i);
            while (2 * i <= this.heap.size() - 1) {
                child = 2 * i;
                if (child < this.heap.size() - 1 
                        && this.comp.compare(this.heap.get(child), 
                                this.heap.get(child + 1)) < 0) {
                    child++;
                }
                if (this.comp.compare(last, this.heap.get(child)) >= 0) {
                    break;
                } else {
                    this.heap.set(i, this.heap.get(child));
                    i = child;
                }
            }
            this.heap.set(i, last);
        }
        return best;
    }

    @Override
    public void insert(T t) {
        this.heap.add(this.heap.size(), t);
        int i = this.heap.size() - 1;
        while (i > 1 
                && this.comp.compare(this.heap.get(i / 2), t) < 0) {
            this.heap.set(i, this.heap.get(i / 2));
            i /= 2;
        }
        this.heap.set(i, t);
    }

    @Override
    public void init(Collection<T> values) {
        this.clear();
        this.heap.addAll(values);
        for (int i = values.size() / 2; i > 0; i--) {
            int child, j = i;
            T last = this.heap.get(j);
            while (2 * j <= this.heap.size() - 1) {
                child = 2 * j;
                if (child < this.heap.size() - 1
                        && this.comp.compare(this.heap.get(child), 
                                this.heap.get(child + 1)) < 0) {
                    child++;
                }
                if (this.comp.compare(last, this.heap.get(child)) >= 0) {
                    break;
                } else {
                    this.heap.set(j, this.heap.get(child));
                    j = child;
                }
            }
            this.heap.set(j, last);
        }
    }
    
    /**
     * Returns a string representation of the heap array, excluding the first
     * unused position.
     * @return String of elements
     */
    public String toString() {
        return this.heap.subList(1, this.heap.size()).toString();
    }
}

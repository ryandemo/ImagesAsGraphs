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
 * Project 4 - Part A - Graph Implementation
 *
 *******************************************************************/


/**
 * Vertex class extension to use templating.
 * @author Henrik Schuh, Sara Cronin, Ryan Demo (hschuh2, scronin2, rdemo1)
 * @param <T> Data object template type
 */
public class GVertex<T> extends Vertex implements Comparable<Vertex> {

    /** Data object of template type T. */
    private T data;
    
    /** 
     * Creates a new vertex with specified ID int and data.
     * @param d Data object
     * @param id Integer unique ID
     */
    public GVertex(T d, int id) {
        super(null, id);
        this.data = d;
    }
    
    /**
     * Returns data object associated with the vertex.
     * @return Data object
     */
    public T getData() {
        return this.data;
    }   
    
    /**
     * Sets data object associated with the vertex.
     * @param d New data object
     */
    public void setData(T d) {
        this.data = d;
    }

}

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


/** Implementation of a weighted, undirected edge class (for graphs).
 *  @param <T> type of vertex
 */
public class WEdge<T> implements Comparable<WEdge<T>> {

    /** Starting vertex of an edge. */
    private GVertex<T> source;
    
    /** Ending vertex of an edge. */
    private GVertex<T> end;
    
    /** Weight of edge. */
    private double weight;

    /** Create an undirected edge.
     *  @param u the start
     *  @param v the end
     *  @param w the weight
     */
    public WEdge(GVertex<T> u, GVertex<T> v, double w) {
        this.source = u;
        this.end = v;
        this.weight = w;
    }

    /** Returns the weight of the edge.
     *  @return the weight of the edge.
     */
    public double weight() {
        return this.weight;
    }

    /** Is a vertex incident to this edge.
     *  @param v the vertex
     *  @return true if source or end, false otherwise
     */
    public boolean isIncident(GVertex<T> v) {
        return this.source.equals(v) || this.end.equals(v);
    }

    /** Get the starting endpoint vertex.
     *  @return the vertex
     */
    public GVertex<T> source() {
        return this.source;
    }

    /** Get the ending endpoint vertex.
     *  @return the vertex
     */
    public GVertex<T> end() {
        return this.end;
    }

    /** Create a string representation of the edge.
     *  @return the string as (source,end)
     */
    public String toString() {
        return "(" + this.source + "," + this.end + "," + this.weight + ")";
    }

    /** Check if two edges are the same.
     *  @param other the edge to compare to this
     *  @return true if directedness and endpoints match, false otherwise
     */
    public boolean equals(Object other) {
        if (!(other instanceof WEdge<?>)) {
            return false;
        }
        WEdge<?> e = (WEdge<?>) other;
        if (e.weight() != this.weight) {
            return false;
        }
        if (e.source().equals(this.source()) 
            || e.source().equals(this.end())) {
            return (e.end().equals(this.source())
                   || e.end().equals(this.end()));
        }
        return false;
    }

    /** Make a hashCode based on the toString.
     *  @return the hashCode
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

    /** Make compareTo based on weights.
     *  @param wedge to edge to compare to
     *  @return the comparison
     */
    public int compareTo(WEdge<T> wedge) {
        return (Double.valueOf(this.weight).
               compareTo(Double.valueOf(wedge.weight())));
    }
}

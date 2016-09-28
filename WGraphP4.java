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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/** Implementation for a weighted undirected graph.
 *  @param <VT> the type of data stored in each vertex
 */
public class WGraphP4<VT> implements WGraph<VT> {

    /** Holds the next integer ID to give to a new vertex.
     */
    private int nextID;
    
    /** Total number of edges in graph. This includes both directed
     * edges in an undirected graph. Divide by 2 for unique edges.
     */
    private int numEdges;
    
    /** List of vertices.
     */
    private ArrayList<GVertex<VT>> vertices;
    
    /** List of list of vertices' edges.
     */
    private ArrayList<ArrayList<WEdge<VT>>> edges;
    
    /** Initialize a new weighted graph.
     */
    public WGraphP4() {
        this.nextID = 0;
        this.vertices = new ArrayList<GVertex<VT>>();
        this.edges = new ArrayList<ArrayList<WEdge<VT>>>();
    }

    @Override
    public int numEdges() {
        //returns actual num of edges
        //this is an undirected weighted graph with each edge
        //stored as two directed edges
        return this.numEdges / 2;
    }

    @Override
    public int numVerts() {
        return this.vertices.size();
    }

    @Override
    public int nextID() {
        return this.nextID++;
    }

    @Override
    public boolean addVertex(VT d) {
        int id = this.nextID();
        this.vertices.add(id, new GVertex<VT>(d, id));
        return true;
    }

    @Override
    public boolean addVertex(GVertex<VT> v) {
        if (this.vertices.size() > v.id()
                && this.vertices.get(v.id()) != null) {
            return false;  // already exists 
        }
        this.vertices.add(v.id(), v);
        this.edges.add(v.id(), new ArrayList<WEdge<VT>>());
        return true;
    }

    @Override
    public boolean addEdge(WEdge<VT> e) {
        return this.addEdge(e.source(), e.end(), e.weight());
    }

    @Override
    public boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight) {
        
        if (!this.addEdgeHelper(v, u)) {
            return false;
        }

        ArrayList<WEdge<VT>> edgesForVertex = this.edges.get(v.id());
        for (WEdge<VT> e: edgesForVertex) {
            if (e.isIncident(v) && e.isIncident(u)) {
                //if one directed edge exists, the other will.
                //therefore can terminate here if found a match
                return false;
            }
        }
        edgesForVertex.add(new WEdge<VT>(v, u, weight));
        this.numEdges++;
        
        edgesForVertex = this.edges.get(u.id());
        for (WEdge<VT> e: edgesForVertex) {
            if (e.isIncident(v) && e.isIncident(u)) {
                return false;
            }
        }
        edgesForVertex.add(new WEdge<VT>(u, v, weight));
        this.numEdges++;
        
        return true;
    }
    
    /** Add vertices if they don't exist. Called during addEdge.
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return false if vertices exist, true if added
     */
    private boolean addEdgeHelper(GVertex<VT> v, GVertex<VT> u) {
        boolean success = true;
        if (this.vertices.size() <= v.id()
                || this.vertices.get(v.id()) == null) {
            success = this.addVertex(v);
        }
        if (this.vertices.size() <= u.id()
                || this.vertices.get(u.id()) == null) {
            success = this.addVertex(u);
        }
        return success;
    }

    @Override
    public boolean deleteEdge(GVertex<VT> v, GVertex<VT> u) {
        if (this.vertices.size() < v.id() || this.vertices.get(v.id()) == null
                || this.vertices.size() < u.id()
                || this.vertices.get(u.id()) == null) {
            return false;
        }
        
        return this.deleteEdgesVU(v, u);
    }
    
    /** Remove both directional edges of an undirected edge if there.  
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if delete, false if wasn't there
     */
    private boolean deleteEdgesVU(GVertex<VT> v, GVertex<VT> u) {
        boolean successV = false;
        ArrayList<WEdge<VT>> edgesForVertex = this.edges.get(v.id());
        for (int i = 0; i < edgesForVertex.size(); i++) {
            WEdge<VT> e = edgesForVertex.get(i);
            if (e.isIncident(v) && e.isIncident(u)) {
                edgesForVertex.remove(i);
                this.numEdges--;
                successV = true;
            }
        }
        
        boolean successU = false;
        edgesForVertex = this.edges.get(u.id());
        for (int i = 0; i < edgesForVertex.size(); i++) {
            WEdge<VT> e = edgesForVertex.get(i);
            if (e.isIncident(v) && e.isIncident(u)) {
                edgesForVertex.remove(i);
                this.numEdges--;
                successU = true;
            }
        }
        return successV && successU;
    }

    @Override
    public boolean areAdjacent(GVertex<VT> v, GVertex<VT> u) {
        if (this.vertices.size() < v.id() || this.vertices.get(v.id()) == null
                || this.vertices.size() < u.id()
                || this.vertices.get(u.id()) == null) {
            return false;
        } else {
            ArrayList<WEdge<VT>> edgesForVertex = this.edges.get(v.id());
            for (WEdge<VT> e: edgesForVertex) {
                if (e.isIncident(v) && e.isIncident(u)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public List<GVertex<VT>> neighbors(GVertex<VT> v) {
        ArrayList<GVertex<VT>> nbrs = new ArrayList<GVertex<VT>>();
        ArrayList<WEdge<VT>> edgesForVertex = this.edges.get(v.id());
        for (WEdge<VT> e: edgesForVertex) {
            nbrs.add(e.end());
        }
        return nbrs;
    }

    @Override
    public int degree(GVertex<VT> v) {
        if (this.vertices.size() < v.id()
                || this.vertices.get(v.id()) == null) {
            return 0;
        }
        return this.edges.get(v.id()).size();
    }
    
    @Override
    public boolean areIncident(WEdge<VT> e, GVertex<VT> v) {
        if (this.vertices.size() < v.id()
                || this.vertices.get(v.id()) == null) {
            return false;
        }
        return e.isIncident(v);
    }

    @Override
    public List<WEdge<VT>> allEdges() {
        ArrayList<WEdge<VT>> all = new ArrayList<WEdge<VT>>(this.numVerts());
        for (int i = 0; i < this.numVerts(); i++) {
            all.addAll(this.edges.get(i));
            // will create duplicate edges for an undirected graph
        }
        return all;
    }

    @Override
    public List<GVertex<VT>> allVertices() {
        return this.vertices;
    }

    @Override
    public List<GVertex<VT>> depthFirst(GVertex<VT> v) {
        ArrayList<GVertex<VT>> reaches = 
                new ArrayList<GVertex<VT>>(this.numVerts());
        // using LinkedList<Vertex> as a Stack
        LinkedList<GVertex<VT>> stack = new LinkedList<GVertex<VT>>();
        boolean[] visited = new boolean[this.numVerts()];  // inits to false
        stack.addFirst(v);
        visited[v.id()] = true;
        while (!stack.isEmpty()) {
            v = stack.removeFirst();
            reaches.add(v);
            for (GVertex<VT> u: this.neighbors(v)) {
                if (!visited[u.id()]) {
                    visited[u.id()] = true;
                    stack.addFirst(u);
                }
            }
        }
        return reaches;
    }

    @Override
    public List<WEdge<VT>> incidentEdges(GVertex<VT> v) {
        if (this.vertices.size() < v.id()
                || this.vertices.get(v.id()) == null) {
            return new ArrayList<WEdge<VT>>();
        }
        return this.edges.get(v.id());
    }
    
    /**
     * Removes all edges from the graph, then adds all edges from the list
     * edgesToAdd.
     * @param edgesToAdd List of edges to add back to graph.
     */
    public void setAllEdges(List<WEdge<VT>> edgesToAdd) {
        for (ArrayList<WEdge<VT>> edgeList : this.edges) {
            edgeList.clear();
        }
        for (WEdge<VT> e : edgesToAdd) {
            this.addEdge(e);
        }
    }
    
    /** Custom comparator to return negation of .compareTo ordering.
     *  This is used to create a min PQHeap.
     *  Overrides the compareTo method.
     *  @param <T> data type
     */
    public static class MinComp<T extends Comparable<? super T>>
        implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return -1 * o1.compareTo(o2);
        }
    }
    
    @Override()
    public List<WEdge<VT>> kruskals() {
        LinkedList<WEdge<VT>> mst = new LinkedList<WEdge<VT>>();
        
        PQHeap<WEdge<VT>> minEdges = 
                new PQHeap<WEdge<VT>>(new MinComp<WEdge<VT>>());
        minEdges.init(this.allEdges());
        
        Partition part = new Partition(this.numVerts());
        while (!minEdges.isEmpty() && mst.size() < this.numVerts() - 1) {
            WEdge<VT> e = minEdges.remove();
            int id1 = e.source().id();
            int id2 = e.end().id();
            if (part.find(id1) != part.find(id2)) {
                part.union(id1, id2);
                mst.addLast(e);
            }
        }
                
        return (List<WEdge<VT>>) mst;
    }

}

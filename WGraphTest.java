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
 * Project 4 - Part A - WGraph Junit Tests
 *
 *******************************************************************/

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/** Set of Junit tests for WGraph implementation.
 */
public class WGraphTest {
    
    /** WGraph of Characters.
     */
    private static WGraphP4<Character> gchar;
    
    /** WGraph of Integers.
     */
    private static WGraphP4<Integer> gint;
    
    /** Character GVertices to use with the WGraph.
     */
    private static GVertex<Character> v, u, x, y;
    
    /** Integer GVertices to use with the WGraph.
     */
    private static GVertex<Integer> a, b, c, d;
    
    /** Character WEdges to use with the WGraph.
     */
    private static WEdge<Character> e, f;
    
    /** Integer WEdges to use with the WGraph.
     */
    private static WEdge<Integer> w, z;
    
    /** Set up Graphs before each test.
     */
    @Before
    public void setupGraph() {
        // initialize Character and Integer graphs
        gchar = new WGraphP4<Character>();
        gint = new WGraphP4<Integer>();
        
        // initialize gvertices and wedges of Characters
        v = new GVertex<Character>('v', gchar.nextID());
        u = new GVertex<Character>('u', gchar.nextID());
        x = new GVertex<Character>('x', gchar.nextID());
        y = new GVertex<Character>('y', gchar.nextID());
        e = new WEdge<Character>(v, u, 2.0);
        f = new WEdge<Character>(v, x, 3.0);
        
        // initializes gvertices and wedges of Integers
        a = new GVertex<Integer>(1, gint.nextID());
        b = new GVertex<Integer>(2, gint.nextID());
        c = new GVertex<Integer>(3, gint.nextID());
        d = new GVertex<Integer>(4, gint.nextID());
        w = new WEdge<Integer>(a, b, 2.0);
        z = new WEdge<Integer>(a, c, 3.0);
    }
    
    /** Test the empty graph, depends on numEdges() and numVerts().
     */
    @Test
    public void testEmpty() {
        // check that WGraph has zero edges and vertices.
        assertEquals("should have zero edges", 0, gchar.numEdges(),
                     gint.numEdges());
        assertEquals("should have zero vertices", 0, gchar.numVerts(),
                     gint.numVerts());
    }
    
    /** Test adding vertices by giving data.
     */
    @Test
    public void testAddVertexWithData() {
        // initialize new graphs
        gchar = new WGraphP4<Character>();
        gint = new WGraphP4<Integer>();

        // check that WGraph has zero edges and vertices.
        assertEquals("should have zero edges", 0, gchar.numEdges(),
                     gint.numEdges());
        assertEquals("should have zero vertices", 0, gchar.numVerts(),
                     gint.numVerts());
        
        // check adding with other constructor
        assertEquals("vertex not already in graph", true, gchar.addVertex('p'));
        assertEquals("vertex not already in graph", true, gchar.addVertex('q'));
        assertEquals("vertex not already in graph", true, gint.addVertex(11));
        
        // check that number if vertices has changed
        assertEquals("number of vertices in char graph should change", 2,
                     gchar.numVerts());
        assertEquals("number of vertices in int graph should change", 1,
                     gint.numVerts());
        
        // check that there are still no edges
        assertEquals("no edges in either graph", gchar.numEdges(),
                     gint.numEdges());
        
    }

    /** Test adding vertices by giving a vertex to add.
     */
    @Test
    public void testAddVertexWithVertices() {
        // lists to hold vertices
        List<GVertex<Character>> cvertices =
                    new ArrayList<GVertex<Character>>();
        List<GVertex<Integer>> ivertices = new ArrayList<GVertex<Integer>>();
        
        // check that vertices not already in graph are added
        assertEquals("vertex not already in graph", true, gchar.addVertex(v));
        assertEquals("vertex not already in graph", true, gchar.addVertex(u));
        assertEquals("vertex not already in graph", true, gint.addVertex(a));
        
        // check that correct vertices are present
        cvertices = gchar.allVertices();
        ivertices = gint.allVertices();
        assertEquals("vertex should be in list", true, cvertices.contains(v));
        assertEquals("vertex should be in list", true, cvertices.contains(u));
        assertEquals("vertex should be in list", true, ivertices.contains(a));
        
        // check number of vertices
        assertEquals("char graph should have 2 vertices", 2, gchar.numVerts());
        assertEquals("int graph should have 1 vertex", 1, gint.numVerts());
        
        // check that no edges were made
        assertEquals("no edges in either graph", gchar.numEdges(),
                     gint.numEdges());
        
        // check that vertices already in graph are not added
        assertEquals("vertex already in graph", false, gchar.addVertex(v));
        assertEquals("vertex already in graph", false, gint.addVertex(a));
        
        // check that number of vertices is unchanged
        assertEquals("number of vertices in char graph should be the same", 2,
                     gchar.numVerts());
        assertEquals("number of vertices in int graph should be the same", 1,
                     gint.numVerts());
    }
    
    /** Test adding edges with both constructors.
     */
    @Test
    public void testAddEdge() {
        // lists to hold edges
        List<WEdge<Character>> cedges = new ArrayList<WEdge<Character>>();
        List<WEdge<Integer>> iedges = new ArrayList<WEdge<Integer>>();
        
        // lists to hold vertices
        List<GVertex<Character>> cvertices =
                            new ArrayList<GVertex<Character>>();
        List<GVertex<Integer>> ivertices = new ArrayList<GVertex<Integer>>();
        
        // check that new edges are added
        assertEquals("should insert edge", true, gchar.addEdge(e));
        assertEquals("should insert edge", true, gint.addEdge(w));
        
        // check that edges are present
        cedges = gchar.allEdges();
        iedges = gint.allEdges();
        assertEquals("edge should be in graph", true, cedges.contains(e));
        assertEquals("edge should be in graph", true, iedges.contains(w));
        
        // check other constructor
        assertEquals("should make new edge", true,
                     gchar.addEdge(v, x, 5.0));
        assertEquals("should make new edge", true,
                     gint.addEdge(a, c, 5.0));
        
        // check that vertices are present
        cvertices = gchar.allVertices();
        ivertices = gint.allVertices();
        assertEquals("vertex should be in list", true, cvertices.contains(v));
        assertEquals("vertex should be in list", true, cvertices.contains(u));
        assertEquals("vertex should be in list", true, cvertices.contains(x));
        assertEquals("vertex should be in list", true, ivertices.contains(a));
        assertEquals("vertex should be in list", true, ivertices.contains(b));
        assertEquals("vertex should be in list", true, ivertices.contains(c));
        
        // check that number of edges changed
        assertEquals("each graph should have two edges", 2, gchar.numEdges(),
                     gint.numEdges());
        
        // check that correct number of vertices have been added
        assertEquals("each graph should have three vertices", 3,
                     gchar.numEdges(), gint.numEdges());
        
        // check that redundant edges are not added (both constructors)
        assertEquals("should not make redundant edge", false,
                     gchar.addEdge(v, u, 5.0));
        assertEquals("should not make redundant edge", false, gchar.addEdge(f));
        assertEquals("should not make redundant edge", false,
                     gint.addEdge(a, b, 5.0));
        assertEquals("should not make redundant edge", false, gint.addEdge(z));
        
        // check that the number of edges and vertices have not changed
        assertEquals("each graph should have two edges", 2, gchar.numEdges(),
                     gint.numEdges());
        assertEquals("each graph should have three edges", 3,
                     gchar.numEdges(), gint.numEdges());
    }

    /** Test of edge deletion.
     */
    @Test
    public void testDeleteEdge() {
        // create edges
        WEdge<Character> h = new WEdge<Character>(u, y, 4.0);
        WEdge<Character> i = new WEdge<Character>(x, y, 8.0);
        WEdge<Integer> t = new WEdge<Integer>(b, d, 4.0);
        WEdge<Integer> s = new WEdge<Integer>(c, d, 8.0);
        
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);
        
        // add edges to graphs
        gchar.addEdge(e);
        gchar.addEdge(f);
        gchar.addEdge(h);
        gchar.addEdge(i);
        gint.addEdge(w);
        gint.addEdge(z);
        gint.addEdge(t);
        gint.addEdge(s);
        
        // check that number of edges is correct
        assertEquals("each graph should have four edges", 4,
                     gchar.numEdges(), gint.numEdges());
        
        // delete edges from graphs
        gchar.deleteEdge(v, u);
        gchar.deleteEdge(x, y);
        gint.deleteEdge(a, b);
        gint.deleteEdge(c, d);
        
        
        // check that correct edge was deleted
        assertEquals("[(0,2,3.0), (1,3,4.0), (2,0,3.0), (3,1,4.0)]",
                     gchar.allEdges().toString());
        assertEquals("[(0,2,3.0), (1,3,4.0), (2,0,3.0), (3,1,4.0)]",
                     gint.allEdges().toString());

        // check that adjacency has changed
        assertEquals("after edge delete, adjacency changed", false,
                     gchar.areAdjacent(v, u));
        assertEquals("after edge delete, adjacency changed", false,
                     gint.areAdjacent(a, b));
        
        // check that degree has changed
        assertEquals("degree of vertex should decrease", 1, gchar.degree(v),
                     gint.degree(a));
        assertEquals("degree of vertex should decrease", 1, gchar.degree(x),
                     gint.degree(c));
        
        // check that neighbors has changed
        assertEquals("neighbors shoud change", "[2]",
                     gchar.neighbors(v).toString());
        assertEquals("neighbors shoud change", "[2]",
                     gint.neighbors(a).toString());
        
        // check that edge number is decremented
        assertEquals("each graph should have two edges", 2,
                     gchar.numEdges(), gint.numEdges());
        
        // check that vertices are not deleted
        assertEquals("each graph should have four vertices", 4,
                     gchar.numVerts(), gint.numVerts());
    }
    
    /** Test allVertices method.
     */
    @Test
    public void testAllVertices() {
        // check that empty graph has empty vertices list
        assertEquals("should be empty", "[]", gchar.allVertices().toString());
        assertEquals("should be empty", "[]", gint.allVertices().toString());
        
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);
        
        // check that all vertices are present
        assertEquals("all vertices should be in graph", "[0, 1, 2, 3]",
                     gchar.allVertices().toString());
        assertEquals("all vertices should be in graph", "[0, 1, 2, 3]",
                     gint.allVertices().toString());
    }
    
    /** Test allEdges method.
     */
    @Test
    public void testAllEdges() {
        // check that empty graph has empty edges list
        assertEquals("should be empty", "[]", gchar.allEdges().toString());
        assertEquals("should be empty", "[]", gint.allEdges().toString());
        
        // create edges
        WEdge<Character> h = new WEdge<Character>(u, y, 4.0);
        WEdge<Character> i = new WEdge<Character>(x, y, 8.0);
        WEdge<Integer> t = new WEdge<Integer>(b, d, 4.0);
        WEdge<Integer> s = new WEdge<Integer>(c, d, 8.0);
        
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);
        
        // add edges to graphs
        gchar.addEdge(e);
        gchar.addEdge(f);
        gchar.addEdge(h);
        gchar.addEdge(i);
        gint.addEdge(w);
        gint.addEdge(z);
        gint.addEdge(t);
        gint.addEdge(s);
        
        // check list of edges
        assertEquals("[(0,1,2.0), (0,2,3.0), (1,0,2.0), (1,3,4.0), (2,0,3.0), "
                     + "(2,3,8.0), (3,1,4.0), (3,2,8.0)]",
                     gchar.allEdges().toString());
        assertEquals("[(0,1,2.0), (0,2,3.0), (1,0,2.0), (1,3,4.0), (2,0,3.0), "
                     + "(2,3,8.0), (3,1,4.0), (3,2,8.0)]",
                     gint.allEdges().toString());
        
        // delete edges
        gchar.deleteEdge(v, u);
        gchar.deleteEdge(v, x);
        gint.deleteEdge(a, b);
        gint.deleteEdge(a, c);
        
        // check that edge list has changed
        assertEquals("[(1,3,4.0), (2,3,8.0), (3,1,4.0), (3,2,8.0)]",
                     gchar.allEdges().toString());
        assertEquals("[(1,3,4.0), (2,3,8.0), (3,1,4.0), (3,2,8.0)]",
                     gint.allEdges().toString());
    }
    
    /** Test the areAdjacent method.
     */
    @Test
    public void testAdjacency() {
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);
        
        // check number of vertices before areAdjacent calls
        assertEquals("Each graph should have 4 vertices", 4, gchar.numVerts(),
                     gint.numVerts());
        
        // check that in graphs without edges, no vertices are adjacent
        assertEquals("should not be adjacent", false,
                     gchar.areAdjacent(u, v));
        assertEquals("should not be adjacent", false,
                     gchar.areAdjacent(u, x));
        assertEquals("should not be adjacent", false,
                     gchar.areAdjacent(u, y));
        assertEquals("should not be adjacent", false,
                     gchar.areAdjacent(v, x));
        assertEquals("should not be adjacent", false,
                     gchar.areAdjacent(v, y));
        assertEquals("should not be adjacent", false,
                     gchar.areAdjacent(x, y));
        
        assertEquals("should not be adjacent", false,
                     gint.areAdjacent(a, b));
        assertEquals("should not be adjacent", false,
                     gint.areAdjacent(a, c));
        assertEquals("should not be adjacent", false,
                     gint.areAdjacent(a, d));
        assertEquals("should not be adjacent", false,
                     gint.areAdjacent(b, c));
        assertEquals("should not be adjacent", false,
                     gint.areAdjacent(b, d));
        assertEquals("should not be adjacent", false,
                     gint.areAdjacent(c, d));
    
        gchar.addEdge(e);
        gchar.addEdge(f);
        assertEquals("vertices with edge should be adjacent", true,
                     gchar.areAdjacent(u, v));
        assertEquals("vertices with edge should be adjacent", true,
                     gchar.areAdjacent(v, u));
        assertEquals("vertices with edge should be adjacent", true,
                     gchar.areAdjacent(v, x));
        assertEquals("without edge, vertices should not be adjacent", false,
                     gchar.areAdjacent(x, u));
        assertEquals("without edge, vertices should not be adjacent", false,
                     gchar.areAdjacent(v, y));
        
        // check number of vertices hasn't changed
        assertEquals("Each graph should have 4 vertices", 4, gchar.numVerts(),
                     gint.numVerts());
    }

    /** Test of the areIncident method.
     */
    @Test
    public void testIncidence() {
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);
        
        // add one edge to each graph
        gchar.addEdge(e);
        gint.addEdge(w);
        
        // check that edges are incident to correct vertices
        assertEquals("should not be incident to edge", false,
                     gchar.areIncident(e, x));
        assertEquals("should not be incident to edge", false,
                     gchar.areIncident(e, y));
        assertEquals("should be incident to edge", true,
                     gchar.areIncident(e, v));
        assertEquals("should be incident to edge", true,
                     gchar.areIncident(e, u));
        assertEquals("should not be incident to edge", false,
                     gint.areIncident(w, c));
        assertEquals("should not be incident to edge", false,
                     gint.areIncident(w, d));
        assertEquals("should be incident to edge", true,
                     gint.areIncident(w, a));
        assertEquals("should be incident to edge", true,
                     gint.areIncident(w, b));
        
        // add another edge
        gchar.addEdge(f);
        gint.addEdge(z);
        
        // check incidence of new edge
        assertEquals("vertex should not be incident to edge", true,
                     gchar.areIncident(f, x));
        assertEquals("vertex should not be incident to edge", false,
                     gchar.areIncident(f, u));
        assertEquals("vertex should be incident to edge", true,
                     gint.areIncident(z, c));
        assertEquals("vertex should not be incident to edge", false,
                     gint.areIncident(z, b));
        
        // check that there are the correct number of edges and vertices
        assertEquals("number of vertices should not change", 4,
                     gchar.numVerts(), gint.numVerts());
        assertEquals("number of edges should not change", 2, gchar.numEdges(),
                     gint.numVerts());
    }

    /** Test of the degree method.
     */
    @Test
    public void testDegree() {
        // add vertices to character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        
        // add vertices to integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        
        // check that degrees of edgeless vertices is zero
        assertEquals("no edges, degree should be zero", 0, gchar.degree(v),
                     gint.degree(a));
        
        // add an edge to each graph
        gchar.addEdge(e);
        gint.addEdge(w);
        
        // check that degree has changed
        assertEquals("one edge, degree should be one", 1, gchar.degree(v),
                     gint.degree(a));
        
        // add another edge to each
        gchar.addEdge(f);
        gint.addEdge(z);
        
        // check degrees again
        assertEquals("two edges, degree two", 2, gchar.degree(v),
                     gint.degree(a));
        assertEquals("one edge, degree one", 1, gchar.degree(u),
                     gint.degree(b));
        assertEquals("no edeges, degree zero", 0, gchar.degree(x),
                     gint.degree(c));
        
        // delete an edge
        gchar.deleteEdge(v, x);
        gint.deleteEdge(a, c);
        
        // check degree of vertices
        assertEquals("after deletion degree should be one", 1, gchar.degree(v),
                     gchar.degree(u));
        assertEquals("after deletion degree should be one", 1, gint.degree(a),
                     gint.degree(b));
        assertEquals("after deletion degree should be zero", 0, gchar.degree(x),
                     gint.degree(c));
    }
    
    /** Test of the neighbors method.
     */
    @Test
    public void testNeighbors() {
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);
        
        // check that the neighbors() list is empty
        assertEquals("[]", gchar.neighbors(v).toString());
        
        // add one edge to each graph
        gchar.addEdge(e);
        gint.addEdge(w);
        
        //        System.out.println(gchar.neighbors(v).toString());
        // check neighbor lists of Character graph
        assertEquals("[1]", gchar.neighbors(v).toString());
        assertEquals("[0]", gchar.neighbors(u).toString());
        gchar.addEdge(f);
        assertEquals("[1, 2]", gchar.neighbors(v).toString());
        assertEquals("[0]", gchar.neighbors(u).toString());
        assertEquals("[0]", gchar.neighbors(x).toString());
        assertEquals("[]", gchar.neighbors(y).toString());
        
        // check neighbor list of Integer graph
        assertEquals("[1]", gint.neighbors(a).toString());
        assertEquals("[0]", gint.neighbors(b).toString());
        gint.addEdge(z);
        assertEquals("[1, 2]", gint.neighbors(a).toString());
        assertEquals("[0]", gint.neighbors(b).toString());
        assertEquals("[0]", gint.neighbors(c).toString());
        assertEquals("[]", gint.neighbors(d).toString());
    }
    
    /** Test of depth first search.
     */
    @Test
    public void testDepthFirstSearch() {
        // declare edges
        WEdge<Character> h = new WEdge<Character>(u, y, 4.0);
        WEdge<Integer> t = new WEdge<Integer>(b, d, 4.0);
        
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);

        // add edges to graphs
        gchar.addEdge(e);
        gchar.addEdge(f);
        gchar.addEdge(h);
        gint.addEdge(w);
        gint.addEdge(z);
        gint.addEdge(t);
        
        // check result of depth first search
        assertEquals("[0, 2, 1, 3]", gchar.depthFirst(v).toString());
        assertEquals("[0, 2, 1, 3]", gint.depthFirst(a).toString());
        
        // check that edges and vertices are not changed
        assertEquals("should be no change in vertices", 4, gchar.numVerts(),
                     gint.numVerts());
        assertEquals("should be no change in edges", 4, gchar.numEdges(),
                     gint.numEdges());
        
        // reconfigure graphs
        gchar.deleteEdge(v, x);
        gint.deleteEdge(a, c);
        
        // check new depth first searches
        assertEquals("[0, 1, 3]", gchar.depthFirst(v).toString());
        assertEquals("[0, 1, 3]", gint.depthFirst(a).toString());
        assertEquals("[2]", gchar.depthFirst(x).toString());
        assertEquals("[2]", gint.depthFirst(c).toString());
    }
    
    /** Test Kruskal's algorithm method.
     */
    @Test
    public void testKruskals() {
        // create vetices
        GVertex<Integer> evert = new GVertex<Integer>(5, gchar.nextID());
        
        // create edges
        WEdge<Character> h = new WEdge<Character>(u, y, 4.0);
        WEdge<Character> i = new WEdge<Character>(x, y, 8.0);
        WEdge<Integer> t = new WEdge<Integer>(b, d, 4.0);
        WEdge<Integer> s = new WEdge<Integer>(c, d, 8.0);
        
        
        // add vertices to Character graph
        gchar.addVertex(v);
        gchar.addVertex(u);
        gchar.addVertex(x);
        gchar.addVertex(y);
        
        // add vertices to Integer graph
        gint.addVertex(a);
        gint.addVertex(b);
        gint.addVertex(c);
        gint.addVertex(d);
        
        // add edges to graphs
        gchar.addEdge(e);
        gchar.addEdge(f);
        gchar.addEdge(h);
        gchar.addEdge(i);
        gint.addEdge(w);
        gint.addEdge(z);
        gint.addEdge(t);
        gint.addEdge(s);
        
        // check Kruskal's algorithm
        assertEquals("[(0,1,2.0), (0,2,3.0), (1,3,4.0)]",
                     gchar.kruskals().toString());
        assertEquals("[(0,1,2.0), (0,2,3.0), (1,3,4.0)]",
                     gint.kruskals().toString());
        
        // check that edges and vertices are not changed
        assertEquals("should be no change in vertices", 4, gchar.numVerts(),
                     gint.numVerts());
        assertEquals("should be no change in edges", 4, gchar.numEdges(),
                     gint.numEdges());
        
        // reconfigure graph
        gint.addEdge(b, c, 5.0);
        gint.addEdge(c, evert, 1.0);
        gint.addEdge(d, evert, 1.0);
        
        // check kruskals of new graph
        assertEquals("[(2,4,1.0), (3,4,1.0), (0,1,2.0), (0,2,3.0)]",
                     gint.kruskals().toString());
        
        
    }
    
}

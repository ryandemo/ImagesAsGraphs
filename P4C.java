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
 * Project 4 - Part C
 *
 *******************************************************************/

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.List;
import java.util.LinkedList;


/**
 * Project 4 Part C.
 */
public final class P4C {

    /** Hexadecimal value of one byte. Used for alpha byte.
     */
    private static final int BYTE_MASK = 0xFF000000;
    
    /** Unused private constructor.
     */
    private P4C() {
    }

    /** Convert an image to a graph of Pixels with edges between
     *  north, south, east and west neighboring pixels.
     *  @param image the image to convert
     *  @param pd the distance object for pixels
     *  @return the graph that was created
     */
    static WGraph<Pixel> imageToGraph(BufferedImage image, Distance<Pixel> pd) {
        WGraph<Pixel> graph = new WGraphP4<Pixel>();

        // See README for explanation of warnings
        GVertex<Pixel>[][] vtcs =
                new GVertex[image.getWidth()][image.getHeight()];
        
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel p = new Pixel(y, x, image.getRGB(x, y));
                GVertex<Pixel> v = new GVertex<Pixel>(p, graph.nextID());
                vtcs[x][y] = v;
                
                if (y > 0) {
                    GVertex<Pixel> upper = vtcs[x][y - 1];
                    double dist = pd.distance(v.getData(), upper.getData());
                    graph.addEdge(v, upper, dist);
                }
                
                if (x > 0) {
                    GVertex<Pixel> left = vtcs[x - 1][y];
                    double dist = pd.distance(v.getData(), left.getData());
                    graph.addEdge(v, left, dist);
                }
                
                if (x == 0 && y == 0) {
                    graph.addVertex(v);
                }
            }
        }

        return graph;
    }

    /**
     * Returns the difference between each element of two arrays, where
     * one has max values and the second has min values.
     * @param min Minimum array
     * @param max Maximum array
     * @return Difference between each element of max/min arrays.
     */
    private static int[] difference(int[] min, int[] max) {
        final int rgbArraySize = 3;
        int[] d = new int[rgbArraySize];
        d[0] = max[0] - min[0];
        d[1] = max[1] - min[1];
        d[2] = max[2] - min[2];
        return d;
    }
    
    /**
     * Finds the min of each element of two RGB arrays, returning
     * the min as a new RGB array.
     * @param p1 First pixel RGB array
     * @param p2 Second pixel RGB array
     * @return Min RGB array
     */
    private static int[] unionMin(int[] p1, int[] p2) {
        final int rgbArraySize = 3;
        int[] u = new int[rgbArraySize];
        u[0] = Math.min(p1[0], p2[0]);
        u[1] = Math.min(p1[1], p2[1]);
        u[2] = Math.min(p1[2], p2[2]);
        return u;
    }
    
    /**
     * Finds the max of each element of two RGB arrays, returning
     * the max as a new RGB array.
     * @param p1 First pixel RGB array
     * @param p2 Second pixel RGB array
     * @return Max RGB array
     */
    private static int[] unionMax(int[] p1, int[] p2) {
        final int rgbArraySize = 3;
        int[] u = new int[rgbArraySize];
        u[0] = Math.max(p1[0], p2[0]);
        u[1] = Math.max(p1[1], p2[1]);
        u[2] = Math.max(p1[2], p2[2]);
        return u;
    }
    
    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @param g the graph to segment
     *  @param kvalue the value to use for k in the merge test
     *  @return a list of the edges in the minimum spanning forest
     */
    static List<WEdge<Pixel>> segmenter(WGraph<Pixel> g, double kvalue) {
        final int rgbArraySize = 3;
        
        LinkedList<WEdge<Pixel>> mst = new LinkedList<WEdge<Pixel>>();
        
        PQHeap<WEdge<Pixel>> edges = 
                new PQHeap<WEdge<Pixel>>(new WGraphP4.MinComp<WEdge<Pixel>>());
        edges.init(g.allEdges());
        
        Pixel[] mins = new Pixel[g.numVerts()];
        Pixel[] maxes = new Pixel[g.numVerts()];
        
        Partition part = new Partition(g.numVerts());
        while (!edges.isEmpty() && mst.size() < g.numVerts() - 1) {
            WEdge<Pixel> e = edges.remove();
            int id1 = e.source().id();
            int id2 = e.end().id();
            int index1 = part.find(id1);
            int index2 = part.find(id2);
            if (index1 != index2) {
                int[] min1 = new int[rgbArraySize];
                int[] max1 = new int[rgbArraySize];
                int[] min2 = new int[rgbArraySize];
                int[] max2 = new int[rgbArraySize];
                if (mins[index1] == null) {
                    min1[0] = e.source().getData().red();
                    min1[1] = e.source().getData().green();
                    min1[2] = e.source().getData().blue();
           
                    max1[0] = e.source().getData().red();
                    max1[1] = e.source().getData().green();
                    max1[2] = e.source().getData().blue();
                } else {
                    min1[0] = mins[index1].red();
                    min1[1] = mins[index1].green();
                    min1[2] = mins[index1].blue();
                    
                    max1[0] = maxes[index1].red();
                    max1[1] = maxes[index1].green();
                    max1[2] = maxes[index1].blue();
                }
                if (mins[index2] == null) {
                    min2[0] = e.end().getData().red();
                    min2[1] = e.end().getData().green();
                    min2[2] = e.end().getData().blue();
           
                    max2[0] = e.end().getData().red();
                    max2[1] = e.end().getData().green();
                    max2[2] = e.end().getData().blue();
                } else {
                    min2[0] = mins[index2].red();
                    min2[1] = mins[index2].green();
                    min2[2] = mins[index2].blue();
                    
                    max2[0] = maxes[index2].red();
                    max2[1] = maxes[index2].green();
                    max2[2] = maxes[index2].blue();
                }

                int[] unionMin = unionMin(min1, min2);
                int[] unionMax = unionMax(max1, max2);

                int[] diffAuB = difference(unionMin, unionMax);
                int[] diffA = difference(min1, max1);
                int[] diffB = difference(min2, max2);
                
                int sizeAB = part.getWeight(id1) + part.getWeight(id2);
                
                boolean redCondition = diffAuB[0] 
                        <= (Math.min(diffA[0], diffB[0]) 
                        + kvalue / sizeAB);
                boolean greenCondition = diffAuB[1] 
                        <= (Math.min(diffA[1], diffB[1]) 
                        + kvalue / sizeAB);
                boolean blueCondition = diffAuB[2] 
                        <= (Math.min(diffA[2], diffB[2]) 
                        + kvalue / sizeAB);
                
                if (redCondition && greenCondition && blueCondition) {
                    part.union(id1, id2);
                    mst.addLast(e);
                    
                    Pixel unionMinPixel = new Pixel(0, 0, 
                            unionMin[0], unionMin[1], unionMin[2]);
                    Pixel unionMaxPixel = new Pixel(0, 0, 
                            unionMax[0], unionMax[1], unionMax[2]);
                    mins[part.find(id1)] = unionMinPixel;
                    maxes[part.find(id1)] = unionMaxPixel;
                }
                
                // simple difference:
                /*int diffR = Math.abs(e.source().getData().red()
                 * - e.end().getData().red());
                int diffG = Math.abs(e.source().getData().green()
                - e.end().getData().green());
                int diffB = Math.abs(e.source().getData().blue()
                - e.end().getData().blue());
                
                if (diffR < kvalue && diffG < kvalue && diffB < kvalue) {
                    vertices.union(e.source().id(), e.end().id());
                    mst.addLast(e);
                }*/
            }              
            
        }
                
        return (List<WEdge<Pixel>>) mst;
        
    }
    
    /**
     * Returns a List of connected components of a graph, where each component
     * is a list of Pixel objects. Uses depth-first search.
     * @param g Graph to get components from
     * @return List of connected components
     */
    private static List<LinkedList<Pixel>> 
                    connectedComponents(WGraphP4<Pixel> g) {
        List<LinkedList<Pixel>> components =
                new LinkedList<LinkedList<Pixel>>();
        boolean[] visited = new boolean[g.numVerts()];
        for (GVertex<Pixel> v : g.allVertices()) {
            if (!visited[v.id()]) {
                LinkedList<Pixel> reaches = new LinkedList<Pixel>();
                LinkedList<GVertex<Pixel>> stack =
                        new LinkedList<GVertex<Pixel>>();
                stack.addFirst(v);
                visited[v.id()] = true;
                while (!stack.isEmpty()) {
                    GVertex<Pixel> v2 = stack.removeFirst();
                    reaches.add(v2.getData());
                    for (GVertex<Pixel> u: g.neighbors(v2)) {
                        if (!visited[u.id()]) {
                            visited[u.id()] = true;
                            stack.addFirst(u);
                        }
                    }
                }
                components.add(reaches);
            }
        }
        return components;
    }

    /**
     * Main entry point to the program.
     * @param args Filename and K value
     */
    public static void main(String[] args) {

        boolean inerror = false;
        
        final int gray = 0xFF202020;

        try {
            // the line that reads the image file
            BufferedImage image = ImageIO.read(new File(args[0]));
            WGraphP4<Pixel> g = (WGraphP4<Pixel>) 
                    imageToGraph(image, new PixelDistance());
            System.out.println("Created graph from image.");
            
            List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));
            System.out.println("Segmented graph.");
            
            g.setAllEdges(res);
            System.out.println("Removed edges to separate segments.");
            
            List<LinkedList<Pixel>> components = 
                    connectedComponents(g);
            System.out.println("Found connected components.");
            
            int filenameIndex = 1;
            
            for (LinkedList<Pixel> c : components) {
                for (int i = 0; i < image.getHeight(); i++) {
                    for (int j = 0; j < image.getWidth(); j++) {
                        image.setRGB(j, i, gray);
                    }
                }
                
                for (Pixel p : c) {
                    int rgba = BYTE_MASK | p.value(); //add alpha bits
                    image.setRGB(p.column(), p.row(), rgba);
                }
                
                File f = new File("output" + filenameIndex + ".png");
                ImageIO.write(image, "png", f);
                System.out.println("Created file: " + filenameIndex);
                filenameIndex++;
            }
            
        } catch (IOException e) {
            System.out.println("Missing file!");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.err.println("Insufficient command line args, "
                    + "should be 'java P4C [imagefile] [k value]'...");
        }
        if (inerror) {
            System.err.println("Exiting...");
            System.exit(1);
        }
    }

}

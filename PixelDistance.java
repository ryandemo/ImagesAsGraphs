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
 * Project 4 - Part C - Images as Graphs
 *
 *******************************************************************/

/**
 * Used to calculate distance between two pixels.
 */
public class PixelDistance implements Distance<Pixel> {
    
    /**
     * Constructor does nothing.
     */
    public PixelDistance() {
    }

    @Override
    public double distance(Pixel one, Pixel two) {
        double d1 = Math.pow((one.red() - two.red()), 2);
        double d2 = Math.pow((one.green() - two.green()), 2);
        double d3 = Math.pow((one.blue() - two.blue()), 2);
        return Math.sqrt(d1 + d2 + d3);
    }

}

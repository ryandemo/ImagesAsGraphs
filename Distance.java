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
 * Interface for distance calculation between two objects.
 * @param <T> Object type
 */
public interface Distance<T> {
    /**
     * Returns the distance between objects one and two.
     * @param one First object
     * @param two Second object
     * @return Distance
     */
    double distance(T one, T two);
}

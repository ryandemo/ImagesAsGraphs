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


/** Alpha-RGB pixel data type implementation for P4C.
 */
public class Pixel {

    /** Bits in one byte for bit shifts.
     */
    private static final int BYTE_BITS = 8;
    
    /** Integer value of one byte.
     */
    private static final int BYTE_MASK = 255;
    
    /** Integer representation of ARGB value.
     */
    public int value;
    
    /** Row of pixel's position.
     */
    private int row;
    
    /** Column of pixel's position.
     */
    private int column;
    
    
    /**
     * Construct new pixel.
     * @param r Row coordinate
     * @param c Column coordinate
     * @param val Integer RGB value
     */
    public Pixel(int r, int c, int val) {
        
        //prevent negative positions
        if (r >= 0) {
            this.row = r;
        } else {
            this.row = 0;
        }
        
        if (c >= 0) {
            this.column = c;
        } else {
            this.column = 0;
        }
        
        this.value = val;
    }
    
    /**
     * Construct new pixel.
     * @param r Row coordinate
     * @param c Column coordinate
     * @param red Red value
     * @param green Green value
     * @param blue Blue value
     */
    public Pixel(int r, int c, int red, int green, int blue) {
        
        //prevent negative positions
        if (r >= 0) {
            this.row = r;
        } else {
            this.row = 0;
        }
        
        if (c >= 0) {
            this.column = c;
        } else {
            this.column = 0;
        }
        
        // start with red value
        this.value = red;
        // shift one byte to the left and insert green value
        this.value = (this.value << BYTE_BITS) + green;
        // shift another byte to the left and insert blue value
        this.value = (this.value << BYTE_BITS) + blue;
        
        // bitwise zero-fill shift 8 to the right for expected
        // alpha byte is not necessary because integers are 32 bits
        // so the preceding 8 bits are 0
    }
    
    /** Return row of pixel's position.
     * @return pixel's row
     */
    public int row() {
        return this.row;
    }
    
    /** Return column of pixel's position.
     * @return pixel's column
     */
    public int column() {
        return this.column;
    }
    
    /** Calculates and returns the red component
     * of pixel's value.
     * @return integer representation of red
     */
    public int red() {
        return (this.value >> 2 * BYTE_BITS) & BYTE_MASK;
    }
    
    /** Calculates and returns the green component
     * of pixel's value.
     * @return integer representation of green
     */
    public int green() {
        return (this.value >> BYTE_BITS) & BYTE_MASK;
    }
    
    /** Calculates and returns the blue component
     * of pixel's value.
     * @return integer representation of blue
     */
    public int blue() {
        return this.value & BYTE_MASK;
    }

    /** Returns the pixel value.
     * @return integer value of pixel
     */
    public int value() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return "{(" + this.column + "," + this.row + ") " + this.value + "}";
    }
}

/**
 * File:   Honey_Hive.java
 * Date:   Nov 25, 2018
 * 
 */
package main.java.Factory;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class HoneyHive extends Abstract_Hive{

    /**
     * Constructor
     * @param x - xPos
     * @param y - yPos
     */
    public HoneyHive(int x, int y) {
        super(Type.HONEY, x, y);
    }

}

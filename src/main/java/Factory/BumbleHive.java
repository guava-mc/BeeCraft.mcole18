/**
 * File:   BumbleHive.java
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
public class BumbleHive extends Abstract_Hive{

    /**
     * Constructor
     * @param x - xPos
     * @param y - yPos
     */
    public BumbleHive(int x, int y) {
        super(Type.BUMBLE, x, y);
    }

}

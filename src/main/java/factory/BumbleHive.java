/**
 * File:   BumbleHive.java
 * Date:   Nov 25, 2018
 * 
 */

package main.java.factory;

/**
 * Description: Bumble bee hive.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class BumbleHive extends AbstractHive {

    /**
     * Constructor.
     * @param x - xPos
     * @param y - yPos
     */
    public BumbleHive(int x, int y) {
        super(Type.BUMBLE, x, y);
    }

}

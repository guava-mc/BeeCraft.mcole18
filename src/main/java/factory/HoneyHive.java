/**
 * File:   Honey_Hive.java
 * Date:   Nov 25, 2018
 * 
 */

package main.java.factory;

/**
 * Description: HoneyHive class.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class HoneyHive extends AbstractHive {

    /**
     * Constructor.
     * @param x - xPos
     * @param y - yPos
     */
    public HoneyHive(int x, int y) {
        super(Type.HONEY, x, y);
    }

}

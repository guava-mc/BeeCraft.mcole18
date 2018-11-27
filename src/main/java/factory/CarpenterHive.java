/**
 * File:   CarpenterHive.java
 * Date:   Nov 25, 2018
 * 
 */

package main.java.factory;

/**
 * Description: CarpenterHive class.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class CarpenterHive extends AbstractHive {

    /**
     * Constructor.
     * @param x - xPos
     * @param y - yPos
     */
    public CarpenterHive(int x, int y) {
        super(Type.CARPENTER, x, y);
    }

}

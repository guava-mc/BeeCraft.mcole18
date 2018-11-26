/**
 * File:   KillerHive.java
 * Date:   Nov 25, 2018
 * 
 */
package main.java.factory;

import main.java.flyweight.BeeEnums;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class KillerHive extends AbstractHive {

    /**
     * Constructor
     * @param x - xPos
     * @param y - yPos
     */
    public KillerHive(int x, int y) {
        super(Type.KILLER, x, y);
    }
}

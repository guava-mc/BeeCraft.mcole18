/**
 * File:   KillerHive.java
 * Date:   Nov 25, 2018
 * 
 */
package main.java.Factory;

import main.java.Flyweight.Bee_Enums;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class KillerHive extends Beehive_Factory {

    /**
     * Constructor
     * @param t
     */
    public KillerHive(int x, int y) {
        super(Type.KILLER, x, y);
        // TODO Auto-generated constructor stub
    }

}

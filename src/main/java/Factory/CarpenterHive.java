/**
 * File:   CarpenterHive.java
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
public class CarpenterHive extends Abstract_Hive{

    /**
     * Constructor
     * @param x - xPos
     * @param y - yPos
     */
    public CarpenterHive(int x, int y) {
        super(Type.CARPENTER, x, y);
    }

}

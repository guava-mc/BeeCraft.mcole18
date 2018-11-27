/**
 * File:   Bee_Enums.java
 * Date:   Nov 23, 2018
 * 
 */

package main.java.flyweight;

/**
 * Description: BeeEnums interface.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public interface BeeEnums {
    enum Task {
        HARVESTING, FIGHTING, BUILDING, RESTING, IDLE
    }
    
    enum Type {
        CARPENTER, KILLER, BUMBLE, HONEY
    } 
}

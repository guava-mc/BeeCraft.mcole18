/**
 * File:   Hive_Factory.java
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
public class Hive_Factory implements Bee_Enums{
    
    public Abstract_Hive makeHive(Type t, int x, int y) {
        if(t == Type.KILLER) {
            return new KillerHive(x,y);
        }
        if(t == Type.CARPENTER) {
            return new CarpenterHive(x,y);
        }
        if(t == Type.BUMBLE) {
            return new BumbleHive(x,y);
        }
        if(t == Type.HONEY) {
            return new HoneyHive(x,y);
        }
        
        return null;
        
        
    }

}

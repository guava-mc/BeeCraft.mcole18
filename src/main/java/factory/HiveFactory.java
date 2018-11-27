/**
 * File:   Hive_Factory.java
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
public class HiveFactory implements BeeEnums{
    
    public AbstractHive makeHive(Type t, int x, int y) {
        System.out.println("new Hive created of type: " + t);
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

/**
 * File:   BeeFactory.java
 * Date:   Nov 23, 2018
 * 
 */
package main.java.Flyweight;

import java.util.HashMap;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class BeeFactory implements Bee_Enums{
    
    private static int killerBeeCount = 0;
    private static int carpenterBeeCount = 0;
    private static int bumbleBeeCount = 0;
    private static int honeyBeeCount = 0;
    
    private static HashMap <Type, Bee_Flyweight> beeMap = new HashMap<>();
    
    public static Bee_Flyweight getBee(Type t) {
        
        setBeeCount(t);
        
        Bee_Flyweight bee = null;
        
        //if bee of Type t has already been created return reference
        if(beeMap.containsKey(t)) {
            bee = beeMap.get(t);
        } else {
          bee = new Bee_Base(t);       
          beeMap.put(t, bee);
        }
        
        return bee;
    }
    
    private static void setBeeCount(Type t) {
        switch (t) {
        case KILLER:
            killerBeeCount++;
            break;
        case BUMBLE:
            bumbleBeeCount++;
            break;
        case CARPENTER:
            carpenterBeeCount++;
            break;
        case HONEY:
            honeyBeeCount++;
            break;
        default:
            break;
        }
    }
    
    static int getBeeCount(Type t) {
        switch (t) {
        case KILLER:
            return killerBeeCount;
        case BUMBLE:
            return bumbleBeeCount;
        case CARPENTER:
            return carpenterBeeCount;
        case HONEY:
            return honeyBeeCount;
        default:
            break;
        }
        return -1;
    }

}

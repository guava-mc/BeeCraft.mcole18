/**
 * File:   Apiary.java
 * Date:   Nov 22, 2018
 * 
 */

package main.java.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import main.java.factory.AbstractHive;

/**
 * Description: Apiary for bees/beehive factions. 
 * Showcases use of Singleton Design Patter.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class Apiary {
    
    private static final int _MAP_WIDTH = 500;
    private static final int _MAP_HEIGHT = 500;
    
    private static Apiary _instance;
    
   
    private static boolean mutex = false;
    private static int ticks;
    private static HashMap<String,AbstractHive> hives;
    
    private Apiary() {
        setTicks(0);
        hives = new HashMap<>();
    }
    
    /**
     * Description: Used to initialize an Apiary singleton.
     * If already exists will return the singleton.
     * 
     * @return Apiary Singleton
     */
    public static Apiary start() {
        
        //if resource available generate singleton
        //else return singleton
        if (!mutex && _instance == null) {
            _instance = new Apiary();
            mutex = true;
        }
        return _instance;
    }
    
    /**
     * Description: updates entire Apiary to next tick increment
     * send tick information to hives so they can act accordingly.
     * 
     * @param tick - number of ticks
     */
    public static void update(int tick) {
        setTicks(tick);
        ArrayList<AbstractHive> destroyedHive = new ArrayList<>();
        for (Entry<String, AbstractHive> hiveEntry : hives.entrySet()) {
            if (!hiveEntry.getValue().isAlive()) {
                destroyedHive.add(hiveEntry.getValue());
            }
        }
        for (AbstractHive hive : destroyedHive) {
            hives.remove(hive.getHiveId());
        }
    }
    
    public static void update() {
        update(1);
    }
    
    public static int getTicks() {
        return ticks;
    }
    
    private static void setTicks(int tick) {
        if (tick < 1) {
            ticks = 0;
        } else {
            ticks += tick;
        }
    }
    
    public HashMap<String, AbstractHive> getHives() {
        return hives;
    }
    
    
    
}

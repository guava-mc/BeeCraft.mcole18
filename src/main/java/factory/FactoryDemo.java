/**
 * File:   FactoryDemo.java
 * Date:   Nov 22, 2018
 * 
 */

package main.java.factory;

import java.util.ArrayList;
import java.util.Iterator;

import main.java.flyweight.BeeEnums.Type;

/**
 * Description: Create a hive factory which can create different hives
 * and use abstract methods to implement hive specific behaviors from
 * single interface.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class FactoryDemo {
       
    /**
     * Description: FactoryDemo Main.
     * 
     * @param args - main args
     */
    public static void main(String[] args) {
        
        System.out.print("=====================================================\n"
                + " Demonstrating Factory Design Pattern with Hive_Factory.\n"
                + "=====================================================\n\n");
        
        
        System.out.println("Creating a hive of each bee type using single "
                + "factory method makeHive(Type t)");
        System.out.println();
        
        HiveFactory factory = new HiveFactory();
        ArrayList<AbstractHive> hives = new ArrayList<>();
               
        hives.add(factory.makeHive(Type.KILLER,0,0));
        hives.add(factory.makeHive(Type.BUMBLE,3,3));
        hives.add(factory.makeHive(Type.HONEY,4,4));
        hives.add(factory.makeHive(Type.CARPENTER,1,1));
        System.out.println();
        
        System.out.println("Creating another hive of each bee type using single "
                + "factory method makeHive(Type t) \nto validate"
                + "each object is unique.");
        System.out.println();
        
        hives.add(factory.makeHive(Type.KILLER,0,0));
        hives.add(factory.makeHive(Type.BUMBLE,3,3));
        hives.add(factory.makeHive(Type.HONEY,4,4));
        hives.add(factory.makeHive(Type.CARPENTER,1,1));
        
        System.out.println("Total hives created: " + hives.size() + "\n");
        
        System.out.println();
        
    }
}

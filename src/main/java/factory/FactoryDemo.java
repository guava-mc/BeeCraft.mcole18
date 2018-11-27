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
        
//        System.out.println("Demonstrating uniqueness of each hive.\n");
//        
//        hives.get(0).attack(hives.get(1), 2);
//        hives.get(1).setEnemyAttacking(hives.get(0));
//        
//        System.out.println();
//        hives.get(2).makeBees(3);
//        hives.get(0).makeBees(20);
//        hives.get(3).makeRooms(3, 2);
//        hives.get(4).makeHarvesters(2);
//        
//        System.out.println();
//        System.out.println("Running update with simulated ticks...");
//        for (Abstract_Hive h : hives) {
//            h.update(500);
//        }
//
//        hives.get(0).makeRooms(20, 3);
//        hives.get(0).makeBees(20);
//
//        for (Abstract_Hive h : hives) {
//            h.update(500);
//        }
//
//        hives.get(0).makeBees(20);
//
//        for (Abstract_Hive h : hives) {
//            h.update(500);
//        }
//        
//        hives.get(0).makeRooms(20, 3);
//        
//        
//
//        
//        for (Abstract_Hive h : hives) {
//            h.update(500);
//        }
//        
//        hives.get(0).makeBees(20);
//        
//        for (Abstract_Hive h : hives) {
//            h.update(500);
//        }
//        
//        System.out.println();
//        hives.get(0).attack(hives.get(1), 30);
//        hives.get(1).setEnemyAttacking(hives.get(0));
//        System.out.println();
//        
//        System.out.println("Hive 1 stats before attack: ");
//        System.out.println(hives.get(0).toString());
//        
//        for (Abstract_Hive h : hives) {
//            h.update(500);
//        }
//        
//        System.out.println("Hive 1 stats after attack: ");
//        System.out.println(hives.get(0).toString());
//        
//        Hive_Factory factory = new Hive_Factory();
//        ArrayList<Abstract_Hive> hives = new ArrayList<>();
//       
//        hives.add(factory.makeHive(Type.KILLER,0,0));
//        hives.add(factory.makeHive(Type.KILLER,3,3));
//        hives.add(factory.makeHive(Type.KILLER,4,4));
//        hives.add(factory.makeHive(Type.KILLER,1,1));
//        
//        hives.add(factory.makeHive(Type.HONEY, 2,2));
//        hives.add(factory.makeHive(Type.BUMBLE, 50, 50));
//        
//        System.out.println();
//        System.out.println(hives.get(1).toString());
//        
//        hives.get(1).makeBees(5);
//        hives.get(1).makeRooms(5, 2);
//        
//        //this will be through mediator later
//        for (Abstract_Hive h : hives) {
//            h.update(100);
//        }
//        
//        System.out.println();
//        System.out.println(hives.get(1).toString());
//        
//        hives.get(1).attack(hives.get(4), 6);
//        //hives.get(4).defend(hives.get(1));
//              
//        System.out.println();
//        System.out.println(hives.get(1).toString());
//        
//        Iterator<Abstract_Hive> iterator = hives.iterator();
//        while (iterator.hasNext()) {
//            if (!iterator.next().isAlive()) {
//                iterator.remove();   
//            }
//        }
//        
//        for (Abstract_Hive h : hives) {
//            h.update(50);
//        }
//        
//        System.out.println();
//        System.out.println(hives.get(1).toString());
//        
//        hives.get(0).attack(hives.get(2), 2);
//        hives.get(2).defend(hives.get(0));
//        
//        hives.get(2).attack(hives.get(5), 2);
//        hives.get(5).defend(hives.get(2));
    }
}

/**
 * File:   FactoryDemo.java
 * Date:   Nov 22, 2018
 * 
 */
package main.java.Factory;

import main.java.Flyweight.Bee_Enums.Type;

/**
 * Description: Create a hive factory which can create different hives
 * and use abstract methods to implement hive specific behaviors from
 * single interface
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class FactoryDemo {
       
    public static void main(String[] args) {
        
        Hive_Factory factory = new Hive_Factory();
       
        Abstract_Hive hive1 = factory.makeHive(Type.KILLER, 0,0);
        Abstract_Hive hive2 = factory.makeHive(Type.KILLER, 3,3);
        Abstract_Hive hive3 = factory.makeHive(Type.KILLER, 4,4);
        Abstract_Hive hive4 = factory.makeHive(Type.KILLER, 1,1);
        
        Abstract_Hive hive5 = factory.makeHive(Type.HONEY, 2,2);
        
        System.out.println(hive1.toString());
        
        hive1.makeBees(5);
        hive1.makeRooms(5, 2);
        
        hive1.update(100);
        
        System.out.println(hive1.toString());
        
        hive1.Attack(hive5, 6);
        
        System.out.println(hive1.toString());
    }
}

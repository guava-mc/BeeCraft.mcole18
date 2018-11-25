/**
 * File:   FactoryDemo.java
 * Date:   Nov 22, 2018
 * 
 */
package main.java.Factory;

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
        Beehive_Factory hive1 = new KillerHive(0,0);
        Beehive_Factory hive2 = new KillerHive(5,5);
        Beehive_Factory hive3 = new KillerHive(4,4);
        Beehive_Factory hive4 = new KillerHive(3,3);
        
        Beehive_Factory hive5 = new HoneyHive(1,1);
        
        System.out.println(hive1.toString());
        
        hive1.makeBees(5);
        hive1.makeRooms(5, 2);
        
        hive1.update(100);
        
        System.out.println(hive1.toString());
        
        hive1.Attack(hive5, 6);
        
        System.out.println(hive1.toString());
    }
}

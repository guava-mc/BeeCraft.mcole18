/**
 * File:   FlyweightDemo.java
 * Date:   Nov 22, 2018
 * 
 */
package main.java.flyweight;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Description: Uses a flyweight to hold an entire Hives base stats
 * so a hive does not need to hold true bee objects, only get the base 
 * state based on type of bee and then keep track of fewer things per 
 * 'bee' object i.e. stamina and id
 * 
 * When calling a bee specific method can pass the flyweight data and the
 * stamina/id to a temporary bee to perform any needed operations on that bee
 * 
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class FlyweightDemo implements BeeEnums{
    
    static Type[] types = {Type.CARPENTER, Type.KILLER, Type.BUMBLE, Type.HONEY};
    
    static Task[] tasks = {Task.BUILDING, Task.FIGHTING, Task.HARVESTING, Task.IDLE,
            Task.RESTING};
    
    static BeeFlyweight[] bees = new BeeFlyweight[10];
    
    static Random ran = new Random();
    
    public static void main(String[] args) {
        
        System.out.print("=====================================================\n"
                + " Demonstrating Flyweight Design Pattern with Bees.\n"
                + "=====================================================\n\n");

        System.out.println("Creating 10 bees of random type and with a random task"
                + "using Flyweight,\nif bee type already exists, copies flyweight data"
                + "and only sets new task\n");
        for(int i = 0; i < 10; i++) {
            BeeFlyweight bee = BeeFactory.getBee(randomType());
            
            bee.setTask(randomTask());       
            
            bees[i] = bee;
        }
        
        int totalBees = 0;
        System.out.println("Verifying 10 Bees created...\nTen Bee List:");
        totalBees += BeeFactory.getBeeCount(Type.KILLER);
        System.out.println("Total number of killer bees: " + BeeFactory.getBeeCount(Type.KILLER));
        totalBees += BeeFactory.getBeeCount(Type.CARPENTER);
        System.out.println("Total number of carpenter bees: " + BeeFactory.getBeeCount(Type.CARPENTER));
        totalBees += BeeFactory.getBeeCount(Type.BUMBLE);
        System.out.println("Total number of bumble bees: " + BeeFactory.getBeeCount(Type.BUMBLE));
        totalBees += BeeFactory.getBeeCount(Type.HONEY);
        System.out.println("Total number of honey bees: " + BeeFactory.getBeeCount(Type.HONEY));
        System.out.println("total bees: " + totalBees);
        
        System.out.println();
    }
      

    private static Type randomType() {
        
        return types[ran.nextInt(4)]; 
    }
    
    private static Task randomTask() {
        
        return tasks[ran.nextInt(5)]; 
    }

}

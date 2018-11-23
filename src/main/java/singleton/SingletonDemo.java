/**
 * File:   SingletonDemo.java
 * Date:   Nov 22, 2018
 * 
 */

package main.java.singleton;

import java.util.Objects;

/**
 * Description: Creates a singleton apiary where all hives exist in a xy 2d map
 * also maintains tick count.
 * 
 * @author mcole18
 * @version 1.0
 *
 */
public class SingletonDemo {

    private static Apiary apiary;
    private static Apiary clone1;
    private static Apiary clone2;

    /**
     * Description: Apiary Demo main method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.print("=====================================================\n"
                + " Demonstrating Singleton Design Pattern with Apiary.\n"
                + "=====================================================\n\n");

        // make sure Singleton is null to verify it is initialized dynamically
        System.out.println("Verifying singleton is null...");
        checkIfExists(apiary);
        System.out.println();

        // initialize apiary
        System.out.println("Initializing singleton with start()...");
        apiary = Apiary.start();
        checkIfExists(apiary);
        System.out.println();

        System.out.println("calling Apiary.start() on new Apiray data clone1 and clone2...");
        clone1 = Apiary.start();
        clone2 = Apiary.start();
        System.out.println("Verifying both new apiaries exist...");
        checkIfExists(clone1);
        checkIfExists(clone2);
        System.out.println();

        System.out.print("Comparing singleton ticks across each Apiary\n" 
                + "apiary: " + apiary.getTicks() + "\n"
                + "clone1: " + clone1.getTicks() + "\n" 
                + "clone2: " + clone1.getTicks() + "\n\n");
        
        System.out.println("Incrementing ticks with apiary.update()\n"
                + "expect all singletons ticks to equal 1");
        
        apiary.update();
        System.out.print("Comparing singleton ticks across each Apiary\n" 
                + "apiary: " + apiary.getTicks() + "\n"
                + "clone1: " + clone1.getTicks() + "\n" 
                + "clone2: " + clone1.getTicks() + "\n\n");

        
        System.out.println("Incrementing ticks with apiary.ticks(int tick)\n"
                + "expect all singletons ticks to equal 15");
        
        apiary.update(14);
        System.out.print("Comparing singleton ticks across each Apiary\n" 
                + "apiary: " + apiary.getTicks() + "\n"
                + "clone1: " + clone1.getTicks() + "\n" 
                + "clone2: " + clone1.getTicks() + "\n\n");

    }

    private static void checkIfExists(Apiary apiary) {
        try {
            Objects.requireNonNull(apiary);
            System.out.println("apiary exists!");
        } catch (NullPointerException e) {
            System.out.println(e + " apiary is null");
        }

    }
}

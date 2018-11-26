/**
 * File:   Mediator.java
 * Date:   Nov 25, 2018
 * 
 */
package main.java.mediator;

import java.util.HashMap;

import main.java.factory.AbstractHive;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public interface MediatorInterface {
    
    void sendTicks(int ticks, HashMap<String, AbstractHive> hives);

    /**
     * Description: When a hive wants to attack another hive it sends a message
     * to the mediator that delegates the message to the correct hives
     * defend call.
     * 
     * @param o
     * @return
     */
    void sendAttack(String caller, String callee, HashMap<String, AbstractHive> hives, int attackers);

}

/**
 * File:   Mediator.java
 * Date:   Nov 25, 2018
 * 
 */

package main.java.mediator;

import java.util.HashMap;
import java.util.Map.Entry;

import main.java.factory.AbstractHive;

/**
 * Description: Mediator DP the Abstract Bee classes need to 
 * be able to communicate with each other between update ticks.
 * 
 * Using the mediator they can create their instances and interact
 * with console in a loosely coupled fashion.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class Mediator implements MediatorInterface, AdapterInterface {

    /* (non-Javadoc)
     * @see main.java.mediator.MediatorInterface#getMessage(java.lang.Object)
     * 
     * for attacks
     */
    @Override
    public void sendAttack(String caller, String callee,
            HashMap<String, AbstractHive> hives, int attackers) {
        hives.get(caller).attack(hives.get(callee), attackers);
    }

    /* (non-Javadoc)
     * @see main.java.mediator.MediatorInterface#getTicks(int)
     */
    @Override
    public void sendTicks(int ticks, HashMap<String, AbstractHive> hives) {
        for (Entry<String, AbstractHive> hiveId : hives.entrySet()) {
            hiveId.getValue().update(ticks);
        }
    }

    /* (non-Javadoc)
     * @see main.java.mediator.AdapterInterface#hiveAction()
     */
    @Override
    public void hiveAction(AbstractHive hive, int method, int var2, int var3) {
        if (hive == null) {
            return;
        }
        hiveActions[method].hiveAction(hive, method, var2, var3);

    }

    private AdapterInterface[] hiveActions = new AdapterInterface[] { new AdapterInterface() {
        public void hiveAction(AbstractHive hive, int method, int var2, int var3) {
            hive.makeBees(var2);            
        }
    }, new AdapterInterface() {
        public void hiveAction(AbstractHive hive, int method, int var2, int var3) {
            hive.makeRooms(var2, var3);
        }
    },  new AdapterInterface() {
        public void hiveAction(AbstractHive hive, int method, int var2, int var3) {
            hive.makeHarvesters(var2);
        }
    },  new AdapterInterface() {
        public void hiveAction(AbstractHive hive, int method, int var2, int var3) {
            System.out.println(hive.toString());
        }
    }
    };

}

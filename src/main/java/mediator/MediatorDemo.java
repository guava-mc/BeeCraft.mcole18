/**
 * File:   MediatorDemo.java
 * Date:   Nov 22, 2018
 * 
 */
package main.java.mediator;

import java.util.HashMap;

import main.java.factory.AbstractHive;
import main.java.factory.HiveFactory;
import main.java.flyweight.BeeEnums.Type;
import main.java.singleton.Apiary;

/**
 * Description: Creates a Mediator object that is used to pass messages
 * from the apiary and the hives
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class MediatorDemo {
    static Apiary apiary;
    public static void main(String[] args) {
        System.out.print("=====================================================\n"
                + " Demonstrating Mediator Design Pattern with some Adapter DP fun.\n"
                + "=====================================================\n\n");
        Mediator mediator = new Mediator();
        apiary = Apiary.start();
        HiveFactory hiveFactory = new HiveFactory();
        
        HashMap<String, AbstractHive> hives = new HashMap<>();
        
        AbstractHive hive1 = hiveFactory.makeHive(Type.KILLER, 0, 0);
        AbstractHive hive2 = hiveFactory.makeHive(Type.HONEY, 1, 1);
        
        String one = hive1.getHiveId();
        String two = hive2.getHiveId();
        hives.put(hive1.getHiveId(), hive1);
        hives.put(hive2.getHiveId(), hive2);
        
        mediator.hiveAction(hives.get(one), 0, 3, 3);
        mediator.hiveAction(hives.get(two), 1, 2, 2);
        
        mediator.sendAttack(one, two, hives, 1);
        
        apiary.update(50);
        
        mediator.sendTicks(apiary.getTicks(), hives);
        
        mediator.hiveAction(hives.get(two), 3, 0, 0);
    }

}

/**
 * File:   MediatorDemo.java
 * Date:   Nov 22, 2018
 * 
 */
package main.java.mediator;

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
    
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Apiary apiary = Apiary.start();
        HiveFactory hiveFactory = new HiveFactory();
        
        AbstractHive hive1 = hiveFactory.makeHive(Type.KILLER, 0, 0);
        AbstractHive hive2 = hiveFactory.makeHive(Type.HONEY, 1, 1);
        apiary._HIVES.put(hive1.getHiveId(), hive1);
        apiary._HIVES.put(hive2.getHiveId(), hive2);
        
        mediator.hiveAction(apiary._HIVES.get("1"), 0, 3, 3);
        mediator.hiveAction(apiary._HIVES.get("2"), 1, 2, 2);
        
        mediator.sendAttack("1", "2", apiary._HIVES, 1);
        
        apiary.update(50);
        
        mediator.sendTicks(apiary.getTicks(), apiary._HIVES);
        
        mediator.hiveAction(apiary._HIVES.get("2"), 3, 0, 0);
    }

}

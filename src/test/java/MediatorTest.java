/**
 * File:   MediatorTest.java
 * Date:   Nov 26, 2018
 * 
 */
package test.java;


import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.factory.AbstractHive;
import main.java.factory.HiveFactory;
import main.java.flyweight.BeeEnums.Type;
import main.java.mediator.Mediator;
import main.java.singleton.Apiary;

/**
 * Description: Test the Mediator DP
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class MediatorTest {

    static Apiary apiary;
    
    Mediator mediator;
    AbstractHive hive1;
    AbstractHive hive2;
    HiveFactory factory;
 
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    //TODO DELTE
//    AbstractHive hive1 = hiveFactory.makeHive(Type.KILLER, 0, 0);
//    AbstractHive hive2 = hiveFactory.makeHive(Type.HONEY, 1, 1);
//    
//    String one = hive1.getHiveId();
//    String two = hive2.getHiveId();
//    hives.put(hive1.getHiveId(), hive1);
//    hives.put(hive2.getHiveId(), hive2);
//    
//    mediator.hiveAction(hives.get(one), MAKE_BEES, 3, 3);
//    mediator.hiveAction(hives.get(two), MAKE_ROOMS, 2, 2);
//    
//    mediator.sendAttack(one, two, hives, 1);
//    
//    apiary.update(50);
//    
//    mediator.sendTicks(apiary.getTicks(), hives);
//    
//    mediator.hiveAction(hives.get("2"), 3, 0, 0);
    
    /**
     * Description: set up tests.
     * 
     * @throws java.lang.Exception - wee
     */
    @Before
    public void setUp() throws Exception {
        mediator = new Mediator();
        
        
        factory = new HiveFactory();
        apiary = Apiary.start();
        hive1 = factory.makeHive(Type.KILLER, 0, 0);
        hive2 = factory.makeHive(Type.HONEY, 1, 1);
        String one = hive1.getHiveId();
        String two = hive2.getHiveId();
        apiary.getHives().put(hive1.getHiveId(), hive1);
        apiary.getHives().put(hive2.getHiveId(), hive2);
        System.setOut(new PrintStream(outContent));
        
    }

    /**
     * Description: tear down tests
     * 
     * @throws java.lang.Exception - wee
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void adapterFightTest() {
        
        mediator.sendAttack(hive1.getHiveId(), hive2.getHiveId(), apiary.getHives(), 5);
        assertTrue(outContent.toString().contains(("Ready to attack")));
    }
    
    @Test
    public void adapterMakeBeesTest() {
       
        mediator.hiveAction(hive1, Mediator.MAKE_BEES, 1, 1);
        assertTrue(outContent.toString().contains(("Incubating")));
        
        mediator.hiveAction(hive1, Mediator.MAKE_BEES, 100, 1);
        assertTrue(outContent.toString().contains(("Not enough FOOD")));
    }
    

    @Test
    public void adapterMakeRoonsTest() {
       
        mediator.hiveAction(hive1, Mediator.MAKE_ROOMS, 1, 1);
        assertTrue(outContent.toString().contains("Hive: 1 type: KILLER "
                + "Added 1 new rooms to the build queue"));
        
        mediator.hiveAction(hive1, Mediator.MAKE_ROOMS, 1, 100);
        assertTrue(!outContent.toString().contains("Transfered 95 idle"));
    }

    @Test
    public void adapterHarvesterTest() {
        mediator.hiveAction(hive1, Mediator.MAKE_HARVESTERS, 1, 1);
        assertTrue(hive1.toString().contains("Bees Harvesting: 6"));
        
        mediator.hiveAction(hive1, Mediator.MAKE_HARVESTERS, 100, 100);
        assertTrue(!outContent.toString().contains("100"));
    }
    
    @Test
    public void adapterToStringTest() {
       
        mediator.hiveAction(hive1, Mediator.HIVE_TOSTRING, 0, 0);
        assertTrue(outContent.toString().contains(hive1.toString()));
    }
    
    @Test
    public void tickTest() {
        hive1.makeBees(1);
        int totalBees = hive1.getTotalBees();
        apiary.update(50);
        mediator.sendTicks(apiary.getTicks(), apiary.getHives());
        assertTrue(hive1.getTotalBees() == ++totalBees);    
    }
    

}

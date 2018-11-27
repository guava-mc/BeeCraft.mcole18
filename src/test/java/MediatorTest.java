/**
 * File:   MediatorTest.java
 * Date:   Nov 26, 2018
 * 
 */
package test.java;


import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import main.java.factory.AbstractHive;
import main.java.factory.HiveFactory;
import main.java.flyweight.BeeEnums.Type;
import main.java.mediator.Mediator;
import main.java.singleton.Apiary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Description: Test the Mediator DP.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class MediatorTest {

    static Apiary apiary = Apiary.start();
    
    Mediator mediator;
    AbstractHive hive1;
    AbstractHive hive2;
    HiveFactory factory;
 
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    /**
     * Description: set up tests.
     * 
     * @throws java.lang.Exception - wee
     */
    @Before
    public void setUp() throws Exception {
        mediator = new Mediator();
        
        factory = new HiveFactory();
        hive1 = factory.makeHive(Type.KILLER, 0, 0);
        hive2 = factory.makeHive(Type.HONEY, 1, 1);
        apiary.getHives().put(hive1.getHiveId(), hive1);
        apiary.getHives().put(hive2.getHiveId(), hive2);
        System.setOut(new PrintStream(outContent, true, "UTF-8"));
        
    }

    /**
     * Description: tear down tests.
     * 
     * @throws java.lang.Exception - wee
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void adapterFightTest() throws UnsupportedEncodingException {
        
        mediator.sendAttack(hive1.getHiveId(), hive2.getHiveId(), apiary.getHives(), 5);
        assertTrue(outContent.toString("UTF-8").contains(("Ready to attack")));
    }
    
    @Test
    public void adapterMakeBeesTest() throws Exception {
       
        mediator.hiveAction(hive1, Mediator.MAKE_BEES, 1, 1);
        assertTrue(outContent.toString("UTF-8").contains(("Incubating")));
        
        mediator.hiveAction(hive1, Mediator.MAKE_BEES, 100, 1);
        assertTrue(outContent.toString("UTF-8").contains(("Not enough FOOD")));
    }
    

    @Test
    public void adapterMakeRoonsTest() throws UnsupportedEncodingException {
       
        mediator.hiveAction(hive1, Mediator.MAKE_ROOMS, 1, 1);
        assertTrue(outContent.toString("UTF-8").contains("Hive: 1 type: KILLER "
                + "Added 1 new rooms to the build queue"));
        
        mediator.hiveAction(hive1, Mediator.MAKE_ROOMS, 1, 100);
        assertTrue(!outContent.toString("UTF-8").contains("Transfered 95 idle"));
    }

    @Test
    public void adapterHarvesterTest() throws UnsupportedEncodingException {
        mediator.hiveAction(hive1, Mediator.MAKE_HARVESTERS, 1, 1);
        assertTrue(hive1.toString().contains("Bees Harvesting: 6"));
        
        mediator.hiveAction(hive1, Mediator.MAKE_HARVESTERS, 100, 100);
        assertTrue(!outContent.toString("UTF-8").contains("100"));
    }
    
    @Test
    public void adapterToStringTest() {
       
        mediator.hiveAction(hive1, Mediator.HIVE_TOSTRING, 0, 0);
        try {
            assertTrue(outContent.toString("UTF-8").contains(hive1.toString()));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

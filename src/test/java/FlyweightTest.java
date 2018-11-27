/**
 * File:   FlyweightTest.java
 * Date:   Nov 26, 2018
 * 
 */
package test.java;



import static org.junit.Assert.assertTrue;

import main.java.flyweight.Bee;
import main.java.flyweight.BeeEnums.Task;
import main.java.flyweight.BeeEnums.Type;
import main.java.flyweight.BeeFactory;
import main.java.flyweight.BeeFlyweight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class FlyweightTest {

    BeeFlyweight killer1;
    BeeFlyweight carpenter1;
    BeeFlyweight bumble1;
    BeeFlyweight honey1;
    BeeFlyweight killer2;
    BeeFlyweight carpenter2;
    
    Bee killer3;

        
    /**
     * Description: set up flyweight tests.
     * 
     * @throws java.lang.Exception !
     */
    @Before
    public void setUp() throws Exception {
        killer1 = BeeFactory.getBee(Type.KILLER);
        carpenter1 = BeeFactory.getBee(Type.CARPENTER);
        bumble1 = BeeFactory.getBee(Type.BUMBLE);
        honey1 = BeeFactory.getBee(Type.HONEY);
        killer2 = BeeFactory.getBee(Type.KILLER);
        carpenter2 = BeeFactory.getBee(Type.CARPENTER);
        
        killer3 = new Bee(Type.KILLER);
    }

    /**
     * Description: tear down flyweight tests.
     * 
     * @throws java.lang.Exception !
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Description: tests that each flyweight is to same reference.
     *
     */
    @Test
    public void flyweightReferenceTest() {
        killer1.highLanderEffect(carpenter1);
        //same reference
        assertTrue(carpenter1.equals(carpenter2));
    }
    
    /**
     * Description: shows highlander works for flyweight
     * is shared
     *
     */
    @Test
    public void flyweightHighlander() {
        killer1.highLanderEffect(carpenter1);
        killer1.highLanderEffect(honey1);
        killer1.highLanderEffect(bumble1);
        
        //same stats
        assertTrue(killer1.getEngineering() == killer2.getEngineering());
        assertTrue(killer1.getHarvesting() == killer2.getHarvesting());
        assertTrue(killer1.getRecoveryRate() == killer2.getRecoveryRate());
        assertTrue(killer1.getReproduction() == killer2.getReproduction());
        assertTrue(killer1.getStrength() == killer2.getStrength());
    }
    
    /**
     * Description: shows when flyweight is base of a Bee tasks can be
     * separate from flyweight task.
     *
     */
    @Test
    public void differentTaskTest() {
        killer1.setTask(Task.BUILDING);
        killer3.setTask(Task.RESTING);
        assertTrue(killer1.getTask() != killer3.getTask());
    }
    
    /**
     * Description: total references goes up with each call.
     *
     */
    @Test
    public void flyweightTypeCountTest() {
        assertTrue(BeeFactory.getKillerCount() > 1);
    }
    

}

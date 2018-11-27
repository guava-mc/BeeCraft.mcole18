/**
 * File:   FactoryTest.java
 * Date:   Nov 26, 2018
 * 
 */

package test.java;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import main.java.factory.AbstractHive;
import main.java.factory.HiveFactory;
import main.java.flyweight.BeeEnums.Type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description: FactoryTest class.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class FactoryTest {
    
    HiveFactory factory = new HiveFactory();
    ArrayList<AbstractHive> hives;

    /**
     * Description: steup.
     * 
     * @throws java.lang.Exception !
     */
    @Before
    public void setUp() throws Exception {
        hives = new ArrayList<>();
    }

    /**
     * Description: teardown.
     * 
     * @throws java.lang.Exception !
     */
    @After
    public void tearDown() throws Exception {
        hives = null;
    }

    @Test
    public void createHivesTest() {
        hives.add(factory.makeHive(Type.KILLER,0,0));
        hives.add(factory.makeHive(Type.BUMBLE,3,3));
        hives.add(factory.makeHive(Type.HONEY,4,4));
        hives.add(factory.makeHive(Type.CARPENTER,1,1));
        hives.add(factory.makeHive(Type.KILLER,0,0));
        hives.add(factory.makeHive(Type.BUMBLE,3,3));
        hives.add(factory.makeHive(Type.HONEY,4,4));
        hives.add(factory.makeHive(Type.CARPENTER,1,1));
        
        assertTrue(hives.size() == 8);
    }

}

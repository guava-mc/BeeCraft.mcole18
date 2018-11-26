/**
 * File:   ApiaryTest.java
 * Date:   Nov 22, 2018
 * 
 */

package test.java;

import static org.junit.Assert.assertTrue;

import main.java.singleton.Apiary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description: Test the Singleton class.
 * 
 * @author mcole18
 * @version 1.0
 *
 */
public class SingletonTest {

    Apiary apiary;
    Apiary clone1;
    Apiary clone2;

    /**
     * Description: setUp tests.
     * 
     * @throws java.lang.Exception !
     */
    @Before
    public void setUp() throws Exception {
        apiary = Apiary.start();
        clone1 = Apiary.start();

    }

    /**
     * Description: tearDown tests.
     * 
     * @throws java.lang.Exception !
     */
    @After
    public void tearDown() throws Exception {
        apiary = null;
        clone1 = null;
        clone2 = null;
        Apiary.update(-1);
    }

    /**
     * Description: Verify that Apiary.start() works as intended.
     * 
     * Tests that Apiary.start() inits the class that second call to Apiary.start
     * references original call and that uninitialized Apiary is not pre-loaded with
     * the attributes.
     *
     */
    @Test
    public void startTest() {
        //verifies apiary is not null and has init value of ticks
        assertTrue(apiary.getTicks() == 0);
        //verifies references same object
        assertTrue(clone1 == apiary);
        //verifies without Apiary.start() Apiary data type is null
        assertTrue(clone2 == null);
    }
    
    /**
     * Description: Verify update() updates tick by one
     * and updates for all instances of Singleton.
     *
     */
    @Test
    public void updateTest() {
        assertTrue(apiary.getTicks() == 0);
        apiary.update();
        assertTrue(apiary.getTicks() == 1);
        assertTrue(clone1.getTicks() == 1);
        clone2 = Apiary.start();
        assertTrue(apiary.getTicks() == clone2.getTicks());
    }
    
    /**
     * Description: Verify setTicks() updates tick by passed value
     * and updates for all instances of Singleton.
     *
     */
    @Test
    public void updateWithTicksTest() {
        assertTrue(apiary.getTicks() == 0);
        apiary.update(15);
        assertTrue(apiary.getTicks() == 15);
        assertTrue(clone1.getTicks() == 15);
        clone2 = Apiary.start();
        assertTrue(apiary.getTicks() == clone2.getTicks());
    }
    
    

}

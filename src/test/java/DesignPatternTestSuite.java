/**
 * File:   DPTestSuite.java
 * Date:   Nov 13, 2018
 * 
 */

package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Description: TestSuite.
 * 
 * @author: mcole18
 * @version 1.0
 *
 */
@RunWith(Suite.class)
@SuiteClasses({SingletonTest.class, MediatorTest.class, FlyweightTest.class, FactoryTest.class})
public class DesignPatternTestSuite {

}

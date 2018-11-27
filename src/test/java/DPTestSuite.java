/**
 * File:   DPTestSuite.java
 * Date:   Nov 13, 2018
 * 
 */

package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import main.java.flyweight.FlyweightDemo;

/**
 * Description: TODO.
 * 
 * @author: mcole18
 * @version 1.0
 *
 */
@RunWith(Suite.class)
@SuiteClasses({SingletonTest.class, MediatorTest.class, FlyweightTest.class})
public class DPTestSuite {

}

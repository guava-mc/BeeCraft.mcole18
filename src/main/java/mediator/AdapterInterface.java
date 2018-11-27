/**
 * File:   AdapterInterface.java
 * Date:   Nov 25, 2018
 * 
 */

package main.java.mediator;

import main.java.factory.AbstractHive;

/**
 * Description: AdapterInterface. Design pattern that allows
 * a single interface method to call mutliple methods in the
 * mediator.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public interface AdapterInterface {
    
    final int MAKE_BEES = 0;
    final int MAKE_ROOMS = 1;
    final int MAKE_HARVESTERS = 2;
    final int HIVE_TOSTRING = 3;
    
    void hiveAction(AbstractHive hive, int method, int var2, int var3);
}

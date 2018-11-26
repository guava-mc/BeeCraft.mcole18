/**
 * File:   AdapterInterface.java
 * Date:   Nov 25, 2018
 * 
 */
package main.java.mediator;

import main.java.factory.AbstractHive;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public interface AdapterInterface {
    
    void hiveAction(AbstractHive hive, int method, int var2, int var3);
}

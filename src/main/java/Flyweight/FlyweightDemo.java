/**
 * File:   FlyweightDemo.java
 * Date:   Nov 22, 2018
 * 
 */
package main.java.Flyweight;

/**
 * Description: Uses a flyweight to hold an entire Hives base stats
 * so a hive does not need to hold true bee objects, only get the base 
 * state based on type of bee and then keep track of fewer things per 
 * 'bee' object i.e. stamina and id
 * 
 * When calling a bee specific method can pass the flyweight data and the
 * stamina/id to a temporary bee to perform any needed operations on that bee
 * 
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class FlyweightDemo {

}

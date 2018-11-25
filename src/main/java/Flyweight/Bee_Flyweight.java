/**
 * File:   Bee_Flyweight.java
 * Date:   Nov 23, 2018
 * 
 */

package main.java.Flyweight;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public interface Bee_Flyweight extends Bee_Enums{
    
    public void setTask(Task t);
    public Type getType();
    public Task getTask();
    public int getEngineering();
    public int getStrength();    
    public int getReproduction();    
    public int getHarvesting();    
    public int getStamMax();    
    public float getRecoveryRate();
    public boolean isResting();
    
    
    /**
     * Description: Determines recovery per tick for a beeType.
     * Can be used in hive through the flyweight with Streams map
     * function to update all BeeStates in the RESTING task for 
     * currentTasks hashMap in hive.
     * 
     * Should be called in a hives update method that 
     * updates each BeeBasic[] in currentTasks and then
     * checks with some sort of conditional to delegate bees
     * to other tasks with another map
     * 
     * i.e  if resting stamina > 50 -> send BeeBasic i to IDLE
     *      if stamina == 0 remove BeeBasic, update totalBees
     *      if stamina < 5 -> send to resting, etc
     * 
     * @return - return the recovered stamina  
     */
    public int rest(int tick);
    
    /**
     * Description: special bee specific trait based on type
     * absorbed by victorious bees in highlander effect
     * based off bonus bools.
     *
     */
    public void special();
    /**
     * Description: Check destroyed hive's bee attributes
     * and adjust conquering hives's bee attributes
     * 
     * @param baseStats - losing hive's bee stats
     */
    public void HighLanderEffect(Bee_Flyweight baseStats);
        
    
}

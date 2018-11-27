/**
 * File:   Queen.java
 * Date:   Nov 23, 2018
 * 
 */

package main.java.flyweight;

/**
 * Description: Creates a queen for a hive.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public class Queen extends AbstractBee {

    private int stamina;
    
    /**
     * Constructor.
     */
    public Queen() {
        staminaMax = 50;
        stamina = staminaMax;
        recoveryRate = 2;
    }

    /* (non-Javadoc)
     * @see main.java.Factory.bee.Abstract_Bee#rest(int)
     */
    @Override
    public int rest(int tick) {
        return (int) (stamina + (idleRecovery * tick * recoveryRate));
        
    }

    /* (non-Javadoc)
     * @see main.java.Factory.bee.Abstract_Bee#special()
     */
    @Override
    public void special() {
        System.out.println("I am the Queen!!!");
        
    }

    /* (non-Javadoc)
     * @see main.java.Flyweight.Bee_Flyweight#setTask(main.java.Flyweight.Bee_Enums.Task)
     */
    @Override
    public void setTask(Task t) {
        // TODO Auto-generated method stub
        
    }
    
    public int getStamina() {
        return stamina;
    }
    
    public void setStamina(int hitPoints) {
        stamina -= hitPoints;
    }

}

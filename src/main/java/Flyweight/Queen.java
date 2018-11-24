/**
 * File:   Queen.java
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
public class Queen extends Abstract_Bee {

    public Queen() {
        staminaMax = 1500;
        recoveryRate = 2;
    }

    /* (non-Javadoc)
     * @see main.java.Factory.bee.Abstract_Bee#rest(int)
     */
    @Override
    public
    int rest(int tick) {
        return (int) (stamina + (IDLE_RECOVERY * tick * recoveryRate));
        
    }

    /* (non-Javadoc)
     * @see main.java.Factory.bee.Abstract_Bee#special()
     */
    @Override
    public
    void special() {
        System.out.println("I am the Queen!!!");
        
    }

    /* (non-Javadoc)
     * @see main.java.Flyweight.Bee_Flyweight#setTask(main.java.Flyweight.Bee_Enums.Task)
     */
    @Override
    public void setTask(Task t) {
        // TODO Auto-generated method stub
        
    }

}

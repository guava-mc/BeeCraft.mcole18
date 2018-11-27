/**
 * File:   Bee.java
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
public class Bee_Base extends Abstract_Bee{
     /**
     * Constructor - set base stats based on type and set bool
     * @param t
     */
    public Bee_Base(Type t) {
        currentTask = Task.IDLE;
        type = t;
        if (Type.CARPENTER == t) {
            carpenterBonus = true;
            staminaMax = 15;
            harvesting = 8;
            reproduction = 8;
            System.out.println("Carpenter Bee created!!");
        } else if (Type.KILLER == t) {
            killerBonus = true;
            strength = 15;
            staminaMax = 10;
            reproduction = 10;
            System.out.println("Killer Bee created!!");
        } else if (Type.BUMBLE == t) {
            bumbleBonus = true;
            engineering = 10;
            recoveryRate = 1.5f;
            harvesting = 10;
            reproduction = 15;
            System.out.println("Bumble Bee created!!");
        } else if (Type.HONEY == t) {
            honeyBonus = true;
            staminaMax = 15;
            harvesting = 15;
            reproduction = 10;
            System.out.println("Honey Bee created!!");
        }
    }

    /* (non-Javadoc)
     * @see main.java.Factory.bee.Abstract_Bee#rest(int)
     */
    @Override
    public int rest(int tick) {
        if(resting) {
            return (int) (stamina + (recoveryRate * tick * RESTING_RECOVERY));
        }
        else {
            return stamina + (IDLE_RECOVERY * tick);
        }
    }

    /* (non-Javadoc)
     * @see main.java.Factory.bee.Abstract_Bee#special()
     */
    @Override
    public void special() {
        if(carpenterBonus) {
            System.out.println("I am a cool carpenter bee.");
        }
        if(killerBonus) {
            System.out.println("I am a kickass killer bee.");
        }
        if(bumbleBonus) {
            System.out.println("I am a beautiful bumble bee.");
        }
        if(honeyBonus) {
            System.out.println("I am a harvesting honey bee.");
        }
        
    }

    /* (non-Javadoc)
     * @see main.java.Flyweight.Bee_Flyweight#setTask(main.java.Flyweight.Bee_Flyweight.Task)
     */
    @Override
    public void setTask(Task t) {
       currentTask = t;
       System.out.println("Setting task to " + t);
    }
    
}

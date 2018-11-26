/**
 * File:   Abstract_Bee.java
 * Date:   Nov 23, 2018
 * 
 */

package main.java.flyweight;

import main.java.flyweight.BeeEnums.Task;
import main.java.flyweight.BeeEnums.Type;

/**
 * Description: AbstraceBee class.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public abstract class AbstractBee implements BeeFlyweight, Comparable<Bee> {
    
    protected static final int restingRecovery = 5;
    protected static final int idleRecovery = 3;
    
    protected boolean carpenterBonus = false;
    protected boolean killerBonus = false;
    protected boolean bumbleBonus = false;
    protected boolean honeyBonus = false;
    
    protected int engineering = 5;
    protected int strength = 5;
    protected int reproduction = 5;
    protected int harvesting = 5;
    protected int staminaMax = 5;
    protected int stamina;
    protected float recoveryRate = 1; //multiplied by constant when resting
    
    protected boolean resting;
    protected Task currentTask;
    protected Type type;
    
    public int getEngineering() {
        return engineering;
    }
    
    public int getStrength() {
        return strength;
    }
    
    public int getReproduction() {
        return reproduction;
    }
    
    public int getHarvesting() {
        return harvesting;
    }
    
    public int getStamMax() {
        return staminaMax;
    }
        
    public float getRecoveryRate() {
        return recoveryRate;
    }
    
    public boolean isResting() {
        return resting;
    }
    
    public Task getCurrentTask() {
        return currentTask;
    }
    
    public Type getType() {
        System.out.println(type);
        return type;
    }
    
    public Task getTask() {
        System.out.println(currentTask);
        return currentTask;
    }
    
    public void highLanderEffect(BeeFlyweight bee) {
        highLanderEffect((AbstractBee) bee);
    }
    
    /**
     * Description: absorb defeated bees powers. 
     * 
     * @param bee - defeated bee
     */
    public void highLanderEffect(AbstractBee bee) {
        if (bee.carpenterBonus) {
            carpenterBonus = true;
            engineering += 5;
            staminaMax += 5;
        }
        if (bee.killerBonus) {
            killerBonus = true;
            strength += 5;
            recoveryRate += 5;
        }
        if (bee.bumbleBonus) {
            bumbleBonus = true;
            reproduction += 5;
            staminaMax += 5;
        }
        if (bee.honeyBonus) {
            honeyBonus = true;
            harvesting += 5;
            staminaMax += 3;
            recoveryRate += 0.5;
        }
    }
    
    @Override
    public int compareTo(Bee other) {
        return this.stamina - other.getStamina();
    }
    
    /*
     *  protected int engineering = 5;
        protected int strength = 5;
        protected int reproduction = 5;
        protected int harvesting = 5;
        protected int staminaMax = 5;
        protected int stamina;
        protected float recoveryRate = 1;
     */
    
    @Override
    public String toString() {
        String stats = "";
        
        stats += "Strength\tMax Stamina\tEngineering\tReproduction\tHarvesting\tRecovery Rate\n";
        stats += "   " + strength + "\t\t     " + staminaMax 
                + "\t\t     " + engineering + "\t\t     " 
                + reproduction + "\t\t     " + harvesting + "\t\t     " + recoveryRate;
       
        
        return stats;
        
    }
}

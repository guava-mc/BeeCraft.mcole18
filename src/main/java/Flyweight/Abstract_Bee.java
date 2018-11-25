/**
 * File:   Abstract_Bee.java
 * Date:   Nov 23, 2018
 * 
 */

package main.java.Flyweight;

import main.java.Flyweight.Bee_Enums.Task;
import main.java.Flyweight.Bee_Enums.Type;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public abstract class Abstract_Bee implements Bee_Flyweight, Comparable<Bee>{
    
    protected final int RESTING_RECOVERY = 5;
    protected final int IDLE_RECOVERY = 3;
    
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
    
//    public int getStamina() {
//        return stamina;
//    }
    
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
    
    public void HighLanderEffect(Bee_Flyweight bee) {
        HighLanderEffect((Abstract_Bee) bee);
    }
    
    public void HighLanderEffect(Abstract_Bee bee) {
        if(bee.carpenterBonus) {
            carpenterBonus = true;
            engineering += 5;
            stamina += 5;
        }
        if(bee.killerBonus) {
            killerBonus = true;
            strength += 5;
            recoveryRate += 5;
        }
        if(bee.bumbleBonus) {
            bumbleBonus = true;
            reproduction += 5;
            stamina += 5;
        }
        if(bee.honeyBonus) {
            honeyBonus = true;
            harvesting += 5;
            stamina += 3;
            recoveryRate += 2;
        }
     }
    
    @Override
    public int compareTo(Bee other) {
      return  this.stamina - other.getStamina();    
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
        stats += "   "+ strength + "\t\t     " + staminaMax + "\t\t     " + engineering + "\t\t     " 
                + reproduction + "\t\t     " + harvesting + "\t\t     " + recoveryRate;
       
        
        return stats;
        
    }
}

/**
 * File:   Bee.java
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
public class Bee implements Bee_Enums, Comparable<Bee>{
    private Bee_Flyweight baseStats;
    private int id;
    private int stamina;
    private Task task;
    
    public Bee (Type t) {
        baseStats = BeeFactory.getBee(t);
        id = BeeFactory.getBeeCount(t);
        stamina = baseStats.getStamMax();
    }
    
    public int getId() {
        return id;
    }
    
    public int getStamina() {
        return stamina;
    }
    
    
    public void setStamina(int hitPoints) {
        stamina -= hitPoints;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }
    
    public int getEngineering() {
        return baseStats.getEngineering();
    }
    
    public int getStrength() {
        return baseStats.getStrength();
    }
    
    public int getReproduction() {
        return baseStats.getReproduction();
    }
    
    public int getHarvesting() {
        return baseStats.getHarvesting();
    }
    
    public int getStamMax() {
        return baseStats.getStamMax();
    }
    
    public float getRecoveryRate() {
        return baseStats.getRecoveryRate();
    }
    
    public boolean isResting() {
        return baseStats.isResting();
    }
    
    public int rest(int tick) {
        return baseStats.rest(tick);
    }
    
    public void special() {
        baseStats.special();
    }
    
    public void HighlanderEffect(Abstract_Bee bee) {
        baseStats.HighLanderEffect(bee);
    }
    
    public String toString() {
        return baseStats.toString() + "\n";
    }
    
    @Override
    public int compareTo(Bee other) {
      return  this.stamina - other.getStamina();    
    }
    

    
}

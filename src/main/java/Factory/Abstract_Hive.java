/**
 * File:   Abstract_Hive.java
 * Date:   Nov 22, 2018
 * 
 */
package main.java.Factory;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import main.java.Flyweight.Bee;
import main.java.Flyweight.Bee_Enums;
import main.java.Flyweight.Queen;

/**
 * Description: TODO
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public abstract class Abstract_Hive implements Bee_Enums, Hive_Constants{
    
    private static int hiveNumber = 1;
    
    private int rooms;
    public Point2D position;
    private String hiveName;
    
    private int food;
    private int totalBees; //use size() of PQ to update this.
    private  int incubatingBees = 0; //if any bees incubating continue growing them per tick
    private  int incubateTime;
    private int roomsInProgress = 0;
    private  int buildTime;
    private  Queen queenBee;
    private final Bee _SENTINEL; //use only this bee to trigger highlander action
    private final Type type;
    
    //different tasks
    private HashMap<Task, PriorityQueue<Bee> > currentTasks;
    
    public Abstract_Hive(Type t, int x, int y) {
        type = t;
        position = new Point2D.Double(x,y);
        queenBee = new Queen();
        _SENTINEL = new Bee(type);
        hiveName = "Hive: " + getHiveNumber() + " type: "+ t;
        setHiveNumber(getHiveNumber() + 1);
        
        currentTasks = new HashMap<>();
        generatePQs();
        
        rooms = 10;
        food = 100;
        incubateTime = _BABY_TICKS - _SENTINEL.getReproduction();
        buildTime = _ROOM_TICKS - _SENTINEL.getEngineering();
        
        updateBeeCount();
    }
    
    /**
     * Description: updates all attributes based on number of ticks
     * 
     * @param ticks
     */
    public void update(int ticks) {
        //TODO
        
        //do last
        buildRooms(ticks);
        incubateBees(ticks);
        updateBeeCount();
    }
    
    /**
     * Description: based off babyFood constant - reproduction
     * and babyTick constant - reproduction
     *
     */
    public void makeBees(int makeCount) {
        if(food >= (_BABY_FOOD - _SENTINEL.getReproduction()) * makeCount && rooms > totalBees) {
            food -= (_BABY_FOOD - _SENTINEL.getReproduction()) * makeCount;
            incubatingBees += makeCount;
        }
        else {
            System.out.println(this.hiveName + ": Not enough food to make them bee babies!");
            //TODO take max food and max babies and begin incubating based off food
        }
    }
    
    private void incubateBees(int ticks) {
        //ticks required to make a baby
        while(ticks > 0 && incubatingBees > 0) {
            incubateTime--;
            if(incubateTime <= 0) {
                incubateTime = _BABY_TICKS - _SENTINEL.getReproduction();;
                incubatingBees--;
                addIdleBee();
            }
            ticks--;
        }
    }
    
    /**
     * Description: based off babyFood constant - reproduction
     * and babyTick constant - reproduction
     *
     */
    public void makeRooms(int makeCount, int builders) {
        int transfered = 1;
        if(food >= (_ROOM_FOOD - _SENTINEL.getEngineering()) * makeCount && 
                (assignedBeeCount(Task.IDLE) > 0 || assignedBeeCount(Task.BUILDING) > 0)) {
            food -= (_ROOM_FOOD - _SENTINEL.getEngineering()) * makeCount;
            roomsInProgress += makeCount;
            transfered = transferBees(Task.IDLE, Task.BUILDING, builders-assignedBeeCount(Task.BUILDING));
            System.out.println(hiveName + " Transfered " + transfered + " idle bees to Building");
        } else {
            if(food < (_ROOM_FOOD - _SENTINEL.getEngineering()) * makeCount && 
                    assignedBeeCount(Task.IDLE) < 1 && assignedBeeCount(Task.BUILDING) < 1) {
                System.out.println(this.hiveName + ": Not enough available bees and not enough food to make them rooms!");
            } else if (food < (_ROOM_FOOD - _SENTINEL.getEngineering()) * makeCount) {
                System.out.println(this.hiveName + ": Not enough food to make them rooms!");
            } else {
                System.out.println(this.hiveName + ": Not enough bees to make them rooms!");
            }
            
        }
    }
    
    private void buildRooms(int ticks) {
        //ticks required to make a room
        while(ticks > 0 && roomsInProgress > 0) {
            buildTime -= assignedBeeCount(Task.BUILDING);
            if(buildTime <= 0) {
                buildTime = _ROOM_TICKS - _SENTINEL.getEngineering();
                rooms++;
                roomsInProgress--;
                System.out.println(hiveName + " room complete");
            }
            ticks--;
        }
    }
    
    //TODO change to message to mediator
    public void Attack(Abstract_Hive enemy, int attackers) {
        if(assignedBeeCount(Task.FIGHTING) < attackers) {
            int neededFighters = attackers - assignedBeeCount(Task.FIGHTING);
            if(neededFighters > assignedBeeCount(Task.IDLE)) {
                System.out.println("Not enough availble Bees to launch attack");
            }
            else {
                int transfered = transferBees(Task.IDLE, Task.FIGHTING, neededFighters);
                if(transfered > 0) {
                    System.out.println("Transfered: " + transfered + " from idle to Fighting.");
                }
                changeStamina(Task.FIGHTING, (int)(position.distance(enemy.position) * 0.1), "marching to " + enemy.hiveName);
                Fight(enemy);
            }
        }
    }
    
    
    public void Fight(Abstract_Hive enemy) {
        int totalHitPoints = enemy._SENTINEL.getStrength() * enemy.getTotalBees();
        changeStaminaa(Task.FIGHTING, totalHitPoints, "Fighting " + enemy.hiveName);
        
        if(assignedBeeCount(Task.FIGHTING) > 0) {
            HighlanderEffect(enemy);
            System.out.println(this.hiveName + " defeated " + enemy.hiveName + " and absorbs their power!");
        } else {
            System.out.println(this.hiveName + " failed their invasion on " + enemy.hiveName);
        }
    }
    
    public void defend(Abstract_Hive enemy) {}
    
    private void changeStaminaa(Task t, int change, String effect) {
        int reduce = 0;
        ArrayList<Bee> deadBees = new ArrayList<>();
        for(Bee b : currentTasks.get(t)) {
            if(change == 0) {
                return;
            }
            reduce = b.getStamina();
            b.setStamina(change);
            change -= reduce;
            if (b.getStamina() <= 0) {
                System.out.println("Bee " + b.getId() + " has died from " + effect);
                deadBees.add(b);
            }
        }
        for (Bee b : deadBees) {
            currentTasks.get(t).remove(b);
        }
        updateBeeCount();
    }
    
    private void changeStamina(Task t, int change, String effect) {
        ArrayList<Bee> deadBees = new ArrayList<>();
        for(Bee b : currentTasks.get(t)) {
            b.setStamina(change);
            if (b.getStamina() <= 0) {
                //int i = 0;
                System.out.println("Bee " + b.getId() + " has died from " + effect);
                deadBees.add(b);
                //i++;
            }
        }
        for (Bee b : deadBees) {
            currentTasks.get(t).remove(b);
        }
        updateBeeCount();
    }
    
       
    private void HighlanderEffect(Abstract_Hive enemy){
        _SENTINEL.HighlanderEffect(enemy.getSentinel());
    }
   
    private int transferBees(Task from, Task to, int toTransfer) {
        int transfered = 0;
        if(assignedBeeCount(from) < 1) { 
            return 0; 
            }
        while(assignedBeeCount(from) > 0 && toTransfer > 0) {
            currentTasks.get(to).add(currentTasks.get(from).remove());
            toTransfer--;
            transfered++;
        }
        return transfered;
        
    }

    /**
     * @return the hiveNumber
     */
    public static int getHiveNumber() {
        return hiveNumber;
    }

    /**
     * @param hiveNumber the hiveNumber to set
     */
    public static void setHiveNumber(int hiveNumber) {
        Abstract_Hive.hiveNumber = hiveNumber;
    }
    
    public Bee getSentinel() {
        return _SENTINEL;
    }
    
    /**
     * Description: HARVESTING, FIGHTING, BUILDING, RESTING, IDLE 
     *
     */
    private void generatePQs() {
        
        //sorted as min PQ to be able to find bees that need to go idle/rest
        currentTasks.put(Task.HARVESTING, new PriorityQueue<Bee>());
        currentTasks.put(Task.BUILDING, new PriorityQueue<Bee>());
        
        //these have MaxPQ so can find rested to put to work first and for fighting so strongest
        //fight first, lose less bees
        currentTasks.put(Task.FIGHTING, new PriorityQueue<Bee>(Collections.reverseOrder()));
        currentTasks.put(Task.RESTING, new PriorityQueue<Bee>(Collections.reverseOrder()));
        currentTasks.put(Task.IDLE, new PriorityQueue<Bee>(Collections.reverseOrder()));
        
        //start each field with a Bee
        for(Entry<Task, PriorityQueue<Bee>> entry : currentTasks.entrySet()) {
            entry.getValue().add(new Bee(type));
        }
    }
    
    private void addIdleBee() {
        System.out.println(hiveName + " baby bee moved to idle");
        currentTasks.get(Task.IDLE).add(new Bee(type));
    }
    
    private void updateBeeCount() {
        totalBees = 0;
        for(Entry<Task, PriorityQueue<Bee>> entry : currentTasks.entrySet()) {
            totalBees += entry.getValue().size();
        } 
    }
    
    private int assignedBeeCount(Task t) {
        return currentTasks.get(t).size();
    }
    
    public String toString() {
        String stats = "";
        stats += hiveName + "\n";
        stats += "total Bees:  " + totalBees + "\n";
        stats += "total Rooms: " + rooms + "\n";
        stats += "total Food:  " + food + "\n\n";
        stats += "Bees incubating:          " + incubatingBees + "\n";
        stats += "Rooms under construction: " + roomsInProgress + "\n\n";
        stats += "Bee stats:\n" + _SENTINEL.toString();
        
        return stats;
        
        
    }
    
    public int getTotalBees() {
        return totalBees;
    }
}

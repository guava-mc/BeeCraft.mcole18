/**
 * File:   Abstract_Hive.java
 * Date:   Nov 22, 2018
 * 
 */

package main.java.factory;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import main.java.flyweight.Bee;
import main.java.flyweight.BeeEnums;
import main.java.flyweight.Queen;

/**
 * Description: Create Abstract Hive for HiveFactory.
 * 
 * @author  mcole18
 * @version 1.0
 *
 */
public abstract class AbstractHive implements BeeEnums, HiveConstants {
    
    private static int hiveNumber = 1;
    
    private boolean isAlive;
    private int rooms;
    public Point2D position;
    private String hiveName;
    private int hiveId;
    
    private int food;
    private int totalBees; //use size() of PQ to update this.
    private int incubatingBees = 0; //if any bees incubating continue growing them per tick
    private int incubateTime;
    private int roomsInProgress = 0;
    private int buildTime;
    private int beeHiveKills = 0;
    private  Queen queenBee;
    private final Bee sentinel; //use only this bee to trigger highlander action
    private final Type type;
    
    private AbstractHive enemyToFight;
    private AbstractHive enemyAttacking;
    private int incomingHitPoints = 0;
    
    //different tasks
    private HashMap<Task, PriorityQueue<Bee>> currentTasks;
    
    /**
     * Constructor.
     * @param t - type of bee
     * @param x - xPos
     * @param y - yPos
     */
    public AbstractHive(Type t, int x, int y) {
        setAlive(true);
        type = t;
        position = new Point2D.Double(x,y);
        queenBee = new Queen();
        sentinel = new Bee(type);
        hiveId = getHiveNumber();
        hiveName = "Hive: " + hiveId + " type: " + t;
        setHiveNumber(getHiveNumber() + 1);
        
        currentTasks = new HashMap<>();
        generatePQs();
        
        rooms = 30;
        food = 100;
        incubateTime = _BABY_TICKS - sentinel.getReproduction();
        buildTime = _ROOM_TICKS - sentinel.getEngineering();
        
        updateBeeCount();
    }
    
    /**
     * Description: updates all attributes based on number of ticks.
     * 
     * @param ticks - update ticks to use
     */
    public void update(int ticks) {
        //TODO
        
        //do last
        harvestFood(ticks);
        buildRooms(ticks);
        incubateBees(ticks);
        fight(enemyToFight);
        defend(enemyAttacking, incomingHitPoints);
        updateBeeCount();
    }
    
    /**
     * Description: based off babyFood constant - reproduction
     * and babyTick constant - reproduction.
     *
     */
    public void makeBees(int makeCount) {
        if (food >= (_BABY_FOOD - sentinel.getReproduction())
                * makeCount && rooms > totalBees + makeCount) {
            food -= (_BABY_FOOD - sentinel.getReproduction()) * makeCount;
            incubatingBees += makeCount;
            System.out.println(hiveName + " Incubating " + makeCount + " baby bees.");
        } else {
            if (food >= (_BABY_FOOD - sentinel.getReproduction())
                    * makeCount) {
                System.out.println(this.hiveName + ": Not enough ROOMS to make them bee babies!");
            } else {
                System.out.println(this.hiveName + ": Not enough FOOD to make them bee babies!");
            }
            //TODO take max food and max babies and begin incubating based off food
        }
    }
    
    private void incubateBees(int ticks) {
        //ticks required to make a baby
        while (ticks > 0 && incubatingBees > 0) {
            incubateTime--;
            if (incubateTime <= 0) {
                incubateTime = _BABY_TICKS - sentinel.getReproduction();;
                incubatingBees--;
                addIdleBee();
            }
            ticks--;
        }
    }
    
    /**
     * Description: based off babyFood constant - reproduction
     * and babyTick constant - reproduction.
     *
     */
    public void makeRooms(int makeCount, int builders) {
        int transfered = 1;
        if (food >= (_ROOM_FOOD - sentinel.getEngineering()) * makeCount 
                && (assignedBeeCount(Task.IDLE) > 0 || assignedBeeCount(Task.BUILDING) > 0)) {
            food -= (_ROOM_FOOD - sentinel.getEngineering()) * makeCount;
            roomsInProgress += makeCount;
            transfered = transferBees(Task.IDLE,
                    Task.BUILDING, builders - assignedBeeCount(Task.BUILDING));
            if(transfered > 0) {
                System.out.println(hiveName + " Transfered " 
                        + transfered + " idle bees to Building");
            }
            System.out.println(hiveName + " Added " + makeCount + " new rooms to the build queue");
        } else {
            if (food < (_ROOM_FOOD - sentinel.getEngineering()) * makeCount 
                    && assignedBeeCount(Task.IDLE) < 1 && assignedBeeCount(Task.BUILDING) < 1) {
                System.out.println(this.hiveName + ": "
                        + "Not enough available bees and not enough food to make them rooms!");
            } else if (food < (_ROOM_FOOD - sentinel.getEngineering()) * makeCount) {
                System.out.println(this.hiveName + ": Not enough food to make them rooms!");
            } else {
                System.out.println(this.hiveName + ": Not enough bees to make them rooms!");
            }
            
        }
    }
    
    private void buildRooms(int ticks) {
        //ticks required to make a room
        while (ticks > 0 && roomsInProgress > 0) {
            buildTime -= assignedBeeCount(Task.BUILDING);
            if (buildTime <= 0) {
                buildTime = _ROOM_TICKS - sentinel.getEngineering();
                rooms++;
                roomsInProgress--;
                System.out.println(hiveName + " room complete");
            }
            ticks--;
        }
    }
    
    /**
     * Description: based off babyFood constant - reproduction
     * and babyTick constant - reproduction.
     *
     */
    public void makeHarvesters(int harvesters) {
        int transfered = 0;
        if ((assignedBeeCount(Task.IDLE) > 0 || assignedBeeCount(Task.HARVESTING) > 0)) {
            transfered = transferBees(Task.IDLE, Task.HARVESTING, 
                    harvesters);
            if (transfered > 0) {
                System.out.println(hiveName + " Transfered " 
                        + transfered + " idle bees to Harvesting");
            }
        } else {
            System.out.println(this.hiveName + ": Not enough bees to make more Harvesters!");
        }
    }
    
    private void harvestFood(int ticks) {
        //ticks required to make a room
        float harvesting = 0;
        int foodHarvested = 0;
        while (ticks > 0) {
            harvesting += assignedBeeCount(Task.HARVESTING)
                    * _HARVEST_RATE * sentinel.getHarvesting();
            ticks--;
        }
        if (harvesting >= 1) {
            food += (int)harvesting;
            foodHarvested += (int)harvesting;
            harvesting = 0;
        }
        System.out.println(hiveName + " food from harvesting: " + foodHarvested);
    }
    
    //TODO change to message to mediator
    /**
     * Description: Attacks enemy hive with number of attackers.
     * 
     * @param enemy - enemy hive
     * @param attackers - number of attackers
     */
    public void attack(AbstractHive enemy, int attackers) {
        if (assignedBeeCount(Task.FIGHTING) < attackers) {
            int neededFighters = attackers - assignedBeeCount(Task.FIGHTING);
            if (neededFighters > assignedBeeCount(Task.IDLE)) {
                System.out.println("Not enough availble Bees to launch attack");
                return;
            } else {
                int transfered = transferBees(Task.IDLE, Task.FIGHTING, neededFighters);
                if (transfered > 0) {
                    System.out.println(hiveName 
                            + " transfered: " + transfered + " from idle to Fighting.");
                }
            }
        }
        if (assignedBeeCount(Task.FIGHTING) >= attackers) {
            System.out.println("Ready to attack " + enemy.hiveName);
            this.enemyToFight = enemy;
            enemy.enemyAttacking = this;
            changeStaminaPerBee(Task.FIGHTING, (int)(position.distance(enemy.position) * 0.5),
                    "marching to " + enemy.hiveName);
        }
    }
    
    /**
     * Description: Fight is the offensive Fighting troops, gets
     * enemy hive from mediator.
     * 
     * @param enemy - Abstract_Hive from mediator
     */
    public void fight(AbstractHive enemy) {
        if (enemy != null) {
            //send message here TODO
            int totalHitPoints = enemy.sentinel.getStrength() 
                    * (enemy.assignedBeeCount(Task.IDLE) + enemy.assignedBeeCount(Task.RESTING));
            changeStamina(Task.FIGHTING,
                    totalHitPoints, "Fighting " + enemy.hiveName);
            if (assignedBeeCount(Task.FIGHTING) > 0) {
                if (attackQueen(assignedBeeCount(Task.FIGHTING) 
                        * sentinel.getStrength()) <= 0) {
                    highlanderEffect(enemy);
                    System.out.println(this.hiveName + " defeated "
                            + enemy.hiveName + " and absorbs their power!");
                    beeHiveKills++;
                } else {
                    System.out.println(this.hiveName 
                            + " failed their invasion on " + enemy.hiveName);
                }
            } else {
                System.out.println(this.hiveName + " failed their invasion on " + enemy.hiveName);
            } 
            this.enemyToFight = null;
        }
    }
    
    //get attacker
    /**
     * Description:. 
     * 
     * @param enemy
     */
    public void setEnemyAttacking(AbstractHive enemy) {
        this.enemyAttacking = enemy;
        incomingHitPoints = enemy.sentinel.getStrength() 
                * enemy.assignedBeeCount(Task.FIGHTING);
    }
    
    /**
     * Description: Beehive defends when attacked, gets enemyHive from mediator.
     * 
     * @param enemy -Abstract_Hive from mediator
     */
    private void defend(AbstractHive enemy, int totalHitPoints) {
        if (enemy != null) {
            totalHitPoints = changeStamina(Task.IDLE, totalHitPoints,
                    "Defending against " + enemy.hiveName);
            if (totalHitPoints > 0) {
                totalHitPoints = changeStamina(Task.RESTING,
                        totalHitPoints, "Defending against " + enemy.hiveName);
            }
            if (totalHitPoints > 0) {
                queenBee.setStamina(totalHitPoints);
            }
            if (queenBee.getStamina() > 0) {
                System.out.println(hiveName + " succesfully defended againt " + enemy.hiveName);
            } else {
                System.out.println(hiveName + " has been destroyed by " + enemy.hiveName);
                setAlive(false);
            }
            this.enemyAttacking = null;
            incomingHitPoints = 0;
        }
    }
    
    private int attackQueen(int totalHitPoints) {
        int enemyQueenStam = enemyToFight.queenBee.getStamina();
        changeStamina(Task.FIGHTING, enemyToFight.queenBee.getStamina(),
                "attacking Queen of " + enemyToFight.hiveName);
        return enemyQueenStam - totalHitPoints;
    }
   
     
    /**
     * Description: used when effect is a global effect that is reduced
     * as it iterates bees.
     * 
     * @param t - task PQ to change bees in
     * @param change - the total value of change to stamina across the group
     * @param effect - string that says what is causing change
     */
    private int changeStamina(Task t, int change, String effect) {
        int reduce = 0;
        ArrayList<Bee> deadBees = new ArrayList<>();
        for (Bee b : currentTasks.get(t)) {
            if (change == 0) {
                break;
            }
            reduce = b.getStamina();
            b.setStamina(change);
            change -= reduce;
            if (b.getStamina() <= 0) {
                System.out.println(hiveName + " Bee " + b.getId() + " has died from " + effect);
                deadBees.add(b);
            }
        }
        for (Bee b : deadBees) {
            currentTasks.get(t).remove(b);
        }
        updateBeeCount();
        return change;
    }
    
    /**
     * Description: used when effect is a per bee effect
     * as it iterates bees.
     * 
     * @param t - task PQ to change bees in
     * @param change - the change to stamina
     * @param effect - string that says what is causing change
     */
    private void changeStaminaPerBee(Task t, int change, String effect) {
        ArrayList<Bee> deadBees = new ArrayList<>();
        for (Bee b : currentTasks.get(t)) {
            b.setStamina(change);
            if (b.getStamina() <= 0) {
                System.out.println(hiveName + " Bee " + b.getId() + " has died from " + effect);
                deadBees.add(b);
            }
        }
        for (Bee b : deadBees) {
            currentTasks.get(t).remove(b);
        }
        updateBeeCount();
    }
    
       
    private void highlanderEffect(AbstractHive enemy) {
        sentinel.highlanderEffect(enemy.getSentinel());
    }
   
    private int transferBees(Task from, Task to, int toTransfer) {
        int transfered = 0;
        if (assignedBeeCount(from) < 1) { 
            return 0; 
        }
        while (assignedBeeCount(from) > 0 && toTransfer > 0) {
            currentTasks.get(to).add(currentTasks.get(from).remove());
            toTransfer--;
            transfered++;
        }
        return transfered;
        
    }

    /**
     * .
     * @return the hiveNumber
     */
    public static int getHiveNumber() {
        return hiveNumber;
    }

    /**
     * .
     * @param hiveNumber the hiveNumber to set
     */
    public static void setHiveNumber(int hiveNumber) {
        AbstractHive.hiveNumber = hiveNumber;
    }
    
    public Bee getSentinel() {
        return sentinel;
    }
    
    /**
     * Description: HARVESTING, FIGHTING, BUILDING, RESTING, IDLE. 
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
        
        //start each field with 5 Bees
        for (Entry<Task, PriorityQueue<Bee>> entry : currentTasks.entrySet()) {
            for (int i = 0; i < 5; i++) {
                entry.getValue().add(new Bee(type));
            }
        }
    }
    
    private void addIdleBee() {
        System.out.println(hiveName + " baby bee moved to idle");
        currentTasks.get(Task.IDLE).add(new Bee(type));
    }
    
    private void updateBeeCount() {
        totalBees = 0;
        for (Entry<Task, PriorityQueue<Bee>> entry : currentTasks.entrySet()) {
            totalBees += entry.getValue().size();
        } 
    }
    
    private int assignedBeeCount(Task t) {
        return currentTasks.get(t).size();
    }
    
    
    /**
     * Override toString.
     */
    @Override
    public String toString() {
        String stats = "\n=========HiveData=========\n";
        if(isAlive) {
            stats += hiveName + "\n";
            stats += "total Bees:  " + totalBees + "\n";
            stats += "total Rooms: " + rooms + "\n";
            stats += "total Food:  " + food + "\n\n";
            stats += "Bees Fighting: " + assignedBeeCount(Task.FIGHTING) + "\n";
            stats += "Bees Building: " + assignedBeeCount(Task.BUILDING) + "\n";
            stats += "Bees Harvesting: " + assignedBeeCount(Task.HARVESTING) + "\n";
            stats += "Bees Resting: " + assignedBeeCount(Task.RESTING) + "\n";
            stats += "Bees Idle: " + assignedBeeCount(Task.IDLE) + "\n\n";
            stats += "Bees incubating:          " + incubatingBees + "\n";
            stats += "Rooms under construction: " + roomsInProgress + "\n\n";
            stats += "total hives destroyed:  " + beeHiveKills + "\n\n";
            stats += "Bee stats:\n" + sentinel.toString();
        } else {
            stats += "Nobody cares about a dead hive!";
        }
        return stats;
        
        
    }
    
    public int getTotalBees() {
        return totalBees;
    }
    
    public String getName() {
        return hiveName;
    }
    
    public String getHiveId() {
        return String.valueOf(hiveId);
    }

    /**
     * getter.
     * @return the isAlive
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * setter.
     * @param isAlive the isAlive to set
     */
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}

/**
 * File:   MainEC.java
 * Date:   Nov 22, 2018
 * 
 */

package main.java.extracredit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import main.java.factory.AbstractHive;
import main.java.factory.HiveFactory;
import main.java.flyweight.BeeEnums;
import main.java.mediator.Mediator;
import main.java.singleton.Apiary;

/**
 * Description: Uses all five DPs I implemented: Singleton, Factory, Flyweight,
 * Mediator, and Adapter.
 * 
 * @author mcole18
 * @version 1.0
 *
 */
public class MainEC implements BeeEnums {

    static Apiary apiary = Apiary.start();
    static Scanner in;
    private static HashMap<String, Methods> helpers = new HashMap<>();
    private static HiveFactory hiveFactory = new HiveFactory();
    private static Mediator mediator = new Mediator();
    private static int totalHives = 0;
    private static boolean gameStarted = false;
    private static boolean gameOver = false;
    // static HashMap<String, ECMethod>

    /**
     * Description: executes game. 
     * 
     * @param args - !
     */
    public static void main(String[] args) {

        helpers = formHelpers();

        in = new Scanner(System.in, "UTF-8");
        String input = "";

        startGame(input);

    }

    static String openingMessage = "Hello! Welcome to BeeCraft, "
            + "to begin spawn two new beehive with:\n"
            + "\'spawn X Y T' to spawn a new behive at "
            + "position X, Y, of type T.\n\n" + "Possible types:\n"
            + "KILLER, CARPENTER, BUMBLE, HONEY";

    static String newRoundMessage = "To issue a command, specify a command:\n" 
            + "ROOMS+ BEES+ HARVESTERS+ \n"
            + "then select hiveId number and number of bees to assign/incubate\n"
            + "for ROOMS+ also indicate number of rooms to be built\n" 
            + "ex:\n" + "BEES+ 1 10\n" + "ROOMS+ 1 1 5\n\n"
            + "To issue an attack, specify as follows:\n"
            + "<command> <AttackHiveID> <DefenderHiveID> <numOfFighters>\n" 
            + "ex:\n" + "ATTACK 1 2 5\n\n"
            + "For a summary of a particular Hive enter:" + "SUMMARY <HiveID>"
            + "To advance ticks use command TICK <numberOfTicks>\n" + "ex:\n" + "TICK 50";

    static void startGame(String input) {
        System.out.println(openingMessage);

        gameLoop(input);

    }

    private static void gameLoop(String input) {
        while (!gameOver && !input.contains("QUIT")) {

            if (gameStarted) {
                // currentStatus();
                while (!input.contains("TICK")) {
                    input = in.nextLine().toUpperCase();
                    userInput(input);
                }

                System.out.println("\npress any key to continue...");
                in.nextLine();
                apiary.update();
                currentStatus();
                input = "";
                gameLoop(input);

            } else {
                input = in.nextLine().toUpperCase();
                userInput(input);

                currentStatus();
            }

        }
    }

    /**
     * Description: handles user input.
     * 
     * @param input - string from user
     */
    static void userInput(String input) {
        if (input.contains("QUIT")) {
            System.out.println("wow, wow, wow...quitter...wow");
            System.exit(0);
        }
        List<String> commands = Arrays.asList(input.split("\\s* \\s*"));

        // System.out.println(commands.size());
        if (commands.size() > 4 || commands.size() < 2) {
            System.out.println("You don't follow directions very well! \n" 
                    + "I'm tempted to call it quits right here!\n");
            // startGame(input);
        } else {
            executeMethod(commands);
        }

    }

    interface Methods {
        void executeMethod(List<String> commands);
    }

    /*
     * (non-Javadoc)
     * 
     * @see main.java.mediator.Mediator.Methods#executeMethod(java.lang.String[])
     */
    /**
     * Description: implements Adapter DP for fun!!!.
     * 
     * @param params - adapter action
     */
    public static void executeMethod(List<String> params) {
        try {
            helpers.get(params.get(0).toUpperCase()).executeMethod(params);
        } catch (Exception e) {
            System.out.println("your input does not match a command");
        }
    }

    static HashMap<String, Methods> formHelpers() {
        HashMap<String, Methods> methods = new HashMap();

        // initialize new hive
        methods.put("SPAWN", new Methods() {
            public void executeMethod(List<String> params) {
                Type t = null;
                //System.out.println(params.get(3));
                switch (params.get(3).toUpperCase().charAt(0)) {
                    case 'K':
                        t = Type.KILLER;
                        break;
                    case 'C':
                        t = Type.CARPENTER;
                        break;
                    case 'B':
                        t = Type.BUMBLE;
                        break;
                    case 'H':
                        t = Type.HONEY;
                        break;
                    default:
                        System.out.println("I dont recognize that type, sry bout it!");
                        break;

                }
                try {
                    AbstractHive temp = hiveFactory.makeHive(t, Integer.parseInt(params.get(1)),
                            Integer.parseInt(params.get(2)));
                    apiary.getHives().put(temp.getHiveId(), temp);
                } catch (Exception e) {
                    // System.out.println(e);
                    System.out.println("Your swap input needs some swapping. Incorrect input, yo!");
                }
            }
        });

        methods.put("TICK", new Methods() {
            public void executeMethod(List<String> params) {
                apiary.update(Integer.parseInt(params.get(1)));
                mediator.sendTicks(apiary.getTicks(), apiary.getHives());
            }

        });

        methods.put("ATTACK", new Methods() {
            public void executeMethod(List<String> params) {
                mediator.sendAttack(params.get(1), params.get(2),
                        apiary.getHives(), Integer.parseInt(params.get(3)));
            }
        });

        methods.put("ROOMS+", new Methods() {
            public void executeMethod(List<String> params) {
                mediator.hiveAction(apiary.getHives().get(params.get(1)), Mediator.MAKE_ROOMS,
                        Integer.parseInt(params.get(2)), Integer.parseInt(params.get(3)));
            }
        });

        methods.put("BEES+", new Methods() {
            public void executeMethod(List<String> params) {
                mediator.hiveAction(apiary.getHives().get(params.get(1)), Mediator.MAKE_BEES,
                        Integer.parseInt(params.get(2)), 0);
            }
        });

        methods.put("HARVESTERS+", new Methods() {
            public void executeMethod(List<String> params) {
                mediator.hiveAction(apiary.getHives().get(params.get(1)), Mediator.MAKE_HARVESTERS,
                        Integer.parseInt(params.get(2)), 0);
            }
        });

        methods.put("SUMMARY", new Methods() {
            public void executeMethod(List<String> params) {
                mediator.hiveAction(apiary.getHives().get(params.get(1)),
                        Mediator.HIVE_TOSTRING, 0, 0);
            }
        });

        return methods;
    }

    private static void currentStatus() {
        try {
            totalHives = apiary.getHives().size();
            if (totalHives > 1) {
                if (!gameStarted) {
                    System.out.println("\nTHE GAME HAS BEGUN MUAHAHAHAHAHAH!\n");
                }
                gameStarted = true;
            } else if (totalHives < 2 && gameStarted) {
                gameOver = true;
                for (Entry<String, AbstractHive> hive : apiary.getHives().entrySet()) {
                    System.out.println("==========================\n" 
                            + hive.getValue().getName() + " WINS!!\n"
                            + "==========================\n\n" + "Final Stats:");
                    mediator.hiveAction(hive.getValue(), mediator.HIVE_TOSTRING, 0, 0);
                }
                System.exit(0);
            }
        } catch (Exception e) {
            totalHives = 0;
        }

        if (!gameStarted) {
            System.out.println("Need " + (2 - totalHives) + " hives to begin game.");
        } else {
            System.out.println(totalHives + " hives alive:\n");
            for (Entry<String, AbstractHive> hive : apiary.getHives().entrySet()) {
                System.out.println(hive.getValue().getName());
            }
            System.out.println();

            System.out.println(newRoundMessage);

        }

    }

}
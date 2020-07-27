package ui;

import java.util.Scanner;

public class TaskApp {
    private Scanner input;
    Boolean keepGoing = true;

    // EFFECTS: runs the task app
    public TaskApp() {
        runTaskApp();
    }

    // MODIFIES: this
    // EFFECTS: runs app in user console with user input
    private void runTaskApp() {

        String command = null;
        Scanner consoleInput = new Scanner(System.in);
        input = new Scanner(System.in);
        while (keepGoing == true) {
            displayInitial();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("session has ended");
    }

    private void addMasterTask() {
        System.out.println("added Master Task");
        System.out.println("\n");
        displayAskMaster();
    }

    /*        public boolean addSubTask() {
            return false;
        }*/

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c1")) {
            keepGoing = true;
            addMasterTask();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays initial menu of options to user
    private void displayInitial() {
        System.out.println("\nWelcome to TwentyFour7:");
        System.out.println("\nStart by creating your new MasterTask");
        System.out.println("\tc1 -> create new MasterTask");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: display menu after master Task has been created, ask for a master task name
    private void displayAskMaster() {
        String selection = "";
        while (!(selection.equals("q"))) {
            System.out.println("Enter the name of the Master Task below:");
            selection = input.next();
            selection = selection.toLowerCase();
            if (!(selection == "")) {
                displayMasterMenu();
                break;
            }
        }
    }

    // MODIFIES:
    // EFFECTS: display menu after master Task has been named
    private void displayMasterMenu() {
        String selection = "";
        while (!(selection.equals("q"))) {
            System.out.println("\n");
            System.out.println("You have successfully created your Master Task: _______");
            System.out.println("Enter s to create your first subTask");
            System.out.println("q -> return to main menu");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("s")) {
                displayAskSub();
            } else {
                System.out.println("invalid selection");
            }
            selection = "q";
        }
    }

    // MODIFIES:
    // EFFECTS: display menu after sub Task has been created, ask for a sub task name
    private void displayAskSub() {
        String selection = "";

        while (!(selection.equals("q"))) {
            System.out.println("Enter the name of the Sub Task below:");
            System.out.println("q -> quit");
            selection = input.next();
            selection = selection.toLowerCase();
            if (!(selection == "")) {
                displaySubMenu();
                break;
            }
        }
    }

    // MODIFIES:
    // EFFECTS: display menu after master Task has been named
    private void displaySubMenu() {
        String selection = "";
        while (!(selection.equals("q"))) {
            System.out.println("\n");
            System.out.println("You have successfully created your Sub Task: _______");
            System.out.println("q -> return to main menu");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        keepGoing = false;
    }
}

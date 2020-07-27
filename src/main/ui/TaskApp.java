package ui;

import model.MasterTask;
import model.Person;
import model.SubTask;

import java.util.Scanner;

public class TaskApp {
    // How would you create a new variable every time the createXYZ method is called?
    // Problem arises when multiple objects are created within this TaskApp

    private volatile MasterTask initMasterTask;
    private volatile SubTask initSubTask;
    private volatile Person initNewPerson;
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
            createMasterTask(selection);
            System.out.println();
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
            System.out.println("You are working on Master Task: _______");
            System.out.println("\ts to create your a subTask");
            //System.out.println("\tp to view people working on this task"); implement
            System.out.println("\tq -> quit");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("s")) {
                // WANT: use the input selection as the title field for our subTask
                // ISSUE: using multiple subTasks updates the same variable?
                createSubTask(selection);
                initMasterTask.addSubTask(initSubTask);
                displayAskSub();
            } else if (selection.equals("q")) {
                keepGoing = false;
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
            System.out.println("You are working on Sub Task: _______");
            System.out.println("\tn -> assign new person to task");
            System.out.println("\tr -> return to MasterTask Menu");
            System.out.println("\tq -> quit");
            selection = input.next();
            selection = selection.toLowerCase();

            if (selection.equals("n")) {
                displayAddPerson();
                break;
            }

            if (selection.equals("r")) {
                displayMasterMenu();
            }
        }
        keepGoing = false;
    }

    private void displayAddPerson() {
        String selection = "";
        while (!(selection.equals("q"))) {
            System.out.println("Enter name of person");
            selection = input.next();
            selection = selection.toLowerCase();

            if (!(selection == "")) {
                createNewPerson(selection);
                initSubTask.addPerson(initNewPerson);
                //System.out.println(initSubTask.assignedPersons.get(0).getSubTaskId());
                break;
            }
        }
        System.out.println("You have added _______ to Sub Task _______");
        displaySubMenu();
    }

    public void createMasterTask(String title) {
        initMasterTask = new MasterTask(title);
    }

    public void createSubTask(String title) {
        initSubTask = new SubTask(title);
    }

    public void createNewPerson(String name) {
        initNewPerson = new Person(name);
    }

}


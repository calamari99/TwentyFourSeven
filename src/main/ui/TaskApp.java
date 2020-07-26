package ui;

import java.util.Scanner;

public class TaskApp {
    private Scanner consoleInput;

    // EFFECTS: runs the task app
    public TaskApp() {
        runTaskApp();
    }

    // MODIFIES: this
    // EFFECTS: runs user input
    private void runTaskApp() {
        boolean keepGoing = true;
        String command = null;
        Scanner consoleInput = new Scanner(System.in);
    }

    private void addMasterTask() {

    }

    /*        public boolean addSubTask() {
            return false;
        }*/


    // MODIFIES: this
    // EFFECTS: processes user command
/*    private void processCommand(String command) {
        if (command.equals("d")) {
            doDeposit();
        } else if (command.equals("w")) {
            doWithdrawal();
        } else if (command.equals("t")) {
            doTransfer();
        } else if (command.equals("s")) {
            saveAccounts();
        } else if (command.equals("p")) {
            printAccount();
        } else {
            System.out.println("Selection not valid...");
        }
    }*/

    private void displayOptions() {

    }
}

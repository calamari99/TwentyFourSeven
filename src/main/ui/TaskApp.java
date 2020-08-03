package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.MasterTask;
import model.Person;
import model.SubTask;

import java.io.*;
import java.util.Scanner;
// TaskApp methods class is taken after Teller

public class TaskApp {

    private static final String ACCOUNTS_FILE = "./data/masterData.JSON";
    private volatile MasterTask initMasterTask;
    private volatile SubTask initSubTask;
    private volatile Person initNewPerson;
    private Scanner input;
    public String masterTitle;
    public String prevSubTitle;
    Boolean keepGoing = true;
    public String stringMasterTask;

    // EFFECTS: runs the task app
    public TaskApp() {
        runTaskApp();
    }

    // REQUIRE: no spaces can be typed as input (for now)
    // MODIFIES: this
    // EFFECTS: runs app in user console with user input
    private void runTaskApp() {
        loadMasterJson();
    }

    // EFFECTS: Prints completion of an added master task and prompts master name
    private void addMasterTask() {
        System.out.println("added Master Task");
        System.out.println("\n");

        displayAskMaster();
    }


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

    // EFFECTS: display menu after master Task has been created, asks for a master task name, changes display to
    //          master menu after name is entered
    private void displayAskMaster() {

        String selection = "";

        while (!(selection.equals("q"))) {
            System.out.println("Enter the name of the MasterTask below:");
            selection = input.next();
            masterTitle = selection;
            selection = selection.toLowerCase();
            createMasterTask(selection);
            convertMasterJson();
            System.out.println();
            if (!(selection == "")) {
                displayMasterMenu();
                break;
            }
            if (selection.equals("q")) {
                keepGoing = false;
            }
        }
    }

    // MODIFIES: this, initMasterTask
    // EFFECTS: display menu after master Task has been named, if s is selected initialize a sub task under master task
    private void displayMasterMenu() {
        String selection = "";
        while (!(selection.equals("q"))) {
            System.out.println("You are working on MasterTask: " + chooseMasterTitle());
            masterMenuCommands();
            selection = input.next();
            String masterTitle = selection;
            selection = selection.toLowerCase();

            if (selection.equals("v")) {
                displaySubTasks();
                displayMasterMenu();
            } else if (selection.equals("s")) {
                displayAskSub();
            } else if (selection.equals("q")) {
                keepGoing = false;
                break;
            } else {
                System.out.println("invalid selection");
            }
            selection = "q";
        }
    }

    // EFFECTS: prints out menu commands in console
    private void masterMenuCommands() {
        System.out.println("\tv -> view subTasks");
        System.out.println("\ts -> create a subTask");
        System.out.println("\tq -> quit");
    }

    // EFFECT: Returns master title from saved file if present, otherwise user input
    private String chooseMasterTitle() {
        if (this.masterTitle == null) {
            return initMasterTask.getTitle();
        } else {
            return masterTitle;
        }
    }

    // EFFECT: Views all subtasks under master tasks
    private void displaySubTasks() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        stringMasterTask = gson.toJson(this.initMasterTask);
        initMasterTask.getSubNames();
        System.out.println(initMasterTask.subTaskNames);

       //System.out.println(this.initMasterTask.getAssignedTasks().toString());
    }

    // EFFECTS: display menu after sub Task has been created, ask for a sub task name
    private void displayAskSub() {
        String selection = "";
        while (!(selection.equals("q"))) {
            System.out.println("Enter the name of the SubTask below:");
            selection = input.next();
            prevSubTitle = selection;
            createSubTask(selection);
            initMasterTask.addSubTask(initSubTask);
            convertMasterJson();
            displaySubMenu();
            break;
        }
    }

    // MODIFIES: this (keepGoing)
    // EFFECTS: show subtask display menu, if n is selected continue to add a person,
    //                                     if r is selected return to master menu,
    //                                     else quit
    private void displaySubMenu() {
        String selection = "";
        while (!(selection.equals("q"))) {
            subMenuCommands();
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("n")) {
                displayAddPerson();
            }
            if (selection.equals("r")) {
                displayMasterMenu();
                break;
            }
            if (selection.equals("v")) {
                //displayPeople();
                displaySubMenu();
                break;
            }
            if (selection.equals("q")) {
                break;
            } else {
                System.out.println("Invalid Selection, Please enter again");
            }
        }
        keepGoing = false;
    }

    private void subMenuCommands() {
        System.out.println("You are working on SubTask: " + prevSubTitle);
        System.out.println("\tn -> assign new person to task");
        System.out.println("\tr -> return to MasterTask Menu");
        System.out.println(("\tv -> view members assigned"));
        System.out.println("\tq -> quit");
    }

    // still need to figure out:
    // use current subtask, call getTeam memberNames
/*    private void displayPeople() {
        initSubTask.getTeamMemberNames();
    }*/


    // EFFECTS: display new person menu, if text is entered, set text to persons name and print success message
    //          then return to the subtask menu
    private void displayAddPerson() {
        String selection = "";
        while (!(selection.equals("q"))) {
            System.out.println("Enter name of person");
            selection = input.next();
            selection = selection.toLowerCase();

            if (!(selection == "")) {
                createNewPerson(selection);
                initSubTask.addPerson(initNewPerson);
                convertMasterJson();
                //System.out.println(initSubTask.assignedPersons.get(0).getSubTaskId());
                break;
            }
        }
        System.out.println(selection + " has been added to SubTask: " + prevSubTitle);
        System.out.println("\n");
        displaySubMenu();
    }

    // EFFECTS: instantiates a MasterTask object
    public void createMasterTask(String title) {
        initMasterTask = new MasterTask(title);
    }

    // EFFECTS: instantiates a SubTask object
    public void createSubTask(String title) {
        initSubTask = new SubTask(title);
    }

    // EFFECTS: instantiates a Person object
    public void createNewPerson(String name) {
        initNewPerson = new Person(name);
    }

    // MODIFIES: masterData json file, this
    // EFFECTS: updates and recreates masterData file
    public void convertMasterJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        stringMasterTask = gson.toJson(this.initMasterTask);
        //System.out.println(stringMasterTask);
        // creates writer
        try {
            Writer writer = new FileWriter(ACCOUNTS_FILE);
            //System.out.println(stringMasterTask);
            writer.write(stringMasterTask);
            writer.close();
            System.out.println("saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Resumes to master menu state if local data is present
    public void loadMasterJson() {
        readMasterJson();

        Scanner consoleInput = new Scanner(System.in);
        input = new Scanner(System.in);
        while (keepGoing) {
            displayMasterMenu();
            if (!keepGoing) {
                break;
            }
            String command;
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    // EFFECTS: Use GSON library to update app to stored data, if no data is found, start program at initial state
    public void readMasterJson() {
        try {
            Gson gson = new Gson();
            // read json file
            JsonReader reader = new JsonReader(new FileReader(ACCOUNTS_FILE));
            this.initMasterTask = gson.fromJson(reader, MasterTask.class);
            System.out.println(initMasterTask.getTitle() + " has been loaded from stored file");
            reader.close();
        } catch (IOException e) {
            while (keepGoing) {
                loadInitMenu();
            }
        }
    }

    // EFFECTS: load initial start menu
    public void loadInitMenu() {
        String command = null;
        Scanner consoleInput = new Scanner(System.in);
        input = new Scanner(System.in);
        displayInitial();
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("q")) {
            keepGoing = false;
        } else {
            processCommand(command);
        }

        System.out.println("session has ended");
    }

}



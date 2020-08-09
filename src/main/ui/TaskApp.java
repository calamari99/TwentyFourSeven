package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.MasterTask;
import model.Person;
import model.SubTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Scanner;
// TaskApp methods class is taken after Teller

public class TaskApp {

    private static final String ACCOUNTS_FILE = "./data/userData/masterData.JSON";
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
        guiApp();
        runTaskApp();
    }

    JFrame frame;
    JPanel panel;

    private void guiApp() {
        frame = new JFrame();
        panel = new JPanel();
        JLabel welcomeText = new JLabel("Welcome to TwentyFour7");
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        JButton newButton = new JButton("Create a new Master Task");
        newButton.addActionListener(this::newMasterEvent);
        JButton loadButton = new JButton("Reload a Master Task");
        panel.setBorder(BorderFactory.createEmptyBorder(
                40, 40, 10, 40
        ));
        panel.setLayout(new GridLayout(3, 1));
        panel.add(welcomeText);
        panel.add(newButton);
        panel.add(loadButton);

        frame.setSize(600, 600);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TwentyFour7");
        frame.pack();
        frame.setVisible(true);
    }

    public void newMasterEvent(ActionEvent e) {
        frame.setTitle("changed"); // test if button works
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
            updateMasterJson();
            System.out.println();
            if (!(selection == "")) {
                displayMasterMenu();
                break;
            }
            if (selection.equals("q")) {
                this.keepGoing = false;
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

            // views subtasks
            if (selection.equals("v")) {
                displaySubTasks();
                displayMasterMenu();
                // adds new subtask
            } else if (selection.equals("s")) {
                displayAskSub();
                // quit application
            } else if (selection.equals("m")) {
                manageSubMenu();
            } else if (selection.equals("q")) {
                this.keepGoing = false;
                break;
            } else {
                System.out.println("invalid selection");
            }
            selection = "q";
        }
    }

    // REQUIRES: only positive integers
    // EFFECTS: displays menu to take action of a particular subtask
    private void manageSubMenu() {
        listOutSubtasks();
        System.out.println("---Insert the number of the subtask you would like to delete---");
        System.out.println("--------------------or press r to return-----------------------");
        String selection = "";
        while (!("r".equals(selection))) {
            selection = input.next();
            // checks if selection input are digits
            if (selection.matches("[0-9]+")) {
                int num = Integer.parseInt(selection);

                if (num <= initMasterTask.getAssignedTasks().size()) {
                    deleteSubtaskJson(num);
                }
                manageSubMenu();

            } else if (selection.equals("r")) {
                displayMasterMenu();
                break;
            } else {
                System.out.println("invalid selection");
            }
            selection = "r";
        }
    }

    private void deleteSubtaskJson(int n) {
        initMasterTask.getAssignedTasks().remove(n - 1);
        System.out.println("Subtask " + n + " was removed");
        updateMasterJson();

    }

    private void listOutSubtasks() {
        System.out.println(initMasterTask.indexSubNames());

        //System.out.println(this.initMasterTask.getAssignedTasks().toString());
    }

    // EFFECTS: prints out menu commands in console
    private void masterMenuCommands() {
        System.out.println("\tv -> view subTasks");
        System.out.println("\tm -> manage a subTask");
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

    // EFFECT: prints all subtasks under current master task
    private void displaySubTasks() {
        setMasterJson();
        initMasterTask.getSubNames();
        System.out.println(initMasterTask.getSubNames());

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
            updateMasterJson();
            displaySubMenu();
            break;
        }
    }

    // MODIFIES: this (keepGoing)
    // EFFECTS: show subtask display menu, if n is selected continue to add a person,
    //                                     if r is selected return to master menu,
    //                                     else quit
    private void displaySubMenu() {
        String selection;
        while (this.keepGoing = true) {
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
                this.keepGoing = false;
                break;
            }
        }
    }

    // EFFECTS: shows sub menu commands in console.
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
    private void displayAddPerson() {
        String selection = "";
        System.out.println("Enter name of person");
        selection = input.next();
        selection = selection.toLowerCase();

        if (!(selection == "")) {
            createNewPerson(selection);
            initSubTask.addPerson(initNewPerson);
            updateMasterJson();
            //System.out.println(initSubTask.assignedPersons.get(0).getSubTaskId());
        }
        System.out.println(selection + " has been added to SubTask: " + prevSubTitle);
        System.out.println("\n");
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
    public void updateMasterJson() {
        setMasterJson();
        createWriter();
    }

    // EFFECTS: creates writer to store data as JSON
    private void createWriter() {
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

    // EFFECTS: sets masterTask object as a string
    private void setMasterJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        stringMasterTask = gson.toJson(this.initMasterTask);
    }

    // EFFECTS: Resumes to master menu state if local data is present
    public void loadMasterJson() {
        readMasterJson();

        Scanner consoleInput = new Scanner(System.in);
        input = new Scanner(System.in);
        while (this.keepGoing) {
            displayMasterMenu();
            if (!this.keepGoing) {
                break;
            }
            String command;
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                this.keepGoing = false;
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
            while (this.keepGoing) {
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
            this.keepGoing = false;
        } else {
            processCommand(command);
        }

        System.out.println("session has ended");
    }
}



package model;

import model.MasterTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SubTask extends MasterTask {
    String taskDetails;
    private final int subTaskId;
    private static final AtomicInteger id = new AtomicInteger(0); //initializes at 0
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html
    public ArrayList<Person> assignedPersons;         // list of people working on this task
    ArrayList<String> teamMemberNames;         // names of people working on this task
    //ArrayList<SubTask> assignedTasks;        // figure out inheritance later
    Boolean isDone = false;
    String subTaskTitle;

    // REQUIRES: string title not empty, id >= 0 (create exceptions here)
    // MODIFIES: this
    // EFFECTS: Creates a subtask with a given title and id, and an empty list of assignedPersons
    //          and an empty list of teamMemberNames
    public SubTask(String title) {
        super(title);
        this.subTaskTitle = title;
        assignedPersons = new ArrayList<Person>();
        teamMemberNames = new ArrayList<String>();
        subTaskId = id.incrementAndGet();
    }

    // MODIFIES: this, assignedPersons
    // EFFECTS: adds a person to the parents assignedTasks list and returns true if added
    public boolean addPerson(Person person) {
        assignedPersons.add(person);
        int m = assignedPersons.size();
        return ((assignedPersons.get(m - 1) == person));
    }

// needs to be tested + implemented
/*    // MODIFIES: this
    // EFFECTS: returns list of Names under a task
    public ArrayList<String> teamNames() {
        ArrayList<String> teamMemberNames = new ArrayList<String>();
        for (Person assignedPerson : assignedPersons) {
            String addName = assignedPerson.name;
            if (!(teamMemberNames.contains(addName))) {
                teamMemberNames.add(addName);
            }
        }
        return teamMemberNames;
    }*/


    // EFFECTS: sets isDone to true
    public Boolean setDone() {
        isDone = true;
        return true;
    }

    // EFFECTS: returns size of team
    public int getTeamSize() {
        return assignedPersons.size();
    }

    // EFFECTS: returns subTaskId
    public int getSubTaskId() {
        return subTaskId;
    }


}
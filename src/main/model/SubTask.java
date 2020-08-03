package model;

import model.MasterTask;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SubTask extends MasterTask {
    private final int subTaskId;
    private static final AtomicInteger id = new AtomicInteger(0); //initializes at 0
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html
    public HashSet<Person> assignedPersons;   // list of people working on this task
    String teamMemberNames;                   // names of people working on this task
    HashSet<SubTask> subAssignedTasks;        // for figuring out after project is over
    // Boolean isDone = false;                // for figuring out after project is over


    // REQUIRES: string title not empty, id >= 0 (create exceptions here)
    // MODIFIES: this
    // EFFECTS: Creates a subtask with a given title and id, and an empty list of assignedPersons
    //          and an empty list of teamMemberNames
    public SubTask(String title) {
        super(title);
        this.projectTitle = title;
        assignedPersons = new HashSet<Person>();
        teamMemberNames = "No Members Assigned!";
        subAssignedTasks = new HashSet<SubTask>();
        subTaskId = id.incrementAndGet();

    }

    // MODIFIES: this, assignedPersons
    // EFFECTS: adds person to assignedPersons list (hashset)
    public void addPerson(Person person) {
        assignedPersons.add(person);
    }

    // implement
    // MODIFIES: this
    // EFFECTS: returns list of Names under a task
    public String getTeamMemberNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Person assignedPerson : assignedPersons) {
            names.add(assignedPerson.name);

        }
        if (names.isEmpty()) {
            return teamMemberNames;
        }
        Collections.sort(names);
        return names.toString();
    }

    // EFFECTS: adds subTask to this subTask (not implemented)
    @Override
    public Boolean addSubTask(SubTask subTask) {
        if (!(this.subAssignedTasks.contains(subTask))) {
            this.subAssignedTasks.add(subTask);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns size of team
    public int getTeamSize() {
        return assignedPersons.size();
    }

    // EFFECTS: returns subTaskId
    public int getSubTaskId() {
        return subTaskId;
    }

    // testing
/*
    // EFFECTS: sets isDone to true
    public Boolean setDone() {
        isDone = true;
        return true;
    }
*/

/*    @Override
    public String getSubTaskTitle() {

    }*/
}
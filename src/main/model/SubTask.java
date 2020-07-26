package model;

import model.MasterTask;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SubTask extends MasterTask {
    int taskNum;
    String taskDetails;

    ArrayList<Person> assignedPersons;         // list of people working on this task
    ArrayList<String> teamMemberNames;         // names of people working on this task
    //ArrayList<SubTask> assignedTasks;        // figure out inheritance later
    Boolean isDone = false;
    String subTaskTitle;

    // REQUIRES: string title not empty, id >= 0 (create exceptions here)
    // MODIFES:
    // EFFECTS: Creates a subtask with a given title and id, and an empty list of assignedPersons
    //          and an emply list of teamMemberNames
    public SubTask(String title, int id) {
        super(title);
        this.subTaskTitle = title;
        this.taskNum = id;
        assignedPersons = new ArrayList<Person>();
        teamMemberNames = new ArrayList<String>();
        //set(id);
    }

    // MODIFIES: this, assignedPersons
    // EFFECTS: adds a person to the parents assignedTasks list and returns true if added
    public boolean addPerson(Person person) {
        assignedPersons.add(person);
        int m = assignedPersons.size();
        if (m == 0) {
            return false;
        } else {
            return ((assignedPersons.get(m - 1) == person));
        }
    }

    // MODIFIES: this
    public ArrayList<String> teamNames() {
        ArrayList<String> teamMemberNames = new ArrayList<String>();
        for (Person assignedPerson : assignedPersons) {
            String addName = assignedPerson.name;
            if (!(teamMemberNames.contains(addName))) {
                teamMemberNames.add(addName);
            }
        }
        return teamMemberNames;
    }

    // EFFECTS: returns taskNum
    public int getTaskNum() {
        return taskNum;
    }

    // EFFECTS: sets isDone to true
    public void setDone() {
        isDone = true;
    }

    public int getTeamSize() {
        return assignedPersons.size();
    }


}

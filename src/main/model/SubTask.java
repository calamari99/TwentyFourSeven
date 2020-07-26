package model;

import model.MasterTask;

import java.util.ArrayList;

public class SubTask {

    String taskDetails;
    ArrayList<Person> assignedPersons;         // list of people working on this task
    ArrayList<SubTask> assignedTasks;
    Boolean isDone = false;
    String subTaskTitle;


    public SubTask(String title, int id) {
        this.subTaskTitle = title;
        //set(id);

    }


}

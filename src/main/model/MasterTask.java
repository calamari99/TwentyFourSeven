package model;

import model.SubTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;


public class MasterTask {
    //String name; // creator name
    String projectTitle;
    String projectDetails;
    ArrayList<SubTask> assignedTasks;

    // EFFECTS: Constructs a MasterTask with a title, an empty list of SubTasks
    public MasterTask(String title) {
        this.projectTitle = title;
        assignedTasks = new ArrayList<SubTask>();


        // figure out what I want to do with empty title later
        // what should I extend to its children?

        // current date
        //setting current date
/*        Date initDate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("E yyyy.MM.dd"); //SimpleDateFormat used to parse
        System.out.println(simpleDate.format(initDate)); https://www.tutorialspoint.com/java/java_date_time.htm*/
    }

    // MODIFIES: this, project details;
    // EFFECTS: takes in user input and sets project detail variable to input, if empty return projectDetails
    public String setProjectDetails(String details) {
        projectDetails = details;
        return projectDetails;
    }


    // MODIFIES: this, assignedTasks
    // EFFECTS: adds a subtask to the parents assignedTasks list
    public void addSubTask(SubTask subtask) {
        assignedTasks.add(subtask);
/*        int m = assignedTasks.size();
        return ((assignedTasks.get(m - 1) == subtask));*/
    }


    // EFFECTS: returns task title
    public String getTitle() {
        return projectTitle;
    }

    // EFFECTS: returns project details
    public String getProjectDetails() {
        return projectDetails;
    }


}

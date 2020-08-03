package model;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.SubTask;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;


public class MasterTask {
    //String name; // creator name
    public String projectTitle;
    String projectDetails = "placeholder";
    ArrayList<SubTask> assignedTasks;
    public String subTaskNames;


    // EFFECTS: Constructs a MasterTask with a title, an empty list of SubTasks
    public MasterTask(String title) {
        projectTitle = title;
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
    // EFFECTS: if not already in list, adds a subtask to the parents assignedTasks and returns true, false otherwise
    public Boolean addSubTask(SubTask subTask) {
        if (!(assignedTasks.contains(subTask))) {
            assignedTasks.add(subTask);
            return true;
        } else {
            return false;
        }
    }

/*    // EFFECT: returns list of assigned subTasks
    public ArrayList<SubTask> getAssignedTasks() {
        return assignedTasks;
    }*/

    public String getSubNames() {
        subTaskNames = "SubTasks:";
        for (SubTask subTask : assignedTasks) {
            subTaskNames = subTaskNames + " " + subTask.projectTitle;
        }
        return subTaskNames;
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

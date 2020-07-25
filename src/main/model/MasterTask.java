package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MasterTask {

    public MasterTask(String title) {
        String name; // creator name
        String projectDetails;
        String projectTitle = title;

        //setting current date
        Date initDate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("E yyyy.MM.dd"); //SimpleDateFormat used to parse
        System.out.println(simpleDate.format(initDate)); //https://www.tutorialspoint.com/java/java_date_time.htm

        // what should I extend to its children?
        // current date

        ArrayList<SubTask> membersOnTask = new ArrayList<SubTask>();
    }


}

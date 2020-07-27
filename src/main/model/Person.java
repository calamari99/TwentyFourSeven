package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Person {
    String name;
    private final int personId;
    private static final AtomicInteger id = new AtomicInteger(0); // initializes at 0
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html


    // MODIFIES: this, name
    // EFFECT: creates a new person with a given name
    public Person(String name) {
        this.name = name;
        personId = id.incrementAndGet(); // increments and updates by 1
        //name = memberName; // if input in console
        // add work schedule down the line?
    }

    // EFFECT: returns name of person
    public String getName() {
        return name;
    }

    // EFFECT: return the personId
    public int getPersonId() {
        return personId;
    }
}

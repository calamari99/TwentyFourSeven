package model;

public class Person {
    String name;

    // MODIFIES: this, name
    // EFFECT: creates a new person with a given name
    public Person(String name) {
        this.name = name;
        //name = memberName; // if input in console
        // add work schedule down the line?
    }


    public String getName() {
        return name;
    }
}

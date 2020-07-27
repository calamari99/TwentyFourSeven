package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SubTaskTest {

    SubTask subTask1;
    SubTask subTask2;
    Person person1;
    Person person2;

    @BeforeEach
    void runBefore() {
        subTask1 = new SubTask("");
        subTask2 = new SubTask("test");
        person1 = new Person("Lilo");
        person2 = new Person("Stitch");
    }

    @Test
    void testSubTaskConstructor() {
        assertEquals(subTask1.getTitle(),"");
        assertEquals(subTask2.getTitle(),"test");
/*
        assertEquals(subTask1.getTaskNum(),1);
        assertEquals(subTask2.getTaskNum(),2);*/
    }

    @Test
    void testAddPerson() {
        assertTrue(subTask1.addPerson(person1)); // empty -> not empty
        assertTrue(subTask1.addPerson(person1)); // adding to not empty
    }

/*    @Test
    void testGetTaskNum() {
        assertEquals(subTask1.getTaskNum(),1);
        assertEquals(subTask2.getTaskNum(),2);
    }*/

    @Test
    void testSetDone() {
        assertTrue(subTask1.setDone());
    }

    @Test
    void testGetTeamSize() {
        subTask1.addPerson(person1);
        assertEquals(subTask1.getTeamSize(),1);
        subTask1.addPerson(person1);
        assertEquals(subTask1.getTeamSize(),2);
    }

    //run separately since id is incrementing based on constructor calls
    @Test
    void testGetSubTaskId() {
        assertEquals(subTask1.getSubTaskId(),1);
        assertEquals(subTask2.getSubTaskId(),2);
    }

}

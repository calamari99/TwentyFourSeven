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
        subTask1.addPerson(person1);
        assertEquals(subTask1.assignedPersons.size(),1); // empty -> not empty
        subTask1.addPerson(person2);
        assertEquals(subTask1.assignedPersons.size(),2); // adding to not empty
        assertTrue(subTask1.assignedPersons.contains(person1));
        assertTrue(subTask1.assignedPersons.contains(person2));
        subTask1.addPerson(person1);
        assertEquals(subTask1.assignedPersons.size(),2); // existing person was added again
    }

/*    @Test
    void testSetDone() {
        assertTrue(subTask1.setDone());
    }*/

    @Test
    void testAddSubtoSubTask() {
        assertTrue(subTask1.addSubTask(subTask2));              // empty -> not empty
        assertFalse(subTask1.addSubTask(subTask2));              // not empty -> not empty
    }

    @Test
    void testGetTeamSize() {
        subTask1.addPerson(person1);
        assertEquals(subTask1.getTeamSize(),1);
        subTask1.addPerson(person1);
        assertEquals(subTask1.getTeamSize(),1);
        subTask1.addPerson(person2);
        assertEquals(subTask1.getTeamSize(),2);
    }

    // testGetSubTaskId is the last test called, thus BeforeEach has been called 6 times before this test runs
    @Test
    void testGetSubTaskId() {
        assertEquals(subTask1.getSubTaskId(),1);
        assertEquals(subTask2.getSubTaskId(),2);
    }

    @Test
    void testGetTeamMemberNames() {
        assertEquals("No Members Assigned!",subTask1.getTeamMemberNames());
        subTask1.addPerson(person1);
        subTask1.addPerson(person2);
        assertEquals("[Lilo, Stitch]",subTask1.getTeamMemberNames());
        subTask1.addPerson(person2);
        assertEquals("[Lilo, Stitch]",subTask1.getTeamMemberNames());

    }

}

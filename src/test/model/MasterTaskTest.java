package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.InvalidNameException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MasterTaskTest {
    MasterTask testTask1;
    MasterTask testTask2;
    MasterTask testTask3;
    MasterTask testTask4;
    MasterTask testTask5;


    SubTask subTask1;
    SubTask subTask2;
    SubTask subTask3;



    @BeforeEach
    void runBefore() {
        testTask1 = new MasterTask("hello");
        testTask2 = new MasterTask("testingMasterTask");
        testTask3 = new MasterTask("");      // not allowed
        testTask4 = new MasterTask("12345"); // not allowed
        testTask5 = new MasterTask("ad123"); // allowed

        subTask1 = new SubTask("sub1");
        subTask2 = new SubTask("sub2");
        subTask3 = new SubTask("");

    }

    @Test
    void testMasterTaskConstructorAndNoException() throws InvalidNameException {
        assertEquals(testTask1.getTitle(),"hello");
        assertEquals(testTask2.getTitle(),"testingMasterTask");
    }

    @Test
    void testGetTitleOnlyDigits() throws InvalidNameException {
        try {
            testTask1.getTitle();
            testTask2.getTitle();
            testTask3.getTitle();
            fail("Exception was not thrown");
        } catch (InvalidNameException e) {
            assertEquals(testTask3.getTitle(),"Invalid Name");
        }
    }

    @Test
    void testTitleTooShort() throws InvalidNameException {
        try {
            testTask1.getTitle();
            testTask2.getTitle();
            testTask4.getTitle();
            fail("Exception was not thrown");
        } catch (InvalidNameException e) {
            assertEquals(testTask4.getTitle(),"Invalid Name");
        }

    }

    @Test
    void testAddSubTask() {
        assertTrue(testTask1.addSubTask(subTask1));              // empty -> not empty
        assertEquals(testTask1.assignedTasks.size(), 1);  // test size
        assertTrue(testTask1.addSubTask(subTask2));              // not empty -> not empty
        assertEquals(testTask1.assignedTasks.size(), 2);  // test size
        assertEquals(testTask1.assignedTasks.get(0),subTask1);   // test boolean first value
        assertEquals(testTask1.assignedTasks.get(1),subTask2);   // test boolean second value
        assertFalse(testTask1.addSubTask(subTask1)); // adding subtask1 in list again
    }


    @Test
    void testSetProjectDetails() {
        testTask1.setProjectDetails("");
        assertEquals(testTask1.getProjectDetails(), ""); //empty empty
        testTask1.setProjectDetails("not empty");
        assertEquals(testTask1.getProjectDetails(), "not empty"); //empty -> not empty
        testTask1.setProjectDetails("not empty again");
        assertEquals(testTask1.getProjectDetails(), "not empty again"); //not empty -> not empty
        testTask1.setProjectDetails("");
        assertEquals(testTask1.getProjectDetails(), ""); //not empty -> empty (makeshift way of deleting details)
    }

    @Test
    void testGetSubNames() {
        testTask2.addSubTask(subTask1);
        testTask2.addSubTask(subTask2);
        assertEquals(testTask2.getSubNames(),"SubTasks: sub1 sub2");
    }

    @Test
    void testIndexSubNames() {
        testTask2.addSubTask(subTask1);
        testTask2.addSubTask(subTask2);
        assertEquals(testTask2.indexSubNames(),
                "SubTasks: (1.) sub1 (2.) sub2");

        testTask1.addSubTask(subTask1);
        assertEquals(testTask1.indexSubNames(), "SubTasks: (1.) sub1");

        testTask3.addSubTask(subTask3);
        assertEquals(testTask3.indexSubNames(), "SubTasks: (1.) ");
    }

    @Test
    void testGetAssignedTasks() {
        testTask2.addSubTask(subTask1);
        testTask2.addSubTask(subTask2);
        assertEquals(testTask2.getAssignedTasks(),testTask2.assignedTasks);
    }
}

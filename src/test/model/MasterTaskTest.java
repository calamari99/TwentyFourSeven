package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasterTaskTest {
    MasterTask testTask1;
    MasterTask testTask2;
    SubTask subTask1;
    SubTask subTask2;

    @BeforeEach
    void runBefore() {
        testTask1 = new MasterTask("");
        testTask2 = new MasterTask("testingMasterTask");
        subTask1 = new SubTask("");
        subTask2 = new SubTask("testingSubTask");
    }

    @Test
    void testMasterTaskConstructor(){
        assertEquals(testTask1.getTitle(),"");
        assertEquals(testTask2.getTitle(),"testingMasterTask");
    }
    @Test
    void testAddSubTask() {
        assertTrue(testTask1.addSubTask(subTask1));
        assertTrue(testTask1.addSubTask(subTask1));
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
}

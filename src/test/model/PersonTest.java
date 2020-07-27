package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person p1;
    Person p2;


    @BeforeEach
    void runBefore() {
        p1 = new Person("");
        p2 = new Person("Lilo");
    }

    @Test
    void testPersonConstructor() {
        assertEquals("", p1.getName());
        assertEquals("Lilo", p2.getName());
    }

    @Test
    void testGetName() {
        assertEquals(p1.getName(), "");
        assertEquals(p2.getName(), "Lilo");
    }

    @Test
    void testGetPersonId() {
        assertEquals(p1.getPersonId(),5); // BeforeEach has run 4 times before
        assertEquals(p2.getPersonId(),6); // BeforeEach has run 5 times before
    }


}

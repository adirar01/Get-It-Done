package model;

import exceptions.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Task class
public class TaskTest {
    private Task testTask;

    @BeforeEach
    public void setUpNoException() {
        try {
            testTask = new Task("CS210 Homework", "02/20/20");
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    public void setUpExceptionThrownBothEmpty() {
        try {
            testTask = new Task("", "");
            fail();
        } catch (EmptyStringException e) {
            // expected
        }
    }

    @Test
    public void setUpExceptionThrownNameEmpty() {
        try {
            testTask = new Task("", "");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    public void setUpExceptionThrownDateEmpty() {
        try {
            testTask = new Task("", "");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    public void testConstructorNonEmpty() {
        assertEquals("CS210 Homework", testTask.getTaskName());
        assertEquals("02/20/20", testTask.getDueDate());
    }

    @Test
    public void testSetTaskNameNoException() {
        try {
            testTask.setTaskName("CS121 Assignment");
        } catch (EmptyStringException e) {
            fail();
        }
        assertEquals("CS121 Assignment", testTask.getTaskName());
    }

    @Test
    public void testSetTaskNameExceptionThrown() {
        try {
            testTask.setTaskName("");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    public void testSetDueDateNoException() {
        try {
            testTask.setDueDate("03/30/20");
        } catch (EmptyStringException e) {
            fail();
        }
        assertEquals("03/30/20", testTask.getDueDate());
    }

    @Test
    public void testSetDueDateExceptionThrown() {
        try {
            testTask.setDueDate("");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    public void testPrintTask() {
        assertEquals("Task: CS210 Homework\t\tDue: 02/20/20", testTask.printTask());
    }


}

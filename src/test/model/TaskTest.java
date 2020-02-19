package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Task class
public class TaskTest {
    private Task testTask;

    @BeforeEach
    public void setUp() {
        testTask = new Task("CS210 Homework", "02/20/20");
    }

    @Test
    public void testConstructor() {
        assertEquals("CS210 Homework", testTask.getTaskName());
        assertEquals("02/20/20", testTask.getDueDate());
    }

    @Test
    public void testSetTaskName() {
        testTask.setTaskName("CS121 Assignment");
        assertEquals("CS121 Assignment", testTask.getTaskName());
    }

    @Test
    public void testSetDueDate() {
        testTask.setDueDate("03/30/20");
        assertEquals("03/30/20", testTask.getDueDate());
    }

    @Test
    public void testPrintTask() {
        assertEquals("Task: CS210 Homework\t\tDue: 02/20/20", testTask.printTask());
    }


}

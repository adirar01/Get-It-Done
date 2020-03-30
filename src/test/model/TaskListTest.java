package model;

import exceptions.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for TaskList class
public class TaskListTest {
    private TaskList testTaskList;
    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    public void setUp() {
        testTaskList = new TaskList();
        try {
            task1 = new Task("task1", "01/20/20");
            task2 = new Task("task2", "02/20/20");
            task3 = new Task("task3", "03/20/20");
        } catch (EmptyStringException e) {
            fail(); // should not happen, necessary for compilation
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testTaskList.numTasks());
    }

    @Test
    public void testAddTaskSingle() {
        assertEquals(0, testTaskList.numTasks());
        assertEquals(task1.printTask() + " added successfully!", testTaskList.addTask(task1));
        assertEquals(1, testTaskList.numTasks());
    }

    @Test
    public void testAddTaskMultiple() {
        assertEquals(0, testTaskList.numTasks());
        assertEquals(task1.printTask() + " added successfully!", testTaskList.addTask(task1));
        assertEquals(task2.printTask() + " added successfully!", testTaskList.addTask(task2));
        assertEquals(task3.printTask() + " added successfully!", testTaskList.addTask(task3));
        assertEquals(3, testTaskList.numTasks());
    }

    @Test
    public void testAddTaskUpperBound() {
        assertEquals(0, testTaskList.numTasks());
        for (int count = 0; count < TaskList.MAX_NUM_TASKS; count++) {
            testTaskList.addTask(task1);
        }
        assertEquals(TaskList.MAX_NUM_TASKS, testTaskList.numTasks());
    }

    @Test
    public void testAddTaskBeyondMaximum() {
        for (int count = 0; count < TaskList.MAX_NUM_TASKS; count++) {
            testTaskList.addTask(task1);
        }
        assertEquals(TaskList.MAX_NUM_TASKS, testTaskList.numTasks());

        assertEquals("Your Task List is currently full! Please delete a Task(s) and try again!", testTaskList.addTask(task2));

        assertEquals(TaskList.MAX_NUM_TASKS, testTaskList.numTasks());
    }

    @Test
    public void testDeleteTaskSingle() {
        assertEquals(0, testTaskList.numTasks());

        testTaskList.addTask(task1);
        assertEquals(1, testTaskList.numTasks());

        testTaskList.deleteTask(1);
        assertEquals(0, testTaskList.numTasks());
    }

    @Test
    public void testDeleteTaskMultiple() {
        assertEquals(0, testTaskList.numTasks());

        testTaskList.addTask(task1);
        testTaskList.addTask(task1);
        testTaskList.addTask(task1);

        assertEquals(3, testTaskList.numTasks());

        testTaskList.deleteTask(3);
        assertEquals(2, testTaskList.numTasks());

        testTaskList.deleteTask(1);
        assertEquals(1, testTaskList.numTasks());

        testTaskList.deleteTask(1);
        assertEquals(0, testTaskList.numTasks());
    }

    @Test
    public void testPrintTaskListEmpty() {
        assertEquals("Yay! Your task list is empty!\n\nUse the 'Add Task' option to add a new task.", testTaskList.printTaskList());
    }

    @Test
    public void testPrintTaskListSingle() {
        testTaskList.addTask(task1);
        assertEquals("\t\t\tTask List:\n\n" + "\n1. " + task1.printTask(), testTaskList.printTaskList());
    }

    @Test
    public void testPrintTaskListMultiple() {
        testTaskList.addTask(task1);
        testTaskList.addTask(task2);
        testTaskList.addTask(task3);
        assertEquals("\t\t\tTask List:\n\n" + "\n1. " + task1.printTask() + "\n2. " + task2.printTask() + "\n3. " + task3.printTask(), testTaskList.printTaskList());
    }

    @Test
    public void testGetTaskSingle() {
        testTaskList.addTask(task1);

        assertEquals(task1, testTaskList.getTask(1));
    }

    @Test
    public void testGetTaskMultiple() {
        testTaskList.addTask(task1);
        testTaskList.addTask(task2);
        testTaskList.addTask(task3);

        assertEquals(task3, testTaskList.getTask(3));
        assertEquals(task2, testTaskList.getTask(2));
        assertEquals(task1, testTaskList.getTask(1));
    }

    @Test
    public void testGetIndexOfSingle() {
        testTaskList.addTask(task1);
        assertEquals(0, testTaskList.getIndexOf(task1));
    }

    @Test
    public void testGetIndexOfMultiple() {
        testTaskList.addTask(task1);
        testTaskList.addTask(task2);
        testTaskList.addTask(task3);

        assertEquals(0, testTaskList.getIndexOf(task1));
        assertEquals(1, testTaskList.getIndexOf(task2));
        assertEquals(2, testTaskList.getIndexOf(task3));
    }
}



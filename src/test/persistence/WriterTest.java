package persistence;

import exceptions.EmptyStringException;
import model.Task;
import model.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.invoke.empty.Empty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Writer class
public class WriterTest {

    /*
     * WriterTest adapted from Teller Sample
     * */

    private static final String TEST_FILE = "./data/testWrite.txt";
    private Writer testWriter;
    private TaskList testTaskList;
    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    public void setUp() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));

        try {
            task1 = new Task("save middle earth", "02/20/20");
            task2 = new Task("visit bilbo at the shire", "04/15/20");
            task3 = new Task("restock closet with white majestic gowns", "03/17/20");
        } catch (EmptyStringException e) {
            fail(); // necessary for compilation, should not happen
        }

        testTaskList = new TaskList();
        testTaskList.addTask(task1);
        testTaskList.addTask(task2);
        testTaskList.addTask(task3);
    }

    @Test
    public void testWriteTaskList() {
        // save task list to file
        ArrayList<Task> currentTaskList = new ArrayList<>(); // making a list of tasks from already formed task list.

        for (int count = 0; count < testTaskList.numTasks(); count++) {
            currentTaskList.add(testTaskList.getTask(count + 1));
        }

        assertEquals(3, currentTaskList.size());

        for (Task task : currentTaskList) {
            testWriter.write(task); // write each task to file on a new line?
        }

        testWriter.close();

        try {
            TaskList taskListReadFromFile = new TaskList();
            taskListReadFromFile = Reader.readTaskList(new File(TEST_FILE)); // read task list only has size 1?

            assertEquals("save middle earth", taskListReadFromFile.getTask(1).getTaskName());
            assertEquals("visit bilbo at the shire", taskListReadFromFile.getTask(2).getTaskName());
            assertEquals("restock closet with white majestic gowns", taskListReadFromFile.getTask(3).getTaskName());

            assertEquals("02/20/20", taskListReadFromFile.getTask(1).getDueDate());
            assertEquals("04/15/20", taskListReadFromFile.getTask(2).getDueDate());
            assertEquals("03/17/20", taskListReadFromFile.getTask(3).getDueDate());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

}

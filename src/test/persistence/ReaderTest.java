package persistence;

import exceptions.EmptyStringException;
import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Reader class
public class ReaderTest {

    /*
    * ReaderTest adapted from Teller Sample
    * */

    private static final String TEST_FILE_1 = "./data/testTaskTracker1.txt";
    private static final String TEST_FILE_2 = "./data/testTaskTracker2.txt";
    private static final String TEST_FILE_INVALID = "./data/middleEarthSecretMissions.txt";

    @Test
    public void testConstructor() {
        Reader reader = new Reader();
        assertNotNull(reader);
    }

    @Test
    public void testParseAccountsFile1() {
        try {
            TaskList taskListReadFromFile = new TaskList();
            taskListReadFromFile = Reader.readTaskList(new File(TEST_FILE_1));

            assertEquals("save middle earth", taskListReadFromFile.getTask(1).getTaskName());
            assertEquals("visit bilbo at the shire", taskListReadFromFile.getTask(2).getTaskName());
            assertEquals("restock closet with white majestic gowns", taskListReadFromFile.getTask(3).getTaskName());

            assertEquals("02/20/20", taskListReadFromFile.getTask(1).getDueDate());
            assertEquals("04/15/20", taskListReadFromFile.getTask(2).getDueDate());
            assertEquals("03/17/20", taskListReadFromFile.getTask(3).getDueDate());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (EmptyStringException ex) {
            fail();
        }
    }

    @Test
    public void testParseAccountsFile2() {
        try {
            TaskList taskListReadFromFile = new TaskList();
            taskListReadFromFile = Reader.readTaskList(new File(TEST_FILE_2));

            assertEquals("CPSC 210 P2 due", taskListReadFromFile.getTask(1).getTaskName());
            assertEquals("Get a cookie from Blue Chip Cafe", taskListReadFromFile.getTask(2).getTaskName());
            assertEquals("Read a book at Tower Beach", taskListReadFromFile.getTask(3).getTaskName());

            assertEquals("02/26/20", taskListReadFromFile.getTask(1).getDueDate());
            assertEquals("02/22/20", taskListReadFromFile.getTask(2).getDueDate());
            assertEquals("03/15/20", taskListReadFromFile.getTask(3).getDueDate());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (EmptyStringException ex) {
            fail();
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readTaskList(new File(TEST_FILE_INVALID));
            fail("Not expecting to reach here");
        } catch (IOException e) {
            // expected
        } catch (EmptyStringException ex) {
            fail();
        }
    }

}


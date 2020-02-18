package persistence;

import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static persistence.Reader.getReader;

// Tests for Reader class
public class ReaderTest {
    private static final String TEST_FILE_1 = "./data/testTaskTracker1.txt";
    private static final String TEST_FILE_2 = "./data/testTaskTracker2.txt";
    private static final String TEST_FILE_INVALID = "./data/middleEarthSecretMissions.txt";

    @Test
    public void testGetReader() {
        assertNotNull(getReader());
    }

    @Test
    public void testParseAccountsFile1() {
        try {
            TaskList taskListReadFromFile = new TaskList();
            taskListReadFromFile = Reader.readTaskList(new File(TEST_FILE_1));

            assertEquals("save middle earth", taskListReadFromFile.produceTask(1).getTaskName());
            assertEquals("visit bilbo at the shire", taskListReadFromFile.produceTask(2).getTaskName());
            assertEquals("restock closet with white majestic gowns", taskListReadFromFile.produceTask(3).getTaskName());

            assertEquals("02/20/20", taskListReadFromFile.produceTask(1).getDueDate());
            assertEquals("04/15/20", taskListReadFromFile.produceTask(2).getDueDate());
            assertEquals("03/17/20", taskListReadFromFile.produceTask(3).getDueDate());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testParseAccountsFile2() {
        try {
            TaskList taskListReadFromFile = new TaskList();
            taskListReadFromFile = Reader.readTaskList(new File(TEST_FILE_2));

            assertEquals("CPSC 210 P2 due", taskListReadFromFile.produceTask(1).getTaskName());
            assertEquals("Get a cookie from Blue Chip Cafe", taskListReadFromFile.produceTask(2).getTaskName());
            assertEquals("Read a book at Tower Beach", taskListReadFromFile.produceTask(3).getTaskName());

            assertEquals("02/26/20", taskListReadFromFile.produceTask(1).getDueDate());
            assertEquals("02/22/20", taskListReadFromFile.produceTask(2).getDueDate());
            assertEquals("03/15/20", taskListReadFromFile.produceTask(3).getDueDate());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readTaskList(new File(TEST_FILE_INVALID));
            fail("Not expecting to reach here");
        } catch (IOException e) {
            // expected
        }
    }

}


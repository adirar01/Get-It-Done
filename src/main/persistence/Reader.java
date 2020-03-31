package persistence;

import exceptions.EmptyStringException;
import model.Task;
import model.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read task list and user data from a file.
public class Reader {
    /* persistence implementation adapted from Teller Sample*/

    public static final String DELIMITER = ",";

    //EFFECTS: has no special behavior once a reader object is created
    //because reader objects are not necessary
    public Reader() {
    }

    // EFFECTS: returns the task list parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static TaskList readTaskList(File file) throws IOException, EmptyStringException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a TaskList parsed from list of strings
    // where each string contains data for a single task
    private static TaskList parseContent(List<String> fileContent) throws EmptyStringException {
        TaskList readTaskList = new TaskList();

        Task readTask;

        for (String line : fileContent) {
            // each line is a task where the name and due date are separated by the DELIMITER
            ArrayList<String> lineComponents = splitString(line);
            String readTaskName = lineComponents.get(0);
            String readDueDate = lineComponents.get(1);
            readTask = new Task(readTaskName, readDueDate);
            readTaskList.addTaskPrintMessage(readTask);


        }

        return readTaskList;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

}

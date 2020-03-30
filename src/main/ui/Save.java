package ui;

import model.Task;
import model.TaskList;
import persistence.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Save {

    static void saveTaskList(TaskList taskList, String writePath)
            throws FileNotFoundException, UnsupportedEncodingException {
        Writer writer = new Writer(new File(writePath));

        ArrayList<Task> currentTaskList = new ArrayList<>(); // making a list of tasks from task list

        for (int count = 0; count < taskList.numTasks(); count++) {
            currentTaskList.add(taskList.getTask(count + 1));
        }

        for (Task task : currentTaskList) {
            writer.write(task); // writes each task to file on a new line
        }

        writer.close();
    }
}

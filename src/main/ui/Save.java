package ui;

import model.Task;
import model.TaskList;
import persistence.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

// Responsible for maintaining saving functionality of program
public class Save {

    // EFFECTS: saves given task list to file given a file path
    static void saveTaskList(TaskList taskList, String writePath)
            throws FileNotFoundException, UnsupportedEncodingException {
        Writer writer = new Writer(new File(writePath));

        for (Task task : taskList) {
            writer.write(task);
        }
        writer.close();
    }
}

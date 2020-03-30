package model;

import exceptions.EmptyStringException;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// Represents a task with a description and due date
public class Task implements Saveable {
    private String taskName;
    private String dueDate;

    /*
     * REQUIRES: taskName and dueDate is not null, dueDate entered in form MM/DD/YY
     * EFFECTS: if task is specified with empty taskName or dueDate, throws EmptyStringException
     *          otherwise creates a new task with description of taskName and due date of dueDate
     * */
    public Task(String taskName, String dueDate) throws EmptyStringException {
        if (taskName.equals("") || dueDate.equals("")) {
            throw new EmptyStringException();
        } else {
            this.taskName = taskName;
            this.dueDate = dueDate;
        }
    }

    //EFFECTS: if taskName is specified with no text, throws EmptyStringException, otherwise sets name of task to
    //         inputted taskName
    public void setTaskName(String taskName) throws EmptyStringException {
        if (taskName.equals("")) {
            throw new EmptyStringException();
        } else {
            this.taskName = taskName;
        }
    }

    public String getTaskName() {
        return taskName;
    }

    //EFFECTS: if dueDate is specified with no text, throws EmptyStringException, otherwise sets due date of task to
    //         inputted dueDate
    public void setDueDate(String dueDate) throws EmptyStringException {
        if (dueDate.equals("")) {
            throw new EmptyStringException();
        } else {
            this.dueDate = dueDate;
        }
    }

    public String getDueDate() {
        return dueDate;
    }

    /*
     * EFFECTS: prints task description and due date
     * */
    public String printTask() {
        return "Task: " + this.taskName + "\t\t" + "Due: " + this.dueDate;
    }

    //MODIFIES: printWriter
    //EFFECTS: saves task name and task due date to file
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(taskName);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(dueDate);
    }
}

package model;

// Represents a task with a description and due date
public class Task {
    private String taskName;
    private String dueDate;

    /*
    * REQUIRES: taskName and dueDate is not null, dueDate entered in form MM/DD/YY
    * EFFECTS: creates a new task with description of taskName and due date of dueDate
    * */
    public Task(String taskName, String dueDate) {
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
}

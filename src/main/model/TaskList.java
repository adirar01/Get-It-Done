package model;

import java.util.ArrayList;

// Represents a task list with pending tasks to complete
public class TaskList {
    private ArrayList<Task> taskList;
    public static final int MAX_NUM_TASKS = 10;

    /*
     * EFFECTS: creates a new empty task list
     * */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: if task list has space, add task to task list, otherwise prints message signalling failure
     * and does not add the latest task
     * */
    public String addTaskPrintMessage(Task task) {
        if (numTasks() < MAX_NUM_TASKS) {
            taskList.add(task);
            return task.printTask() + " added successfully!";
        } else {
            return "Your Task List is currently full! Please delete a Task(s) and try again!";
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if task list has space, add task to task list, otherwise does nothing
     * */
    public void addTask(Task task) {
        if (numTasks() < MAX_NUM_TASKS) {
            taskList.add(task);
        }
    }

    /*
     * REQUIRES: task list is not empty, and i >= 1
     * MODIFIES: this
     * EFFECTS: deletes a task from the task list at a given number/ position in printTaskList()
     * */
    public void deleteTask(int i) {
        taskList.remove(i - 1);
    }

    /*
     * EFFECTS: prints and numbers tasks in task list from 1 and on
     * */
    public String printTaskList() {
        StringBuilder printedTaskList = new StringBuilder();
        if (numTasks() == 0) {
            return "Yay! Your task list is empty!\n\nUse the 'Add Task' option to add a new task.";
        } else {
            printedTaskList.append("\t\t\tTask List:\n\n");
            for (int i = 0; i < numTasks(); i++) {
                printedTaskList.append("\n").append(i + 1).append(". ").append(taskList.get(i).printTask());
            }
        }
        return String.valueOf(printedTaskList);
    }

    /*
     * EFFECTS: returns number of elements in task list
     * */
    public int numTasks() {
        return taskList.size();
    }


    /*
     * EFFECTS: returns index of given task in task list; otherwise returns -1.
     * */
    public int getIndexOf(Task task) { // TODO: do i need to test?
        return taskList.indexOf(task);
    }

    /*
     * REQUIRES: a task must exist at the given index
     * EFFECTS: returns selected task
     * */
    public Task getTask(int i) { // question: specification needed?
        return taskList.get(i - 1);
    }

}


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
     * EFFECTS: if task list has space, add task to task list, otherwise signal failure
     *  and does not add the latest task
     * */
    public String addTask(Task task) {
        if (numTasks() < MAX_NUM_TASKS) {
            taskList.add(task);
            return task.printTask() + " added successfully!";
        } else {
            return "Your Task List is currently full! Please delete a Task(s) and try again!";
        }
    }

    /*
     * REQUIRES: task list is not empty, i >= 1
     * MODIFIES: this
     * EFFECTS: deletes a task from the task list at a given number/ position
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
            printedTaskList.append("\t\tTask List:\n\n");
            for (int i = 0; i < numTasks(); i++) {
                printedTaskList.append("\n" + (i + 1) + ". " + taskList.get(i).printTask());
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
}


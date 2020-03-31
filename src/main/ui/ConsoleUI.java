package ui;

import exceptions.EmptyStringException;
import model.Task;
import model.TaskList;
import model.User;
import persistence.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

// Console UI for Get It Done application
public class ConsoleUI {

    //***
    // note: set up of Get It Done! application UI is adapted from Teller sample application.
    // ***

    private User defaultUser;
    private Scanner input;
    private TaskList taskList;
    private boolean appStatus;
    private boolean homeStatus;
    private static final String TASK_TRACKER_FILE = "./data/GandalfTaskTracker.txt";

    // EFFECTS: runs the Get It Done application
    public ConsoleUI() {
        runGetItDone();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for application
    public void runGetItDone() {
        appStatus = true;
        homeStatus = true;
        loadTaskTracker();

        while (appStatus) {
            runHome();
            if (!appStatus) {
                break;
            }
            displayMenu();
            String command = input.next();
            input.nextLine(); // added to prevent line skipping for doAddTask
            if (command.equals("6")) {
                appStatus = false;
                System.out.println("\nThank you for using Get It Done! Have a nice day!");
            } else {
                processCommand(command);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user input for home page
    public void runHome() {
        String command = null;

        while (homeStatus) {
            displayHomeScreen();
            command = input.nextLine();
            if (command.equals("1")) {
                if (login()) {
                    homeStatus = false;
                }
            } else if (command.equals("2")) {
                homeStatus = false;
                appStatus = homeStatus;
                System.out.println("\nThank you for using Get It Done! Have a nice day!");
            } else {
                System.out.println("Please enter a valid command.");
            }
        }

    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\n\n\n\n*************************************************************************");
        System.out.println("\n\t\t\tWelcome to your Get It Done account, " + defaultUser.getUsername() + "!");
        System.out.println("\n\n\t\tPlease choose one of the following:");
        System.out.println("\n\t 1. Add a Task");
        System.out.println("\n\t 2. View Task Tracker");
        System.out.println("\n\t 3. Delete a Task");
        System.out.println("\n\t 4. Edit a Task");
        System.out.println("\n\t 5. Save Task Tracker");
        System.out.println("\n\t 6. Log out and quit");
        System.out.println("\n*************************************************************************");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("1")) {
            doAddTask();
        } else if (command.equals("2")) {
            doViewTasks();
        } else if (command.equals("3")) {
            doDeleteTask();
        } else if (command.equals("4")) {
            doEditTask();
        } else if (command.equals("5")) {
            doSaveTask();
        } else {
            System.out.println("Please enter a valid command.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows a user to add a task to their task tracker
    public void doAddTask() {
        System.out.println("\nPlease enter task description:");
        String taskName = input.nextLine();

        System.out.println("\nPlease enter task due date (MM/DD/YY):");
        String dueDate = input.nextLine();

        Task newTask = null; // whenever a new task object is created, same name?
        try {
            newTask = new Task(taskName, dueDate);
            System.out.println("\n" + taskList.addTaskPrintMessage(newTask));
        } catch (EmptyStringException e) {
            System.out.println("Could not add task! Please ensure you specified the name and due date of the task!");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows a user to view their task tracker
    public void doViewTasks() {
        System.out.println(taskList.printTaskList());
    }

    // MODIFIES: this
    // EFFECTS: allows a user to delete a task from their task tracker
    public void doDeleteTask() {
        if (taskList.numTasks() == 0) {
            System.out.println("No tasks to delete! Please add a task to use this feature.");
        } else {
            System.out.println(taskList.printTaskList());
            System.out.println("\nPlease select the number of the task you would like to delete:");

            int index = input.nextInt();
            if (index <= taskList.numTasks()) {
                taskList.deleteTask(index);
                System.out.println("\nYour updated Task Tracker is printed below:");
                System.out.println("\n" + taskList.printTaskList());
            } else {
                System.out.println("Please select a valid task.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: allows a user to edit a task in their task tracker
    public void doEditTask() {
        if (taskList.numTasks() == 0) {
            System.out.println("No tasks to edit! Please add a task to use this feature.");
        } else {
            System.out.println(taskList.printTaskList());
            System.out.println("\n\nPlease select the number of the task you would like to edit:");

            int index = input.nextInt();

            if (index <= taskList.numTasks()) {
                taskList.getTask(index);

                System.out.println("What would you like to rename this task?");
                input.nextLine(); // is there a way around this to stop it from skipping?
                String renamedTask = input.nextLine();

                System.out.println("What is this task's new due date?");
                String rescheduledDueDate = input.nextLine();

                robustEditTask(index, renamedTask, rescheduledDueDate);
            } else {
                System.out.println("Please select a valid task.");
            }
        }
    }

    private void robustEditTask(int index, String renamedTask, String rescheduledDueDate) {
        try {
            taskList.getTask(index).setTaskName(renamedTask);
            taskList.getTask(index).setDueDate(rescheduledDueDate);
            System.out.println("\n\nThis task has been changed to:");
            System.out.println("\n" + taskList.getTask(index).printTask());
        } catch (EmptyStringException e) {
            System.out.println("Could not edit task! Please ensure you specified the task completely!");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows a user to save their task list
    public void doSaveTask() {
        if (taskList.numTasks() == 0) {
            System.out.println("No tasks to save! Please add a task to use this feature.");
        } else {
            try {
                Save.saveTaskList(taskList, TASK_TRACKER_FILE);
                System.out.println("Accounts saved to file " + TASK_TRACKER_FILE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to save data to " + TASK_TRACKER_FILE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                // this is due to a programming error
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads task tracker from TASK_TRACKER_FILE, if that file exists;
    // otherwise initializes accounts with default values
    public void loadTaskTracker() {
        try {
            defaultUser = new User("Gandalf", "Treebeard");
            input = new Scanner(System.in);
            taskList = Reader.readTaskList(new File(TASK_TRACKER_FILE));
        } catch (IOException | EmptyStringException ex) {
            initialize();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a user, user input, and a new task tracker
    public void initialize() {
        defaultUser = new User("Gandalf", "Treebeard");
        input = new Scanner(System.in);
        taskList = new TaskList();
    }

    // EFFECTS: checks if inputted username and password is
    // equal to the initialized username and password
    public boolean checkCredentials(String username, String password) {
        if (username.equals(defaultUser.getUsername()) && password.equals(defaultUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user login information
    public boolean login() {
        System.out.println("\nPlease enter your username:");
        String inputtedUserName = input.nextLine();
        System.out.println("\nPlease enter your password:");
        String inputtedPassword = input.nextLine();
        if (checkCredentials(inputtedUserName, inputtedPassword)) {
            return true;
        } else {
            System.out.println("\nYour username or password is incorrect, please try again.");
            return false;
        }
    }

    // EFFECTS: displays home screen of application to user
    public void displayHomeScreen() {
        System.out.println("\n*********************************************");
        System.out.println("\t\t\t\tGet It Done!\n\t\t\t\t(TM) 2020\n");
        System.out.println("\t Please choose one of the following:");
        System.out.println("\t 1. Log in");
        System.out.println("\t 2. Quit");
        System.out.println("\n*********************************************");
    }

}

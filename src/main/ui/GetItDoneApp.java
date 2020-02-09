package ui;

import model.Task;
import model.TaskList;
import model.User;

import java.util.Scanner;

// Get It Done! application
public class GetItDoneApp {

    //***
    // note: set up of Get It Done! application UI is adapted from Teller sample application.
    // ***

    private User defaultUser;
    private Scanner input;
    private TaskList taskList;
    private boolean appStatus;
    private boolean homeStatus;

    // EFFECTS: runs the Get It Done application
    public GetItDoneApp() {
        runGetItDone();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for application
    public void runGetItDone() {
        appStatus = true;
        homeStatus = true;
        String command = null;
        initialize();

        while (appStatus) {
            runHome();
            if (!appStatus) {
                break;
            } else {
                displayMenu();
                command = input.next();
                input.nextLine(); // added to prevent line skipping for doAddTask
                if (command.equals("4")) {
                    appStatus = false;
                    System.out.println("\nThank you for using Get It Done! Have a nice day!");
                } else {
                    processCommand(command);
                }
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
        System.out.println("\n\n\n\n\t\tWelcome to your Get It Done account, " + defaultUser.getUsername() + "!");
        System.out.println("\n\n\t Please choose one of the following:");
        System.out.println("\n\t 1. Add a Task");
        System.out.println("\n\t 2. View Task List");
        System.out.println("\n\t 3. Delete a Task");
        System.out.println("\n\t 4. Log out and quit");
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
        } else {
            System.out.println("Please enter a valid command uwu.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows a user to add a task to their task list
    public void doAddTask() {
        System.out.println("\n Please enter task description:");
        String taskName = input.nextLine();
        System.out.println("\n Please enter task due date (MM/DD/YY):");
        String dueDate = input.next();
        Task newTask = new Task(taskName, dueDate); // its always called new Task?
        System.out.println(taskList.addTask(newTask));
    }

    // MODIFIES: this
    // EFFECTS: allows a user to view their task list
    public void doViewTasks() {
        System.out.println(taskList.printTaskList());
    }

    // MODIFIES: this
    // EFFECTS: allows a user to delete a task from their task list
    public void doDeleteTask() {
        if (taskList.numTasks() == 0) {
            System.out.println("No tasks to delete! Please add a task to use this feature.");
        } else {
            System.out.println(taskList.printTaskList());
            System.out.println("Please select the number of the task you would like to delete:");

            int index = input.nextInt();
            taskList.deleteTask(index);
            System.out.println("Your updated Task List is printed below:");
            System.out.println(taskList.printTaskList());
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a user, user input, and a new task list
    public void initialize() {
        defaultUser = new User("gandalf", "thegrey");
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
        System.out.println("Please enter your username:\n");
        String inputtedUserName = input.nextLine();
        System.out.println("Please enter your password:\n");
        String inputtedPassword = input.nextLine();
        if (checkCredentials(inputtedUserName, inputtedPassword)) {
            return true;
        } else {
            System.out.println("Your username or password is incorrect, please try again.");
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

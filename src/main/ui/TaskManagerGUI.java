package ui;

import model.Task;
import model.TaskList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI of Get It Done app
public class TaskManagerGUI extends JFrame {
    /*
    * Adapted from Oracle's list swing tutorials and
    * other provided class repositories
    * */
    private TaskList taskList = new TaskList();
    protected JList<Task> tasks;
    protected static final String addTaskString = "Add Task";
    protected static final String deleteTaskString = "Delete Task";
    protected static final String editTaskString = "Edit Task";
    protected static final String saveTaskListString = "Save Task List";
    protected static final String loadTaskListString = "Load Task List";
    protected static final String titleText = "Get It Done!";
    protected static final String taskNameTitleString = "Task Name:";
    protected static final String dueDateTitleString = "Due Date:";

    protected JButton addTaskButton;
    protected JButton deleteTaskButton;
    protected JButton editTaskButton;
    protected JButton saveTaskListButton;
    protected JButton loadTaskListButton;
    protected JTextField taskName;
    protected JTextField dueDate;

    protected JLabel topTitle;
    protected JLabel taskNameTitle;
    protected JLabel dueDateTitle;

    protected JPanel title;
    protected JPanel taskSpecification;
    protected JPanel interactionButtons;
    protected JPanel taskListViewer;

    protected JScrollPane taskListScrollPane;


    public TaskManagerGUI() {
        //Create and set up the window.
        super("Get It Done!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(4, 1));
        GridBagConstraints c = new GridBagConstraints();
        buildTitle();
        buildTaskSpecification();
        buildTaskListViewer();
        buildInteractionButtons();
        this.addTaskButton.addActionListener(new AddTaskListener());
        add(title);
        add(taskSpecification);
        add(taskListViewer);
        add(interactionButtons);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);

    }

    public void buildTitle() {
        this.title = new JPanel();
        title.setLayout(new GridBagLayout());
        this.topTitle = new JLabel(titleText);
        Font font = new Font("Century Gothic", Font.BOLD,35);
        this.topTitle.setFont(font);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        title.add(topTitle, c);
    }

    public void buildTaskSpecification() {
        this.taskSpecification = new JPanel();
        this.taskSpecification.setLayout(new GridBagLayout());
        this.taskName = new JTextField(10);
        this.dueDate = new JTextField(10);
        this.addTaskButton = new JButton(addTaskString);
        this.taskNameTitle = new JLabel(taskNameTitleString);
        this.dueDateTitle = new JLabel(dueDateTitleString);

        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 0;
        c.gridy = 0;
        this.taskSpecification.add(this.taskNameTitle, c);

        c.gridx = 1;
        c.gridy = 0;
        this.taskSpecification.add(this.dueDateTitle, c);

        c.gridx = 0;
        c.gridy = 1;
        this.taskSpecification.add(this.taskName, c);

        c.gridx = 1;
        c.gridy = 1;
        this.taskSpecification.add(this.dueDate, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        this.taskSpecification.add(this.addTaskButton, c);
    }

    public void buildTaskListViewer() {
        this.taskListViewer = new JPanel();
        taskListViewer.setLayout(new GridLayout(1,1));
        this.tasks = new JList();
        tasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tasks.setVisibleRowCount(5);
        this.taskListScrollPane = new JScrollPane(tasks);
        taskListViewer.add(taskListScrollPane);
    }

    public void buildInteractionButtons() {
        this.interactionButtons = new JPanel();
        this.interactionButtons.setLayout(new GridLayout(2,2));
        this.deleteTaskButton = new JButton(deleteTaskString);
        this.editTaskButton = new JButton(editTaskString);
        this.saveTaskListButton = new JButton(saveTaskListString);
        this.loadTaskListButton = new JButton(loadTaskListString);

        this.interactionButtons.add(deleteTaskButton);
        this.interactionButtons.add(editTaskButton);
        this.interactionButtons.add(saveTaskListButton);
        this.interactionButtons.add(loadTaskListButton);
    }

    class AddTaskListener implements ActionListener { // TODO:what
        @Override
        public void actionPerformed(ActionEvent e) {
            // create a new task
            String taskNameString = taskName.getText();
            String dueDateString = dueDate.getText();
            Task createdTask = new Task(taskNameString, dueDateString);

            // add a task the task list
            taskList.addTask(createdTask);
            System.out.println(taskList.getTask(taskList.numTasks()).printTask()); // get whatever i just added
        }
    }

    public class DeleteTaskListener extends TaskManagerGUI implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // delete task from tasklist

            // update JList with new task list + refresh screen
        }
    }

    public class EditTaskListener extends TaskManagerGUI implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // edit task in tasklist

            // update JList with new task list + refresh screen
        }
    }

    public class SaveTaskListListener extends TaskManagerGUI implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // save tasklist to file

            // dialog box about success or failure otherwise
        }
    }

    public class LoadTaskListListener extends TaskManagerGUI implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // load tasklist into screen
        }
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TaskManagerGUI();
            }
        });
    }

}


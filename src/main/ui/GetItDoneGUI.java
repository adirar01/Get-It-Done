package ui;

import model.TaskList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// GUI of Get It Done app
public class GetItDoneGUI extends JFrame {
    /*
    * Adapted from Oracle's list swing tutorials and
    * other provided class repositories
    * */
    private TaskList taskList;
    private JList<String> tasks;
    private static final String taskString = "Add Task";
    private static final String dueDateString = "Add Task";
    private static final String addTaskString = "Add Task";
    private static final String deleteTaskString = "Delete Task";
    private static final String editTaskString = "Edit Task";
    private static final String saveTaskListString = "Save Task List";
    private static final String loadTaskListString = "Load Task List";
    private static final String titleText = "Get It Done!";



    private JButton addTaskButton;
    private JButton deleteTaskButton;
    private JButton editTaskButton;
    private JButton saveTaskListButton;
    private JButton loadTaskListButton;
    private JTextField taskName;
    private JTextField dueDate;

    private JLabel topTitle;

    private JPanel title;
    private JPanel taskSpecification;
    private JPanel interactionButtons;
    private JPanel taskListViewer;

    private JScrollPane taskListScrollPane;


    public GetItDoneGUI() {
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

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.taskSpecification.add(this.taskName, c);

        c.gridx = 1;
        c.gridy = 0;
        this.taskSpecification.add(this.dueDate, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
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

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GetItDoneGUI();
            }
        });
    }
}

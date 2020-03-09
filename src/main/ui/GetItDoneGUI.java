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
    private JButton addTaskButton;
    private JButton deleteTaskButton;
    private JButton editTaskButton;
    private JButton saveTaskListButton;
    private JButton loadTaskListButton;
    private JTextField taskName;
    private JTextField dueDate;

    public GetItDoneGUI() {
        //Create and set up the window.
        super("Get It Done!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.addTaskButton = new JButton(addTaskString);
        this.deleteTaskButton = new JButton(deleteTaskString);
        this.editTaskButton = new JButton(editTaskString);
        this.taskName = new JTextField(5);
        this.dueDate = new JTextField(5);
        this.saveTaskListButton = new JButton(saveTaskListString);
        this.loadTaskListButton = new JButton(loadTaskListString);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(taskName, c);


        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(dueDate, c);


        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(addTaskButton, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(deleteTaskButton, c);

        c.gridx = 2;
        c.gridy = 4;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(editTaskButton, c);


        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(saveTaskListButton, c);

        c.gridx = 2;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(loadTaskListButton, c);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

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

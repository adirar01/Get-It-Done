package ui;

import model.Task;
import model.TaskList;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

// GUI of Get It Done app
public class TaskManagerGUI extends JFrame {
    /*
     * Adapted from Oracle's list swing tutorials and
     * other provided class repositories
     * */
    private DefaultListModel extractedTasks = new DefaultListModel();

    private static final String TASK_TRACKER_FILE = "./data/GandalfTaskTracker.txt";
    private TaskList taskList; // remember you broke this
    protected JList<Task> tasks;
    protected static final String addTaskString = "Add Task";
    protected static final String deleteTaskString = "Delete Task";
    protected static final String editTaskString = "Edit Task";
    protected static final String saveTaskListString = "Save Task List";
    protected static final String titleText = "Get It Done!";
    protected static final String taskNameTitleString = "Task Name:";
    protected static final String dueDateTitleString = "Due Date:";

    protected JButton addTaskButton;
    protected JButton deleteTaskButton;
    protected JButton editTaskButton;
    protected JButton saveTaskListButton;
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
        this.deleteTaskButton.addActionListener(new DeleteTaskListener());
        this.editTaskButton.addActionListener(new EditTaskListener());
        this.saveTaskListButton.addActionListener(new SaveTaskListListener());
        loadTaskList();
        add(title);
        add(taskSpecification);
        add(taskListViewer);
        add(interactionButtons);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    private void loadTaskList() {
        try {
            taskList = Reader.readTaskList(new File(TASK_TRACKER_FILE));
            updateVisualList();
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    private void updateVisualList() {
        extractedTasks.removeAllElements(); // start from nothing
        ArrayList<Task> currentTaskList = new ArrayList<>(); // making a list of tasks from task list

        for (int count = 0; count < taskList.numTasks(); count++) {
            currentTaskList.add(taskList.getTask(count + 1));
        }

        for (Task task : currentTaskList) {
            StringBuilder newTaskEntry = printTaskInList(task);
            extractedTasks.addElement(newTaskEntry);
        }
    }

    public void buildTitle() {
        this.title = new JPanel();
        title.setLayout(new GridBagLayout());
        this.topTitle = new JLabel(titleText);
        Font font = new Font("Century Gothic", Font.BOLD, 35);
        this.topTitle.setFont(font);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        title.add(topTitle, c);
    }

    public void buildTaskSpecification() {
        initializeBuildTaskSpecificationFields();

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

    private void initializeBuildTaskSpecificationFields() {
        this.taskSpecification = new JPanel();
        this.taskSpecification.setLayout(new GridBagLayout());
        this.taskName = new JTextField(10);
        this.dueDate = new JTextField(10);
        this.addTaskButton = new JButton(addTaskString);
        this.taskNameTitle = new JLabel(taskNameTitleString);
        this.dueDateTitle = new JLabel(dueDateTitleString);
    }

    public void buildTaskListViewer() {
        this.taskListViewer = new JPanel();
        taskListViewer.setLayout(new GridLayout(1, 1));
        this.tasks = new JList(extractedTasks);
        tasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tasks.setVisibleRowCount(5);
        this.taskListScrollPane = new JScrollPane(tasks);
        taskListViewer.add(taskListScrollPane);
    }

    public void buildInteractionButtons() {
        this.interactionButtons = new JPanel();
        this.interactionButtons.setLayout(new GridBagLayout());
        this.deleteTaskButton = new JButton(deleteTaskString);
        this.editTaskButton = new JButton(editTaskString);
        this.saveTaskListButton = new JButton(saveTaskListString);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.interactionButtons.add(deleteTaskButton, c);

        c.gridx = 1;
        c.gridy = 0;
        this.interactionButtons.add(editTaskButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.interactionButtons.add(saveTaskListButton, c);
    }

    private void resetTextFields() {
        taskName.setText("");
        dueDate.setText("");
    }

    class AddTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (taskName.getText().equals("") || dueDate.getText().equals("")) {
                emptyErrorDialogBox();
            } else if (taskList.numTasks() >= TaskList.MAX_NUM_TASKS) {
                fullErrorDialogBox();
            } else {
                createAndAddNewTask();
                resetTextFields();
            }
        }

        private void createAndAddNewTask() {
            String taskNameString = taskName.getText();
            String dueDateString = dueDate.getText();
            Task createdTask = new Task(taskNameString, dueDateString);

            // add a task the task list
            taskList.addTask(createdTask);

            Task justAdded = taskList.getTask(taskList.numTasks());

            StringBuilder newTaskEntry = printTaskInList(justAdded);

            extractedTasks.addElement(newTaskEntry);
        }
    }

    private void fullErrorDialogBox() {
        try {
            playZeldaSound();
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
        }

        String errorMessageFull = "Maximum number of manageable tasks exceeded."
                + "\nPlease complete/ delete a task to add a new one.";
        JOptionPane.showMessageDialog(new JFrame(), errorMessageFull,
                "Full Error", JOptionPane.ERROR_MESSAGE);
    }

    private void emptyErrorDialogBox() {
        try {
            playZeldaSound();
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
        }
        String errorMessageEmpty = "One or more of the task specification fields are empty."
                + "\nPlease specify a task to use this feature.";
        JOptionPane.showMessageDialog(new JFrame(), errorMessageEmpty,
                "Empty Error", JOptionPane.ERROR_MESSAGE);
    }

    private void playZeldaSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        /*
        * The following code is adapted from:
        * https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
        * Audio source:
        * https://www.youtube.com/watch?v=LqkRghWjD0Y
        *  */
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./data/zelda.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    private void savedDialogBox() {
        String savedMessage = "Your Task List has successfully saved!";
        JOptionPane.showMessageDialog(new JFrame(), savedMessage,
                "Saved", JOptionPane.INFORMATION_MESSAGE);
    }

    private void fileNotFoundDialogBox() {
        String fileNotFound = "File not found! Please consult the developer!";
        JOptionPane.showMessageDialog(new JFrame(), fileNotFound,
                "Error 404", JOptionPane.ERROR_MESSAGE);
    }

    private StringBuilder printTaskInList(Task justAdded) {
        StringBuilder newTaskEntry = new StringBuilder();
        newTaskEntry.append(taskList.getIndexOf(justAdded) + 1).append(". ");
        newTaskEntry.append(taskNameTitleString).append(" ");
        newTaskEntry.append(justAdded.getTaskName());
        newTaskEntry.append("\t\t\t\t\t\t\t\t\t\t\t\t");
        newTaskEntry.append(dueDateTitleString).append(" ");
        newTaskEntry.append(justAdded.getDueDate());
        return newTaskEntry;
    }

    public class DeleteTaskListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = tasks.getSelectedIndex();

            if (selectedIndex == -1) {
                Toolkit.getDefaultToolkit().beep();
            } else {
                taskList.deleteTask(selectedIndex + 1);
                extractedTasks.remove(selectedIndex);
                updateVisualList();
            }
        }
    }

    public class EditTaskListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = tasks.getSelectedIndex();
            String taskNameString = taskName.getText();
            String dueDateString = dueDate.getText();

            if (selectedIndex == -1) {
                Toolkit.getDefaultToolkit().beep();
            } else if (taskNameString.equals("") || dueDateString.equals("")) {
                emptyErrorDialogBox();
            } else {
                Task taskToEdit = taskList.getTask(selectedIndex + 1);
                taskToEdit.setTaskName(taskNameString);
                taskToEdit.setDueDate(dueDateString);

                StringBuilder editedTaskEntry = printTaskInList(taskToEdit);

                extractedTasks.set(selectedIndex, editedTaskEntry);

                resetTextFields();
            }
        }
    }

    public class SaveTaskListListener implements ActionListener {

        private void emptyTaskListError() {
            try {
                playZeldaSound();
            } catch (Exception e) {
                Toolkit.getDefaultToolkit().beep();
            }
            String errorMessageEmpty = "Could not save. Your Task List is empty.\nAdd tasks to use this feature.";
            JOptionPane.showMessageDialog(new JFrame(), errorMessageEmpty,
                    "Empty Error", JOptionPane.ERROR_MESSAGE);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (taskList.numTasks() == 0) {
                emptyTaskListError();
            } else {
                try {
                    Writer writer = new Writer(new File(TASK_TRACKER_FILE));

                    ArrayList<Task> currentTaskList = new ArrayList<>(); // making a list of tasks from task list

                    for (int count = 0; count < taskList.numTasks(); count++) {
                        currentTaskList.add(taskList.getTask(count + 1));
                    }

                    for (Task task : currentTaskList) {
                        writer.write(task); // writes each task to file on a new line
                    }

                    writer.close();

                    savedDialogBox();
                } catch (FileNotFoundException e1) {
                    fileNotFoundDialogBox();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
            // save tasklist to file

            // dialog box about success or failure otherwise
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


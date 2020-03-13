package ui;

import model.Task;
import model.TaskList;
import persistence.Reader;
import persistence.Writer;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

// GUI of Get It Done app
public class TaskManagerGUI extends JPanel {
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
    protected static final String taskListTitleString = "Your Task List:";

    protected JButton addTaskButton;
    protected JButton deleteTaskButton;
    protected JButton editTaskButton;
    protected JButton saveTaskListButton;
    protected JTextField taskName;
    protected JTextField dueDate;

    protected JLabel topTitle;
    protected JLabel taskNameTitle;
    protected JLabel dueDateTitle;
    protected JLabel imageLabel;
    protected JLabel taskListTitle;

    protected JPanel title;
    protected JPanel taskSpecification;
    protected JPanel interactionButtons;
    protected JPanel taskListViewer;

    protected JScrollPane taskListScrollPane;

    private static final Color background1 = new Color(201, 242, 255);//
    private static final Color background2 = new Color(251, 255, 201);


    public TaskManagerGUI() {
        //Create and set up the window.
        setPreferredSize(new Dimension(500, 600));
        setBorder(new EmptyBorder(13, 13, 13, 13));
        setBackground(background1);
        setLayout(new GridLayout(4, 1));
        GridBagConstraints c = new GridBagConstraints();
        buildTitle();
        buildTaskSpecification();
        buildTaskListViewer();
        buildInteractionButtons();
        this.addTaskButton.addActionListener(new AddTaskListener());
        this.taskName.addActionListener(new AddTaskListener());
        this.dueDate.addActionListener(new AddTaskListener());
        this.deleteTaskButton.addActionListener(new DeleteTaskListener());
        this.editTaskButton.addActionListener(new EditTaskListener());
        this.saveTaskListButton.addActionListener(new SaveTaskListListener());
        loadTaskList();
        add(title);
        add(taskSpecification);
        add(taskListViewer);
        add(interactionButtons);
        setVisible(true);
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
        this.title.setBackground(background1);
        this.title.setLayout(new GridBagLayout());
        this.topTitle = new JLabel(titleText);
        Font font = new Font("Century Gothic", Font.BOLD, 55);
        this.topTitle.setFont(font);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridheight = 2;
        title.add(topTitle, c);
        c.gridy = 1;

        try {
            drawIcon();
        } catch (IOException e) {
            e.printStackTrace();
        }

        c.gridy = 3;
        c.gridheight = 1;
        title.add(imageLabel, c);
    }

    private void drawIcon() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("./data/check.png"));
        Image newImage = myPicture.getScaledInstance(125, 160, Image.SCALE_SMOOTH);
        this.imageLabel = new JLabel(new ImageIcon(newImage));
    }


    public void buildTaskSpecification() {
        initializeBuildTaskSpecificationFields();
        taskSpecification.setBackground(background1);

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
        Font font1 = new Font("Century Schoolbook", Font.BOLD,15);
        Font font2 = new Font("Century Schoolbook", Font.ITALIC,15);
        this.taskNameTitle.setFont(font1);
        this.dueDateTitle.setFont(font1);
        this.addTaskButton.setFont(font2);
    }

    public void buildTaskListViewer() {
        this.taskListViewer = new JPanel();
        taskListViewer.setBackground(background2);
        Font font = new Font("Century Schoolbook", Font.BOLD,15);
        taskListViewer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.taskListTitle = new JLabel(this.taskListTitleString);
        this.tasks = new JList(extractedTasks);
        tasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tasks.setVisibleRowCount(5);
        this.taskListScrollPane = new JScrollPane(tasks);
        this.taskListTitle.setFont(font);
        c.gridy = 0;
        taskListViewer.add(taskListTitle, c);
        c.insets = new Insets(10, 0, 10, 0);
        c.anchor = GridBagConstraints.LINE_END;
        c.gridy = 5;
        taskListViewer.add(taskListScrollPane, c);
    }

    public void buildInteractionButtons() {
        this.interactionButtons = new JPanel();
        interactionButtons.setBackground(background1);
        this.interactionButtons.setLayout(new GridBagLayout());
        this.deleteTaskButton = new JButton(deleteTaskString);
        this.editTaskButton = new JButton(editTaskString);
        this.saveTaskListButton = new JButton(saveTaskListString);
        Font font = new Font("Century Schoolbook", Font.ITALIC,15);
        this.deleteTaskButton.setFont(font);
        this.editTaskButton.setFont(font);
        this.saveTaskListButton.setFont(font);

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
                try {
                    playZeldaSound();
                } catch (Exception e1) {
                    Toolkit.getDefaultToolkit().beep();
                }
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
                try {
                    playZeldaSound();
                } catch (Exception e1) {
                    Toolkit.getDefaultToolkit().beep();
                }
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
        }
    }
}


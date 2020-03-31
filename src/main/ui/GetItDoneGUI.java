package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Get It Done GUI with Login and Task Manager GUI Panels
public class GetItDoneGUI extends JFrame {
    LoginGUI loginGUI;
    TaskManagerGUI taskManagerGUI;
    boolean successful;

    /*
     * EFFECTS: creates and initializes Get It Done frame and functionality
     * */
    public GetItDoneGUI() {
        super("Get It Done!");
        this.successful = false;
        this.loginGUI = new LoginGUI();
        this.taskManagerGUI = new TaskManagerGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.loginGUI);
        setPreferredSize(new Dimension(500, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setUpLoginPageButtons();
    }

    /*
     * EFFECTS: initializes and adds functionality to login page buttons
     * */
    private void setUpLoginPageButtons() {
        loginGUI.quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        loginGUI.login.addActionListener(new LoginListener());
        loginGUI.passwordInput.addActionListener(new LoginListener());
    }

    // ActionListener for login functionality
    class LoginListener implements ActionListener {

        /*
         * EFFECTS: verifies login credentials.
         * If successful, plays successful login audio clip and updates panel to Task Manager GUI
         * otherwise, resets text fields to allow user to retry
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loginGUI.verifyLogin()) {
                try {
                    loginGUI.playMarioSound();
                } catch (Exception ex) {
                    Toolkit.getDefaultToolkit().beep();
                }
                setContentPane(taskManagerGUI);
                revalidate();
            } else {
                loginGUI.resetTextFields();
            }
        }
    }
}

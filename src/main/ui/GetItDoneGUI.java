package ui;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GetItDoneGUI extends JFrame {
    LoginGUI loginGUI;
    TaskManagerGUI taskManagerGUI;
    boolean successful;

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

    private void setUpLoginPageButtons() {
        loginGUI.quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        loginGUI.login.addActionListener(
                new ActionListener() {
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
                });
    }
}

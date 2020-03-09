package ui;

import apple.laf.AquaLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private static final String usernameText = "Username:";
    private static final String passwordText = "Password:";
    private static final String loginText = "Log in";
    private static final String quitText = "Quit Application";
    private static final String adminUserName = "Gandalf";
    private static final String adminPass = "Treebeard";

    private JLabel username;
    private JLabel password;

    private JTextField usernameInput;
    private JPasswordField passwordInput;

    private JButton login;
    private JButton quit;

    public LoginGUI() {
        super("Get It Done! Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(3, 2, 0, 80));
        this.username = new JLabel(usernameText);
        this.password = new JLabel(passwordText);
        this.usernameInput = new JTextField();
        this.passwordInput = new JPasswordField();

        this.login = new JButton(loginText);
        this.login.setActionCommand("login");
        this.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verifyLogin()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Login Successful");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect Username or Password :(");
                }
            }
        });

        this.quit = new JButton(quitText);
        this.quit.setActionCommand("quit");
        this.quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // refactor to do away with this
            }
        });


        add(this.username);
        add(this.usernameInput);
        add(this.password);
        add(this.passwordInput);
        add(this.login);
        add(this.quit);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }

    public boolean verifyLogin() {
        String givenUsername = usernameInput.getText();
        String givenPassword = String.valueOf(passwordInput.getPassword());

        return givenUsername.equals(adminUserName) && givenPassword.equals(adminPass);

    }
}

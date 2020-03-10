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
    private static final String titleText = "Get It Done!";

    private JLabel username;
    private JLabel password;
    private JLabel topTitle;

    private JTextField usernameInput;
    private JPasswordField passwordInput;

    private JButton login;
    private JButton quit;

    private JPanel title;
    private JPanel loginMenu;

    public LoginGUI() {
        super("Get It Done! Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 650));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(2, 1));
        buildTitle();
        buildLoginMenu();
        add(this.title);
        add(this.loginMenu);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
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

    public void buildLoginMenu() {
        this.loginMenu = new JPanel();
        loginMenu.setLayout(new GridLayout(3, 2, 0, 80));
        initializeLoginFields();
        this.loginMenu.add(this.username);
        this.loginMenu.add(this.usernameInput);
        this.loginMenu.add(this.password);
        this.loginMenu.add(this.passwordInput);
        this.loginMenu.add(this.login);
        this.loginMenu.add(this.quit);
    }

    public void initializeLoginFields() {
        this.username = new JLabel(usernameText);
        this.password = new JLabel(passwordText);
        this.usernameInput = new JTextField();
        this.passwordInput = new JPasswordField();

        this.login = new JButton(loginText);
        this.login.setActionCommand("login");
        this.login.addActionListener(new ActionListener() { // TODO: REFACTOR
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
        this.quit.addActionListener(new ActionListener() { // TODO: REFACTOR
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // refactor to do away with this
            }
        });
    }

    public boolean verifyLogin() {
        String givenUsername = usernameInput.getText();
        String givenPassword = String.valueOf(passwordInput.getPassword());

        return givenUsername.equals(adminUserName) && givenPassword.equals(adminPass);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }


}

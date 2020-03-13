package ui;

import apple.laf.AquaLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginGUI extends JPanel {
    private static final String usernameText = "Username:";
    private static final String passwordText = "Password:";
    private static final String loginText = "Log in";
    private static final String quitText = "Quit Application";
    private static final String adminUserName = "Gandalf";
    private static final String adminPass = "Treebeard";
    private static final String titleText = "Get It Done!";
    private static final Color background = new Color(203, 255, 191);

    private JLabel username;
    private JLabel password;
    private JLabel topTitle;
    private JLabel imageLabel;

    protected JTextField usernameInput;
    protected JPasswordField passwordInput;

    protected JButton login;
    protected JButton quit;

    private JPanel title;
    private JPanel loginMenu;

    public LoginGUI() {
        setBackground(background);
        setPreferredSize(new Dimension(500, 650));
        setBorder(new EmptyBorder(80, 40, 80, 40));
        setLayout(new GridLayout(2, 1));
        buildTitle();
        buildLoginMenu();
        add(this.title);
        add(this.loginMenu);
        setVisible(true);

    }

    public void buildTitle() {
        this.title = new JPanel();
        title.setBackground(background);
        title.setLayout(new GridBagLayout());
        this.topTitle = new JLabel(titleText);
        Font font = new Font("Century Schoolbook", Font.BOLD,65);
        this.topTitle.setFont(font);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        title.add(topTitle, c);
        try {
            drawIcon();
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.gridy = 1;
        title.add(imageLabel, c);

    }

    private void drawIcon() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("./data/check.png"));
        Image newImage = myPicture.getScaledInstance(140, 180, Image.SCALE_SMOOTH);
        this.imageLabel = new JLabel(new ImageIcon(newImage));
    }

    public void buildLoginMenu() {
        this.loginMenu = new JPanel();
        loginMenu.setBackground(background);
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
        Font font1 = new Font("Century Schoolbook", Font.BOLD,20);
        Font font2 = new Font("Century Schoolbook", Font.ITALIC,15);
        this.username = new JLabel(usernameText);
        this.username.setFont(font1);
        this.password = new JLabel(passwordText);
        this.password.setFont(font1);
        this.usernameInput = new JTextField();
        this.passwordInput = new JPasswordField();
        this.login = new JButton(loginText);
        this.quit = new JButton(quitText);
        this.login.setFont(font2);
        this.quit.setFont(font2);
    }

    protected void resetTextFields() {
        this.usernameInput.setText("");
        this.passwordInput.setText("");
    }

    public boolean verifyLogin() {
        String givenUsername = usernameInput.getText();
        String givenPassword = String.valueOf(passwordInput.getPassword());

        return givenUsername.equals(adminUserName) && givenPassword.equals(adminPass);
    }

    protected void playMarioSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        /*
         * The following code is adapted from:
         * https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
         * Audio source:
         * https://www.youtube.com/watch?v=Aax1SEZESts
         *  */
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./data/mario.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}

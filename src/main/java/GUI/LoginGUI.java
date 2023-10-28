package GUI;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import Controllers.LogInController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modules.Account;
import java.awt.*;

public class LoginGUI extends JFrame {
    LogInController loginController;

    JLabel loginLabel;

    JLabel usernameLabel;
    JTextField usernameField;

    JLabel passwordLabel;
    JPasswordField passwordField;

    JButton loginButton;

    JLabel warningLabel;

    public LoginGUI() {
        super();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(null);
        setLocationRelativeTo(null);
        setSize(600, 480);
        setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, 600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginController = new LogInController();

        loginLabel = new JLabel("Welcome");
        loginLabel.setFont(new Font("Helvetica", Font.PLAIN, 26));
        loginLabel.setVerticalAlignment(JLabel.BOTTOM);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setBounds(140, 74, 320, 30);
        add(loginLabel);

        usernameLabel = new JLabel("Username");
        usernameLabel.setVerticalAlignment(JLabel.BOTTOM);
        usernameLabel.setHorizontalAlignment(JLabel.LEFT);
        usernameLabel.setBounds(170, 140, 100, 24);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(260, 140, 180, 24);
        add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setVerticalAlignment(JLabel.BOTTOM);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        passwordLabel.setBounds(170, 180, 100, 24);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(260, 180, 180, 24);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(240, 240, 120, 40);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Account account = loginController.login(usernameField.getText(), String.valueOf(passwordField.getPassword()));

                if (account != null) {
                    MainFrame m = new MainFrame(account);
                    m.setVisible(true);

                    setVisible(false);
                    setEnabled(false);
                } else {
                    warningLabel.setVisible(true);
                }
            }
        });
        add(loginButton);

        warningLabel = new JLabel("Incorrect username or password");
        warningLabel.setVerticalAlignment(JLabel.BOTTOM);
        warningLabel.setHorizontalAlignment(JLabel.CENTER);
        warningLabel.setBounds(140, 276, 320, 40);
        warningLabel.setForeground(Color.RED);
        warningLabel.setVisible(false);
        add(warningLabel);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        LoginGUI f = new LoginGUI();
        f.setResizable(false);
        f.setVisible(true);              
    }
}
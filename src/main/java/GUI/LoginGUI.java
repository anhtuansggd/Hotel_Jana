package GUI;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class LoginGUI extends JFrame {
    JLabel usernameLabel;
    JTextField usernameField;

    JLabel passwordLabel;
    JPasswordField passwordField;

    JButton loginButton;

    public LoginGUI() {
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

        setLayout(null);
        setLocationRelativeTo(null);
        setSize(600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(240, 240, 120, 40);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(usernameField.getText() + " " + String.valueOf(passwordField.getPassword()));
            }
        });
        add(loginButton);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        LoginGUI f = new LoginGUI();
        f.setVisible(true);              
    }
}
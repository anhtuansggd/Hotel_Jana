package GUI;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import Controllers.LogInController;
import Modules.Account;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    LogInController loginController;

    JLabel loginLabel;

    JLabel usernameLabel;
    JTextField usernameField;
    JButton usernameClearButton;

    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton passwordClearButton;

    JButton loginButton;

    JLabel warningLabel;

    JLabel quitButton;

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

        // Clear buttons
        int w = 14;
        int h = 14;

        ImageIcon xNormalIcon = newIcon("/xButtonImageNormal.png", w, h);
        ImageIcon xHoveredIcon = newIcon("/xButtonImageHovered.png", w, h);

        usernameClearButton = newImageButton(xNormalIcon, xHoveredIcon, 428 - w/2, 152 - h/2, w, h, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
            }
        });
        add(usernameClearButton);

        passwordClearButton = newImageButton(xNormalIcon, xHoveredIcon, 428 - w/2, 192 - h/2, w, h, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
            }
        });
        add(passwordClearButton);

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

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(460, 384, 100, 32);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(quitButton);

        warningLabel = new JLabel("Incorrect username or password");
        warningLabel.setVerticalAlignment(JLabel.BOTTOM);
        warningLabel.setHorizontalAlignment(JLabel.CENTER);
        warningLabel.setBounds(140, 276, 320, 40);
        warningLabel.setForeground(Color.RED);
        warningLabel.setVisible(false);
        add(warningLabel);
    }

    private ImageIcon newIcon(String path, int w, int h) {
        ImageIcon xIcon = new ImageIcon(getClass().getResource(path));
        Image xImage = xIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(xImage);
    }

    private JButton newImageButton(ImageIcon normalIcon, ImageIcon hoveredIcon, int x, int y, int w, int h, ActionListener actionListener) {
        JButton button = new JButton(normalIcon);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(actionListener);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setIcon(hoveredIcon);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setIcon(normalIcon);
            }
        });
        button.setBounds(x, y, w, h);

        return button;
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        LoginGUI f = new LoginGUI();
        f.setResizable(false);
        f.setVisible(true);              
    }
}
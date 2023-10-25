package GUI;

import javax.swing.*;
import java.awt.*;
import Modules.Account;

public class MainGUI extends JFrame {
    public MainGUI(Account account) {
        super();

        // For debugging children panels
        // uncomment the below block and run MainGUI.java
        
        /*
        JPanel p1 = new JPanel();
        p1.setBackground(Color.gray);
        p1.setBounds(0, 0, 120, 720);
        add(p1);

        JPanel p2 = new RoomSearchPanel();
        p2.setBackground(Color.green);
        p2.setBounds(120, 0, 960, 720);
        add(p2);
         */

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        // creating icon

        JComponent p1 = new RoomSearchPanel();
        tabbedPane.addTab("Search room", null, p1, "dummy");
        JLabel lab = new JLabel("Search room");
        lab.setPreferredSize(new Dimension(100, 30));
        tabbedPane.setTabComponentAt(0, lab);

        JComponent p2 = new AccountManagementPanel();
        tabbedPane.addTab("Manage account", null, p2, "astley");
        lab = new JLabel("Manage account");
        lab.setPreferredSize(new Dimension(100, 30));
        tabbedPane.setTabComponentAt(1, lab);

        JComponent p3 = new BookingReviewPanel();
        tabbedPane.addTab("Review Booking", null, p3, "huh");
        lab = new JLabel("Review Booking");
        lab.setPreferredSize(new Dimension(100, 30));
        tabbedPane.setTabComponentAt(2, lab);


        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBounds(0, 0, 1080, 720);
        add(tabbedPane);

        setLayout(null);
        setLocationRelativeTo(null);
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainGUI m = new MainGUI(new Account(null, null, null,null, null,null ));
        System.out.println("Initialize " + m);
    }
}
package GUI;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;

import Modules.Account;

public class MainFrame extends JFrame {
    public MainFrame(Account account) {
        super();

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
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        MainFrame m = new MainFrame(new Account(null, null, null, null, null, null));
        m.setVisible(true);
    }
}
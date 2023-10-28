package GUI;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;

import Modules.Account;
import Modules.Account.*;

public class MainFrame extends JFrame {
    JTabbedPane tabbedPane;

    public MainFrame(Account account) {
        super();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(null);
        setLocationRelativeTo(null);
        setSize(1080, 720);
        setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, 1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        // creating icon

        switch (account.getAccountType()) {
            case MANAGER:
                addTab(tabbedPane, RoomSearchPanel.class, "Search room", 0);
                addTab(tabbedPane, BookingReviewPanel.class, "Review Booking", 1);
                addTab(tabbedPane, AccountManagementPanel.class, "Manage account", 2);
                break;

            case RECEPTIONIST:
                addTab(tabbedPane, RoomSearchPanel.class, "Search room", 0);
                addTab(tabbedPane, BookingReviewPanel.class, "Review Booking", 1);
                break;

            case GUEST:
                addTab(tabbedPane, RoomSearchPanel.class, "Search room", 0);
                break;

            default:
                break;
        }
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBounds(0, 0, 1080, 720);
        add(tabbedPane);
    }

    private <T> void addTab(JTabbedPane tabbedPane, Class<T> tabType, String tabName, int index) {
        T t;
        try {
            t = tabType.getDeclaredConstructor().newInstance();
            tabbedPane.addTab(tabName, null, (JComponent)t, "huh");
            JLabel label = new JLabel(tabName);
            label.setPreferredSize(new Dimension(100, 30));
            tabbedPane.setTabComponentAt(index, label);

        } catch (Exception e) {
            System.out.println("Invalid tab type or tab constructor");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        MainFrame m = new MainFrame(
            new Account("1", AccountType.MANAGER, "drac_34", "password", "Dracula", Race.VAMPIRE)
        );
        m.setVisible(true);
    }
}
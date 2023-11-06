package GUI;

import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.*;

import Controllers.NotificationController;

public class NotificationPanel extends ChildrenPanel {
    NotificationController notificationController;

    JButton resetButton;

    public NotificationPanel(MainFrame f) {
        super(f, 1);
        notificationController = new NotificationController();
        refreshTableScrollPane(notificationController.search(Integer.valueOf(f.account.getId())));
        panelTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        panelTable.getColumnModel().getColumn(1).setPreferredWidth(580);
        panelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        

        resetButton = getFormattedButton("Reset", 30, 60, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTableScrollPane(notificationController.search(Integer.valueOf(f.account.getId())));
                panelTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                panelTable.getColumnModel().getColumn(1).setPreferredWidth(580);
                panelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            }
        });
        add(resetButton);
    }
}
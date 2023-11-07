package GUI;

import javax.swing.JButton;
import javax.swing.JTable;

import Controllers.NotificationController;

public class NotificationPanel extends ChildrenPanel {
    NotificationController notificationController;

    JButton resetButton;

    public NotificationPanel(MainFrame f) {
        super(f, 1);
        notificationController = new NotificationController();
        refreshTableScrollPane(notificationController.search(Integer.valueOf(f.account.getId())));
        reformatPanelTable();
    }

    private void reformatPanelTable() {
        panelTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        panelTable.getColumnModel().getColumn(1).setPreferredWidth(578);

        panelTable.setRowHeight(38);
        WordWrapCellRenderer r = new WordWrapCellRenderer();
        panelTable.getColumnModel().getColumn(1).setCellRenderer(r);

        panelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    @Override
    public void onDataBaseChange() {
        refreshTableScrollPane(notificationController.search(Integer.valueOf(mainFrame.account.getId())));
        reformatPanelTable();
    }
}
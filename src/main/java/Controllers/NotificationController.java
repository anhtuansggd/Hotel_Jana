package Controllers;


import Modules.Notification;

import java.sql.SQLException;

public class NotificationController extends Controller<Notification> {
    private final String insertNotificationSQL = "INSERT INTO notification VALUES (?,?,?);";
    //private final String updateNotificationSQL = "UPDATE notifcation \n";


    private final String countTotalSQL = "select count(id)\n" + "from notification";

    public NotificationController() {
        super();
    }


    @Override
    public TableState add(Notification notification) {
        try {
            int total = getTotalRows(countTotalSQL) + 1;
            ppsm = connection.prepareStatement(insertNotificationSQL);
            ppsm.setString(1, String.valueOf(total));
            ppsm.setInt(2, notification.getAccount_id());
            ppsm.setString(3, notification.getMessage());
            execute(ppsm);

            System.out.println("Notification insert succeeded");

        } catch (SQLException e) {
            System.out.println("Notification insert failed: " + e.toString());
        }
        return getAll();
    }

    @Override
    public TableState update(Notification notification) {
        return null;
    }

    @Override
    public TableState delete(Notification notification) {
        return null;
    }
    public TableState getAll() {return _getAll("notification");}
}

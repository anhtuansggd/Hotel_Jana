package Controllers;

import Modules.Notification;

import java.sql.*;
import java.util.ArrayList;

public class NotificationController extends DatabaseManager implements Controller<Notification> {
    private final String insertNotificationSQL = "INSERT INTO notification VALUES (?,?,?);";
    //private final String updateNotificationSQL = "UPDATE notifcation \n";
    private final String searchNotificationSQL = "SELECT n.id, n.reservation_number, n.description\n" +
            "FROM notification n\n" +
            "JOIN room_booking rb ON n.reservation_number = rb.reservation_number\n" +
            "WHERE rb.account_id = ?;";


    private final String countTotalSQL = "select count(id)\n" + "from notification";


    @Override
    public TableState add(Notification notification) {
        try {
            Connection connection = getConnection();
            PreparedStatement ppsm = connection.prepareStatement(insertNotificationSQL);
            int total = getTotalRows(countTotalSQL, ppsm) + 1;
            ppsm.setString(1, String.valueOf(total));
            ppsm.setInt(2, notification.getReservation_number());
            ppsm.setString(3, notification.getMessage());
            execute(ppsm);

            ppsm.close();
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

    public TableState search(int accountId) {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        try{
            Connection connection = getConnection();

            PreparedStatement ppsm = connection.prepareStatement(searchNotificationSQL);
            ppsm.setInt(1, accountId);
            executeSearch(ppsm);

            ResultSet rs = executeSearch(ppsm);
            while (rs.next()){
                String[] row= new String[3];
                for(int i=1; i<=3; i++){
                    row[i-1] = rs.getString(i);
                }
                arrayList.add(row);
            }
            System.out.println("Notification search succeeded");
            String[][] resultArray = new String[arrayList.size()][];
            resultArray = arrayList.toArray(resultArray);
            String[] columns = getAccountColumns("notification");
            TableState tableState = new TableState(columns, resultArray);

            rs.close();
            ppsm.close();
            return tableState;
        }catch (SQLException e){
            System.out.println("Room search failed "+e.toString());
        }
        return null;
    }

    public TableState getAll() {return _getAll("notification");}
}

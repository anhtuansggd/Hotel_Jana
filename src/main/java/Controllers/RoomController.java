package Controllers;


import Modules.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class RoomController extends Controller<Room>{
    private static final String insertRoomSQL = "INSERT INTO room VALUES(?,?,?);";
    private static final String updateRoomSQL = "UPDATE room SET room_style=?, is_smoking=? WHERE room_number=?;";
    private static final String deleteRoomSQL = "DELETE FROM room WHERE room_number=?;";
    private static final String searchRoomSQL = "SELECT *\n" +
            "FROM room r\n" +
            "WHERE r.room_style = ?\n" +
            "OR r.room_number IN (\n" +
            "    SELECT rb.room_number\n" +
            "\tFROM room_booking rb\n" +
            "\tWHERE (rb.start_date <= ? AND rb.start_date + rb.duration > ?)\n" +
            "\tOR (rb.start_date < ? AND rb.start_date + rb.duration >= ?)\n" +
            ");";

    public RoomController() {
        super();
    }

    @Override
    public TableState add(Room room){
        try{
            ppsm = connection.prepareStatement(insertRoomSQL);
            ppsm.setString(1, room.getRoomNumber());
            ppsm.setString(2, room.getStyle().toString());
            ppsm.setInt(3, room.isSmoking());
            execute(ppsm);

            System.out.println("Room insert succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
            System.out.println("Room insert failed " + e.toString());
        }
        close();
        return getAll();
    }

    @Override
    public TableState update(Room room){
        try{
            /*
            reservation_number is pk
             */
            ppsm = connection.prepareStatement(updateRoomSQL);
            ppsm.setString(1, room.getStyle().toString());
            ppsm.setInt(2, room.isSmoking());
            ppsm.setString(3, room.getRoomNumber());
            execute(ppsm);

            System.out.println("RoomBooking update succeeded");
        }catch (SQLException e){
            System.out.println("RoomBooking update failed");
        }
        close();
        return getAll();
    }

    @Override
    public TableState delete(Room room){
        try{
            ppsm = connection.prepareStatement(deleteRoomSQL);
            ppsm.setString(1, room.getRoomNumber());
            execute(ppsm);

            System.out.println("Room delete succeeded");
        }catch (SQLException e){
            System.out.println("Room delete failed");
        }
        close();
        return getAll();
    }

    public class RoomSearchQuery {
        public Room.RoomStyle roomStyle;
        public LocalDate startDate;
        public int duration ;
    }

    /**
     * No attributes allowed to be null
     **/
    public TableState search(RoomSearchQuery roomSearchQuery) {
        try{
            ppsm = connection.prepareStatement(searchRoomSQL);
            ppsm.setString(1, roomSearchQuery.roomStyle.toString());
            ppsm.setDate(2, Date.valueOf(roomSearchQuery.startDate));
            ppsm.setDate(3, Date.valueOf(roomSearchQuery.startDate));
            ppsm.setDate(4, Date.valueOf(roomSearchQuery.startDate.plusDays(roomSearchQuery.duration)));
            ppsm.setDate(5, Date.valueOf(roomSearchQuery.startDate.plusDays(roomSearchQuery.duration)));
            //executeSearch(searchRoomSQL, ppsm);

            System.out.println("Room search succeeded");
            return getAll();
        }catch (SQLException e){
            System.out.println("Room search failed "+e.toString());
        }
        close();
        return null;
    }

    public String[][] search1(RoomSearchQuery roomSearchQuery) {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        try{
            ppsm = connection.prepareStatement(searchRoomSQL);
            ppsm.setString(1, roomSearchQuery.roomStyle.toString());
            ppsm.setDate(2, Date.valueOf(roomSearchQuery.startDate));
            ppsm.setDate(3, Date.valueOf(roomSearchQuery.startDate));
            ppsm.setDate(4, Date.valueOf(roomSearchQuery.startDate.plusDays(roomSearchQuery.duration)));
            ppsm.setDate(5, Date.valueOf(roomSearchQuery.startDate.plusDays(roomSearchQuery.duration)));
            executeSearch(ppsm);
            System.out.println(ppsm);
            ResultSet rs = executeSearch(ppsm);

            while (rs.next()){
                String[] row= new String[6];
                for(int i=1; i<3; i++){
                    row[i] = rs.getString(i);
                }
                arrayList.add(row);
            }
            System.out.println("Account search succeeded");
            String[][] resultArray = new String[arrayList.size()][];
            resultArray = arrayList.toArray(resultArray);
            close();
            return resultArray;
        }catch (SQLException e){
            System.out.println("Account search failed "+e.toString());
        }
        return null;
    }

    @Override
    public TableState getAll() {
        return _getAll("room");
    }

}
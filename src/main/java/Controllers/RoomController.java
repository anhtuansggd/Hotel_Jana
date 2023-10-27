package Controllers;


import Modules.Room;

import java.sql.*;
import java.time.LocalDate;


public class RoomController extends Controller{

    public RoomController() {
        super();
    }


    public TableState add(Room room){
        PreparedStatement ppsm = null;
        try{
            ppsm = connection.prepareStatement(
                    "INSERT INTO room VALUES(?,?,?)");
            ppsm.setString(1, room.getRoomNumber());
            ppsm.setString(2, room.getStyle().toString());
            ppsm.setInt(3, room.isSmoking());
            ppsm.executeUpdate();

            System.out.println("Room insert succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
            System.out.println("Room insert failed " + e.toString());
        }
        return getAll();
    }

    public TableState update(Room room){
        PreparedStatement ppsm = null;

        try{
            /*
            reservation_number is pk
             */
            ppsm = connection.prepareStatement(
                    "UPDATE room SET room_style=?, is_smoking=? WHERE room_number=?;");
            ppsm.setString(1, room.getStyle().toString());
            ppsm.setInt(2, room.isSmoking());
            ppsm.setString(3, room.getRoomNumber());
            ppsm.executeUpdate();

            System.out.println("RoomBooking update succeeded");
        }catch (SQLException e){
            System.out.println("RoomBooking update failed");
        }
        return getAll();
    }

    public TableState delete(Room room){
        PreparedStatement ppsm = null;
        try{
            ppsm = connection.prepareStatement(
                    "DELETE FROM room WHERE room_number=?;");
            ppsm.setString(1, room.getRoomNumber());
            ppsm.executeUpdate();

            System.out.println("Room delete succeeded");
        }catch (SQLException e){
            System.out.println("Room delete failed");
        }
        return getAll();
    }

    public TableState getAll() {
        return _getAll("room");
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
            ppsm = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM room r\n" +
                            "WHERE r.room_style = ?\n" +
                            "OR r.room_number IN (\n" +
                            "    SELECT rb.room_number\n" +
                            "\tFROM room_booking rb\n" +
                            "\tWHERE (rb.start_date <= ? AND rb.start_date + rb.duration > ?)\n" +
                            "\tOR (rb.start_date < ? AND rb.start_date + rb.duration >= ?)\n" +
                            ")");
            ppsm.setString(1, roomSearchQuery.roomStyle.toString());
            ppsm.setDate(2, Date.valueOf(roomSearchQuery.startDate));
            ppsm.setDate(3, Date.valueOf(roomSearchQuery.startDate));
            ppsm.setDate(4, Date.valueOf(roomSearchQuery.startDate.plusDays(roomSearchQuery.duration)));
            ppsm.setDate(5, Date.valueOf(roomSearchQuery.startDate.plusDays(roomSearchQuery.duration)));
            System.out.println(ppsm);
            ppsm.executeQuery();
            System.out.println("Room search succeeded");
            return getAll();
        }catch (SQLException e){
            System.out.println("Room search failed "+e.toString());
        }
        return null;
    }


    public boolean find(int reservation_number){
        return false;
    };



}
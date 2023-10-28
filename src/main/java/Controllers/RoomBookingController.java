package Controllers;


import Modules.RoomBooking;

import java.sql.*;
import java.time.LocalDate;


public class RoomBookingController extends Controller{

    public RoomBookingController() {
        super();
    }

    public TableState add(RoomBooking roombooking){
        try{
            ppsm = connection.prepareStatement(
                    "INSERT INTO room_booking VALUES(?,?,?,?,?)");
            ppsm.setInt(1, roombooking.getReservationNumber());
            ppsm.setDate(2, java.sql.Date.valueOf(roombooking.getStartDate()));
            ppsm.setInt(3, roombooking.getDurationInDays());
            ppsm.setString(4, roombooking.getRoomId());
            ppsm.setInt(5, roombooking.getGuestId());
            ppsm.executeUpdate();

            System.out.println("RoomBooking insert succeeded");
        }catch (SQLException e) {

            //Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            //logger.log(Level.INFO, "Inserted failed");

            System.out.println("RoomBooking insert failed " + e.toString());
        }
        close();
        return getAll();
    }

    public TableState update(RoomBooking roombooking){
        try{
            /*
            reservation_number is pk
             */
            ppsm = connection.prepareStatement(
                    "UPDATE room_booking SET start_date=?, duration=?, room_number=?, account_id=? WHERE reservation_number=?;");
            ppsm.setDate(1, java.sql.Date.valueOf(roombooking.getStartDate()));
            ppsm.setInt(2, roombooking.getDurationInDays());
            ppsm.setString(3, roombooking.getRoomId());
            ppsm.setInt(4, roombooking.getGuestId());
            ppsm.setInt(5, roombooking.getReservationNumber());
            ppsm.executeUpdate();

            System.out.println("RoomBooking update succeeded");
        }catch (SQLException e){
            System.out.println("RoomBooking update failed");
        }
        close();
        return getAll();
    }


    public TableState delete(RoomBooking roombooking){
        try{
            ppsm = connection.prepareStatement(
                    "DELETE FROM room_booking WHERE reservation_number=?;");
            ppsm.setInt(1, roombooking.getReservationNumber());
            ppsm.executeUpdate();

            System.out.println("RoomBooking delete succeeded");
        }catch (SQLException e){
            System.out.println("RoomBooking delete failed "+e.toString());
        }
        close();
        return getAll();
    }

    public class RoomBookingSearchQuery {
        public int reservationNumber;
        public LocalDate startDate;
        public int duration;
        public String room_number;
        public String account_id;
    }

    /**
     * start_date, duration, room_number, account_id are allowed to be null
     **/
    public TableState search(RoomBookingSearchQuery roomBookingSearchQuery){
        try{
            ppsm = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM room_booking\n" +
                            "WHERE reservation_number = ?\n" +
                            "OR ((start_date LIKE ? OR start_date IS NULL)\n" +
                            "AND (duration=? OR duration IS NULL)\n" +
                            "AND (room_number=? OR room_number IS NULL)\n" +
                            "AND (account_id=? OR account_id IS NULL));");
            /**
             * 3 variables below are to handle value of "" as null when being passed from GUI.
             * Primitive can't be null -> Wrapper class such as Integer
             */
            Integer reservationNumber = "".equals(roomBookingSearchQuery.reservationNumber) ? null : (roomBookingSearchQuery.reservationNumber);
            LocalDate startDate = "".equals(roomBookingSearchQuery.startDate) ? null : (roomBookingSearchQuery.startDate);
            Integer duration = "".equals(roomBookingSearchQuery.duration) ? null : (roomBookingSearchQuery.duration);

            ppsm.setInt(1, reservationNumber);
            ppsm.setDate(2, java.sql.Date.valueOf(roomBookingSearchQuery.startDate));
            ppsm.setInt(3, roomBookingSearchQuery.duration);
            ppsm.setString(4, roomBookingSearchQuery.room_number.equals("")? null : roomBookingSearchQuery.room_number);
            ppsm.setString(5, roomBookingSearchQuery.account_id.equals("")? null : roomBookingSearchQuery.account_id);
            ppsm.executeQuery();
            System.out.println("Room search succeeded");
            return getAll();
        }catch (SQLException e){
            System.out.println("Room search failed "+e.toString());
        }
        close();
        return null;
    }

    @Override
    public TableState getAll() {
        return _getAll("room_booking");
    }


    public boolean find(int reservation_number){
        return false;
    };


}
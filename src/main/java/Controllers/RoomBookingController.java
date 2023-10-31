package Controllers;


import Modules.RoomBooking;

import java.sql.*;
import java.time.LocalDate;


public class RoomBookingController extends Controller<RoomBooking>{
    private static final String insertRoomSQL = "INSERT INTO room_booking VALUES(?,?,?,?,?);";
    private static final String updateRoomSQL = "UPDATE room_booking SET start_date=?, duration=?, room_number=?, account_id=? WHERE reservation_number=?;";
    private static final String deleteRoomSQL = "DELETE FROM room_booking WHERE reservation_number=?;";
    private static final String searchRoomSQL = "SELECT *\n" +
            "FROM room_booking\n" +
            "WHERE reservation_number = ?\n" +
            "OR ((start_date LIKE ? OR start_date IS NULL)\n" +
            "AND (duration=? OR duration IS NULL)\n" +
            "AND (room_number=? OR room_number IS NULL)\n" +
            "AND (account_id=? OR account_id IS NULL));";

    public RoomBookingController() {
        super();
    }

    @Override
    public TableState add(RoomBooking roombooking){
        try{
            ppsm = connection.prepareStatement(insertRoomSQL);
            ppsm.setInt(1, roombooking.getReservationNumber());
            ppsm.setDate(2, java.sql.Date.valueOf(roombooking.getStartDate()));
            ppsm.setInt(3, roombooking.getDurationInDays());
            ppsm.setString(4, roombooking.getRoomId());
            ppsm.setInt(5, roombooking.getGuestId());
            System.out.println("Con1");
            execute(ppsm);
            System.out.println("RoomBooking insert succeeded");
        }catch (SQLException e) {

            //Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            //logger.log(Level.INFO, "Inserted failed");

            System.out.println("RoomBooking insert failed " + e.toString());
        }
        close();
        return getAll();
    }

    @Override
    public TableState update(RoomBooking roombooking){
        try{
            /*
            reservation_number is pk
             */
            ppsm = connection.prepareStatement(updateRoomSQL);
            ppsm.setDate(1, java.sql.Date.valueOf(roombooking.getStartDate()));
            ppsm.setInt(2, roombooking.getDurationInDays());
            ppsm.setString(3, roombooking.getRoomId());
            ppsm.setInt(4, roombooking.getGuestId());
            ppsm.setInt(5, roombooking.getReservationNumber());
            execute( ppsm);

            System.out.println("RoomBooking update succeeded");
        }catch (SQLException e){
            System.out.println("RoomBooking update failed");
        }
        close();
        return getAll();
    }


    @Override
    public TableState delete(RoomBooking roombooking){
        try{
            ppsm = connection.prepareStatement(deleteRoomSQL);
            ppsm.setInt(1, roombooking.getReservationNumber());
            execute(ppsm);
            System.out.println("RoomBooking delete succeeded");
        }catch (SQLException e){
            System.out.println("RoomBooking delete failed "+e.toString());
        }
        close();
        return getAll();
    }

    public static class RoomBookingSearchQuery {
        public int reservationNumber;
        public LocalDate startDate;
        public int duration;
        public String room_number;
        public String account_id;

        public RoomBookingSearchQuery(int reservationNumber, LocalDate startDate, int duration, String room_number, String account_id) {
            this.reservationNumber = reservationNumber;
            this.startDate = startDate;
            this.duration = duration;
            this.room_number = room_number;
            this.account_id = account_id;
        }
    }

    /**
     * start_date, duration, room_number, account_id are allowed to be null
     **/
//    public void search(RoomBookingSearchQuery roomBookingSearchQuery){
//        try{
//            ppsm = connection.prepareStatement(searchRoomSQL);
//            /**
//             * 3 variables below are to handle value of "" as null when being passed from GUI.
//             * Primitive can't be null -> Wrapper class such as Integer
//             */
//            Integer reservationNumber = "".equals(roomBookingSearchQuery.reservationNumber) ? null : (roomBookingSearchQuery.reservationNumber);
//            LocalDate startDate = "".equals(roomBookingSearchQuery.startDate) ? null : (roomBookingSearchQuery.startDate);
//            Integer duration = "".equals(roomBookingSearchQuery.duration) ? null : (roomBookingSearchQuery.duration);
//
//            ppsm.setInt(1, reservationNumber);
//            ppsm.setDate(2, java.sql.Date.valueOf(roomBookingSearchQuery.startDate));
//            ppsm.setInt(3, roomBookingSearchQuery.duration);
//            ppsm.setString(4, roomBookingSearchQuery.room_number.equals("")? null : roomBookingSearchQuery.room_number);
//            ppsm.setString(5, roomBookingSearchQuery.account_id.equals("")? null : roomBookingSearchQuery.account_id);
//            ResultSet rs = executeSearch(ppsm);
//
//            while (rs.next()){
//                String[] row= new String[6];
//                for(int i=1; i<=5; i++){
//                    row[i-1] = rs.getString(i);
//                }
//                arrayList.add(row);
//            }
//            System.out.println("Account search succeeded");
//            String[][] resultArray = new String[arrayList.size()][];
//            resultArray = arrayList.toArray(resultArray);
//            System.out.println("Room search succeeded");
//        }catch (SQLException e){
//            System.out.println("Room search failed "+e.toString());
//        }
//        close();
//    }

    @Override
    public TableState getAll() {
        return _getAll("room_booking");
    }

}
package Controllers;


import Modules.Room;

import java.sql.*;


public class RoomController extends Controller{

    public RoomController() {
        super();
    }


    public TableState add(Room R){
        PreparedStatement ppsm = null;
        try{
            ppsm = connection.prepareStatement(
                    "INSERT INTO room VALUES(?,?,?)");
            ppsm.setString(1, R.getRoomNumber());
            ppsm.setString(2, R.getStyle().toString());
            ppsm.setInt(3, R.isSmoking());
            ppsm.executeUpdate();

            System.out.println("Room insert succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
            System.out.println("Room insert failed " + e.toString());
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("connection close failed");
            }

            try{
                ppsm.close();
            }catch (SQLException e){
                System.out.println("ppsm close failed");
            }
        }
        return getAll();
    }

    public TableState update(Room R){
        PreparedStatement ppsm = null;

        try{
            /*
            reservation_number is pk
             */
            ppsm = connection.prepareStatement(
                    "UPDATE room SET room_style=?, is_smoking=? WHERE room_number=?;");
            ppsm.setString(1, R.getStyle().toString());
            ppsm.setInt(2, R.isSmoking());
            ppsm.setString(3, R.getRoomNumber());
            ppsm.executeUpdate();

            System.out.println("RoomBooking update succeeded");
        }catch (SQLException e){
            System.out.println("RoomBooking update failed");
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("connection close failed");
            }

            try{
                ppsm.close();
            }catch (SQLException e){
                System.out.println("ppsm close failed");
            }
        }
        return getAll();
    }

    public TableState delete(Room R){
        PreparedStatement ppsm = null;

        try{
            ppsm = connection.prepareStatement(
                    "DELETE FROM room WHERE room_number=?;");
            ppsm.setString(1, R.getRoomNumber());
            ppsm.executeUpdate();

            System.out.println("Room delete succeeded");
        }catch (SQLException e){
            System.out.println("Room delete failed");
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("connection close failed");
            }

            try{
                ppsm.close();
            }catch (SQLException e){
                System.out.println("ppsm close failed");
            }
        }
        return getAll();
    }




    public TableState getAll() {
        return _getAll("room");
    }


    public void search(RoomSearchQuery o) {

    }


    public boolean find(int reservation_number){
        return false;
    };



}
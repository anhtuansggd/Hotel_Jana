package Controllers;


import Modules.Room;

import java.sql.*;


public class RoomSearchController extends Controller implements Search<RoomSearchQuery>{
    public void add(Room R){
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
    }

    @Override
    public void search(RoomSearchQuery o) {

    }

    public TableState getAll(){
        return _getAll("room");
    }

    public boolean find(int reservation_number){
        return false;
    };



}
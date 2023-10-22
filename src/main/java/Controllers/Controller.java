package Controllers;

import java.sql.*;

public class Controller {
    Connection connection;

    Controller() {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/hotel_dbms";
            String userName = "root";
            String password = "firsttime";
            connection = DriverManager.getConnection(dbUrl, userName, password);

            System.err.println("Connected to the server");

            // Statement stmt = conn.createStatement();
            // ResultSet rSet = stmt.executeQuery("SELECT * FROM room");

            // while (rSet.next()) {
            //     int n = rSet.getInt("room_number");
            //     System.out.println(n);
            // }

        } catch (SQLException e) {
            System.err.println("Error connecting to the server");
        }
    }

    public static void main(String[] args) {
        Controller con = new Controller();
    }
}
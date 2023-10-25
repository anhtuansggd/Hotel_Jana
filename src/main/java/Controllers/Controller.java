package Controllers;

import java.sql.*;

public class Controller {
    protected Connection connection;

    public Controller() {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/hotel_dbms";
            String userName = "tuan";
            String password = "Password123!";
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

    public class TableState {
        public String[] columns;
        public String[][] data;

        public TableState(String[] c, String[][] d) {
            columns = c;
            data = d;
        }
    }

    public static void main(String[] args) {
        Controller con = new Controller();
        System.out.println("Establish " + con);
    }
}




/*
public interface Controller<T>{
    static Connection getConnection(){
        Connection connection = null;
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/hotel_dbms";
            String userName = "tuan";
            String password = "Password123!";
            return DriverManager.getConnection(dbUrl, userName, password);

        } catch (SQLException e) {
            System.err.println("Error connecting to the server");
        }
        System.err.println("Connected to the server");
        return connection;
    }

}
*/

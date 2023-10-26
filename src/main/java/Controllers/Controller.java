package Controllers;

import Modules.Room;

import java.sql.*;
import java.util.ArrayList;


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

    public TableState add(){ return null; }
    public TableState update(){ return null; }
    public TableState delete(){ return null; }
    public TableState search(){ return null; }



    public TableState getAll() {
        return null;
    }

    protected TableState _getAll(String tableName) {
        String[] columns = getAccountColumns(tableName);
        String[][] data = getAccountData(tableName, columns);
        return new TableState(columns, data);
    }

    private String[][] getAccountData(String tableName, String[] columns) {
        ArrayList<String[]> accountsArrayList = new ArrayList<String[]>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rSet = stmt.executeQuery("SELECT * FROM " + tableName);

            while (rSet.next()) {
                String[] accountRow = new String[columns.length];
                for (int i = 0; i < columns.length; i++) {
                    accountRow[i] = rSet.getString(i+1);
                }
                accountsArrayList.add(accountRow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[][] accountsArray = new String[accountsArrayList.size()][];
        accountsArray = accountsArrayList.toArray(accountsArray);
        return accountsArray;
    }

    private String[] getAccountColumns(String tableName) {
        ArrayList<String> columnsArrayList = new ArrayList<String>();

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columnSet = metaData.getColumns(null, null, tableName, null);

            while (columnSet.next()) {
                columnsArrayList.add(columnSet.getString("COLUMN_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] columnsArray = new String[columnsArrayList.size()];
        columnsArray = columnsArrayList.toArray(columnsArray);
        return columnsArray;
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
    //Connection connection = null;
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


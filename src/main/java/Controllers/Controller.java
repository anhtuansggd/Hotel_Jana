package Controllers;

import Modules.Room;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;


public class Controller {
    /**
     * Instead of create new connection for each query
     * -> Implementing connection pool as cache of connection for reuse purpose
     * -> Use HikariCP library for this
     */

    protected static HikariDataSource dataSource;

    static{
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_dbms");
        config.setUsername("tuan");
        config.setPassword("Password123!");

        dataSource = new HikariDataSource(config);
        System.err.println("Connected to the server");
    }

    protected Connection connection;
    protected PreparedStatement ppsm;
    public Controller() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Error connecting to the server");
        }
    }

    public void close(){
        try{
            if(ppsm!=null){
                ppsm.close();
            }
        }catch (SQLException e){
            System.out.println("PreparedStatement close failed " + e.toString());
            System.err.println("Error closing PreparedStatement");
        } finally {
            try {
                if(connection!=null && !connection.isClosed()){
                    connection.close();
                }
            }catch (SQLException e){
                System.out.println("Connection close failed " + e.toString());
                System.err.println("Error closing Connection");
            }
        }
    }


    public TableState add(){ return null; }
    public TableState update(){ return null; }
    public TableState delete(){ return null; }
    public TableState search(){ return null; }

    protected void executeInsert(String sql, PreparedStatement ppsm) throws SQLException{
        System.out.println("Con2");
        ppsm.executeUpdate();
        System.out.println("Inserted");
    }

    protected void executeUpdate(String sql, PreparedStatement ppsm) throws SQLException{
        System.out.println("Con3");
        ppsm.executeUpdate();
        System.out.println("Updated");
    }

    protected void executeDelete(String sql, PreparedStatement ppsm) throws SQLException{
        System.out.println("Con4");
        ppsm.executeUpdate();
        System.out.println("Deleted");
    }

    protected void executeSearch(String sql, PreparedStatement ppsm) throws SQLException{
        System.out.println("Con5");
        ppsm.executeQuery();
        System.out.println("Searched");
    }



    public TableState getAll() {
        System.out.println("getAll() function not initialized");
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


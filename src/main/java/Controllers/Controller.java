package Controllers;

import Modules.Room;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.*;
import java.io.*;


public class Controller {
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    /**
     * Instead of create new connection for each query
     * -> Implementing connection pool as cache of connection for reuse purpose
     * -> Use HikariCP library for this
     */

    protected static HikariDataSource dataSource;

    static{
        loadConfig();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);

        dataSource = new HikariDataSource(config);
    }

    protected Connection connection;
    protected PreparedStatement ppsm;
    public Controller() {
        try {
            connection = dataSource.getConnection();
            System.err.println("Connected to the server");
        } catch (SQLException e) {
            System.err.println("Error connecting to the server");
        }
    }

    /**
     * System.out.println(System.getProperty("user.dir"));
     * Use above command to get path, then replace before /config.properties
     */

    private static void loadConfig() {
        Properties prop = new Properties();
        //System.out.println(System.getProperty("user.dir"));
        try(FileInputStream file = new FileInputStream("/home/tuan/Documents/Java/Hotel_Java/config.properties")){
            prop.load(file);
            dbUrl = prop.getProperty("db.url");
            dbUsername = prop.getProperty("db.username");
            dbPassword = prop.getProperty("db.password");
        }catch (IOException e){
            System.out.println("loadConfig "+e.toString());
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
    
    protected void executeInsert(String sql, PreparedStatement ppsm){
        try{
            if(connection.isClosed()){
                connection = dataSource.getConnection();
            }
            ppsm.executeUpdate();
            System.out.println("Inserted");
        }catch (SQLException e){
            System.out.println("Insert failed " + e.toString());
        }

    }

    protected void executeUpdate(String sql, PreparedStatement ppsm){
        try{
            if(connection.isClosed()){
                connection = dataSource.getConnection();
            }
            ppsm.executeUpdate();
            System.out.println("Updated");
        }catch (SQLException e){
            System.out.println("Update failed " + e.toString());
        }
    }

    protected void executeDelete(String sql, PreparedStatement ppsm){
        try{
            if(connection.isClosed()){
                connection = dataSource.getConnection();
            }
            ppsm.executeUpdate();
            System.out.println("Deleted");
        }catch (SQLException e){
            System.out.println("Delete failed " + e.toString());
        }
    }

    protected void executeSearch(String sql, PreparedStatement ppsm){
        try{
            if(connection.isClosed()){
                connection = dataSource.getConnection();
            }
            ppsm.executeQuery();
            System.out.println("Searched");
        }catch (SQLException e){
            System.out.println("Search failed " + e.toString());
        }
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
            if(connection.isClosed()){
                connection = dataSource.getConnection();
            }
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
            if(connection.isClosed()){
                connection = dataSource.getConnection();
            }
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


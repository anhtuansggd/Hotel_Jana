package Controllers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.*;
import java.io.*;



public abstract class Controller<T> {
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    protected static Connection connection;

    private void init(){
        //try(FileInputStream file = new FileInputStream("D:\\Documents\\3\\1 & 2\\OOP\\Hotel_Jana\\config.properties"))){
        //try(FileInputStream file = new FileInputStream("C:\\Users\\ACER\\Documents\\GitHub\\Hotel_Jana\\config.properties"){
        try(FileInputStream file = new FileInputStream("/home/tuan/Documents/Java/Hotel_Jana/config.properties")){
            Properties prop = new Properties();
            prop.load(file);
            dbUrl = prop.getProperty("db.url");
            dbUsername = prop.getProperty("db.username");
            dbPassword = prop.getProperty("db.password");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if(connection != null){
                System.err.println("Connection connected");
            }
        }catch (IOException | SQLException e){
            System.out.println("loadConfig "+e.toString());
        }
    }


    public Controller() {
        if (connection == null){
            init();
        }
    }

    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }
        System.out.println("getCon err");
        return null;
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (connection != null) {
                    try {
                        connection.close();
                        System.err.println("Connection closed");
                    } catch (SQLException e) {
                        System.err.println("Connection close err");
                    }
                }
            }
        });
    }



    /**
     * Due to
     */
    public abstract TableState add(T entity);
    public abstract TableState update(T entity);
    public abstract TableState delete(T entity);


    protected void execute(PreparedStatement ppsm){
        try{
            ppsm.executeUpdate();
            System.out.println("Executed");
        }catch (SQLException e){
            System.out.println("Execute failed " + e.toString());
        }

    }

    protected ResultSet executeSearch(PreparedStatement ppsm){
        try{
            if(connection.isClosed()){
                connection = getConnection();
            }
            System.out.println("Searched");
            return ppsm.executeQuery();
        }catch (SQLException e){
            System.out.println("Search failed " + e.toString());
        }
        return null;
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

    protected String[][] getAccountData(String tableName, String[] columns) {
        ArrayList<String[]> accountsArrayList = new ArrayList<String[]>();

        try {
            if(connection.isClosed()){
                connection = getConnection();
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

    protected String[] getAccountColumns(String tableName) {
        ArrayList<String> columnsArrayList = new ArrayList<String>();

        try {
            if(connection.isClosed()){
                connection = getConnection();
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

    public static class TableState {
        public String[] columns;
        public String[][] data;

        public TableState(String[] c, String[][] d) {
            columns = c;
            data = d;
        }
    }

    public int getTotalRows(String countAllSQL, PreparedStatement ppsm){
        try{
            ppsm = connection.prepareStatement(countAllSQL);
            ResultSet rSet = executeSearch(ppsm);
            rSet.next();
            return rSet.getInt(1);
        }catch (SQLException e) {
            System.out.println("count failed " + e.toString());
        }
        return -1;
    }


    public static void main(String[] args) {
        //Controller con = new Controller();
        //System.out.println("Establish " + con);
    }
}




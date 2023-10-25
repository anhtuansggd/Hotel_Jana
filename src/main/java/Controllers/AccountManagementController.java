package Controllers;

import Modules.Account;
import java.sql.*;

import java.util.ArrayList;

public class AccountManagementController extends Controller {
    public AccountManagementController() {
        super();
    }

    public void addAccount(Account account) {
        
    }

    public void updateAccount(Account account) {

    }

    public void deleteAccount(Account account) {

    }

    public void searchAccount(Account account) {

    }

    public TableState reset() {
        String[] columns = getAccountColumns();
        String[][] data = getAccountData(columns);
        return new TableState(columns, data);
    }

    private String[][] getAccountData(String[] columns) {
        ArrayList<String[]> accountsArrayList = new ArrayList<String[]>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rSet = stmt.executeQuery("SELECT * FROM account");

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

    private String[] getAccountColumns() {
        ArrayList<String> columnsArrayList = new ArrayList<String>();

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columnSet = metaData.getColumns(null, null, "account", null);

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
}

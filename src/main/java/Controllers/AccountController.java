package Controllers;

import Modules.Account;

import java.sql.*;
import java.util.*;

public class AccountController extends Controller<Account>{
    private final String insertAccountSQL = "INSERT INTO account VALUES(?,?,?,?,?,?);";
    private final String updateAccountSQL = "UPDATE account \n" +
            "SET account_type = ?,\n" +
            "    password = COALESCE(?, password), \n" +
            "    name = COALESCE(?, name), \n" +
            "    race = ?\n" +
            "WHERE user_name = ?;";
    private final String deleteAccountSQL = "DELETE FROM account WHERE id=?;";
    private final String searchAccountSQL = "SELECT *\n" +
            "FROM account\n" +
            "WHERE (account_type=?)\n" +
            "AND (user_name=? OR ? IS NULL) \n" +
            "AND (name=? OR ? IS NULL) \n" +
            "AND (race=?);";
    private final String countTotalSQL = "select count(id)\n" +
            "from account;";


    public AccountController() {
        super();
    }

    /**
     * if "getTotalRows()" is executed after "ppsm = connection."
     * then there will be an error -> Why?
     */
    @Override
    public TableState add(Account account) {
        try{
            int total = getTotalRows(countTotalSQL)+1;
            ppsm = connection.prepareStatement(insertAccountSQL);
            ppsm.setString(1, String.valueOf(total));
            ppsm.setString(2, account.getAccountType().toString());
            ppsm.setString(3, account.getUsername());
            ppsm.setString(4, Account.hashPassword(account.getPassword()));
            ppsm.setString(5, account.getName());
            ppsm.setString(6, account.getRace().toString());
            execute(ppsm);

            System.out.println("Account insert succeeded");
        }catch (SQLException e){
            System.out.println("Account insert failed " + e.toString());
        }
        return getAll();
    }

    /**
     * Only username, password, name are allowed to be null
     */
    @Override
    public TableState update(Account account) {
        try{
            ppsm = connection.prepareStatement(updateAccountSQL);
            ppsm.setString(1, account.getAccountType().toString());
            //ppsm.setString(2, account.getUsername().equals("")? null : account.getUsername());
            ppsm.setString(2, account.getPassword().equals("")? null : Account.hashPassword(account.getPassword()));
            ppsm.setString(3, account.getName().equals("")? null : account.getName());
            ppsm.setString(4, account.getRace().toString());
            ppsm.setString(5, account.getUsername());
            //ppsm.setString(5, account.getId());
            System.out.println(ppsm);
            execute(ppsm);

            System.out.println("Account update succeeded");
        }catch (SQLException e){
            System.out.println("Account update failed " + e.toString());
        }
        return getAll();
    }

    @Override
    public TableState delete(Account account) {
        try{
            ppsm = connection.prepareStatement(deleteAccountSQL);
            ppsm.setString(1, account.getId());
            execute(ppsm);
            System.out.println("Account delete succeeded");
        }catch (SQLException e){
            System.out.println("Account delete failed " + e.toString());
        }
        return getAll();
    }



    /*
    * id, username, name are allowed to be null
     */
    public TableState search(Account accountSearchQuery) {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        try{
            ppsm = connection.prepareStatement(searchAccountSQL);
            ppsm.setString(1, accountSearchQuery.getType().toString());
            ppsm.setString(2, accountSearchQuery.getUsername().equals("")? null : accountSearchQuery.getUsername());
            ppsm.setString(3, accountSearchQuery.getUsername().equals("")? null : accountSearchQuery.getUsername());
            ppsm.setString(4, accountSearchQuery.getName().equals("")? null : accountSearchQuery.getName());
            ppsm.setString(5, accountSearchQuery.getName().equals("")? null : accountSearchQuery.getName());
            ppsm.setString(6, accountSearchQuery.getRace().toString());
            ResultSet rs = executeSearch(ppsm);

            while (rs.next()){
                String[] row= new String[6];
                for(int i=1; i<=6; i++){
                    row[i-1] = rs.getString(i);
                }
                arrayList.add(row);
            }
            System.out.println("Account search succeeded");
            String[][] resultArray = new String[arrayList.size()][];
            resultArray = arrayList.toArray(resultArray);
            TableState resultTableState = new TableState( getAccountColumns("account"), resultArray);
            //close();
            return resultTableState;
        }catch (SQLException e){
            System.out.println("Account search failed "+e.toString());
        }
        return null;
    }


    @Override
    public TableState getAll() {
        return _getAll("account");
    }
}

package Controllers;

import Modules.Account;
import com.google.protobuf.Value;

import java.sql.*;
import java.util.*;

public class AccountController extends Controller<Account>{
    private final String insertAccountSQL = "INSERT INTO account VALUES(?,?,?,?,?,?);";
    private final String updateAccountSQL = "UPDATE account \n" +
            "SET account_type = ?,\n" +
            "    user_name = COALESCE(?, user_name), \n" +
            "    password = COALESCE(?, password), \n" +
            "    name = COALESCE(?, name), \n" +
            "    race = ?\n" +
            "WHERE id = ?;";
    private final String deleteAccountSQL = "DELETE FROM account WHERE id=?;";
    private final String searchAccountSQL = "SELECT *\n" +
            "FROM account\n" +
            "WHERE (id=? OR ? IS NULL) \n" +
            "AND (account_type=? OR ? IS NULL) \n" +
            "AND (user_name=? OR ? IS NULL) \n" +
            "AND (name=? OR ? IS NULL) \n" +
            "AND (race=? OR ? IS NULL);";
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
        close();
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
            ppsm.setString(2, account.getUsername().equals("")? null : account.getUsername());
            ppsm.setString(3, account.getPassword().equals("")? null : Account.hashPassword(account.getPassword()));
            ppsm.setString(4, account.getName().equals("")? null : account.getName());
            ppsm.setString(5, account.getRace().toString());
            ppsm.setString(6, String.valueOf(getTotalRows(countTotalSQL)+1));
            execute(ppsm);

            System.out.println("Account update succeeded");
        }catch (SQLException e){
            System.out.println("Account update failed " + e.toString());
        }
        close();
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
        close();
        return getAll();
    }



    /*
    * id, username, name are allowed to be null
     */
    public String[][] search(Account accountSearchQuery) {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        try{
            ppsm = connection.prepareStatement(searchAccountSQL);
            ppsm.setString(1, accountSearchQuery.getId().equals("")? null : accountSearchQuery.getId());
            ppsm.setString(2, accountSearchQuery.getId().equals("")? null : accountSearchQuery.getId());
            ppsm.setString(3, accountSearchQuery.getType().toString().equals("")? null : accountSearchQuery.getType().toString());
            ppsm.setString(4, accountSearchQuery.getType().toString().equals("")? null : accountSearchQuery.getType().toString());
            ppsm.setString(5, accountSearchQuery.getUsername().equals("")? null : accountSearchQuery.getUsername());
            ppsm.setString(6, accountSearchQuery.getUsername().equals("")? null : accountSearchQuery.getUsername());
            ppsm.setString(7, accountSearchQuery.getName().equals("")? null : accountSearchQuery.getName());
            ppsm.setString(8, accountSearchQuery.getName().equals("")? null : accountSearchQuery.getName());
            ppsm.setString(9, accountSearchQuery.getRace().toString().equals("")? null : accountSearchQuery.getRace().toString());
            ppsm.setString(10, accountSearchQuery.getRace().toString().equals("")? null : accountSearchQuery.getRace().toString());
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
            close();
            return resultArray;
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

package Controllers;

import Modules.Account;
import java.sql.*;
import java.util.*;

public class AccountController extends Controller<Account>{
    private static final String insertAccountSQL = "INSERT INTO account VALUES(?,?,?,?,?,?);";
    private static final String updateAccountSQL = "UPDATE account SET account_type=?, user_name=?, password=?, name=?, race=? WHERE id=?;";
    private static final String deleteAccountSQL = "DELETE FROM account WHERE id=?;";
    private static final String searchAccountSQL = "SELECT *\n" +
            "FROM account\n" +
            "WHERE (id=? OR ? IS NULL) \n" +
            "AND (account_type=? OR ? IS NULL) \n" +
            "AND (user_name=? OR ? IS NULL) \n" +
            "AND (name=? OR ? IS NULL) \n" +
            "AND (race=? OR ? IS NULL);";



    public AccountController() {
        super();
    }


    @Override
    public TableState add(Account account) {
        try{
            ppsm = connection.prepareStatement(insertAccountSQL);
            ppsm.setString(1, account.getId());
            ppsm.setString(2, account.getAccountType().toString());
            ppsm.setString(3, account.getUsername());
            ppsm.setString(4, account.hashPassword(account.getPassword()));
            ppsm.setString(5, account.getName());
            ppsm.setString(6, account.getRace().toString());
            execute(ppsm);

            System.out.println("Account insert succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
            System.out.println("Account insert failed " + e.toString());
        }
        close();
        return getAll();
    }

    @Override
    public TableState update(Account account) {
        try{
            ppsm = connection.prepareStatement(updateAccountSQL);
            ppsm.setString(1, account.getAccountType().toString());
            ppsm.setString(2, account.getUsername());
            ppsm.setString(3, account.hashPassword(account.getPassword()));
            ppsm.setString(4, account.getName());
            ppsm.setString(5, account.getRace().toString());
            ppsm.setString(6, account.getId());
            execute(ppsm);

            System.out.println("Account update succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
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
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
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

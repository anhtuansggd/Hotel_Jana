package Controllers;

import Modules.Account;
import java.sql.*;

public class AccountController extends Controller<Account>{
    private static final String insertAccountSQL = "INSERT INTO account VALUES(?,?,?,?,?,?);";
    private static final String updateAccountSQL = "UPDATE account SET account_type=?, user_name=?, password=?, name=?, race=? WHERE id=?;";
    private static final String deleteAccountSQL = "DELETE FROM account WHERE id=?;";
    private static final String searchAccountSQL = "SELECT *\n" +
            "FROM account\n" +
            "WHERE (id=? OR id IS NULL) \n" +
            "AND account_type=? \n" +
            "AND (user_name=? OR id IS NULL) \n" +
            "AND (name=? OR id IS NULL) \n" +
            "AND race=?;";



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
            executeInsert(insertAccountSQL, ppsm);

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
            executeUpdate(updateAccountSQL, ppsm);

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
            executeDelete(deleteAccountSQL, ppsm);
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

    public static class AccountSearchQuery {
        private String id;
        private Account.AccountType type;
        private String username;
        private String name;
        private Account.Race race;

        public AccountSearchQuery(String i, Account.AccountType t, String u, String n, Account.Race r) {
            id = i;
            type = t;
            username = u;
            name = n;
            race = r;
        }
    }

    /*
    * id, username, name are allowed to be null
     */
    public TableState search(AccountSearchQuery accountSearchQuery) {
        try{
            ppsm = connection.prepareStatement(searchAccountSQL);
            ppsm.setString(1, accountSearchQuery.id.equals("")? null : accountSearchQuery.id);
            ppsm.setString(2, accountSearchQuery.type.toString());
            ppsm.setString(3, accountSearchQuery.username.equals("")? null : accountSearchQuery.username);
            ppsm.setString(4, accountSearchQuery.name.equals("")? null : accountSearchQuery.name);
            ppsm.setString(5, accountSearchQuery.race.toString());
            executeSearch(searchAccountSQL, ppsm);
            System.out.println("Account search succeeded");
            return getAll();
        }catch (SQLException e){
            System.out.println("Account search failed "+e.toString());
        }
        close();
        return null;
    }

    @Override
    public TableState getAll() {
        return _getAll("account");
    }
}

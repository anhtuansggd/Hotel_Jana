package Controllers;

import Modules.Account;
import java.sql.*;

public class AccountController extends Controller {
    public AccountController() {
        super();
    }

    public TableState addAccount(Account account) {
        PreparedStatement ppsm = null;
        try{
            ppsm = connection.prepareStatement(
                    "INSERT INTO account VALUES(?,?,?,?,?,?)");
            ppsm.setString(1, account.getId());
            ppsm.setString(2, account.getAccountType().toString());
            ppsm.setString(3, account.getUsername());
            ppsm.setString(4, account.getPassword());
            ppsm.setString(5, account.getName());
            ppsm.setString(6, account.getRace().toString());
            ppsm.executeUpdate();

            System.out.println("Account insert succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
            System.out.println("Account insert failed " + e.toString());
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("connection close failed");
            }

            try{
                ppsm.close();
            }catch (SQLException e){
                System.out.println("ppsm close failed");
            }
        }
        return getAll();
    }

    public TableState updateAccount(Account account) {
        PreparedStatement ppsm = null;
        try{
            ppsm = connection.prepareStatement(
                    "UPDATE account SET account_type=?, user_name=?, password=?, name=?, race=? WHERE id=?");
            ppsm.setString(1, account.getAccountType().toString());
            ppsm.setString(2, account.getUsername());
            ppsm.setString(3, account.getPassword());
            ppsm.setString(4, account.getName());
            ppsm.setString(5, account.getRace().toString());
            ppsm.setString(6, account.getId());
            ppsm.executeUpdate();

            System.out.println("Account update succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
            System.out.println("Account update failed " + e.toString());
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("connection close failed");
            }

            try{
                ppsm.close();
            }catch (SQLException e){
                System.out.println("ppsm close failed");
            }
        }
        return getAll();
    }

    public TableState deleteAccount(Account account) {
        PreparedStatement ppsm = null;
        try{
            ppsm = connection.prepareStatement(
                    "DELETE FROM account WHERE id=? ");
            ppsm.setString(1, account.getId());
            ppsm.executeUpdate();
            System.out.println("Account delete succeeded");
        }catch (SQLException e){
            /*
            Logger logger = Logger.getLogger(RoomBookingController.class.getName());
            logger.log(Level.INFO, "Inserted failed");
             */
            System.out.println("Account delete failed " + e.toString());
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("connection close failed");
            }

            try{
                ppsm.close();
            }catch (SQLException e){
                System.out.println("ppsm close failed");
            }
        }
        return getAll();
    }

    public class AccountSearchQuery {
        private String id;
        private Account.AccountType type;
        private String username;
        private String name;
        private Account.Race race;
    }

    /*
    * id, username, name are allowed to be null
     */
    public TableState search(AccountSearchQuery accountSearchQuery) {
        PreparedStatement ppsm = null;
        try{
            Account acount = new Account("4", Account.AccountType.GUEST, "frfr", "123456", "Frank", Account.Race.FRANKENSTEIN);
            ppsm = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM account\n" +
                            "WHERE (id=? OR id IS NULL) \n" +
                            "AND account_type=? \n" +
                            "AND (user_name=? OR id IS NULL) \n" +
                            "AND (name=? OR id IS NULL) \n" +
                            "AND race=?;");
            ppsm.setString(1, accountSearchQuery.id.equals("")? null : accountSearchQuery.id);
            ppsm.setString(2, accountSearchQuery.type.toString());
            ppsm.setString(3, accountSearchQuery.username.equals("")? null : accountSearchQuery.username);
            ppsm.setString(4, accountSearchQuery.name.equals("")? null : accountSearchQuery.name);
            ppsm.setString(5, accountSearchQuery.race.toString());
            ppsm.executeQuery();
            System.out.println("Account search succeeded");
            return getAll();
        }catch (SQLException e){
            System.out.println("Account search failed "+e.toString());
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("connection close failed "+ e.toString());
            }

            try{
                ppsm.close();
            }catch (SQLException e){
                System.out.println("ppsm close failed");
            }

        }
        return null;
    }

    public TableState getAll() {
        return _getAll("account");
    }
}

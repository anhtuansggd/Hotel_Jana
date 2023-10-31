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
            "WHERE (id=? OR id IS NULL) \n" +
            "OR (account_type=? OR account_type IS NULL) \n" +
            "OR (user_name=? OR user_name IS NULL) \n" +
            "OR (name=? OR name IS NULL) \n" +
            "OR (race=? OR race IS NULL);";



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

    public static class AccountSearchQuery {
        private String id;
        private String type;
        private String username;
        private String name;
        private String race;

        public AccountSearchQuery(String i, String t, String u, String n, String r) {
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
    public String[][] search(AccountSearchQuery accountSearchQuery) {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        try{
            ppsm = connection.prepareStatement(searchAccountSQL);
            ppsm.setString(1, accountSearchQuery.id.equals("")? null : accountSearchQuery.id);
            ppsm.setString(2, accountSearchQuery.type.toString().equals("")? null : accountSearchQuery.type.toString());
            ppsm.setString(3, accountSearchQuery.username.equals("")? null : accountSearchQuery.username);
            ppsm.setString(4, accountSearchQuery.name.equals("")? null : accountSearchQuery.name);
            ppsm.setString(5, accountSearchQuery.race.toString().equals("")? null : accountSearchQuery.race.toString());
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

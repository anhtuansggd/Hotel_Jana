package Controllers;

import Modules.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Enum.valueOf;

public class LogInController extends Controller{
    public LogInController(){
        super();
    }

    public Account Login(){
        String user_name = "RemyRat";
        String password = "Remy123";
        try{
            ppsm = connection.prepareStatement(
                    "SELECT * \n" +
                            "FROM account\n" +
                            "WHERE user_name=? AND password=?;");
            ppsm.setString(1, user_name);
            ppsm.setString(2, password);
            ResultSet set = ppsm.executeQuery();
            set.next();
            System.out.println("Login succeeded");
            return new Account(set.getString(1), Account.AccountType.valueOf(set.getString(2).toUpperCase()), set.getString(3), set.getString(4),  set.getString(5) , Account.Race.valueOf(set.getString(6).toUpperCase()));
        }catch (SQLException e){
            System.out.println("Login  failed");
        }
        close();
        return null;
    }
}

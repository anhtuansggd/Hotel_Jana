package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController extends Controller{
    public LogInController(){
        super();
    }

    public void Login(){
        String user_name = "RemyRat";
        String password = "Remy123";
        PreparedStatement ppsm = null;
        try{
            ppsm = connection.prepareStatement(
                    "SELECT * \n" +
                            "FROM account\n" +
                            "WHERE user_name=? AND password=?;");
            ppsm.setString(1, user_name);
            ppsm.setString(2, password);
            ResultSet set = ppsm.executeQuery();
            while(set.next()){
                String s = set.getString(1);
                System.out.println(s);
            }
            System.out.println("Room delete succeeded");
        }catch (SQLException e){
            System.out.println("Room delete failed");
        }

    }
}

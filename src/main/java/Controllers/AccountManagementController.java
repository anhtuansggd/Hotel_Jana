package Controllers;

import Modules.Account;
import java.sql.*;

import java.util.ArrayList;

public class AccountManagementController extends Controller {
    public AccountManagementController() {
        super();
    }

    public TableState getAll() {
        return _getAll("account");
    }

    public TableState addAccount() {

        return null;   
    }
}

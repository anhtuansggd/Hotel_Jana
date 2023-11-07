package Controllers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.*;
import java.io.*;
import Controllers.DatabaseManager.*;



public interface Controller<T> {
    /**
     * Due to
     */
    public TableState add(T entity);
    public TableState update(T entity);
    public TableState delete(T entity);


}




package me.kobeshow.springbootproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao{

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/springtest", "kobeshow", "eptest00");
    }
}

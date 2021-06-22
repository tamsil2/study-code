package me.kobeshow.springbootproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mariadb.jdbc.driver");
        return DriverManager.getConnection("jdbc:mariadb://localhost/springtest", "kobeshow", "eptest00");
    }
}

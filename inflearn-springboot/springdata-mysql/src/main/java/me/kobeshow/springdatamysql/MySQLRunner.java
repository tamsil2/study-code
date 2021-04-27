package me.kobeshow.springdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class MySQLRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(Connection connection = dataSource.getConnection()) {
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

//            Statement statement = connection.createStatement();
//            String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, NAME VARCHAR(256), PRIMARY KEY(ID))";
//            statement.executeUpdate(sql);
        }

//        jdbcTemplate.execute("INSERT INTO USER VALUES(1, 'tamsil')");
    }
}

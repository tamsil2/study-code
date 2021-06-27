package me.kobeshow.springbootproject.dao;

import me.kobeshow.springbootproject.domain.Level;
import me.kobeshow.springbootproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoJdbc implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    };

    @Override
    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id, name, password, level, login, recommend, email) values(?,?,?,?,?,?,?)",
                user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getEmail());
    }

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, this.userMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update(
                "update users set name = ?, password = ?, level = ?, login = ?, recommend = ?, email = ? where id = ? ",
                user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getEmail(), user.getId()
        );
    }
}

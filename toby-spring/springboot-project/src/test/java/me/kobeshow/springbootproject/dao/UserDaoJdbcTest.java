package me.kobeshow.springbootproject.dao;

import me.kobeshow.springbootproject.domain.Level;
import me.kobeshow.springbootproject.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoJdbcTest {

    @Autowired
    UserDao userDao;

    @Test
    public void addTest() {
        User user = new User();
        user.setId("sdfsfs");
        user.setName("홍준의");
        user.setPassword("test");
        user.setLevel(Level.BASIC);
        user.setLogin(1);
        user.setRecommend(1);
        user.setEmail("test1@email.com");

        userDao.add(user);
    }
}

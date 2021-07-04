package me.kobeshow.dao;

import me.kobeshow.domain.Level;
import me.kobeshow.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
class UserDaoJdbcTest {

    @Autowired
    UserDao userDao;

    @Test
    public void addTest() throws Exception{
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

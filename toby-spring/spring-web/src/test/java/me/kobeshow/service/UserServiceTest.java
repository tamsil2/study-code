package me.kobeshow.service;

import me.kobeshow.dao.UserDao;
import me.kobeshow.domain.Level;
import me.kobeshow.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.kobeshow.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static me.kobeshow.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
@Transactional
class UserServiceTest {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    UserService userService;

    @Autowired
    MailSender mailSender;

    @Autowired
    UserDao userDao;

    @Autowired
    ApplicationContext context;

    List<User> users;

    @BeforeEach
    public void setUp() {
        users = Arrays.asList(
                new User("p1", "박범진", "bumjin", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0, "test1@email.com"),
                new User("p2", "강명성", "joytouch", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "test2@email.com"),
                new User("p3", "신승한", "erwins", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD-1, "test3@email.com"),
                new User("p4", "이상호", "fdjshf1", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD, "test4@email.com"),
                new User("p5", "오민규", "green", Level.GOLD, 100, 100, "test5@email.com")
        );
    }

    @Test
    public void addTest() throws Exception {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel()).isEqualTo(userWithLevel.getLevel());
        assertThat(userWithoutLevelRead.getLevel()).isEqualTo(userWithoutLevel.getLevel());
    }

    @Test
    @Transactional
    public void transactionSync() throws Exception {
        userService.deleteAll();
        userService.add(users.get(0));
        userService.add(users.get(1));
    }

    private void checkLevelUpgraded(User user, boolean upgraded) throws Exception {
        User userUpdate = userDao.get(user.getId());
        if (upgraded) {
            assertThat(userUpdate.getLevel()).isEqualTo(user.getLevel().nextLevel());
        } else {
            assertThat(userUpdate.getLevel()).isEqualTo(user.getLevel());
        }
    }

    private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) {
        assertThat(updated.getId()).isEqualTo(expectedId);
        assertThat(updated.getLevel()).isEqualTo(expectedLevel);
    }

    private void checkLevel(User user, Level expectedLevel) throws Exception {
        User userUpdate = userDao.get(user.getId());
        assertThat(userUpdate.getLevel()).isEqualTo(expectedLevel);
    }

    static class MockMailSender implements MailSender {
        private List<String> requests = new ArrayList<>();

        public List<String> getRequests() {
            return requests;
        }

        public void send(SimpleMailMessage mailMessage) throws MailException {
            requests.add(mailMessage.getTo()[0]);
        }

        public void send(SimpleMailMessage[] mailMessages) throws MailException {

        }
    }
}

package me.kobeshow.springbootproject.service;

import me.kobeshow.springbootproject.dao.UserDao;
import me.kobeshow.springbootproject.domain.Level;
import me.kobeshow.springbootproject.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Arrays;
import java.util.List;

import static me.kobeshow.springbootproject.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static me.kobeshow.springbootproject.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockTest {

    private List<User> users;

    @Mock
    UserDao mockUserDao;

    @Mock
    MailSender mockMailSender;

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
    public void mockUpgradeLevels() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();

        when(mockUserDao.getAll()).thenReturn(this.users);
        userService.setUserDao(mockUserDao);
        userService.setMailSender(mockMailSender);
        userService.upgradeLevels();

        verify(mockUserDao, times(2)).update(any(User.class));
        verify(mockUserDao).update(users.get(1));
        assertThat(users.get(1).getLevel()).isEqualTo(Level.SILVER);
        verify(mockUserDao).update(users.get(3));
        assertThat(users.get(3).getLevel()).isEqualTo(Level.GOLD);

        ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender, times(2)).send(mailMessageArg.capture());
        List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
        assertThat(mailMessages.get(0).getTo()[0]).isEqualTo(users.get(1).getEmail());
        assertThat(mailMessages.get(1).getTo()[0]).isEqualTo(users.get(3).getEmail());
    }
}

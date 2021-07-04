package me.kobeshow.service;

import me.kobeshow.dao.UserDao;
import me.kobeshow.domain.Level;
import me.kobeshow.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Qualifier("userServiceImpl")
public class UserServiceImpl implements UserService{
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MailSender mailSender;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void add(User user) throws Exception {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    @Override
    public User get(String id) throws Exception {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userDao.getAll();
    }

    @Override
    public void deleteAll() throws Exception{
        userDao.deleteAll();
    }

    @Override
    public void update(User user) throws Exception{
        userDao.update(user);
    }

    @Override
    public void upgradeLevels() throws Exception {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
                sendUpgradeEmail(user);
            }
        }
    }

    protected void upgradeLevel(User user) throws Exception{
        user.upgradeLevel();
        userDao.update(user);
    }

    private void sendUpgradeEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());

        this.mailSender.send(mailMessage);
    }

    private boolean canUpgradeLevel(User user) {
        Level currrentLevel = user.getLevel();
        switch (currrentLevel) {
            case BASIC:
                return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER:
                return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown Level: " + currrentLevel);
        }
    }
}

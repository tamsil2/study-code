package me.kobeshow.service;

import me.kobeshow.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("testUserServiceImpl")
public class TestUserServiceImpl extends UserServiceImpl{
    private String id = "fdjshf1";

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgradeLevel(user);
    }

    public List<User> getAll() {
        for (User user : super.getAll()) {
            super.update(user);
        }
        return null;
    }

    static class TestUserServiceException extends RuntimeException {
    }
}

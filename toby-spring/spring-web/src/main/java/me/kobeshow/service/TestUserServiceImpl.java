package me.kobeshow.service;

import me.kobeshow.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("testUserServiceImpl")
public class TestUserServiceImpl extends UserServiceImpl{
    private String id = "fdjshf1";

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgradeLevel(user);
    }

    static class TestUserServiceException extends RuntimeException {
    }
}

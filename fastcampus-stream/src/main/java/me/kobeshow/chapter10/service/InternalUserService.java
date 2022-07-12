package me.kobeshow.chapter10.service;

import me.kobeshow.chapter10.model.User;

public class InternalUserService extends AbstractUserService {

    @Override
    protected boolean validateUser(User user) {
        System.out.println("Validating internal user " + user.getName());
        return true;
    }

    @Override
    protected void writeToDB(User user) {
        System.out.println("Writing user " + user.getName() + " to internal DB");
    }
}

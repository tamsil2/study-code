package me.kobeshow.springbootproject.service;

import me.kobeshow.springbootproject.domain.User;

import java.sql.SQLException;

public interface UserService {
    void add(User user);
    void upgradeLevels() throws SQLException;
}

package me.kobeshow.service;


import me.kobeshow.domain.User;

import java.sql.SQLException;

public interface UserService {
    void add(User user);
    void upgradeLevels() throws SQLException;
}

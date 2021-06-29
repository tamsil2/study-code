package me.kobeshow.service;


import me.kobeshow.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    void update(User user);

    void upgradeLevels() throws SQLException;
}

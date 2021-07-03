package me.kobeshow.dao;

import me.kobeshow.domain.User;

import java.util.List;

public interface UserDao {
    void add(final User user) throws Exception;

    User get(String id);

    void deleteAll();

    int getCount();

    List<User> getAll();

    void update(User user);
}

package me.kobeshow.springbootproject.dao;

import me.kobeshow.springbootproject.domain.User;

import java.util.List;

public interface UserDao {
    void add(final User user);

    User get(String id);

    void deleteAll();

    int getCount();

    List<User> getAll();

    void update(User user);
}

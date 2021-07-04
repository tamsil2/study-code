package me.kobeshow.dao;

import me.kobeshow.domain.User;

import java.util.List;

public interface UserDao {
    void add(final User user) throws Exception;

    User get(String id) throws Exception;

    void deleteAll() throws Exception;

    int getCount() throws Exception;

    List<User> getAll() throws Exception;

    void update(User user) throws Exception;
}

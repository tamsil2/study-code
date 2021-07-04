package me.kobeshow.service;


import me.kobeshow.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
public interface UserService {
    void add(User user) throws Exception;
    void deleteAll() throws Exception;
    void update(User user) throws Exception;
    void upgradeLevels() throws Exception;

    @Transactional(readOnly = true)
    User get(String id) throws Exception;

    @Transactional(readOnly = true)
    List<User> getAll() throws Exception;
}

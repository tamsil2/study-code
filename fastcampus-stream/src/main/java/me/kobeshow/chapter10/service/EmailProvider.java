package me.kobeshow.chapter10.service;

import me.kobeshow.chapter10.model.User;

public interface EmailProvider {
    String getEmail(User user);
}

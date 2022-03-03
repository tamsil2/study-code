package me.kobeshow.chapter6.model;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private List<Integer> friendUserId;

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public User setVerified(boolean verified) {
        isVerified = verified;
        return this;
    }

    public List<Integer> getFriendUserId() {
        return friendUserId;
    }

    public User setFriendUserId(List<Integer> friendUserId) {
        this.friendUserId = friendUserId;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", isVerified=" + isVerified +
                ", friendUserId=" + friendUserId +
                '}';
    }
}

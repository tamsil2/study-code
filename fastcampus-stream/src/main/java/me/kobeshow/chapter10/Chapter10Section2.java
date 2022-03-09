package me.kobeshow.chapter10;

import me.kobeshow.chapter10.model.User;

public class Chapter10Section2 {
    public static void main(String[] args) {
//        User user = User.builder(1, "Alice")
//                .withEmailAddress("alice@fastcampus.co.kr")
//                .withVerified(true)
//                .build();

        User user = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = true;
                }).build();
        System.out.println(user);
    }
}

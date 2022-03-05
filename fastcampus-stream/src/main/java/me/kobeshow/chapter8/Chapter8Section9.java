package me.kobeshow.chapter8;

import me.kobeshow.chapter8.model.User;
import me.kobeshow.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Chapter8Section9 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 2, 1);
//        numbers.stream().forEach(number -> System.out.println("The number is " + number));
        numbers.forEach(number -> System.out.println("The number is " + number));

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");
        List<User> users = Arrays.asList(user1, user2, user3);

        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail);

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        }

        // 위의 index가 있는 for문을 IntStream을 통해서 해결할 수 있다
        IntStream.range(0, users.size()).forEach(i -> {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        });
    }
}

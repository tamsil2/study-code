package me.kobeshow.chapter8;

import me.kobeshow.chapter8.model.User;
import me.kobeshow.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter8Section10 {
    public static void main(String[] args) {
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
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setEmailAddress("david@fastcampus.co.kr")
                .setVerified(true);
        User user5 = new User()
                .setId(105)
                .setName("Eve")
                .setEmailAddress("eve@fastcampus.co.kr")
                .setVerified(false);
        User user6 = new User()
                .setId(106)
                .setName("Frank")
                .setEmailAddress("frank@fastcampus.co.kr")
                .setVerified(false);
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);
        EmailService emailService = new EmailService();

        // TODO 검증되지 않은 User들에게 Email을 보냈던 기존 Stream과 동일한 처리를 병렬Stream으로 처리하였을 경우 속도 비교

        // TODO 기존 Stream
        long startTime = System.currentTimeMillis();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail);
        long endTime = System.currentTimeMillis();;
        System.out.println("Sequential: " + (endTime - startTime) + "ms");

        // TODO 동일한 Stream에서 병렬처리 Stream을 추가한 경우
        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + "ms");

        // TODO 중간처리가 병렬로 처리가 된 이후에 종결처리에서는 순서대로 처리된다.
        List<User> processedUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());
        System.out.println(processedUsers);
    }
}

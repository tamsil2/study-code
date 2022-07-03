package me.kobeshow.chapter8;

import me.kobeshow.chapter8.model.Order;
import me.kobeshow.chapter8.model.OrderLine;
import me.kobeshow.chapter8.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Chapter8Section4 {

    public static void main(String[] args) {
        // TODO reduce를 이용하여 값 모두 더하기
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int sum = numbers.stream()
                .reduce((x, y) -> x + y)
                .get();
        System.out.println(sum);

        // TODO reduce를 이용하여 최소값 찾기
        int min = numbers.stream()
                .reduce((x, y) -> x > y ? x : y).get();

        // TODO reduce 초기값을 지정하여 모든 수를 곱하기
        int product = numbers.stream()
                .reduce(1, (x, y) -> x * y);
        System.out.println(product);

        // TODO String의 형태로 숫자가 넘어왔다고 가정, List 안에 있는 숫자들의 합을 알고 싶을 경우
        List<String> numberStrList = Arrays.asList("3", "2", "5", "-4");
        int sumOfNumberStrList = numberStrList.stream()
                .map(Integer::parseInt)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberStrList);
        numberStrList.stream()
                .reduce(0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
        System.out.println(sumOfNumberStrList);

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setFriendUserIds(Arrays.asList(204, 205, 207));
        List<User> users = Arrays.asList(user1, user2, user3);

        // TODO User의 친구들의 총 합 구하기
        int sumOfNumberOfFriends = users.stream()
                .map(User::getFriendUserIds)
                .map(List::size)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberOfFriends);

        Order order1 = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000))));
        Order order2 = new Order()
                .setId(1002L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(3000))));
        Order order3 = new Order()
                .setId(1003L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000))));
        List<Order> orders = Arrays.asList(order1, order2, order3);

        // TODO Order들의 OrderLine 값의 총합
        BigDecimal sumOfAmounts = orders.stream()
                .map(Order::getOrderLines)   // Stream<List<OrderLine>>
                .flatMap(List::stream)       // Stream<OrderLine>
                .map(OrderLine::getAmount)   // Stream<BigDecimal>
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sumOfAmounts);
    }
}

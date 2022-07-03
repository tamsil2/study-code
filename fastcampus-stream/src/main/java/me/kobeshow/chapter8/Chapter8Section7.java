package me.kobeshow.chapter8;

import me.kobeshow.chapter8.model.Order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static me.kobeshow.chapter8.model.Order.*;

public class Chapter8Section7 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        // TODO 같은 1의 자리수를 같는 List로 Grouping
        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10));
        System.out.println(unitDigitMap);

        // TODO 같은 1의 자리수를 같는 Set으로 Grouping
        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
        System.out.println(unitDigitSet);

        // TODO 같은 1의 자리수를 같은 Grouping인데 숫자에 String을 붙힌 List로 만들어서 Grouping하기
        Map<Integer, List<String>> unitDigitStrMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10, Collectors.mapping(number -> "unit digit is " + number, Collectors.toList())));
        System.out.println(unitDigitStrMap.get(3));

        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(OrderStatus.CREATED);
        Order order2 = new Order()
                .setId(1002L)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(OrderStatus.ERROR);
        Order order3 = new Order()
                .setId(1003L)
                .setAmount(BigDecimal.valueOf(3000))
                .setStatus(OrderStatus.ERROR);
        Order order4 = new Order()
                .setId(1004L)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(OrderStatus.PROCESSED);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // TODO OrderStatus끼리 묶어서 Map을 만드는 Grouping
        Map<OrderStatus, List<Order>> orderStatusMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        System.out.println(orderStatusMap);

        // TODO Key : OrderStatus, Value : List 안에 있는 Order들의 값의 합

        Map<OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.mapping(Order::getAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        System.out.println(orderStatusToSumOfAmountMap);
    }
}

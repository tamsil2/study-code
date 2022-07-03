package me.kobeshow.chapter8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter8Section5 {
    public static void main(String[] args) {
        // TODO List의 형태로 출력
        List<Integer> numberList = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toList());
        System.out.println(numberList);

        // TODO SET의 형태로 출력
        Set<Integer> numberOfSet = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toSet());
        System.out.println(numberOfSet);

        // TODO Mapping
        List<Integer> numberList2 = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
        System.out.println(numberList2);

        Set<Integer> numberSet2 = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet()));
        System.out.println(numberSet2);

        // TODO 전체합을 reducing으로 구하기
        int sum = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.reducing(0, (x, y) -> x + y));
        System.out.println(sum);
    }
}

package me.kobeshow.chapter8;

import java.util.Optional;
import java.util.stream.Stream;

public class Chapter8Section3 {
    public static void main(String[] args) {
        // TODO 음수 아무거나 꺼내오고 싶다
        Optional<Integer> anyNegativeInteger = Stream.of(3, 2, -5, 6)
                .filter(x -> x < 0)
                .findAny();
        System.out.println(anyNegativeInteger.get());

        // TODO 첫번째 양수를 찾아본다
        Optional<Integer> firstPositiveInteger = Stream.of(3, 2, -5, 6)
                .filter(x -> x > 0)
                .findFirst();
        System.out.println(firstPositiveInteger.get());
    }
}

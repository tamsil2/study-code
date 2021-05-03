package chapter05;

import chapter04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quiz {
    public static void main(String[] args) {
        // 5-1
        List<Dish> dishes = Dish.menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());

        // 5-2 매핑
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());

        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);
        List<int[]> pairs2 =
                numbers3.stream()
                .flatMap(i -> numbers4.stream().filter(j -> (i + j) % 3 == 0)
                .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        // 5-4 피보나치 수열 만들기
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
}

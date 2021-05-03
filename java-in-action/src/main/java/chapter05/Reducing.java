package chapter05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        // 초기값이 없는 경우
        Optional<Integer> sum3 = numbers.stream().reduce((a, b) -> (a + b));
        System.out.println(sum3);

        // 최대값 찾기
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max);

        // 최소값 찾기
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println(min);
    }
}

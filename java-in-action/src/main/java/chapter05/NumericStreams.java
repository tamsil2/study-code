package chapter05;

import chapter04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static chapter04.Dish.menu;

public class NumericStreams {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);

        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                .mapToObj((
                        b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                ).filter(t -> t[2] % 1 == 0));
    }
}

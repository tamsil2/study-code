package chapter04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamVSCollection {
    public static void main(String[] args) throws IOException {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
//        s.forEach(System.out::println); // java.lang.IllegalStateException: 스트림이 이미 소비되었거나 닫힘
    }

    public static void externalIteration(List<Dish> dishes) {
        List<String> names = new ArrayList<>();
        for(Dish dish : dishes) {
            names.add(dish.getName());
        }
    }

    public static void externalIteration2(List<Dish> dishes) {
        List<String> names = new ArrayList<>();
        Iterator<Dish> iterator = dishes.iterator();
        while(iterator.hasNext()) {
            Dish dish = iterator.next();
            names.add(dish.getName());
        }
    }

    public static void internalIteration(List<Dish> dishes) {
        List<String> names = dishes.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}

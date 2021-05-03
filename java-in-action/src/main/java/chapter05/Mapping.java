package chapter05;

import chapter04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter04.Dish.menu;

public class Mapping {
    public static void main(String[] args) {
        List<String> dishNames = menu.stream().map(Dish::getName).collect(Collectors.toList());

        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        List<Integer> dishnameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        // FlatMap
        List<String> uniqueCharacters =
                words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}

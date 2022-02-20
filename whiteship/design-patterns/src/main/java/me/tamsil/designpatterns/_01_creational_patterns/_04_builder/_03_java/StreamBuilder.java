package me.tamsil.designpatterns._01_creational_patterns._04_builder._03_java;

import java.util.stream.Stream;

public class StreamBuilder {
    public static void main(String[] args) {
        Stream.Builder<String> stringStreamBuilder = Stream.builder();
        Stream<String> names = stringStreamBuilder.add("kobeshow").add("whiteship").build();
        names.forEach(System.out::println);
    }
}

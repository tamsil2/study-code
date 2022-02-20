package me.kobeshow.java8stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("hong");
        names.add("tamsil");
        names.add("kobeshow");
        names.add("foo");

        List<String> collect = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase(Locale.ROOT);
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("======================");
        names.forEach(System.out::println);

        System.out.println("======================");
        List<String> collect2 = names.parallelStream().map(String::toUpperCase)
                .collect(Collectors.toList());
        collect2.forEach(System.out::println);

        // parallelStream을 썼을때
        List<String> collect3 = names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase(Locale.ROOT);
        }).collect(Collectors.toList());
        collect3.forEach(System.out::println);

        // 그냥 Stream을 썼을때
        List<String> collect4 = names.stream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase(Locale.ROOT);
        }).collect(Collectors.toList());
        collect4.forEach(System.out::println);
    }
}

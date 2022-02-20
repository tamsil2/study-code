package me.kobeshow.staticmethod;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("Tamsil");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();

        // Java8 API 기본 메소드와 스태틱 메소드
        List<String> name = new ArrayList<>();
        name.add("hong");
        name.add("tamsil");
        name.add("kobeshow");
        name.add("foo");

//        name.forEach(System.out::println);

        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator11 = spliterator.trySplit();
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("===============");
        while(spliterator11.tryAdvance(System.out::println));

        name.removeIf(s -> s.startsWith("k"));
        name.forEach(System.out::println);

        // Comparator
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());
    }
}

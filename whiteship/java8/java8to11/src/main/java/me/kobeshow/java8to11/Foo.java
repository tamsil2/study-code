package me.kobeshow.java8to11;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {

//        int baseNumber = 10;
//
//        RunSomething runSomething = number -> number + baseNumber;
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        Function<Integer, Integer> plus10_2 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        Function<Integer, Integer> multiply2AndPlus10 = plus10_2.compose(multiply2);
        System.out.println(plus10_2.andThen(multiply2).apply(2));

        // Consumer
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        // Supplier
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // BinaryOperator
        BinaryOperator<Integer> sum = (a, b) -> a + b;

        // Predicate
        Predicate<String> startsWithHong = (s) -> s.startsWith("hong");
        Predicate<Integer> isEven = (i) -> i%2 == 0;

        // UnaryOperator
        UnaryOperator<Integer> plus10_3 = (i) -> i + 10;
        UnaryOperator<Integer> multiply3 = (i) -> i * 2;
        System.out.println(plus10_3.andThen(multiply3).apply(2));


    }
}

package me.kobeshow.java8methodreff;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        // Static 메서드
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println(hi.apply("tamsil"));

        // 인스턴스 메서드
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("hong"));

        // 생성자
        Supplier<Greeting> newGreeting = Greeting::new;
        newGreeting.get();

        // 생성자(매개변수 있음)
        Function<String, Greeting> hongGreeting = Greeting::new;
        Greeting tamsil = hongGreeting.apply("tamsil");
        System.out.println(tamsil.getName());

        // 임의의 객체에 인스턴스 메서드 참조
        String[] names = {"hong", "tamsil", "kobeshow"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}

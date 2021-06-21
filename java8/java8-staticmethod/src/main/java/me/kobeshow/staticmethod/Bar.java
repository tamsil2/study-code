package me.kobeshow.staticmethod;

public interface Bar extends Foo{

    default void printNameUpperCase() {
        System.out.println("BAR");
    }
}
